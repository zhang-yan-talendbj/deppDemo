package moinmoin;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailDemo {

	public static void main(String[] args) throws MessagingException {
		Session session = getSession();
		
//		MimeMessage message = new MimeMessage(session);
//		message.setSubject("Hello");
//		message.setText("Word");
//		Address address=new InternetAddress("Bruce-Y.Zhang@aia.com");
//		message.setFrom(address);
//		message.setRecipient(Message.RecipientType.TO, address);
//		
//		Transport.send(message);
//		
//		System.out.println("success!");
//		
//		MimeMessage forward = new MimeMessage(session);
//		forward.setSubject("Fwd: bruce");
//		forward.setFrom(address);
//		forward.setRecipient(Message.RecipientType.TO, address);
//		
//		MimeBodyPart bodyPart = new MimeBodyPart();
//		bodyPart.setText("Here you go with the .....");
//		
//		MimeMultipart mimeMultipart = new MimeMultipart();
//		mimeMultipart.addBodyPart(bodyPart);
//		
//		bodyPart = new MimeBodyPart();
//		bodyPart.setDataHandler(message.getDataHandler());
//		mimeMultipart.addBodyPart(bodyPart);
//		
//		forward.setContent(mimeMultipart);
//		
//		Transport.send(forward);
		
		// Create the message to forward
		Message forward = new MimeMessage(session);

		// Fill in header
		forward.setSubject("Fwd: test");
		String from="Bruce-Y.Zhang@aia.com";
		forward.setFrom(new InternetAddress(from));
		String to="Bruce-Y.Zhang@aia.com";
		forward.addRecipient(Message.RecipientType.TO,
		    new InternetAddress(to));

		// Create your new message part
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(
		    "Here you go with the original message:/n/n");

		// Create a multi-part to combine the parts
		Multipart multipart = new MimeMultipart();
//		multipart.addBodyPart(messageBodyPart);

		// Create and fill part for the forwarded content
		MimeBodyPart attachment = new MimeBodyPart();
		attachment.setDataHandler(new DataHandler(new FileDataSource("c:/Documents and Settings/bsnpbag/My Documents/Downloads/linkTest.html")));
		MimeBodyPart attachment2 = new MimeBodyPart();
		attachment2.setDataHandler(new DataHandler(new FileDataSource("c:/Documents and Settings/bsnpbag/My Documents/Downloads/linkTest2.csv")));
		attachment2.setFileName("a.csv");
		// Add part to multi part
		multipart.addBodyPart(attachment);
		multipart.addBodyPart(attachment2);

		// Associate multi-part with message
		forward.setContent(multipart);

		// Send message
		Transport.send(forward);
		System.out.println(123);
	}

	private static void recieveMail(Session session) throws NoSuchProviderException, MessagingException {
		Store store = session.getStore("pop3");
		store.connect("Bruce-Y.Zhang@aia.com", "qepucpgf66K1");
		System.out.println(store.getURLName());
	}

	private static Session getSession() {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "melsmtp1");
		
		Authenticator authenticator=new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("Bruce-Y.Zhang@aia.com", "qepucpgf66K1");
			}
		};
		Session session = Session.getDefaultInstance(props,authenticator);
		return session;
	}
}
