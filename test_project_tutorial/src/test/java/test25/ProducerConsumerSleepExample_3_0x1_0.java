package test25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
public class ProducerConsumerSleepExample_3_0x1_0 {
    @Test
    void testProducerConsumerExample_3_0x1_0() {
        Assertions.assertTimeout(Duration.ofMillis(30000), () ->{
            BlockedBoundedArrayBuffer buffer = new BlockedBoundedArrayBuffer();
            Producer producer = new Producer(1, 0, buffer);
            new Thread(producer, "Producer1").start();
            new Thread(new Consumer(0, buffer), "Consumer1").start();
            new Thread(new Consumer(0, buffer), "Consumer2").start();
            new Thread(new Consumer(0, buffer), "Consumer3").start();

            // ждем пока произведется более 10000 элементов
            while (producer.getValue() < 10000);

            // проверяем размер буфера
            assertTrue(buffer.getSize() == 10);
            // проверяем заполненость буфера
            assertTrue(buffer.getCurrent() < 3);
            System.out.println(buffer.getCurrent());
        });

	}
}