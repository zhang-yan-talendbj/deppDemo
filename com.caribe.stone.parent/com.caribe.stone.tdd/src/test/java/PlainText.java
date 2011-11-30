public class PlainText implements Segment {

	private String text;

	public PlainText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object other) {
		return text.equals(((PlainText) other).text);
	}
}
