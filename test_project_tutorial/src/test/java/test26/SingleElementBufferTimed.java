package test26;

import java.util.concurrent.TimeoutException;

/**
 * Задание: Вам дана "заготовка" класса SingleElementBufferTimed, 
 * который должен в случае превышения времени ожидания в wait(long) 
 * выбросить исключение TimeoutException. 
 */
public class SingleElementBufferTimed {
	private Integer elem = null;

	public synchronized void put(Integer newElem, long timeout)
			throws InterruptedException, TimeoutException {
		while (elem != null ) {
			wait();
		}
		this.elem = newElem;
		this.notifyAll();
	}

	public synchronized Integer get(long timeout) throws InterruptedException, TimeoutException {
		while (elem == null) {
			wait();
		}
		int result = this.elem;
		this.elem = null;
		this.notifyAll();
		return result;
	}
}