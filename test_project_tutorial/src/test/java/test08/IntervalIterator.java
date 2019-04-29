package test08;

import java.util.Iterator;

/**
 * Написать итератор для перебора интервала от left до right с шагом 1
 */
public class IntervalIterator implements Iterator<Integer> {
	private int current;
	private final int max;
	
	public IntervalIterator(int left, int right) {
		if(left > right){
			throw new IllegalArgumentException("Неверные границы интервала");
		}
		this.current = left;
		this.max = right;
	}

	@Override
	public boolean hasNext() {
        return current < max;
	}

	@Override
	public Integer next() {
        return current++;
	}

	@Override
	public void remove() {
        throw new UnsupportedOperationException();
	}
}
