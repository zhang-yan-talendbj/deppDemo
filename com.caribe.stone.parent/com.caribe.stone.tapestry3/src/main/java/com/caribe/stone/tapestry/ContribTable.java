package com.caribe.stone.tapestry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tapestry.html.BasePage;

public abstract class ContribTable extends BasePage {
	public abstract Object getCurrentHistory();

	private List policyRuleHistory = new ArrayList();

	public List getPolicyRuleHistory() {
		policyRuleHistory.add(getObject());
		policyRuleHistory.add(getObject());
		policyRuleHistory.add(getObject());
		policyRuleHistory.add(getObject());
		return policyRuleHistory;
	}

	private HashMap getObject() {
		HashMap map = new HashMap();
		map.put("name", "zhang");
		map.put("age", "123");
		map.put("birthday", new Date());
		return map;
	}

	public SimpleDateFormat getFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf;
	}

	public void setPolicyRuleHistory(List policyRuleHistory) {
		this.policyRuleHistory = policyRuleHistory;
	}
}
