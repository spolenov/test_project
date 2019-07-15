package test22;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOSWithStrategyWithoutCoping extends OutputStream{

	// В OutputStream use pattern Tamplate Method for realizable method write()
	
	private static final int DEFAULT_START_SIZE = 16;
	private static final AllocateStrategy DEFAULT_ALLOCATE_STRATEGY = new DoubleAllocateStrategy();
	private final AllocateStrategy strategy;
	private List<byte[]> bufferList = new ArrayList<byte[]>(16);
	private int count = 0;

	public BOSWithStrategyWithoutCoping() {
		this(DEFAULT_START_SIZE, DEFAULT_ALLOCATE_STRATEGY);
	}

	public BOSWithStrategyWithoutCoping(int startSize) {
		this(startSize, DEFAULT_ALLOCATE_STRATEGY);
	}

	BOSWithStrategyWithoutCoping(AllocateStrategy strategy) {
		this(DEFAULT_START_SIZE, strategy);
	}

	BOSWithStrategyWithoutCoping(int startSize,
                                        AllocateStrategy strategy) {
		bufferList.add(new byte[startSize]);
		this.strategy = strategy;
	}

	@Override
	public void write(int b) throws IOException {
		// получаем текущий буфер
		byte[] lastBuff = bufferList.get(bufferList.size() - 1);
		
		// переполнение! надо увеличить
		if (count == lastBuff.length) {
			// увеличиваем буфер согласно стратегии
			int newSize = strategy.nextAfter(lastBuff.length);
			byte[] newLastBuff = new byte[newSize];
			bufferList.add(newLastBuff);
			count = 0;
			lastBuff = newLastBuff;
		}
		
		// все ОК. просто пишем
		lastBuff[count++] = (byte) b;
	}
	
	@Override
	public void write(byte b[]) throws IOException {
		for(byte bt: b){
			write(bt);
		}
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
        for(int i = 0; i < off; i++){
        	write(0);
		}
        for(int i = 0; i < len; i++){
			write(b[i]);
		}
	}

	void writeTo(OutputStream out) throws IOException {
		for(byte[] buff: bufferList){
			out.write(buff);
		}
	}
	
	byte[] toByteArray() {
		// calc length byte array
		int countByte = 0;
		int lastArray = bufferList.size() - 1;
		
		for (int i = 0; i < lastArray; i++) {
			countByte += bufferList.get(i).length;
		}
		countByte += count;
		
		// craate new array
		byte[] byteArray = new byte[countByte];

		int position = 0;
		for (int i = 0; i < lastArray; i++) {
			byte[] currentArray = bufferList.get(i);
			System.arraycopy(currentArray, 0, byteArray, position, currentArray.length);
			position += currentArray.length;
		}
		System.arraycopy(bufferList.get(lastArray), 0, byteArray, position, count);
		
		return byteArray;
	
	}
}
