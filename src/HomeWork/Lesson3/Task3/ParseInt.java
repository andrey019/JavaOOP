package HomeWork.Lesson3.Task3;

import java.util.Scanner;

/**
 * Created by andrey on 11.04.16.
 */
class ParseInt {
    static int parseInt(String string) throws ParsIntException{
        long result = 0;
        char tempChar;
        if (string.length() > 10) throw new ParsIntException("Number is too big!");
        for (int i = 0; i < string.length(); i++){
            tempChar = string.charAt(i);
            if ((tempChar > 47) && (tempChar < 58)){
                result += (tempChar - 48) * (long) ( Math.pow(10, (string.length() - i - 1)) );
            } else throw new ParsIntException("Forbidden characters found!");
        }
        if (result <= Integer.MAX_VALUE){
            return (int) result;
        } else throw new ParsIntException("Number is too big!");
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter string to parse: ");
        String input = scan.nextLine();
        int result = 0;

        try {
            result = parseInt(input);
        } catch (ParsIntException e){
            System.out.print(e.getMessage());
        }
    }
}
