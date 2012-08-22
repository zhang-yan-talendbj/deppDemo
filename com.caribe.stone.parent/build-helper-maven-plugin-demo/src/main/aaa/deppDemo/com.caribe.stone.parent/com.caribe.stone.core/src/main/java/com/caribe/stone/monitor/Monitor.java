package com.caribe.stone.monitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Hello world!
 *
 */
public class Monitor 
{
    public static void main( String[] args ) throws Exception
    {
    	String filePath="e:/bruce/aia/depp/maven/source/com.caribe.stone.monitor/logs/monitor.log";
    	File file = new File(filePath);
		FileInputStream is = new FileInputStream(file);
		Long time = 0L;
		Integer lineNumber=0;
    	while(true){
    		Thread.sleep(3000);
    		
    		System.out.println(FileUtils.isFileNewer( new File(filePath),file));
//    		FileUtils.isSymlink(file)
//    		if(time!=file.lastModified()){
//    			List<String> list = IOUtils.readLines(is);
//    			int tmp=list.size();
//    			System.out.println("list size:"+list.size());
//    			list=list.subList(lineNumber, list.size());
//    			for (String string : list) {
//					System.out.println(string);
//				}
//    			lineNumber=tmp;
//    			time=file.lastModified();
//    			System.out.println("lastModified time:"+time);
//    		}else{
//    			System.out.println("file is same.");
//    			
//    		}
    	}
    	
    	
    }
}
