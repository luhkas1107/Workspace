package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class sql {

	private static final String URL_DATABASE = "jdbc:sqlserver://localhost;databaseName=venda2;";
	private static final String DRIVER_JDBC = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	private static final String USUARIO_DB = "sa";
	private static final String SENHA_USUARIO_DB = "123";
	
	static {
		try {
			Class.forName(DRIVER_JDBC);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() 	throws exception {
		try {
			Connection connection = DriverManager.getConnection(URL_DATABASE, USUARIO_DB, SENHA_USUARIO_DB);
			connection.setAutoCommit(true);
			System.out.println(".");
			return connection;
		} catch (SQLException e) {
			throw new exception(e);
		}
	}
	
	public static void close(Connection conn, Statement statement, ResultSet result) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (result != null) {
				result.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		JOptionPane.showMessageDialog(null, "Carregando...");
		System.out.println("..");
	}
}
