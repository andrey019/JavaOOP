package HomeWork.Lesson7.Task0;

import java.util.concurrent.CountDownLatch;

class ArraySum extends Thread {
    private int[] intArray;
    private int first;
    private int last;
    private CountDownLatch countDownLatch;
    private long sum = 0;

    public ArraySum(int[] intArray, int first, int last, CountDownLatch countDownLatch) {
        this.intArray = intArray;
        this.first = first;
        this.last = last;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = first; i < last; i++) {
            sum += intArray[i];
        }
        countDownLatch.countDown();
    }

    long getSum() {
        return sum;
    }
}
