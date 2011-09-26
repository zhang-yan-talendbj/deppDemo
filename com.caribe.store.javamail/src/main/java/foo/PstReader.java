package foo;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pff.PSTAttachment;
import com.pff.PSTException;
import com.pff.PSTFile;
import com.pff.PSTFolder;
import com.pff.PSTMessage;

public class PstReader {
	private static Logger log = LoggerFactory.getLogger(PstReader.class);
	public static List<PSTMessage> readAttchmentPSTFromTime(String pstFilePath,
			String subjectFilter) {

		List<PSTMessage> list = new LinkedList<PSTMessage>();
		try {
			PSTFile pstFile = new PSTFile(pstFilePath);
			long time = getLastTime();
			log.info("last analyze time " + new Date(time));
			processFolder(pstFile.getRootFolder(), list, time, subjectFilter);
			
		} catch (Exception e) {
			log.warn("IOException", e);
			e.printStackTrace();
		}
		return list;
	}

	public static void setLastTime(long date) {
		File flagFile = new File("emailFlag/emailFlag.tmp");
		flagFile.setLastModified(date);
	}

	private static long getLastTime() {
		File flagFile = new File("emailFlag/emailFlag.tmp");
		if (!flagFile.exists()) {
			try {
				flagFile.createNewFile();
			} catch (IOException e) {
				log.warn("IOException", e);
				e.printStackTrace();
			}
		}
		return flagFile.lastModified();
	}

	private static List<PSTMessage> processFolder(PSTFolder rootFolder,
			List<PSTMessage> list, long lastTime, String subjectFilter)
			throws PSTException, java.io.IOException {
		// the root folder doesn't have a display name
		// go through the folders...
		if (rootFolder.hasSubfolders()) {
			Vector<PSTFolder> childFolders = rootFolder.getSubFolders();
			for (PSTFolder childFolder : childFolders) {
				processFolder(childFolder, list, lastTime, subjectFilter);
				if (childFolder.getDisplayName().equals("Inbox")) {
					log.info("Inbox total:" + childFolder.getContentCount());
					break;
				}
			}
		}

		// and now the emails for this folder
		int totalCount = rootFolder.getContentCount();
		if (totalCount > 0 && rootFolder.getDisplayName().equals("Inbox")) {
			// folder.moveChildCursorTo(200);
			PSTMessage email = (PSTMessage) rootFolder.getNextChild();
			// messages=rootFolder.getSubFolders();

			while (email != null) {
				long receiveTime = email.getMessageDeliveryTime().getTime();
				// receive time > lasttime ==>new message

				// log.info("email delivery time :"+email.getMessageDeliveryTime());
				// log.info("last time"+new Date(lastTime));
				// log.info(email.getSubject());
				// log.info(receiveTime>lastTime);
				// log.info(email.getSubject().contains(subjectFilter));
				if (receiveTime > lastTime
						&& email.getSubject().contains(subjectFilter)) {
					if (email.getNumberOfAttachments() > 0) {
						list.add(email);
						log.info("add email:" + email.getSubject());
					}
				}
				email = (PSTMessage) rootFolder.getNextChild();
			}
		}
		return list;
	}

	public static List<PSTAttachment> getAttchments(PSTMessage email)
			throws PSTException {
		List<PSTAttachment> list = new LinkedList<PSTAttachment>();
		int numberOfAttachments = email.getNumberOfAttachments();
		for (int x = 0; x < numberOfAttachments; x++) {
			PSTAttachment attach = null;
			try {
				attach = email.getAttachment(x);
				// both long and short filenames can be used for attachments
				String filename = attach.getLongFilename();
				if (filename.equals("")) {
					filename = attach.getFilename();
				}
				if (filename.endsWith(".pdf")) {
					list.add(attach);
				}
			} catch (IOException e) {
				log.warn("IOException", e);
				e.printStackTrace();
			}
		}
		return list;
	}
}
