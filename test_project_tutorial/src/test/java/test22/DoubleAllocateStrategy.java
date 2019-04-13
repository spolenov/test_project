package test22;

public class DoubleAllocateStrategy implements AllocateStrategy {

	@Override
	public int nextAfter(int now) {
		return now * 2;
	}

}
