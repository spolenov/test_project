package test11;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Сделать адаптер к InputStream, чтобы его можно было использовать как итератор
 */
public class ISToIteratorAdapter implements Iterator<Integer> {

	private InputStream is;
	private int oneByte;

	private boolean getNext = true;

	ISToIteratorAdapter(InputStream is) {
		this.is = is;
	}

	@Override
	public boolean hasNext() {
		if(getNext){
			try{
				oneByte = getNextByte();
				getNext = false;
			} catch(IOException | IndexOutOfBoundsException e){
				return false;
			}
		}
		return true;
	}

	@Override
	public Integer next() {
		getNext = true;
		return oneByte;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	private byte getNextByte() throws IOException {
		byte[] bytes = new byte[1];
		int count = is.read(bytes, 0, 1);

		if(count <= 0){
			throw new IOException("Нет данных в InputStream.");
		}
		return bytes[0];
	}
}
