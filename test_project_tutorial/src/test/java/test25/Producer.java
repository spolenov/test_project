package test25;

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
    private final BlockedBoundedArrayBuffer buffer;

    public int getValue() {
        return value;
    }

    public Producer(int startValue, int period, BlockedBoundedArrayBuffer buffer) {
        this.buffer = buffer;
        this.period = period;
        this.value = startValue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                buffer.put(value++);
                log.info("{}: produced {}", Thread.currentThread().getName(), value);
                Thread.sleep(period);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " stopped.");
                return;
            }
        }
    }
}