package svn;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class SVNCommitFileHelper {

	public static void main(String[] args) throws IOException {

		String str = "E:/bruce/aia/workspace/May/ITPC/Claims(Rehab)/svn/";

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				SVNCommitFileHelper.class.getResourceAsStream("files")));
		BufferedReader excludedFileReader = new BufferedReader(new InputStreamReader(
				SVNCommitFileHelper.class.getResourceAsStream("excluded_files")));
		String fileName = null;
		List<String> excludedFileList = new LinkedList<String>();
		while ((fileName = excludedFileReader.readLine()) != null) {
			excludedFileList.add(fileName);
		}

		while ((fileName = reader.readLine()) != null) {
			File file = new File(str + fileName);
			System.out.println();
			if (!excludedFileList.contains(fileName)) {
				String dirsName = "2012-07-17";
				FileUtils.copyFile(file, new File("d:/tmp/" + dirsName + "/" + fileName));
			}
		}

	}
}
