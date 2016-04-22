package HomeWork.Lesson6.Task6;


class Main {
    static void copyFile(String src, String to) {
        Thread copyThread = new Thread(new Copy(src, to));
        copyThread.start();
    }

    public static void main(String[] args) {
        copyFile("c:\\razved.mp4", "c:\\razved_copy.mp4");
    }
}
