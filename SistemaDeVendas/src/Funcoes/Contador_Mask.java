package Funcoes;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import Menu.Menu;

public class Contador_Mask extends TimerTask{
	
		private int hora = 0;  
	    private int minuto = 0;  
	    public  int segundo = 0;  
	    public static boolean encerrar = false;  
	    static Date dataHoje = new Date();
	    static SimpleDateFormat formataData = new SimpleDateFormat(
	    	"dd/MM/yyyy HH:mm:ss:SSSS");
	    static SimpleDateFormat formataDataArq = new SimpleDateFormat

	    ("ddMMyyyyHH");
	    static String agora = formataData.format(dataHoje);
	    static String nomeArq = formataDataArq.format(dataHoje);
	    static Logger logger = Logger.getLogger

	    (Contador.class);  // Classe que ta sendo feito o LOG
	    public static String tela = "Backup_automatico"; // Nome da Tela
	   
	    
    public void run() {  
 
    	
    	
    	System.out.println("Tempo para backup do software (2 em 2 horas) :  "+hora + ":" + minuto + ":" + segundo);  
          
if(hora==2){

	
	try {
		Menu menu = new Menu();
		menu.backup();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    		
}
        segundo++;  
          
        if (segundo == 60) {  
            segundo = 0;  
            minuto++; 
         
          
            if (minuto == 60) {  
                minuto = 0;  
                hora++;  
                if (hora == 2) {  
                    hora = 0;  
             
                	Menu menu;
					try {
						menu = new Menu();
						menu.backup();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                    
                }  
            }  
         
   }
                              
} 
    
   
					
					
    
    
}
