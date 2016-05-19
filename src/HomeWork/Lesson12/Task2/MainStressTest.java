package HomeWork.Lesson12.Task2;

class MainStressTest {
    public static void main(String[] args) {
        WebStressTestHandler handler = new WebStressTestHandler("ukr.net", 80, 200);
        handler.start();
    }
}
