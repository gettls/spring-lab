package bridgePattern.abstraction;

import bridgePattern.Implemetor.PlayGameAPI;

public abstract class GamePlayer {
	protected PlayGameAPI playGameAPI;

	public GamePlayer(PlayGameAPI playGameAPI) {
		this.playGameAPI = playGameAPI;
	}
	
	public abstract void play();
}
