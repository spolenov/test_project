package test26;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * Одинокий Производитель не ждет вечно
 *
 */
public class SingleProducerBufferTimedTest {

    @Test
    void testSingleProducerBufferTimed() {
        Assertions.assertTimeout(Duration.ofMillis(3000), () ->{
            SingleElementBufferTimed buffer = new SingleElementBufferTimed();
            Thread producer = new Thread(new ProducerTimed(1, 1000, buffer, 100));
            producer.start();

            while(producer.getState() != Thread.State.TERMINATED);
            // Зависания не должно быть.
            // Работа программы завершается по time out!
        });
	}
}