package test23;

import java.util.List;

/**
 * Реализуйте метод toString у CyclicEmployee, который "обрезает" вложенные поля,
 * которые могут привести к рекурсии.
 */
public class CyclicEmployee {
	private int age;
	private String name;
	private CyclicEmployee boss;
	private List<CyclicEmployee> subordinate;
	
	CyclicEmployee(int age, String name, CyclicEmployee boss, List<CyclicEmployee> subordinate) {
		this.age = age;
		this.name = name;
		this.boss = boss;
		this.subordinate = subordinate;
	}

	public void setBoss(CyclicEmployee boss) {
		this.boss = boss;
	}

	@Override
	public String toString() {
		return "CyclicEmployee[age=" + age +
				(name == null? "": ", name=" + name)  +
				(boss == null? "": ", boss=" + boss)  +
				(subordinate == null? "": ", subordinate=" + subordinate) + "]";
	}
}