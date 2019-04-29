package test23;

/**
 * Реализуйте метод toString у NestedEmployee. Он должен работать следующим образом:
 * NestedEmployee emp = ...
 * System.out.println(emp);
 * NestedEmployee[age=.., name=.., address=Address[..], phone = Phone[...]]
 */
public class NestedEmployee {
    private int age;
    private String name;
    private Address[] addresses;
    private Phone[] phones;

    public NestedEmployee(int age, String name, Address[] addresses, Phone[] phones) {
    	this.age = age;
    	this.name = name;
    	this.addresses = addresses;
    	this.phones = phones;
	}

	@Override
    public String toString() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
    }
}
