package test03;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Реализовать сортировку выбором
 */
class SelectionSort {

    static void selectionSort(int[] arr) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }

    @Test
    void testSelectionSortEmptyArray() {
        int[] actual = {};
        int[] expected = {};
        selectionSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSelectionSortOneElement() {
        int[] actual = {1};
        int[] expected = {1};
        selectionSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSelectionSortThreeElement() {
        int[] actual = {3,2,1,};
        int[] expected = {1,2,3};
        selectionSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSelectionSortTenElement() {
        int[] actual = {-7,6,8,5,4,9,0,3,2,10};
        int[] expected = {-7,0,2,3,4,5,6,8,9,10};
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }
}
