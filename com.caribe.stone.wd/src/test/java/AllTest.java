import java.lang.reflect.Field;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.caribe.stone.junit.SeleniumScreenShotRunner;
import com.caribe.stone.wd.AppTest;

public class AllTest {
	public static void main(String[] args) throws SecurityException, NoSuchFieldException {
		Field declaredFields = AppTest.class.getSuperclass().getDeclaredField("wd");
		System.out.println(declaredFields.getName());
	}
}
