package HomeWork.Lesson3.Task4;

/**
 * Created by andrey on 11.04.16.
 */
class DualityException extends Exception {
    public DualityException(){
        super("You can't be at two schools at the same time");
    }

    @Override
    public String getMessage(){
        return "Duality exception: " + super.getMessage();
    }
}
