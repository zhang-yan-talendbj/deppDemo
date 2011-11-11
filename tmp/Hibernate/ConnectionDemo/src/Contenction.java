import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Contenction {
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/test", "root", "sa");
		System.out.println(conn == null);
	}
}
