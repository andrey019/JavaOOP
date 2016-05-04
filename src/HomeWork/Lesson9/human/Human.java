package HomeWork.Lesson9.human;

import java.io.Serializable;

class Human implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    public String name;
    public int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "My name is " + name + ", i'm " + age + " years old";
    }

    @Override
    public int hashCode() {
        return age * name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.hashCode() != this.hashCode()) {
            return false;
        }
        if (obj.getClass() == this.getClass()) {
            Human temp = (Human) obj;
            if ( (temp.age == this.age) && (temp.name.equalsIgnoreCase(this.name)) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Human human = (Human) super.clone();
        human.name = new String(this.name);
        return human;
    }
}
