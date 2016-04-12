package HomeWork.Lesson3.Students;

/**
 * Created by andrey on 12.04.16.
 */
class AddException extends Exception {
    public AddException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Exception: " + super.getMessage();
    }
}
