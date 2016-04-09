package ClassWork.Lesson3;

import java.io.File;
import java.io.IOException;
class Main {
    private static void test() {
        try {
            File f = File.createTempFile("1B", "DEF");
            System.out.println(f.getCanonicalPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        test();
    }
}