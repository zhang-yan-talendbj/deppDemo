package com.caribe.stone.java.operator;
import static junit.framework.Assert.*;

/**
 * Unit test for simple App.
 */
public class OperatorTest{

    public void plusTest(){
        int i=1;
        assertEquals(i++,1);
        assertEquals(++i,2);
        assertEquals("", "");;
    }
}
