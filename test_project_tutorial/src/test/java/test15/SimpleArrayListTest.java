package test15;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleArrayListTest {
	
	@Test
	void testAdd() {
		SimpleList<Integer> list = new SimpleArrayList<Integer>();
		assertTrue(list.isEmpty());
		assertFalse(list.contains(1));
		assertFalse(list.contains(null));
		list.add(1);
		assertEquals(1, list.size());
		assertEquals(new Integer(1), list.get(0));
		assertTrue(list.contains(1));
		assertFalse(list.contains(2));
		list.add(2);
		assertEquals(2, list.size());
		assertEquals(new Integer(2), list.get(1));
		assertFalse(list.contains(null));
		assertTrue(list.contains(2));
		assertFalse(list.isEmpty());
	}

	@Test
	void testAddByIndex() {
		SimpleList<Integer> list = new SimpleArrayList<Integer>();
		list.add(0, 1);
		assertEquals(1, list.size());
		list.add(1, 2);
		assertEquals(2, list.size());
	}

    @Test
    void testConstrains() {
        SimpleList<Integer> list = new SimpleArrayList<Integer>();
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
        assertFalse(list.contains(null));
        list.add(null);
        assertTrue(list.contains(null));
    }

	@Test
	void testSet() {
		SimpleList<Integer> list = new SimpleArrayList<Integer>();
		list.add(0, 1);
		assertEquals(1, list.size());
		assertEquals(new Integer(1), list.set(0, 2));
		assertEquals(1, list.size());
		assertEquals(new Integer(2), list.get(0));
		// FIXME КОСЯК!))
		// метод set может изменить элемент в ячейки выходящий за размер массива
		// при этом не увеличивая размер
		assertNull(list.set(1, 1));
		assertEquals(1, list.size());
		assertEquals(new Integer(2), list.get(0));
	}

	@Test
	void testRemove() {
		SimpleList<Integer> list = new SimpleArrayList<Integer>();
		list.add(1);
		assertEquals(1, list.size());
		assertNotNull(list.get(0));
		list.remove(0);
		assertEquals(0, list.size());
		assertNull(list.get(0));
	}

	@Test
	void testIncreaseCapasity() {
		SimpleList<Integer> list = new SimpleArrayList<Integer>();
		for (int i = 0; i < 16; i++) {
			list.add(i);
		}
		list.add(16);
	}

	@Test
	void testArrayIndexOutOfBounds() {
		SimpleList<Integer> list = new SimpleArrayList<Integer>();
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(-1));
	}

	@Test
	void testIntegerToString() {
		SimpleList<Integer> list = new SimpleArrayList<Integer>();
		assertEquals("[]", list.toString());
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals("[1,2,3]", list.toString());
	}

	@Test
	void testStringToString() {
		SimpleList<String> list = new SimpleArrayList<String>();
		assertEquals("[]", list.toString());
		list.add("to");
		list.add("say");
		list.add("what");
		assertEquals("[to,say,what]", list.toString());
	}

	@Test
	void testListToString() {
		SimpleList<SimpleArrayList<Integer>> list = new SimpleArrayList<SimpleArrayList<Integer>>();
		assertEquals("[]", list.toString());
		SimpleArrayList<Integer> item1 = new SimpleArrayList<Integer>();
		item1.add(1);
		item1.add(2);
		item1.add(3);
		list.add(item1);
		SimpleArrayList<Integer> item2 = new SimpleArrayList<Integer>();
		item2.add(4);
		item2.add(5);
		item2.add(6);
		list.add(item2);
		SimpleArrayList<Integer> item3 = new SimpleArrayList<Integer>();
		item3.add(7);
		item3.add(8);
		item3.add(9);
		list.add(item3);
		assertEquals("[[1,2,3],[4,5,6],[7,8,9]]", list.toString());
	}

	@Test
	void testHashCode() {
		SimpleList<Integer> list = new SimpleArrayList<Integer>();
		assertTrue(list.hashCode() > 0);
	}

	@Test
	void testEquals() {
		SimpleList<Integer> list1 = new SimpleArrayList<Integer>();
		SimpleList<Integer> list2 = new SimpleArrayList<Integer>();
		assertEquals(list1, list2);
		list1.add(1);
		list2.add(1);
		assertEquals(list1, list2);
		list1.add(2);
		assertFalse(list1.equals(list2));
		list2.add(null);
		assertFalse(list1.equals(list2));
	}

	@Test
	void testIterator() {
		SimpleList<Integer> list = new SimpleArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		Iterator<Integer> iter = list.iterator();

		iter.remove();

		assertTrue(iter.hasNext());
		assertTrue(iter.hasNext());
		assertTrue(iter.hasNext());

		while (iter.hasNext()) {
			Integer cur = iter.next();
			assertTrue(cur >=2 && cur <=3);
		}
	}

	@Test
	void testRemoveByElement() {
		SimpleList<Integer> list = new SimpleArrayList<Integer>();
		list.add(1);
		assertEquals(1, list.size());
		assertNotNull(list.get(0));
		assertTrue(list.remove(new Integer(1)));
		assertFalse(list.remove(new Integer(0)));
		assertEquals(0, list.size());
		assertNull(list.get(0));
	}
	
}
