package HomeWork.Lesson3.Task1;

/**
 Написать метод разбора списка параметров в формате URL:
 para1=value1&param2=value2&param3=value3. В случае ошибки в
 формате бросать исключение
 */
public class Main {
    static void checkURL(String str, int paramAmount) throws Exception{
        if (str.contains(" ")){
            throw new SpaceException();
        }
        String[] ampersandAmount = str.split("&");
        if (ampersandAmount.length != paramAmount) throw new AmpersandException();

        int beginParam = -1;
        int endParam = 0;
        int beginValue = -1;
        int endValue = 0;
        for (int i = 0; i < paramAmount; i++){
            endParam = str.indexOf("param", beginParam + 1);
            if (-1 != endParam){
                beginParam = endParam;
            } else throw new ParamExeption();
            endValue = str.indexOf("value", beginValue + 1);
            if (str.charAt(endValue-1) != '=') throw new EqualsException();
            if (-1 != endValue){
                beginValue = endValue;
            } else throw new ValueExeption();
            if (str.charAt(endValue - 2) != str.charAt(endValue + 5)) throw new IndexException();
        }
    }

    public static void main(String[] args){
        int paramAmount = 3;
        String input = "param1=value1&param2=value2&param3=value3";

        try {
            checkURL(input, paramAmount);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
