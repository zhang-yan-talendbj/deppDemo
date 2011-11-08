<<<<<<< HEAD
package foo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
public class AppTest extends AbstractJUnit4SpringContextTests{
	
	@Transactional(propagation=Propagation.NEVER)
	@Test
	public void testName() throws Exception {
		DataSource ds = (DataSource) applicationContext.getBean("dataSource");
		Connection con = ds.getConnection();
		PreparedStatement psmt = con.prepareStatement("INSERT INTO TEST(name) VALUES('Hello"+new Date().toString()+"')");
		psmt.execute();
	}
}
 
=======
package foo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
