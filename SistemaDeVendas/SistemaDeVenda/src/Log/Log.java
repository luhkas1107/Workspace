package Log;



import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import Conexao.exception;
import Conexao.sql;


public class Log {
	static Date dataHoje = new Date();
	static SimpleDateFormat formataData = new SimpleDateFormat(
	"dd/MM/yyyy HH:mm:ss:SSSS");
	static SimpleDateFormat formataDataArq = new SimpleDateFormat(
			"ddMMyyyyHH");
	static String agora = formataData.format(dataHoje);
	static String nomeArq = formataDataArq.format(dataHoje);
	
	static Logger logger = Logger.getLogger(Log.class);  


	private static String INSERIR_LOG= 
			"EXEC PROC_I_LOG ?,?";

	
	
	public static void main(String[] args) throws IOException {
	
		
		  
    }       


		
	
	//Erro no SQL
	public static void gerarLog(SQLException erro, String tela) throws IOException{
		System.out.println(" --------------- Gravando Log --------------- ");

		Date dataHoje = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss:SSSS");
		String agora = formataData.format(dataHoje);
		SimpleDateFormat formataMilisegundo = new SimpleDateFormat("HHmmssSSSS");
		String nomeLog = formataMilisegundo.format(dataHoje);
		
		
		
		FileWriter arq = new FileWriter("Log\\"+tela+"_"+nomeLog+".txt");
		PrintWriter gravarLog = new PrintWriter(arq);
		System.out.println("Criando arquivo de Log - " +tela+"_"+nomeLog+".txt");
		gravarLog.printf(agora+" - " +erro.getMessage());
		
		arq.close();
		
		System.out.println(" --------------- Finalizando Log --------------- ");
	}
	
	//Erro de String ou funcões
	public static void gerarLogApp(Exception erro, String tela) throws IOException{
		System.out.println(" --------------- Gravando Log --------------- ");

		Date dataHoje = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss:SSSS");
		String agora = formataData.format(dataHoje);
		SimpleDateFormat formataMilisegundo = new SimpleDateFormat("HHmmssSSSS");
		String nomeLog = formataMilisegundo.format(dataHoje);
		
		
		
		FileWriter arq = new FileWriter("Log\\"+tela+"_"+nomeLog+".txt");
		PrintWriter gravarLog = new PrintWriter(arq);
		System.out.println("Criando arquivo de Log - " +tela+"_"+nomeLog+".txt");
		gravarLog.printf(agora+" - " +erro.getMessage());
		
		arq.close();
		
		System.out.println(" --------------- Finalizando Log --------------- ");
	}
	
	//Erro de eventos
	public static void gerarLogEvents(ActionEvent erro, String tela) throws IOException{
		System.out.println(" --------------- Gravando Log --------------- ");

		Date dataHoje = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss:SSSS");
		String agora = formataData.format(dataHoje);
		SimpleDateFormat formataMilisegundo = new SimpleDateFormat("HHmmssSSSS");
		String nomeLog = formataMilisegundo.format(dataHoje);
		
		
		
		FileWriter arq = new FileWriter("Log\\"+tela+"_"+nomeLog+".txt");
		PrintWriter gravarLog = new PrintWriter(arq);
		System.out.println("Criando arquivo de Log - " +tela+"_"+nomeLog+".txt");
		gravarLog.printf(agora+" - Erro de Evento (botão)");
		
		arq.close();
		
		System.out.println(" --------------- Finalizando Log --------------- ");
	}



public static void GerarLog4J_WARN (Exception e, String tela) throws IOException{

	try {
		inserirLog(e, tela);
	} catch (exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	BasicConfigurator.configure();  
	  
	  	/* Cria um novo FileAppender baseado no layout padrão, 
	  	 * pré-definido na constante TTCC_CONVERSION_PATTERN 
	  	 * da classe PatternLayout. */  
	  	 
	Appender fileAppender = new FileAppender(  
	  				new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN), "Log\\Erro\\"+tela+"_"+nomeArq+".log");  
      
    logger.addAppender(fileAppender);  
    logger.setLevel(Level.FATAL); 
    logger.fatal(agora+ " - Message: " + e.getMessage());  
      
}

public static void inserirLog(Exception e, String tela) throws exception{

	Connection conn = sql.getConnection();
	PreparedStatement statement = null;
	ResultSet result = null;
	  
	try {
		statement = conn.prepareStatement(INSERIR_LOG);
		statement.setString(1, tela);
		statement.setString(2, e.getMessage());
		statement.executeUpdate();

	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}
}

