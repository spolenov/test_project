package test25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 3 Producer'а очень быстро добавляют элементы в буфер, 
 * а Comsumer пытается немедленно забрать эти элементы,
 */
public class ProducerConsumerExample_1_0x3_0 {

    // Условно считаем, что если за 30 секунд работы
    // удалось произвести 10000 элементов, то зацикливания нет
    @Test
    void testProducerConsumerExample_1_0x3_0() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(30000), () ->{
            BlockedBoundedArrayBuffer buffer = new BlockedBoundedArrayBuffer();
            Producer producer = new Producer(1, 0, buffer);
            new Thread(producer, "Producer1").start();
            new Thread(new Producer(100, 0, buffer), "Producer2").start();
            new Thread(new Producer(10000, 0, buffer), "Producer3").start();
            new Thread(new Consumer(0, buffer), "Consumer1").start();

            // ждем пока произведется более 10000 элементов
            await().until(() -> producer.getValue() >= 10000);

            // проверяем размер буфера
            assertTrue(buffer.getSize() == 10);
            // проверяем заполненость буфера

            assertThat(Arrays.asList(buffer.getBuffer()), hasSize(greaterThan(5)));
        });
	}
}