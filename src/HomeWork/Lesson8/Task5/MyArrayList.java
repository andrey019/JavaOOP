package HomeWork.Lesson8.Task5;

class MyArrayList<A> {
    private Object[] array;

    public MyArrayList() {
        array = new Object[1];
    }

    public void addElement(A value) {
        if ((array[0] == null) && (array.length == 1)) {
            array[0] = value;
        } else {
            Object[] temp = new Object[array.length + 1];
            temp[array.length] = value;
            System.arraycopy(array, 0, temp, 0, temp.length - 1);
            array = temp;
        }
    }

    public A getElement(int index) {
        return (A) array[index];
    }
//
//    public void add(Object value) {
//        A[] temp;
//        temp = new value[array.length + 1];
//        temp[array.length + 1] = value;
//        System.arraycopy(array, 0, temp, 0, temp.length);
//        array = temp;
//    }
}
