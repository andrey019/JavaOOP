package HomeWork.Lesson3.Task2;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by andrey on 11.04.16.
 */
class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        try {
            int i = scan.nextInt();
        } catch (InputMismatchException e){
            e.printStackTrace();
        }

        try {
            int[] arr1 = new int[3];
            arr1[3] = 5;
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        try {
            int i2 = Integer.parseInt("dfg234dgs");
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        try {
            File file1 = new File("E:\\file.txt");
            FileReader fr = new FileReader(file1);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        try {
            StringBuilder[] sb = new StringBuilder[3];
            sb[0].append("dfdf");
        } catch (NullPointerException e){
            e.printStackTrace();
        }

    }
}
