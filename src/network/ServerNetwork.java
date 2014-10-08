package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael
 */
public class ServerNetwork implements Runnable {

    private Thread clientAcceptorThread;
    private ArrayList<byte[]> receivedMessages;
    ServerSocket serverSocket;
    private ArrayList<String> clients;
    private ArrayList<Socket> sockets;

    /**
     * Initializes the server and starts accepting clients.
     */
    public ServerNetwork() {
        try {
            receivedMessages = new ArrayList<>();
            clients = new ArrayList<>();
            sockets = new ArrayList<>();
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
     * @param message
     */
    public void sendMessageToAll(byte[] message) {
        for (Socket socket : sockets) {
            try {
                socket.getOutputStream().write(message);
                socket.getOutputStream().write("\n".getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Return all received messages. The list can be empty if no messages are received yet.
     *
     * @return a list of all received messages
     */
    public List<byte[]> getMessages() {
        ArrayList<byte[]> messages = new ArrayList<>();
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
        return clients;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String name = reader.readLine();
                clients.add(name);
                System.out.println("[SERV]: New client " + name);

                Thread messageReceiveThread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            while (true) {
                                String message = reader.readLine();
                                if (message == null) {
                                    System.out.println("[SERV]: " + name + " disconnected (eof)");
                                    break;
                                }
                                receivedMessages.add(message.getBytes());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            System.out.println("[SERV]: " + name + " disconnected (exception)");
                        }
                    }
                });
                messageReceiveThread.setName("messageReceiveThread for " + name);
                messageReceiveThread.start();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
