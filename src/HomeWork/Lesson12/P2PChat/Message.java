package HomeWork.Lesson12.P2PChat;

import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;

class Message implements Serializable {
    private Date date = null;
    private String from = null;
    private String text = null;
    private boolean isTechInfoMessage = false;
    private ArrayList<InetAddress> clients = null;

    public void writeToStream(OutputStream out)
            throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        try {
            os.writeObject(this);
        } finally {
            os.flush();
            os.close();
        }

        byte[] packet = bs.toByteArray();

        DataOutputStream ds = new DataOutputStream(out);
        ds.writeInt(packet.length);
        ds.write(packet);
        ds.flush();
    }

    public static Message readFromStream(InputStream in)
            throws IOException, ClassNotFoundException {
        if (in.available() <= 0)
            return null;

        DataInputStream ds = new DataInputStream(in);
        int len = ds.readInt();
        byte[] packet = new byte[len];
        ds.read(packet);

        ByteArrayInputStream bs = new ByteArrayInputStream(packet);
        ObjectInputStream os = new ObjectInputStream(bs);
        try {
            Message msg = (Message) os.readObject();
            return msg;
        } finally {
            os.close();
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("[")
                .append(date.toString())
                .append(", From: ")
                .append(from)
                .append("] ")
                .append(text)
                .toString();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTechInfoMessage() {
        return isTechInfoMessage;
    }

    public void setTechInfoMessage(boolean techInfoMessage) {
        isTechInfoMessage = techInfoMessage;
    }

    public ArrayList<InetAddress> getClients() {
        return clients;
    }

    public void setClients(ArrayList<InetAddress> clients) {
        this.clients = clients;
    }
}
