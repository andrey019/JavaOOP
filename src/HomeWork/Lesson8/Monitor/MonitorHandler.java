package HomeWork.Lesson8.Monitor;

import java.util.ArrayList;

class MonitorHandler extends Thread {
    ArrayList<Monitor> monitors = new ArrayList<>();

    public MonitorHandler(String...paths) {
        for (int i = 0; i < paths.length; i++) {
            monitors.add(new Monitor(paths[i]));
        }
    }

    public void run() {
        for (Thread thread : monitors) {
            thread.start();
        }
        while (true) {
            System.out.println("Monitoring...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
