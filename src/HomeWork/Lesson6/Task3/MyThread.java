package HomeWork.Lesson6.Task3;

import java.util.ArrayList;

/**
 * Created by Лена on 16.04.2016.
 */
class MyThread extends Thread {
    public MyThread(String name) {
        super();
        setName(name);
    }

    public void run() {
        System.out.println("Created " + getName());
        while (!isInterrupted()){;}
        System.out.println(getName() + " is finished");
    }

    public static void main(String[] args) {
        ArrayList<MyThread> threads = new ArrayList<MyThread>();
        for (int i = 0; i < 100; i++) {
            threads.add(new MyThread("Thread " + i));
            threads.get(i).start();
        }
        for (MyThread t : threads) {
            t.interrupt();
        }
    }
}
