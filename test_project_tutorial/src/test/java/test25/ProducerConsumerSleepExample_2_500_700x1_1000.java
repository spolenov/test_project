package test25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProducerConsumerSleepExample_2_500_700x1_1000 {
    @Test
    void testProducerConsumerExample_2_500_700x1_1000() {
        Assertions.assertTimeout(Duration.ofMillis(30000), () ->{
            BlockedBoundedArrayBuffer buffer = new BlockedBoundedArrayBuffer();
            Producer producer = new Producer(1, 500, buffer);
            new Thread(producer, "Producer1").start();
            new Thread(new Producer(1, 700, buffer), "Producer2").start();
            new Thread(new Consumer(1000, buffer), "Consumer1").start();

            // ждем пока произведется более 20 элементов
            while (producer.getValue() < 20);

            // проверяем размер буфера
            assertTrue(buffer.getSize() == 10);
            // проверяем заполненость буфера
            assertTrue(buffer.getCurrent() > 8);
        });
	}
}