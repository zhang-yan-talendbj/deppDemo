package com.caribe.stone.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelDemo {
	public static void main(String[] args) {
		String pathname = "D:/bruce/aia/workspace/Claims(Rehab)/branch/mainline/claims-admin-domain-test/src/test/resources/";
		String string = "DomainJPAMappingTestData.xls.merge-left.r8770";
		String string2 = "DomainJPAMappingTestData.xls.merge-right.r8792";
		String string3 = "DomainJPAMappingTestData.xls";
		readXls(pathname+string, pathname+string+".txt");
		readXls(pathname+string2, pathname+string2+".txt");
		readXls(pathname+string3, pathname+string3+".txt");
//		readXls("d:/bak/10-10/DomainJPAMappingTestData.xls", "d:/bak/10-10/DomainJPAMappingTestData.txt");
	}

	public static void readXls(String pathname, String pathname2) {
		File f = new File(pathname);
		try {
			FileInputStream is = new FileInputStream(f);
			HSSFWorkbook wbs = new HSSFWorkbook(is);
			System.out.println(wbs.getNumberOfSheets());
			// System.out.println(childSheet.getPhysicalNumberOfRows());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < wbs.getNumberOfSheets(); i++) {

				HSSFSheet sheet = wbs.getSheetAt(i);
				sb.append(sheet.getSheetName());
				sb.append("\r\n");
				Iterator rows = sheet.rowIterator();
				while (rows.hasNext()) {
					HSSFRow row = (HSSFRow) rows.next();
					// System.out.print("行：" + row.getRowNum() + " ");
					Iterator cells = row.cellIterator();
					while (cells.hasNext()) {
						HSSFCell cell = (HSSFCell) cells.next();  
	                    // System.out.println("列：" + cell.getCellNum());  
						switch (cell.getCellType()) {  
	                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字  
	                        String s = cell.getNumericCellValue() + "   ";
	                        sb.append(s);
	                        break;  
	                    case HSSFCell.CELL_TYPE_STRING: // 字符串  
	                        String s2 = cell.getStringCellValue() + "   ";sb.append(s2);
	                        break;  
	                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
	                        String x = cell.getBooleanCellValue() + "   ";sb.append(x);
	                        break;  
	                    case HSSFCell.CELL_TYPE_FORMULA: // 公式  
	                        String s3 = cell.getCellFormula() + "   ";sb.append(s3);
	                        break;  
	                    case HSSFCell.CELL_TYPE_BLANK: // 空值  
	                    	sb.append(" ");
	                        break;  
	                    case HSSFCell.CELL_TYPE_ERROR: // 故障  
	                    	sb.append(" ");
	                        break;  
	                    default:  
	                        String s4 = "未知类型   ";
	                        sb.append(s4);
	                        break;  
	                    }  
					}
					sb.append("\r\n");
				}
			
			}
			FileUtils.writeStringToFile(new File(pathname2), sb.toString());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
