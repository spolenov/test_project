package test12;

/**
 * Написать compareTo для сравнения age в порядке возрастания
 * Написать toString, HashCode и Equals
 */
public class ComparableUser implements Comparable<ComparableUser>{
	private int age;
	private String name;
	
	public ComparableUser(int age, String name) {
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
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}
	
	@Override
	public int compareTo(ComparableUser that) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public int hashCode() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public boolean equals(Object obj) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

}
