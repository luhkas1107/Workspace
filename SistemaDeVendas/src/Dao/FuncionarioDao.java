package Dao;

import java.sql.*;
import java.util.*;

import javax.swing.*;

//import br.com.lab.util.DbUtil;
import Dao.*;
import Funcoes.Criptografia;
import Conexao.*;
import Beans.ClienteBean;
import Beans.FuncionarioBean;
import Conexao.exception;
import Conexao.sql;

import java.util.*;

public class FuncionarioDao {

	private static final String EXCLUIR = 
//			"delete from funcionario where id_func = ?";
			"update funcionario set "+
			"situacao = 'Desativado'" +
			"where id_func = ?";
	private static final String INSERIR =
			"insert into funcionario (nome_func," +
			"senha_func," +
			"telefone_func," +
			"endereco_func," +
			"num_func," + 
			"comple_func," + 
			"bairro_func," +
			"cidade_func," +
			"uf_func," +
			"cpf_func," +
			"num_matricula_func," +
			"funcao_func," +
			"inicio_func," +
			"termino_func," +
			"salario_func,"+
			//"comissao_func," +
			"cpts," +
			"email," +
			"sexo_func,caminho_imagem,situacao)"+
			//"porcentagem_comissao_func"+
			"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
	 // aki vem os dados para inserção
	private static final String ATUALIZAR =
			"EXEC PROC_A_FUNCT ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
	private static final String VALIDAR_LOGIN_SENHA = 
			"EXEC PROC_V_FUNC ?,?";

	private static String CONSULTAS= 
			"select * from funcionario";
	
	private static String CONSULTAS_NOME = 
			"select * from funcionario where nome_func like ? order by nome_func";
	
	private static String CONSULTA_ID = 
			"select * from funcionario where id_func = ?";
	
	
	private static final String ATUALIZAR_LOGIN =
		"update funcionario set " +
		"senha_func = ? " +
		"where id_func= ? ";
	private static final String SELECIONAR_LOGIN =
			"select funcao_func , nome_func, id_func, caminho_imagem, situacao from funcionario where cpf_func=? and senha_func=?";

	private static final String VALIDADE_CPF=
			"select cpf_func from funcionario";		
	private static final String ESQUECI_SENHA=
			"select id_func, cpf_func, caminho_imagem from funcionario where cpf_func=? and cpts=? and id_func=?";

