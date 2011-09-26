package foo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Authenticator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pff.PSTAttachment;
import com.pff.PSTException;
import com.pff.PSTMessage;

import foo.util.EmailUtil;
import foo.util.PropertiesUtils;


public class AppTask extends TimerTask {
	private static Logger log = LoggerFactory.getLogger(AppTask.class);
	public void run() {
		PropertiesUtils.loadProperties("src/main/resource/config.properties");
		String pstPath = PropertiesUtils.getProperty("pstPath");
		String username = PropertiesUtils.getProperty("mail.username");
		String password = PropertiesUtils.getProperty("mail.password");
		String from = PropertiesUtils.getProperty("mail.from");
		String subject = "Application Daily Report";
		Properties properties = PropertiesUtils.getProperties();
		Authenticator auth = new EmailAuthenticator(username, password);
		
		
		String subjectFilter=PropertiesUtils.getProperty("mail.subjectFilter");;
		// get messages from pst file
		List<PSTMessage> messageList = PstReader.readAttchmentPSTFromTime(pstPath,subjectFilter);

		
		log.info("new message count:"+messageList.size());
		for (PSTMessage pstMessage : messageList) {
			try {
				// get attachments from message
				List<PSTAttachment> attachments = PstReader
						.getAttchments(pstMessage);
				
				log.info(pstMessage.getSubject()+" has attachments count:"+attachments.size());
				for (PSTAttachment attachment : attachments) {
					InputStream input = null;
					try {
						input = attachment.getFileInputStream();
						// read pdf attachemnt
						List<Map<String, String>> emailList = PDFReader
								.readPdfFolder(input);
						for (Map<String, String> map : emailList) {
							EmailUtil.sendWithAuth(auth, properties, from,
									EmailUtil.getEmailAddress(map.get("Email address")), subject,
									map.get("content"));
						}
					} catch (IOException e) {
						log.warn("IOException", e);
						e.printStackTrace();
					} finally {
						if (input != null) {
							try {
								input.close();
							} catch (IOException e) {
								log.warn("IOException", e);
								e.printStackTrace();
							}
						}
					}
				}
			} catch (PSTException e) {
				log.warn("IOException", e);
				e.printStackTrace();
			}
			if (!messageList.isEmpty()) {
				PstReader.setLastTime();
			}
		}
	}
}
