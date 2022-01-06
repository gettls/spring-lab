package abstractFactoryPattern;

import abstractFactoryPattern.factory.ClothesAbstractFactory;
import abstractFactoryPattern.factory.DressFactory;
import abstractFactoryPattern.factory.JeanFactory;
import abstractFactoryPattern.product.Clothes;
import abstractFactoryPattern.product.Dress;
import abstractFactoryPattern.product.Jean;

public class abstractFactoryPatternTest {

	public static void main(String[] args) {
		
		ClothesAbstractFactory factory1 = new JeanFactory();
		Jean jean = factory1.createJean();
		jean.name();
		
		ClothesAbstractFactory factory2 = new DressFactory();
		Dress dress = factory2.createDress();
		dress.name();
		
	}
	
}
