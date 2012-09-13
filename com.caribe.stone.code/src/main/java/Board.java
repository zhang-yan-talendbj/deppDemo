/**方法只使用一级缩进
 * @author bsnpbag
 *
 */
public class Board {
	private String boardBad() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				buffer.append("test");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	private String board() {
		StringBuffer buffer = new StringBuffer();
		collectRows(buffer);
		return buffer.toString();
	}

	private void collectRows(StringBuffer buffer) {
		for (int i = 0; i < 10; i++) {
			collectRow(buffer);
		}
	}

	private void collectRow(StringBuffer buffer) {
		for (int j = 0; j < 10; j++) {
			buffer.append("test");
		}
		buffer.append("\n");
	}
}
