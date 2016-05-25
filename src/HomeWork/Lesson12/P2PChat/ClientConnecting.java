package HomeWork.Lesson12.P2PChat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ClientConnecting extends Thread {
    private ArrayList<Socket> clients;
    private ArrayList<InetAddress> clientsForMessage;
    private boolean startStandalone;
    private int port;
    private String connectTo;
    private ClientReceiving clientReceiving;

    ClientConnecting(boolean startStandalone, int port) {
        this.clients = new ArrayList<>();
        this.clientsForMessage = new ArrayList<>();
        this.startStandalone = startStandalone;
        this.port = port;
        this.clientReceiving = new ClientReceiving(clients);
    }

    ClientConnecting(boolean startStandalone, int port, String connectTo) {
        this.clients = new ArrayList<>();
        this.clientsForMessage = new ArrayList<>();
        this.startStandalone = startStandalone;
        this.port = port;
        this.connectTo = connectTo;
        this.clientReceiving = new ClientReceiving(clients);
    }

    public void run() {
        clientReceiving.start();
        if (startStandalone) {
            startStandalone();
        } else {
            startConnected();
            startStandalone();
        }
    }

    private void startStandalone() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                if (!clientsForMessage.contains(socket.getInetAddress())) {
                    clients.add(socket);
                    clientsForMessage.add(socket.getInetAddress());
                    sendClientsList();
                }
                System.out.println("[SYSTEM MESSAGE] " + clients.size() + " users available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void startConnected() {
        try {
            Socket socket = new Socket(connectTo, port);
            while (socket.getInputStream().available() < 1) {
                Thread.sleep(1);
            }
            Message message = Message.readFromStream(socket.getInputStream());
            if (message.isTechInfoMessage()) {
                for (InetAddress inetAddress : message.getClients()) {
                    Socket newSocket = new Socket(inetAddress, port);
                    if (!clientsForMessage.contains(newSocket.getInetAddress())) {
                        clients.add(newSocket);
                        clientsForMessage.add(inetAddress);
                    }
                }
            }
            System.out.println("[SYSTEM MESSAGE] " + clients.size() + " users available");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendClientsList() {
        Message message = new Message();
        message.setTechInfoMessage(true);
        message.setClients(clientsForMessage);
        sendMessage(message);
    }

    public void sendMessage(Message message) {
        for (Socket socket : clients) {
            try {
                message.writeToStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                clientsForMessage.remove(socket.getInetAddress());
                clients.remove(socket);
            }
        }
    }

    public ArrayList<Socket> getClients() {
        return clients;
    }
}
