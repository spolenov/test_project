package test24;

import org.junit.jupiter.api.Test;

/**
 * Система с одним потребителем сразу же блокируется 
 * (потребитель висит на очереди ожидая данных)
 *
 */

class ProducerConsumerExample_0x1 {
    @Test
    void testProducerConsumer_0x1() {
		SingleElementBuffer buffer = new SingleElementBuffer();
		Consumer consumer = new Consumer(buffer);
		Thread thread = new Thread(consumer);
		thread.start();

        // программа виснет, и consumer ждет бесконечно...
	}
}