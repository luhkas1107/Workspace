package Assinatura;

import java.io.IOException;

import Funcoes.ValidarKey;

public class Thread_Analisar extends Thread {

    public  int segundo = 0;  
    public static boolean encerrar = false;  
    public static String key = AssinadorPacote.aux;
    public static String cnpj = AssinadorPacote.cnpj;
    public void run(){
    	
		for (segundo=0; segundo<=7;segundo++){
		
			

			
			
			
			
			System.out.println("Segundos> "+segundo);
//			AssinadorPacote telaPacote = new AssinadorPacote ();
//			telaPacote.panel_Principal.setVisible(true);
//			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		
		
	
		}
		
			this.interrupt();
			
			System.out.println("Entrou nos 7 segundos.");
//			AssinadorPacote assinatura = new AssinadorPacote();
			
			try {
				
				key = AssinadorPacote.aux;
				String cnpj = AssinadorPacote.cnpj;
								
				
				if(ValidarKey.validarKey(key,cnpj)){
					System.out.println("Chave Esta sendo validada!");
					AssinadorPacote.assinar();	
				}else{
					System.out.println("Chave não esta sendo validada!");
					AssinadorPacote.Keyinvalida();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
	
	
	
	

	}
    public void init(){
    	Thread_Analisar thread = new Thread_Analisar();
       	thread.start();
    }
	public void exit(){
		Thread_Analisar thread = new Thread_Analisar();
       	thread.interrupt();
		
	}
	}

