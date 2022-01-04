package test.racing_game.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import racing_game.domain.Car;
import racing_game.service.RegisterService;

class RegisterServiceTest {

	private RegisterService registerService = new RegisterService();
	
	@Test
	void testValidateName() {
		String[] names = {"aaaaaa,bb,cccccc"};
		registerService.validateName(names);
		List<Car> cars = registerService.getCars();
		for(Car car : cars) {
			System.out.println(car.getName());
		}
	}

	@Test
	void testInputCarNames() {
		registerService.inputCarNames();
		
		List<Car> cars = registerService.getCars();
		
		for(Car car : cars) {
			System.out.println(car.getName());
		}
	}

}
