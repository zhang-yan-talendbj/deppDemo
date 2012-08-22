package com.caribe.stone.thread.balk;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Data {
	private String fileName;
	private String content;
	private boolean flag;

	public Data(String fileName, String content) {
		this.fileName = fileName;
		this.content = content;
	}
	
	public synchronized void change(String content){
		this.content=content;
		this.flag=true;
	}
	public synchronized void save(){
		if(!flag){
			return ;
		}
		dosave();
		flag=false;
	}

	private void dosave() {
		try {
			PrintWriter pw=new PrintWriter(fileName);
			pw.write(content);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
