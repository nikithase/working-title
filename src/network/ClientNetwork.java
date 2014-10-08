package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class ClientNetwork {

    private Socket socket;
    private BufferedReader reader;

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
            socket.getOutputStream().write((name + "\n").getBytes());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * send a byte message to the host.
     *
     * @param bytes
     */
    public void sendMessage(byte[] bytes) {
        try {
            socket.getOutputStream().write(bytes);
            socket.getOutputStream().write("\n".getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * <<<<<<< HEAD Wait until a message is received.
     *
     * @return ======= receive an array of bytes from the host
     *
     * @return the message >>>>>>> dce962ed3e23ca79f03dd5f259b6c9cecd07bd66
     */
    public byte[] receiveMessage() {
        try {
            return reader.readLine().getBytes();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
