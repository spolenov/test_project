package test23;

public class Phone {
	private final int code;
	private final int number;

	/**
	 * Реализуйте метод toString у Phone. Он должен работать следующим образом:
	 * System.out.println(new Phone(777, 1234567));
 	 * Phone[(777) 123-45-67]
	 */
	Phone(int code, int number) {
		this.code = code;
		this.number = number;
	}
	
	@Override
	public String toString() {
		String num = "";
        return String.format("Phone[(%d) %s]", code, getStringNumber());
	}

	private String getStringNumber(){
		if(number < 0){
			throw new IllegalArgumentException("Неправильный номер: " + number);
		}

		String tmp = String.valueOf(number);

		if(number < 1000){
			return tmp;
		}

		if(number < 100000){
			return tmp.substring(0, 3) + "-" +tmp.substring(3);
		}

		if(number < 10000000){
			return tmp.substring(0, 3) + "-" + tmp.substring(3, 5)  + "-" + tmp.substring(5);
		}
		throw new IllegalArgumentException("Неправильный номер: " + number);
	}
}
