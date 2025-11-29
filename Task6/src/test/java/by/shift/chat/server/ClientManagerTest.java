package by.shift.chat.server;

import by.shift.chat.common.model.Message;
import by.shift.chat.common.model.MessageType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientManagerTest {

    ClientManager clientManager = new ClientManager();

    @Test
    void registerClientWhenNewClientThenTrue() {
        boolean result = clientManager.registerClient("user", new ClientSession(clientManager, null));

        assertThat(result).isTrue();
    }

    @Test
    void registerClientWhenNameThatExistThenFalse() {
        clientManager.registerClient("user", new ClientSession(clientManager, null));
        boolean result = clientManager.registerClient("user", new ClientSession(clientManager, null));

        assertThat(result).isFalse();
    }

    @Test
    void unregisterClientRemoveFromClientSessionMap() {
        ClientSession clientSession = new ClientSession(clientManager, null);
        clientManager.registerClient("user", clientSession);
        clientManager.addClient(clientSession);
        clientManager.unregisterClient("user", clientSession);

        assertThat(clientManager.getUserNames()).doesNotContain("user");
    }


    @Test
    void sendAllMessageWhenChatThenAddInHistory() {
        clientManager.sendAll(Message.builder()
                .messageType(MessageType.CHAT)
                .text("test")
                .build());

        assertThat(clientManager.getMessageHistory()).hasSize(1);
    }

    @Test
    void sendAllMessageWhenNotChatThenNotAddInHistory() {
        clientManager.sendAll(Message.builder()
                .messageType(MessageType.JOIN)
                .text("test")
                .build());

        assertThat(clientManager.getMessageHistory()).isEmpty();
    }
}