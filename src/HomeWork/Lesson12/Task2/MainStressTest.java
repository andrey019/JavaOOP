package HomeWork.Lesson12.Task2;

class MainStressTest {
    public static void main(String[] args) {
        WebStressTestHandler handler = new WebStressTestHandler("di.fm", 80, 100);
        handler.start();
    }
}
