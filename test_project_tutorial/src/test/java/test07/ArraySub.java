package test07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Реализовать метод возвращающий диапазон элементов массива с start индеска длиной length
 */
class ArraySub {

    private int[] arraySub(int[] arr1, int start, int length) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }

    @Test
    void testArraySubAll() {
        int[] arr1 = {1, 3, 2, 5};
        int[] arr2 = {1, 3, 2, 5};
        assertArrayEquals(arraySub(arr1, 0, 4), arr2);
    }

    @Test
    void testArraySub1_2() {
        int[] arr1 = {1, 3, 2, 5};
        int[] arr2 = {3, 2};
        assertArrayEquals(arraySub(arr1, 1, 2), arr2);
    }

    @Test
    void testArraySub4_2() {
        int[] arr1 = {1, 3, 2, 5, 1, 7};
        int[] arr2 = {1, 7};
        assertArrayEquals(arraySub(arr1, 4, 2), arr2);
    }

    @Test
    void testArraySub_EmptyArray() {
        int[] arr1 = {};
        assertThrows(IllegalArgumentException.class, () -> arraySub(arr1, 4, 2));
    }

    @Test
    void testArraySub0_4() {
        int[] arr1 = {1, 3, 2};
        arraySub(arr1, 0, 4);
        assertThrows(IllegalArgumentException.class, () -> arraySub(arr1, 0, 4));
    }
}
