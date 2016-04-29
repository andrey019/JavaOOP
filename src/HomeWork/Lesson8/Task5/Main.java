package HomeWork.Lesson8.Task5;

class Main {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.addElement("0000");
        list.addElement("1111");
        list.addElement("2222");
        list.addElement("3333");
        list.addElement("4444");
        list.addElement("1111");
        list.addElement("5555");

        list.deleteElement(2);

        System.out.println(list.contains("3333"));
        System.out.println(list.indexOf("4444"));
        System.out.println(list.lastIndexOf("1111"));

        MyArrayList<Integer> listInt = new MyArrayList<>();
        listInt.addElement(0);
        listInt.addElement(1);
        listInt.addElement(2);
        listInt.addElement(3);
        listInt.addElement(4);
        listInt.addElement(5);
        listInt.addElement(6);

        Integer integer = listInt.getElement(4);

        System.out.println(integer);
        System.out.println(listInt.toString());
        System.out.println(listInt.size());
    }
}
