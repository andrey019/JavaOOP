package HomeWork.Lesson3.Task1;

/**
 * Created by andrey on 11.04.16.
 */
class SpaceException extends Exception {
    public SpaceException(){
        super("Spaces are not allowed!");
    }

    @Override
    public String getMessage(){
        return "Space exception: " + super.getMessage();
    }
}
