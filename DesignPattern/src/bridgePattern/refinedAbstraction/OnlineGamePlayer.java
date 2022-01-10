package bridgePattern.refinedAbstraction;

import bridgePattern.Implemetor.PlayGameAPI;
import bridgePattern.abstraction.GamePlayer;

public class OnlineGamePlayer extends GamePlayer{
	private final String id;
	private final String password;
	
	public OnlineGamePlayer(PlayGameAPI playGameAPI, final String id, final String password) {
		super(playGameAPI);
		this.id = id;
		this.password = password;
	}

	@Override
	public void play() {
		playGameAPI.playGame(id, password);
	}

}
