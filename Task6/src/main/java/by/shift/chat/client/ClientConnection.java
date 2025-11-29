package by.shift.chat.client;

import by.shift.chat.common.model.Message;
import by.shift.chat.common.model.MessageType;
import by.shift.chat.common.util.MapperCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.slf4j.LoggerFactory.getLogger;

@RequiredArgsConstructor
class ClientConnection {

    private static final Logger LOG = getLogger(ClientConnection.class);

    private static final int RETRIES = 50;

    private final String host;

    private final int port;

    private final String userName;

    private final ObjectMapper objectMapper = MapperCreator.getMAPPER();

    private Socket socket;

    private PrintWriter out;

    @Setter
    private Consumer<String> text;

    @Setter
    private Consumer<List<String>> users;

    @Setter
    private Consumer<String> error;

    private boolean running = true;

    void start() {
        new Thread(this::run).start();
    }

    void stop() {
        running = false;
        closeSocket();
    }

    void sendChatMessage(String text) {
        if (text.isEmpty() || out == null) {
            return;
        }
        Message message = Message.builder()
                .messageType(MessageType.CHAT)
                .sender(userName)
                .text(text)
                .timestamp(LocalDateTime.now())
                .build();
        try {
            String json = objectMapper.writeValueAsString(message);
            out.println(json);
        } catch (JsonProcessingException e) {
            LOG.error("Failed to serialize chat message for user {}", userName, e);
        }
    }

    private void closeSocket() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            LOG.warn("Failed to close client socket", e);
        }
    }

    private void run() {
        int retries = RETRIES;
        while (running && retries > 0) {
            try {
                connectAndListen();
                return;
            } catch (IOException e) {
                if (!running) {
                    return;
                }
                LOG.error("Connection error for user {}", userName, e);
                addText("Connection lost. Retrying...");
                sleep();
                retries--;
            }
        }
        addText("Failed to reconnect");
    }

    private void connectAndListen() throws IOException {
        try (Socket s = new Socket(host, port);
             PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            this.socket = s;
            this.out = pw;

            sendJoinMessage();
            LOG.debug("Connected to {}:{}", host, port);

            String line;
            while ((line = in.readLine()) != null) {
                Message message = objectMapper.readValue(line, Message.class);
                processIncomingMessage(message);
            }
        } finally {
            closeSocket();
        }
    }

    private void sendJoinMessage() {
        Message join = Message.builder()
                .messageType(MessageType.JOIN)
                .sender(userName)
                .text("")
                .timestamp(LocalDateTime.now())
                .build();
        try {
            out.println(objectMapper.writeValueAsString(join));
        } catch (JsonProcessingException e) {
            LOG.error("Failed to serialize join message for user {}", userName, e);
        }
    }

    private void processIncomingMessage(Message message) {
        switch (message.getMessageType()) {
            case CHAT -> addText(formatMessage(message));
            case JOIN, LEAVE -> addText(message.getText());
            case ERROR -> addError(message.getText());
            case USERLIST -> updateUsers(message.getText());
            default -> LOG.warn("Unknown message type {}", message.getMessageType());
        }
    }

    private void updateUsers(String users) {
        List<String> usersList = users.isEmpty() ? List.of() : Arrays.asList(users.split(","));
        if (this.users != null) {
            this.users.accept(usersList);
        }
    }

    private String formatMessage(Message message) {
        return String.format("[%s] %s: %s", LocalDateTime.now(), message.getSender(), message.getText());
    }

    private void addText(String text) {
        if (this.text != null) {
            this.text.accept(text);
        }
    }

    private void addError(String text) {
        if (this.text != null) {
            this.error.accept(text);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}