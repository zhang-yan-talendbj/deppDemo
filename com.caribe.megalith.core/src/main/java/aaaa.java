<<<<<<< HEAD
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Vector;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.pff.PSTException;
//import com.pff.PSTFile;
//import com.pff.PSTFolder;
//import com.pff.PSTMessage;
//
//public class aaaa {
//	private static Logger logger = LoggerFactory.getLogger(aaaa.class);
//
//	public static void main(String[] args) throws InterruptedException {
//		logger.warn("aaa");
//		// log.info("last analyze time "+new Date());
//		// System.out.println(new Date().getTime());
//		// Thread.sleep(1000);
//		// System.out.println(new Date().getTime());
//		System.out.println(new Date());
//		new aaaa("test-pst/test.pst");
//		try {
//			InputStream input = new FileInputStream(new File("abc"));
//		} catch (FileNotFoundException e) {
//
//			logger.warn("", e);
//			e.printStackTrace();
//		}
//
//		// System.out.println("re:16".contains("16"));
//	}
//
//	public aaaa(String filename) {
//		try {
//			PSTFile pstFile = new PSTFile(filename);
//			System.out.println(pstFile.getMessageStore().getDisplayName());
//			processFolder(pstFile.getRootFolder());
//		} catch (Exception err) {
//			err.printStackTrace();
//		}
//	}
//
//	int depth = -1;
//
//	public void processFolder(PSTFolder folder) throws PSTException,
//			java.io.IOException {
//
//		depth++;
//		// the root folder doesn't have a display name
//		if (depth > 0) {
//			printDepth();
//			System.out.println(folder.getDisplayName());
//		}
//
//		// go through the folders...
//		if (folder.hasSubfolders()) {
//			Vector<PSTFolder> childFolders = folder.getSubFolders();
//			for (PSTFolder childFolder : childFolders) {
//				processFolder(childFolder);
//				if (childFolder.getDisplayName().equals("Inbox")) {
//					break;
//				}
//			}
//		}
//		List list = new ArrayList();
//		// and now the emails for this folder
//		if (folder.getContentCount() > 0) {
//			depth++;
//			PSTMessage email = (PSTMessage) folder.getNextChild();
//			Vector<PSTFolder> messages = folder.getSubFolders();
//			while (email != null) {
//				// printDepth();
//				if (email.getSubject().equals("RE: 123123aa")) {
//					System.out.println("Email: " + email.getSubject());
//					System.out.println(email.getClientSubmitTime());
//					System.out.println(email.getMessageDeliveryTime());
//					System.out.println(email.getTaskDueDate());
//					System.out.println(email.getTaskStartDate());
//					System.out.println(new Date());
//					list.add(email.getSubject());
//				}
//				email = (PSTMessage) folder.getNextChild();
//			}
//			depth--;
//		}
//		depth--;
//	}
//
//	public void printDepth() {
//		for (int x = 0; x < depth - 1; x++) {
//			System.out.print(" | ");
//		}
//		System.out.print(" |- ");
//	}
//}
=======
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Vector;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.pff.PSTException;
//import com.pff.PSTFile;
//import com.pff.PSTFolder;
//import com.pff.PSTMessage;
//
//public class aaaa {
//	private static Logger logger = LoggerFactory.getLogger(aaaa.class);
//
//	public static void main(String[] args) throws InterruptedException {
//		logger.warn("aaa");
//		// log.info("last analyze time "+new Date());
//		// System.out.println(new Date().getTime());
//		// Thread.sleep(1000);
//		// System.out.println(new Date().getTime());
//		System.out.println(new Date());
//		new aaaa("test-pst/test.pst");
//		try {
//			InputStream input = new FileInputStream(new File("abc"));
//		} catch (FileNotFoundException e) {
//
//			logger.warn("", e);
//			e.printStackTrace();
//		}
//
//		// System.out.println("re:16".contains("16"));
//	}
//
//	public aaaa(String filename) {
//		try {
//			PSTFile pstFile = new PSTFile(filename);
//			System.out.println(pstFile.getMessageStore().getDisplayName());
//			processFolder(pstFile.getRootFolder());
//		} catch (Exception err) {
//			err.printStackTrace();
//		}
//	}
//
//	int depth = -1;
//
//	public void processFolder(PSTFolder folder) throws PSTException,
//			java.io.IOException {
//
//		depth++;
//		// the root folder doesn't have a display name
//		if (depth > 0) {
//			printDepth();
//			System.out.println(folder.getDisplayName());
//		}
//
//		// go through the folders...
//		if (folder.hasSubfolders()) {
//			Vector<PSTFolder> childFolders = folder.getSubFolders();
//			for (PSTFolder childFolder : childFolders) {
//				processFolder(childFolder);
//				if (childFolder.getDisplayName().equals("Inbox")) {
//					break;
//				}
//			}
//		}
//		List list = new ArrayList();
//		// and now the emails for this folder
//		if (folder.getContentCount() > 0) {
//			depth++;
//			PSTMessage email = (PSTMessage) folder.getNextChild();
//			Vector<PSTFolder> messages = folder.getSubFolders();
//			while (email != null) {
//				// printDepth();
//				if (email.getSubject().equals("RE: 123123aa")) {
//					System.out.println("Email: " + email.getSubject());
//					System.out.println(email.getClientSubmitTime());
//					System.out.println(email.getMessageDeliveryTime());
//					System.out.println(email.getTaskDueDate());
//					System.out.println(email.getTaskStartDate());
//					System.out.println(new Date());
//					list.add(email.getSubject());
//				}
//				email = (PSTMessage) folder.getNextChild();
//			}
//			depth--;
//		}
//		depth--;
//	}
//
//	public void printDepth() {
//		for (int x = 0; x < depth - 1; x++) {
//			System.out.print(" | ");
//		}
//		System.out.print(" |- ");
//	}
//}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
