package HomeWork.Lesson3.Task1;

/**
 * Created by andrey on 11.04.16.
 */
public class UrlException extends Exception {
    public UrlException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "URL Exception: " + super.getMessage();
    }
}
