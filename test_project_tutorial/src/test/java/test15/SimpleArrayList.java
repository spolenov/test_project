package test15;

import java.util.Iterator;

import static java.lang.Math.max;

/**
 * 
 * В классе SimpleArrayList реализовать методы:
 * 1) remove(Object value)
 * 2) iterator()
 * 3) toString()
 * 4) equals(Object other)
 * 5) hashCode()
 * 6) contains()
 * Они должны вести себя так же, как и соответствующие методы java.util.ArrayList.
 *  
 */
public class SimpleArrayList<E> implements SimpleList<E> {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private E[] data;
	private int size = 0;

	public SimpleArrayList() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	public SimpleArrayList(int initialCapacity) {
		this.data = (E[]) new Object[initialCapacity];
	}

	// *** *** *** ADD *** *** ***
	@Override
	public boolean add(E newElement) {
		ensureCapacity(size + 1);
		data[size] = newElement;
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		rangeCheck(index);
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = element;
		size++;
	}

	// *** *** *** READ *** *** ***
	@Override
	public E get(int index) {
		rangeCheck(index);
		return data[index];
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int current = 0;

			@Override
			public boolean hasNext() {
                // TODO реализовать метод
                throw new UnsupportedOperationException("to do implementation");
			}

			@Override
			public E next() {
                // TODO реализовать метод
                throw new UnsupportedOperationException("to do implementation");
			}

			@Override
			public void remove() {
                // TODO реализовать метод
                throw new UnsupportedOperationException("to do implementation");
			}
		};
	}

	// *** *** *** CHECK *** *** ***
	@Override
	public boolean contains(Object element) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	// *** *** *** MUTATE *** *** ***
	@Override
	public E set(int index, E newElement) {
		rangeCheck(index);
		E oldElement = data[index];
		data[index] = newElement;
		return oldElement;
	}

	// *** *** *** REMOVE *** *** ***
	@Override
	public boolean remove(Object element) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	private void shift(int index) {
		int numMoved = size - index - 1;
		System.arraycopy(data, index + 1, data, index, numMoved);
		data[--size] = null;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		E oldValue = data[index];
		shift(index);
		return oldValue;
	}

	// *** *** *** OBJECT METHODS *** *** ***
	@Override
	public boolean equals(Object o) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public int hashCode() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public String toString() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	// ---------- internals ----------
	private void rangeCheck(int index) {
		if (index < 0 || size < index) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	private void ensureCapacity(int minCapacity) {
		if (minCapacity > data.length) {
			int newCapacity = max(minCapacity, data.length + (data.length >> 1));
			E[] newData = (E[]) new Object[newCapacity];
			System.arraycopy(data, 0, newData, 0, data.length);
			this.data = newData;
		}
	}
}