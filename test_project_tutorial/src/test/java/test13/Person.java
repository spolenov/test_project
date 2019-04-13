package test13;

import java.util.Arrays;

/**
 * Считать, что порядок адресов в массиве - важен.
 * Написать в классе Person метод hashCode.
 * Написать в классе Person метод toString.
 * Написать в классе Person метод equals.
 *
 * Написать в классе Person метод CompareTo.
 * Считать что раньше должен стоять тот, кто моложе, если равного возраста, то тот у кого имя
 * лексиграфически меньше (считайте null больше всех), если имена совпадают, то на адресах определите
 * порядок сами (но он должен быть корректным - антисимметричным, транзитивным).
 * 
 * корректно обработать ситуации когда поле ссылочного типа равно null.
 * кореектно обрабатывать массив нулевой длины
 * 
 * порядок адресов в массиве - важен, если массивы адресов [address0, address1, address2]
 * и [address2, address1, address0] считаются не эквивалентными.
 *
 */

public class Person implements Comparable<Person> {
	private int age;
	private String name;
	private Address[] addresses;

	public Person(int age, String name, Address[] addresses) {
		this.age = age;
		this.name = name;
		this.addresses = addresses;
	}
	
	@Override
	public String toString() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}
	
	/*
	 * метод compareTo не имеет право выбрасывать исключение
	 * какие бы аргументы ему не передавали
	 */
	@Override
	public int compareTo(Person o) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public int hashCode() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	/* 
	 * метод equal не имеет право выбрасывать исключение
	 * какие бы аргументы ему не передавали
	 */
	@Override
	public boolean equals(Object obj) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}
}
