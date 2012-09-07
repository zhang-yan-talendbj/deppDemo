package svn;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class SVNCommitFileHelper {

	public static void main(String[] args) throws IOException {

		String str = "d:/bruce/aia/workspace/May/ITPC/Claims(Rehab)/branch/br_reopen_status_git/br_reopen_status/";

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				SVNCommitFileHelper.class.getResourceAsStream("files")));
		BufferedReader excludedFileReader = new BufferedReader(new InputStreamReader(
				SVNCommitFileHelper.class.getResourceAsStream("excluded_files")));
		String fileName = null;
		List<String> excludedFileList = new LinkedList<String>();
		while ((fileName = excludedFileReader.readLine()) != null) {
			excludedFileList.add(fileName);
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String dirsName = format.format(new Date());
		
		while ((fileName = reader.readLine()) != null) {
			File file = new File(str + fileName);
			System.out.println();
			if (!excludedFileList.contains(fileName)) {
				FileUtils.copyFile(file, new File("d:/tmp/" + dirsName + "/" + fileName));
			}
		}
		System.out.println("d:/tmp/" + dirsName + "/");

	}
}
