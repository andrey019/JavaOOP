package HomeWork.Lesson31.Developers;

/**
 * Created by andrey on 10.04.2016.
 */
class MiddleDeveloper extends Developer {
    public MiddleDeveloper(String name, double basicSalary, int experience) {
        super(name, basicSalary, experience);
    }

    @Override
    public double getSalary() {
        return (basicSalary * 2) + (experience > 0 ? basicSalary * experience * 0.1 : 0);
    }
}
