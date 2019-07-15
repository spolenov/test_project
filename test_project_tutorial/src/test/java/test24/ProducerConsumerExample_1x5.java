package test24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * Система с одним производителем и пятью потребителеми
 * через некоторое время уходит в DeadLock...
 * помогите автору исправить досадную ошибку в реализации буфера
 * Исправления нужно делать в классе SingleElementBuffer
 */

public class ProducerConsumerExample_1x5 {

    // Условно считаем, что если за 30 секунд работы
    // удалось произвести 10000 элементов, то зацикливания нет
    @Test
	void testProducerConsumer_1x5() {
        Assertions.assertTimeout(Duration.ofMillis(30000), () ->{
            SingleElementBuffer buffer = new SingleElementBuffer();
            Consumer consumer = new Consumer(buffer);
            Producer producer1 = new Producer(1, 1, buffer);
            new Thread(producer1).start();
            new Thread(new Producer(2, 1, buffer)).start();
            //new Thread(new Producer(3, 1, buffer)).start();
            //new Thread(new Producer(4, 1, buffer)).start();
            //new Thread(new Producer(5, 1, buffer)).start();
            new Thread(consumer).start();

            // ждем пока произведется более 10000 элементов
            do {
                Thread.sleep(100);
            } while (producer1.getValue() < 10000);
        });
	}
}