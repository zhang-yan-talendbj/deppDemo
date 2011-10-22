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
