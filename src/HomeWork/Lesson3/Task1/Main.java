package HomeWork.Lesson3.Task1;

/**
 * Created by Лена on 09.04.2016.
 */
public class Main {
    public static void testMet() throws ParamExeption{
        throw new ParamExeption();
    }

    public static void main(String[] args){
        try {
            testMet();
        } catch (ParamExeption ex){
            System.out.println(ex.getMessage());
        }
    }
}
