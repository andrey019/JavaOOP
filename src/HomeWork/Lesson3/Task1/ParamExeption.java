package HomeWork.Lesson3.Task1;

/**
 * Created by Лена on 09.04.2016.
 */
public class ParamExeption extends Exception{
    public ParamExeption(){
        super("Wrong parameter!");
    }

    @Override
    public String getMessage(){
        return "ParamExeption: " + super.getMessage();
    }
}
