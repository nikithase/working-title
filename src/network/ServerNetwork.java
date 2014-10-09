package network;

import gamelogic.Command;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import server.ClientConnection;

/**
 *
 * @author Michael
 */
public class ServerNetwork implements Runnable {

    private Thread clientAcceptorThread;
    private ArrayList<Command> receivedMessages;
    ServerSocket serverSocket;
    private ArrayList<ClientConnection> clients;

    /**
     * Initializes the server and starts accepting clients.
     */
    public ServerNetwork() {
        try {
            receivedMessages = new ArrayList<>();
            clients = new ArrayList<>();
            serverSocket = new ServerSocket(12345);
            clientAcceptorThread = new Thread(this);
            clientAcceptorThread.setName("ServerNetwork.ClientAcceptorThread");
            clientAcceptorThread.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Send message to all clients.
     *
     * @param command
     */
    public void sendMessageToAll(Command command) {
        clients.stream().forEach((client) -> {
            try {
                client.output.writeObject(command);
                client.output.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Return all received messages. The list can be empty if no messages are received yet.
     *
     * @return a list of all received messages
     */
    public List<Command> getMessages() {
        ArrayList<Command> messages = new ArrayList<>();
        messages.addAll(receivedMessages);
        receivedMessages.clear();
        return messages;
    }

    /**
     * Return a list of the names of the connected clients.
     *
     * @return a list of the connected clients
     */
    public List<String> getConnectedClients() {
        throw new UnsupportedOperationException("not implemented yet.");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientConnection connection = new ClientConnection();
                connection.socket = socket;
                connection.output = new ObjectOutputStream(socket.getOutputStream());
                connection.input = new ObjectInputStream(socket.getInputStream());
                connection.name = connection.input.readUTF();
                System.out.println("[SERV]: New client " + connection.name);

                Thread messageReceiveThread = new Thread(() -> {
                    try {
                        while (true) {
                            Command command = (Command) connection.input.readObject();
                            receivedMessages.add(command);
                        }
                    } catch (SocketException ex) {
                        System.out.println("[SERV]: " + connection.name + " disconnected (eof)");
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                        System.out.println("[SERV]: " + connection.name + " disconnected (exception)");
                    }
                });
                messageReceiveThread.setName("messageReceiveThread for " + connection.name);
                messageReceiveThread.start();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
