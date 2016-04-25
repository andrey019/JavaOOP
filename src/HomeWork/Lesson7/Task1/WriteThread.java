package HomeWork.Lesson7.Task1;


import java.util.concurrent.CountDownLatch;

class WriteThread implements Runnable {
    private String string;
    private WriterTxt writerTxt;
    private CountDownLatch countDownLatch;

    public WriteThread(WriterTxt writerTxt, String string, CountDownLatch countDownLatch) {
        this.string = string;
        this.writerTxt = writerTxt;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            writerTxt.getLock().lock();
            writerTxt.writeLn(string);
            Thread.sleep(100);      //making sure we've got some fighting for lock
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writerTxt.getLock().unlock();
            countDownLatch.countDown();
        }
    }
}
