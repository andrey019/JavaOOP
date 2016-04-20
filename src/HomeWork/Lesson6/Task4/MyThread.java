package HomeWork.Lesson6.Task4;


import java.util.ArrayList;

public class MyThread extends Thread {
    private int childThreads;

    public MyThread(String name, int childThreads) {
        super(name);
        this.childThreads = childThreads;
    }

    public void run() {
        System.out.println(getName() + " started");
        ArrayList<ChildThread> childThreads = new ArrayList<>();
        ThreadGroup threadGroup = new ThreadGroup("child threads");
        for (int i = 0; i < this.childThreads; i++) {
            childThreads.add(new ChildThread(threadGroup, "ChildThread " + i));
            childThreads.get(i).start();
        }
        while (!isInterrupted());
        threadGroup.interrupt();
        System.out.println(getName() + " finished");
    }
}
