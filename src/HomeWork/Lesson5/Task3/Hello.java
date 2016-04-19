package HomeWork.Lesson5.Task3;

import java.io.*;
import java.util.ArrayList;

class Hello {
    private static ArrayList<String> readTextFile(String srcFile) {
        BufferedReader bufferedReader;
        String line;
        ArrayList<String> arrayText = new ArrayList<String>();
        try {
            bufferedReader = new BufferedReader(new FileReader(srcFile));
            try {
                line = bufferedReader.readLine();
                while (line != null) {
                    arrayText.add(line);
                    line = bufferedReader.readLine();
                }
            } finally {
                bufferedReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayText;
    }

    private static void writeTextFile(ArrayList<String> arrayText, String toFile) {
        BufferedWriter bufferedWriter;
        String newLine = System.getProperty("line.separator");
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFile));
            try {
                for (String s : arrayText) {
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

    private static void replaceText(ArrayList<String> arrayText, String regex, String replacement) {
        String temp = "";
        for (int i = 0; i < arrayText.size(); i++) {
            temp = arrayText.get(i).replaceAll(regex, replacement);
            arrayText.set(i, temp);
        }
    }

    public static void main(String[] args) {
        String textFile = "c:\\test\\hello.txt";
        ArrayList<String> arrayText = readTextFile(textFile);
        replaceText(arrayText, "hello", "1234");
        writeTextFile(arrayText, textFile);
    }
}
