package Dao;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import Dao.*;
import Conexao.*;
import Beans.ClienteBean;
import Beans.FornecedorBean;
import Beans.FuncionarioBean;
import Beans.VendaBean;
import Conexao.exception;
import Conexao.sql;
import java.util.*;
import Beans.ProdutoBean;
public class VendaDao {


	private static final String EXCLUIR_VENDA = 
			"delete from venda where id_venda = ?";
	private static final String ADICIONAR_VENDA =
			"insert into venda(id_func," +
			"id_cliente," +											
			"valor_total," +
			"valor_pago," +
			"forma_pagamento," +
			"data," +
			"hora)" +
			"values (?,?,?,?,?,?,?)";
	 // aki vem os dados para inserção
	private static final String ATUALIZAR_VENDA = 
	"update venda set"  + 
	"id_fornecedor=? ," +
	"nome=? ," +
	"preco=? ," +
	"quantidade_estoque=?"  +
	"where id_venda=? "
	;
	
	private static String CONSULTA_VENDA = 
			"select * from venda order by id_venda";

	private static String CONSULTA_VENDA_ID =
			"select * from venda where id_venda = ? ";
	
	private static String ATUALIZAR_ESTOQUE =
			"update produto set quantidade_estoque = ?" +
			"where id_produto = ?";
	
		
	private static String CONSULTA_VENDA_CLIENTE =
			"select * from venda where id_cliente = ? ";
	
	
	private static String CONSULTAR_ULTIMA_VENDA =
			"select MAX(id_venda)'Id_venda'from venda";
	
	private static String CONSULTA_VENDA_FUNCIONARIO =
			"select * from venda where id_func = ? ";
		

	private static String CONSULTA_VENDA_DATA =
			"select * from venda where data = ? ";
	
	private static String CONSULTA_PRODUTO_CLIENTE_ID= 
			"select * from venda where id_cliente like ? order by id_cliente";
	
	private static String CONSULTA_PRODUTO_CLIENTE_NOME= 
	
"Select a.id_venda 'ID_VENDA', a.id_cliente 'Id_venda_cliente', b.nome 'Nome_Cliente'  From venda a inner join cliente b" +
"on a.id_cliente = b.id_cliente WHERE a.id_cliente = ?";

	
	private static String CONSULTA_NOME_FUNCIONARIO_IDVENDA=
"Select a.id_venda, a.id_func, b.id_func, b.nome_func from venda a inner join funcionario b on a.id_func = b.id_func where a.id_func=?";			
	

//	private static String NOME_FORNECEDOR = 
//			"select * from fornecedor where id_produto ? ";
	
	
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



