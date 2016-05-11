package HomeWork.Lesson10.UserDatabase;

import java.io.Serializable;
import java.util.*;

class UserDatabase implements Serializable {
    private HashMap<Integer, User> database;
    private HashSet<Integer> reusableKeys;
    private int currentKey;

    UserDatabase() {
        this.database = new HashMap<>();
        this.reusableKeys = new HashSet<>();
        this. currentKey = 0;
    }

    boolean addUser(String name, int age, String sex) {
        User user = new User(name, age, sex);
        if (database.containsValue(user)) {
            return false;
        } else {
            if (reusableKeys.isEmpty()) {
                database.put(currentKey, user);
                currentKey++;
                return true;
            } else {
                Iterator<Integer> iterator = reusableKeys.iterator();
                Integer key = iterator.next();
                database.put(key, user);
                reusableKeys.remove(key);
                return true;
            }
        }
    }

    boolean deleteUser(User user) {
        if (database.containsValue(user)) {
            for (Map.Entry<Integer, User> entry : database.entrySet()) {
                if (entry.getValue().equals(user)) {
                    reusableKeys.add(entry.getKey());
                    database.remove(entry.getKey());
                    return true;
                }
            }
        }
        return false;
    }

    User getUser(int key) {
        return database.get(key);
    }

    ArrayList<User> searchByName(String name) {
        ArrayList<User> foundUsers = new ArrayList<>();
        for (Map.Entry<Integer, User> entry : database.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                foundUsers.add(entry.getValue());
            }
        }
        return foundUsers;
    }

    ArrayList<User> searchByAge(int age) {
        ArrayList<User> foundUsers = new ArrayList<>();
        for (Map.Entry<Integer, User> entry : database.entrySet()) {
            if (entry.getValue().getAge() == age) {
                foundUsers.add(entry.getValue());
            }
        }
        return foundUsers;
    }
}
