package test08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static test08.IteratorUtils.interval;

public class IteratorTest {
	double[] array = {0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5};
	
	@Test
	public void index0ToEndTest() {
		String actuals = "";
		for (int index : interval(0, array.length)) {
			actuals += " " + index;
		}
		assertEquals(" 0 1 2 3 4 5 6 7 8 9", actuals);
	}
	
	@Test
	public void value0ToEndTest() {
		String actuals = "";
		for (int index : interval(0, array.length)) {
			actuals += " " + array[index];
		}
		assertEquals(" 0.5 1.5 2.5 3.5 4.5 5.5 6.5 7.5 8.5 9.5", actuals);
	}
	
	@Test
	public void index4To8Test() {
		String actuals = "";	
		for (int index : interval(4, 8)) {
			actuals += " " + index;
		}
		assertEquals(" 4 5 6 7", actuals);
	}
			
	@Test
	public void value4To8Test() {
		String actuals = "";	
				for (int index : interval(4, 8)) {
			actuals += " " + array[index];
		}
		assertEquals(" 4.5 5.5 6.5 7.5", actuals);
	}
}
