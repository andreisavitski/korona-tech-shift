package by.shift.chat.server;

import by.shift.chat.common.model.Message;
import by.shift.chat.common.model.MessageType;
import by.shift.chat.common.util.MapperCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

@RequiredArgsConstructor
class ClientSession implements Runnable {

    private static final Logger LOG = getLogger(ClientSession.class);

    private static final String SERVER = "server";

    private final ClientManager clientManager;

    private final Socket socket;

    private final ObjectMapper objectMapper = MapperCreator.getMAPPER();

    private PrintWriter out;

    private String userName;

    @Override
    public void run() {
        try (socket; BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            if (requestToJoin(in)) {
                return;
            }
            reportForJoining();
            processMessage(in);
        } catch (IOException e) {
            LOG.error("I/O error in client session for remote address {}", socket.getRemoteSocketAddress(), e);
        } finally {
            unregisterClient();
        }
    }

    void send(String json) {
        out.println(json);
    }

    private boolean requestToJoin(BufferedReader in) throws IOException {
        String joinJson = in.readLine();
        if (joinJson == null) {
            return true;
        }
        Message joinMessage = objectMapper.readValue(joinJson, Message.class);
        String requestedName = joinMessage.getSender();
        if (!clientManager.registerClient(requestedName, this)) {
            Message error = Message.builder()
                    .messageType(MessageType.ERROR)
                    .sender(SERVER)
                    .text("Name already in use")
                    .timestamp(LocalDateTime.now())
                    .build();
            send(objectMapper.writeValueAsString(error));
            return true;
        }
        this.userName = requestedName;
        clientManager.addClient(this);
        LOG.debug("Client {} registered", userName);
        return false;
    }

    private void reportForJoining() {
        Message reportMessage = Message.builder()
                .messageType(MessageType.JOIN)
                .sender(SERVER)
                .text(String.format("User %s has joined", userName))
                .timestamp(LocalDateTime.now())
                .build();
        sendUserList();
        sendMessageHistory();
        clientManager.sendAll(reportMessage);
    }

    private void sendUserList() {
        Message userList = Message.builder()
                .messageType(MessageType.USERLIST)
                .sender(SERVER)
                .text(String.join(",", clientManager.getUserNames()))
                .timestamp(LocalDateTime.now())
                .build();
        clientManager.sendAll(userList);
    }

    private void processMessage(BufferedReader in) throws IOException {
        String line;
        while ((line = in.readLine()) != null) {
            Message incomingMessage = objectMapper.readValue(line, Message.class);
            clientManager.sendAll(incomingMessage);
        }
    }

    private void unregisterClient() {
        if (userName != null) {
            clientManager.unregisterClient(userName, this);
            Message left = Message.builder()
                    .messageType(MessageType.LEAVE)
                    .sender(SERVER)
                    .text(String.format("User %s left", userName))
                    .timestamp(LocalDateTime.now())
                    .build();
            clientManager.sendAll(left);
            sendUserList();
        }
    }

    private void sendMessageHistory() {
        for (Message message : clientManager.getMessageHistory()) {
            try {
                send(objectMapper.writeValueAsString(message));
            } catch (JsonProcessingException e) {
                LOG.error("Failed to serialize message for broadcast: {}", message, e);
            }
        }
    }
}