package HomeWork.Lesson31.Task4;

import java.util.Scanner;

/**
 * Created by andrey on 10.04.2016.
 */
class ConsoleDraw {
    static void draw(int x, int y){
        StringBuilder[] str = new StringBuilder[y];
        for (int i = 0; i < y; i++){
            str[i] = new StringBuilder();
        }

        if (y == 2){
            for (int i = 0; i < y; i++){
                for (int j = 0; j < x; j++){
                    str[i].append("*");
                }
            }
        } else {
            for (int i = 0; i < y; i++){
                for (int j = 0; j < x; j++){
                    if ((i == 0) || (i == y - 1)){
                        str[i].append("*");
                    } else if ((j == 0) || (j == x - 1)){
                        str[i].append("*");
                    } else {
                        str[i].append(" ");
                    }
                }
            }
        }

        for (StringBuilder s:str){
            System.out.println(s.toString());
        }

    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int height = 0;
        int width = 0;

        while (height < 2){
            System.out.print("Enter rectangle height (at least 2): ");
            height = scan.nextInt();
        }
        while (width < 2){
            System.out.print("Enter rectangle width (at least 2): ");
            width = scan.nextInt();
        }

        draw(height, width);
    }
}
