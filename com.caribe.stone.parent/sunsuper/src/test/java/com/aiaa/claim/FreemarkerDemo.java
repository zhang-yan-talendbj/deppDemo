package com.aiaa.claim;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerDemo {
	@Test
	public void testName() throws Exception {

		// ClassPathResource classPathResource = new
		// ClassPathResource("template.txt");
		Configuration cfg = new Configuration();
		// 设置模板文件夹
		cfg.setDirectoryForTemplateLoading(new ClassPathResource("/template").getFile());
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template temp = cfg.getTemplate("HelloWord.ftl");
		List<String> l = null;
		// ${Salutation} ${First_Name} ${Surname}
		Writer out = new OutputStreamWriter(System.out);

		Map map = new HashMap();
		ArrayList inputList = new ArrayList();
		ParseHtmlDemo p = new ParseHtmlDemo();
		map.put("input", p.getInputList());
		map.put("select", inputList);
		map.put("checkbox", inputList);
		map.put("msg", "sss");

		System.out.println(map);
		temp.process(map, out);
		out.flush();
		out.close();
	}

}
