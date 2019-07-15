package test20;

public class Node {
    private int value;
    private Node next;
    private boolean lock = false;
    private NodeException e = new NodeException();

    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    void lock() throws InterruptedException {
        if(this.lock){
            throw new InterruptedException("The node is locked, cannot lock once more.");
        }
        this.lock = true;
    }

    void unlock() throws InterruptedException {
        if(!this.lock){
            throw new InterruptedException("The node is unlocked, cannot unlock once more.");
        }
        this.lock = false;
    }

    public int getValue() throws InterruptedException {
        if(!this.lock){
            throw new InterruptedException("Must lock node to get a value");
        }
        return this.value;
    }

    void setValue(int value) throws InterruptedException {
        if(!this.lock){
            throw new InterruptedException("Must lock node to set a value");
        }
        this.value = value;
    }

    public Node getNext() throws InterruptedException {
        if(!this.lock){
            throw new InterruptedException("Must lock node to get next.");
        }
        return this.next;
    }

    public void setNext(Node next) throws InterruptedException {
        if(!this.lock){
            throw new InterruptedException("Must lock node to set next.");
        }
        this.next = next;
    }
    
    boolean isLock() {
        return this.lock;
	}
}