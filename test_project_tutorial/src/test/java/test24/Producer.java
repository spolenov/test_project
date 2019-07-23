package test24;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс-производитель (producer), 
 * производит последовательно числа начиная со startValue 
 * (startValue, startValue+1, startValue+2, startValue+3, ...) 
 * и помещает их в буфер (buffer.put(...)), спит period миллисекунд, 
 * повторяет (while(true) {...})
 *
 */
@Slf4j
public class Producer implements Runnable {
    private int value;
    private final int period;
    private final SingleElementBuffer<Integer> buffer;

    public Producer(int startValue, int period, SingleElementBuffer<Integer> buffer) {
        this.buffer = buffer;
        this.period = period;
        this.value = startValue;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void run() {
        while (true) {
            try {
                log.info(value + " produced");
                buffer.put(value++);

                Thread.sleep(period);

                //await().atLeast(period, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " stopped.");
                return;
            }
        }
    }
}