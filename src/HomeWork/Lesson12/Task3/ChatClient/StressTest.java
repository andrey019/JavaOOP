package HomeWork.Lesson12.Task3.ChatClient;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

class StressTest extends Thread {
    private Socket socket;
    private OutputStream os;
    private String login;
    private String hostName;
    private int port;

    StressTest(String hostName, int port, String login) throws Exception {
        this.hostName = hostName;
        this.port = port;
        this.login = login;
    }

    public void run() {
        try {
            socket = new Socket(hostName, port);
            os = socket.getOutputStream();
            //while (true) {
                String text = "stress test";
                Message m = new Message();
                m.text = text;
                m.from = login;
                m.to = "";
                m.writeToStream(os);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int loadFactor = 10;
        String hostName = "127.0.0.1";
        int port = 5000;
        ArrayList<StressTest> threads = new ArrayList<>();
        for (int i = 0; i < loadFactor; i++) {
            try {
                threads.add(new StressTest(hostName, port, "client " + i));
                threads.get(i).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
