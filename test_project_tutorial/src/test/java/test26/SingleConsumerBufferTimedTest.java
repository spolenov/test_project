package test26;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

/**
 * Одинокий Потребитель не ждет вечно
 *
 */

@Slf4j
public class SingleConsumerBufferTimedTest {

    @Test
    void testSingleConsumerBufferTimed() {
        final int timeout = 5000;

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(timeout), () ->{
            SingleElementBufferTimed buffer = new SingleElementBufferTimed();
            Thread consumer = new Thread(new ConsumerTimed(buffer, 3000), "Consumer");
            consumer.start();

            await().atMost(timeout, TimeUnit.MILLISECONDS)
                    .until(() -> {
                        log.info ("Consumer state is {}", consumer.getState());
                        return consumer.getState() == Thread.State.TERMINATED;
                    });

            // Зависания не должно быть.
            // Работа программы завершается по time out!
        });
	}
}