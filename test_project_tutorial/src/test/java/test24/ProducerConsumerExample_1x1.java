package test24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

/**
 * Система с одним производителем и одним потребителем
 * вроде работает нормально...
 *
 */
class ProducerConsumerExample_1x1 {

    // Условно считаем, что если за 30 секунд работы
    // удалось произвести 10000 элементов, то зацикливания нет
    @Test
    void testProducerConsumer_1x1() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(30000), () ->{
            SingleElementBuffer<Integer> buffer = new SingleElementBuffer<>();
            Consumer consumer = new Consumer(buffer);
            Producer producer = new Producer(1, 2, buffer);
            new Thread(producer).start();
            new Thread(consumer).start();

            // ждем пока произведется более 10000 элементов
            do{
                sleep(100);
            } while (producer.getValue() < 10000);
        });
	}
}