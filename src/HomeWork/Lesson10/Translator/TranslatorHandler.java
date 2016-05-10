package HomeWork.Lesson10.Translator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

class TranslatorHandler {
    private Translator translator;
    private Scanner scanner;
    private String path;

    TranslatorHandler() {
        this.translator = null;
        this.path = null;
        this.scanner = new Scanner(System.in);
    }

    void start() {
        mainMenu();
    }

    private void mainMenuScreen() {
        System.out.print("1) Open translation database; ");
        System.out.print("2) Create and open translation database; ");
        System.out.println("0) Exit the program");
    }

    private void mainMenu() {
        mainMenuScreen();
        String input = scanner.nextLine();
        while (true) {
            switch (input) {
                case "1":
                    if (openDatabase()) {
                        translator();
                        mainMenuScreen();
                    }
                    input = "";
                    break;
                case "2":
                    if (createDatabase()) {
                        translator();
                        mainMenuScreen();
                    }
                    input = "";
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    if (input == "") {
                        input = scanner.nextLine();
                        break;
                    }
                    System.out.println("No such option!");
                    input = scanner.nextLine();
                    break;
            }
        }
    }

    private boolean openDatabase() {
        System.out.print("Type in database file full path: ");
        String path = scanner.nextLine() + ".TDB";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            translator = (Translator) objectInputStream.readObject();
            this.path = path;
            return true;
        } catch (Exception e) {
            System.out.println("No such database exists, or it has been damaged");
            return false;
        }
    }

    private boolean saveDatabase() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(translator);
            return true;
        } catch (Exception e) {
            System.out.println("Database save error!");
            return false;
        }
    }

    private boolean createDatabase() {
        System.out.print("Type in new database file full path: ");
        this.path = scanner.nextLine() + ".TDB";
        translator = new Translator();
        if (saveDatabase()) {
            System.out.println("New database created");
            return true;
        } else {
            System.out.println("Database creation error!");
            return false;
        }
    }

    private void translatorScreen() {
        System.out.println("-- type in word to get translation or...");
        System.out.print("1) Add translation; ");
        System.out.print("2) Delete translation; ");
        System.out.print("9) Back to main menu; ");
        System.out.println("0) Exit the program");
    }

    private void translator() {
        translatorScreen();
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("tomainmenu")) {
            switch (input) {
                case "1":
                    addTranslation();
                    input = "";
                    break;
                case "2":
                    deleteTranslation();
                    input = "";
                    break;
                case "9":
                    input = "tomainmenu";
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    if (input == "") {
                        input = scanner.nextLine();
                        break;
                    }
                    translation(input);
                    input = "";
                    break;
            }
        }
    }

    private void addTranslation() {
        System.out.print("Enter the word: ");
        String word = scanner.nextLine();
        if (translator.contains(word)) {
            System.out.print("This word is already translated! Do you want to overwrite? (Y/N): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                System.out.print("Enter the translation: ");
                translator.addTranslation(word, scanner.nextLine());
                saveDatabase();
                System.out.println("Done!");
            } else {
                System.out.println("Aborted!");
            }
        } else {
            System.out.print("Enter the translation: ");
            translator.addTranslation(word, scanner.nextLine());
            saveDatabase();
            System.out.println("Done!");
        }
    }

    private void deleteTranslation() {
        System.out.print("Enter the word: ");
        if (translator.removeTranslation(scanner.nextLine())) {
            saveDatabase();
            System.out.println("Deleted!");
        } else {
            System.out.println("Not found in database!");
        }
    }

    private void translation(String input) {
        String translation = translator.translate(input);
        if (translation != null) {
            System.out.println("Translation: " + translation);
        } else {
            System.out.println("Not found in database!");
        }
    }
}
