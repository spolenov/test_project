package test25;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс-потребитель (consumer), 
 * с максимальной скоростью изымает числа из буфера (buffer.get()), 
 * выводит в консоль, повторяет (while(true) {...}).
 *
 */

@Slf4j
public class Consumer implements Runnable {
    private final BlockedBoundedArrayBuffer buffer;
	private int period;

    public Consumer(int period, BlockedBoundedArrayBuffer buffer) {
        this.buffer = buffer;
        this.period = period;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int elem = buffer.get();
                log.info("{}: consumed {}", Thread.currentThread().getName(), elem);
                Thread.sleep(period);
            } catch (InterruptedException e) {
                log.error (Thread.currentThread().getName() + " stopped.", e);
                return;
            }
        }
    }
}