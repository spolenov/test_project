package test01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Реализовать пузырьковую сортировку
 */
class BubbleSort {
	private static void bubbleSort(int[] arr) {
        // TODO реализовать метод
		throw new UnsupportedOperationException("to do implementation");
	}

	@Test
	void testBubbleSortEmptyArray() {
		int[] actual = {};
		int[] expected = {};
		bubbleSort(actual);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void testBubbleSortOneElement() {
		int[] actual = {1};
		int[] expected = {1};
		bubbleSort(actual);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void testBubbleSortThreeElement() {
		int[] actual = {3,2,1,};
		int[] expected = {1,2,3};
		bubbleSort(actual);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void testBubbleSortTenElement() {
		int[] actual = {-7,6,8,5,4,9,0,3,2,10};
		int[] expected = {-7,0,2,3,4,5,6,8,9,10};
		bubbleSort(actual);
		assertArrayEquals(expected, actual);
	}
}
