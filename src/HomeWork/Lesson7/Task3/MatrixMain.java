package HomeWork.Lesson7.Task3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

class MatrixMain {
    static int[][] matrixAA;
    static int[][] matrixBB;

    private static int[][] createMatrix(int size) {
        Random random = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(10) + 1;
            }
        }
        return matrix;
    }

    private static int[][] calculateSingle(int size) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        int[][] matrixA;
        int[][] matrixB;
        matrixA = matrixAA;
        matrixB = matrixBB;
        SingleThreadMatrix single = new SingleThreadMatrix(matrixA, matrixB, countDownLatch);
        Thread singleThread = new Thread(single);
        singleThread.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return single.getMatrixResult();
    }

    private static int[][] calculateMulty(int size) {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        int[][] matrixA;
        int[][] matrixB;
        matrixA = matrixAA;
        matrixB = matrixBB;
        ArrayList<MultyThreadMatrix> multy = new ArrayList<>();
        ArrayList<Thread> multyThreads = new ArrayList<>();
        try {
            for (int i = 0; i < 4; i++) {
                multy.add(new MultyThreadMatrix(matrixA, matrixB, i + 1, countDownLatch));
                multyThreads.add(new Thread(multy.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        for (Thread thread : multyThreads) {
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getMultyResults(multy, size);
    }

    private static int[][] getMultyResults(ArrayList<MultyThreadMatrix> multy, int size) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        int[] rowQuad1 = multy.get(0).getRowsResult();
        int[] rowQuad2 = multy.get(1).getRowsResult();
        int[] rowQuad3 = multy.get(2).getRowsResult();
        int[] rowQuad4 = multy.get(3).getRowsResult();
        int[] colQuad1 = multy.get(0).getColsResult();
        int[] colQuad2 = multy.get(1).getColsResult();
        int[] colQuad3 = multy.get(2).getColsResult();
        int[] colQuad4 = multy.get(3).getColsResult();
        MultyThreadColsRows multyRows = new MultyThreadColsRows(rowQuad1, rowQuad2, rowQuad3, rowQuad4,
                true, countDownLatch);
        MultyThreadColsRows multyCols = new MultyThreadColsRows(colQuad1, colQuad2, colQuad3, colQuad4,
                true, countDownLatch);
        Thread multyRowsThread = new Thread(multyRows);
        Thread multyColsThread = new Thread(multyCols);
        multyRowsThread.start();
        multyColsThread.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getMatrixResult(multyRows, multyCols, size);
    }

    private static int[][] getMatrixResult(MultyThreadColsRows multyRows, MultyThreadColsRows multyCols, int size) {
        int[][] matrixResult = new int[size][size];
        int[] rows = multyRows.getResult();
        int[] cols = multyCols.getResult();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrixResult[j][i] = rows[i] + cols[j];
            }
        }
        return matrixResult;
    }

    private static void displayMatrix(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int size = 4000;
        matrixAA = createMatrix(size);
        matrixBB = createMatrix(size);
        System.out.println("Preparing time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        calculateSingle(size);
        System.out.println("Single thread time: " + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        calculateMulty(size);
        System.out.println("Multi thread time: " + (System.currentTimeMillis() - startTime));

    }
}
