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
 
