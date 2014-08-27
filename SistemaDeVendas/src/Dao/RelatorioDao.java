package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Beans.FuncionarioBean;
import Beans.PedidoBean;
import Beans.RelatorioBean;
import Conexao.exception;
import Conexao.sql;

public class RelatorioDao {

	
	private static String BONUS_FUNCIONARIO_PT1 =
			"SELECT venda.id_func, " +
			"funcionario.nome_func," +
			"funcionario.cpf_func, " +
			"funcionario.caminho_imagem " +
			"FROM   venda " +
			"inner join funcionario" +
			" on venda.id_func = funcionario.id_func" +
			" WHERE  venda.id_func =(SELECT max(venda.id_func)" +
			" FROM venda)";
		
	private static String BONUS_FUNCIONARIO_PT2 =
		"SELECT count (venda.id_func)'QuantidadeDeVendas'" +
		" FROM   venda inner join funcionario on venda.id_func = funcionario.id_func " +
		"WHERE  venda.id_func = (SELECT max(venda.id_func) FROM venda)";
	
	private static String GERAR_FOLHA_PAGAMENTO =
			"SELECT " +
			"id_func, " +
			"nome_func, " +
			"salario_func, " +
			"cpts, " +
			"funcao_func, " +
			"cpf_func, " +
			"inicio_func, " +
			"termino_func" +
			" from funcionario where id_func = ?";

	private static String GERAR_FOLHA_PAGAMENTO2 =
			
			"SELECT id_func, "+
			"nome_func, "+
			"salario_func, "+
			"cpts, "+
			"funcao_func, "+
			"cpf_func, "+
			"inicio_func, "+
			"termino_func "+
			"from funcionario";


	private static String GERAR_FOLHA_RELATORIO_COMPLETO =

	"Select a.id_pedido, "+
	"a.num_pedido ,"+
	"a.id_produto, "+
	"a.id_cliente, "+
	"a.id_func, "+
	"a.qtd_produto, "+
	"a.preco_produto_unitario, "+
	"a.preco_produto_total, "+
	"a.forma_pagamento, "+
	"b.nome, "+
	"b.caminho_imagem, "+
	"b.quantidade_estoque, "+
	"cliente.cpf, "+
	"d.nome_func "+
	"From pedido a "+
	"inner join produto b "+
	"on a.id_produto = b.id_produto "+
	"inner join cliente "+
	"on a.id_cliente = cliente.id_cliente "+
	"inner join funcionario d "+
	"on a.id_func = d.id_func "+
	"where num_pedido = ?";
	
	
	private static String GERAR_FOLHA_VENDA =

			
			"Select a.num_pedido, "+ 
			"b.nome, "+
			"a.qtd_produto, "+
			"a.preco_produto_unitario, "+
			"a.preco_produto_total, "+
			"c.cpf,	"+
			"d.valor_total, " +
			"d.valor_pago "+
			"From pedido a "+
			"inner join produto b "+
			"on a.id_produto = b.id_produto "+
			"inner join cliente c "+
			"on a.id_cliente = c.id_cliente "+
			"inner join venda d "+
			"on a.num_pedido = d.id_venda "+
			"where num_pedido = ?" ;
			
			
			
			
			
//"Select a.num_pedido, " +
//"b.nome ," +
//"a.qtd_produto, " +
//"a.preco_produto_unitario, " +
//"a.preco_produto_total, " +
//"c.cpf, " +
//"d.valor_total " + 
//"From pedido a" +
//"inner join produto b " +
//"on a.id_produto = b.id_produto " +
//"inner join cliente c " +
//"on a.id_cliente = c.id_cliente " +
//"inner join venda d " +
//"on a.num_pedido = d.id_venda " +
//" where num_pedido = ?";	
	
