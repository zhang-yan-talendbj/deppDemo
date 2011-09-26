package foo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

public class ReadMessage {
	public void readMessage() throws Exception {
		String host = "localmailserver.com";
		String username = "test";
		String password = "test";

		//String attachmentName = "Payments Schedule.pdf";

		// Create empty properties
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		// Get session
		Session session = Session.getDefaultInstance(props, null);

		// Get the store
		Store store = session.getStore("pop3");
		store.connect(host, username, password);

		// Get folder
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.HOLDS_MESSAGES);

		// Get directory
		Message messages[] = folder.getMessages();

		for (int i = 0, n = messages.length; i < n; i++) {
			//if (messages[i].getSubject().indexOf(testSubject) != -1) {
				System.out.println(i + ": " + messages[i].getFrom()[0] + "\t"
						+ messages[i].getSubject());
				//saveAttachment(attachmentName, messages[i]);
			//	break;
			//}
			// System.out.println(i + ": " + messages[i].getFrom()[0] + "\t" +
			// ">>>>>>" + messages[i].getSubject() + "<<<<<<");
		}

		// Close connection
		folder.close(false);
		store.close();
	}

	private void saveAttachment(String attachmentName, Message message)
			throws Exception {
		Multipart multipart = (Multipart) message.getContent();

		for (int i = 0, n = multipart.getCount(); i < n; i++) {
			Part part = multipart.getBodyPart(i);
			String disposition = part.getDisposition();

			if (((disposition != null) && ((disposition.equals(Part.ATTACHMENT) || (disposition
					.equals(Part.INLINE)))))) {
				if (attachmentName.equalsIgnoreCase(part.getFileName())) {
					System.out.println("Start saving attachment:");
					saveFile(part.getFileName(), part.getInputStream());
				}
			}
		}
	}

	private void saveFile(String filename, InputStream in) throws Exception {
		// from saveFile()
//		File file = new File("C:\\" + filename);
//		for (int i = 0; file.exists(); i++) {
//			file = new File(filename + i);
//		}

		String newFileName = "d:\\" + filename.replaceAll(" ", "_");
		File f2 = new File(newFileName);

		// For Overwrite the file.
		OutputStream out = new FileOutputStream(f2);

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
		System.out.println("Attachment file saved.");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ReadMessage receiver = new ReadMessage();
		receiver.readMessage();
	}
}
