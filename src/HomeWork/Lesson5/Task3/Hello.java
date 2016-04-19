package HomeWork.Lesson5.Task3;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by andrey on 19.04.16.
 */
class Hello {
    public static void main(String[] args) {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        ArrayList<String> arrayList = new ArrayList<String>();
        String line;
        String newLine = System.getProperty("line.separator");

        try {
            bufferedReader = new BufferedReader(new FileReader("c:\\test\\hello.txt"));
            line = bufferedReader.readLine();
            while (line != null) {
                line = line.replaceAll("hello", "1234");
                arrayList.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            bufferedWriter = new BufferedWriter(new FileWriter("c:\\test\\hello.txt"));
            for (String s : arrayList) {
                bufferedWriter.write(s);
                bufferedWriter.write(newLine);
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
