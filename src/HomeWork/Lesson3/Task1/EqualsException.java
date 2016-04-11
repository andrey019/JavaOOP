package HomeWork.Lesson3.Task1;

/**
 * Created by andrey on 11.04.16.
 */
class EqualsException extends Exception {
    public EqualsException(){
        super("Equals sign missing!");
    }

    @Override
    public String getMessage(){
        return "Equality exception: " + super.getMessage();
    }
}
