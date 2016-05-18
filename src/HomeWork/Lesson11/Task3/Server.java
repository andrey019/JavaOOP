package HomeWork.Lesson11.Task3;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server extends Thread {
    private int requestCounter;
    private ServerSocket serverSocket;
    private StringBuilder environment;

    Server(int port) throws Exception {
        this.serverSocket = new ServerSocket(port);
        this.requestCounter = 0;
        this.environment = new StringBuilder();
        environment.append(System.getenv("COMPUTERNAME") + "\r\n");
        environment.append(System.getenv("OS") + "\r\n");
        environment.append(System.getenv("PROCESSOR_IDENTIFIER"));
    }

    public void run() {
        try {
            Socket socket;
            System.out.println("Server is running...");
            while (!isInterrupted()) {
                socket = serverSocket.accept();
                System.out.println("Connected to: " + socket.getRemoteSocketAddress());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("Your request number is " + requestCounter++ + "\r\n" + environment);
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server(20000);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
