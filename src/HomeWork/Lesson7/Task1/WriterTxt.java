package HomeWork.Lesson7.Task1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.locks.ReentrantLock;

class WriterTxt {
    private ReentrantLock lock = new ReentrantLock();
    private PrintWriter printWriter;

    public WriterTxt(String filePath) throws FileNotFoundException {
        printWriter = new PrintWriter(filePath);
    }

    void writeLn(String string) {
        printWriter.println(string);
    }

    void writerClose() {
        printWriter.close();
    }

    ReentrantLock getLock() {
        return lock;
    }
}
