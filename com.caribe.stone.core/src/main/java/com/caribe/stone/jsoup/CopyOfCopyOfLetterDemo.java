package com.caribe.stone.jsoup;

import java.io.File;
import java.io.IOException;

import org.aspectj.util.FileUtil;

public class CopyOfCopyOfLetterDemo {
	public static void main(String[] args) throws IOException {
		System.out.println((int) 'z');
		String letterPath = "d:/english/AnkiWord/voice/letter/American/";
		for (int i = 97; i < 123; i++) {
			char i2 = (char) i;
			String x = "d:/english/voice/American/" + i2 + "/" + i2 + ".mp3";
			System.out.println(x);
			File x2 = new File(x);
			try {
				FileUtil.copyFile(x2, new File(letterPath+i2+".mp3"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
}
