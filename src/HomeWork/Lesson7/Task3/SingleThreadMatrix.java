package HomeWork.Lesson7.Task3;

import java.util.concurrent.CountDownLatch;

class SingleThreadMatrix extends Thread {
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] matrixResult;
    private CountDownLatch countDownLatch;

    public SingleThreadMatrix(int[][] matrixA, int[][] matrixB, CountDownLatch countDownLatch) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrixResult = new int[matrixA.length][matrixA.length];
        this.countDownLatch = countDownLatch;
    }

    void multiply() {
        int length = matrixA.length;
        int rowTemp = 1;
        int colTemp = 1;
        int[] rowsTemp = new int[length];
        int[] colsTemp = new int[length];
        for (int i = 0; i < length; i++) {
            for (int m = 0; m < length; m++) {
                rowTemp *= matrixA[m][i];
                colTemp *= matrixB[i][m];
            }
            rowsTemp[i] = rowTemp;
            colsTemp[i] = colTemp;
            rowTemp = 1;
            colTemp = 1;
        }
        for (int i = 0; i < length; i++) {
            for (int m = 0; m < length; m++) {
                matrixResult[m][i] = rowsTemp[i] + colsTemp[m];
            }
        }
    }

    @Override
    public void run() {
        multiply();
        countDownLatch.countDown();
    }

    public int[][] getMatrixResult() {
        return matrixResult;
    }
}
