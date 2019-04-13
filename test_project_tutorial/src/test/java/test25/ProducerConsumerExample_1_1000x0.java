package test25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Producer заполняет буфер и ждет..
 */
public class ProducerConsumerExample_1_1000x0 {
    @Test
    void testProducerConsumerExample_1_1000x0() throws InterruptedException {

        Assertions.assertTimeout(Duration.ofMillis(30000), () ->{
            BlockedBoundedArrayBuffer buffer = new BlockedBoundedArrayBuffer();
            Producer producer = new Producer(1, 1000, buffer);
            new Thread(producer,"Producer1").start();

            Thread.currentThread().sleep(15000); // даем возможность producer'у заполнить буфер

            // проверим емкость буфера
            assertEquals(buffer.getSize(), 10);

            // проверим что буфер полностью заполнен. 10 элементов, нумерация от нуля
            assertEquals(buffer.getCurrent(), 9);

            // программа заполняет буфер в 10 элементов, и producer ждет бесконечно...
        });
	}
}