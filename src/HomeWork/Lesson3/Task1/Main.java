package HomeWork.Lesson3.Task1;

/**
 Написать метод разбора списка параметров в формате URL:
 para1=value1&param2=value2&param3=value3. В случае ошибки в
 формате бросать исключение
 */
public class Main {
    static void checkURL(String str, int paramAmount) throws UrlException{
        if (str.contains(" ")){
            throw new UrlException("Spaces are not allowed!");
        }
        String[] ampersandAmount = str.split("&");
        if (ampersandAmount.length != paramAmount) throw new UrlException("Ampersand is missing!");

        int beginParam = -1;
        int endParam = 0;
        int beginValue = 0;
        int endValue = 0;
        for (int i = 0; i < paramAmount; i++){
            endParam = str.indexOf("param", beginParam + 1);
            if (-1 != endParam){
                beginParam = endParam;
            } else throw new UrlException("Wrong parameter!");
            endValue = str.indexOf("value", beginValue + 1);
            if (-1 != endValue){
                beginValue = endValue;
            } else throw new UrlException("Wrong value!");
            if (str.charAt(endValue-1) != '=') throw new UrlException("Equal sign missing!");
            if (str.charAt(endValue - 2) != str.charAt(endValue + 5)) throw new UrlException("Wrong index!");
        }
    }

    public static void main(String[] args){
        int paramAmount = 3;
        String input = "praram1=value1&param2=value2&param3=value3";

        try {
            checkURL(input, paramAmount);
        } catch (UrlException e) {
            System.out.print(e.getMessage());
        }
    }
}
