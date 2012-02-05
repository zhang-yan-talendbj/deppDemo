package com.caribe.stone.tdd.template;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bsnpbag
 * 
 */
public class Template {
	private String templateText;
	private Map<String, String> variables;

	public Template(String string) {
		variables = new HashMap<String, String>();
		this.templateText = string;
	}

	public void set(String string, String string2) {
		this.variables.put(string, string2);
	}

	public String evaluate() {
		// String result = replaceVariables();
		// checkForMissingValue(result);
		TemplateParse t = new TemplateParse();
		List<String> segments = t.parse(templateText);
		return concatenate(segments);
	}

	public String concatenate(List<String> segments) {
		StringBuilder result = new StringBuilder();
		for (String seg : segments) {
			append(seg, result);
		}
		return result.toString();
	}

	private void append(String seg, StringBuilder result) {
		if (isVariable(seg)) {
			String var = seg.substring(2, seg.length() - 1);
			if (!variables.containsKey(var)) {
				throw new MissingValueException("No value for " + seg);
			}
			result.append(variables.get(var));
		} else {
			result.append(seg);
		}

	}

	public static boolean isVariable(String seg) {
		return seg.startsWith("#{") && seg.endsWith("}");
	}

	public String replaceVariables() {
		String result = templateText;
		for (Entry<String, String> entry : variables.entrySet()) {
			String key = "#\\{" + entry.getKey() + "\\}";
			result = result.replaceAll(key, entry.getValue());
		}
		return result;
	}

	public void checkForMissingValue(String result) {
		String regex = ".*#\\{.+\\}.*";
		Matcher m = Pattern.compile(regex).matcher(result);
		if (m.find()) {
			throw new MissingValueException("No value for " + m.group());
		}
	}

}
