package test10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static test10.IteratorUtils.squares;

public class SquaresTest {

	@Test
	public void indexSquare0To100Test() {
		String actuals = "";
		for (int index : squares(0, 100)) {
			actuals += " " + index;
		}
		assertEquals(" 0 1 4 9 16 25 36 49 64 81", actuals);
	}

	@Test
	public void indexSquare0To1000Test() {
		String actuals = "";
		for (int index : squares(0, 1000)) {
			actuals += " " + index;
		}
		assertEquals(" 0 1 4 9 16 25 36 49 64 81 100 121 144 169 196 225 256" +
				" 289 324 361 400 441 484 529 576 625 676 729 784 841 900 961", actuals);
	}

    @Test
    public void indexSquare9To81Test() {
        String actuals = "";
        for (int index : squares(9, 81)) {
            actuals += " " + index;
        }
        assertEquals(" 9 16 25 36 49 64", actuals);
    }

    @Test
    public void indexSquare17To25Test() {
        String actuals = "";
        for (int index : squares(17, 25)) {
            actuals += " " + index;
        }
        assertEquals("", actuals);
    }
}
