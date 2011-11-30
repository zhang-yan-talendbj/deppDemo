import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParse {

	public List<String> parse(String template) {
		List<String> segments = new ArrayList<String>();
		int index = collectSegments(template, segments);
		addTail(template, segments, index);
		addEmptyStringIfTemplateWasEmpty(segments);
		return segments;
	}

	public int collectSegments(String src, List<String> segments) {
		Pattern p = Pattern.compile("#\\{[^}]*\\}");
		Matcher m = p.matcher(src);
		int index = 0;
		while (m.find()) {
			addPrecedingPlainText(src, segments, m, index);
			addVariable(src, segments, m);
			index = m.end();
		}
		return index;
	}

	public void addEmptyStringIfTemplateWasEmpty(List<String> l) {
		if (l.isEmpty()) {
			l.add("");
		}
	}

	public void addTail(String src, List<String> l, int index) {
		if (index < src.length()) {
			l.add(src.substring(index));
		}
	}

	public void addVariable(String template, List<String> l, Matcher m) {
		l.add(template.substring(m.start(), m.end()));
	}

	public void addPrecedingPlainText(String template, List<String> l, Matcher m, int index) {
		if (index != m.start()) {
			l.add(template.substring(index, m.start()));
		}
	}

	public List<Segment> parseSegment(String string) {
		List<Segment> segments = new ArrayList<Segment>();
		List<String> strings = parse(string);
		for (String s : strings) {
			if (Template.isVariable(s)) {
				String name = s.substring(2, s.length() - 1);
				segments.add(new Variable(s));
			} else {
				segments.add(new PlainText(s));
			}
		}
		return segments;
	}
}
