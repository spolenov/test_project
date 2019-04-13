package test08;

import java.util.Iterator;

/**
 * Написать итератор для перебора интервала от left до right с шагом 1
 */
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
