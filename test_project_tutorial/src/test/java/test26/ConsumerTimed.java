package test26;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeoutException;

/**
 * Потребитель для буфера
 *
 */

@Slf4j
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
                log.info(elem + " consumed");
            } catch (InterruptedException e) {
                log.info(Thread.currentThread().getName() + " stopped.", e);
                return;
            } catch (TimeoutException e) {
                log.info(Thread.currentThread().getName() + " has timed out.", e);
                return;
            }
        }
    }
}