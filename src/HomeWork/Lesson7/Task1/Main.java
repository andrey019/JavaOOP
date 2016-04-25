package HomeWork.Lesson7.Task1;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

class Main {
    static void threadWrite(String filePath, int threadsAmount) {
        WriterTxt writerTxt;
        CountDownLatch countDownLatch = new CountDownLatch(threadsAmount);
        try {
            writerTxt = new WriterTxt(filePath);
            ArrayList<Thread> threads = createThreads(writerTxt, threadsAmount, countDownLatch);
            threadsStart(threads);
            countDownLatch.await();
            writerTxt.writerClose();
            System.out.println("Written successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Thread> createThreads(WriterTxt writerTxt, int threadsAmount, CountDownLatch countDownLatch) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsAmount; i++) {
            threads.add(new Thread(new WriteThread(writerTxt, "Thread " + i, countDownLatch)));
        }
        return threads;
    }

    private static void threadsStart(ArrayList<Thread> threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public static void main(String[] args) {
        threadWrite("c:\\test\\writeThreads.txt", 30);
    }
}
