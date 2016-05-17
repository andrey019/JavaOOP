package HomeWork.Lesson11.Task3;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.Scanner;

class Client {
    private static void connectToServer(String name, int port) {
        try {
            Socket socket = new Socket(name, port);
            System.out.println("Connected: " + socket.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("Server response: " + in.readUTF());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type in '1' to connect...");
            if (scanner.nextLine().equalsIgnoreCase("1")) {
                connectToServer("localhost", 20000);
                System.out.println("Connection is closed");
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        start();
    }
}
