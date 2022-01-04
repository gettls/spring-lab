package racing_game.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import racing_game.domain.Car;

public class RegisterService {
	
	private List<Car> cars = new ArrayList<>();
	
	public void register() {
		inputCarNames();
	}
	
	public void validateName(String[] names) {
		try {
			for(String name : names) {
				if(name.length() > 5) {
					throw new IllegalArgumentException(); 
				}
				cars.add(new Car(name));
			}
		}catch(IllegalArgumentException e){
			System.out.println("[ERROR] 이름은 5자 이하만 가능합니다.");
			inputCarNames();
		}
	}
	
	public void inputCarNames() {
		System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
		Scanner sc = new Scanner(System.in);
		String carList = sc.next();
		String[] names = carList.split(","); 
		validateName(names);
	}
	
	public List<Car> getCars(){
		return cars;
	}
}
