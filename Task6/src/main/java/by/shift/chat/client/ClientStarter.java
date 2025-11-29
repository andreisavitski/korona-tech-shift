package by.shift.chat.client;

import javax.swing.*;

class ClientStarter {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String host = JOptionPane.showInputDialog(
                    null, "host:", "localhost"
            );
            String port = JOptionPane.showInputDialog(
                    null, "port:", "8085"
            );
            String name = JOptionPane.showInputDialog(
                    null, "name:"
            );
            if (isValid(name, port, host)) {
                return;
            }
            try {
                ClientUI clientUI = new ClientUI(host.trim(), Integer.parseInt(port.trim()), name.trim());
                clientUI.show();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid port",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static boolean isValid(String name, String port, String host) {
        return name == null || name.isBlank() || port == null || port.isBlank() || host == null || host.isBlank();
    }
}