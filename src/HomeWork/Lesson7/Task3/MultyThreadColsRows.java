package HomeWork.Lesson7.Task3;

import java.util.concurrent.CountDownLatch;

class MultyThreadColsRows extends Thread {
    private int[] quad1;
    private int[] quad2;
    private int[] quad3;
    private int[] quad4;
    private int[] result;
    private boolean row;
    private CountDownLatch countDownLatch;

    public MultyThreadColsRows(int[] quad1, int[] quad2, int[] quad3, int[] quad4, boolean row,
                               CountDownLatch countDownLatch) {

        this.quad1 = quad1;
        this.quad2 = quad2;
        this.quad3 = quad3;
        this.quad4 = quad4;
        this.result = new int[quad1.length * 2];
        this.row = row;
        this.countDownLatch = countDownLatch;
    }

    private void calculateFull() {
        int length = quad1.length;
        if (row) {
            for (int i = 0; i < length; i++) {
                result[i] = quad1[i] * quad2[i];
            }
            for (int i = 0; i < length; i++) {
                result[i + length] = quad3[i] * quad4[i];
            }
        } else {
            for (int i = 0; i < length; i++) {
                result[i] = quad1[i] * quad3[i];
            }
            for (int i = 0; i < length; i++) {
                result[i + length] = quad2[i] * quad4[i];
            }
        }
    }

    @Override
    public void run() {
        calculateFull();
        countDownLatch.countDown();
    }

    public int[] getResult() {
        return result;
    }
}
