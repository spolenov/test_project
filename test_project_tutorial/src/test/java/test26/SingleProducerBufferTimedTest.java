package test26;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

/**
 * Одинокий Производитель не ждет вечно
 *
 */
public class SingleProducerBufferTimedTest {
    final int timeout = 3000;
    @Test
    void testSingleProducerBufferTimed() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(timeout), () ->{
            SingleElementBufferTimed buffer = new SingleElementBufferTimed();
            Thread producer = new Thread(new ProducerTimed(1, 1000, buffer, 100));
            producer.start();

            await().atMost(timeout, TimeUnit.MILLISECONDS)
                    .until(() -> producer.getState() == Thread.State.TERMINATED);

            // Зависания не должно быть.
            // Работа программы завершается по time out!
        });
	}
}