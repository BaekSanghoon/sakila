package sakila.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	// DB연결
	public static Connection getConnection()throws Exception{	
		final String DB_URL = "jdbc:mariadb://localhost:3306/sakila";
		final String DB_USER = "root";
		final String DB_PASSWORD = "java1004";
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		return conn;
	}
}
