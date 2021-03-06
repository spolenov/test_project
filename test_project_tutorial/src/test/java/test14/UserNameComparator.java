package test14;

import java.util.Comparator;

/**
 * Написать Comparator для сравнения имени User'а в лексикографическом порядке
 */
public class UserNameComparator implements Comparator<User> {

	@Override
	public int compare(User user0, User user1) {
        return user0.getName().compareTo(user1.getName());
	}
	
}
