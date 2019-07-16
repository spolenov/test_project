package test24;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс-потребитель (consumer), 
 * с максимальной скоростью изымает числа из буфера (buffer.get()), 
 * выводит в консоль, повторяет (while(true) {...}).
 *
 */

@Slf4j
public class Consumer implements Runnable {
    private final SingleElementBuffer<Integer> buffer;

    public Consumer(SingleElementBuffer<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        log.info("Consumer {} has been started.", Thread.currentThread().getName());
        while (true) {
            try {
                int elem =  buffer.get();
                log.info(elem + " consumed");
            } catch (InterruptedException e) {
                log.error(Thread.currentThread().getName() + " stopped.");
                return;
            }
        }
    }
}