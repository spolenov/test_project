package test09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static test09.IteratorUtils.interval;
import static test09.IteratorUtils.merge;

public class MergeIteratorTest {

	@Test
	public void merge10To12_10To12() {
		String actuals = "";
		for (int k : merge(interval(10, 12), interval(10, 12))) {
			actuals += " " + k;
		}
		assertEquals(" 10 10 11 11", actuals);
	}

	@Test
	public void merge10To10_10To12() {
		String actuals = "";
		for (int k : merge(interval(10, 10), interval(10, 12))) {
			actuals += " " + k;
		}
		assertEquals(" 10 11", actuals);
	}
	
	@Test
	public void merge10To12_10To10() {
		String actuals = "";
		for (int k : merge(interval(10, 12), interval(10, 10))) {
			actuals += " " + k;
		}
		assertEquals(" 10 11", actuals);
	}

	@Test
	public void merge10To10_10To10() {
		String actuals = "";
		for (int k : merge(interval(10, 10), interval(10, 10))) {
			actuals += " " + k;
		}
		assertEquals("", actuals);
	}

	@Test
	public void merge0To10_1000To1002() {
		String actuals = "";
		for (int k : merge(interval(0, 10), interval(1000, 1002))) {
			actuals += " " + k;
		}
		assertEquals(" 0 1 2 3 4 5 6 7 8 9 1000 1001", actuals);
	}

	@Test
	public void merge1000To1002_0To10() {
		String actuals = "";
		for (int k : merge(interval(1000, 1002), interval(0, 10))) {
			actuals += " " + k;
		}
		assertEquals(" 0 1 2 3 4 5 6 7 8 9 1000 1001", actuals);
	}

	@Test
	public void merge0To10_5To15() {
		String actuals = "";
		for (int k : merge(interval(0, 10), interval(5, 15))) {
			actuals += " " + k;
		}
		assertEquals(" 0 1 2 3 4 5 5 6 6 7 7 8 8 9 9 10 11 12 13 14", actuals);
	}
}
