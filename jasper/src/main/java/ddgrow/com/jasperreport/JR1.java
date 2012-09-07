package ddgrow.com.jasperreport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

public class JR1 {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			JasperCompileManager.compileReportToFile(
					"e:/bruce/aia/depp/maven/source/jasper/target/classes/jr1.jrxml");
			
			JasperFillManager.fillReportToFile("e:/bruce/aia/depp/maven/source/jasper/target/classes/JR1.jasper",
					new HashMap(),new JREmptyDataSource());
			
			InputStream jrprint = new FileInputStream("e:/bruce/aia/depp/maven/source/jasper/target/classes/JR1.jrprint");
			JasperViewer.viewReport(jrprint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}