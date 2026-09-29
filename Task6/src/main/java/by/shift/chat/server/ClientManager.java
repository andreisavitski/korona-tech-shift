package by.shift.chat.server;

import by.shift.chat.common.model.Message;
import by.shift.chat.common.model.MessageType;
import by.shift.chat.common.util.MapperCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.slf4j.LoggerFactory.getLogger;

class ClientManager {

    private static final Logger LOG = getLogger(ClientManager.class);

    private final Map<String, ClientSession> clientSessionMap = new ConcurrentHashMap<>();

    private final List<ClientSession> clients = new CopyOnWriteArrayList<>();

    private final List<Message> messageHistory = new CopyOnWriteArrayList<>();

    private final ObjectMapper objectMapper = MapperCreator.getMAPPER();

    boolean registerClient(String name, ClientSession clientSession) {
        return clientSessionMap.putIfAbsent(name, clientSession) == null;
    }

    void unregisterClient(String name, ClientSession clientSession) {
        clientSessionMap.remove(name, clientSession);
        clients.remove(clientSession);
        LOG.debug("Client {} unregistered", name);
    }

    void addClient(ClientSession clientSession) {
        clients.add(clientSession);
    }

    Set<String> getUserNames() {
        return clientSessionMap.keySet();
    }

    void sendAll(Message message) {
        if (message.getMessageType() == MessageType.CHAT) {
            messageHistory.add(message);
        }
        String json;
        try {
            json = objectMapper.writeValueAsString(message);
        } catch (IOException e) {
            LOG.error("Failed to serialize message for send all: {}", message, e);
            return;
        }
        for (ClientSession client : clients) {
            client.send(json);
        }
    }

    List<Message> getMessageHistory() {
        return messageHistory;
    }
}