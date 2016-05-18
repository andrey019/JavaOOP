package HomeWork.Lesson12.Task2;

import java.io.IOException;
import java.net.Socket;

class WebStressTest extends Thread {
    private String hostName;
    private int port;
    private int threadNumber;
    private WebStressTestHandler webStressTestHandler;

    WebStressTest(String hostName, int port, int threadNumber, WebStressTestHandler webStressTestHandler) {
        this.hostName = hostName;
        this.port = port;
        this.threadNumber = threadNumber;
        this.webStressTestHandler = webStressTestHandler;
    }

    public void run() {
        try {
            Socket socket = new Socket(hostName, 80);
            socket.close();
        } catch (IOException e) {
            System.out.println("Host not found!");
            this.interrupt();
        }
        Socket socket;
        while (!isInterrupted()) {
            try {
                socket = new Socket(hostName, 80);
                socket.close();
                webStressTestHandler.setMadeConnections(threadNumber);
            } catch (IOException e) {
                webStressTestHandler.setFailedConnections(threadNumber);
            }
        }
    }
}
