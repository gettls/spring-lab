package racing_game.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import racing_game.domain.Car;

public class RaceService {

	private List<Car> cars = new ArrayList<>();
	private List<String> winners = new ArrayList<>();
	private int NUMBER_OF_GAME;
	private int POSITION_OF_WINNER;

	public void start() {
		inputNumberOfGame();
		for (int i = 0; i < NUMBER_OF_GAME; i++) {
			race();
		}
		getWinners();
		announceWinners();
	}

	public void inputNumberOfGame() {
		Scanner sc = new Scanner(System.in);
		System.out.println("시도할 회수는 몇회인가요?");
		NUMBER_OF_GAME = sc.nextInt();
	}

	public void race() {
		System.out.println("실행 결과");
		for (Car car : cars) {
			moveForward(car);
			outputPosition(car);
			updateHighPosition(car);
		}
		System.out.println();
	}

	public void outputPosition(Car car) {
		System.out.print(car.getName() + " : ");
		for (int i = 0; i < car.getPosition(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	public void moveForward(Car car) {
		if (isAbletoMove()) {
			car.move();
		}
	}

	public void updateHighPosition(Car car) {
		if (POSITION_OF_WINNER < car.getPosition()) {
			POSITION_OF_WINNER = car.getPosition();
		}
	}
	
	private boolean isAbletoMove() {
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
	}
	
	public void announceWinners() {
		System.out.print("최종 우승자 : ");
		String str = winners.toString();
		str = str.replace("[", "").replace("]", "");
		System.out.println(str);
	}
	
	
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}
