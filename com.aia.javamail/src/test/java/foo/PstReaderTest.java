<<<<<<< HEAD
package foo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.pff.PSTMessage;

public class PstReaderTest {

	@Test
	public void testReadAttchmentPSTFromTime() {
	List<PSTMessage> list = PstReader.readAttchmentPSTFromTime("src/test/resource/test.pst", "test1");
	assertEquals(1, list.size());
	assertEquals("test1", list.get(0).getSubject());
	assertEquals(2, list.get(0).getNumberOfAttachments());
	}
}
=======
package foo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.pff.PSTMessage;

public class PstReaderTest {

	@Test
	public void testReadAttchmentPSTFromTime() {
	List<PSTMessage> list = PstReader.readAttchmentPSTFromTime("src/test/resource/test.pst", "test1");
	assertEquals(1, list.size());
	assertEquals("test1", list.get(0).getSubject());
	assertEquals(2, list.get(0).getNumberOfAttachments());
	}
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
