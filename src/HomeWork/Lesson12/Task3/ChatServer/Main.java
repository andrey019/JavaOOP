package HomeWork.Lesson12.Task3.ChatServer;

public class Main {
    public static void main(String[] args) throws Exception {
        Server s = new Server(5000);
        s.start();
    }
}
