package test14;

import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserNameNullComparator {

	@Test
	void testUserNameNullComaparatorComplex() {
		SortedSet<User> set = new TreeSet<User>(new UserNameNullComparator());
		set.add(new User(3, "B"));
		set.add(new User(1, "C"));
		set.add(new User(2, "A"));
		set.add(new User(2, null));
		set.add(new User(7, null));
		set.add(new User(5, "D"));
		set.add(new User(4, "E"));
		set.add(new User(8, null));
		assertEquals("[User[age=2, name'null'], User[age=7, name'null'], User[age=8, name'null'], User[age=2, name'A'], User[age=3, name'B']" +
				", User[age=1, name'C'], User[age=5, name'D'], User[age=4, name'E']]", set.toString());
	}

	@Test
	void testUserNameNullComaparatorEmpty() {
		SortedSet<User> set = new TreeSet<User>(new UserNameNullComparator());
		assertEquals("[]", set.toString());
	}

	@Test
	void testUserNameNullComaparatorOneNull() {
		SortedSet<User> set = new TreeSet<User>(new UserNameNullComparator());
		set.add(new User(5, null));
		assertEquals("[User[age=5, name'null']]", set.toString());
	}

	@Test
	void testUserNameNullComaparatorFourNull() {
		SortedSet<User> set = new TreeSet<User>(new UserNameNullComparator());
		set.add(new User(5, null));
		set.add(new User(4, null));
		set.add(new User(7, null));
		set.add(new User(0, null));
		assertEquals("[User[age=0, name'null'], User[age=4, name'null'], User[age=5, name'null'], User[age=7, name'null']]", set.toString());
	}

	@Test
	void testUserNameNullComaparatorStringAndNull() {
		SortedSet<User> set = new TreeSet<User>(new UserNameNullComparator());
		set.add(new User(5, "A"));
		set.add(new User(10, "A"));
 		set.add(new User(4, null));
		set.add(new User(7, "B"));
		set.add(new User(11, ""));
		set.add(new User(0, null));
		set.add(new User(0, "B"));
		set.add(new User(1, "A"));
		assertEquals("[User[age=0, name'null'], User[age=4, name'null'], User[age=11, name'']," +
				" User[age=1, name'A'], User[age=5, name'A'], User[age=10, name'A']," +
				" User[age=0, name'B'], User[age=7, name'B']]", set.toString());
	}

	@Test
	void testUserNameNullComaparatorStringDuplicate() {
		SortedSet<User> set = new TreeSet<User>(new UserNameNullComparator());
		set.add(new User(5, "A"));
		set.add(new User(5, "A"));
 		set.add(new User(5, "A"));
		assertEquals("[User[age=5, name'A']]", set.toString());
	}

	@Test
	void testUserNameNullComaparatorNullDuplicate() {
		SortedSet<User> set = new TreeSet<User>(new UserNameNullComparator());
		set.add(new User(5, null));
		set.add(new User(5, null));
		set.add(new User(5, null));
		assertEquals("[User[age=5, name'null']]", set.toString());
	}

	@Test
	void testUserNameNullComaparatorStringEmpty() {
		SortedSet<User> set = new TreeSet<User>(new UserNameNullComparator());
		set.add(new User(5, ""));
		set.add(new User(5, ""));
		set.add(new User(5, ""));
		assertEquals("[User[age=5, name'']]", set.toString());
	}
}
