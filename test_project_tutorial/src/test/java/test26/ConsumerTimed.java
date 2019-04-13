package test26;

import java.util.concurrent.TimeoutException;

/**
 * Потребитель для буфера
 *
 */
public class ConsumerTimed implements Runnable {
    private final SingleElementBufferTimed buffer;
    private final long timeout;

    public ConsumerTimed(SingleElementBufferTimed buffer, long timeout) {
        this.buffer = buffer;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int elem = buffer.get(timeout);
                System.out.println(elem + " consumed");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " stopped.");
                return;
            } catch (TimeoutException e) {
                System.out.println(Thread.currentThread().getName() + " time out.");
                return;
            }
        }
    }
}