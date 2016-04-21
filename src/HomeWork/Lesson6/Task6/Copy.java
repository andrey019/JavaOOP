package HomeWork.Lesson6.Task6;

import java.io.RandomAccessFile;
import java.util.Date;


class Copy implements Runnable {
    private CopyProgress copyProgress;
    private String src;
    private String to;

    public Copy(String src, String to) {
        copyProgress = new CopyProgress();
        this.src = src;
        this.to = to;
    }

    @Override
    public void run() {
        Thread progress = new Thread(copyProgress);
        progress.start();
        copyFile();
        copyProgress.setStop(true);
    }

    private void copyFile() {
        try {
            RandomAccessFile in = new RandomAccessFile(src, "r");
            try {
                RandomAccessFile out = new RandomAccessFile(to, "rw");
                long start = new Date().getTime();
                try {
                    long onePercent = in.length() / 100;
                    long progressRaw = 0;
                    byte[] buf = new byte[1024];
                    int count;
                    do {
                        count = in.read(buf, 0, buf.length);
                        if (count > 0) {
                            out.write(buf, 0, buf.length);
                            progressRaw += buf.length;
                            copyProgress.setCurrentProgress((int) (progressRaw / onePercent));
                        }
                    } while (count > 0);
                } finally {
                    out.close();
                    long end = new Date().getTime();
                    copyProgress.setTime( ((double) (end - start)) / 1000 );
                }
            } finally {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
