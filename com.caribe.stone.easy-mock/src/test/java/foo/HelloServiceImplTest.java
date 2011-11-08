<<<<<<< HEAD
package foo;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;

public class HelloServiceImplTest {

	@Test
	public void testSay() {
		HelloServiceImpl impl = EasyMock.createMock(HelloServiceImpl.class);
		EasyMock.expect(impl.say()).andReturn("HelloWorld123");
		
		EasyMock.replay(impl);
		Assert.assertEquals("HelloWorld123", impl.say());
		EasyMock.verify(impl);
		
	}

}
=======
package foo;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;

public class HelloServiceImplTest {

	@Test
	public void testSay() {
		HelloServiceImpl impl = EasyMock.createMock(HelloServiceImpl.class);
		EasyMock.expect(impl.say()).andReturn("HelloWorld123");
		
		EasyMock.replay(impl);
		Assert.assertEquals("HelloWorld123", impl.say());
		EasyMock.verify(impl);
		
	}

}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
