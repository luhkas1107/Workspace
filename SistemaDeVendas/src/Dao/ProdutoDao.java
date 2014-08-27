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
import Beans.ProdutoBean;
public class ProdutoDao {


	private static final String EXCLUIR_PRODUTO = 
			"delete from produto where id_produto = ?";
	private static final String INSERIR_PRODUTO =
			"insert into produto(id_fornecedor," +
			"nome," +											
			"preco," +
			"quantidade_estoque, " +
			"caminho_imagem, " +
			"preco_venda, " +
			"categoria)" +
			"values (?,?,?,?,?,?,?)";
	 // aki vem os dados para inserção
	private static final String ATUALIZAR_PRODUTO =
	"update produto set "  + 
	"id_fornecedor = ?, " +
	"nome = ?, " +
	"preco = ?, " +	
	"quantidade_estoque = ?, "  +
	"caminho_imagem = ?, " +
	"preco_venda = ?," +
	"categoria = ? "  +
	"where id_produto = ?"
	;
	
	private static String CONSULTA_PRODUTO = 
			"select * from produto order by quantidade_estoque ASC";
	
	private static String ATUALIZACAO_ESTOQUE = 
			"update produto set "  + 
					"quantidade_estoque = ? "  +
					"where id_produto = ? "
					;
					

	private static String CONSULTA_PRODUTO_ID =
			"select * from produto where id_produto = ? ";
	
	
	private static String CONSULTA_PRODUTO_NOME = 
			"select * from produto where nome like ? order by nome";
	
	private static String NOME_FORNECEDOR = 
			
			"Select a.id_produto,"+
			"a.id_fornecedor ,"+
			"a.nome,"+
			"a.preco,"+
			"a.quantidade_estoque,"+
			"a.caminho_imagem,"+
			"a.preco_venda,"+
			"a.categoria,"+
			"b.empresa "+
			"From produto a "+
			"inner join fornecedor b "+
			"on a.id_fornecedor = b.id_fornecedor "+
			"where a.id_produto = ?";
			
	
	


