package test20;

public class Node {
    private int value;
    private Node next;
    private boolean lock = false;
    private NodeException e = new NodeException();

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public void lock() throws InterruptedException {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }

    public void unlock() throws InterruptedException {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }

    public int getValue() throws InterruptedException {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }

    public void setValue(int value) throws InterruptedException {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }

    public Node getNext() throws InterruptedException {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }

    public void setNext(Node next) throws InterruptedException {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }
    
    public boolean isLock() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}
}