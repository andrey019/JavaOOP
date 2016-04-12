package HomeWork.Lesson3.Students;

import java.util.GregorianCalendar;
import java.util.Scanner;

class MyClass {
    static void addStudent(StudentList studentList) throws AddException{
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

	public static void main(String[] args) {
		StudentList sl = new StudentList();
		
		sl.add(new Student("Seva", "Evgienko", new GregorianCalendar(1986, 12, 1).getTime()));
        sl.add(new Student("Vasya", "Pupkin", new GregorianCalendar(1970, 3, 28).getTime()));
        sl.add(new Student("Arnold", "Schwarzenegger", new GregorianCalendar(1956, 5, 4).getTime()));
        sl.add(new Student("Ronnie", "Coleman", new GregorianCalendar(1966, 8, 15).getTime()));

        System.out.println("1) Add student");
        System.out.println("2) Delete student");
        System.out.println("3) Show all students");
        System.out.println("4) Exit");
        int mode = 0;
        Scanner scan = new Scanner(System.in);
        while(mode != 4){
            while ( !((mode > 0) && (mode < 5)) ){
                System.out.print("Choose the mode: ");
                try {
                    mode = scan.nextInt();
                } catch (Exception e){
                    System.out.println("Exception: Input mismatch!");
                    scan.nextLine();
                }
            }
            switch (mode){
                case 1:
                    try {
                        addStudent(sl);
                    } catch (AddException e) {
                        System.out.println(e.getMessage());
                    }
                    mode = 0;
                    break;
                case 2:
                    System.out.print("State student index to delete: ");
                    try {
                        sl.deleteByIndex(scan.nextInt());
                    } catch (AddException e){
                        System.out.println(e.getMessage());
                    } catch (Exception e){
                        System.out.println("Exception: Input mismatch!");
                        scan.nextLine();
                    }
                    mode = 0;
                    break;
                case 3:
                    mode = 0;
                    sl.showAllStudents();
                    break;
            }
        }

	}
}
