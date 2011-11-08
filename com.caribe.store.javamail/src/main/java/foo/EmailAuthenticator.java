<<<<<<< HEAD
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
=======
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
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
