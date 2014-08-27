package Dao;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import Dao.*;
import Conexao.*;
import Beans.ClienteBean;
import Beans.FornecedorBean;
import Beans.FuncionarioBean;
import Conexao.exception;
import Conexao.sql;
import java.util.*;
public class FornecedorDao {


	private static final String EXCLUIR_FORNECEDOR = 
			"delete from fornecedor where id_fornecedor = ?";
	private static final String INSERIR_FORNECEDOR =
			"insert into fornecedor(empresa," +
			"cnpj," +											
			"nome," +
			"telefone," +
			"endereco," +
			"num_empresa," +
			"complemento," +
			"bairro," +
			"cidade," +
			"uf," +
			"email)" +
			"values (?,?,?,?,?,?,?,?,?,?,?)";
	 // aki vem os dados para inserção
	private static final String ATUALIZAR_FORNECEDOR = 
			"update fornecedor set "  + "empresa = ?, " +
			"cnpj = ?, " +
			"nome = ?," +
			"telefone = ?, " +
			"endereco = ?, " +
			"num_empresa = ?, " +
			"complemento = ?, " +
			"bairro = ?, " +
			"cidade = ?, " +
			"uf = ?, " +
			"email = ? " +
			"where id_fornecedor = ? "
			;


	private static String CONSULTA_FORNECEDOR =
		"select * from fornecedor";

	private static String CONSULTA_FORNECEDOR_NOME =
		
			"select * from fornecedor where nome like ? order by nome";
	
	private static String CONSULTA_FORNECEDOR_EMPRESA =
			
			"select * from fornecedor where empresa like ? order by empresa";
	
	private String CONSULTA_FORNECEDOR_ID = 
			"select * from fornecedor where id_fornecedor = ?";
	
	
	//public boolean getAutenticacao(String nome, String senha) throws exception  {
//		Connection conn = sql.getConnection();
//		PreparedStatement statement = null;
//		ResultSet result = null;
//		int numReg = 0;
//		boolean autenticado = false;
//		try {
//			statement = conn.prepareStatement(VALIDAR_LOGIN_SENHA);
//			statement.setString(1, nome);
//			statement.setString(2, senha);
//			result = statement.executeQuery();
//			if (result.next()) {
//				numReg = result.getInt("total");
//			}
//		} catch (SQLException e) {
//			throw new exception(e);
//		} finally {
//			sql.close(conn, statement, result);
//		}
//		if(numReg != 0){
//			return autenticado = true;
//		}else{
//			return autenticado;			
//		}
//	}


	
	
	
	
	
	
	public static List<FornecedorBean> consultarFornecedor(String nome) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<FornecedorBean> listaForn = new ArrayList<FornecedorBean>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_FORNECEDOR);
			}else{
				statement = conn.prepareStatement(CONSULTA_FORNECEDOR_NOME);
				statement.setString(1, "%"+nome+"%");
			}

			result = statement.executeQuery();
			while (result.next()) {
				FornecedorBean objForn = new FornecedorBean();
				
				objForn.setId_fornecedor(result.getInt(1));
				objForn.setEmpresa(result.getString(2));
				objForn.setCnpj(result.getString(3));
				objForn.setNome(result.getString(4));
				objForn.setTelefone(result.getString(5));
				objForn.setEndereco(result.getString(6));
				objForn.setNum_empresa(result.getString(7));
				objForn.setComplemento(result.getString(8));
				objForn.setBairro(result.getString(9));
				objForn.setCidade(result.getString(10));
				objForn.setUf(result.getString(11));
				objForn.setEmail(result.getString(12));
				listaForn.add(objForn);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaForn;		
	}
	
	public static List<FornecedorBean> consultarFornecedor02(String nome) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<FornecedorBean> listaForn = new ArrayList<FornecedorBean>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_FORNECEDOR);
			}else{
				statement = conn.prepareStatement(CONSULTA_FORNECEDOR_EMPRESA);
				statement.setString(1, "%"+nome+"%");
			}

			result = statement.executeQuery();
			while (result.next()) {
				FornecedorBean objForn = new FornecedorBean();
				objForn.setId_fornecedor(result.getInt(1));
				objForn.setEmpresa(result.getString(2));
				objForn.setCnpj(result.getString(3));
				objForn.setNome(result.getString(4));
				objForn.setTelefone(result.getString(5));
				objForn.setEndereco(result.getString(6));
				objForn.setNum_empresa(result.getString(7));
				objForn.setComplemento(result.getString(8));
				objForn.setBairro(result.getString(9));
				objForn.setCidade(result.getString(10));
				objForn.setUf(result.getString(11));
				objForn.setEmail(result.getString(12));
				listaForn.add(objForn);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaForn;		
	}

	public FornecedorBean consultarFornecedorID(int id_fornecedor) throws exception{		
		FornecedorBean objForn = new FornecedorBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_FORNECEDOR_ID);
			statement.setInt(1, id_fornecedor);
			result = statement.executeQuery();
			while (result.next()) {
				
				
			objForn.setEmpresa(result.getString(1));
			objForn.setCnpj(result.getString(2));
			objForn.setNome(result.getString(3));
			objForn.setTelefone(result.getString(4));
			objForn.setEndereco(result.getString(5));
			objForn.setNum_empresa(result.getString(6));
			objForn.setComplemento(result.getString(7));
			objForn.setBairro(result.getString(8));
			objForn.setCidade(result.getString(9));
			objForn.setUf(result.getString(10));
			objForn.setEmail(result.getString(11));
								
			}
		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objForn;		
	}
	
	public boolean inserirFornecedor(FornecedorBean obj) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_FORNECEDOR);
			statement.setString(1, obj.getEmpresa());
			statement.setString(2, obj.getCnpj());
			statement.setString(3, obj.getNome());
			statement.setString(4, obj.getTelefone());
			statement.setString(5, obj.getEndereco());
			statement.setString(6, obj.getNum_empresa());
			statement.setString(7, obj.getComplemento());
			statement.setString(8, obj.getBairro());
			statement.setString(9, obj.getCidade());
			statement.setString(10, obj.getUf());
			statement.setString(11, obj.getEmail());
			statement.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	public boolean atualizarFornecedor(FornecedorBean objForn) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_FORNECEDOR);
			statement.setString(1, objForn.getEmpresa());
			statement.setString(2, objForn.getCnpj());
			statement.setString(3, objForn.getNome());
			statement.setString(4, objForn.getTelefone());
			statement.setString(5, objForn.getEndereco());
			statement.setString(6, objForn.getNum_empresa());
			statement.setString(7, objForn.getComplemento());
			statement.setString(8, objForn.getBairro());
			statement.setString(9, objForn.getCidade());
			statement.setString(10, objForn.getUf());
			statement.setString(11, objForn.getEmail());
			statement.setInt(12, objForn.getId_fornecedor());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	
	public boolean excluirFornecedor(Integer id_fornecedor) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_FORNECEDOR);
			statement.setInt(1, id_fornecedor);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	
	
}

