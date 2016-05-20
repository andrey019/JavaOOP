package HomeWork.Lesson12.Task4.ChatServer;

class Main {
    public static void main(String[] args) throws Exception {
        Server s = new Server(5000);
        s.start();
    }
}
