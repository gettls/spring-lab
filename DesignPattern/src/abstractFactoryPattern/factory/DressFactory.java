package abstractFactoryPattern.factory;

import abstractFactoryPattern.product.Clothes;
import abstractFactoryPattern.product.Dress;
import abstractFactoryPattern.product.Jean;

public class DressFactory implements ClothesAbstractFactory{

	@Override
	public Jean createJean() {
		return new Jean();
	}

	@Override
	public Dress createDress() {
		return new Dress();
	}
}
