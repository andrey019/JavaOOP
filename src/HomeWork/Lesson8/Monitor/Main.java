package HomeWork.Lesson8.Monitor;

class Main {
    public static void main(String[] args) {
        MonitorHandler monitorHandler = new MonitorHandler("c:\\test", "c:\\test1", "g:\\test");
        monitorHandler.start();
    }
}
