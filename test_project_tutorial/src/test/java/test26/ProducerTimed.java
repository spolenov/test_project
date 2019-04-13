package test26;

import java.util.concurrent.TimeoutException;

/**
 * Производитель для буфера
 *
 */
public class ProducerTimed implements Runnable {
    private int value;
    private final int period;
    private final SingleElementBufferTimed buffer;
    private final long timeout;

    public ProducerTimed(int startValue, int period, SingleElementBufferTimed buffer, long timeout) {
        this.buffer = buffer;
        this.period = period;
        this.value = startValue;
        this.timeout = timeout;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(value + " produced");
                buffer.put(value++, timeout);
                Thread.sleep(period);
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