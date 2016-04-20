package HomeWork.Lesson5.Task6;


import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.Vector;

class ManyToOne {
    private static void copyFiles(String to, String...src) {
        try {
            RandomAccessFile out = new RandomAccessFile(to, "rw");
            try {
                for (String s : src) {
                    try {
                        RandomAccessFile in = new RandomAccessFile(s, "r");
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
                            in.close();
                        }
                    } finally {}
                }
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyFilesSequence(String to, String...src) {
        try {
            RandomAccessFile out = new RandomAccessFile(to, "rw");
            Vector<InputStream> inputStreams = new Vector<>();
            for (String s : src) {
                inputStreams.add(new FileInputStream(s));
            }
            SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStreams.elements());
            try {
                byte[] buf = new byte[1024];
                int count;
                do {
                    count = sequenceInputStream.read(buf, 0, buf.length);
                    if (count > 0) {
                        out.write(buf, 0, buf.length);
                    }
                } while (count > 0);
            } finally {
                sequenceInputStream.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        copyFiles("c:\\test\\all.txt", "c:\\test\\1.txt", "c:\\test\\2.txt", "c:\\test\\hello2.txt");
        copyFilesSequence("c:\\test\\allSequence.txt", "c:\\test\\1.txt", "c:\\test\\2.txt", "c:\\test\\hello2.txt");
    }
}
