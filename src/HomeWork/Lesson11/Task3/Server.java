package HomeWork.Lesson11.Task3;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server extends Thread {
    private int port;
    private int requestCounter;
    private ServerSocket serverSocket;

    Server(int port) throws Exception {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.requestCounter = 0;
    }

    public void run() {
        try {
            Socket socket;
            String message = "hello!";
            while (!isInterrupted()) {
                socket = serverSocket.accept();
                System.out.println("Connected: " + socket.getRemoteSocketAddress());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(message + " Your request number is " + requestCounter++);
                out.writeUTF(System.getenv("PROCESSOR_IDENTIFIER"));
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
