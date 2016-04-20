package HomeWork.Lesson6.Task4;


class ChildThread extends Thread {
    public ChildThread(ThreadGroup threadGroup , String name) {
        super(threadGroup, name);
    }

    public void run() {
        System.out.println(getName() + " started");
        while (!isInterrupted());
        System.out.println(getName() + " finished");
    }
}