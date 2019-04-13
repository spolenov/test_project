package test23;

public class Phone {
	private final int code;
	private final int number;

	/**
	 * Реализуйте метод toString у Phone. Он должен работать следующим образом:
	 * System.out.println(new Phone(777, 1234567));
 	 * Phone[(777) 123-45-67]
	 */
	public Phone(int code, int number) {
		this.code = code;
		this.number = number;
	}
	
	@Override
	public String toString() {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}
}
