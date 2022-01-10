package bridgePattern.concreteImplementor;

import bridgePattern.Implemetor.PlayGameAPI;

public class Overwatch implements PlayGameAPI{
	@Override
	public void playGame(String id, String password) {
		System.out.println("Overwatch Connect Complete [id : " + id + " password : "+password+"]");
	}
}
