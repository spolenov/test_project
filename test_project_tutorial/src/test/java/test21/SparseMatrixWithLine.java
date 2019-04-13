package test21;

/**
 * Расширения интерфейса для работы с разряженной матрицей
 * Теперь можно получать элементы лежащие на одной линии
 */
public interface SparseMatrixWithLine extends SparseMatrix {
	
	SparseList row(int rowNum);
	
	SparseList col(int colNum);
	
}
