package test12;

import java.util.Objects;

/**
 * Написать compareTo для сравнения age в порядке возрастания
 * Написать toString, HashCode и Equals
 */
public class ComparableUser implements Comparable<ComparableUser>{
	private int age;
	private String name;
	
	ComparableUser(int age, String name) {
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
        return "User[age=" + this.age + ", name'" + this.name + "']";
	}
	
	@Override
	public int compareTo(ComparableUser that) {
		if(this.age == that.age){
			return 0;
		}
		if(this.age < that.age){
			return -1;
		}
        return 1;
	}

	@Override
	public int hashCode() {
        return Objects.hash(age, name) ;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ComparableUser)){
			return false;
		}

        return this.age == ((ComparableUser)obj).age &&
				this.name.equals(((ComparableUser)obj).name);
	}
}
