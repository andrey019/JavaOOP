package HomeWork.Lesson3.Task4;

/**
 * Created by andrey on 11.04.16.
 */
class MilitaryBallet {
    private boolean militarySchool;
    private boolean balletSchool;

    public boolean isMilitarySchool() {
        return militarySchool;
    }

    public void setMilitarySchool(boolean militarySchool) throws DualityException {
        if (balletSchool) throw new DualityException();
        else this.militarySchool = militarySchool;
    }

    public boolean isBalletSchool() {
        return balletSchool;
    }

    public void setBalletSchool(boolean balletSchool) throws DualityException {
        if (militarySchool) throw new DualityException();
        else this.balletSchool = balletSchool;
    }

    public static void main(String[] args){
        MilitaryBallet test1 = new MilitaryBallet();

        try {
            test1.setBalletSchool(true);
            test1.setMilitarySchool(true);
        } catch (DualityException e){
            System.out.print(e.getMessage());
        }
    }
}
