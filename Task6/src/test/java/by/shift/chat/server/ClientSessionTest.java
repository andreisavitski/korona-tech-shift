package by.shift.chat.server;

import by.shift.chat.common.model.Message;
import by.shift.chat.common.model.MessageType;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class ClientSessionTest {

    ClientManager clientManager = new ClientManager();

    ClientSession clientSession = new ClientSession(clientManager, null);

    @Test
    void sendWhenValidJsonThenWriteToOut() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);

        Field out = ClientSession.class.getDeclaredField("out");
        out.setAccessible(true);
        out.set(clientSession, printWriter);

        Method send = ClientSession.class.getDeclaredMethod("send", String.class);
        send.setAccessible(true);
        send.invoke(clientSession, "{\"test\":1}");

        assertThat(stringWriter.toString()).contains("{\"test\":1}");
    }

    @Test
    void unregisterClientWhenUserNameInSetThenUnregisterAndSendLeave() throws Exception {
        clientManager.registerClient("user", clientSession);
        clientManager.addClient(clientSession);

        Field userName = ClientSession.class.getDeclaredField("userName");
        userName.setAccessible(true);
        userName.set(clientSession, "user");

        Method unregisterClient = ClientSession.class.getDeclaredMethod("unregisterClient");
        unregisterClient.setAccessible(true);
        unregisterClient.invoke(clientSession);

        assertThat(clientManager.getUserNames()).doesNotContain("user");
    }

    @Test
    void processMessageWhenValidJsonThenSendAll() throws Exception {
        String json = """
                {"messageType":"CHAT","sender":"user","text":"test","timestamp":"2025-01-01T00:00:00"}
                """.trim();
        BufferedReader reader = new BufferedReader(new StringReader(json));

        Method processMessage = ClientSession.class.getDeclaredMethod("processMessage", BufferedReader.class);
        processMessage.setAccessible(true);
        processMessage.invoke(clientSession, reader);

        assertThat(clientManager.getMessageHistory()).hasSize(1);

        Message message = clientManager.getMessageHistory().get(0);

        assertThat(message)
                .extracting(Message::getMessageType, Message::getText)
                .containsExactly(MessageType.CHAT, "test");
    }
}