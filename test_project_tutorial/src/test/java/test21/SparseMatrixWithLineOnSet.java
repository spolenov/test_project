package test21;

/**
 * Получение всех элементов лежащих на одной прямой разряженной матрицы
 * Реализация через Set - быстра и изящна
 */
public class SparseMatrixWithLineOnSet extends SparseMatrixOnSet implements SparseMatrixWithLine {

	@Override
	public SparseList row(final int rowNum) {
        return i -> usedIndexes.contains(new Index2D(rowNum, i)) ? 1: 0;
	}

	@Override
	public SparseList col(final int colNum) {
		return i -> usedIndexes.contains(new Index2D(i, colNum)) ? 1: 0;
	}

}