	public static RelatorioBean funcionarioMesPt1() throws exception{
		RelatorioBean objRelatorio = new RelatorioBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(BONUS_FUNCIONARIO_PT1);
			result = statement.executeQuery();
			while (result.next()) {
				objRelatorio.setId_func(result.getInt(1));
				objRelatorio.setNome_func(result.getString(2));
				objRelatorio.setCpf_func(result.getString(3));
				objRelatorio.setCaminho_imagem(result.getString(4));						
			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objRelatorio;		
	}

	public static RelatorioBean funcionarioMesPt2() throws exception{
		RelatorioBean objRelatorio = new RelatorioBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(BONUS_FUNCIONARIO_PT2);
			result = statement.executeQuery();
			while (result.next()) {
				objRelatorio.setQtd_vendas(result.getInt(1));			}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objRelatorio;		
	}
	
	public static List<FuncionarioBean> gerarPagamento(int id) throws exception{
		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<FuncionarioBean> ListaRelatorio = new ArrayList<FuncionarioBean>();
		try {
			if(id<=0){
				statement = conn.prepareStatement(GERAR_FOLHA_PAGAMENTO2);
				
			}else{
				statement = conn.prepareStatement(GERAR_FOLHA_PAGAMENTO);
				statement.setInt(1,id);
					
			}
			
			result = statement.executeQuery();
			while (result.next()) {
				FuncionarioBean objRelatorio = new FuncionarioBean();
				objRelatorio.setId_func(result.getInt(1));
				objRelatorio.setNome_func(result.getString(2));
				objRelatorio.setSalario_func(result.getFloat(3));
				objRelatorio.setCpts(result.getString(4));
				objRelatorio.setFuncao_func(result.getString(5));
				objRelatorio.setCpf_func(result.getString(6));
				objRelatorio.setInicio_func(result.getString(7));
				objRelatorio.setTermino_func(result.getString(8));
				ListaRelatorio.add(objRelatorio);
				
				}

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return ListaRelatorio;
	}
		
			public static List<RelatorioBean> gerarVenda(String id) throws exception{
				
				Connection conn = sql.getConnection();
				PreparedStatement statement = null;
				ResultSet result = null;
				List<RelatorioBean> ListaRelatorio = new ArrayList<RelatorioBean>();
				try {
					statement = conn.prepareStatement(GERAR_FOLHA_VENDA);
					statement.setString(1,id);
					result = statement.executeQuery();
					while (result.next()) {
						DecimalFormat df = new DecimalFormat();
						RelatorioBean objRelatorio = new RelatorioBean();
						
						objRelatorio.setNum_pedido(result.getInt(1));
						objRelatorio.setNome(result.getString(2));
						objRelatorio.setQtd_produto(result.getInt(3));
						objRelatorio.setPreco_produto_unitario(result.getFloat(4));
						objRelatorio.setPreco_produto_total(result.getFloat(5));
						objRelatorio.setCpf(result.getString(6));
						objRelatorio.setValor_total(result.getFloat(7));
						objRelatorio.setValor_pago(result.getFloat(8));
						ListaRelatorio.add(objRelatorio);
						
						}
		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return ListaRelatorio;		
	}
	
	
	public static List<PedidoBean> gerarRelatorioCompleto(String id) throws exception{
		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<PedidoBean> ListaRelatorio = new ArrayList<PedidoBean>();
		try {
			statement = conn.prepareStatement(GERAR_FOLHA_RELATORIO_COMPLETO);
			statement.setString(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				PedidoBean objRelatorio = new PedidoBean();
				objRelatorio.setId_pedido(result.getInt(1));
//				JOptionPane.showMessageDialog(null, "Id Pedido> " +objRelatorio.getId_pedido());
				objRelatorio.setNum_pedido(result.getString(2));
//				JOptionPane.showMessageDialog(null, "Num Pedido> " +objRelatorio.getNum_pedido());
				objRelatorio.setId_produto(result.getInt(3));
//				JOptionPane.showMessageDialog(null, "Id Produto> " +objRelatorio.getId_produto());
				objRelatorio.setId_cliente(result.getInt(4));
//				JOptionPane.showMessageDialog(null, "Id Cliente> " +objRelatorio.getId_cliente());
				objRelatorio.setId_func(result.getInt(5));
//				JOptionPane.showMessageDialog(null, "Id Func> " +objRelatorio.getId_func());
				objRelatorio.setQtd_produto(result.getInt(6));
//				JOptionPane.showMessageDialog(null, "Qtd Produto> " +objRelatorio.getQtd_produto());
				objRelatorio.setPreco_produto_unitario(result.getFloat(7));
//				JOptionPane.showMessageDialog(null, "Preco Unit> " +objRelatorio.getPreco_produto_unitario());
				objRelatorio.setPreco_produto_total(result.getFloat(8));
//				JOptionPane.showMessageDialog(null, "Preco Total> " +objRelatorio.getPreco_produto_total());
				objRelatorio.setForma_pagamento(result.getString(9));
//				JOptionPane.showMessageDialog(null, "Forma Pagamento> " +objRelatorio.getForma_pagamento());
				objRelatorio.setNome(result.getString(10));
//				JOptionPane.showMessageDialog(null, "Nome_produto> " +objRelatorio.getNome());
				objRelatorio.setCaminho_imagem(result.getString(11));
//				JOptionPane.showMessageDialog(null, "Caminho Imagem> " +objRelatorio.getCaminho_imagem());
				objRelatorio.setQuantidade_estoque(result.getInt(12));
//				JOptionPane.showMessageDialog(null, "Quantidade_ Estoque> " +objRelatorio.getQuantidade_estoque());
				objRelatorio.setCpf_cliente(result.getString(13));
//				JOptionPane.showMessageDialog(null, "Cpf cliente> " +objRelatorio.getCpf_cliente());
				objRelatorio.setNome_func(result.getString(14));
//				JOptionPane.showMessageDialog(null, "Nome _ func> " +objRelatorio.getNome_func());
				ListaRelatorio.add(objRelatorio);
				
				}

			

		} catch (SQLException e) {
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return ListaRelatorio;		
	}
		
	
}
