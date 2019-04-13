package test17;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class XMapTest {

	public static int INITIAL_CAPACITY = 1024 * 1024 ;

	/**
	 * метод put в HashMap работает быстро,
	 * но когда нужно увеличить размер HashMap,
	 * то происходит резкое увеличение времени работы put
	 *
	 * 16790
	 * 1273
	 * 4738
	 * 102878534
 	 * 3354
	 */
	@Test
	void testOriginalHashMapTimePutFor() {
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(INITIAL_CAPACITY, 1);
		for (int k = 0; k < INITIAL_CAPACITY - 2; k++) {
			map.put(k, null);
		}
		
		for (int k = 0; k <= 4; k++) {
			long t0 = System.nanoTime();
			map.put(-k, null);
			long t1 = System.nanoTime();
			System.out.println(t1 - t0);
		}
	}

    @Test
    void testMap() {
        XHashMap<Integer, Integer> map = new XHashMap<Integer, Integer>();
        assertFalse(map.containsKey(1));
        map.put(1, 1);
        assertTrue(map.containsKey(1));
        map.put(2, 2);
        assertTrue(map.containsKey(2));
        map.put(1, 2);
        assertEquals(new Integer(2), map.get(1));
        assertEquals(new Integer(2), map.get(2));
    }

    @Test
    void testRemoveForKey() {
        XHashMap<Integer, Integer> map = new XHashMap<Integer, Integer>();
        map.put(1, 1);
        map.put(2, 2);
        assertEquals(new Integer(1), map.get(1));
        assertEquals(new Integer(2), map.get(2));
        map.remove(1);
        assertNull(map.get(1));
        map.remove(2);
        assertNull(map.get(2));
    }
}
