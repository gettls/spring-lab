package racing_game.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import racing_game.domain.Car;

public class RacingGame {

	private List<Car> cars = new ArrayList<>();
	private List<String> winners = new ArrayList<>();
	private int NUMBER_OF_GAME;
	private int POSITION_OF_WINNER;

	public RacingGame() {
	}

	public void start() {
		Scanner sc = new Scanner(System.in);
		registerCars();

		System.out.println("시도할 회수는 몇회인가요?");
		NUMBER_OF_GAME = sc.nextInt();

		for (int i = 0; i < NUMBER_OF_GAME; i++) {
			race();
		}

		getWinners();
	}

	public void registerCars() {
		System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
		String[] strArr = getCarNames();
		List<String> carsName = new ArrayList<>(Arrays.asList(strArr));
		registerAndValidateName(carsName);
	}

	public void registerAndValidateName(List<String> carsName) {
		try {
			carsName.forEach(s -> {
				if(s.length()>5) throw new IllegalArgumentException();
				cars.add(new Car(s));
			});
		} catch (IllegalArgumentException e) {
			System.out.println("[ERROR] 이름은 5자 이하만 가능합니다.");
			String[] strArr = getCarNames();
			List<String> names = new ArrayList<>(Arrays.asList(strArr));
			
			registerAndValidateName(names);
		}	
	}

	public String[] getCarNames() {
		Scanner sc = new Scanner(System.in);
		String carList = sc.next();
		return carList.split(",");
	}
	
	public void race() {
		System.out.println("실행 결과");
		for (Car car : cars) {
			if (isAbletoMove()) { car.move(); }
			if (POSITION_OF_WINNER < car.getPosition()) {
				POSITION_OF_WINNER = car.getPosition();
			}
			raceResult(car);
		}
		System.out.println();
	}

	public void raceResult(Car car) {
		System.out.print(car.getName() + " : ");
		for(int i=0;i<car.getPosition();i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	public boolean isAbletoMove() {
		int rand = (int) (Math.random() * 9) + 1;
		if (rand >= 4)
			return true;
		return false;
	}

	public void getWinners() {
		for (Car car : cars) {
			if (POSITION_OF_WINNER == car.getPosition()) {
				winners.add(car.getName());
			}
		}
		System.out.print("최종 우승자 : ");
		String str = winners.toString();
		str = str.replace("[", "").replace("]", "");
		System.out.println(str);
	}
}
