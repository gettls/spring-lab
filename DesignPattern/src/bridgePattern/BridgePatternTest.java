package bridgePattern;

import bridgePattern.Implemetor.PlayGameAPI;
import bridgePattern.abstraction.GamePlayer;
import bridgePattern.concreteImplementor.LOL;
import bridgePattern.concreteImplementor.Overwatch;
import bridgePattern.refinedAbstraction.OnlineGamePlayer;
// 구현부에서 추상층을 분리해서 
// 각자 독립적으로 변형할 수 있게 하는 패턴
public class BridgePatternTest {

	public static void main(String[] args) {

		PlayGameAPI LOLPlayer = new LOL();
		String id = "LOL";
		String password = "1234";
		
		GamePlayer gamePlayer1 = new OnlineGamePlayer(LOLPlayer, id, password);
		gamePlayer1.play();
		
		
		PlayGameAPI OverwatchPlayer = new Overwatch();
		id = "Overwatch";
		password = "1234";
		
		GamePlayer gamePlayer2 = new OnlineGamePlayer(OverwatchPlayer, id, password);
		gamePlayer2.play();
		
	}

}
