package network;

import gamelogic.Command;
import gamelogic.Gamelogic;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class ServerNetwork implements Runnable {

    private final ServerNetworkMessageHandler messageHandler;
    private Thread clientAcceptorThread;
    private ArrayList<NetworkMessage> receivedMessages;
    ServerSocket serverSocket;
    private ArrayList<ClientConnection> clients;

    /**
     * Initializes the server and starts accepting clients.
     *
     * @param messageHandler the MessageHandler to handle all received messages
     * from clients
     */
    public ServerNetwork(ServerNetworkMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
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

    public void broadcastMessage(NetworkMessage message) {
        clients.stream().forEach((client) -> {
            try {
                client.output.writeObject(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Return a list of the names of the connected clients.
     *
     * @return a list of the connected clients
     */
    public List<String> getConnectedClients() {
        ArrayList<String> names = new ArrayList<>();
        clients.stream().forEach((client) -> {
            names.add(client.name);
        });
        return names;
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
                clients.add(connection);
                System.out.println("[SERV]: New client " + connection.name);

                Thread messageReceiveThread = new Thread(() -> {
                    try {
                        while (true) {
                            NetworkMessage message = (NetworkMessage) connection.input.readObject();
                            message.receivedFrom = connection.name;
                            receivedMessages.add(message);
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

    /**
     * Call the ServerNetworkMessageHandler for every message received since the
     * last call to this method.
     */
    public void handleMessages() {
        for (NetworkMessage message : receivedMessages) {
            message.handleOnServer(messageHandler);
        }
        receivedMessages.clear();
    }

}
