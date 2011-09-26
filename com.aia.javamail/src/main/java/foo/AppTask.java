package foo;

import java.util.TimerTask;

public class AppTask extends TimerTask{

	public void run() {
		PropertiesUtils.loadProperties("src/main/resource/config.properties");
		PstUtils.readAttchmentPST(PropertiesUtils.getProperty("pstPath"));
	}
}
