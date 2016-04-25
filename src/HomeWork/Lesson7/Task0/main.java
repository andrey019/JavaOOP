package HomeWork.Lesson7.Task0;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

class main {
    static long arraySum(int arrayCapacity, int threadsAmount) {
        CountDownLatch countDownLatch = new CountDownLatch(threadsAmount);
        int[] intArray = createIntArray(arrayCapacity);
        ArrayList<ArraySum> threads = createThreads(intArray, threadsAmount, countDownLatch);
        long resultSum = calculateSum(threads, countDownLatch);
        return resultSum;
    }

    private static int[] createIntArray(int arrayCapacity) {
        int[] intArray = new int[arrayCapacity];
        Random random = new Random();
        Arrays.fill(intArray, random.nextInt());
        return intArray;
    }

    private static ArrayList<ArraySum> createThreads(int[] intArray, int threadsAmount, CountDownLatch countDownLatch) {
        ArrayList<ArraySum> threads = new ArrayList<>();
        int threadArraySize = intArray.length / threadsAmount;
        int lastThreadArraySize = threadArraySize + (intArray.length - (threadArraySize * threadsAmount));
        if (threadsAmount > 1) {
            for (int i = 0; i < threadsAmount - 1; i++) {
                threads.add(new ArraySum(intArray, threadArraySize * i, threadArraySize * (i + 1), countDownLatch));
            }
            threads.add(new ArraySum(intArray, threadArraySize * (threadsAmount - 1), intArray.length, countDownLatch));
        } else {
            threads.add(new ArraySum(intArray, 0, intArray.length, countDownLatch));
        }

        return threads;
    }

    private static long calculateSum(ArrayList<ArraySum> threads, CountDownLatch countDownLatch) {
        for (ArraySum thread : threads) {
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long resultSum = 0;
        for (ArraySum thread : threads) {
            resultSum += thread.getSum();
        }
        return resultSum;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long arraySum = arraySum(200000000, 8);
        double spentTime = (double)(System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Result = " + arraySum + "; time spent = " + spentTime + " seconds");
        startTime = System.currentTimeMillis();
        arraySum = arraySum(200000000, 1);
        spentTime = (double)(System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Result = " + arraySum + "; time spent = " + spentTime + " seconds");
    }
}
