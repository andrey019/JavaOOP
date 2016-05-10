package HomeWork.Lesson10;

import java.util.HashSet;
import java.util.Scanner;

class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter numbers: ");
        String inString = scanner.nextLine();
        String[] inStrings = inString.split(",");
        HashSet<String> hashSet = new HashSet<String>();
        for (int i = 0; i < inStrings.length; i++) {
            hashSet.add(inStrings[i]);
        }
        System.out.println(hashSet.toString());       // task 4
        System.out.println("There is " + (inStrings.length - hashSet.size()) + " repeated elements");   //task 3
    }
}
