package HomeWork.Lesson5.Task4;

import java.io.File;
import java.io.RandomAccessFile;


class FilesCopy {
    static void copyFiles (String src, String to) {
        new File(to).mkdir();
        File catalogue = new File(src);
        File[] files = catalogue.listFiles();
        for (File f : files) {
            try {
                if (f.isFile()) {
                    copy(f.getCanonicalPath(), to + "\\" + f.getName());
                } else {
                    copyFiles(f.getCanonicalPath(), to + "\\" + f.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void copy(String src, String to) {
        try {
            RandomAccessFile in = new RandomAccessFile(src, "r");
            try {
                RandomAccessFile out = new RandomAccessFile(to, "rw");
                try {
                    byte[] buf = new byte[1024];
                    int count;
                    do {
                        count = in.read(buf, 0, buf.length);
                        if (count > 0) {
                            out.write(buf, 0, buf.length);
                        }
                    } while (count > 0);
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        copyFiles("c:\\test", "c:\\testo");
    }
}
