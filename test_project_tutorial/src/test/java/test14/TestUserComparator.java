package test14;

import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserComparator {
	
	@Test
	void testComparatorUserAge() {
		SortedSet<User> set = new TreeSet<User>(new UserAgeComparator());
		set.add(new User(3, "B"));
		set.add(new User(1, "C"));
		set.add(new User(2, "A"));
		assertEquals("[User[age=1, name'C'], User[age=2, name'A'], User[age=3, name'B']]", set.toString());
	}

	@Test
	void testComparatorUserName() {
		SortedSet<User> set = new TreeSet<User>(new UserNameComparator());
		set.add(new User(3, "B"));
		set.add(new User(1, "C"));
		set.add(new User(2, "A"));
		assertEquals("[User[age=2, name'A'], User[age=3, name'B'], User[age=1, name'C']]", set.toString());
	}

}
