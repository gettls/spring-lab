package factoryPattern;

public class factoryPatternTest {

	public static void main(String[] args) {
		shapeFactory shapeFactory = new shapeFactory();
		
		Shape circle = shapeFactory.getShape("CIRCLE");
		circle.draw();
		
		Shape square = shapeFactory.getShape("SQUARE");
		square.draw();
		
		Shape rectangle= shapeFactory.getShape("RECTANGLE");
		rectangle.draw();
		
	}
}
