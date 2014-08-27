package Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import javax.swing.table.DefaultTableModel;
import Beans.LogBean;
import Conexao.exception;
import Conexao.sql;
import Log.Log;

public class LogDao {
	static Date dataHoje = new Date();
	static SimpleDateFormat formataData = new SimpleDateFormat(
	"dd/MM/yyyy HH:mm:ss:SSSS");
	static SimpleDateFormat formataDataArq = new SimpleDateFormat(
			"ddMMyyyyHH");
	static String agora = formataData.format(dataHoje);
	static String nomeArq = formataDataArq.format(dataHoje);
	
	static Logger logger = Logger.getLogger(AssinaturaDao.class);  
	
	public static String tela = "Gerenciar_log";

	private static String CONSULTA_LOG= 
			"EXEC PROC_P_TGENLOG ?";

			
			public static List<LogBean> consultarLog(String tela1) throws exception, IOException{
				Appender fileAppender = new FileAppender(  
		 		new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN), "Log\\"+tela+"_"+nomeArq+"_Service.log");  
				logger.addAppender(fileAppender);  
				logger.info(agora+" ------------------- Iniciando Consulta ao Log ------------------- ");

				Connection conn = sql.getConnection();
				PreparedStatement statement = null;
				ResultSet result = null;
				List<LogBean> listaLog = new ArrayList<LogBean>();
				
				try{
					
					
					if(tela.equals("")){
						logger.info(agora+" - Entrando na procedure 'PROC_P_TGENLOG'");
						statement = conn.prepareStatement(CONSULTA_LOG);
						statement.setString(1, "");
						logger.info(agora+" - Saindo da consulta");
						
					}else{
						logger.info(agora+" - Entrando na procedure 'PROC_P_TGENLOG'");
								statement = conn.prepareStatement(CONSULTA_LOG);
								statement.setString(1,tela1);
						logger.info(agora+" - Saindo da consulta");
								
					}
					
					result = statement.executeQuery();
					logger.info(agora+" - Entrando em um laço para captura dos valores");
					while (result.next()) {
						LogBean objLog = new LogBean();
						logger.info(agora+" - Preparando-se para armazenar os valores....");
						
						objLog.setId_log(result.getString(1));
						objLog.setTela(result.getString(2));
						objLog.setData_log(result.getString(3));
						objLog.setMensagem(result.getString(4));
						logger.info(agora+" - Armazenagem feita com sucesso!");
						logger.info(agora+" - Saindo do laço");		
						listaLog.add(objLog);
					}

				
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error!");
					log(e,tela);
					logger.error(agora+" - Error "+ e.getMessage());
					throw new exception(e);
				} finally {
					sql.close(conn, statement, result);
				}
				return listaLog;		
			}


			private static void log(SQLException e, String tela) 	throws IOException {
				// TODO Auto-generated method stub
				System.out.println("Entrou no log");
				Log.GerarLog4J_WARN(e,tela);
			}	


}
