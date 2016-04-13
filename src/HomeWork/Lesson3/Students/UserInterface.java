package HomeWork.Lesson3.Students;

import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created by andrey on 13.04.16.
 */
class UserInterface {
    private static void addStudent(StudentList studentList) throws AddException{
        Scanner scan = new Scanner(System.in);
        System.out.print("Type in the name: ");
        String name = scan.nextLine();
        if (name.length() == 0) throw new AddException("Name field can't be empty!");
        System.out.print("Type in the surname: ");
        String surname = scan.nextLine();
        if (surname.length() == 0) throw new AddException("Surname field can't be empty!");
        System.out.print("Type in the birth date (YYYY MM DD): ");
        String date = scan.nextLine();
        String[] dateArr = date.split(" ");
        if (dateArr.length != 3) throw new AddException("Wrong date format!");
        int year = 0;
        int month = 0;
        int day = 0;
        try {
            year = Integer.parseInt(dateArr[0]);
            if ((year > 9999) || (year < 1900)) throw new AddException("");
            month = Integer.parseInt(dateArr[1]);
            if ((month > 12) || (month < 1)) throw new AddException("");
            day = Integer.parseInt(dateArr[2]);
            if ((day > 31) || (day < 1)) throw new AddException("");
        } catch (Exception e){
            throw new AddException("Wrong date format!");
        }
        studentList.add(new Student(name, surname, new GregorianCalendar(year, month - 1, day).getTime()));
    }

    private static void modeExecute(int mode, StudentList studentList){
        Scanner scan = new Scanner(System.in);
        switch (mode){
            case 1:
                try {
                    addStudent(studentList);
                    System.out.println("Added successfully");
                } catch (AddException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                System.out.print("State student index to delete: ");
                try {
                    studentList.deleteByIndex(scan.nextInt());
                    System.out.println("Deleted successfully");
                } catch (AddException e){
                    System.out.println(e.getMessage());
                } catch (Exception e){
                    System.out.println("Exception: Input mismatch!");
                    scan.nextLine();
                }
                break;
            case 3:
                studentList.showAllStudents();
                break;
            case 4:
                System.exit(0);
                break;
        }
    }

    static void start(StudentList studentList){
        System.out.println("1) Add student");
        System.out.println("2) Delete student");
        System.out.println("3) Show all students");
        System.out.println("4) Exit");
        int mode = 0;
        Scanner scan = new Scanner(System.in);
        while(true){
            while ( !((mode > 0) && (mode < 5)) ){
                System.out.print("Choose the mode: ");
                try {
                    mode = scan.nextInt();
                } catch (Exception e){
                    System.out.println("Exception: Input mismatch!");
                    scan.nextLine();
                }
            }
            modeExecute(mode, studentList);
            mode = 0;
        }
    }
}
