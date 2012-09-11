package com.caribe.stone.j2se.file;

import java.io.File;
import java.io.FileWriter;

import org.junit.Test;

public class FileTest {
	@Test
	public void testCompareFiles() throws Exception {
		File fileA = new File("a.txt");
		FileWriter writer = new FileWriter(fileA);
		writer.write("this is a test");
		writer.flush();
		writer.close();
		File fileB = new File("b.txt");
		FileWriter writerB = new FileWriter(fileB);
		writerB.write("this is a test");
		writerB.flush();
		writerB.close();
		
		System.out.println(fileA.equals(fileB));
		
		File fileC=new File("a.txt");
		
		System.out.println(fileA.equals(fileC));
		
		fileA.deleteOnExit();
		fileB.deleteOnExit();
		fileC.deleteOnExit();
		
		
	}
}
