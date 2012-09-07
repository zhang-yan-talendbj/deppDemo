package foo.domain;

import java.sql.*;

public class ExcelConnectionDemo {
	private static String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static String dbURL = "jdbc:odbc:driver={Microsoft Excel Driver (*.xls)};DBQ=e:\\bruce\\aia\\workspace\\April\\db\\OLAS.xls"; // 不设置数据源
	// private static String dbURL="jdbc:odbc:ExcelTest"; //数据源连接方式
	// DSN：ExcelTest

	private static Connection dbConn = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, "", "");
			Statement smt = dbConn.createStatement();
			ResultSet set = smt.executeQuery("select BFPOLNUM from [lldbfc$]");
			while (set.next()) {
				System.out.print(set.getString(1) + "\t");
//				System.out.print(set.getString(2) + "\t");
//				System.out.println(set.getString(3));
//				System.out.println(set.getString(4));
//				System.out.println(set.getString(5));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}