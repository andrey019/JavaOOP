package HomeWork.Lesson9.human;

import java.util.ArrayList;

class SerialTest {
    public static void main(String[] args) {
        ArrayList<String> humans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            humans.add("name" + i);
        }


        if (humans.contains("name6")) {
            System.out.println(true);
        }
    }
}
