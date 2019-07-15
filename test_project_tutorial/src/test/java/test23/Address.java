package test23;

/**
 * 
 * Реализуйте метод toString у Address. 
 */

public class Address {
	
	private String country;
	private String city;
	private String street;
	private int houseNumber;

	Address(String country, String city, String street, int houseNumber) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	@Override
	public String toString() {
		String result = "Address[country=%s, city=%s, street=%s, houseNumber=%d]";
        return String.format(result, country, city, street, houseNumber);
	}

}