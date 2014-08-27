package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.EmailBean;
import Conexao.exception;
import Conexao.sql;

public class EmailDao {
	
	private static final String INSERIR_EMAIL =
			"insert into funcionario (cpf_func, senha_func)"+
			"values(?,?)";

		private static final String ZERAR_TABELA= 
			"truncate table email";
		
		
		public boolean zerar() throws exception{		
			Connection conn = sql.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(ZERAR_TABELA);
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new exception(e);
			} finally {
				sql.close(conn, statement, result);
			}
			return true;		
		}
		
		public boolean inserirLogin(EmailBean obj) throws exception{		
			Connection conn = sql.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(INSERIR_EMAIL);
				
				statement.setString(1, obj.getEmail());

				
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new exception(e);
			} finally {
				sql.close(conn, statement, result);
			}
			return true;		
		}

		

}
