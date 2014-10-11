package main;

import client.Client;
import server.Server;

/**
 *
 * @author Michael
 */
public class TwoClientsOneServer {

    private final Thread serverThread;
    private final Thread clientThread1;
    private final Thread clientThread2;

    public TwoClientsOneServer() {
        serverThread = new Thread(() -> {
            Server.main(new String[]{});
        });
        serverThread.setName("ServerThread");
        serverThread.start();

        try {
            Thread.sleep(1); // use higher values for woodpc
        } catch (InterruptedException ex) {

        }

        clientThread1 = new Thread(() -> {
            Client.main(new String[]{"--left", "--Klaus", "--autoconnect"});
        });
        clientThread1.setName("clientThread1");
        clientThread1.start();

        clientThread2 = new Thread(() -> {
            Client.main(new String[]{"--right", "--Peter", "--autoconnect"});
        });
        clientThread2.setName("clientThread2");
        clientThread2.start();

    }

    public static void main(String args[]) {
        new TwoClientsOneServer();
    }

}
