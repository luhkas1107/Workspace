package Funcoes;

import java.util.InputMismatchException;

import javax.swing.JOptionPane;

public class ValidarKey {

	
	public static boolean validarKey(String key, String cnpj){
		boolean result = false;

		String descripto1=key.replace("y", "0");
		String fraseDescripto=descripto1;
		
		String descripto2=fraseDescripto.replace("A", "1");
		String fraseDescripto1=descripto2;
		
		String descripto3=fraseDescripto1.replace("c", "2");
		String fraseDescripto2=descripto3;
		
		String descripto4=fraseDescripto2.replace("a", "3");
		String fraseDescripto3=descripto4;
		
		String descripto5=fraseDescripto3.replace("Y", "4");
		String fraseDescripto4=descripto5;
		
		String descripto6=fraseDescripto4.replace("C", "5");
		String fraseDescripto5=descripto6;
		
		String descripto7=fraseDescripto5.replace("d", "6");
		String fraseDescripto6=descripto7;
		
		String descripto8=fraseDescripto6.replace("f", "7");
		String fraseDescripto7=descripto8;
		
		String descripto9=fraseDescripto7.replace("D", "8");
		String fraseDescripto8=descripto9;
		
		String descripto10=fraseDescripto8.replace("F", "9");
		String fraseDescripto9=descripto10;
		
		String chave = fraseDescripto9;
		
		
		
		if(chave.equals(cnpj)){
		if(ValidarCNPJ_CPF.isCnpjValido(chave)){
			result=true;
		}
			}else{
				JOptionPane.showMessageDialog(null, "A chave não confere com a sua empresa favor entrar em contato com o suporte (11) - 98083-8196.");
			}
		
		return result;
		
	}
	
}
