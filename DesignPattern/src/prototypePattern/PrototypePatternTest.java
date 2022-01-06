package prototypePattern;

import java.util.List;

// 이미 존재하는 객체를 Prototype으로 만들어서
// 수정을 하기 위해서 사용한다
// DB에 다시 접근하는것 보다 이미 꺼내온 객체를 복사해 사용하는게 효율적이다.
public class PrototypePatternTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Employees emps = new Employees();
		emps.loadData();
		
		Employees emps1 = (Employees)emps.clone();
		Employees emps2 = (Employees)emps.clone();
		
		List<String> list1 = emps1.getEmplist();
		list1.add("KIM");
		List<String> list2 = emps2.getEmplist();
		list2.add("PARK");
		
		System.out.println("list1 : "+list1);
		System.out.println("list2 : "+list2);
	}

}
