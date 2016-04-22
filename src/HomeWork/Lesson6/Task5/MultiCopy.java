package HomeWork.Lesson6.Task5;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

class MultiCopy implements Runnable {
    private long position;
    private int blockSize;
    private RandomAccessFile in;
    private RandomAccessFile out;
    private ConsoleMessages consoleMessages;

    public MultiCopy(String src, String to, long position, int blockSize, ConsoleMessages consoleMessages) {
        this.position = position;
        this.blockSize = blockSize;
        this.consoleMessages = consoleMessages;
        try {
            this.in = new RandomAccessFile(src, "r");
            this.out = new RandomAccessFile(to, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        copyFile();
    }

    private void copyFile() {
        try {
            in.seek(position);
            out.seek(position);
            byte[] buf = new byte[blockSize];
            in.read(buf, 0, blockSize);
            out.write(buf, 0, blockSize);
            in.close();
            out.close();
            consoleMessages.threadDone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
