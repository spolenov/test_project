package test26;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * Одинокий Потребитель не ждет вечно
 *
 */
public class SingleConsumerBufferTimedTest {

    @Test
    void testSingleConsumerBufferTimed() {
        Assertions.assertTimeout(Duration.ofMillis(5000), () ->{
            SingleElementBufferTimed buffer = new SingleElementBufferTimed();
            Thread consumer = new Thread(new ConsumerTimed(buffer, 3000), "Consumer");
            consumer.start();

            while(consumer.getState() != Thread.State.TERMINATED);
            // Зависания не должно быть.
            // Работа программы завершается по time out!
        });
	}
}