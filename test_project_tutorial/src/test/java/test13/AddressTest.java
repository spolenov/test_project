package test13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {
	
	private Address address1;
	private Address address2;
	
	@BeforeEach
	public void setUp() {
		address1 = new Address(State.NY, "New York", "17 Avenu", 1);
		address2 = new Address(State.NY, "New York", "17 Avenu", 1);
	}

	@Test
	void testToString() {
		assertEquals("Address[state=NY, city='New York', street='17 Avenu', houseNumber=1]", address1.toString());
	}

	@Test
	void testEquals() {
		assertEquals(address2, address1);
	}

	@Test
	void testHashCode() {
		assertFalse(address1.hashCode() != address2.hashCode());
	}

	@Test
	void testComprareStateLess() {
		Address address1 = new Address(State.CA, "New York", "17 Avenu", 1);
		Address address2 = new Address(State.NY, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) < 0);
	}

	@Test
	void testComprareEquals() {
		Address address1 = new Address(State.CA, "New York", "17 Avenu", 1);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) == 0);
	}

	@Test
	void testComprareStateMore() {
		Address address1 = new Address(State.FL, "New York", "17 Avenu", 1);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) > 0);
	}

	@Test
	void testComprareStateFirstNull() {
		Address address1 = new Address(null, "New York", "17 Avenu", 1);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) < 0);
	}

	@Test
	void testComprareStateSecondNull() {
		Address address1 = new Address(State.CA, "New York", "17 Avenu", 1);
		Address address2 = new Address(null, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) > 0);
	}

	@Test
	void testComprareStateBothNull() {
		Address address1 = new Address(null, "New York", "17 Avenu", 1);
		Address address2 = new Address(null, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) == 0);
	}

	@Test
	void testComprareCityLess() {
		Address address1 = new Address(State.CA, "Alabama", "17 Avenu", 1);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) < 0);
	}

	@Test
	void testComprareCityMore() {
		Address address1 = new Address(State.CA, "Washington", "17 Avenu", 1);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) > 0);
	}

	@Test
	void testComprareCityFirstNull() {
		Address address1 = new Address(State.CA, null, "17 Avenu", 1);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) < 0);
	}

	@Test
	void testComprareCitySecondNull() {
		Address address1 = new Address(State.CA, "New York", "17 Avenu", 1);
		Address address2 = new Address(State.CA, null,  "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) > 0);
	}

	@Test
	void testComprareCityBothNull() {
		Address address1 = new Address(State.CA, null, "17 Avenu", 1);
		Address address2 = new Address(State.CA, null, "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) == 0);
	}

	@Test
	void testComprareStreetLess() {
		Address address1 = new Address(State.CA, "New York", "10 Avenu", 1);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) < 0);
	}

	@Test
	void testComprareStreetMore() {
		Address address1 = new Address(State.CA, "New York", "27 Avenu", 1);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) > 0);
	}

	@Test
	void testComprareStreetFirstNull() {
		Address address1 = new Address(State.CA, "New York", null, 1);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) < 0);
	}

	@Test
	void testComprareStreetSecondNull() {
		Address address1 = new Address(State.CA, "New York", "17 Avenu", 1);
		Address address2 = new Address(State.CA, "New York", null, 1);
		assertTrue(address1.compareTo(address2) > 0);
	}

	@Test
	void testComprareStreetBothNull() {
		Address address1 = new Address(State.CA, "New York", null, 1);
		Address address2 = new Address(State.CA, "New York", null, 1);
		assertTrue(address1.compareTo(address2) == 0);
	}

	@Test
	void testComprareHouseNumberLess() {
		Address address1 = new Address(State.CA, "New York", "17 Avenu", 0);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) < 0);
	}

	@Test
	void testComprareHouseNumberMore() {
		Address address1 = new Address(State.CA, "New York", "17 Avenu", 5);
		Address address2 = new Address(State.CA, "New York", "17 Avenu", 1);
		assertTrue(address1.compareTo(address2) > 0);
	}
}
