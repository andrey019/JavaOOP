package HomeWork.Lesson10.Translator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

class Translator implements Serializable {
    private HashMap<String, String> dataBase;

    Translator() {
        this.dataBase = new HashMap<>();
    }

    String translate(String word) {
        if (dataBase.containsKey(word)) {
            return dataBase.get(word);
        } else if (dataBase.containsValue(word)) {
            return getKey(word);
        }
        return null;
    }

    private String getKey(String value) {
        for (Map.Entry<String, String> entry : dataBase.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    boolean contains(String word) {
        if (dataBase.containsKey(word)) {
            return true;
        } else if (dataBase.containsValue(word)) {
            return true;
        }
        return false;
    }

    void addTranslation(String word, String translation) {
        dataBase.put(word, translation);
    }

    boolean removeTranslation(String word) {
        if (!contains(word)) {
            return false;
        } else if (dataBase.containsKey(word)) {
            dataBase.remove(word);
            return true;
        } else {
            dataBase.remove(getKey(word));
            return true;
        }
    }
}
