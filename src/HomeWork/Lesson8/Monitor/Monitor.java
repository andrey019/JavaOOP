package HomeWork.Lesson8.Monitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Monitor extends Thread {
    private String path;
    ArrayList<File> newDataBase = new ArrayList<>();
    ArrayList<File> oldDataBase = new ArrayList<>();


    public Monitor(String path) {
        this.path = path;
        oldDataBaseFill(path);
    }

    private void oldDataBaseFill(String path) {
        try {
            File src = new File(path);
            File[] allFiles = src.listFiles();
            for (File f : allFiles) {
                oldDataBase.add(f);
                if (!f.isFile()) {
                    oldDataBaseFill(f.getCanonicalPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkDeleted() {
        try {
            for (int i = 0; i < oldDataBase.size(); i++) {
                if (!newDataBase.contains(oldDataBase.get(i))) {
                    System.out.println(oldDataBase.get(i).getCanonicalPath() + " has been deleted");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    void fillDataBase(String path) {
//        try {
//            File src = new File(path);
//            File[] allFiles = src.listFiles();
//            for (int i = 0; i < allFiles.length; i++) {
//                if (allFiles[i].isFile()) {
//                    dataBase.add(allFiles[i].lastModified());
//                } else {
//                    fillDataBase(allFiles[i].getCanonicalPath());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    void monitoring(String path) {
        try {
            File src = new File(path);
            File[] allFiles = src.listFiles();
            for (File f : allFiles) {
                newDataBase.add(f);
                if (f.isFile()) {
                    int oldFileIndex = oldDataBase.indexOf(f);
                    if (oldFileIndex != -1) {
                        if (oldDataBase.get(oldFileIndex).lastModified() != f.lastModified()) {
                            System.out.println(f.getCanonicalPath() + " has been modified");
                        }
                    } else {
                        System.out.println(f.getCanonicalPath() + " has been created");
                    }
                } else {
                    monitoring(f.getCanonicalPath());
                }
            }

//            for (int i = 0; i < newDataBase.size(); i++) {
//                File tempFile = newDataBase.get(i);
//                if (tempFile.isFile()) {
//                    int oldFileIndex = oldDataBase.indexOf(tempFile);
//                    if (oldFileIndex != -1) {
//                        if (oldDataBase.get(oldFileIndex).lastModified() != tempFile.lastModified()) {
//                            System.out.println(tempFile.getCanonicalPath() + " has been modified");
//                        }
//                    } else {
//                        System.out.println(tempFile.getCanonicalPath() + " has been created");
//                    }
//                } else {
//                    monitoring(tempFile.getCanonicalPath());
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clear() {
        oldDataBase.clear();
        oldDataBase.addAll(newDataBase);
        newDataBase.clear();
        oldDataBase.trimToSize();
        newDataBase.trimToSize();
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
