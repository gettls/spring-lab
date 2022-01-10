package adapterPattern;

public class Adapter110Vto220V implements Electronic220V{

	private Electronic110V electornic110v;
	
	public Adapter110Vto220V(Electronic110V electornic110v) {
		this.electornic110v = electornic110v; 
	}

	@Override
	public void connect220V() {
		electornic110v.switchOn110V();
	}
}
