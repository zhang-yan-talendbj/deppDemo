package foo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Scaffold {
	public static void main(String[] args) throws IOException, TemplateException {
		ClassPathResource classPathResource = new ClassPathResource("template.txt");
		String content = FileUtils.readFileToString(classPathResource.getFile(), "Cp1252");
		Pattern p1 = Pattern.compile("##[^#]+##");
		Matcher m1 = p1.matcher(content);
		List<String> l=new LinkedList<String>();
		while (m1.find()) {
			l.add(m1.group().replaceAll("##", ""));
		}
		Configuration cfg = new Configuration();
		// 设置模板文件夹
		cfg.setDirectoryForTemplateLoading(new File("templateDirectory/"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template temp = cfg.getTemplate("sunsuper.ftl");
		// ${Salutation} ${First_Name} ${Surname}
		print(l, temp);
	}

	private static void print(List<String> l, Template temp) throws TemplateException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", l);
		Writer out = new OutputStreamWriter(System.out);
		temp.process(map, out);
		out.flush();
	}

	public static String getReferenceName(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			final char charAt = s.charAt(i);
			if (charAt >= 65 && charAt <= 90)
				sb.append(charAt);
		}
		return sb.toString().toLowerCase();
	}

	private static void consoleFind() throws IOException {
		BufferedReader in;
		Pattern pattern = Pattern.compile("##.*##");
		in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null) {
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()) {
				System.out.println(matcher.group());
			}
		}
		in.close();
	}
}
