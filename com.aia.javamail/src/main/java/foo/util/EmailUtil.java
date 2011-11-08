<<<<<<< HEAD
package foo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * 
 */
public class EmailUtil {
	private EmailUtil() {
	}

	private static Logger log = LoggerFactory.getLogger(EmailUtil.class);

	/**
	 * "send" method to send the message.
	 */
	public static void send(String smtpServer, String from, String to,
			String subject, String body) {
		try {
			Properties props = System.getProperties();
			// -- Attaching to default Session, or we could start a new one --
			props.put("mail.smtp.host", smtpServer);
			Session session = Session.getDefaultInstance(props, null);
			// -- Create a new message --
			Message msg = new MimeMessage(session);
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));
			// -- We could include CC recipients too --
			// if (cc != null)
			// msg.setRecipients(Message.RecipientType.CC
			// ,InternetAddress.parse(cc, false));
			// -- Set the subject and body text --
			msg.setSubject(subject);
			msg.setText(body);
			// -- Set some other header information --
			msg.setHeader("X-Mailer", "LOTONtechEmail");
			msg.setSentDate(new Date());
			// -- Send the message --
			Transport.send(msg);
			log.info("Message sent OK.");
		} catch (Exception e) {
			log.warn("IOException", e);
			e.printStackTrace();
		}
	}

	/**
	 * Send email with authenticator
	 * 
	 * @param mailauth
	 * @param prop
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 */
	public static void sendWithAuth(Authenticator mailauth, Properties prop,
			String from, String to, String subject, Object content) {
		// log.info(from+"-->"+to);
		// log.info("message content:"+content);
		Session session = Session.getInstance(prop, (Authenticator) mailauth);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(subject);
			message.setContent(content, "text/plain");
			message.setSentDate(new Date());
			Transport.send(message, message.getAllRecipients());
			log.info("Auth Message sent OK.");
		} catch (Exception e) {
			log.warn("IOException", e);
		}
	}

	/**
	 * this method only for demo
	 * 
	 * @param string
	 * @return
	 */
	public static String getEmailAddress(String string) {
		String email = null;
		if (string != null) {
			Properties p = new Properties();
			// TODO Auto-generated method stub
			try {
				p.load(new FileInputStream(new File(
						"emailPath/email.properties")));
				email = p.getProperty(string.trim());
			} catch (FileNotFoundException e) {
				log.warn("IOException", e);
				e.printStackTrace();
			} catch (IOException e) {
				log.warn("IOException", e);
				e.printStackTrace();
			}
		}
		return email;
	}
}
=======
package foo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * 
 */
public class EmailUtil {
	private EmailUtil() {
	}

	private static Logger log = LoggerFactory.getLogger(EmailUtil.class);

	/**
	 * "send" method to send the message.
	 */
	public static void send(String smtpServer, String from, String to,
			String subject, String body) {
		try {
			Properties props = System.getProperties();
			// -- Attaching to default Session, or we could start a new one --
			props.put("mail.smtp.host", smtpServer);
			Session session = Session.getDefaultInstance(props, null);
			// -- Create a new message --
			Message msg = new MimeMessage(session);
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));
			// -- We could include CC recipients too --
			// if (cc != null)
			// msg.setRecipients(Message.RecipientType.CC
			// ,InternetAddress.parse(cc, false));
			// -- Set the subject and body text --
			msg.setSubject(subject);
			msg.setText(body);
			// -- Set some other header information --
			msg.setHeader("X-Mailer", "LOTONtechEmail");
			msg.setSentDate(new Date());
			// -- Send the message --
			Transport.send(msg);
			log.info("Message sent OK.");
		} catch (Exception e) {
			log.warn("IOException", e);
			e.printStackTrace();
		}
	}

	/**
	 * Send email with authenticator
	 * 
	 * @param mailauth
	 * @param prop
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 */
	public static void sendWithAuth(Authenticator mailauth, Properties prop,
			String from, String to, String subject, Object content) {
		// log.info(from+"-->"+to);
		// log.info("message content:"+content);
		Session session = Session.getInstance(prop, (Authenticator) mailauth);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(subject);
			message.setContent(content, "text/plain");
			message.setSentDate(new Date());
			Transport.send(message, message.getAllRecipients());
			log.info("Auth Message sent OK.");
		} catch (Exception e) {
			log.warn("IOException", e);
		}
	}

	/**
	 * this method only for demo
	 * 
	 * @param string
	 * @return
	 */
	public static String getEmailAddress(String string) {
		String email = null;
		if (string != null) {
			Properties p = new Properties();
			// TODO Auto-generated method stub
			try {
				p.load(new FileInputStream(new File(
						"emailPath/email.properties")));
				email = p.getProperty(string.trim());
			} catch (FileNotFoundException e) {
				log.warn("IOException", e);
				e.printStackTrace();
			} catch (IOException e) {
				log.warn("IOException", e);
				e.printStackTrace();
			}
		}
		return email;
	}
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
