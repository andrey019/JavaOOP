package HomeWork.Lesson6.Task5;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

class MultiCopy implements Runnable {
    private long position;
    private int blockSize;
    private static final int BUFFER_SIZE = 1024;
    private RandomAccessFile in;
    private RandomAccessFile out;
    private CopyProgress copyProgress;

    public MultiCopy(String src, String to, long position, int blockSize, CopyProgress copyProgress) {
        this.position = position;
        this.blockSize = blockSize;
        this.copyProgress = copyProgress;
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
            int copyCount = blockSize / BUFFER_SIZE;
            int lastBufSize = (blockSize - (copyCount * BUFFER_SIZE));
            byte[] buf = new byte[blockSize];
            for (int i = 0; i < copyCount; i++) {
                in.read(buf, 0, BUFFER_SIZE);
                out.write(buf, 0, BUFFER_SIZE);
                copyProgress.addDoneBytes(BUFFER_SIZE);
            }
            in.read(buf, 0, lastBufSize);
            out.write(buf, 0, lastBufSize);
            copyProgress.addDoneBytes(lastBufSize);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
