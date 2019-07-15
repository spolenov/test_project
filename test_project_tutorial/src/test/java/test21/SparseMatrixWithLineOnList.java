package test21;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Получение всех элементов лежащей на одной плоскости разряженной матрицы
 * Реализация через List, более сложная и медленная
 */
public class SparseMatrixWithLineOnList extends SparseMatrixOnList implements SparseMatrixWithLine {

	@Override
	public SparseList row(final int rowNum) {
		ListEntry result = rowCol.stream()
				.filter(r -> r.index == rowNum)
				.findAny()
				.orElse(null);
		if(result == null){
			return i -> 0;
		}

        return i -> result.line.stream().anyMatch(r -> r == i) ? 1: 0;
	}

	/* 
	 * Из-за особенностей алгоритма хранения данных
	 * (матрица хранится в массиве-столбце, который содержит другие массивы-строки) 
	 * этот метод получился медленным, т.к. он выбирает столбцы
	 */
	@Override
	public SparseList col(int colNum) {
		List<ListEntry> result = rowCol.stream()
				.filter(r -> r.line.stream().anyMatch(l -> l == colNum))
				.collect(Collectors.toList());

		if(result.isEmpty()){
			return i -> 0;
		}

		return i -> result.stream().anyMatch(r -> r.index == i) ? 1: 0;
	}
}
