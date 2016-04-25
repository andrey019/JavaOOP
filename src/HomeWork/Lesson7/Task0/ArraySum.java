package HomeWork.Lesson7.Task0;

import java.util.concurrent.CountDownLatch;

class ArraySum extends Thread {
    private int[] intArray;
    private CountDownLatch countDownLatch;
    private long sum = 0;

    public ArraySum(int[] intArray, CountDownLatch countDownLatch) {
        this.intArray = intArray;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int number : intArray) {
            sum += number;
        }
        countDownLatch.countDown();
    }

    long getSum() {
        return sum;
    }
}
