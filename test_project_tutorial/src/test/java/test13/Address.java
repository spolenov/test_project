package test13;

import lombok.Getter;

import java.util.Comparator;
import java.util.Objects;

@Getter
public class Address implements Comparable<Address>{
	private State state;
	private String city;
	private String street;
	private int houseNumber;

	Address(State state, String city, String street, int houseNumber) {
		this.state = state;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}

        if(!(obj instanceof  Address)){
        	return false;
		}

        return  this.state.equals(((Address) obj).state) &&
				  this.city.equals(((Address) obj).city) &&
				  this.street.equals(((Address) obj).street) &&
				  this.houseNumber == ((Address)obj).houseNumber;
	}
	
	@Override
	public int hashCode() {
        return Objects.hash(this.state, this.city, this.street, this.houseNumber);
	}
	
	@Override
	public String toString() {
        return "Address[state=" + state + ", " +
				"city='" + city + "', " +
				"street='" + street + "', " +
				"houseNumber=" + houseNumber + "]";
	}

	@Override
	public int compareTo(Address o) {
		if(o == null){
			return 1;
		}
		if(this == o){
			return 0;
		}
        return getComparator().compare(this, o);
	}

	Comparator<Address> getComparator(){
		Comparator<Address> byState = Comparator
				.comparing(Address::getState, Comparator.nullsFirst(Comparator.naturalOrder()));
		Comparator<Address> byCity = Comparator
				.comparing(Address::getCity, Comparator.nullsFirst(Comparator.naturalOrder()));
		Comparator<Address> byStreet = Comparator
				.comparing(Address::getStreet, Comparator.nullsFirst(Comparator.naturalOrder()));
		Comparator<Address> byHouse = Comparator
				.comparing(Address::getHouseNumber);
		return byState
				.thenComparing(byCity)
				.thenComparing(byStreet)
				.thenComparing(byHouse);
	}
}
