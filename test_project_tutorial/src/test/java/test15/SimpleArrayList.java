package test15;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

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

		if(size == 0 && index == 0){
			return null;
		}

		return data[index];
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int current = 0;

			@Override
			public boolean hasNext() {
                return current < size;
			}

			@Override
			public E next() {
                return data[current++];
			}

			@Override
			public void remove() {
                SimpleArrayList.this.remove(current);
			}
		};
	}

	// *** *** *** CHECK *** *** ***
	@Override
	public boolean contains(Object element) {
		for(int i = 0; i < size; i++){
			E el = this.data[i];

			if(el == null && element == null){
				return true;
			} else{
				if(el != null && el.equals(element)){
					return true;
				}
			}
		}
        return false;
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
        for(int i = 0; i< size; i++){
        	if(this.data[i].equals(element)){
        		size--;
        		E[] newData = (E[]) new Object[size];

        		for(int j = 0; j < size + 1 && j != i ; j++){
					newData[j] = data[j];
				}
        		this.data = newData;
        		return true;
			}
		}
        return false;
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
        if(o == null){
        	return false;
		}
        if(!(o instanceof SimpleList)){
        	return false;
		}
        if(this.size != ((SimpleList) o).size()){
        	return false;
		}
        for(int i = 0; i < this.size; i++){
			if(!this.get(i).equals(((SimpleList)o).get(0))){
				return false;
			}
		}
        return true;
	}

	@Override
	public int hashCode() {
        return Objects.hashCode(data);
	}

	@Override
	public String toString() {
		if(this.data == null){
			return "null";
		}
		if(this.data.length == 0){
			return "[]";
		}
        return "[" + Arrays.stream(this.data)
				.filter(Objects::nonNull)
				.map(Object::toString)
				.collect(Collectors.joining(",")) + "]";
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