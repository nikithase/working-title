package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Tobias Fleig <tobifleig@googlemail.com>
 */
class ClientConnection {

    public ObjectOutputStream output;
    public ObjectInputStream input;
    public String name;
    public Socket socket;

}
