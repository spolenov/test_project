package test25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Producer пытается добавить в буфер элемент каждую секуднду, 
 * а 3 Comsumer'а пытаются забирать эти элементы,
 * возникают перебои с прозводством
 * 
 */
public class ProducerConsumerExample_1_1000x1_3000 {
    @Test
    void testProducerConsumerExample_1_1000x1_3000() {
        final int timeout= 30000;

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(timeout), () ->{
            BlockedBoundedArrayBuffer buffer = new BlockedBoundedArrayBuffer();
            Producer producer = new Producer(1, 1000, buffer);
            new Thread(producer, "Producer1").start();
            new Thread(new Consumer(1000, buffer), "Consumer1").start();
            new Thread(new Consumer(1500, buffer), "Consumer1").start();
            new Thread(new Consumer(2000, buffer), "Consumer1").start();

            // ждем пока произведется более 20 элементов
            await().atMost(timeout, TimeUnit.MILLISECONDS)
                    .until(() -> producer.getValue() >= 20);

            // проверяем размер буфера
            assertTrue(buffer.getSize() == 10);
            // проверяем заполненость буфера
            assertTrue(buffer.getCurrent() < 3);
        });
	}
}