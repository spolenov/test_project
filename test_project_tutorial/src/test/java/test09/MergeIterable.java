package test09;

import java.util.Iterator;

public class MergeIterable implements Iterable<Integer> {
	
	Iterable<Integer> one;
	Iterable<Integer> two;

	public MergeIterable(Iterable<Integer> one, Iterable<Integer> two) {
		this.one = one;
		this.two = two;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new MergeIterator(one, two);
	}

}
