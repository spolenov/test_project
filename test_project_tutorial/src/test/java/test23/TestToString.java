package test23;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestToString {

	Address address1 = new Address("Canada", "Ottava", "17 avenu", 1);
	Address address2 = new Address("Russia", "Moscow", "35 arbat", 7);
	Address[] addresses = {address1, address2};
	
	Phone phone1 = new Phone(777, 1234567);
	Phone phone2 = new Phone(555, 7654321);
	Phone[] phones = {phone1, phone2};

	NestedEmployee nestedEmployee = new NestedEmployee(27, "Mike", addresses, phones);

	@Test
	void testAddressToString() {
		assertEquals("Address[country=Canada, city=Ottava, street=17 avenu, houseNumber=1]", address1.toString());
	}

	@Test
	void testPhoneToString() {
		assertEquals("Phone[(777) 123-45-67]", phone1.toString());
	}

	@Test
	void testNestedEmployee() {
		assertEquals("NestedEmployee[age=27, name=Mike, " +
				"addresses=[Address[country=Canada, city=Ottava, street=17 avenu, houseNumber=1], " +
				"Address[country=Russia, city=Moscow, street=35 arbat, houseNumber=7]], " +
				"phones=[Phone[(777) 123-45-67], Phone[(555) 765-43-21]]]", nestedEmployee.toString());
	}

	@Test
	void testCyclicEmployee() {
		CyclicEmployee jimmy = new CyclicEmployee(25, "Jimmy", null, null);
		CyclicEmployee ann = new CyclicEmployee(45, "Ann", null, null);
		List<CyclicEmployee> sub = Arrays.asList(jimmy, ann);
		CyclicEmployee mike = new CyclicEmployee(30, "Mike", ann, sub);
		assertEquals("CyclicEmployee[age=30, name=Mike, " +
				"boss=CyclicEmployee[age=45, name=Ann], " +
				"subordinate=CyclicEmployee[age=25, name=Jimmy], " +
					"CyclicEmployee[age=45, name=Ann]]]", mike.toString());
	}

	@Test
	void testRecursionEmployeeNullBoss() {
		RecursionEmployee jimmy = new RecursionEmployee(25, "Jimmy", null, null);
		assertEquals("RecursionEmployee[age=25, name=Jimmy, boss=null, subordinate=null]]", jimmy.toString());
	}

	@Test
	void testRecursionEmployeeCyclicBoss() {
		RecursionEmployee ann = new RecursionEmployee(45, "Ann", null, null);
		RecursionEmployee mike = new RecursionEmployee(30, "Mike", ann, null);
		ann.setBoss(mike);
		assertEquals("RecursionEmployee[age=30, name=Mike, boss=[...], subordinate=null]]", mike.toString());
	}

	@Test
	void testRecursionEmployee() {
		RecursionEmployee jimmy = new RecursionEmployee(25, "Jimmy", null, null);
		RecursionEmployee ann = new RecursionEmployee(45, "Ann", null, null);
		List<RecursionEmployee> sub = Arrays.asList(jimmy, ann);
		RecursionEmployee mike = new RecursionEmployee(30, "Mike", ann, sub);
		ann.setBoss(mike);
		assertEquals("RecursionEmployee[age=30, name=Mike, boss=[...] " +
				"boss=RecursionEmployee[age=45, name=Ann, boss[...]], " +
				"subordinate=RecursionEmployee[age=25, name=Jimmy, boss=null], " +
					"RecursionEmployee[age=45, name=Ann]]]", mike.toString());
	}
	
	@Test
	void testArraysToString() {
		Object[] a1 = new Object[2];
		a1[0] = 1;
		
		Object[] a2 = new Object[2];
		a2[0] = 1;
		a2[1] = a1;
		
		Object[] a3 = new Object[2];
		a3[0] = 1;
		a3[1] = a2;
		
		a1[1] = a3; // a3->a2->a1
		
		assertNotNull(Arrays.toString(a3)); // "[1, [Ljava.lang.Object;@1478a43]"
		// при deepToString у вложенных объектов вызывается toString. Причем, рекурсия заменяется многоточием
		assertEquals("[1, [1, [1, [...]]]]", Arrays.deepToString(a3));

	}
	
}
