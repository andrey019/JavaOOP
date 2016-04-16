package ClassWork.Lesson6.Counter;

/**
 * Created by Лена on 16.04.2016.
 */
class Counter extends Thread {
    private int start;
    private int end;

    public Counter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        int x = start;
        while (!isInterrupted())
            if (x <= end) {
                System.out.println(x++);
            } else {
                x = start;
            }
        System.out.println("Thread " + getId() + " interrupted");
    }

    public static void main(String[] args) {
        Counter c = new Counter(115, 666);
        c.start(); // запускаем поток
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            ;
        }
        c.interrupt();
        System.out.println("Main thread finished");
    }
}
