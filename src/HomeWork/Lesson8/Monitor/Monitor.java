package HomeWork.Lesson8.Monitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Monitor extends Thread {
    private String path;
    ArrayList<File> newFiles = new ArrayList<>();
    ArrayList<File> oldFiles = new ArrayList<>();
    ArrayList<Long> newTimings = new ArrayList<>();
    ArrayList<Long> oldTimings = new ArrayList<>();

    public Monitor(String path) {
        this.path = path;
        oldFill(path);
    }

    private void oldFill(String path) {
        try {
            File src = new File(path);
            File[] allFiles = src.listFiles();
            for (File f : allFiles) {
                oldFiles.add(f);
                oldTimings.add(f.lastModified());
                if (!f.isFile()) {
                    oldFill(f.getCanonicalPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void monitoring(String path) {
        try {
            File src = new File(path);
            File[] allFiles = src.listFiles();
            for (File f : allFiles) {
                newFiles.add(f);
                newTimings.add(f.lastModified());
                if (f.isFile()) {
                    int oldFileIndex = oldFiles.indexOf(f);
                    if (oldFileIndex != -1) {
                        long old = oldFiles.get(oldFileIndex).lastModified();
                        long neww = f.lastModified();
                        if (!oldTimings.contains(f.lastModified())) {
                            System.out.println(f.getCanonicalPath() + " has been modified");
                        }
                    } else {
                        System.out.println(f.getCanonicalPath() + " has been created");
                    }
                } else {
                    if (!oldFiles.contains(f)) {
                        System.out.println(f.getCanonicalPath() + " has been created");
                    }
                    monitoring(f.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkDeleted() {
        try {
            for (int i = 0; i < oldFiles.size(); i++) {
                if (!newFiles.contains(oldFiles.get(i))) {
                    System.out.println(oldFiles.get(i).getCanonicalPath() + " has been deleted");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clear() {
        oldFiles.clear();
        oldFiles.addAll(newFiles);
        newFiles.clear();
        oldFiles.trimToSize();
        newFiles.trimToSize();
        oldTimings.clear();
        oldTimings.addAll(newTimings);
        newTimings.clear();
        oldTimings.trimToSize();
        newTimings.trimToSize();
    }

    public void run() {
        while (true) {
            monitoring(path);
            checkDeleted();
            clear();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
