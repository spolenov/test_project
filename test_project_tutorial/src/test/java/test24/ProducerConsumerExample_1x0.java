package test24;

import org.junit.jupiter.api.Test;

/**
 * Система с одним производителем блокируется позже 
 * (успевает произвести 2 числа и повисает пытаясь поместить 
 * второе число в очередь еще занятую первым числом)
 *
 */
class ProducerConsumerExample_1x0 {

    @Test
    void testProducerConsumer_1x0() {
		SingleElementBuffer buffer = new SingleElementBuffer();
        Producer producer = new Producer(1, 1, buffer);
        Thread thread = new Thread(producer);
        thread.start();

        // программа виснет, и producer ждет бесконечно...
	}
}