package test06;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Реализовать метод сравнения элеметнов двух массивой
 * true - если массивы содержат одинаковые элементы
 */
class ArrayEquals {

    boolean arrayEquals(int[] arr1, int[] arr2) {
        if(arr1 == null && arr2 == null){
            return true;
        }
        if(arr1 == null ^ arr2 == null){
            return false;
        }

        if(arr1.length != arr2.length){
            return false;
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        for(int i = 0; i< arr1.length; i++){
            if(arr1[i] != arr2[i]){
                return false;
            }
        }

        return true;
    }

    @Test
    void testEqualsIntOneElement() {
        int[] arr1 = {1};
        int[] arr2 = {1};
        assertTrue(arrayEquals(arr1, arr2));
    }

    @Test
    void testEqualsIntEightElement() {
        int[] arr1 = {1, 7, 0, 21, 12, -8, -5, 19};
        int[] arr2 = {7, 1, 19, -8, 12, 21, -5, 0};
        assertTrue(arrayEquals(arr1, arr2));
    }

    @Test
    void testEqualsIntEmptyArray() {
        int[] arr1 = {};
        int[] arr2 = {};
        assertTrue(arrayEquals(arr1, arr2));
    }

    @Test
    void testNotEqualsIntEmptyArray() {
        int[] arr1 = {};
        int[] arr2 = {1};
        assertFalse(arrayEquals(arr1, arr2));
    }

    @Test
    void testNotEqualsIntOneElement() {
        int[] arr1 = {2};
        int[] arr2 = {1};
        assertFalse(arrayEquals(arr1, arr2));
    }

    @Test
    void testNotEqualsIntDifferentElement() {
        int[] arr1 = {2, 5, 7};
        int[] arr2 = {2, 5};
        assertFalse(arrayEquals(arr1, arr2));
    }

    @Test
    void testNotEqualsInt() {
        int[] arr1 = {2, 5, 7};
        int[] arr2 = {2, 5, 6};
        assertFalse(arrayEquals(arr1, arr2));
    }


}
