package HomeWork.Lesson12.Task2;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

class WebStressTestHandler extends Thread {
    private String hostName;
    private int port;
    private int loadAmount;
    private int[] establishedConnections;
    private int[] failedConnections;

    WebStressTestHandler(String hostName, int port, int loadAmount) {
        this.hostName = hostName;
        this.port = port;
        this.loadAmount = loadAmount;
        this.establishedConnections = new int[loadAmount];
        this.failedConnections = new int[loadAmount];
    }

    void setMadeConnections(int threadNumber) {
        establishedConnections[threadNumber]++;
    }

    void setFailedConnections(int threadNumber) {
        failedConnections[threadNumber]++;
    }

    private boolean testConnection() {
        try {
            Socket socket = new Socket(hostName, port);
            socket.close();
            return true;
        } catch (IOException e) {
            System.out.println("Host not found!");
            return false;
        }
    }

    private int sumConnections(int[] connections) {
        int sum = 0;
        for (int i = 0; i < connections.length; i++) {
            sum += connections[i];
        }
        return sum;
    }

    private int aliveThreads() {
        int result = 0;
        for (int i = 0; i < loadAmount; i++) {
            if ((establishedConnections[i] > 0) || (failedConnections[i] > 0)) {
                result++;
            }
        }
        return result;
    }

    private void progressOnScreen() {
        while (true) {
            System.out.println("Established connections: " + sumConnections(establishedConnections) +
                                    ". Failed connections: " + sumConnections(failedConnections) +
                                    ". Alive threads: " + aliveThreads());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        ArrayList<WebStressTest> threads = new ArrayList<>();
        if (testConnection()) {
            for (int i = 0; i < loadAmount; i++) {
                threads.add(new WebStressTest(hostName, port, i, this));
                threads.get(i).start();
            }
        }
        progressOnScreen();
    }
}
