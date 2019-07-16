package test24;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

/**
 * Система с одним производителем и пятью потребителеми
 * через некоторое время уходит в DeadLock...
 * помогите автору исправить досадную ошибку в реализации буфера
 * Исправления нужно делать в классе SingleElementBuffer
 */

class ProducerConsumerExample_1x5 {

    // Условно считаем, что если за 30 секунд работы
    // удалось произвести 10000 элементов, то зацикливания нет
    @Test
	void testProducerConsumer_1x5() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(30000), () ->{
            SingleElementBuffer<Integer> buffer = new SingleElementBuffer<>();
            Consumer consumer = new Consumer(buffer);

            Producer producer1 = new Producer(1, 2, buffer);
            new Thread(producer1).start();
            testWait(producer1);

            Producer producer2 = new Producer(2, 2, buffer);
            new Thread(producer2).start();
            testWait(producer2);

            Producer producer3 = new Producer(3, 2, buffer);
            new Thread(producer3).start();
            testWait(producer3);

            Producer producer4 = new Producer(4, 2, buffer);
            new Thread(producer4).start();
            testWait(producer4);

            Producer producer5 = new Producer(5, 2, buffer);
            new Thread(producer5).start();
            testWait(producer5);

            new Thread(consumer).start();

            // ждем пока произведется более 10000 элементов
            await().until(() -> producer1.getValue() >= 10000);

        });
	}
	
	void testWait(Producer producer){
        final int pause = 1;
        Awaitility.setDefaultPollInterval(pause, TimeUnit.MILLISECONDS);
        Awaitility.setDefaultPollDelay(org.awaitility.Duration.ZERO);
        Awaitility.setDefaultTimeout(org.awaitility.Duration.ONE_MINUTE);

        await()
                .untilTrue(new AtomicBoolean(producer.getValue() > 0));
    }
}