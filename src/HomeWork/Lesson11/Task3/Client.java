package HomeWork.Lesson11.Task3;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.Scanner;

class Client {
    private String hostName;
    private int port;

    Client(String hostName, int port) throws Exception {
        this.hostName = hostName;
        this.port = port;
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket(hostName, port);
            System.out.println("Connected to: " + socket.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("Server response: " + in.readUTF());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type in '1' to connect...");
            if (scanner.nextLine().equalsIgnoreCase("1")) {
                connectToServer();
                System.out.println("Connection is closed");
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 20000);
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
