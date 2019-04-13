package test11;

import java.io.InputStream;
import java.util.Iterator;

/**
 * Сделать адаптер к InputStream, чтобы его можно было использовать как итератор
 */
public class ISToIteratorAdapter implements Iterator<Integer> {

	InputStream is;
	int oneByte;
	
	public ISToIteratorAdapter(InputStream is) {
		this.is = is;
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
