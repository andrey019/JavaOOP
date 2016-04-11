package HomeWork.Lesson3.Task3;

/**
 * Created by andrey on 11.04.16.
 */
class ParsIntException extends Exception{
    public ParsIntException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Parsing exception: " + super.getMessage();
    }
}
