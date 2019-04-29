package test21;

/**
 * Получение всех элементов лежащей на одной плоскости разряженной матрицы
 * Реализация через List, более сложная и медленная
 */
public class SparseMatrixWithLineOnList extends SparseMatrixOnList implements SparseMatrixWithLine {

	@Override
	public SparseList row(final int rowNum) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	/* 
	 * Из-за особенностей алгоритма хранения данных
	 * (матрица хранится в массиве-столбце, который содержит другие массивы-строки) 
	 * этот метод получился медленным, т.к. он выбирает столбцы
	 */
	@Override
	public SparseList col(int colNum) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

}
