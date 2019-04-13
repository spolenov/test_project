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
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

}
