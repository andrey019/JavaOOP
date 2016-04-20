package HomeWork.Lesson6.Task4;


class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread("Main Thread", 50);
        myThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.interrupt();
    }
}
