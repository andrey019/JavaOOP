package HomeWork.Lesson6.Task6;


class CopyProgress implements Runnable {
    private int currentProgress = 0;
    private int oldProgress = 0;
    private boolean stop = false;

    @Override
    public void run() {
        displayProgress();
    }

    void displayProgress() {
        System.out.println("Copying...");
        while (!stop) {
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
        System.out.println("Copying is completed");
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
