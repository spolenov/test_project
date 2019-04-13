package test09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Получает два Iterable, возвращающие итераторы на сортированные последовательности,
 * и сливает в Iterable возвращающий итератор на сортированную
 * последовательность образованную в результате слияния
 */
public class MergeIterator implements Iterator<Integer> {
	
	Iterator<Integer> one;
	Iterator<Integer> two;
	
	public MergeIterator(Iterable<Integer> one, Iterable<Integer> two) {
		this.one = one.iterator();
		this.two = two.iterator();
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
