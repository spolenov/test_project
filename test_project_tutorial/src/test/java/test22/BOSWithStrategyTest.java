package test22;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class BOSWithStrategyTest {
	private final static int COUNT = 10;
	private final static AllocateStrategy ALLOCATED_STRATEGY = now -> now + (now << 1);
	
	@Test
	void test_fillByByte_WriteTo() throws IOException {
		BOSWithStrategyWithoutCoping buff = fillByByte();
		ByteArrayOutputStream tmpBuff = new ByteArrayOutputStream();
		buff.writeTo(tmpBuff);
		byte[] dataFromBAOS = tmpBuff.toByteArray();
		checkCorrectData(dataFromBAOS);
	}

	@Test
	void test_fillByArray_WriteTo() throws IOException {
		BOSWithStrategyWithoutCoping buff = fillByArray();
		ByteArrayOutputStream tmpBuff = new ByteArrayOutputStream();
		buff.writeTo(tmpBuff);
		byte[] dataFromBAOS = tmpBuff.toByteArray();
		checkCorrectData(dataFromBAOS);
	}

	@Test
	void test_fillByByte_toByteArray() throws IOException {
		BOSWithStrategyWithoutCoping buff = fillByByte();
		byte[] dataFromBAOS = buff.toByteArray();
		checkCorrectData(dataFromBAOS);
	}

	@Test
	void test_fillByArray_toByteArray() throws IOException {
		BOSWithStrategyWithoutCoping buff = fillByArray();
		byte[] dataFromBAOS = buff.toByteArray();
		checkCorrectData(dataFromBAOS);
	}

	private static void checkCorrectData(byte[] array) {
		Random rnd = new Random(0);
		for (int k = 0; k < COUNT; k++) {
			byte expected = (byte) rnd.nextInt();
			byte actual = array[k];
			if (actual != expected) {
				throw new AssertionError("expected = " + expected + ", actual = " + actual);
			}
		}
	}
	
	private static BOSWithStrategyWithoutCoping fillByByte() throws IOException {
		BOSWithStrategyWithoutCoping result = new BOSWithStrategyWithoutCoping(ALLOCATED_STRATEGY);
		Random rnd = new Random(0);
		for (int k = 0; k < COUNT; k++) {
			result.write((byte) rnd.nextInt());
		}
		return result;
	}
	
	private static BOSWithStrategyWithoutCoping fillByArray() throws IOException {
		BOSWithStrategyWithoutCoping result = new BOSWithStrategyWithoutCoping(ALLOCATED_STRATEGY);
		Random dataRnd = new Random(0);
		Random arraySizeRnd = new Random(1);
		
		int wroteSize = 0;
		while (wroteSize < COUNT) {
			int newBuffSize = 1 + arraySizeRnd.nextInt(10);
			if (newBuffSize == 1) {
				result.write((byte) dataRnd.nextInt());
				wroteSize +=1;
			} else {
				byte[] buff = new byte[newBuffSize];
				for (int k = 0; k < buff.length; k++) {
					buff[k] = (byte) dataRnd.nextInt();
				}
				result.write(buff);
				wroteSize +=1;
			}
		}
		
		return result;
	}

}
