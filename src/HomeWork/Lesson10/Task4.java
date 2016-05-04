package HomeWork.Lesson10;

import java.util.HashMap;
import java.util.Scanner;

class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter numbers: ");
        String inString = scanner.nextLine();
        String[] inStrings = inString.split(",");
        HashMap<String,String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < inStrings.length; i++) {
            hashMap.put(inStrings[i], inStrings[i]);
        }
        System.out.println(hashMap.values());
        System.out.println("There is " + (inStrings.length - hashMap.size()) + " repeated elements");
    }
}
