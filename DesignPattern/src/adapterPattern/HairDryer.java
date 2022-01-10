package adapterPattern;

public class HairDryer implements Electronic110V{

	@Override
	public void switchOn110V() {
		System.out.println("드라이기 on");
	}

}
