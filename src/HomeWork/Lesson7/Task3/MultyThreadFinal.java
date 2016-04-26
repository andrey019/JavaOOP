package HomeWork.Lesson7.Task3;

import java.util.concurrent.CountDownLatch;

class MultyThreadFinal extends Thread {
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;
    private int[] rows;
    private int[] cols;
    private int[][] matrixResult;
    private CountDownLatch countDownLatch;

    public MultyThreadFinal(int[][] matrixResult, int[] rows, int[] cols, int quadrant,
                            CountDownLatch countDownLatch) throws MatrixException {

        this.matrixResult = matrixResult;
        this.rows = rows;
        this.cols = cols;
        this.countDownLatch = countDownLatch;
        quadrantStartEnd(quadrant, rows.length);
    }

    private void quadrantStartEnd(int quadrant, int length) throws MatrixException {
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
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                matrixResult[j][i] = rows[i] + cols[j];
            }
        }
    }

    @Override
    public void run() {
        calculate();
        countDownLatch.countDown();
    }
}
