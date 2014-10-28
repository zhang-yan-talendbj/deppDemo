package com.caribe.stone.j2se.clazz;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileClassLoader extends ClassLoader {
	public Class findClass(String name) {
		byte[] data = loadClassData(name);
		return defineClass(name, data, 0, data.length);
	}

	private byte[] loadClassData(String name) {
		FileInputStream fis = null;
		byte[] data = null;
		try {
			fis = new FileInputStream(new File("D:\\class\\" + name + ".class"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int ch = 0;
			while ((ch = fis.read()) != -1) {
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}