package test09;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Iterator;

/**
 * Получает два Iterable, возвращающие итераторы на сортированные последовательности,
 * и сливает в Iterable возвращающий итератор на сортированную
 * последовательность образованную в результате слияния
 */
public class MergeIterator implements Iterator<Integer> {

	private Iterator<Integer> one;
	private Iterator<Integer> two;

	private Integer currentFromFirst = null;
	private Integer currentFromSecond = null;

	public MergeIterator(Iterable<Integer> one, Iterable<Integer> two) {
		this.one = one.iterator();
		this.two = two.iterator();
	}

	@Override
	public boolean hasNext() {
		if(one.hasNext() || two.hasNext()){
			return true;
		}

		return currentFromFirst != null || currentFromSecond != null;
	}

	@Override
	public Integer next() {
		Integer temp = null;

        if(currentFromFirst == null && one.hasNext()){
			currentFromFirst = one.next();
		}
		if(currentFromSecond == null && two.hasNext()){
			currentFromSecond = two.next();
		}

		if(currentFromFirst == null){
			temp = currentFromSecond;
			currentFromSecond = null;
		} else{
			if(currentFromSecond == null){
				temp = currentFromFirst;
				currentFromFirst = null;
			}
		}

		if(temp != null){
			return temp;
		}
		if(currentFromFirst <= currentFromSecond){
			temp = currentFromFirst;
			currentFromFirst = null;
		} else{
			temp = currentFromSecond;
			currentFromSecond = null;
		}

		return temp;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
