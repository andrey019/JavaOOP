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

    private void listToBytes(OutputStream os, InputStream is) throws IOException {
        final int sz = msg.size();
        for (int i = pos; i < sz; i++) {
            if ( (msg.get(i).to.equalsIgnoreCase(clientName) || msg.get(i).to.equalsIgnoreCase("") ||
                msg.get(i).from.equalsIgnoreCase(clientName)) && !msg.get(i).isFile ) {

                msg.get(i).writeToStream(os);
            } else if ( (msg.get(i).to.equalsIgnoreCase(clientName) || msg.get(i).to.equalsIgnoreCase("")) &&
                        msg.get(i).isFile ) {
                if (fileReceiveRequest(msg.get(i).from, msg.get(i).fileTypeAndName, os, is)) {
                    msg.get(i).writeToStream(os);
                }
            }
        }
		pos = msg.size();
	}

    private boolean fileReceiveRequest(String from, String fileTypeAndName, OutputStream os, InputStream is) {
        try {
            Message request = new Message();
            request.date = new Date(System.currentTimeMillis());
            request.from = "SERVER";
            request.to = clientName;
            request.text = "User '" + from + "' sent you file '" + fileTypeAndName + "'. To accept it reply 'y', " +
                    "to ignore reply anything else";
            request.writeToStream(os);

            while (is.available() < 1) {
                Thread.sleep(100);
            }
            Message reply = Message.readFromStream(is);
            if (reply.text.equalsIgnoreCase("y")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
	
	public void run() {
		try {
			final InputStream is = socket.getInputStream();
			final OutputStream os = socket.getOutputStream();
			
			while ( ! isInterrupted()) {
				if (pos < msg.size())
					listToBytes(os, is);
				
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
