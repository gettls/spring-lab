package singlentonPattern;

public class Car {

	private static Car car = new Car();
	
	public static Car getInstance() {
		return car;
	}
}
