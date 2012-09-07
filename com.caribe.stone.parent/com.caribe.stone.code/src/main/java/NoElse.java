/**
 * 拒绝使用else关键字
 * 
 * @author bsnpbag
 * 
 */
public class NoElse {
	private void useElse(Object foo, Object bar) {
		if (bar == foo) {
			doSomething();
		} else {
			doAnotherthing();
		}

	}

	private void noElse1(Object foo, Object bar) {
		if (bar == foo) {
			doSomething();
			return;
		}
		doAnotherthing();
	}

	private int noElse2(Object foo, Object bar) {
		if (bar == foo) {
			return 1;
		}
		return 2;
	}

	private int noElse3(Object foo, Object bar) {
		return bar == foo ? 1 : 2;
	}

	private void doSomething() {
		// TODO Auto-generated method stub

	}

	private void doAnotherthing() {
		// TODO Auto-generated method stub

	}
}
