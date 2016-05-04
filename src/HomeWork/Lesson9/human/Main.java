package HomeWork.Lesson9.human;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    private static void humanTest() {
        Human human1 = new Human("Rita", 23);
        Human human2 = new Human("Rita", 23);
        Human human3 = new Human("Peter", 33);
        Human human4 = human1;
        Human human5;

        try {
            human5 = (Human) human3.clone();
            System.out.println(human5.equals(human3));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(human1.toString());
        System.out.println(human3.toString());

        System.out.println(human1.equals(human3));
        System.out.println(human1.equals(human2));
        System.out.println(human1.equals(human4));
    }

    private static void serializableTest(String path) {
        ArrayList<Human> humans = new ArrayList<>();
        humans.add(new Human("Rita", 23));

        serialWrite(humans, path);
        humans = serialRead(humans.size(), path);
        onDisplay(humans);
    }

    private static void consoleWriteRead(int entryAmount, String path) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Human> humans = new ArrayList<>();
        String name;
        int age;
        for (int i = 0; i < entryAmount; i++) {
            System.out.print("Enter the name for the entry number " + i + ": ");
            name = scanner.nextLine();
            age = -1;
            while (!(age > -1 && age < 120)) {
                try {
                    System.out.print("Enter the age for the entry number " + i + ": ");
                    age = scanner.nextInt();
                    scanner.nextLine();
                } catch (Exception e) {
                    age = -1;
                    scanner.nextLine();
                    System.out.println("Wrong age!");
                }
            }
            humans.add(new Human(name, age));
        }
        deleteEqualEntries(humans);
        serialWrite(humans, path);
        humans = serialRead(humans.size(), path);
        onDisplay(humans);
    }

    private static void deleteEqualEntries(ArrayList<Human> humans) {
        for (int i = 0; i < humans.size(); i++) {
            int lastIndex = humans.lastIndexOf(humans.get(i));
            while (lastIndex != i) {
                humans.remove(lastIndex);
                lastIndex = humans.lastIndexOf(humans.get(i));
            }
        }
    }

    static boolean serialWrite(ArrayList<Human> humans, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (int i = 0; i < humans.size(); i++) {
                objectOutputStream.writeObject(humans.get(i));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static ArrayList<Human> serialRead(int entryAmount, String path) {
        ArrayList arrayList = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            for (int i = 0; i < entryAmount; i++) {
                arrayList.add((Human) objectInputStream.readObject());
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static void onDisplay(ArrayList<Human> humans) {
        for (Human human : humans) {
            System.out.println(human.toString());
        }
    }

    public static void main(String[] args) {
        humanTest();    // task 1
        serializableTest("c:\\test\\human.tmp");     // task 2
        consoleWriteRead(10, "c:\\test\\humans.tmp");    // task 3
    }
}
