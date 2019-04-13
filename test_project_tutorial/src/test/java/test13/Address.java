package test13;

public class Address implements Comparable<Address>{
	private State state;
	private String city;
	private String street;
	private int houseNumber;

	public Address(State state, String city, String street, int houseNumber) {
		this.state = state;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}
	
	@Override
	public int hashCode() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}
	
	@Override
	public String toString() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public int compareTo(Address o) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}
}
