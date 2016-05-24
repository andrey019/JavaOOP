package HomeWork.Lesson12.Task3.ChatClient;

import HomeWork.Lesson12.Task3.ChatServer.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static Message createMessage(String s, String login) {
        int del = s.indexOf(':');
        String to = "";
        String text = s;

        if (del >= 0) {
            to = s.substring(0, del);
            text = s.substring(del + 1);
        }

        Message m = new Message();
        m.text = text;
        m.from = login;
        m.to = to;
        return m;
    }

    private static Message createFileMessage(String login, Scanner scanner) {
        System.out.print("Type in full file path: ");
		String filePath = scanner.nextLine();
        Message m = new Message();
        if (m.attachFile(filePath)) {
			int position = filePath.lastIndexOf("\\");
			filePath = filePath.substring(position + 1);
			m.fileTypeAndName = filePath;
            System.out.print("Type in to whom you want to send file: ");
            m.to = scanner.nextLine();
            m.from = login;
            m.isFile = true;
            return m;
        } else {
            System.out.println("No such file!");
            return null;
        }
    }

	private static void fileMessageReceive(Message msg, Scanner scanner) {
		System.out.println(msg + msg.fileTypeAndName);
		System.out.print("Type in location for saving file: ");
		Scanner scanner1 = new Scanner(System.in);
		String path = scanner1.nextLine();
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(path + "\\" + msg.fileTypeAndName);
			file.createNewFile();

			RandomAccessFile randomAccessFile = new RandomAccessFile(path + "\\" + msg.fileTypeAndName, "rw");
			randomAccessFile.write(msg.fileBytes);
			randomAccessFile.close();
			System.out.println("File saved!");
            scanner1.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("File save error!");
		}
	}

	public static void main(String[] args) {
		try {
			final Scanner scanner = new Scanner(System.in);
			final Socket socket = new Socket("127.0.0.1", 5000);
			final InputStream is = socket.getInputStream();
			final OutputStream os = socket.getOutputStream();

			System.out.println("Enter login: ");
			final String login = scanner.nextLine();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
            objectOutputStream.writeUTF(login);
            objectOutputStream.flush();
			System.out.println("To send files type in '!sendfile' command");

            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        while ( ! isInterrupted()) {
                            Message msg = Message.readFromStream(is);
                            if (msg == null)
                                Thread.yield();
                            else if (!msg.isFile) {
								System.out.println(msg.toString());
							} else {
								fileMessageReceive(msg, scanner);
							}


                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                        return;
                    }
                }
            };
            th.setDaemon(true);
            th.start();
			
			try {
				while (true) {
					String s = scanner.nextLine();
					if (s.isEmpty())
						break;
                    Message m = null;
                    if (s.equalsIgnoreCase("!sendfile")) {
                        m = createFileMessage(login, scanner);
                    } else {
                        m = createMessage(s, login);
                    }
                    if (m != null) {
                        m.writeToStream(os);
                    }
				}
			} finally {
				th.interrupt();
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
