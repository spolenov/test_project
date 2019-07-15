package test14;

import java.util.Comparator;

/**
 * Сравнить User'ов по именам в лексикографическом порядке,
 * причем null меньше любого символа.
 * Если имена совпадают, сравниваем по возрасту в порядвке возрастания
 */
public class UserNameNullComparator implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {
        return Comparator
				.comparing(User::getName, Comparator.nullsFirst(Comparator.naturalOrder()))
				.thenComparing(User::getAge)
				.compare(u1, u2);
	}
}
