package test16;

import java.util.Iterator;
import java.util.Objects;

/**
 * 
 * В классе SimpleLinkedList реализовать методы:
 * 1) remove(Object value)
 * 2) iterator()
 * 3) toString()
 * 4) equals(Object other)
 * 5) hashCode()
 * 6) contains()
 * Они должны вести себя так же, как и соответствующие методы java.util.LinkedList. 
 * 
 */
public class SimpleLinkedList<E> implements SimpleList<E> {
	private Node<E> first = null; // head
	private Node<E> last = null; // tail
	private int size = 0;

	// *** *** *** ADD *** *** ***
	public boolean add(E newElement) {
        final Node<E> tmp = last;
        final Node<E> newNode = new Node<E>(tmp, newElement, null);
        last = newNode;
        if (tmp == null) {
            first = newNode;
        } else {
            tmp.next = newNode;
        }
        size++;
        return true;
    }

	public void add(int index, E element) {
		checkIndex(index);

		if (index == size)
			linkLast(element);
		else
			linkBefore(element, node(index));
	}

	// *** *** *** READ *** *** ***
	public E get(int index) {
		checkIndex(index);
		return node(index).item;
	}

	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private Node<E> current = new Node<E>(null, null, SimpleLinkedList.this.first);

			@Override
			public boolean hasNext() {
                return current.next != null;
			}

			@Override
			public E next() {
				Node<E> newNext = current.next.next;
				current = new Node<E>(current, current.next.item, newNext);
                return current.item;
			}

			@Override
			public void remove() {
                Node<E> prev = current.prev;
                Node<E> next = current.next;

                Node<E> newNext = next == null? null: next.next;

                if(next == null){
                	current = prev;
				} else {
					current = new Node<E>(prev, next.item, newNext);
				}

  				size--;
			}

		};
	}

	// *** *** *** CHECK *** *** ***
	public boolean contains(Object hasElement) {
		return indexOf(hasElement) != -1;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// *** *** *** MUTATE *** *** ***
	public E set(int index, E newElement) {
		checkIndex(index);
		Node<E> foundNode = node(index);
		E oldVal = foundNode.item;
		foundNode.item = newElement;
		return oldVal;
	}

	// *** *** *** REMOVE *** *** ***
	public boolean remove(Object o) {
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()){
			if(iter.next().equals(o)){
				iter.remove();
				return true;
			}
		}
		return false;
	}

	public E remove(int index) {
		checkIndex(index);
		return unlink(node(index));
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
		Iterator iter = this.iterator();
		Iterator iterObj = ((SimpleList)o).iterator();

		while(iter.hasNext() && iterObj.hasNext()){
			if(!(iter.next().equals(iterObj.next()))){
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 31;

		Iterator<E> iter = this.iterator();
		while(iter.hasNext()){
			result += Objects.hashCode(iter.next());
		}
		return result;
	}

	@Override
	public String toString() {
		String result = "[";

		Iterator<E> iter = this.iterator();
		while(iter.hasNext()){
			result += iter.next().toString() + (iter.hasNext() ? "," : "");
		}

		result += "]";
		return result;
	}

	// ---------- internals ----------
	private void checkIndex(int index) {
		if (index < 0 || size < index) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	private Node<E> node(int index) {
		if (index < size / 2) {
			Node<E> tmp = first;
			for (int i = 0; i < index; i++) {
				tmp = tmp.next;
			}
			return tmp;
		} else {
			Node<E> x = last;
			for (int i = size - 1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	}

	private int indexOf(Object o) {
		int index = 0;

		Iterator<E> iter = this.iterator();
		while(iter.hasNext()){
			E next = iter.next();

			if(next == null){
				if(o == null){
					return index;
				}
			}else{
				if(next.equals(o)){
					return index;
				}
			}
			index++;
		}
		return -1;
	}

	private E unlink(Node<E> x) { // todo:
		// assert x != null;
		final E element = x.item;
		final Node<E> next = x.next;
		final Node<E> prev = x.prev;

		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			x.prev = null;
		}

		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
			x.next = null;
		}

		x.item = null;
		size--;
		return element;
	}

	private void linkLast(E e) { //todo
        final Node<E> tmp = last;
        final Node<E> newNode = new Node<E>(tmp, e, null);
        last = newNode;
        if (tmp == null) {
            first = newNode;
        } else {
            tmp.next = newNode;
        }
        size++;
    }

	private void linkBefore(E e, Node<E> succ) { //todo
        // assert succ != null;
        final Node<E> prev = succ.prev;
        final Node<E> newNode = new Node<E>(prev, e, succ);
        succ.prev = newNode;
        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
    }

	private static class Node<T> {
		private Node<T> prev;
		private T item;
		private Node<T> next;

		private Node(Node<T> prev, T item, Node<T> next) {
			this.prev = prev;
			this.item = item;
			this.next = next;
		}
	}
}