package HomeWork.Lesson3.Task1;

/**
 * Created by Лена on 09.04.2016.
 */
class ValueExeption extends Exception {
    public ValueExeption(){
        super("Wrong value!");
    }

    @Override
    public String getMessage(){
        return "Value exception: " + super.getMessage();
    }
}
