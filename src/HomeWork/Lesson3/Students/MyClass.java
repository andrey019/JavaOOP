package HomeWork.Lesson3.Students;

import java.util.GregorianCalendar;

class MyClass {
	public static void main(String[] args) {
		StudentList sl = new StudentList();
		
		sl.add(new Student("Seva", "Evgienko", new GregorianCalendar(1986, 12, 1).getTime()));
        sl.add(new Student("Vasya", "Pupkin", new GregorianCalendar(1970, 3, 28).getTime()));
        sl.add(new Student("Arnold", "Schwarzenegger", new GregorianCalendar(1956, 5, 4).getTime()));
        sl.add(new Student("Ronnie", "Coleman", new GregorianCalendar(1966, 8, 15).getTime()));

        UserInterface.start(sl);
	}
}
