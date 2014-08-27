package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Beans.FornecedorBean;
import Beans.PedidoBean;
import Conexao.exception;
import Conexao.sql;

public class PedidoDao {

	private static final String EXCLUIR_PEDIDO = 
			"delete from pedido where id_pedido = ?";
	private static final String INSERIR_PEDIDO =
			"insert into pedido (num_pedido," +
			"id_produto," +											
			"id_cliente," +
			"id_func," +
			"qtd_produto," +
			"preco_produto_unitario," +
			"preco_produto_total," +
			"forma_pagamento)" +
			"values (?,?,?,?,?,?,?,?)";
	 // aki vem os dados para inserção
	private static final String ATUALIZAR_PEDIDO = 
			"update pedido set "  + "num_pedido = ?, " +
			"id_produto = ?, " +
			"id_cliente = ?," +
			"id_func = ?, " +
			"qtd_produto = ?, " +
			"preco_produto_unitario = ?, " +
			"preco_produto_total = ?, " +
			"forma_pagamento = ? " +
			
			"where id_venda = ? "
			;


	private static String CONSULTA_PEDIDO =
			"select * from pedido order by num_pedido";
//			"select * from fornecedor";
	private static String CONSULTA_NUM_PEDIDO =
			"Select a.id_pedido,"+ 
			"a.num_pedido ,"+
			"a.id_produto,"+
			"a.id_cliente,"+
			"a.id_func,"+
			"a.qtd_produto,"+
			"a.preco_produto_unitario,"+
			"a.preco_produto_total,"+
			"a.forma_pagamento,"+
			"b.nome, " +
			"b.caminho_imagem," +
			"b.quantidade_estoque "+
			"From pedido a "+
			"inner join produto b "+
			"on a.id_produto = b.id_produto "+
			"WHERE a.num_pedido = ?";

	private static String CONSULTA_PEDIDO_CLIENTE_ID =
			//"select * from fornecedor order by nome";
			"select * from pedido where id_cliente = ?";
	private String CONSULTA_FUNCIONARIO_ID = 
			"select * from pedido where id_func = ?";
	
	
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


	
	
	
	
	
	
	public static List<PedidoBean> consultarPedido(int id) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<PedidoBean> listaPedido = new ArrayList<PedidoBean>();
		try {
			if(id<=0){
				statement = conn.prepareStatement(CONSULTA_PEDIDO);
			}else{
				statement = conn.prepareStatement(CONSULTA_NUM_PEDIDO);
				statement.setInt(1, id);
			}

			result = statement.executeQuery();
			while (result.next()) {
				PedidoBean objPedido = new PedidoBean();
				objPedido.setId_pedido(result.getInt(1));
				objPedido.setNum_pedido(result.getString(2));
				objPedido.setId_produto(result.getInt(3));
				objPedido.setId_cliente(result.getInt(4));
				objPedido.setId_func(result.getInt(5));
				objPedido.setQtd_produto(result.getInt(6));
				objPedido.setPreco_produto_unitario(result.getFloat(7));
				objPedido.setPreco_produto_total(result.getFloat(8));
				objPedido.setForma_pagamento(result.getString(9));
				objPedido.setNome_produto(result.getString(10));
				objPedido.setCaminho_imagem(result.getString(11));
				objPedido.setQuantidade_estoque(result.getInt(12));
				listaPedido.add(objPedido);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaPedido;		
	}

	public PedidoBean consultarNumPedidoClienteID(int id) throws exception{		
		PedidoBean objPedido = new PedidoBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_PEDIDO_CLIENTE_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				
				
				
				
				
				objPedido.setNum_pedido(result.getString(1));
				objPedido.setId_produto(result.getInt(2));
				objPedido.setId_cliente(result.getInt(3));
				objPedido.setId_func(result.getInt(4));
				objPedido.setQtd_produto(result.getInt(5));
				objPedido.setPreco_produto_unitario(result.getFloat(6));
				objPedido.setPreco_produto_total(result.getFloat(7));
				objPedido.setForma_pagamento(result.getString(8));
								
			}
		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objPedido;		
	}
	
	public boolean inserirPedido(PedidoBean obj) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_PEDIDO);
			statement.setString(1, obj.getNum_pedido());
			statement.setInt(2, obj.getId_produto());
			statement.setInt(3, obj.getId_cliente());
			statement.setInt(4, obj.getId_func());
			statement.setInt(5, obj.getQtd_produto());
			statement.setFloat(6, obj.getPreco_produto_unitario());
			statement.setFloat(7, obj.getPreco_produto_total());
			statement.setString(8, obj.getForma_pagamento());
			statement.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	
	public boolean atualizarPedido(PedidoBean objPedido) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_PEDIDO);
			statement.setString(1, objPedido.getNum_pedido());
			statement.setInt(2, objPedido.getId_produto());
			statement.setInt(3, objPedido.getId_cliente());
			statement.setInt(4, objPedido.getId_func());
			statement.setInt(5, objPedido.getQtd_produto());
			statement.setFloat(6, objPedido.getPreco_produto_unitario());
			statement.setFloat(7, objPedido.getPreco_produto_total());
			statement.setString(8, objPedido.getForma_pagamento());
			statement.setInt(9, objPedido.getId_pedido());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
		
	public static boolean excluirPedido(Integer id) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_PEDIDO);
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	
	
	
	
	
}
