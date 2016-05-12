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

    HashMap<Integer, User> getUser(int key) {
        HashMap<Integer, User> foundUser = new HashMap<>();
        if (database.containsKey(key)) {
            foundUser.put(key, database.get(key));
        }
        return foundUser;
    }

    HashMap<Integer, User> getAllUsers() {
        return database;
    }

    HashMap<Integer, User> searchByName(String name) {
        HashMap<Integer, User> foundUsers = new HashMap<>();
        for (Map.Entry<Integer, User> entry : database.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                foundUsers.put(entry.getKey(), entry.getValue());
            }
        }
        return foundUsers;
    }

    HashMap<Integer, User> searchByAge(int age) {
        HashMap<Integer, User> foundUsers = new HashMap<>();
        for (Map.Entry<Integer, User> entry : database.entrySet()) {
            if (entry.getValue().getAge() == age) {
                foundUsers.put(entry.getKey(), entry.getValue());
            }
        }
        return foundUsers;
    }
}
