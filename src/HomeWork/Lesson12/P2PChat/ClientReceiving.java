package HomeWork.Lesson12.P2PChat;

import java.net.Socket;
import java.util.ArrayList;

class ClientReceiving extends Thread {
    private ArrayList<Socket> clients;
    private ClientConnecting clientConnecting;

    ClientReceiving(ArrayList<Socket> clients, ClientConnecting clientConnecting) {
        this.clients = clients;
        this.clientConnecting = clientConnecting;
    }

    public void run() {
        while (!isInterrupted()) {
            for (Socket socket : clients) {
                try {
                    if (socket.getInputStream().available() > 0) {
                        Message message = Message.readFromStream(socket.getInputStream());
                        if (!message.isTechInfoMessage()) {
                            System.out.println(message.toString());
                        } else {
                            clientConnecting.addClients(message.getClients());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
