package HomeWork.Lesson6.Task5;


class CopyProgress implements Runnable {
    private long doneBytes = 0;
    private long allBytes;
    private int onePercent;

    public CopyProgress(long allBytes) {
        this.onePercent = (int) (allBytes / 100);
        this.allBytes = allBytes;
    }

    @Override
    public void run() {
        displayProgress();
    }

    void displayProgress() {
        int currentProgress = 0;
        int oldProgress = 0;
        System.out.println("Copying...");
        while (allBytes != doneBytes) {
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

    synchronized void addDoneBytes(int doneBytes) {
        this.doneBytes += doneBytes;
    }
}
