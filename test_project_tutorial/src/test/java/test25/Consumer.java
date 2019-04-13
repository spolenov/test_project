package test25;

/**
 * Класс-потребитель (consumer), 
 * с максимальной скоростью изымает числа из буфера (buffer.get()), 
 * выводит в консоль, повторяет (while(true) {...}).
 *
 */
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
                System.out.println(" - " + elem + " " + Thread.currentThread().getName());
                Thread.sleep(period);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " stopped.");
                return;
            }
        }
    }
}