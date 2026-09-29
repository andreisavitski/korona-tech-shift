package by.shift.chat.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

class ClientUI {

    private JFrame frame;

    private JTextArea textArea;

    private JTextField textField;

    private JList<String> usersList;

    private final ClientConnection clientConnection;

    private final String userName;

    public ClientUI(String host, int port, String userName) {
        this.userName = userName;
        this.clientConnection = new ClientConnection(host, port, userName);
        initGui();
        subscribeEvents();
    }

    private void initGui() {
        frame = new JFrame("Chat: " + userName);
        textArea = new JTextArea();
        textArea.setEditable(false);
        textField = new JTextField();
        JButton sendButton = new JButton("Send");
        usersList = new JList<>(new DefaultListModel<>());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(textField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(new JScrollPane(usersList), BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        sendButton.addActionListener(e -> acceptMessage());
        textField.addActionListener(e -> acceptMessage());

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clientConnection.stop();
                frame.dispose();
            }
        });
    }

    public void show() {
        frame.setVisible(true);
        clientConnection.start();
    }

    private void subscribeEvents() {
        clientConnection.setText(this::addText);
        clientConnection.setUsers(this::updateUsers);
        clientConnection.setError(this::addError);
    }

    private void addText(String text) {
        SwingUtilities.invokeLater(() -> textArea.append(text + "\n"));
    }

    private void addError(String text) {
        JOptionPane.showMessageDialog(
                null,
                text,
                "Error",
                JOptionPane.ERROR_MESSAGE);
        clientConnection.stop();
    }

    private void updateUsers(List<String> users) {
        SwingUtilities.invokeLater(() -> {
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String user : users) {
                listModel.addElement(user);
            }
            usersList.setModel(listModel);
        });
    }

    private void acceptMessage() {
        String inputText = textField.getText().trim();
        if (inputText.isEmpty()) {
            return;
        }
        textField.setText("");
        clientConnection.sendChatMessage(inputText);
    }
}