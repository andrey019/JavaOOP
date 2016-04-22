package HomeWork.Lesson6.Task5;


class CopyProgress implements Runnable {
    private long doneBytes = 0;
    private int onePercent;
    private int threadsAmount;
    private int doneThreadsAmount = 0;

    public CopyProgress(long allBytes, int threadsAmount) {
        this.onePercent = (int) (allBytes / 100);
        this.threadsAmount = threadsAmount;
    }

    @Override
    public void run() {
        displayProgress();
    }

    void displayProgress() {
        int currentProgress = 0;
        int oldProgress = 0;
        System.out.println("Copying...");
        while (threadsAmount != doneThreadsAmount) {
            currentProgress = (int) (doneBytes / onePercent);
            if (currentProgress != oldProgress) {
                System.out.println(currentProgress + "% is completed");
                oldProgress = currentProgress;
            }
            try {
                Thread.sleep(1000);     //delay to constrict the amount of progress messages
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done!");
    }

    void addDoneBytes(int doneBytes) {
        this.doneBytes += doneBytes;
    }

    void threadDone() {
        doneThreadsAmount++;
    }
}
