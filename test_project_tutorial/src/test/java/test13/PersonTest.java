package test13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
	
	private Person person1;
	private Person person2;
	private Address[] address = {
			new Address(State.NY, "New York", "19 Avenu", 19),
			new Address(State.NY, "New York", "12 Avenu", 12),
			new Address(State.NY, "New York", "15 Avenu", 15)
	};

	@BeforeEach
	public void setUp() {
		person1 = new Person(18, "Ivan", address);
		person2 = new Person(18, "Ivan", address);
	}

	@Test
	public void toStringTest() {
		assertEquals(person1.toString() , "Person[age=18, name='Ivan', addresses=[" +
				"Address[state=NY, city='New York', street='19 Avenu', houseNumber=19], " +
				"Address[state=NY, city='New York', street='12 Avenu', houseNumber=12], " +
				"Address[state=NY, city='New York', street='15 Avenu', houseNumber=15]]]");
	}

	@Test
	void testEquals() {
		assertEquals(person2, person1);
	}

	@Test
	void testHashCode() {
		assertFalse(person1.hashCode() != person2.hashCode());
	}

	@Test
	void testComprareEquals() {
		Person person1 = new Person(18, "Ivan", address);
		Person person2 = new Person(18, "Ivan", address);
		assertTrue(person1.compareTo(person2) == 0);
	}

	@Test
	void testComprareAgeLess() {
		Person person1 = new Person(14, "Ivan", address);
		Person person2 = new Person(18, "Ivan", address);
		assertTrue(person1.compareTo(person2) < 0);
	}

	@Test
	void testComprareAgeMore() {
		Person person1 = new Person(24, "Ivan", address);
		Person person2 = new Person(18, "Ivan", address);
		assertTrue(person1.compareTo(person2) > 0);
	}

	@Test
	void testComprareNameLess() {
		Person person1 = new Person(18, "Artem", address);
		Person person2 = new Person(18, "Ivan", address);
		assertTrue(person1.compareTo(person2) < 0);
	}

	@Test
	void testComprareNameMore() {
		Person person1 = new Person(18, "Ivan", address);
		Person person2 = new Person(18, "Artem", address);
		assertTrue(person1.compareTo(person2) > 0);
	}

	@Test
	void testComprareNameFirstNull() {
		Person person1 = new Person(18, null, address);
		Person person2 = new Person(18, "Artem", address);
		assertTrue(person1.compareTo(person2) < 0);
	}

	@Test
	void testComprareNameSecondNull() {
		Person person1 = new Person(18, "Ivan", address);
		Person person2 = new Person(18, null, address);
		assertTrue(person1.compareTo(person2) > 0);
	}

	@Test
	void testComprareNameBothNull() {
		Person person1 = new Person(18, null, address);
		Person person2 = new Person(18, null, address);
		assertTrue(person1.compareTo(person2) == 0);
	}

	@Test
	void testComprareAddressZeroSize() {
		Address[] address1 = {};
		Address[] address2 = {};
		Person person1 = new Person(18, null, address1);
		Person person2 = new Person(18, null, address2);
		assertTrue(person1.compareTo(person2) == 0);
	}

	@Test
	void testComprareAddressZeroSizeFirst() {
		Address[] address1 = {};
		Address[] address2 = {
				new Address(State.NY, "New York", "19 Avenu", 19),
				new Address(State.NY, "New York", "12 Avenu", 12),
				new Address(State.NY, "New York", "15 Avenu", 15)
		};
		Person person1 = new Person(18, null, address1);
		Person person2 = new Person(18, null, address2);
		assertTrue(person1.compareTo(person2) < 0);
	}

	@Test
	void testComprareAddressZeroSizeSecond() {
		Address[] address1 = {
				new Address(State.NY, "New York", "19 Avenu", 19),
				new Address(State.NY, "New York", "12 Avenu", 12),
				new Address(State.NY, "New York", "15 Avenu", 15)
		};
		Address[] address2 = {};
		Person person1 = new Person(18, null, address1);
		Person person2 = new Person(18, null, address2);
		assertTrue(person1.compareTo(person2) > 0);
	}

	@Test
	void testComprareAddressLess() {
		Address[] address1 = {
				new Address(State.NY, "New York", "19 Avenu", 19),
				new Address(State.NY, "New York", "12 Avenu", 12)
		};
		Address[] address2 = {
				new Address(State.NY, "New York", "19 Avenu", 19),
				new Address(State.NY, "New York", "12 Avenu", 12),
				new Address(State.NY, "New York", "15 Avenu", 15)
		};
		Person person1 = new Person(18, "Ivan", address1);
		Person person2 = new Person(18, "Ivan", address2);
		assertTrue(person1.compareTo(person2) < 0);
	}

	@Test
	void testComprareAddressMore() {
		Address[] address1 = {
				new Address(State.NY, "New York", "19 Avenu", 19),
				new Address(State.NY, "New York", "12 Avenu", 12),
				new Address(State.NY, "New York", "15 Avenu", 15)
		};
		Address[] address2 = {
				new Address(State.NY, "New York", "19 Avenu", 19),
				new Address(State.NY, "New York", "12 Avenu", 12)
		};
		Person person1 = new Person(18, "Ivan", address1);
		Person person2 = new Person(18, "Ivan", address2);
		assertTrue(person1.compareTo(person2) > 0);
	}
}
