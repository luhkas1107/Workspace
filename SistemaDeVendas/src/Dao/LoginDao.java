package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import apresentacao.Login;

import Beans.FuncionarioBean;
import Beans.LoginBean;
import Conexao.exception;
import Conexao.sql;


public class LoginDao {
	
	private static final String INSERIR_LOGIN =
		"insert into funcionario (cpf_func, senha_func)"+
		"values(?,?)";

	private static final String VALIDAR_LOGIN_SENHA = 
		"select "+
			"count(id_func) as total " +
		"from "+
			"cpf_func f "+
		"where "+
			"f.cpf_func = ? and " +
			"f.senha_func = ?";
	
	private static final String ATUALIZAR_LOGIN =
		"update login set " +
		"cpf_func = ?, " +
		"senha_func = ?, " +
		"where id_func= ? ";
	
	public boolean inserirLogin(LoginBean obj) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_LOGIN);
			
			statement.setString(1, obj.getCpf_func());
			statement.setString(2, obj.getSenha_func());

			
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean atualizarLogin(FuncionarioBean obj) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_LOGIN);
			statement.setString(1, obj.getCpf_func());
			statement.setString(2, obj.getSenha_func());

			
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean getAutenticacao(String cpf_func, String senha_func) throws exception {
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		int numReg = 0;
		boolean autenticado = false;
		try {			
			statement = conn.prepareStatement(VALIDAR_LOGIN_SENHA);
			statement.setString(1, cpf_func);
			statement.setString(2, senha_func);
			result = statement.executeQuery();
			if (result.next()) {
				numReg = result.getInt("total");
			}
		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		if(numReg != 0){
			return autenticado = true;
		}else{
			return autenticado;			
		}
	}		
}