	public static List<ProdutoBean> consultarProduto (String nome) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ProdutoBean> listaProd= new ArrayList<ProdutoBean>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_PRODUTO);
			}else{
				statement = conn.prepareStatement(CONSULTA_PRODUTO_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				ProdutoBean objProd = new ProdutoBean();
				
				objProd.setId_produto(result.getInt(1));
				objProd.setId_fornecedor(result.getInt(2));
				objProd.setNome(result.getString(3));
				objProd.setPreco(result.getFloat(4));
				objProd.setQuantidade(result.getInt(5));
				objProd.setCaminho(result.getString(6));
				objProd.setPreco_venda(result.getFloat(7));
				objProd.setCategoria(result.getString(8));
				listaProd.add(objProd);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaProd;		
	}
	
	
	public static List<ProdutoBean> consultarProdutoParaPesquisaDoComboFornecedor (int id) throws exception{ 				// DUVIDOSA		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ProdutoBean> listaProd= new ArrayList<ProdutoBean>();
		try {
				statement = conn.prepareStatement(NOME_FORNECEDOR);
				statement.setInt(1, id);
			
			result = statement.executeQuery();
			while (result.next()) {
				ProdutoBean objProd = new ProdutoBean();
				
				objProd.setId_produto(result.getInt(1));
				objProd.setId_fornecedor(result.getInt(2));
				objProd.setNome(result.getString(3));
				objProd.setPreco(result.getFloat(4));
				objProd.setQuantidade(result.getInt(5));
				objProd.setCaminho(result.getString(6));
				objProd.setPreco_venda(result.getFloat(7));
				objProd.setCategoria(result.getString(8));
				objProd.setEmpresa_fornecedor(result.getString(9));
				listaProd.add(objProd);
						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaProd;		
	}
	

	public static ProdutoBean consultaProdutoVenda (int id) throws exception{ 				// DUVIDOSA
		ProdutoBean objProd = new ProdutoBean();
		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

//		List<ProdutoBean> listaProd= new ArrayList<ProdutoBean>();
		
		try {
			if(id<=0){
				JOptionPane.showMessageDialog(null, "Identificação incorreta!");
			}else{
				
				
				statement = conn.prepareStatement(CONSULTA_PRODUTO_ID);
				statement.setInt(1, id);
			
			result = statement.executeQuery();
			while (result.next()) {
				
				objProd.setId_produto(result.getInt(1));
				objProd.setId_fornecedor(result.getInt(2));
				objProd.setNome(result.getString(3));
				objProd.setPreco(result.getFloat(4));
				objProd.setQuantidade(result.getInt(5));
				objProd.setCaminho(result.getString(6));
				objProd.setPreco_venda(result.getFloat(7));
				objProd.setCategoria(result.getString(8));
				
//				JOptionPane.showMessageDialog(null, "Nome do produto "+objProd.getNome());
				if (objProd.getNome().equals("")){
					
				}
			}
			}
			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro! Por favor verifique o codígo do produto corretamente.");
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}

		return objProd;
		
	}

	
	
	public static List<ProdutoBean> consultarProdutoID(int id) throws exception{ 
		ProdutoBean objProd = new ProdutoBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ProdutoBean> listaProd= new ArrayList<ProdutoBean>();
		
		try {
			if(id<=0){
			
				statement = conn.prepareStatement(CONSULTA_PRODUTO);
	
			}else{
				
			
					statement = conn.prepareStatement(CONSULTA_PRODUTO_ID);
					statement.setInt(1, id);
			}
			result = statement.executeQuery();
			while (result.next()) {
				
			objProd.setId_produto(result.getInt(1));	
//			JOptionPane.showMessageDialog(null, "Id> "+objProd.getId_produto());
			objProd.setId_fornecedor(result.getInt(2));
//			JOptionPane.showMessageDialog(null, "Id> "+objProd.getId_fornecedor());
			objProd.setNome(result.getString(3));
//			JOptionPane.showMessageDialog(null, "Id> "+objProd.getNome());
			objProd.setPreco(result.getFloat(4));
//			JOptionPane.showMessageDialog(null, "Id> "+objProd.getPreco());
			objProd.setQuantidade(result.getInt(5));
			objProd.setCaminho(result.getString(6));
			objProd.setPreco_venda(result.getFloat(7));
			objProd.setCategoria(result.getString(8));
			listaProd.add(objProd);
			
			}
		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return listaProd;		
	}

	public static ProdutoBean consultarProdutoParaVenda(int id) throws exception{ 
		ProdutoBean objProd = new ProdutoBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			
			statement = conn.prepareStatement(CONSULTA_PRODUTO_ID);
			statement.setInt(1, id);

			result = statement.executeQuery();
			while (result.next()) {
			objProd.setId_produto(result.getInt(1));
			objProd.setQuantidade(result.getInt(5));
			
			
			
			
			}
		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objProd;		
	}
	
	
	
	public boolean inserirProduto(ProdutoBean obj) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_PRODUTO);
			statement.setInt(1, obj.getId_fornecedor());
			statement.setString(2, obj.getNome());
			statement.setFloat(3, obj.getPreco());
			statement.setInt(4, obj.getQuantidade());
			statement.setString(5, obj.getCaminho());
			statement.setFloat(6, obj.getPreco_venda());
			statement.setString(7, obj.getCategoria());
			
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	public static boolean atualizarProduto(ProdutoBean objProd) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_PRODUTO);
			
			statement.setInt(1, objProd.getId_fornecedor());
			statement.setString(2, objProd.getNome());
			statement.setFloat(3, objProd.getPreco());
			statement.setInt(4, objProd.getQuantidade());
			statement.setString(5, objProd.getCaminho());
			statement.setFloat(6, objProd.getPreco_venda());
			statement.setString(7, objProd.getCategoria());
			statement.setInt(8, objProd.getId_produto());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	public static boolean atualizarEstoque(ProdutoBean objProd) throws exception{		
		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZACAO_ESTOQUE);
			
			statement.setInt(1, objProd.getQuantidade());
			statement.setInt(2, objProd.getId_produto());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	
	
	public static boolean excluirProduto(int id_produto) throws exception{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_PRODUTO);
			statement.setInt(1, id_produto);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	
}