	public boolean getAutenticacao(String cpf_func, String senha_func) throws exception  {
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

	



	public static List<FuncionarioBean> consultarFuncionariosID(int id) throws exception{ 						
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<FuncionarioBean> listaFunc = new ArrayList<FuncionarioBean>();
		try {
			
			if(id<=0){
				statement = conn.prepareStatement(CONSULTAS);
				
			}else{
				statement = conn.prepareStatement(CONSULTA_ID);
				statement.setInt(1, id);
			}
			result = statement.executeQuery();
			while (result.next()) {
				FuncionarioBean objFunc = new FuncionarioBean();
				objFunc.setId_func(result.getInt(1));
				objFunc.setNome_func(result.getString(2));
				objFunc.setSenha_func(result.getString(3));
				objFunc.setTelefone_func(result.getString(4));
				objFunc.setEndereco_func(result.getString(5));
				objFunc.setNum_func(result.getString(6));
				objFunc.setComple_func(result.getString(7));
				objFunc.setBairro_func(result.getString(8));
				objFunc.setCidade_func(result.getString(9));
				objFunc.setUf_func(result.getString(10));
				objFunc.setCpf_func(result.getString(11));
				objFunc.setNum_matricula_func(result.getString(12));
				objFunc.setFuncao_func (result.getString(13));
				objFunc.setInicio_func(result.getString(14));
				objFunc.setTermino_func(result.getString(15));
				objFunc.setSalario_func(result.getFloat(16));
			//	objFunc.setPorcentagem_comissao_func(result.getString(17));
				objFunc.setCpts(result.getString(17));
				objFunc.setSexo_func(result.getString(18));
				objFunc.setEmail(result.getString(19));
				objFunc.setCaminho(result.getString(20));
				objFunc.setSituacao(result.getString(21));
				listaFunc.add(objFunc);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaFunc;		
	}



	public static FuncionarioBean consultarFuncionariosIDvenda(int id) throws exception{
		FuncionarioBean objFunc = new FuncionarioBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
				statement = conn.prepareStatement(CONSULTA_ID);
				statement.setInt(1, id);
			
			result = statement.executeQuery();
			while (result.next()) {
				
				objFunc.setId_func(result.getInt(1));
				objFunc.setNome_func(result.getString(2));
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objFunc;		
	}


	
	public static boolean atualizarFuncionario(FuncionarioBean objFunc) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR);
			statement.setString(1, objFunc.getNome_func());
			statement.setString(2, Criptografia.cripto(objFunc.getSenha_func()));
			statement.setString(3, objFunc.getTelefone_func());
			statement.setString(4, objFunc.getEndereco_func());
			statement.setString(5, objFunc.getNum_func());
			statement.setString(6, objFunc.getComple_func());
			statement.setString(7, objFunc.getBairro_func());
			statement.setString(8, objFunc.getCidade_func());
			statement.setString(9, objFunc.getUf_func());
			statement.setString(10, objFunc.getCpf_func());
			statement.setString(11, objFunc.getNum_matricula_func());
			statement.setString(12, objFunc.getFuncao_func());
			statement.setString(13, objFunc.getInicio_func());
			statement.setString(14, objFunc.getTermino_func());
			statement.setFloat(15, objFunc.getSalario_func());
			statement.setString(16, objFunc.getCpts());
			statement.setString(17, objFunc.getSexo_func());
			statement.setString(18, objFunc.getEmail());
			statement.setString(19, objFunc.getCaminho());			
			statement.setString(20, objFunc.getSituacao());
			statement.setInt(21, objFunc.getId_func());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	

	public static boolean atualizarLogin(String senha, int id) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_LOGIN);
			statement.setString(1, Criptografia.cripto(senha));
			statement.setInt(2, id);

			
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}

	
	
	
	public static List<FuncionarioBean> consultarFuncionarios(String nome) throws exception{ 						
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<FuncionarioBean> listaFunc = new ArrayList<FuncionarioBean>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTAS);
			}else{
				statement = conn.prepareStatement(CONSULTAS_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				FuncionarioBean objFunc = new FuncionarioBean();
				objFunc.setId_func(result.getInt(1));
				objFunc.setNome_func(result.getString(2));
				objFunc.setSenha_func(result.getString(3));
				objFunc.setTelefone_func(result.getString(4));
				objFunc.setEndereco_func(result.getString(5));
				objFunc.setNum_func(result.getString(6));
				objFunc.setComple_func(result.getString(7));
				objFunc.setBairro_func(result.getString(8));
				objFunc.setCidade_func(result.getString(9));
				objFunc.setUf_func(result.getString(10));
				objFunc.setCpf_func(result.getString(11));
				objFunc.setNum_matricula_func(result.getString(12));
				objFunc.setFuncao_func (result.getString(13));
				objFunc.setInicio_func(result.getString(14));
				objFunc.setTermino_func(result.getString(15));
				objFunc.setSalario_func(result.getFloat(16));
			//	objFunc.setPorcentagem_comissao_func(result.getString(17));
				objFunc.setCpts(result.getString(17));
				objFunc.setSexo_func(result.getString(18));
				objFunc.setEmail(result.getString(19));
				objFunc.setCaminho(result.getString(20));
				objFunc.setSituacao(result.getString(21));
				listaFunc.add(objFunc);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaFunc;		
	}

	public boolean inserirFuncionarios(FuncionarioBean funcionario) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR);
			statement.setString(1, funcionario.getNome_func());
			statement.setString(2, Criptografia.cripto(funcionario.getSenha_func()));
			statement.setString(3, funcionario.getTelefone_func());
			statement.setString(4, funcionario.getEndereco_func());
			statement.setString(5, funcionario.getNum_func());
			statement.setString(6, funcionario.getComple_func());
			statement.setString(7, funcionario.getBairro_func());
			statement.setString(8, funcionario.getCidade_func());
			statement.setString(9, funcionario.getUf_func());
			statement.setString(10, funcionario.getCpf_func());
			statement.setString(11, funcionario.getNum_matricula_func());
			statement.setString(12, funcionario.getFuncao_func());
			statement.setString(13, funcionario.getInicio_func());
			statement.setString(14, funcionario.getTermino_func());
			statement.setFloat(15, funcionario.getSalario_func());
			//statement.setString(15, funcionario.getPorcentagem_comissao_func());
			statement.setString(16, funcionario.getCpts());
			statement.setString(17, funcionario.getEmail());
			statement.setString(18, funcionario.getSexo_func());
			statement.setString(19, funcionario.getCaminho());
			statement.setString(20, funcionario.getSituacao());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	public static boolean excluirFuncionario(String id_func) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR);
			statement.setString(1, id_func);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	

	public static FuncionarioBean selecionarLogin(String cpf_func, String senha_func) throws exception{ // 						
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		FuncionarioBean objFunc = new FuncionarioBean();
		try {

			statement = conn.prepareStatement(SELECIONAR_LOGIN);
			statement.setString(1 , cpf_func );
			statement.setString(2 , Criptografia.cripto(senha_func));
			
			result = statement.executeQuery();
			
			while (result.next()) {
							
				//objFunc.setCpf_func(result.getString(1));
				objFunc.setFuncao_func (result.getString(1).toString());
				objFunc.setNome_func (result.getString(2).toString());
				objFunc.setId_func(result.getInt(3));
				objFunc.setCaminho(result.getString(4));
				objFunc.setSituacao(result.getString(5));
				//objFunc.add(objFunc);
						
			}

			

		} catch (SQLException e) { 
			JOptionPane.showMessageDialog(null, "Senha inválida.", "Cuidado!!!", JOptionPane.ERROR_MESSAGE);
			System.out.println("... Verificando senha ....");
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objFunc;		
	}
	
	public static FuncionarioBean EsqueceuSenha(String cpf_func, String ctps, int id) throws exception{ // 						
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		FuncionarioBean objFunc = new FuncionarioBean();
		try {

			
			statement = conn.prepareStatement(ESQUECI_SENHA);
			statement.setString(1 , cpf_func );
			statement.setString(2 , ctps);
			statement.setInt(3 , id);
			
			result = statement.executeQuery();
			
			while (result.next()) {
							
			
				objFunc.setId_func(result.getInt(1));
				objFunc.setCpf_func(result.getString(2).toString());
				objFunc.setCaminho(result.getString(3));

						
			}

			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Verifique todas as informações.", "Cuidado!!!", JOptionPane.ERROR_MESSAGE);
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objFunc;		
	}


	
	
	
}

