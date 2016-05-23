package HomeWork.Lesson12.Task3.ChatServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientThread extends Thread {

	private Socket socket;
	private List<Message> msg;
    private List<String> clients;
	private int pos = 0;
    private String clientName;
	
	public ClientThread(Socket socket, List<Message> msg, String clientName, List<String> clients) {
        this.clientName = clientName;
        this.clients = clients;
		this.socket = socket;
		this.msg = msg;
	}

    private void listToBytes(OutputStream os) throws IOException {
        final int sz = msg.size();
        for (int i = pos; i < sz; i++) {
            if (msg.get(i).to.equalsIgnoreCase(clientName) || msg.get(i).to.equalsIgnoreCase("") ||
                msg.get(i).from.equalsIgnoreCase(clientName)) {

                msg.get(i).writeToStream(os);
            }
        }
		pos = msg.size();
	}
	
	public void run() {
		try {
			final InputStream is = socket.getInputStream();
			final OutputStream os = socket.getOutputStream();
			
			while ( ! isInterrupted()) {
				if (pos < msg.size())
					listToBytes(os);
				
				Message m = Message.readFromStream(is);
				if (m != null) {
                    if (clients.contains(m.to) || m.to.equalsIgnoreCase("")) {
                        msg.add(m);
                    } else {
                        Message message = new Message();
                        message.date = new Date(System.currentTimeMillis());
                        message.from = "SERVER";
                        message.to = clientName;
                        message.text = "NO SUCH USER!";
                        message.writeToStream(os);
                    }
                }

                Thread.sleep(100);
			}
			
			socket.close();
			
		} catch (Exception e) {
            e.printStackTrace();
			return;
		}
	}
}
