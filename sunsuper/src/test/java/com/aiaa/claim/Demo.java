package com.aiaa.claim;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Demo {

	public static void main(String[] args) throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		// 设置模板文件夹
		cfg.setDirectoryForTemplateLoading(new ClassPathResource("/template").getFile());
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template temp = cfg.getTemplate("policyNotice.ftl");
		List<String> l = null;
		// ${Salutation} ${First_Name} ${Surname}
		Writer out = new OutputStreamWriter(System.out);

		String str = "Policy No	Policy Owner	Life Insured	Premium Mode	Benefit	Current Sum Insured	Current Premium	New Sum Insured(after renewal)	New Premium(after renewal)	Paid to Date	Policy Fee	Total Premium After Renewal(Inc. Policy Fee)  ";
		String[] column = str.split("	");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "HelloWord");
		map.put("column", column);
		temp.process(map, out);
		out.flush();
		out.close();
	}
}
