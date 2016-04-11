package HomeWork.Lesson3.Task1;

/**
 * Created by andrey on 11.04.16.
 */
public class AmpersandException extends Exception{
    public AmpersandException(){
        super("Ampersand is missing!");
    }

    @Override
    public String getMessage(){
        return "Ampersand exception: " + super.getMessage();
    }
}
