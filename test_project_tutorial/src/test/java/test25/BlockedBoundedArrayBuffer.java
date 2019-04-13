package test25;

/**
 * Реализовать BlockedBoundedArrayBuffer
 * с ограниченным буфером (массивом из 10 элементов)
 * Дописать методы get и put
 *
 */
public class BlockedBoundedArrayBuffer {
    private Integer[] buffer;
    private int size; 
    private int current = -1;
    
    public BlockedBoundedArrayBuffer() {
		this(10);
	}

    public BlockedBoundedArrayBuffer(int size) {
    	this.size = size;
    	buffer = new Integer[size];
	}

    public int getSize() {
        return size;
    }

    public int getCurrent() {
        return current;
    }

    public synchronized void put(Integer newElem) throws InterruptedException {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }

    public synchronized Integer get() throws InterruptedException {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }
	
}