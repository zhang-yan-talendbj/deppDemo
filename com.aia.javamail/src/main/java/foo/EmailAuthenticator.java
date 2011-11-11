package foo;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator {
	private String username = null;

	private String userPW = null;

	void setUsername(String username) {
		this.username = username;
	}

	void setUserpass(String userpass) {
		this.userPW = userpass;
	}

	public EmailAuthenticator(String username, String userpass) {
		super();
		setUsername(username);
		setUserpass(userpass);
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, userPW);
	}
}
