package by.shift.chat.server;

import org.slf4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.slf4j.LoggerFactory.getLogger;

class ServerStarter {

    private static final Logger LOG = getLogger(ServerStarter.class);

    public static void main(String[] args) {
        ServerConfiguration serverConfiguration = new ServerConfiguration();
        int port = serverConfiguration.getPort();

        ClientManager clientManager = new ClientManager();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOG.debug("Server started on port {}", port);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientSession handler = new ClientSession(clientManager, socket);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            LOG.error("I/O error in server main cycle on port {}", port, e);
        }
    }
}