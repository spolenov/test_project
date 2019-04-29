package test11;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ISToIteratorAdapterTest {

	@Test
	void ISToIteratorAdapter0() {
		String actual = "";
		Iterator<Integer> iter0 = new ISToIteratorAdapter(new ByteArrayInputStream(new byte[0]));
		while (iter0.hasNext()) {
			actual += " " + iter0.next();
		}
		assertEquals("", actual);
	}

	@Test
	void ISToIteratorAdapter10() {
		String actual = "";
		Iterator<Integer> iter1 = new ISToIteratorAdapter(new ByteArrayInputStream(new byte[] { 10 }));
		while (iter1.hasNext()) {
			actual += " " + iter1.next();
		}
		assertEquals(" 10", actual);
	}

	@Test
	void ISToIteratorAdapter10_20_30() {
		String actual = "";

		Iterator<Integer> iter3 = new ISToIteratorAdapter(new ByteArrayInputStream(new byte[] { 10, 20, 30 }));
		while (iter3.hasNext()) {
			actual += " " + iter3.next();
		}
		assertEquals(" 10 20 30", actual);
	}

    @Test
    void ISToIteratorAdapterNilpotent() {
        String actual = "";

        Iterator<Integer> iter3 = new ISToIteratorAdapter(new ByteArrayInputStream(new byte[] { 10 }));
        iter3.hasNext();
        iter3.hasNext();
        iter3.hasNext();
        iter3.hasNext();
        assertEquals("10", "" + iter3.next());
    }
}
