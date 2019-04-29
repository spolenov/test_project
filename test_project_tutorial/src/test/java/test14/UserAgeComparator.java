package test14;

import java.util.Comparator;

/**
 * Написать Comparator для сравнения возраста User'а в порядке убывания
 */
public class UserAgeComparator implements Comparator<User>{

	@Override
	public int compare(User user0, User user1) {
        return Integer.compare(user0.getAge(), user1.getAge());
	}
	
}
