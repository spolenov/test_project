package test24;

/**
 * Класс-потребитель (consumer), 
 * с максимальной скоростью изымает числа из буфера (buffer.get()), 
 * выводит в консоль, повторяет (while(true) {...}).
 *
 */
public class Consumer implements Runnable {
    private final SingleElementBuffer buffer;

    public Consumer(SingleElementBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int elem = buffer.get();
                System.out.println(elem + " consumed");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " stopped.");
                return;
            }
        }
    }
}