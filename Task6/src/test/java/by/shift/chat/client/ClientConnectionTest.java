package by.shift.chat.client;

import by.shift.chat.common.model.Message;
import by.shift.chat.common.model.MessageType;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ClientConnectionTest {

    private final ClientConnection clientConnection = new ClientConnection(
            "host",
            8085,
            "user"
    );

    @Test
    void sendChatMessageWhenEmptyTextThenDoNothing() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);

        Field out = ClientConnection.class.getDeclaredField("out");
        out.setAccessible(true);
        out.set(clientConnection, printWriter);
        clientConnection.sendChatMessage("");

        assertThat(stringWriter.toString()).isEmpty();
    }

    @Test
    void sendChatMessageWhenInvalidTextThenSendJson() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);

        Field out = ClientConnection.class.getDeclaredField("out");
        out.setAccessible(true);
        out.set(clientConnection, printWriter);
        clientConnection.sendChatMessage("test");

        assertThat(stringWriter.toString())
                .contains("\"messageType\":\"CHAT\"")
                .contains("\"sender\":\"user\"")
                .contains("\"text\":\"test\"");
    }

    @Test
    void updateUsersWhenEmptyLineThenReturnEmptyList() throws Exception {
        List<String> users = new ArrayList<>();
        clientConnection.setUsers(users::addAll);

        Method updateUsers = ClientConnection.class.getDeclaredMethod("updateUsers", String.class);
        updateUsers.setAccessible(true);
        updateUsers.invoke(clientConnection, "");

        assertThat(users).isEmpty();
    }

    @Test
    void updateUsersWhenValidLineThenReturnUserList() throws Exception {
        List<String> users = new ArrayList<>();
        clientConnection.setUsers(users::addAll);

        Method updateUsers = ClientConnection.class.getDeclaredMethod("updateUsers", String.class);
        updateUsers.setAccessible(true);
        updateUsers.invoke(clientConnection, "user1,user2,user3");

        assertThat(users).containsExactly("user1", "user2", "user3");
    }

    @Test
    void formatMessageWhenValidMessageThenReturnFormatText() throws Exception {
        Message message = Message.builder()
                .messageType(MessageType.CHAT)
                .sender("user")
                .text("test")
                .build();

        Method formatMessage = ClientConnection.class.getDeclaredMethod("formatMessage", Message.class);
        formatMessage.setAccessible(true);
        String result = (String) formatMessage.invoke(clientConnection, message);

        assertThat(result).contains("user").contains("test");
    }

    @Test
    void addTextWhenValidTextThenSendTextConsumer() throws Exception {
        List<String> messages = new ArrayList<>();
        clientConnection.setText(messages::add);

        Method method = ClientConnection.class.getDeclaredMethod("addText", String.class);
        method.setAccessible(true);
        method.invoke(clientConnection, "test");

        assertThat(messages).containsExactly("test");
    }

    @Test
    void processIncomingMessageWhenChatThenAddText() throws Exception {
        List<String> messages = new ArrayList<>();
        clientConnection.setText(messages::add);

        Message message = Message.builder()
                .messageType(MessageType.CHAT)
                .sender("user")
                .text("test")
                .build();

        invokeProcessIncomingMessage(message);

        assertThat(messages).hasSize(1);
        assertThat(messages.get(0)).contains("user").contains("test");
    }

    @Test
    void processIncomingMessageWhenJoinThenAddText() throws Exception {
        List<String> messages = new ArrayList<>();
        clientConnection.setText(messages::add);

        Message message = Message.builder()
                .messageType(MessageType.JOIN)
                .text("test")
                .build();

        invokeProcessIncomingMessage(message);

        assertThat(messages).containsExactly("test");
    }

    @Test
    void processIncomingMessageWhenUserListThenUpdatesUsers() throws Exception {
        List<String> users = new ArrayList<>();
        clientConnection.setUsers(users::addAll);

        Message message = Message.builder()
                .messageType(MessageType.USERLIST)
                .text("user1,user2")
                .build();

        invokeProcessIncomingMessage(message);

        assertThat(users).containsExactly("user1", "user2");
    }

    private void invokeProcessIncomingMessage(Message message) throws Exception {
        Method handleIncoming = ClientConnection.class.getDeclaredMethod("processIncomingMessage", Message.class);
        handleIncoming.setAccessible(true);
        handleIncoming.invoke(clientConnection, message);
    }
}