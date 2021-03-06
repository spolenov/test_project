package test18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * На месте каждого комментария можно:
 *	- оставить комментарий
 *	- вставить throw new NullPointerException();
 *	- вставить throw new Error();
 * Исправьте тесты. Допишите примеры всех возможных вариантов.
 * Цифрмы должны быть отсортированы в лексикографическом порядке.
 */
public class ExceptionSimple {
    String s = "";

    private void foo1() {
        s+="0";
        try {
            s+="1";
            if (true) {
                //NOP
            }
            s+="2";
        } catch (RuntimeException e) {
            s+="3";
            if (true) {
                //NOP
            }
            s+="4";
        } finally {
            s+="5";
            if (true) {//NOP
                 }
            s+="6";
        }
        s+="7";
    }

    @Test
    void f1() {
        foo1();
        assertEquals("012567", s);
    }


    private void foo3() {
        s+="0";
        try {
            s+="1";
            if (true) {throw new NullPointerException();}
            s+="2";
        } catch (RuntimeException e) {
            s+="3";
            if (true) {
                //NOP
            }
            s+="4";
        } finally {
            s+="5";
            if (true) {
                throw  new NullPointerException("Exception in 'finally' block");
            }
            s+="6";
        }
        s+="7";
    }

    @Test
    void f3() {
        try {
            foo3();
            fail();
        } catch (NullPointerException e) {
            assertEquals("01345", s);
        }
    }

    private void foo4() {
        s+="0";
        try {
            s+="1";
            if (true) {throw new RuntimeException("Initial exception");}
            s+="2";
        } catch (RuntimeException e) {
            s+="3";
            if (true) {
                //NOP
            }
            s+="4";
        } finally {
            s+="5";
            if (true) {
                //NOP
            }
            s+="6";
        }
        s+="7";
    }

    @Test
    void f4() {
        foo4();
        assertEquals("0134567", s);
    }

    private void foo5() {
        s+="0";
        try {
            s+="1";
            if (true) {
                throw new RuntimeException("Initial exception");
            }
            s+="2";
        } catch (RuntimeException e) {
            s+="3";
            if (true) {
                throw new RuntimeException("Secondary exception");
            }
            s+="4";
        } finally {
            s+="5";
            if (true) {
                throw new NullPointerException("Exception in 'finally' block");
            }
            s+="6";
        }
        s+="7";
    }

    @Test
    void f5() {
        try {
            foo5();
            fail();
        } catch (NullPointerException e) {
            assertEquals("0135", s);
        }
    }

    private void foo6() {
        s+="0";
        try {
            s+="1";
            if (true) {throw new RuntimeException("Initial exception");}
            s+="2";
        } catch (RuntimeException e) {
            s+="3";
            if (true) {throw new NullPointerException("Secondary exception");}
            s+="4";
        } finally {
            s+="5";
            if (true) {
                //NOP
            }
            s+="6";
        }
        s+="7";
    }

    @Test
    void f6() {
        try {
            foo6();
            fail();
        } catch (NullPointerException e) {
            assertEquals("01356", s);
        }
    }

    private void foo7(){
        s+="0";
        try {
            s+="1";
            if (true) {throw new Error("Initial error");}
            s+="2";
        } catch (RuntimeException e) {
            s+="3";
            if (true) //NOP}
            s+="4";
        } finally {
            s+="5";
            if (true) {throw new Error("Error in 'finally' block");}
            s+="6";
        }
        s+="7";
    }

    @Test
    void f7() {
        try {
            foo7();
            fail();
        } catch (Error e) {
            assertEquals("015", s);
        }
    }

    private void foo8() {
        s+="0";
        try {
            s+="1";
            if (true) {throw new Error("Initial error");}
            s+="2";
        } catch (RuntimeException e) {
            s+="3";
            if (true) {
                //NOP
            }
            s+="4";
        } finally {
            s+="5";
            if (true) {
                //NOP
            }
            s+="6";
        }
        s+="7";
    }

    @Test
    void f8() {
        try {
            foo8();
            fail();
        } catch (Error e) {
            assertEquals("0156", s);
        }
    }

}
