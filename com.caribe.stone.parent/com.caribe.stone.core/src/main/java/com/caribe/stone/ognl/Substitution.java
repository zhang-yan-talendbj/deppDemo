/*
 * Created on 13/12/2005
 */
package com.caribe.stone.ognl;

import java.util.Map;
import java.util.logging.Logger;

import ognl.Ognl;
import ognl.OgnlException;

/**
 * @author TomH
 */
public abstract class Substitution {
	public static final String TOKEN_SURROUNDS = "##";
	private static final Logger LOG = Logger.getLogger(Substitution.class.getName());
	private Object parsedOgnlExpression;

	public Substitution(String ognl) {
		try {
			this.parsedOgnlExpression = Ognl.parseExpression(ognl);
		} catch (OgnlException e) {
			LOG.info("Could not parse ognl expression for standard letter template" + e);
			throw new IllegalArgumentException("Could not parse ognl expression for standard letter template:" + e);
		}
	}

	public String getMissingString(String keyword) {
		return TOKEN_SURROUNDS + "MISSING_" + keyword.toUpperCase() + TOKEN_SURROUNDS;
	}

	public Object getParsedOgnlExpression() {
		return parsedOgnlExpression;
	}

	/** result is a string, input is a value collection
	 * @param result
	 * @param input
	 * @return
	 * @throws OgnlException
	 */
	public abstract String subsitute(String result, Map input) throws OgnlException;

	protected Object getValue(Map<String,String> map) throws OgnlException {
		Object result = Ognl.getValue(getParsedOgnlExpression(), map);
		if (result == null) {
			return null;
		} else {
			return result;
		}
	}

	protected String escapeXML(String str) {
		// probably exists elsewhere, but the time to take and the time to write
		// ...
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < str.length(); ++i) {
			char c = str.charAt(i);
			if (c == '&') {
				result.append("&amp;");
			} else if (c == '<') {
				result.append("&lt;");
			} else if (c == '>') {
				result.append("&gt;");
			} else {
				result.append(c);
			}
		}
		return result.toString();
	}
}
