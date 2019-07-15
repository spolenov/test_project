package test21;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный способ хранения разряженной матрицы, 
 * сложнее реализации через Set. 
 * Добавление/получение элемента происходит медленее 
 */
public class SparseMatrixOnList implements SparseMatrix {
	protected final List<ListEntry> rowCol = new ArrayList<ListEntry>();

	@Override
	public int get(int rowNum, int colNum) {
		if(rowNum < 0 || colNum < 0){
			throw new IllegalArgumentException("Illegal coordinates.");
		}
        if(rowCol.stream().anyMatch(r -> r.index == rowNum && r.line.contains(colNum))){
        	return 1;
		}
        return 0;
	}

	@Override
	public void set(int rowNum, int colNum) {
		if(rowNum < 0 || colNum < 0){
			throw new IllegalArgumentException("Illegal coordinates.");
		}

		ListEntry existing = rowCol.stream().filter(r -> r.index == rowNum)
				.findAny()
				.orElse(null);

		if(existing == null){
			rowCol.add(new ListEntry(rowNum, new ArrayList<Integer>(){{add(colNum);}}));
		} else{
			if(existing.line.stream().noneMatch(l -> l == colNum)){
				existing.line.add(colNum);
			}
		}
	}
}
