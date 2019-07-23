package test23;

import java.util.List;

/**
 * Реализуйте метод toString у RecursionEmployee, 
 * который "разворачивает" вложенные поля, пока не встретит цикл.
 * Пример: проанализируйте, как реализован Arrays.deepToString(...)
 */
public class RecursionEmployee {
	private int age;
	private String name;
	private RecursionEmployee boss;
	private List<RecursionEmployee> subordinate;

	public RecursionEmployee(int age, String name, RecursionEmployee boss, List<RecursionEmployee> subordinate) {
		this.age = age;
		this.name = name;
		this.boss = boss;
		this.setSubordinate(subordinate);
	}

	public void setBoss(RecursionEmployee boss) {
		this.boss = boss;
	}

    @Override
    public String toString() {
		return toString(2);
    }

    private String toString(int counter){
		if(counter < 2){
			return "[...]";
		}{
			return "RecursionEmployee[" + "age=" + age + ", name=" + name + ", " +
					"boss=" +
						(boss == null? "null" : boss.toString(counter - 1)) + ", " +
					"subordinate=" +
						(subordinate == null? "null": subordinate.toString()) + "]";
		}
	}

	public void setSubordinate(List<RecursionEmployee> subordinate) {
		this.subordinate = subordinate;
	}

	public List<RecursionEmployee> getSubordinate() {
		return subordinate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RecursionEmployee getBoss() {
		return boss;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RecursionEmployee that = (RecursionEmployee) o;

		if (age != that.age) return false;
		return name != null ? name.equals(that.name) : that.name == null;
	}

	@Override
	public int hashCode() {
		int result = age;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}