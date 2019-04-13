package test21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SparseMatrixWithLineOnSetTest {
	
	SparseMatrixWithLine matrix;

	@BeforeEach
	public void setUp() {
		matrix = new SparseMatrixWithLineOnSet();
	}

	@Test
	void testRow() {
		matrix.set(1, 1);
		matrix.set(1, 2);
		matrix.set(1, 3);
		matrix.set(2, 5);
		matrix.set(2, 6);
		matrix.set(4, 1);
		matrix.set(4, 3);

		SparseList row1 = matrix.row(1);
		assertEquals(row1.get(0), 0);
		assertEquals(row1.get(1), 1);
		assertEquals(row1.get(2), 1);
		assertEquals(row1.get(3), 1);
		assertEquals(row1.get(4), 0);

		SparseList row2 = matrix.row(2);
		assertEquals(row2.get(4), 0);
		assertEquals(row2.get(5), 1);
		assertEquals(row2.get(6), 1);
		assertEquals(row2.get(7), 0);

		SparseList row3 = matrix.row(3);
		assertEquals(row3.get(0), 0);

		SparseList row4 = matrix.row(4);
		assertEquals(row4.get(0), 0);
		assertEquals(row4.get(1), 1);
		assertEquals(row4.get(2), 0);
		assertEquals(row4.get(3), 1);
		assertEquals(row4.get(4), 0);
	}

	@Test
	void testCol() {
		matrix.set(1, 1);
		matrix.set(4, 1);
		matrix.set(2, 2);
		matrix.set(3, 2);
		matrix.set(4, 2);
		matrix.set(1, 5);
		matrix.set(3, 5);

		SparseList col1 = matrix.col(1);
		assertEquals(col1.get(0), 0);
		assertEquals(col1.get(1), 1);
		assertEquals(col1.get(2), 0);
		assertEquals(col1.get(3), 0);
		assertEquals(col1.get(4), 1);
		assertEquals(col1.get(5), 0);

		SparseList col2 = matrix.col(2);
		assertEquals(col2.get(1), 0);
		assertEquals(col2.get(2), 1);
		assertEquals(col2.get(3), 1);
		assertEquals(col2.get(4), 1);
		assertEquals(col2.get(5), 0);

		SparseList col3 = matrix.col(3);
		assertEquals(col3.get(0), 0);
		
		SparseList col5 = matrix.col(5);
		assertEquals(col5.get(0), 0);
		assertEquals(col5.get(1), 1);
		assertEquals(col5.get(2), 0);
		assertEquals(col5.get(3), 1);
		assertEquals(col5.get(4), 0);		
	}
}
