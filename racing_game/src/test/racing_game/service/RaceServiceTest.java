package test.racing_game.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import racing_game.domain.Car;
import racing_game.service.RaceService;
import racing_game.service.RegisterService;

class RaceServiceTest {

	private RaceService raceService = new RaceService();
	
	@Test
	void race() {
		
		List<Car> cars = new ArrayList<>();
		
		cars.add(new Car("toby"));
		cars.add(new Car("amy"));
		cars.add(new Car("john"));
		cars.add(new Car("moth"));
		cars.add(new Car("mcTo"));
		
		raceService.setCars(cars);
		
	}

}
