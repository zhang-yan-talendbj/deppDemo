package com.caribe.stone.j2se.operator;
import static junit.framework.Assert.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class OperatorTest{

	@Test
    public void plusTest(){
        int i=1;
        assertEquals(i++,1);
        assertEquals(++i,3);
        assertEquals("", "");;
    }
}
