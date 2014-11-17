package com.caribe.stone.anki;

public class Note {
	private long id;
	private String word;
	private String content;
	private String tags;

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	private boolean isChange = false;

	private String example;
	private String phonetic;
	private String front;
	private String back;
	private int fieldCount;

	private static final Character US = '';
	private Long deckId;
	private IcibaVoiceService icibaVoiceService;
	private ExplainService explainService;

	public Note(String word) {
		this.word = word;
	}

	public Note() {
	}

	public String getFilePath(String mediaPath) {
		return mediaPath + getWord() + "-us" + ".mp3";
	}

	public boolean needToAddVoice() {
		return getFieldCount() >= 3 && getWord().indexOf(" ") < 0 && getWord().indexOf("-") < 0;
	}

	public boolean needToUpdatePhonetic() {
		return getFields() >= 1 && getWord().indexOf(" ") < 0 && getWord().indexOf("-") < 0 && hasntPhonetic();
	}

	public boolean needToUpdateExplain() {
		// return false;
		return getFields() >= 1 && getWord().indexOf(" ") < 0 && getWord().indexOf("-") < 0 && hasntExplain();
	}

	public boolean hasntExplain() {
		return getBack() == null || getBack().length() == 0 || getBack().replace(" ", "").length() == 0;
	}

	public boolean hasntPhonetic() {
		// return true;
		return getPhonetic() == null || getPhonetic().length() == 0 || getPhonetic().replace(" ", "").length() == 0;
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
			if (getFields() >= 3) {
				buf.append($(front)).append(US);
				buf.append($(phonetic)).append(US);
				buf.append($(back)).append(US);
			}else{
				System.out.println(this.front+"--------error-------");
			}
			if (getFields() == 4) {
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

	public Long getDeckId() {
		return deckId;
	}

	public void setDeckId(Long deckId) {
		this.deckId = deckId;
	}

	public boolean needToUpdateIndustry( ExplainService explainService) {
		this.explainService=explainService;
        if("null".equals(example)) {
            this.example=null;
        }
		if (this.example == null || this.example.length() == 0) {
			return true;
		}
		if(tags!=null){
			String[] split = tags.split(" ");
			if(split!=null){
				for (String tag : split) {
					if("marked".equals(tag)){
						this.back=null;
						this.example=null;
						return true;
					}
				}
			}
		}
		
//		return aa();
		return false;
	}

	private boolean aa() {
        if(back==null || back.trim().length()==0){
            return true;
        }
		if (back != null && !back.startsWith("<div class=\"caption\">")) {
			if (!back.startsWith("<div class=\"wt-container\">") && !back.startsWith("<div class=\"trans-container\">") && !back.startsWith("<section class=\"def-pbk\">")) {
				// System.out.println(note.getFront());              <div class="trans-container">
                System.out.println(word);
                String collinsExplain = explainService.getCollinsExplain(word);
				if (collinsExplain != null && collinsExplain.trim().length() > 0) {
					setBack(collinsExplain);
					setExample(null);
					return true;
				}
			}
		}
		return false;
	}

	public void expandExample(IcibaVoiceService icibaVoiceService) {
		setChange(true);
		StringBuffer buf = new StringBuffer(example==null?"":example);
		String industry = icibaVoiceService.getIndustry(this.word);
		example = buf.append(industry).toString();
	}
}
