package racing_game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import racing_game.domain.Car;
import racing_game.service.RaceService;
import racing_game.service.RegisterService;

public class RacingGame {

	private RegisterService registerService = new RegisterService();
	private RaceService raceService = new RaceService();
	
	public void start() {
		registerService.register();
		raceService.setCars(registerService.getCars());
		raceService.start();
	}
}
