package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Beans.ClienteBean;
import Conexao.exception;
import Conexao.sql;
import Funcoes.DbUtilBackup;

public class BackupDao {
	
	private static final String BACKUP_NOVO = 
//			"backup database venda2 to disk=? ";
	"EXEC PROC_BACK ?";
	private static final String BACKUP_RESTORE =
	"EXEC PROC_RESTORE ?";
		


	public static boolean backup_novo(String caminho) throws exception{		
		Connection conn = DbUtilBackup.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(BACKUP_NOVO);
			statement.setString(1, caminho);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			throw new exception(e);
		} finally {
			DbUtilBackup.close(conn, statement, result);
		}
		return true;		
	}

	public static boolean backup_restore(String caminho) throws exception{		
		Connection conn = DbUtilBackup.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(BACKUP_RESTORE);
			statement.setString(1, caminho);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			throw new exception(e);
		} finally {
			DbUtilBackup.close(conn, statement, result);
		}
		return true;		
	}

	
	
	

}
