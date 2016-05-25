package HomeWork.Lesson12.P2PChat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ClientConnecting extends Thread {
    private ArrayList<Socket> clientsSocket;
    private ArrayList<InetAddress> clientsAdress;
    private boolean startStandalone;
    private int port;
    private String connectTo;
    private ClientReceiving clientReceiving;

    ClientConnecting(boolean startStandalone, int port) {
        this.clientsSocket = new ArrayList<>();
        this.clientsAdress = new ArrayList<>();
        this.startStandalone = startStandalone;
        this.port = port;
        this.clientReceiving = new ClientReceiving(clientsSocket, this);
    }

    ClientConnecting(boolean startStandalone, int port, String connectTo) {
        this.clientsSocket = new ArrayList<>();
        this.clientsAdress = new ArrayList<>();
        this.startStandalone = startStandalone;
        this.port = port;
        this.connectTo = connectTo;
        this.clientReceiving = new ClientReceiving(clientsSocket, this);
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
                if (!clientsAdress.contains(socket.getInetAddress())) {
                    clientsSocket.add(socket);
                    clientsAdress.add(socket.getInetAddress());
                    sendClientsList();
                }
                System.out.println("[SYSTEM MESSAGE] " + clientsSocket.size() + " users available");
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
                    if (!clientsAdress.contains(newSocket.getInetAddress())) {
                        clientsSocket.add(newSocket);
                        clientsAdress.add(inetAddress);
                    }
                }
            }
            System.out.println("[SYSTEM MESSAGE] " + clientsSocket.size() + " users available");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addClients(ArrayList<InetAddress> newClients) {
        for (InetAddress inetAddress : newClients) {
            if (!clientsAdress.contains(inetAddress)) {
                try {
                    Socket socket = new Socket(inetAddress, port);
                    clientsSocket.add(socket);
                    clientsAdress.add(inetAddress);
                } catch (IOException e) {}
            }
        }
    }

    private void sendClientsList() {
        Message message = new Message();
        message.setTechInfoMessage(true);
        message.setClients(clientsAdress);
        sendMessage(message);
    }

    public void sendMessage(Message message) {
        for (Socket socket : clientsSocket) {
            try {
                message.writeToStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                clientsAdress.remove(socket.getInetAddress());
                clientsSocket.remove(socket);
            }
        }
    }

    public ArrayList<Socket> getClients() {
        return clientsSocket;
    }
}
