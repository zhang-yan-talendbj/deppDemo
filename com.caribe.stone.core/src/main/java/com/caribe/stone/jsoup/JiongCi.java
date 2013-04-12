package com.caribe.stone.jsoup;

import java.util.LinkedList;
import java.util.List;

public class JiongCi {

	public static final String flag = "囧";
	List<JiongCiExplain> explainList = new LinkedList<JiongCiExplain>();

	public List<JiongCiExplain> getExplainList() {
		return explainList;
	}

	public void setExplainList(List<JiongCiExplain> list) {
		this.explainList = list;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getPng() {
		return png;
	}

	public void setPng(String png) {
		this.png = png;
	}

	private String word;
	private String png;

	public String getContent() {
		StringBuffer sb = new StringBuffer();
		if (getPng() != null) {
			sb.append("<div><img src=\"").append(getPng()).append("\" /></div>").append("<br>");
		}
		if(explainList!=null){
			for (JiongCiExplain e:explainList) {
				sb.append("<div>").append(e.getExplain()).append("</div><br>");
				sb.append("<div><img src=\"").append(e.getImg()).append("\" /></div><br>");
				// sb.append("<img src=\"").append(imgName).append("\" />");
			}
		}
		sb.append(flag);
		return sb.toString();
	}
}
