package com.caribe.stone.anki;

public class Note {
	private long id;
	private String word;
	private String content;

	private boolean isChange = false;

	private String example;
	private String phonetic;
	private String front;
	private String back;
	private int fieldCount;

	private static final Character US = '';

	public Note(String word) {
		this.word = word;
	}

	public Note() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getContent() {
		if (isChange) {
			StringBuffer buf = new StringBuffer();
			if (fieldCount >= 3) {
				buf.append($(front)).append(US);
				buf.append($(phonetic)).append(US);
				buf.append($(back)).append(US);
			}
			if (fieldCount == 4) {
				buf.append($(example)).append(US);
			}
			buf.deleteCharAt(buf.length() - 1);
			return buf.toString();
		} else {
			return content;
		}
	}

	private String $(String str) {
		return str == null ? "" : str;
	}

	public void setContent(String content) {
		this.content = content;
		setChange(true);

		String[] fields = content.split(US.toString());
		int length = fields.length;
		if (length > 0) {
			this.front = fields[0];
		}
		if (length > 1) {
			this.phonetic = fields[1];
		}
		if (length > 2) {
			this.back = fields[2];
		}
		if (length > 3) {
			this.example = fields[3];
		}
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", word=" + word + ", content=" + content + "]";
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
		setChange(true);
	}

	public String getPhonetic() {
		return phonetic;
	}

	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
		setChange(true);
	}

	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
		setChange(true);
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
		setChange(true);
	}

	public int getFields() {
		if (fieldCount > 0) {
			return fieldCount;
		}
		int length = 0;
		if (content != null && content.indexOf(US) > 0) {
			for (int i = 0; i < content.length(); i++) {
				if (US.equals(content.charAt(i))) {
					length++;
				}
			}
			fieldCount = length + 1;
			return fieldCount;

		}
		return length;
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	public int getFieldCount() {
		return fieldCount;
	}

	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}
}
