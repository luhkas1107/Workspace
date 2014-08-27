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
public class ClienteDao {

	private static final String CONTAGEM_NUM_CLINTE=
			"SELECT COUNT (*) FROM cliente";
	private static final String EXCLUIR_CLIENTE = 
			"delete from cliente where id_cliente = ?";
	private static final String INSERIR_CLIENTE =
			"insert into cliente(nome," +
			"cpf," +
			"telefone," +											
			"endereco," +
			"num_residencia," +
			"complemento," +
			"bairro," +
			"cidade," +
			"uf," +
			"email)" +
			"values (?,?,?,?,?,?,?,?,?,?)";
	 // aki vem os dados para inserção
	private static final String ATUALIZAR_CLIENTE = 
			"update cliente set "  + "nome=? , " +
			"cpf=? , " +
			"telefone=? , " +
			"endereco=? , " +
			"num_residencia=? , " +
			"complemento=? , " +
			"bairro=? , " +
			"cidade=? , " +
			"uf=? , " +
			"email=? " +
			"where id_cliente = ? "
			;
		
	private static String CONSULTA_CLIENTE= 
			"select * from cliente";
	
	private static final  String CONSULTA_CLIENTE_NOME =
			"select * from cliente where nome like ? order by nome";
	
	
	private static String CONSULTA_CLIENTE_ID = 
			"select * from cliente where id_cliente = ?";
	


	public static List<ClienteBean> consultarCliente(String nome) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ClienteBean> listaClie= new ArrayList<ClienteBean>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_CLIENTE);
			}else{
				statement = conn.prepareStatement(CONSULTA_CLIENTE_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				ClienteBean objClie = new ClienteBean();
				objClie.setId_cliente(result.getInt(1));
				objClie.setNome(result.getString(2));
				objClie.setCpf(result.getString(3));
				objClie.setTelefone(result.getString(4));
				objClie.setEndereco(result.getString(5));
				objClie.setNum_residencia(result.getString(6));
				objClie.setComplemento(result.getString(7));
				objClie.setBairro(result.getString(8));
				objClie.setCidade(result.getString(9));
				objClie.setUf(result.getString(10));
				objClie.setEmail(result.getString(11));
				listaClie.add(objClie);
			}
		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaClie;		
	}
	
	public static List<ClienteBean> consultarClienteID2(int id) throws exception{
		ClienteBean objClie = new ClienteBean();		
		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ClienteBean> listaClie= new ArrayList<ClienteBean>();
		
		try {	
			statement = conn.prepareStatement(CONSULTA_CLIENTE_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			while (result.next()) {
				objClie.setId_cliente(result.getInt(1));
				objClie.setNome(result.getString(2));
				objClie.setCpf(result.getString(3));
				objClie.setTelefone(result.getString(4));
				objClie.setEndereco(result.getString(5));
				objClie.setNum_residencia(result.getString(6));
				objClie.setComplemento(result.getString(7));
				objClie.setBairro(result.getString(8));
				objClie.setCidade(result.getString(9));
				objClie.setUf(result.getString(10));
				objClie.setEmail(result.getString(11));
				listaClie.add(objClie);
				
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro, favor verificar a identificação do cliente!");
			
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaClie;		
	}

	public static int contagem () throws exception{
		int contador = 0;
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {	
			statement = conn.prepareStatement(CONTAGEM_NUM_CLINTE);
			result = statement.executeQuery();
			
			while (result.next()) {
				
				contador=result.getInt(1);
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro na contagem dos clientes" +contador);
			
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return contador;		
	
	}
	public static ClienteBean consultarClienteID3(int id) throws exception{
		ClienteBean objClie = new ClienteBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
//		List<ClienteBean> listaClie= new ArrayList<ClienteBean>();
		
		try {	
			statement = conn.prepareStatement(CONSULTA_CLIENTE_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			while (result.next()) {
				
				objClie.setId_cliente(result.getInt(1));
				objClie.setNome(result.getString(2));
				objClie.setCpf(result.getString(3));
				objClie.setTelefone(result.getString(4));
				objClie.setEndereco(result.getString(5));
				objClie.setNum_residencia(result.getString(6));
				objClie.setComplemento(result.getString(7));
				objClie.setBairro(result.getString(8));
				objClie.setCidade(result.getString(9));
				objClie.setUf(result.getString(10));
				objClie.setEmail(result.getString(11));
//				listaClie.add(objClie);
				
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro, favor verificar a identificação do cliente!");
			
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objClie;		
	}
	

	public static ClienteBean consultarClienteID(String id) throws exception{		
		ClienteBean objClie = new ClienteBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_CLIENTE_ID);
			statement.setString(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				

			objClie.setId_cliente(Integer.parseInt(result.getString(1)));
			objClie.setNome(result.getString(2));
			objClie.setCpf(result.getString(3));
			objClie.setTelefone(result.getString(4));
			objClie.setEndereco(result.getString(5));
			objClie.setNum_residencia(result.getString(6));
			objClie.setComplemento(result.getString(7));
			objClie.setBairro(result.getString(8));
			objClie.setCidade(result.getString(9));
			objClie.setUf(result.getString(10));
			objClie.setEmail(result.getString(11));
								
			}
		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objClie;		
	}
	
	
	public static boolean inserirCliente(ClienteBean obj) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_CLIENTE);
			statement.setString(1, obj.getNome());
			statement.setString(2, obj.getCpf());
			statement.setString(3, obj.getTelefone());
			statement.setString(4, obj.getEndereco());
			statement.setString(5, obj.getNum_residencia());
			statement.setString(6, obj.getComplemento());
			statement.setString(7, obj.getBairro());
			statement.setString(8, obj.getCidade());
			statement.setString(9, obj.getUf());
			statement.setString(10, obj.getEmail());
			statement.executeUpdate();
			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Usuário já existe por favor cadastre com outro CPF.");
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	public static boolean atualizarCliente(ClienteBean objClie) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_CLIENTE);
			statement.setString(1, objClie.getNome());
			statement.setString(2, objClie.getCpf());
			statement.setString(3, objClie.getTelefone());
			statement.setString(4, objClie.getEndereco());
			statement.setString(5, objClie.getNum_residencia());
			statement.setString(6, objClie.getComplemento());
			statement.setString(7, objClie.getBairro());
			statement.setString(8, objClie.getCidade());
			statement.setString(9, objClie.getUf());
			statement.setString(10, objClie.getEmail());
			statement.setInt(11, objClie.getId_cliente());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	

	public static boolean excluirCliente(String id_cliente) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_CLIENTE);
			statement.setString(1, id_cliente);
			statement.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}

	
}

