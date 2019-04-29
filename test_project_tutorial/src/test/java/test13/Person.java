package test13;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

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

@Getter
public class Person implements Comparable<Person> {
	private int age;
	private String name;
	private Address[] addresses;

	Person(int age, String name, Address[] addresses) {
		this.age = age;
		this.name = name;
		this.addresses = addresses;
	}
	
	@Override
	public String toString() {
        return "Person[age=" + age + ", name='" + name + "', addresses=" +
				Arrays.asList(addresses).toString() + "]";
	}
	
	/*
	 * метод compareTo не имеет право выбрасывать исключение
	 * какие бы аргументы ему не передавали
	 */
	@Override
	public int compareTo(Person o) {
		if(o == null){
			return 1;
		}
		if(this == o){
			return 0;
		}
		return getComparator().compare(this, o);
	}

	private Comparator<Person> getComparator(){
		Comparator<Person> byAge = Comparator
				.comparing(Person::getAge);
		Comparator<Person> byName = Comparator
				.comparing(Person::getName, Comparator.nullsFirst(Comparator.naturalOrder()));
		Comparator<Person> byAddresses = (o1, o2) -> {
			int result = 0;
			int len1 = 0;
			int len2 = 0;

			Address[] addresses1 = o1.getAddresses();
			Address[] addresses2 = o2.getAddresses();

			if(addresses1 == null && addresses2 == null){
				return 0;
			}
			if(addresses1 == null){
				return -1;
			}
			if(addresses2 == null){
				return 1;
			}
			len1 = addresses1.length;
			len2 = addresses2.length;

			if(len1 == 0 && len2 == 0){
				return 0;
			}
			if(len1 == 0){
				return -1;
			}
			if(len2 == 0){
				return 1;
			}

			for(int i = 0; i < (len1 > len2 ? len2: len1); i++){
				result = addresses1[i].compareTo(addresses2[i]);
				if(result != 0){
					break;
				}
			}

			if(result == 0){
				return Integer.compare(len1, len2);
			}

			return result;
		};
		return byAge
				.thenComparing(byName)
				.thenComparing(byAddresses);
	}

	@Override
	public int hashCode() {
        return Objects.hash(age, name) + (this.addresses == null? 0: (int) Arrays.stream(this.addresses).collect(Collectors.summarizingInt(Address::hashCode)).getSum());
	}

	/* 
	 * метод equal не имеет право выбрасывать исключение
	 * какие бы аргументы ему не передавали
	 */
	@Override
	public boolean equals(Object obj) {
        if(obj == null){
        	return false;
		}
        if(!(obj instanceof Person)){
        	return false;
		}

        return Objects.deepEquals(this.addresses,((Person)obj).getAddresses()) &&
				this.age == ((Person)obj).getAge() &&
				this.name.equals(((Person)obj).getName());
	}
}
