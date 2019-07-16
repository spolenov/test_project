package test26;

import java.util.Date;
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
		long startMillis = new Date().getTime();
		long millis;

		while (elem != null ) {
			wait(timeout);

			millis = new Date().getTime() - startMillis;

			if(millis >= timeout){
				throw new TimeoutException("Adding element timeout of " + timeout + " " +
						"millis has been exceeded.");
			}
		}
		this.elem = newElem;
		this.notifyAll();
	}

	public synchronized Integer get(long timeout) throws InterruptedException, TimeoutException {
		long startMillis = new Date().getTime();
		long millis;

		while (elem == null) {
			wait(timeout);

			millis = new Date().getTime() - startMillis;

			if(millis >= timeout){
				throw new TimeoutException("Getting element timeout of " + timeout + " " +
						"millis has been exceeded.");
			}


		}
		int result = this.elem;
		this.elem = null;
		this.notifyAll();
		return result;
	}
}