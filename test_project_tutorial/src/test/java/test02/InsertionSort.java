package test02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Реализовать сортировку вставкой
 */
class InsertionSort {

    private static void insertionSort(int[] arr) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }

    @Test
    void testInsertionSortEmptyArray() {
        int[] actual = {};
        int[] expected = {};
        insertionSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testInsertionSortOneElement() {
        int[] actual = {1};
        int[] expected = {1};
        insertionSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testInsertionSortThreeElement() {
        int[] actual = {3,2,1,};
        int[] expected = {1,2,3};
        insertionSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testInsertionSortTenElement() {
        int[] actual = {-7,6,8,5,4,9,0,3,2,10};
        int[] expected = {-7,0,2,3,4,5,6,8,9,10};
        insertionSort(actual);
        assertArrayEquals(expected, actual);
    }

}
