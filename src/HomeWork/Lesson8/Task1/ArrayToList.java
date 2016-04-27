package HomeWork.Lesson8.Task1;

import java.util.ArrayList;
import java.util.Random;

class ArrayToList {
    private static ArrayList intArrayToList(int[] array) {          // task 1
        ArrayList intArray = new ArrayList();
        for (int i = 0, length = array.length; i < length; i++) {
            intArray.add(array[i]);
        }
        return intArray;
    }

    private static ArrayList stringArrayToList(String[] array) {    // task 1
        ArrayList intArray = new ArrayList();
        for (int i = 0, length = array.length; i < length; i++) {
            intArray.add(array[i]);
        }
        return intArray;
    }

    private static ArrayList makeListDeleteElements() {             // task 2
        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.subList(0, 2).clear();
        list.remove(list.size() - 1);
        list.trimToSize();
        return list;
    }

    public static void main(String[] args) {
        int length = 10;
        int[] intArray = new int[length];
        String[] strArray = new String[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            intArray[i] = random.nextInt(100);
            strArray[i] = String.valueOf(random.nextInt(100));
            System.out.print(intArray[i] + " ");
        }
        System.out.println();
        ArrayList list = intArrayToList(intArray);
        ArrayList list1 = stringArrayToList(strArray);
        System.out.println(list.toString());

        ArrayList list2 = makeListDeleteElements();
        System.out.println(list2.toString());
    }
}
