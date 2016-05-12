package HomeWork.Lesson10.UserDatabase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class UserDatabaseHandler {
    private UserDatabase database;
    private Scanner scanner;
    private String path;
    private String extension = ".UDB";

    UserDatabaseHandler() {
        this.database = null;
        this.scanner = new Scanner(System.in);
        this.path = null;
    }

    void start() {
        mainMenu();
    }

    private void mainMenuScreen() {
        System.out.print("1) Open user database; ");
        System.out.print("2) Create and open user database; ");
        System.out.println("0) Exit the program");
    }

    private void mainMenu() {
        mainMenuScreen();
        String input = scanner.nextLine();
        while (true) {
            switch (input) {
                case "1":
                    if (openDatabase()) {
                        database();
                        mainMenuScreen();
                    }
                    input = "";
                    break;
                case "2":
                    if (createDatabase()) {
                        database();
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
        String path = scanner.nextLine() + extension;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            database = (UserDatabase) objectInputStream.readObject();
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
            objectOutputStream.writeObject(database);
            return true;
        } catch (Exception e) {
            System.out.println("Database save error!");
            return false;
        }
    }

    private boolean createDatabase() {
        System.out.print("Type in new database file full path: ");
        this.path = scanner.nextLine() + extension;
        database = new UserDatabase();
        if (saveDatabase()) {
            System.out.println("New database created");
            return true;
        } else {
            System.out.println("Database creation error!");
            return false;
        }
    }

    private void databaseScreen() {
        System.out.println("-- choose...");
        System.out.print("1) Add user; ");
        System.out.print("2) Delete user; ");
        System.out.print("3) Search user by key; ");
        System.out.print("4) Search user by name; ");
        System.out.print("5) Search user by age; ");
        System.out.print("6) Show all; ");
        System.out.print("9) Back to main menu; ");
        System.out.println("0) Exit the program");
    }

    private void database() {
        databaseScreen();
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("tomainmenu")) {
            switch (input) {
                case "1":
                    addUser();
                    input = "";
                    break;
                case "2":
                    deleteUser();
                    input = "";
                    break;
                case "3":
                    searchByKey();
                    input = "";
                    break;
                case "4":
                    searchByName();
                    input = "";
                    break;
                case "5":
                    searchByAge();
                    input = "";
                    break;
                case "6":
                    showUsersOnScreen(database.getAllUsers());
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
                    System.out.println("No such option!");
                    break;
            }
        }
    }

    private void addUser() {
        System.out.print("Enter the name of user: ");
        String name = scanner.nextLine();
        int age = userAgeInput();
        System.out.print("Enter the sex of user: ");
        String sex = scanner.nextLine();
        if (database.addUser(name, age, sex)) {
            System.out.println("User added!");
            saveDatabase();
        } else {
            System.out.println("This user is already exists!");
        }
    }

    private void deleteUser() {
        System.out.print("Enter the key of user: ");
        int key = userKeyInput();
        User user = null;
        if (!database.getUser(key).isEmpty()) {
            user = database.getUser(key).get(key);
            if (database.deleteUser(user)) {
                System.out.println("User deleted!");
                saveDatabase();
            } else {
                System.out.println("Delete error!");
            }
        } else {
            System.out.println("No such user exists!");
        }
    }

    private void searchByKey() {
        System.out.print("Enter the key: ");
        int key = userKeyInput();
        HashMap<Integer, User> user = database.getUser(key);
        if (!user.isEmpty()) {
            showUsersOnScreen(user);
        } else {
            System.out.println("No user with such key!");
        }
    }

    private void searchByName() {
        System.out.print("Enter the name of the user: ");
        String name = scanner.nextLine();
        HashMap<Integer, User> users = database.searchByName(name);
        if (!users.isEmpty()) {
            showUsersOnScreen(users);
        } else {
            System.out.println("No users found with such name!");
        }
    }

    private void searchByAge() {
        System.out.print("Enter the age of user: ");
        int age = userAgeInput();
        HashMap<Integer, User> users = database.searchByAge(age);
        if (!users.isEmpty()) {
            showUsersOnScreen(users);
        } else {
            System.out.println("No users found with such age!");
        }
    }

    private int userKeyInput() {
        int key = -1;
        while (key < 0) {
            try {
                key = Integer.valueOf(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("This is not a correct key! Try again: ");
            }
        }
        return key;
    }

    private int userAgeInput() {
        int age = -1;
        while (age < 0) {
            try {
                age = Integer.valueOf(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("This is not a correct age! Try again: ");
            }
        }
        return age;
    }

    private void showUsersOnScreen(HashMap<Integer, User> users) {
        if (!users.isEmpty()) {
            System.out.println("[key] name age sex");
            for (Map.Entry<Integer, User> entry : users.entrySet()) {
                System.out.println("[" + entry.getKey() + "] " + entry.getValue().getName() + " " +
                        entry.getValue().getAge() + " " + entry.getValue().getSex());
            }
        } else {
            System.out.println("Database is empty!");
        }
    }
}
