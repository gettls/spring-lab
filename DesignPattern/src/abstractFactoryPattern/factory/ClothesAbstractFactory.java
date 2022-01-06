package abstractFactoryPattern.factory;

import abstractFactoryPattern.product.Clothes;
import abstractFactoryPattern.product.Dress;
import abstractFactoryPattern.product.Jean;

public interface ClothesAbstractFactory {
	Jean createJean();
	Dress createDress();
}
