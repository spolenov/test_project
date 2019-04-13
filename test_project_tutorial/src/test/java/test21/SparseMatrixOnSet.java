package test21;

import java.util.HashSet;
import java.util.Set;

/**
 * Данный способ хранения разряженной матрицы, самый оптимальный,
 * легко добавить/получить элемент
 */
public class SparseMatrixOnSet implements SparseMatrix {

	protected final Set<Index2D> usedIndexes = new HashSet<Index2D>();

	@Override
	public int get(int rowNum, int colNum) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public void set(int rowNum, int colNum) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}
	
}
