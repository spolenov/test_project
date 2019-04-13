package test21;

import java.util.List;

/**
 * Костыль для реализации хранения разряженной матрицы через List
 */
public class ListEntry implements Comparable<ListEntry>{
	
	public final int index;
	public final List<Integer> line;
	
	public ListEntry(int index, List<Integer> line) {
		this.index = index;
		this.line = line;
	}

	@Override
	public int compareTo(ListEntry that) {
		// сравниваем ТОЛЬКО по индексу
		return this.index - that.index;
	}
	
	
}
