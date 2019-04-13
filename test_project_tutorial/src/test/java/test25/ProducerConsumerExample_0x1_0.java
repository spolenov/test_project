package test25;

import org.junit.jupiter.api.Test;

/**
 * Потребитель ждет, т.к. буфер пуст
 */
public class ProducerConsumerExample_0x1_0 {
    @Test
    void testProducerConsumerExample_0x1_0() {
        BlockedBoundedArrayBuffer buffer = new BlockedBoundedArrayBuffer();
		Thread thread = new Thread(new Consumer(0, buffer),"Consumer1");
		thread.start();

        // программа виснет, и consumer ждет бесконечно...
	}
}