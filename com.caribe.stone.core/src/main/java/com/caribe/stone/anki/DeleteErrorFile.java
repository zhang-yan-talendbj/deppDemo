package com.caribe.stone.anki;

import java.io.File;

public class DeleteErrorFile {
public static void main(String[] args) {
	File file = new File("/Users/thinkdeeply/Documents/Anki/User 1/collection.media");
	File[] list = file.listFiles();
	for (File f : list) {
		if(f.getName().indexOf("-yd.mp3")>0){
			System.out.println(f);
			f.delete();
		}
	}
}
}
