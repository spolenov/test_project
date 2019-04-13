package test21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SparseMatrixOnListTest {

	SparseMatrix matrix = null;
		
	@BeforeEach
	public void setUp() {
		matrix = new SparseMatrixOnList();
	}

	@Test
	void testSet1() {
		matrix.set(0, 0);
		assertEquals(1, matrix.get(0, 0));
		assertEquals(0, matrix.get(1, 1));
	}
	
	@Test
	void testSetMax() {
		matrix.set(Integer.MAX_VALUE, Integer.MAX_VALUE);
		assertEquals(1, matrix.get(Integer.MAX_VALUE, Integer.MAX_VALUE));
		assertEquals(0, matrix.get(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1));
		assertEquals(0, matrix.get(0, 0));
	}
	
	@Test
	void testSet100500() {
		matrix.set(100500, 1);
		assertEquals(1, matrix.get(100500, 1));
		assertEquals(0, matrix.get(0, 0));
		assertEquals(0, matrix.get(100499, 1));
		assertEquals(0, matrix.get(100500, 0));
	}

	@Test
	void testSet10() {
		matrix.set(5, 10);
		assertEquals(1, matrix.get(5, 10));
		assertEquals(0, matrix.get(10, 5));
		assertEquals(0, matrix.get(10, 10));
		assertEquals(0, matrix.get(5, 5));
	}
	
	@Test
	void testSetLineOne() {
		matrix.set(1, 1);
		matrix.set(1, 2);
		matrix.set(1, 3);
		matrix.set(2, 10);
		assertEquals(0, matrix.get(1, 0));
		assertEquals(1, matrix.get(1, 1));
		assertEquals(1, matrix.get(1, 2));
		assertEquals(1, matrix.get(1, 3));
		assertEquals(0, matrix.get(1, 4));
		assertEquals(1, matrix.get(2, 10));
		assertEquals(0, matrix.get(2, 20));
		assertEquals(0, matrix.get(3, 1));
		assertEquals(0, matrix.get(0, 1));
	}

	// Проверка граничных значений для Set
	@Test
	void testSetIndexOutOfBoundsBothMaх() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.set(Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1));
	}
	
	@Test
	void testSetIndexOutOfBoundsOneMaх() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.set(Integer.MAX_VALUE + 1, Integer.MAX_VALUE));
	}
	
	@Test
	void testSetIndexOutOfBoundsTwoMaх() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.set(Integer.MAX_VALUE, Integer.MAX_VALUE + 1));
	}
	
	@Test
	void testSetIndexOutOfBoundsBothMin() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.set(-1, -1));
	}
	
	@Test
	void testSetIndexOutOfBoundsOneMin() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.set(-1, 0));
	}
	
	@Test
	void testSetIndexOutOfBoundsTwoMin() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.set(0, -1));
	}
	
	// Проверка граничных значений для Get
	@Test
	void testGetIndexOutOfBoundsBothMaх() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.get(Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1));
	}
	
	@Test
	void testGetIndexOutOfBoundsOneMaх() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.get(Integer.MAX_VALUE + 1, Integer.MAX_VALUE));
	}
	
	@Test
	void testGetIndexOutOfBoundsTwoMaх() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.get(Integer.MAX_VALUE, Integer.MAX_VALUE + 1));
	}
	
	@Test
	void testGetIndexOutOfBoundsBothMin() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.get(-1, -1));
	}
	
	@Test
	void testGetIndexOutOfBoundsOneMin() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.get(-1, 0));
	}
	
	@Test
	void testGetIndexOutOfBoundsTwoMin() {
		assertThrows(IllegalArgumentException.class, () ->
				matrix.get(0, -1));
	}
	
	//	Такая проверка ОШИБОЧНА, и не удовлетворяет правилам TDD!
	//	- она долго работает (тест должен работать бысто)
	//	- хотя она и проверяет все значения, но это НЕ нужно (проверять только граничные)	
	//	@Test
	//	void testGetEmpty() {
	//		for (int i = 0; i <= Integer.MAX_VALUE; i++) {
	//			for (int j = 0; i <= Integer.MAX_VALUE; i++) {
	//				assertEquals(0, matrix.get(i, j));
	//			}
	//		}
	//	}
}
