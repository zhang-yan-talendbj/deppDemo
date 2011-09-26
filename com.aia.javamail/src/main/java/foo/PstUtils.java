package foo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Authenticator;

import com.pff.PSTAttachment;
import com.pff.PSTException;
import com.pff.PSTFile;
import com.pff.PSTFolder;
import com.pff.PSTMessage;

public class PstUtils {

	public static void readAttchmentPST(String pstFilePath) {
		Properties p = new Properties();
		Integer old = 0;
		try {
			p.load(new FileInputStream(new File("emailLog/emailLog.properties")));
			old = Integer.valueOf(p.getProperty("emailCursor"));
			ec.setCursor(old);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PSTFile pstFile = new PSTFile(pstFilePath);
//			System.out.println(pstFile.getMessageStore().getDisplayName());
			processFolder(pstFile.getRootFolder());
		} catch (Exception err) {
			err.printStackTrace();
		}
		try {
			if (ec.getCursor() > old) {
				p.put("emailCursor", String.valueOf(ec.getCursor()));
				p.store(new FileOutputStream(new File(
						"emailLog/emailLog.properties")), "cursor");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static EmailCursor ec = new EmailCursor();

	private static void processFolder(PSTFolder folder) throws PSTException,
			java.io.IOException {
		// the root folder doesn't have a display name
		// go through the folders...
		if (folder.hasSubfolders()) {
			Vector<PSTFolder> childFolders = folder.getSubFolders();
			for (PSTFolder childFolder : childFolders) {
				processFolder(childFolder);
			}
		}

		// and now the emails for this folder
		int totalCount = folder.getContentCount();
		if (totalCount > 0 && totalCount > ec.getCursor()&&folder.getDisplayName().equals("Inbox")) {
			// folder.moveChildCursorTo(200);
			PSTMessage email = (PSTMessage) folder.getNextChild();

			while (email != null) {
				System.out.println(email.getSubject());
				ec.add();
				getAttchment(email);
				email = (PSTMessage) folder.getNextChild();
				if (ec.getCursor() == totalCount) {
					email = null;
				}
			}
		}}

	private static void getAttchment(PSTMessage email) throws PSTException 
			 {
		int numberOfAttachments = email.getNumberOfAttachments();
		for (int x = 0; x < numberOfAttachments; x++) {
			PSTAttachment attach=null;
			InputStream attachmentStream=null;
			try {
				attach = email.getAttachment(x);
				attachmentStream = attach.getFileInputStream();
			
			// both long and short filenames can be used for attachments
			String filename = attach.getLongFilename();
			if (filename.equals("")) {
				filename = attach.getFilename();
			}
			
			if (filename.endsWith(".pdf")) {
				List<Map<String, String>> list = PDFReader.readPdfFolder(attachmentStream);
				sendMail(list);
				attachmentStream.close();
			}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(attachmentStream!=null){
					try {
						attachmentStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static void sendMail(List<Map<String, String>> list) {
		if(list.size()>0){
			for (Map<String, String> map : list) {
				Authenticator auth = new EmailAuthenticator(
						PropertiesUtils.getProperty("mail.username"),
						PropertiesUtils.getProperty("mail.password"));
				EmailUtil.sendWithAuth(auth, PropertiesUtils.getProperties(),
						PropertiesUtils.getProperty("mail.from"),
						EmailUtil.getEmailAddress(map.get("Email address")),
						"Application Daily Report", map.get("content"));

				// EmailUtil.send(PropertiesUtils.getProperty("mail.smtp.host"),
				// PropertiesUtils.getProperty("mail.from"),
				// PropertiesUtils.getProperty("mail.username"),
				// "This is s subject.", "Hello~!");
			}
			}
	}

}

/** email cursor for pstfile
 * @author bsnpbag
 *
 */
class EmailCursor {
	private int cursor = 0;

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

	public void add() {
		cursor++;
	}

}