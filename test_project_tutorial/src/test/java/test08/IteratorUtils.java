package test08;

public class IteratorUtils {
	public static Iterable<Integer> interval(int left, int right) {
		return new IntervalIterable(left, right);
	}
}
