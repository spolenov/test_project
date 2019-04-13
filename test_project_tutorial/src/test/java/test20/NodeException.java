package test20;

/**
 * Реализация своего exception наследника InterruptedException,
 * который содержит колчиество брошенных исключений,
 * и обнуляет счетчик в нужный момент
 */
public class NodeException extends InterruptedException {
	
	private static final long serialVersionUID = 6477535963872946745L;
	
	private int count = 0;

	public void inc() throws NodeException {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}
	
	public void reset() {
		count = 0;
	}
	
}
