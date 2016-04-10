package HomeWork.Lesson31.Developers;

import java.util.Random;

class Main {
    static Developer[] randomInit(int amount){
        Developer[] list1 = new Developer[amount];
        Random randDev = new Random();
        Random randExp = new Random();
        int countName = 0;

        for (int i = 0; i < amount; i++){
            countName++;
            switch (randDev.nextInt(4)){
                case 0: list1[i] = new JuniorDeveloper("junior" + countName, 500, randExp.nextInt(11));
                        break;
                case 1: list1[i] = new MiddleDeveloper("middle" + countName, 500, randExp.nextInt(11));
                    break;
                case 2: list1[i] = new SeniorDeveloper("senior" + countName, 500, randExp.nextInt(11));
                    break;
                case 3: list1[i] = new TeamLeadDeveloper("lead" + countName, 500, randExp.nextInt(11));
                    break;
            }
        }
        return list1;
    }

    static void showDevList(Developer[] list){
        StringBuilder sb;

        for (Developer d : list) {         // show all list members
            sb = new StringBuilder() // !!!
                    .append(d.getName())
                    .append(": ")
                    .append(d.getBasicSalary())
                    .append(" -> ")
                    .append(d.getSalary());

            System.out.println(sb.toString());
        }
        System.out.println();
    }

    static void showSenior1500(Developer[] list){
        StringBuilder sb;

        for (Developer d : list) {
            if ((d instanceof SeniorDeveloper) && (d.getSalary() > 1500)) {  // show seniors with 1500+ salary
                sb = new StringBuilder() // !!!
                        .append(d.getName())
                        .append(": ")
                        .append(d.getBasicSalary())
                        .append(" -> ")
                        .append(d.getSalary());

                System.out.println(sb.toString());
            }
        }
        System.out.println();
    }

    static void separateAndShow(Developer[] list){
        int amount = list.length;
        int countJun = 0;
        int countSen = 0;
        int countLead = 0;
        Developer[] listJunTemp = new Developer[amount];
        Developer[] listSenTemp = new Developer[amount];
        Developer[] listLeadTemp = new Developer[amount];

        for (Developer d : list){
            if (d instanceof JuniorDeveloper){
                listJunTemp[countJun] = d;
                countJun++;
            } else if (d instanceof SeniorDeveloper){
                listSenTemp[countSen] = d;
                countSen++;
            } else if (d instanceof TeamLeadDeveloper){
                listLeadTemp[countLead] = d;
                countLead++;
            }
        }
        Developer[] listJun = new Developer[countJun];
        System.arraycopy(listJunTemp, 0, listJun, 0, countJun);
        Developer[] listSen = new Developer[countSen];
        System.arraycopy(listSenTemp, 0, listSen, 0, countSen);
        Developer[] listLead = new Developer[countLead];
        System.arraycopy(listLeadTemp, 0, listLead, 0, countLead);

        showDevList(listJun);
        showDevList(listSen);
        showDevList(listLead);
    }

	public static void main(String[] args) {
        int amount = 10;
        Developer[] listAll = randomInit(amount);

        System.out.println("All developers:");
        showDevList(listAll);
        System.out.println("Senior developers with 1500+ salary:");
        showSenior1500(listAll);
        System.out.println("Separate lists:");
        separateAndShow(listAll);
	}
}
