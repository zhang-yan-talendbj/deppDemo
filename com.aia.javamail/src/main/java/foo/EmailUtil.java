package foo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class EmailUtil 
{
	private EmailUtil(){
	}
	/**
	    * "send" method to send the message.
	    */
	  public static void send(String smtpServer, String from, String to
	   , String subject, String body)
	  {
	    try
	    {
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
	      System.out.println("Message sent OK.");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	  }
	/** Send email with authenticator
	 * @param mailauth
	 * @param prop
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 */
	public static void sendWithAuth(Authenticator mailauth, Properties prop, String from, String to, String subject, Object content) {
			Session session = Session.getInstance(prop,
					(Authenticator) mailauth);
			MimeMessage message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(from));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(subject);
				message.setContent(content, "text/plain");
				message.setSentDate(new Date());
				Transport.send(message, message.getAllRecipients());
				System.out.println("Auth Message sent OK.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	/** this method only for demo
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return email;
	}
}
