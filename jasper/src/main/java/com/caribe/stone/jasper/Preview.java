package com.caribe.stone.jasper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import net.sf.jasperreports.view.JasperDesignViewer;

public class Preview {
	public static void main(String[] args) {
		try {
			URL fileURL = ClassLoader.getSystemClassLoader().getResource(
					"jr1.jrxml");
			File file = new File(fileURL.toURI());
			InputStream jrxml = new FileInputStream(file);
			JasperDesignViewer.viewReportDesign(jrxml, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}