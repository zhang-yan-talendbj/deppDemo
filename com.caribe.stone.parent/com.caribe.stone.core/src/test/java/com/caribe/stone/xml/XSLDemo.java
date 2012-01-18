package com.caribe.stone.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XSLDemo {
	public static void main(String[] args) throws Exception {

		Document transformedDoc = read(new File("d:/tmp/Remider1.xml").getAbsolutePath());
		List<Element> all = transformedDoc.getRootElement().selectNodes("//w:p");
		System.out.println(all.size());
		int i = 0;
//		for (Element element : all) {
//			i++;
//			System.out.println(i+element.getStringValue());
//		}
		List<Element> ad2 = transformedDoc.getRootElement().selectNodes("//w:body/wx:sect/w:p[position()=7 or position()=8]");
		for (Element e : ad2) {
			System.out.println(e.getStringValue());
			System.out.println(e.getParent().remove(e));
		}
		String asXML = transformedDoc.asXML();
		transformedDoc = DocumentHelper.parseText(asXML);
		all = transformedDoc.getRootElement().selectNodes("//w:p");
		i = 0;
		for (Element e : all) {
			i++;
			System.out.println(i + e.getStringValue());
		}
		File file = new File("d:/tmp/result.xml");
		file.createNewFile();
		FileUtil.writeAsString(file, transformedDoc.asXML());
		// FileUtils.writeStringToFile(new
		// File("e:/bruce/aia/depp/maven/source/com.caribe.stone.parent/com.caribe.stone.core/src/test/resources/com/caribe/stone/xml/result.xml"),
		// asXML);
		// System.out.println("success");
		// System.out.println(asXML);
		// toPretyXml(transformedDoc);
	}

	public static Document read(String fileName) throws MalformedURLException, DocumentException {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));

		return document;
	}

	public static void toPretyXml(Document document) throws Exception {

		StringWriter sw = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer;
		try {
			writer = new XMLWriter(sw, format);
			Document doc = DocumentHelper.parseText(document.asXML());
			writer.write(doc);
			writer.flush();
			System.out.println(sw.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}