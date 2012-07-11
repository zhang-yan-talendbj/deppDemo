package moinmoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;

public class CopySelectedFile {

	public static void main(String[] args) throws IOException {

		String str="E:/bruce/aia/workspace/June/claimsreporting/ITPC/";
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(CopySelectedFile.class.getResourceAsStream("files")));
		String fileName=null;
		while((fileName=reader.readLine())!=null){
			File file = new File(str+fileName);
			System.out.println(file.exists());
			FileUtils.copyFile(file, new File("d:/tmp/2012-06-29/"+fileName));
		}
	
	}
}
