package HomeWork.Lesson9.VirtualFS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

class VFSHandler {
    private VFSRoot root;
    private String path;
    private Scanner scanner;

    VFSHandler() {
        this.root = null;
        this.scanner = new Scanner(System.in);
    }

    void startScreen() {
        System.out.print("1) Open root VFS; ");
        System.out.print("2) Create and open new root VFS; ");
        System.out.println("0) Exit program");
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

    void openRoot(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            root = (VFSRoot) objectInputStream.readObject();
            root.setCurrentPosition(null);
            this.path = path;
            root();
        } catch (Exception e) {
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

    void rootScreen() {
        System.out.println("-- type in directories or files names to access them or...");
        System.out.print("1) Create file; ");
        System.out.print("2) Rename file; ");
        System.out.print("3) Delete file; ");
        System.out.print("4) Create directory; ");
        System.out.print("5) Rename directory; ");
        System.out.print("6) Delete directory; ");
        System.out.print("7) Search files; ");
        System.out.print("9) Return; ");
        System.out.println("0) Exit program");
    }

    void root() {
        rootScreen();
        rootContent();
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("mainmenu")) {
            switch (input) {
                case "1":
                    createFile();
                    input = "";
                    break;
                case "2":
                    renameFile();
                    input = "";
                    break;
                case "3":
                    deleteFile();
                    input = "";
                    break;
                case "4":
                    createDirectory();
                    input = "";
                    break;
                case "5":
                    renameDirectory();
                    input = "";
                    break;
                case "6":
                    deleteDirectory();
                    input = "";
                    break;
                case "7":
                    searchFiles();
                    input = "";
                    break;
                case "9":
                    if (root.getCurrentPosition() == null) {
                        input = "mainmenu";
                        break;
                    } else if (root.getCurrentPosition().getParentDirectory() == null) {
                        root.setCurrentPosition(null);
                        rootContent();
                    } else {
                        root.setCurrentPosition(root.getCurrentPosition().getParentDirectory());
                        rootContent();
                    }
                    input = "";
                    break;
                case "0":
                    System.exit(0);
                default:
                    if (input == "") {
                        input = scanner.nextLine();
                        break;
                    }
                    openFileOrDirectory(input);
                    input = scanner.nextLine();
                    break;
            }
        }
    }

    void rootContent() {
        if (root.getCurrentPosition() == null) {
            if ( (root.getChildDirectories() == null) && (root.getFiles() == null) ||
                    (root.getChildDirectories().isEmpty() && root.getFiles().isEmpty()) ) {
                System.out.println("No files or directories yet");
            } else {
                System.out.println("Content: ");
                for (VFSDirectory directory : root.getChildDirectories()) {
                    System.out.println("\\" + directory.getName());
                }
                for (VFSFile file : root.getFiles()) {
                    System.out.println("\\" + file.getName());
                }
            }
        } else {
            if ( (root.getCurrentPosition().getChildDirectories() == null) &&
                 (root.getCurrentPosition().getFiles() == null) ||
                 (root.getCurrentPosition().getChildDirectories().isEmpty() &&
                  root.getCurrentPosition().getFiles().isEmpty()) ) {
                System.out.println("No files or directories yet");
            } else {
                System.out.println("Content: ");
                for (VFSDirectory directory : root.getCurrentPosition().getChildDirectories()) {
                    System.out.println("\\" + directory.getFullPath());
                }
                for (VFSFile file : root.getCurrentPosition().getFiles()) {
                    System.out.println("\\" + file.getFullPath());
                }
            }
        }

    }

    void createFile() {
        System.out.print("Type in file name: ");
        if (root.addFile(scanner.nextLine())) {
            System.out.println("File added!");
            saveRoot();
            rootContent();
        } else {
            System.out.println("File with this name already exists!");
        }
    }

    void renameFile() {
        System.out.print("Type in the name of the file to rename: ");
        VFSFile file;
        if (root.getCurrentPosition() == null) {
            file = root.getFile(scanner.nextLine());
        } else {
            file = root.getCurrentPosition().getFile(scanner.nextLine());
        }
        if (file != null) {
            System.out.print("Type in new name: ");
            if (file.rename(scanner.nextLine())) {
                System.out.println("File renamed!");
                saveRoot();
                rootContent();
            } else {
                System.out.println("This file name is already in use!");
                rootContent();
            }
        } else {
            System.out.println("No such file exists!");
            rootContent();
        }
    }

    void deleteFile() {
        System.out.print("Type in the name of the file to delete: ");
        if (root.deleteFile(scanner.nextLine())) {
            System.out.println("File deleted!");
            saveRoot();
            rootContent();
        } else {
            System.out.println("No such file!");
        }
    }

    void createDirectory() {
        System.out.print("Type in directory name: ");
        if (root.addDirectory(scanner.nextLine())) {
            System.out.println("Directory added!");
            saveRoot();
            rootContent();
        } else {
            System.out.println("Directory with this name already exists!");
        }
    }

    void renameDirectory() {
        System.out.print("Type in the name of the directory to rename: ");
        VFSDirectory directoryRename;
        if (root.getCurrentPosition() == null) {
            directoryRename = root.getChildDirectory(scanner.nextLine());
        } else {
            directoryRename = root.getCurrentPosition().getChildDirectory(scanner.nextLine());
        }
        if (directoryRename != null) {
            System.out.print("Type in new name: ");
            if (directoryRename.rename(scanner.nextLine())) {
                System.out.println("Directory renamed!");
                saveRoot();
                rootContent();
            } else {
                System.out.println("This directory name is already in use!");
                rootContent();
            }
        } else {
            System.out.println("No such directory exists!");
            rootContent();
        }
    }

    void deleteDirectory() {
        System.out.print("Type in the name of the directory to delete: ");
        if (root.deleteDirectory(scanner.nextLine())) {
            System.out.println("Directory deleted!");
            saveRoot();
            rootContent();
        } else {
            System.out.println("No such directory!");
        }
    }

    void searchFiles() {
        System.out.print("Type in the name of the file you want to find: ");
        ArrayList<VFSFile> foundFiles = root.searchFiles(scanner.nextLine());
        if (foundFiles.isEmpty()) {
            System.out.println("Nothing is found");
        } else {
            search(foundFiles);
        }
    }

    void openFileOrDirectory(String input) {
        if (root.getCurrentPosition() == null) {
            if (openDirectory(root.getChildDirectory(input))) {
                rootScreen();
                rootContent();
            } else {
                if (openFile(root.getFile(input))) {
                    System.out.println("Done");
                    rootScreen();
                    rootContent();
                } else {
                    System.out.println("No such file or directory");
                }
            }
        } else {
            if (openDirectory(root.getCurrentPosition().getChildDirectory(input))) {
                rootScreen();
                rootContent();
            } else {
                if (openFile(root.getCurrentPosition().getFile(input))) {
                    System.out.println("Done");
                    rootScreen();
                    rootContent();
                } else {
                    System.out.println("No such file or directory");
                }
            }
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
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("exit")) {
            switch (input) {
                case "saveexit":
                    saveRoot();
                    input = "exit";
                    break;
                default:
                    file.getData().add(input);
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

    void searchScreen(ArrayList<VFSFile> foundFiles) {
        System.out.print("1) Open file; ");
        System.out.print("2) Rename file; ");
        System.out.print("3) Delete file; ");
        System.out.print("9) Return; ");
        System.out.println("0) Exit program");
        if ( (foundFiles == null) || (foundFiles.isEmpty()) ) {
            System.out.println("No files found");
        } else {
            for (int i = 0; i < foundFiles.size(); i++) {
                System.out.println("[" + (i) + "] \\" + foundFiles.get(i).getFullPath());
            }
        }
    }

    void search(ArrayList<VFSFile> foundFiles) {
        searchScreen(foundFiles);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("exittoroot")) {
            switch (input) {
                case "1":
                    searchOpenFile(foundFiles);
                    input = "";
                    break;
                case "2":
                    searchRename(foundFiles);
                    searchScreen(foundFiles);
                    input = "";
                    break;
                case "3":
                    searchDelete(foundFiles);
                    searchScreen(foundFiles);
                    input = "";
                    break;
                case "9":
                    input = "exittoroot";
                    rootScreen();
                    rootContent();
                    break;
                case "0":
                    System.exit(0);
                default:
                    if (input == "") {
                        input = scanner.nextLine();
                        break;
                    }
                    System.out.println("No such option!");
                    break;
            }
        }
    }

    void searchDelete(ArrayList<VFSFile> foundFiles) {
        System.out.print("Type in the index of the file to delete: ");
        VFSFile file = searchGetFile(foundFiles);
        if (file != null) {
            if (file.getParentDirectory() == null) {
                root.getFiles().remove(file);
                foundFiles.remove(file);
            } else {
                file.getParentDirectory().getFiles().remove(file);
                foundFiles.remove(file);
            }
        } else {
            System.out.println("No such index!");
        }
    }

    void searchRename(ArrayList<VFSFile> foundFiles) {
        System.out.print("Type in the index of the file to rename: ");
        VFSFile file = searchGetFile(foundFiles);
        if (file != null) {
            System.out.print("Type in new name: ");
            String name = scanner.nextLine();
            if ( file.rename(name) ) {
                System.out.println("File renamed!");
            } else {
                System.out.println("This name is already in use in this file's directory!");
            }
        } else {
            System.out.println("No such index!");
        }
    }

    void searchOpenFile(ArrayList<VFSFile> foundFiles) {
        System.out.print("Type in the index of the file to open: ");
        VFSFile file = searchGetFile(foundFiles);
        if (openFile(file)) {
            System.out.println("Done");
            searchScreen(foundFiles);
        } else {
            System.out.println("No such index!");
        }
    }

    VFSFile searchGetFile(ArrayList<VFSFile> foundFiles) {
        int input = -1;
        try {
            input = Integer.valueOf(scanner.nextLine());
        } catch (Exception e) {
            return null;
        }
        if ( (input < 0) || (input >= foundFiles.size()) ) {
            return null;
        } else {
            return foundFiles.get(input);
        }
    }
}
