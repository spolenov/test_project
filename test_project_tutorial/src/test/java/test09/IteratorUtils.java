package test09;


public class IteratorUtils {
	public static Iterable<Integer> interval(int left, int right) {
		return new IntervalIterable(left, right);
	}

	public static Iterable<Integer> merge(Iterable<Integer> one, Iterable<Integer> two) {
		return new MergeIterable(one, two);
	}
}
