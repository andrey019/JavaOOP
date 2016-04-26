package HomeWork.Lesson7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Calc extends Thread {
    int[] arr;
    int start, end;
    long sum;

    public Calc(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i < end; i++)
            sum += arr[i];
    }

    public long get() {
        return sum;
    }
}

public class ThreadArrSum {
    public static void main(String[] args) throws Exception {
	    int[] arr = new int[100000000];
        int procCount = Runtime.getRuntime().availableProcessors();
        List<Calc> list = new ArrayList<Calc>();
        Calc calc;
        int p = 0, partSize = arr.length / procCount;

        // #1
        Arrays.fill(arr, 1);
        long tm1 = System.currentTimeMillis();

        for (int i = 0; i < procCount; i++) {
            calc = new Calc(arr, p, p += partSize);
            list.add(calc);
            calc.start();
        }

        long sum = 0;
        for (Calc c : list) {
            c.join();
            sum += c.get();
        }

        System.out.println("Sum1 = " + sum);
        System.out.println("Time1 = " + (System.currentTimeMillis() - tm1));

        // #2
        tm1 = System.currentTimeMillis();
        sum = 0;

        for (int i = 0; i < arr.length; i++)
            sum += arr[i];

        System.out.println("Sum2 = " + sum);
        System.out.println("Time2 = " + (System.currentTimeMillis() - tm1));
    }
}
