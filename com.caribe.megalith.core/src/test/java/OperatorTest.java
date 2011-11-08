<<<<<<< HEAD
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class OperatorTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OperatorTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(OperatorTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }
    public void plusTest(){
        int i=1;
        assertEquals(i++,1);
        assertEquals(++i,2);
    }
}
=======
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class OperatorTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OperatorTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(OperatorTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }
    public void plusTest(){
        int i=1;
        assertEquals(i++,1);
        assertEquals(++i,2);
    }
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
