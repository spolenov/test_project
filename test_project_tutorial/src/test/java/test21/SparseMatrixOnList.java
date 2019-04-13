package test21;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.binarySearch;

/**
 * Данный способ хранения разряженной матрицы, 
 * сложнее реализации через Set. 
 * Добавление/получение элемента происходит медленее 
 */
public class SparseMatrixOnList implements SparseMatrix {
	protected final List<ListEntry> rowCol = new ArrayList<ListEntry>();

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
