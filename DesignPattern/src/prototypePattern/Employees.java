package prototypePattern;

import java.util.ArrayList;
import java.util.List;

public class Employees implements Cloneable{

	private List<String> emplist;
	
	public Employees() {
		emplist = new ArrayList<>();
	}
	
	public List<String> getEmplist() {
		return emplist;
	}

	public Employees(List<String> list) {
		emplist = list;
	}
	
	public void loadData() {
		emplist.add("Ann");
		emplist.add("Tom");
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		List<String> tmp = new ArrayList<>();
		for(String str : emplist) {
			tmp.add(str);
		}
		return new Employees(tmp);
	}
}
