package test10;

import java.util.Iterator;

/**
 * Дописать SquaresIterator чтобы возвращал квадраты целых чисел
 * большие или равные left и меньшие right
 */

public class SquaresIterator implements Iterator<Integer> {
	int left;
	int current;
	int right;

	public SquaresIterator(int left, int right) {
		this.left = left;
		this.right = right;
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
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
