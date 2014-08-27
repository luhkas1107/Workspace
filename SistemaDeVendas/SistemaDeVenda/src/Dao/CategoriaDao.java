package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Beans.CategoriaBean;
import Beans.FornecedorBean;
import Beans.FuncionarioBean;
import Beans.LoginBean;
import Conexao.exception;
import Conexao.sql;

public class CategoriaDao {

	private static final String EXCLUIR_CATEGORIA = 
			"delete from categoria where id_categoria= ?";
	
	private static final String INSERIR_CATEGORIA =
			"insert into categoria (nome_categoria)"+
			"values(?)";

		private static final String CONSULTA_CATEGORIA_NOME =
				"select * from categoria where nome_categoria like ? order by nome_categoria";
		private static final String CONSULTA_CATEGORIA =
				"select * from categoria";

		
		private static final String ATUALIZAR_CATEGORIA = 
				"update categoria set nome_categoria = ? "+
				"where id_categoria = ? "
				;

		
		public boolean inserirCategoria(CategoriaBean obj) throws exception{		
			Connection conn = sql.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(INSERIR_CATEGORIA);
				
				statement.setString(1, obj.getNome_categoria());

				
				statement.executeUpdate();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar." +
						"\nFavor veririficar se já existi categoria cadastrada.", "Cuidado!!!", JOptionPane.ERROR_MESSAGE, null);
				throw new exception(e);
			} finally {
				sql.close(conn, statement, result);
			}
			return true;		
		}
		
		public boolean atualizarCategoria(CategoriaBean obj) throws exception{		
			Connection conn = sql.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(ATUALIZAR_CATEGORIA);
				statement.setString(1, obj.getNome_categoria());
				statement.setInt(2, obj.getId_categoria());
				

				
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new exception(e);
			} finally {
				sql.close(conn, statement, result);
			}
			return true;		
		}
		

		public static List<CategoriaBean> consultarCategoria(String nome) throws exception{ 				// DUVIDOSA		
			Connection conn = sql.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			List<CategoriaBean> listaForn = new ArrayList<CategoriaBean>();
			try {
				if(nome.equals("")){
					statement = conn.prepareStatement(CONSULTA_CATEGORIA);
				}else{
					statement = conn.prepareStatement(CONSULTA_CATEGORIA_NOME);
					statement.setString(1, "%"+nome+"%");
				}

				result = statement.executeQuery();
				while (result.next()) {
					CategoriaBean objForn = new CategoriaBean();
					objForn.setId_categoria(result.getInt(1));				
					objForn.setNome_categoria(result.getString(2));				
					listaForn.add(objForn);
							
				}

				

			} catch (SQLException e) {
				throw new exception(e);
			} finally {
				sql.close(conn, statement, result);
			}
			return listaForn;		
		}

		public static boolean excluirCategoria(Integer id_categoria) throws exception{		
			Connection conn = sql.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(EXCLUIR_CATEGORIA);
				statement.setInt(1, id_categoria);
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new exception(e);
			} finally {
				sql.close(conn, statement, result);
			}
			return true;		
		}

		
}
