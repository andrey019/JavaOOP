package HomeWork.Lesson6.Task2;


import java.util.Date;
import java.util.Scanner;

class Time extends Thread {
    private boolean running = true;

    public void run() {
        while (running) {
            System.out.println(new Date().toString());
            try {
                sleep(2000);
            } catch (InterruptedException e) {}
        }
        System.out.println("Interrupted!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Time time = new Time();
        System.out.println("Type in 'q' to quit");
        time.start();
        while (scanner.nextLine().equalsIgnoreCase("q"));
        time.running = false;
    }
}

