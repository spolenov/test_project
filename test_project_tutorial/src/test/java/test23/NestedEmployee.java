package test23;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    NestedEmployee(int age, String name, Address[] addresses, Phone[] phones) {
    	this.age = age;
    	this.name = name;
    	this.addresses = addresses;
    	this.phones = phones;
	}

	@Override
    public String toString() {
        return "NestedEmployee[age=" + age +
                (name == null? "": ", name=" + name)  +
                (addresses == null? "": ", addresses=" + toStringAddresses())  +
                (phones == null? "": ", phones=" + toStringPhones()) + "]";
    }

    private String toStringAddresses(){
        if(addresses == null){
            return "";
        }
        return "[" + Arrays.stream(addresses)
                .map(Address::toString)
                .collect(Collectors.joining(", ")) + "]";
    }

    private String toStringPhones(){
        if(phones == null){
            return "";
        }
        return "[" + Arrays.stream(phones)
                .map(Phone::toString)
                .collect(Collectors.joining(", ")) + "]";
    }
}
