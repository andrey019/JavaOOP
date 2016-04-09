interface Colored {
    static final int RED = 0;
    static final int BLUE = 1;
    static final int YELLOW = 2;
    int getColor();    
}

interface WithWeight {
	int getWeight();
}

class Hamburger implements WithWeight {
	public int getWeight() {
		return 200; // грамм
	}
}

class Toy implements Colored, WithWeight {
	public int getColor() {
		return RED;
	}
	
	public int getWeight() {
		return 50; // грамм
	}
}

class Car implements Colored, WithWeight {
	public String getName() {
		return "car";
	}

	public int getWeight() {
		return 0;
	}

	public int getColor() {
		return BLUE;
	}
}

class BMW extends Car {
	@Override
	public String getName() {
		return "BMW";
	}
	
	@Override
	public int getWeight() {
		return 2000;
	}

	public int getColor() {
		return YELLOW;
	}
}

abstract class Shape {
	public abstract int getS();
}

class Rectangle extends Shape implements Colored {

	int height, width;
	
	public Rectangle(int height, int width) {
		this.width = width;
		this.height = height;
	}
	
	public int getColor() {
		return BLUE;
	}

	public int getS() {
		return height * width;
	}
}

public class MyClass {
   public static void main(String[] args) {
	   WithWeight ww = new Hamburger();
	   System.out.println("Humburget weight = " + ww.getWeight());
	   
	   ww = new Toy();
	   System.out.println("Toy weight = " + ww.getWeight());
	   
	   System.out.println("Colors: ");
	   Colored[] coloredList = {new Toy(), new BMW(), new Rectangle(5, 7)};
	   for (Colored c : coloredList)
		   System.out.println("\t" + c.getColor());
	   
	   Car car = new BMW();
	   System.out.println("Car weight: " + car.getWeight());
	   
	   Colored c = (Colored)car;
	   System.out.println("Car color: " + c.getColor());
	   
	   Hamburger h = new Hamburger();
	   c = (Colored)h; // ClassCastException !!!
	   System.out.println("Humburger color: " + c.getColor());
   }
}