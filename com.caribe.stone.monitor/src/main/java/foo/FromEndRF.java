package foo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FromEndRF {
	public static void main(String args[]) throws InterruptedException {
		RandomAccessFile rf = null;
		try {
			
			String filePath = "e:/bruce/aia/depp/maven/source/com.caribe.stone.monitor/logs/monitor.log";
			rf = new RandomAccessFile(filePath, "r");
			long len = rf.length();
			long start = rf.getFilePointer();
			long nextend = start + len - 1;
			String line;
			rf.seek(nextend);
			int c = -1;
			while(true){
				Thread.sleep(1000);
			while (nextend > start) {
				c = rf.read();
				if (c == '\n' || c == '\r') {
					line = rf.readLine();
					if (line == null) {// 处理文件末尾是空行这种情况
						nextend--;
						rf.seek(nextend);
						continue;
					}
					System.out.println(line);
					nextend--;
				}
				nextend--;
				rf.seek(nextend);
				if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
					System.out.println(rf.readLine());
				}
			}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}