package test07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Реализовать метод возвращающий диапазон элементов массива с start индеска длиной length
 */
class ArraySub {

    private int[] arraySub(int[] arr1, int start, int length) {
        if(arr1 == null){
            throw new IllegalArgumentException("Входной массив не должен быть null");
        }
        if(start < 0 || length < 1){
            throw new IllegalArgumentException("Неверные входные значения индексов");
        }
        if(start + length > arr1.length){
            throw new IllegalArgumentException("Индексы за пределами допустимого диапазона");
        }

        int[] result = new int[length];

        int counter = 0;
        for(int i = start; i<= start + length - 1; i++){
            result[counter] = arr1[i];
            counter++;
        }
        return result;
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
        assertThrows(IllegalArgumentException.class, () -> arraySub(arr1, 0, 4));
    }
}
