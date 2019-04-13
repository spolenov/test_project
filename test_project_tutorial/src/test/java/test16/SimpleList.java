package test16;

import java.util.Iterator;

public interface SimpleList<T> {

	public boolean add(T newElement);

	public void add(int index, T element);

	public T get(int index);

	public Iterator<T> iterator();

	public boolean contains(Object hasElement);

	public int size();

	public boolean isEmpty();

	public T set(int index, T newElement);

	public boolean remove(Object o);

	public T remove(int index);
}