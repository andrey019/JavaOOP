package HomeWork.Lesson12.P2PChat;

import java.net.Socket;
import java.util.ArrayList;

class ClientReceiving extends Thread {
    private ArrayList<Socket> clients;

    ClientReceiving(ArrayList<Socket> clients) {
        this.clients = clients;
    }

    public void run() {
        while (!isInterrupted()) {
            for (Socket socket : clients) {
                try {
                    if (socket.getInputStream().available() > 0) {
                        Message message = Message.readFromStream(socket.getInputStream());
                        if (!message.isTechInfoMessage()) {
                            System.out.println(message.toString());
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
