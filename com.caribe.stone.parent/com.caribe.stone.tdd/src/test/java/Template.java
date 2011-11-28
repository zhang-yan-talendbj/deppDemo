import java.util.HashMap;
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

	public Object evaluate() {
		String result = templateText;
		for (Entry<String, String> entry : variables.entrySet()) {
			String key = "#\\{" + entry.getKey() + "\\}";
			result = result.replaceAll(key, entry.getValue());
		}
		checkForMissingValue(result);
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
