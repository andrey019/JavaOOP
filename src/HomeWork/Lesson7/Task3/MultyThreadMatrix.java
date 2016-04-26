package HomeWork.Lesson7.Task3;

import java.util.concurrent.CountDownLatch;

class MultyThreadMatrix implements Runnable {
    private int[][] matrixA;
    private int[][] matrixB;
    private int[] rowsResult;
    private int[] colsResult;
    private int startRow;
    private int endRow;
    private int startCol;
    private int endCol;
    private CountDownLatch countDownLatch;

    public MultyThreadMatrix(int[][] matrixA, int[][] matrixB, int quadrant,
                             CountDownLatch countDownLatch) throws MatrixException {

        int length = matrixA.length;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.rowsResult = new int[length / 2];
        this.colsResult = new int[length / 2];
        this.countDownLatch = countDownLatch;
        switch (quadrant) {
            case 1:
                startCol = 0;
                endCol = length / 2;
                startRow = 0;
                endRow = length / 2;
                break;
            case 2:
                startCol = length / 2;
                endCol = length;
                startRow = 0;
                endRow = length / 2;
                break;
            case 3:
                startCol = 0;
                endCol = length / 2;
                startRow = length / 2;
                endRow = length;
                break;
            case 4:
                startCol = length / 2;
                endCol = length;
                startRow = length / 2;
                endRow = length;
                break;
            default:
                throw new MatrixException("no such quadrant!");
        }
    }

    private void calculate() {
        int rowTemp = 1;
        int colTemp = 1;
        int j = 0;
        for (int i = startRow; i < endRow; i++) {
            for (int m = startCol; m < endCol; m++) {
                rowTemp *= matrixA[m][i];
                colTemp *= matrixB[i][m];
            }
            rowsResult[j] = rowTemp;
            colsResult[j] = colTemp;
            j++;
            rowTemp = 1;
            colTemp = 1;
        }
    }

    @Override
    public void run() {
        calculate();
        countDownLatch.countDown();
    }

    public int[] getRowsResult() {
        return rowsResult;
    }

    public int[] getColsResult() {
        return colsResult;
    }
}
