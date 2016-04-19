package HomeWork.Lesson5.Task5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;


class LogFile {
    private static void logCatalogue(String logFile, String srcCatalogue) {
        ArrayList<String> arrayList = new ArrayList<String>();
        findFiles(arrayList, srcCatalogue);
        writeLogFile(arrayList, logFile);
    }

    private static void findFiles(ArrayList filePaths, String srcCatalogue) {
        File catalogue = new File(srcCatalogue);
        File[] files = catalogue.listFiles();
        for (File f : files) {
            try {
                if (f.isFile()) {
                    filePaths.add("[" + new Date(f.lastModified()).toString() + "] " + f.getCanonicalPath());
                } else {
                    findFiles(filePaths , f.getCanonicalPath());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeLogFile(ArrayList<String> filePaths , String logFile) {
        BufferedWriter bufferedWriter;
        String newLine = System.getProperty("line.separator");
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(logFile));
            try {
                for (String s : filePaths) {
                    bufferedWriter.write(s);
                    bufferedWriter.write(newLine);
                }
            } finally {
                bufferedWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        logCatalogue("c:\\log.txt", "c:\\test");
    }
}
