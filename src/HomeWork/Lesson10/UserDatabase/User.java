package HomeWork.Lesson10.UserDatabase;

import java.io.Serializable;

class User implements Serializable {
    private String name;
    private int age;
    private String sex;

    User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int hashCode() {
        return name.hashCode() * age * sex.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.hashCode() != this.hashCode()) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        } else {
            User temp = (User) obj;
            if ( (temp.getName().equalsIgnoreCase(name)) && (temp.getAge() == age) &&
                 (temp.getSex().equalsIgnoreCase(sex))) {
                return true;
            } else {
                return false;
            }
        }
    }
}
