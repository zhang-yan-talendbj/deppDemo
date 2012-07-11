package com.caribe.stone.freemarker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerDemo {
	@Test
	public void hello() throws IOException, TemplateException {

		Configuration cfg = new Configuration();
		// 设置模板文件夹
		cfg.setDirectoryForTemplateLoading(new ClassPathResource("templateDirectory/").getFile());
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template temp = cfg.getTemplate("hello.ftl");
		// ${Salutation} ${First_Name} ${Surname}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "HelloWord");
		console(temp, map);
	}

	private static void console(Template temp, Map<String, Object> map) throws TemplateException,
			IOException {
		Writer out = new OutputStreamWriter(System.out);
		temp.process(map, out);
		out.flush();
	}
}
