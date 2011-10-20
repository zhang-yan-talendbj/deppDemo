package foo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.util.List;

public class ExportData {

	public static void main(String[] args) throws Exception {
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		Connection con = DriverManager
				.getConnection("jdbc:db2://10.69.4.187:50000/sunsuper",
						"db2admin", "Asd12345");
		List<String> tableList = new LinkedList<String>();
		tableList.add("PRECLAIM.REMINDER_TYPE_LKUP");
		tableList.add("PRECLAIM.REMINDER_STATUS_LKUP");
		tableList.add("PRECLAIM.INBOUND_COMMUNICATION_TYPE_LKUP");
		tableList.add("PRECLAIM.NOTIFICATION_STATUS_LKUP");
		tableList.add("PRECLAIM.NOTIFICATION_DOCUMENT_STATUS_LKUP");
		tableList.add("PRECLAIM.DOCUMENT");
		tableList.add("PRECLAIM.FUND_DOCUMENT_MAPPING");
		for (String tableName : tableList) {
			PreparedStatement psmt = con.prepareStatement("select * from "
					+ tableName);
			psmt.execute();

			ResultSet rs = psmt.getResultSet();
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			StringBuffer buf = new StringBuffer();
//			buf.append("delete from ").append(tableName).append(";");
			while (rs.next()) {
				buf.append("insert into ").append(tableName).append(" (");
				StringBuffer columnName = new StringBuffer();
				StringBuffer values = new StringBuffer();
				for (int i = 1; i <= columnCount; i++) {
					columnName.append(metaData.getColumnName(i)).append(",");
					int type = metaData.getColumnType(i);
					if (type == 5) {
						values.append(rs.getString(i)).append(",");
					} else if (type == 12) {
						values.append(" '" + rs.getString(i) + "' ")
								.append(",");
					} else if (type == -5) {
						values.append(rs.getString(i)).append(",");
					} else {
						System.out.println(tableName);
						throw new Exception("type error!" + type + ":"
								+ metaData.getColumnName(i));
					}
				}
				buf.append(columnName.substring(0, columnName.length() - 1));
				buf.append(")");
				buf.append(" values (");
				buf.append(values.substring(0, values.length() - 1));
				buf.append(");");
				buf.append("\n\r");
			}
			System.out.println(buf);
			System.out.println();
		}
	}
}
