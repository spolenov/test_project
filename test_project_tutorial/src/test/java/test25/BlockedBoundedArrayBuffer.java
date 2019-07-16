package test25;

import lombok.Getter;

/**
 * Реализовать BlockedBoundedArrayBuffer
 * с ограниченным буфером (массивом из 10 элементов)
 * Дописать методы get и put
 *
 */
public class BlockedBoundedArrayBuffer {
    @Getter
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
        while(current + 1 == buffer.length){
            wait();
        }
        buffer[++current] = newElem;
        notifyAll();
    }

    public synchronized Integer get() throws InterruptedException {
        Integer result;
        while (current + 1 == 0){
            wait();
        }
        result = buffer[current--];
        notifyAll();
        return result;
    }
}