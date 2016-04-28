package HomeWork.Lesson8.Task3;

import java.util.Arrays;

class ArrayContainer {
    static class ArrContainer <A> {
        private A[] array;

        ArrContainer(A[] array) {
            this.array = array;
        }

        public A[] getArray() {
            return array;
        }

        public A getCell(int index) {
            return array[index];
        }
    }

    public static void main(String[] args) {
        int length = 10;
        Integer[] intArr = new Integer[length];
        Arrays.fill(intArr, 33);
        ArrContainer intCont = new ArrContainer(intArr);
        System.out.println(intCont.getCell(5));

        String[] strArr = new String[length];
        Arrays.fill(strArr, "sdf");
        ArrContainer strCont = new ArrContainer(strArr);
        System.out.println(strCont.getCell(5));
    }
}
