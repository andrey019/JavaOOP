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
        int threadArrayCapacity = intArray.length / threadsAmount;
        int lastThreadArrayCapacity = threadArrayCapacity + (intArray.length - (threadArrayCapacity * threadsAmount));
        if (threadsAmount > 1) {
            for (int i = 0; i < threadsAmount - 1; i++) {
                int[] threadArray = new int[threadArrayCapacity];
                System.arraycopy(intArray, threadArrayCapacity * i, threadArray, 0, threadArrayCapacity);
                threads.add(new ArraySum(threadArray, countDownLatch));
            }
            int[] threadArray = new int[lastThreadArrayCapacity];
            System.arraycopy(intArray, threadArrayCapacity * (threadsAmount - 1), threadArray, 0, lastThreadArrayCapacity);
            threads.add(new ArraySum(threadArray, countDownLatch));
        } else {
            threads.add(new ArraySum(intArray, countDownLatch));
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
        long startTime = new Date().getTime();
        long arraySum = arraySum(25000000, 1);
        double spentTime = (double)(new Date().getTime() - startTime) / 1000;
        System.out.println("Result = " + arraySum + "; time spent = " + spentTime + " seconds");
    }
}
