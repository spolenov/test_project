package test26;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * Нетерпеливый потребитель не дожидается медленного производителя и умирает, 
 * после - умирает производитель
 *
 */
public class SlowProducerExampleTest {

    @Test
    void testSlowProducerExample() {
        Assertions.assertTimeout(Duration.ofMillis(5000), () ->{
            int producerSleepTime = 1500;
            int consumerWaitTime = 1000;

            SingleElementBufferTimed buffer = new SingleElementBufferTimed();
            Thread producer = new Thread(new ProducerTimed(1, producerSleepTime, buffer, 100), "Producer");
            producer.start();
            Thread consumer = new Thread(new ConsumerTimed(buffer, consumerWaitTime), "Consumer");
            consumer.start();

            while(!(producer.getState() == Thread.State.TERMINATED
                    && consumer.getState() == Thread.State.TERMINATED));
            // Зависания не должно быть.
            // Работа программы завершается по time out!
        });
	}
}