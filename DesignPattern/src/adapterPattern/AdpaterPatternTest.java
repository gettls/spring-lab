package adapterPattern;

// 서로 다른 두 클래스를 연결해주는 역할을 한다
public class AdpaterPatternTest {
	
	public static void main(String[] args) {
		
		HairDryer hairDryer = new HairDryer();
		hairDryer.switchOn110V();
		
		AirConditional airConditional = new AirConditional();
		airConditional.connect220V();
		
		Electronic220V adapter = new Adapter110Vto220V(hairDryer);
		adapter.connect220V();
	}
}
