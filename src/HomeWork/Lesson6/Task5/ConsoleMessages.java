package HomeWork.Lesson6.Task5;

import java.util.Date;

class ConsoleMessages implements Runnable {
    private int threadsAmount;
    private int doneThreads = 0;

    public ConsoleMessages(int threadsAmount) {
        this.threadsAmount = threadsAmount;
    }

    @Override
    public void run() {
        long startTime = new Date().getTime();
        System.out.println("Copying...");
        while (doneThreads != threadsAmount) {}
        double spentTime = ((double)(new Date().getTime() - startTime)) / 1000;
        System.out.println("Done in " + spentTime + " seconds");
    }

    void threadDone() {
        doneThreads++;
    }
}
