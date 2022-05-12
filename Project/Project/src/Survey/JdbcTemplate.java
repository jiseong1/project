package Survey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTemplate {
	private static JdbcTemplate instance;
	private String ur = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "c##project";
	private String pw = "1";
	
	
	private JdbcTemplate() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static JdbcTemplate getInstance() {
		if(instance == null) {
			instance = new JdbcTemplate();
		}
		return instance;
	}
	
	public Connection getConn() throws SQLException {
		return DriverManager.getConnection(ur, id, pw);
	}
}
