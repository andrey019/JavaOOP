package HomeWork.Lesson9.VirtualFS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

class VFSHandler {
    private VFSRoot root;
    private String path;
    private Scanner scanner;

    VFSHandler() {
        this.root = null;
        this.scanner = new Scanner(System.in);
    }

    void start() {
        startScreen();
        String choice = scanner.nextLine();
        while (true) {
            switch (choice) {
                case "1":
                    System.out.print("Type in full root path: ");
                    path = scanner.nextLine() + ".VFS";
                    openRoot(path);
                    choice = "";
                    startScreen();
                    break;
                case "2":
                    if (createRoot()) {
                        openRoot(path);
                        startScreen();
                    } else {
                        path = "";
                    }
                    choice = "";
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    if (choice != "") {
                        System.out.println("No such option!");
                    }
                    choice = scanner.nextLine();
            }
        }
    }

    void startScreen() {
        System.out.println("1) Open root VFS");
        System.out.println("2) Create and open new root VFS");
        System.out.println("0) Exit program");
    }

    void openRoot(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            root = (VFSRoot) objectInputStream.readObject();
            this.path = path;
            root();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such root file, or it has been damaged");
            root = null;
        }
    }

    boolean saveRoot() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(root);
            return true;
        } catch (Exception e) {
            System.out.println("Root file save error!");
            return false;
        }
    }

    boolean createRoot() {
        System.out.print("Type in new VFS root file full path: ");
        this.path = scanner.nextLine() + ".VFS";
        root = new VFSRoot();
        if (saveRoot()) {
            System.out.println("New root created");
            return true;
        } else {
            System.out.println("Root creation error!");
            return false;
        }
    }

    void rootScreen() {     //relate to root
        System.out.println("-- type in directories or files names to access them or...");
        System.out.println("1) Create file");
        System.out.println("2) Rename file");
        System.out.println("3) Delete file");
        System.out.println("4) Create directory");
        System.out.println("5) Rename directory");
        System.out.println("6) Delete directory");
        System.out.println("9) Return");
        System.out.println("0) Exit program");
    }

    void rootContent() {
        if ( (root.getChildDirectories() == null) && (root.getFiles() == null) ||
             (root.getChildDirectories().isEmpty() && root.getFiles().isEmpty()) ) {
            System.out.println("No files or directories yet");
        } else {
            for (VFSDirectory directory : root.getChildDirectories()) {
                System.out.println("\\" + directory.getName());
            }
            for (VFSFile file : root.getFiles()) {
                System.out.println("\\" + file.getName());
            }
        }
    }

    void root() {   // relate to rootScreen
        rootScreen();
        rootContent();
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("9")) {
            switch (input) {
                case "1":
                    System.out.print("Type in file name: ");
                    if (root.addFile(getInputString())) {
                        System.out.println("File added!");
                        saveRoot();
                        rootScreen();
                        rootContent();
                    } else {
                        System.out.println("File with this name already exists!");
                    }
                    input = "";
                    break;
                case "2":

                case "9":
                    break;
                case "0":
                    System.exit(0);

                default:
                    if (input == "") {
                        input = scanner.nextLine();
                        break;
                    }
                    if (openDirectory(root.getDirectory(input))) {
                        // change position -
                        break;
                    } else {
                        if (openFile(root.getFile(input))) {
                            System.out.println("Done");
                            rootScreen();
                            rootContent();
                        } else {
                            System.out.println("No such file or directory");
                        }
                    }
                    input = scanner.nextLine();
                    break;
            }
        }
    }

    boolean openDirectory(VFSDirectory directory) {
        if (directory == null) {
            return false;
        } else {
            root.setCurrentPosition(directory);
            return true;
        }
    }

    boolean openFile(VFSFile file) {
        if (file == null) {
            return false;
        } else {
            fileContent(file);
            fileAddContent(file);
            return true;
        }
    }

    void fileContent(VFSFile file) {
        if (file.getData().isEmpty()) {
            System.out.println("File is empty");
        } else {
            for (String data : file.getData()) {
                System.out.println(data);
            }
        }
    }

    void fileAddContent(VFSFile file) {
        System.out.println("Fill the file. To save and exit type in 'saveexit'");
        String input = getInputString();
        while (!input.equalsIgnoreCase("exit")) {
            switch (input) {
                case "saveexit":
                    saveRoot();
                    input = "exit";
                    break;
                default:
                    file.getData().add(input);
                    input = getInputString();
                    break;
            }
        }
    }









    void createFile() {

    }

    void createNewRoot() {

    }

    private int getInputInt() {
        int input = -1;
        while (input < 0) {
            try {
                input = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("No such choice!");
            }
        }
        return input;
    }

    private String getInputString() {
        return scanner.nextLine();
    }
}
