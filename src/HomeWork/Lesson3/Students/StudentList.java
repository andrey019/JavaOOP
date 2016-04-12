package HomeWork.Lesson3.Students;

import java.util.Date;

class StudentList {
	private Student[] list = new Student[100];
	private int p = 0;
	
	public void add(Student s) {
		if (p-1 == list.length){            // защита от выхода за пределы массива
			Student[] temparr = new Student[list.length * 2];
			System.arraycopy(list, 0, temparr, 0, list.length);
			list = temparr;
		}
		list[p++] = s;
	}

    public boolean deleteByIndex(int index) throws Exception{                // удаление
        Student[] temparr = new Student[list.length - 1];
        if (index == (p - 1)){
            System.arraycopy(list, 0, temparr, 0, index);
            list = temparr;
            p--;
            return true;
        } else if (index < (p - 1)){
            System.arraycopy(list, 0, temparr, 0, index);
            System.arraycopy(list, (index + 1), temparr, index, (list.length - index - 1));
            list = temparr;
            p--;
            return true;
        } else if (index == 0){
            System.arraycopy(list, (index + 1), temparr, (index + 1), (list.length - index - 2));
            list = temparr;
            p--;
        } else if (index >= p) throw new AddException("Out of boundaries!");
        return false;
    }
	
	public Student get(int n) {
		return list[n];
	}
	
	public int findName(String name) {
		for (int i = 0; i < p; i++) {
			if (list[i].getName().equalsIgnoreCase(name))
				return i;
		}
		return -1;
	}

	public int findSurname(String surname) {        // поиск по фамилии
		for (int i = 0; i < p; i++) {
			if (list[i].getSurname().equalsIgnoreCase(surname))
				return i;
		}
		return -1;
	}

	public int findBirth(Date birth){       // поиск по дате
        for (int i = 0; i < p; i++){
            if (list[i].getBirth() == birth){
                return i;
            }
        }
        return -1;
    }

	public void showAllStudents(){
		for (int i = 0; i < p; i++){
			System.out.println(i + ") " + list[i].getName() + " " + list[i].getSurname() + " " + list[i].getBirth());
		}
	}
}
