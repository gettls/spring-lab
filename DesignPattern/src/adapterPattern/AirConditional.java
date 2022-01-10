package adapterPattern;

public class AirConditional implements Electronic220V{
	@Override
	public void connect220V() {
		System.out.println("에어컨 on");
	}
}
