package Dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import Beans.AssinaturaBean;
import Conexao.exception;
import Conexao.sql;
import Log.Log;
public class AssinaturaDao {

	static Date dataHoje = new Date();
	static SimpleDateFormat formataData = new SimpleDateFormat(
	"dd/MM/yyyy HH:mm:ss:SSSS");
	static SimpleDateFormat formataDataArq = new SimpleDateFormat(
			"ddMMyyyyHH");
	static String agora = formataData.format(dataHoje);
	static String nomeArq = formataDataArq.format(dataHoje);
	
	static Logger logger = Logger.getLogger(AssinaturaDao.class);  
	
	public static String tela = "Assinatura_Dao";

	private static final String CONTAGEM =
	"exec PROC_P_CONTASSPAC";

	private static final String INSERIR_ASSINATURA =
	"EXEC PROC_I_ASSPAC ?";

	private static final String ATUALIZAR_ASSINATURA_DESATIVADO =
	"EXEC PROC_A_ASSPAC ?";
	
	private static final String ATUALIZAR_ASSINATURA_ATIVADO =
	"EXEC PROC_A_ASSPAC_ATIVO ?,?,?,?,?,? ";
	
	private static String CONSULTA_ASSINATURA= 
	"EXEC PROC_P_AT";

	
	public static AssinaturaBean consultarAssinatura() throws exception, IOException{ 				// DUVIDOSA

		Appender fileAppender = new FileAppender(  
 		new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN), "Log\\"+tela+"_"+nomeArq+"_Service.log");  
		logger.addAppender(fileAppender);  

		logger.info(agora+" ------------------- Iniciando Consulta à Assinatura  ------------------- ");
		AssinaturaBean objProd = new AssinaturaBean();
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			logger.info(agora+" - Entrando na tabela 'Assinatura'");
			statement = conn.prepareStatement(CONSULTA_ASSINATURA);
			logger.info(agora+" - Saindo da consulta à tabela 'Assinatura'");
			result = statement.executeQuery();
			logger.info(agora+" - Entrando em um laço para captura dos valores");
			while (result.next()) {
				
				logger.info(agora+" - Preparando-se para armazenar os valores....");
				objProd.setSituacao(result.getString(1));
				logger.info(agora+" - Situação: "+objProd.getSituacao());
				objProd.setFichas(result.getInt(2));
				logger.info(agora+" - Fichas: "+objProd.getFichas());
				logger.info(agora+" - Armazenagem feita com sucesso!");
				logger.info(agora+" - Saindo do laço");		
			}

			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error!");
			log(e,tela);
			logger.error(agora+" - Error "+ e.getMessage());
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return objProd;		
	}

	

	public static AssinaturaBean Contagem() throws exception, IOException{ 				// DUVIDOSA
		System.out.println(" --------------- Pesquisando Assinatura --------------- ");
		AssinaturaBean objProd = new AssinaturaBean();
		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {	
		System.out.println("- Entrando na procedure> PROC_P_CONTASSPAC");		
			statement = conn.prepareStatement(CONTAGEM);
			result = statement.executeQuery();
//			objProd.setContagem(result.getInt(1));
		
//			String contador1 = String.valueOf(objProd.getContagem());
			
//			System.out.println("Inspeção(Contagem de Assinaturas) " +contador1);
			
			while (result.next()) {

			objProd.setContagem(result.getInt(1));
			objProd.setContagem(result.getInt(1));
			int contador = objProd.getContagem();
			System.out.println("Inspeção(Contagem de Assinaturas) " +contador);
			
			}
			
			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro na assinatura!");
			System.out.println(e.getMessage());
			log(e,tela);
			System.exit(0);

			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}

		return objProd;
		
	}

	public static boolean inserirAssinatura(int x) throws exception, IOException{
		System.out.println(" --------------- Gerando uma nova Assinatura --------------- ");	
				Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			
			statement = conn.prepareStatement(INSERIR_ASSINATURA);
			
			statement.setInt(1,x);
			statement.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error!");
			log(e,tela);
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		System.out.println(" --------------- Finalizando nova assinatura --------------- ");
		return true;		
	}
	
	
	
	public static boolean atualizarAssinaturaAtivado(String Empresa, String Cnpj, String Telefone, String Email, String Atualizacao, String chave) throws exception, IOException{		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			System.out.println("- Executando Procedure (PROC_I_ASSPAC)");
			statement = conn.prepareStatement(ATUALIZAR_ASSINATURA_ATIVADO);
			
			statement.setString(1,Empresa);
			statement.setString(2,Cnpj);
			statement.setString(3,Telefone);
			statement.setString(4,Email);
			statement.setString(5,Atualizacao);
			statement.setString(6, chave);
			
			System.out.println("- Finalizando Procedure (PROC_I_ASSPAC)");
			statement.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error!");
			log(e,tela);
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	public static boolean atualizarAssinaturaDesativado(AssinaturaBean objProd) throws exception, IOException{		
		
		Connection conn = sql.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_ASSINATURA_DESATIVADO);
			statement.setInt(1, objProd.getFichas());
			
			statement.executeUpdate();

		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error!");
			log(e,tela);
			System.exit(0);
			throw new exception(e);
		} finally {
			sql.close(conn, statement, result);
		}
		return true;		
	}
	
	private static void log(SQLException e, String tela) throws IOException {
		// TODO Auto-generated method stub
//		Log logging = new Log();
//		Log.gerarLog(e, tela);
		System.out.println("Entrou no log");
		Log.GerarLog4J_WARN(e,tela);
	}	
		
	
	
	
}



