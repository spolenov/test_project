package test09;

import java.util.Iterator;

public class IntervalIterator implements Iterator<Integer> {
	private int current;
	private final int max;
	
	public IntervalIterator(int left, int right) {
		this.current = left;
		this.max = right;
	}

	@Override
	public boolean hasNext() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public Integer next() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
