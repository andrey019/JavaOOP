package HomeWork.Lesson3.Students;

/**
 * Created by andrey on 12.04.16.
 */
class NameException extends Exception {
    public NameException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Excetion: " + super.getMessage();
    }
}
