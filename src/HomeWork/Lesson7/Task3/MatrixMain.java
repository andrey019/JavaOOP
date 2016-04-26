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
        try {
            for (int i = 0; i < 4; i++) {
                multy.add(new MultyThreadMatrix(matrixA, matrixB, i + 1, countDownLatch));
                multy.get(i).start();
            }
        } catch (MatrixException e) {
            e.getMessage();
            System.exit(-1);
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
        multyRows.start();
        multyCols.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getMatrixResult(multyRows.getResult(), multyCols.getResult(), size);
    }

    private static int[][] getMatrixResult(int[] rows, int[] cols, int size) {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        int[][] matrixResult = new int[size][size];
        ArrayList<MultyThreadFinal> multyFinals = new ArrayList<>();
        try {
            for (int i = 0; i < 4; i++) {
                multyFinals.add(new MultyThreadFinal(matrixResult, rows, cols, i + 1, countDownLatch));
                multyFinals.get(i).start();
            }
        } catch (MatrixException e) {
            e.getMessage();
            System.exit(-1);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        int size = 11000;
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

//Preparing time: 3214
//Single thread time: 6049
//Multi thread time: 2286

