package HomeWork.Lesson7.Task3;

class MatrixException extends Exception {
    public MatrixException(String string) {
        super(string);
    }

    @Override
    public String getMessage() {
        return "Matrix exception: " + super.getMessage();
    }
}
