package test21;

public class Index2D {

	public final int row;
	public final int col;
	
	/**
	 * Точка в двухмерной системе координат
	 * @param row - индекс строки
	 * @param col - индекс столбца
	 */
	public Index2D(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Index2D) {
			Index2D that = (Index2D) obj;
			return (this.row == that.row) && (this.col == that.col);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		/*
	    * корректно, не обязательно через простые числа (row*31 + col*37),
		* коллизии в любом случае будут 
		* (hashCode всего 4млрд, а размер матрицы 4млрд * 4млрд), 
		* поэтому всегда найдется сочетание которое возвратит один и тот же hashCode.
		*/
		return row + col;
	}
}
