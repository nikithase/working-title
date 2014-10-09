package network;

import gamelogic.Command;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author Michael
 */
public class ClientNetwork {

    private final Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientNetwork() {
        socket = new Socket();
    }

    /**
     * Trys to connect to the host
     *
     * @param host name of the host
     * @param port TCP Port
     * @param name Name of the player
     * @return true if connection success
     */
    public boolean tryConnect(String host, int port, String name) {
        try {
            socket.connect(new InetSocketAddress(host, port));
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            output.writeUTF(name);
            output.flush();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * send a command to the host.
     *
     * @param command the command
     */
    public void sendCommand(Command command) {
        try {
           output.writeObject(command);
           output.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Wait until a message is received.
     *
     * @return receive an array of bytes from the host
     *
     * @return the command
     */
    public Command receiveCommand() {
        try {
            return (Command) input.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
