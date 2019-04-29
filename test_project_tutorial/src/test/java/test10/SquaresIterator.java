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
	boolean returnedZero = false;

	public SquaresIterator(int left, int right) {
		this.left = left;
		this.right = right;
		this.current = 0;
	}

	@Override
	public boolean hasNext() {
        return getNextSquare() < right;
	}

	@Override
	public Integer next() {
		this.current = getNextSquare();

		if(this.current == 0){
			this.returnedZero = true;
		}
        return this.current;
	}

	private int getNextSquare(){
		int result = 0;
		int tmpSquare;

		if(left == 0 && current == 0 && !returnedZero){
			return 0;
		}
		if(left == 0 && current == 0){
			return 1;
		}

		do{
			result++;
			tmpSquare = result * result;
		} while (this.current == 0? tmpSquare < left: tmpSquare <= this.current);

		return tmpSquare;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
