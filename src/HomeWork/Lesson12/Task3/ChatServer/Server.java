package HomeWork.Lesson12.Task3.ChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
	private int port;
	private Thread thread;
    private int connected = 0;
	private List<Message> msg = Collections.synchronizedList(new ArrayList<Message>());

	public Server(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		thread = new Thread() {
			@Override
			public void run() {
				try {
					ServerSocket s = new ServerSocket(port);

					while ( ! isInterrupted()) {
						Socket c = s.accept();
						
						ClientThread ct = new ClientThread(c, msg);
						ct.start();
                        connected++;
					}

					s.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
        while (true) {
            System.out.println("Received messages: " + msg.size() + ". Connected: " + connected);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}

	public void stop() {
		thread.interrupt();
	}
}
