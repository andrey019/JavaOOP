package HomeWork.Lesson3.Task1;

/**
 * Created by andrey on 11.04.16.
 */
public class IndexException extends Exception {
    public IndexException(){
        super("Wrong index!");
    }

    @Override
    public String getMessage(){
        return "Index Exception: " + super.getMessage();
    }
}