	public static List<VendaBean> consultarVendaNomeFuncionario (String id_func) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<VendaBean> listaVenda= new ArrayList<VendaBean>();
		try {
			if(id_func.equals("")){
				statement = conn.prepareStatement(CONSULTA_VENDA);
			}else{
				statement = conn.prepareStatement(CONSULTA_NOME_FUNCIONARIO_IDVENDA);
				statement.setString(1, id_func);
			}
			
			result = statement.executeQuery();
			while (result.next()) {
				VendaBean objProd = new VendaBean();
				
				objProd.setId_venda(result.getInt(1));
				objProd.setId_func(result.getInt(2));
				objProd.setId_cliente(result.getInt(3));
				objProd.setValortotal(result.getFloat(4));
				objProd.setValorpago(result.getFloat(5));
				objProd.setForma_pagamento(result.getString(6));
				
				listaVenda.add(objProd);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaVenda;		
	}
	

	public static VendaBean consultarUltimaVenda () throws exception{ 				// DUVIDOSA		
		VendaBean objProd = new VendaBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		String UltimaVenda ;
		try {	statement = conn.prepareStatement(CONSULTAR_ULTIMA_VENDA);
			
			result = statement.executeQuery();
			while (result.next()) {
				
				
				objProd.setId_venda(result.getInt(1));
				UltimaVenda=String.valueOf(objProd.getId_venda());
				
				if(UltimaVenda.equals("")){
					objProd.setId_venda(0);					
				}
						
			}

			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objProd;		
	}
	
	
	public static List<VendaBean> consultarVendaCliente (String id_cliente) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<VendaBean> listaVenda= new ArrayList<VendaBean>();
		try {
			if(!id_cliente.equals("")){
				statement = conn.prepareStatement(CONSULTA_VENDA_CLIENTE);
				int id_cliente2 = Integer.parseInt(id_cliente);
				statement.setInt(1, id_cliente2);
								
			}else{
				statement = conn.prepareStatement(CONSULTA_VENDA);
			}
			result = statement.executeQuery();
			while (result.next()) {
				VendaBean objProd = new VendaBean();
				
				objProd.setId_venda(result.getInt(1));
				objProd.setId_func(result.getInt(2));
				objProd.setId_cliente(result.getInt(3));
				objProd.setValortotal(result.getFloat(4));
				objProd.setValorpago(result.getFloat(5));
				objProd.setForma_pagamento(result.getString(6));
				objProd.setData(result.getString(7));
				objProd.setHora(result.getString(8));
				
				listaVenda.add(objProd);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaVenda;		
	}


	public static List<VendaBean> consultarVendaFuncionario (int id_func) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<VendaBean> listaVenda= new ArrayList<VendaBean>();
		try {
			if(id_func!=0){
				statement = conn.prepareStatement(CONSULTA_VENDA_FUNCIONARIO);
				statement.setInt(1, id_func);
								
			}else{
				statement = conn.prepareStatement(CONSULTA_VENDA);
			}
			result = statement.executeQuery();
			while (result.next()) {
				VendaBean objProd = new VendaBean();
				
				objProd.setId_venda(result.getInt(1));
				objProd.setId_func(result.getInt(2));
				objProd.setId_cliente(result.getInt(3));
				objProd.setValortotal(result.getFloat(4));
				objProd.setValorpago(result.getFloat(5));
				objProd.setForma_pagamento(result.getString(6));
				objProd.setData(result.getString(7));
				objProd.setHora(result.getString(8));
				
				listaVenda.add(objProd);
						
			}

			

		} catch (SQLException e) {
			
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaVenda;		
	}
	
	public static List<VendaBean> consultarVendaData (String data) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<VendaBean> listaVenda= new ArrayList<VendaBean>();
		try {
			if(!data.equals("")){
				statement = conn.prepareStatement(CONSULTA_VENDA_DATA);
				statement.setString(1, data);
								
			}else{
				statement = conn.prepareStatement(CONSULTA_VENDA);
			}
			result = statement.executeQuery();
			while (result.next()) {
				VendaBean objProd = new VendaBean();
				
				objProd.setId_venda(result.getInt(1));
				objProd.setId_func(result.getInt(2));
				objProd.setId_cliente(result.getInt(3));
				objProd.setValortotal(result.getFloat(4));
				objProd.setValorpago(result.getFloat(5));
				objProd.setForma_pagamento(result.getString(6));
				objProd.setData(result.getString(7));
				objProd.setHora(result.getString(8));
				
				listaVenda.add(objProd);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaVenda;		
	}
	
	
	
	public static List<VendaBean> consultarVenda (String id_venda) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<VendaBean> listaVenda= new ArrayList<VendaBean>();
		try {
			if(!id_venda.equals("")){
				statement = conn.prepareStatement(CONSULTA_VENDA_ID);
				int id2 = Integer.parseInt(id_venda);
				statement.setInt(1, id2);
								
			}else{
				statement = conn.prepareStatement(CONSULTA_VENDA);
			}
			result = statement.executeQuery();
			while (result.next()) {
				VendaBean objProd = new VendaBean();
				
				objProd.setId_venda(result.getInt(1));
				objProd.setId_func(result.getInt(2));
				objProd.setId_cliente(result.getInt(3));
				objProd.setValortotal(result.getFloat(4));
				objProd.setValorpago(result.getFloat(5));
				objProd.setForma_pagamento(result.getString(6));
				objProd.setData(result.getString(7));
				objProd.setHora(result.getString(8));
				listaVenda.add(objProd);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaVenda;		
	}

	public static List<VendaBean> consultarVendaID(String id_venda) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<VendaBean> listaVenda= new ArrayList<VendaBean>();
		try {
			if(!id_venda.equals("")){
				statement = conn.prepareStatement(CONSULTA_VENDA_ID);
				statement.setString(1, id_venda);
				
								
			}else{

				statement = conn.prepareStatement(CONSULTA_VENDA);
				
			}			result = statement.executeQuery();
			while (result.next()) {
				VendaBean objProd = new VendaBean();
				
				objProd.setId_venda(result.getInt(1));
				objProd.setId_func(result.getInt(2));
				objProd.setId_cliente(result.getInt(3));
				objProd.setValortotal(result.getFloat(4));
				objProd.setValorpago(result.getFloat(5));
				objProd.setForma_pagamento(result.getString(6));
				objProd.setData(result.getString(7));
				objProd.setHora(result.getString(8));
				
				listaVenda.add(objProd);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaVenda;		
	}

	
	public static List<VendaBean> consultaVendaCliente (int id) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<VendaBean> listaVenda= new ArrayList<VendaBean>();
		
		try {
			if(id<=0){
				JOptionPane.showMessageDialog(null, "Identificação incorreta!");
			}else{
				statement = conn.prepareStatement(CONSULTA_PRODUTO_CLIENTE_ID);
				statement.setInt(1, id);
			
			result = statement.executeQuery();
			while (result.next()) {
				
				JOptionPane.showMessageDialog(null, "Esta chegando até aqui!3");
				

				VendaBean objProd = new VendaBean();
				
				objProd.setId_venda(result.getInt(1));
				objProd.setId_func(result.getInt(2));
				objProd.setId_cliente(result.getInt(3));
				objProd.setValortotal(result.getFloat(4));
				objProd.setValorpago(result.getFloat(5));
				
				listaVenda.add(objProd);
				
				
				
				
			}
			}
			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}

		return listaVenda;
		
	}

	
//	
//	public static List<ProdutoBean> consultarProdutoID(int id) throws exception{ 
//		ProdutoBean objProd = new ProdutoBean();
//		Connection conn = sql.getConnection();
//		PreparedStatement statement = null;
//		ResultSet result = null;
//		List<ProdutoBean> listaProd= new ArrayList<ProdutoBean>();
//		
//		try {
//			
//				if(id==0){
//					statement = conn.prepareStatement(CONSULTA_PRODUTO);
//				}else{
//					statement = conn.prepareStatement(CONSULTA_PRODUTO_ID);
//					statement.setInt(1, id);
//				}
//
//			result = statement.executeQuery();
//			while (result.next()) {
//				
//			objProd.setId_produto(result.getInt(1));	
//			objProd.setId_fornecedor(result.getInt(2));
//			objProd.setNome(result.getString(3));
//			objProd.setPreco(result.getFloat(4));
//			objProd.setQuantidade(result.getInt(5));
//			
//			}
//		} catch (SQLException e) {
//			throw new exception(e);
//		} finally {
//			sql.close(conn, statement, result);
//		}
//		return listaProd;		
//	}

	
	public static boolean inserirVenda(VendaBean obj) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ADICIONAR_VENDA);
			statement.setInt(1, obj.getId_func());
			statement.setInt(2, obj.getId_cliente());
			statement.setFloat(3, obj.getValortotal());
			statement.setFloat(4, obj.getValorpago());
			statement.setString(5, obj.getForma_pagamento());
			statement.setString(6, obj.getData());
			statement.setString(7, obj.getHora());
			
			
//			statement.setDate(parameterIndex, x)(2, java.sql.Date.valueOf(new Data().formataDataBD()));
			
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	

//	public static List<VendaBean> consultaVendaClienteNome (String nome) throws exception{ 				// DUVIDOSA		
//		Connection conn = sql.getConnection();
//		PreparedStatement statement = null;
//		ResultSet result = null;
//		List<VendaBean> listaVenda= new ArrayList<VendaBean>();
//		
//		
//		
//		if(nome.equals("")){
//			statement = conn.prepareStatement(CONSULTA_CLIENTE);
//		}else{
//			statement = conn.prepareStatement(CONSULTA_CLIENTE_NOME);
//			statement.setString(1, "%"+nome+"%");
//		}
//		
//		
//		
//		
//		try {
//			if(nome.equals("")){
//				statement = conn.prepareStatement(CONSULTA_PRODUTO_CLIENTE_NOME);
//			}else{
//				statement = conn.prepareStatement(CONSULTA_PRODUTO_CLIENTE_ID);
//				statement.setInt(1, id);
//			
//			result = statement.executeQuery();
//			while (result.next()) {
//				
//				JOptionPane.showMessageDialog(null, "Esta chegando até aqui!3");
//				
//
//				VendaBean objProd = new VendaBean();
//				
//				objProd.setId_venda(result.getInt(1));
//				objProd.setId_func(result.getInt(2));
//				objProd.setId_cliente(result.getInt(3));
//				objProd.setValortotal(result.getFloat(4));
//				objProd.setValorpago(result.getFloat(5));
//				
//				listaVenda.add(objProd);
//				
//				
//				
//				
//			}
//			}
//			
//
//		} catch (SQLException e) {
//			throw new exception(e);
//		} finally {
//			sql.close(conn, statement, result);
//		}
//
//		return listaVenda;
//	}	
	public static boolean excluirVenda(int id_venda) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_VENDA);
			statement.setInt(1, id_venda);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	public static boolean atualizarEstoque(int quantidade_estoque, String id_produto) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_ESTOQUE);
			statement.setInt(1, quantidade_estoque);
			statement.setString(2, id_produto);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	
}


