package network;

/**
 *
 * @author Michael
 */
public class ClientNetwork {

	/**
	 * Trys to connect to the host
	 * 
	 * @param host name of the host
	 * @param port TCP Port
	 * @param name Name of the player
	 * @return true if connection success
	 */
    public boolean tryConnect(String host, int port, String name) {
        return true;
    }

    /**
     * send a byte message to the host.
     * 
     * @param bytes
     */
    public void sendMessage(byte[] bytes) {

    }

    /**
     * receive an array of bytes from the host
     * 
     * @return the message
     */
    public byte[] receiveMessage() {
        return null;
    }
}
