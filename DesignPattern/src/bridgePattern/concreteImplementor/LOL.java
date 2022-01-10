package bridgePattern.concreteImplementor;

import bridgePattern.Implemetor.PlayGameAPI;

public class LOL implements PlayGameAPI{
	@Override
	public void playGame(String id, String password) {
		System.out.println("LOL Connect Complete [id : " + id + " password : "+password+"]");
	}
}
