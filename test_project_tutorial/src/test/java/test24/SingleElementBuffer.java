package test24;

/**
 * BoundedBuffer (ограниченный буфер) на один элемент
 *
 */
public class SingleElementBuffer {
    private Integer elem = null;

    public synchronized void put(Integer newElem) throws InterruptedException {
        while (elem != null) {
            this.wait();
        }
        this.elem = newElem;
        this.notify();
    }

    public synchronized Integer get() throws InterruptedException {
        while (elem == null) {
            this.wait();
        }
        int result = elem;
        this.elem = null;
        this.notify();
        return result;
    }
}