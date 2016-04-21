package HomeWork.Lesson6.Task6;


import java.util.Date;

class CopyProgress implements Runnable {
    private int currentProgress = 0;
    private int oldProgress = 0;
    private double time = -1;
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
        while (time == -1) {}
        System.out.println("Copying is completed in " + time + " seconds");
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
