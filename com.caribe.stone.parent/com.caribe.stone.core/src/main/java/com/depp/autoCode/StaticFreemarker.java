package com.depp.autoCode;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class StaticFreemarker {

	public static void main(String[] args) throws IOException, TemplateException {
		final String manager = "ArticleManager";

		Configuration _cfg = new Configuration();
		//设置模板文件夹
		_cfg.setDirectoryForTemplateLoading(new File("templateDirectory/string"));
		_cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template temp = _cfg.getTemplate("list" + ".ftl");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "赵琳");
		map.put("manager", manager);
		map.put("managerRefer", getReferenceName(manager));
		/*final String name = StaticFreemarker.class.getName();
		System.out.println(name.substring(name.lastIndexOf('.') + 1));*/
		final String rm = "ArticleRecommendManager";
		map.put("recommendManager", rm);
		map.put("recommendManagerRefer", getReferenceName(rm));
		map.put("top", 3);
		final String value = "ArticleRecommend";
		map.put(value, value);
		map.put("ArticleRecommendRefer", getReferenceName(value));
		map.put("Article", "Article");
		map.put("x", "300");
		map.put("y", "50");
		map.put("user", "<body></body>");

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
}
