package test01;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Реализовать пузырьковую сортировку
 */
class BubbleSort {
	private static void bubbleSort(int[] arr) {
		if(arr == null || arr.length <= 1){
			return;
		}

		for(int counterOuter = 1; counterOuter < arr.length; counterOuter++){
			for(int counterInner = 1; counterInner < arr.length; counterInner++){
				if(arr[counterInner - 1] > arr[counterInner]){
					swap(arr, counterInner - 1, counterInner);
				}
			}
		}
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

	@Test
	void testSortWithNonUniqueElements(){
		int[] actual = {-1,6,-7,0,6,-5,-2,-10,-4,6,-5,-1,-2,3,-2,5,-7,-7,-7,6,6,10,-9,-6,5,-5,1,-7,4,-6,8,-10,-3,-4,2,-4,-4,10,1,9,4,9,7,-8,6,2,-2,8,-2,6};
		int[] expected = actual.clone();
		Arrays.sort(expected);
		bubbleSort(actual);
		assertArrayEquals(expected, actual);
	}

	private static void swap(int[] arr, int posFirst, int posSecond){
		int bufferValue;

		if(posFirst < 0 || posSecond < 0){
			throw new IllegalArgumentException("Номер позиции не должен быть отрицательным");
		}

		if(posFirst > posSecond){
			int bufferPos = posFirst;
			posFirst = posSecond;
			posSecond = bufferPos;
		}

		if(posFirst == posSecond){
			return;
		}

		bufferValue = arr[posFirst];
		arr[posFirst] = arr[posSecond];
		arr[posSecond] = bufferValue;
	}
}
