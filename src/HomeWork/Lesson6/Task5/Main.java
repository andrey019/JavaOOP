package HomeWork.Lesson6.Task5;

import java.io.RandomAccessFile;
import java.util.ArrayList;

class Main {
    static void copyFile(String src, String to, int threadsAmount) {
        RandomAccessFile in;
        RandomAccessFile out;
        ArrayList<Thread> threads;
        try {
            in = new RandomAccessFile(src, "r");
            long fileLength = in.length();
            if (fileLength < threadsAmount) {
                System.out.println("Error! To many threads");
                System.exit(-1);
            }
            out = new RandomAccessFile(to, "rw");
            out.setLength(fileLength);
            threads = createThreads(src, to, fileLength, threadsAmount);
            startThreads(threads);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Thread> createThreads(String src, String to, long fileLength, int threadsAmount) {
        ArrayList<Thread> threads = new ArrayList<>();
        CopyProgress copyProgress = new CopyProgress(fileLength);
        new Thread(copyProgress).start();
        long position = 0;
        int blockSize = (int) (fileLength / threadsAmount);
        int lastBlockSize = (int) ( blockSize + (fileLength - (blockSize * threadsAmount)) );
        for (int i = 0; i < threadsAmount - 1; i++) {
            threads.add(new Thread(new MultiCopy(src, to, position, blockSize, copyProgress)));
            position += blockSize;
        }
        threads.add(new Thread(new MultiCopy(src, to, position, lastBlockSize, copyProgress)));
        return threads;
    }

    private static void startThreads(ArrayList<Thread> threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public static void main(String[] args) {
        copyFile("c:\\razved.mp4", "c:\\razved_copy.mp4", 11);
    }
}
