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

    public void deleteElement(int index) {
        Object[] temp = new Object[array.length - 1];
        if (index == 0) {
            System.arraycopy(array, 1, temp, 0, temp.length);
            array = temp;
        } else if (index == array.length - 1) {
            System.arraycopy(array, 0, temp, 0, temp.length);
            array = temp;
        } else {
            System.arraycopy(array, 0, temp, 0, index);
            System.arraycopy(array, index + 1, temp, index, temp.length - index);
            array = temp;
        }
    }

    public boolean contains(A value) {
        if (indexOf(value) != -1) {
            return true;
        }
        return false;
    }

    public int indexOf(A value) {
        if (array[0].getClass() == "string".getClass()) {
            String strValue = (String) value;
            for (int i = 0; i < array.length; i++) {
                String temp = (String) array[i];
                if (temp.equalsIgnoreCase(strValue)) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(A value) {
        if (array[0].getClass() == "string".getClass()) {
            String strValue = (String) value;
            for (int i = array.length - 1; i >= 0; i--) {
                String temp = (String) array[i];
                if (temp.equalsIgnoreCase(strValue)) {
                    return i;
                }
            }
        } else {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == value) {
                    return i;
                }
            }
        }
        return -1;
    }

    public A getElement(int index) {
        return (A) array[index];
    }

    public int size() {
        return array.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[" + array[0] + ", ");
        for (int i = 1; i < array.length - 1; i++) {
            stringBuilder.append(array[i] + ", ");
        }
        stringBuilder.append(array[array.length - 1] + "]");
        return stringBuilder.toString();
    }
}