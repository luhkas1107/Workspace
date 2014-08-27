package Funcoes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class Criptografia {
	public static String senhaCripto="";
	static String senha;
	public static String senhaDescripto="";
	
	public static String cripto (String senhaBase)  {  
		

		
		String senha3 = senhaBase.trim();
		String senha2 = senha3.toLowerCase();
		
		senha = senha2;
		senhaCripto = senha;
		int tamanhoSenha = senha.length();
		tamanhoSenha--;
//		String caractere3 = String.valueOf(senha.charAt(3));
//			String.valueOf(senha.charAt(3));
			
//		JOptionPane.showMessageDialog(null, "Senha: "+senha+
//				"\nTamanho: " +tamanhoSenha+
//				"\nCaractere[3]" +caractere3);
		
		for(int x=0;x<=tamanhoSenha;x++){
		
		char caractere1 =senha.charAt(x);
		
		
		String caractere = String.valueOf(caractere1);
		 // CRIPTOGRAFANDO LETRAS
		if(caractere.equals("a")){
		String cripto1=senhaCripto.replace("a", "*");
		senhaCripto=cripto1;
		}
		if(caractere.equals("b")){
		String cripto2=senhaCripto.replace("b", "#");
		senhaCripto=cripto2;
		}
		if(caractere.equals("c")){
		String cripto3=senhaCripto.replace("c", "ê");
		senhaCripto=cripto3;
		}
		if(caractere.equals("d")){
		String cripto4=senhaCripto.replace("d", "ô");
		senhaCripto=cripto4;
		}
		if(caractere.equals("e")){
		String cripto5=senhaCripto.replace("e", "$");
		senhaCripto=cripto5;
		}
		if(caractere.equals("f")){
		String cripto6=senhaCripto.replace("f", "!");
		senhaCripto=cripto6;
		}
		if(caractere.equals("g")){
		String cripto7=senhaCripto.replace("g", "?");
		senhaCripto=cripto7;
		}
		if(caractere.equals("h")){
		String cripto8=senhaCripto.replace("h", "~");
		senhaCripto=cripto8;
		}
		if(caractere.equals("i")){
		String cripto9=senhaCripto.replace("i", "@");
		senhaCripto=cripto9;
		}
		if(caractere.equals("j")){
		String cripto10=senhaCripto.replace("j", "¬");
		senhaCripto=cripto10;
		}
		if(caractere.equals("k")){
		String cripto11=senhaCripto.replace("k", "&");
		senhaCripto=cripto11;
		}
		if(caractere.equals("l")){
		String cripto12=senhaCripto.replace("l", "+");
		senhaCripto=cripto12;
		}
		if(caractere.equals("m")){
		String cripto13=senhaCripto.replace("m", "â");
		senhaCripto=cripto13;
		}
		if(caractere.equals("n")){
		String cripto14=senhaCripto.replace("n", "%");
		senhaCripto=cripto14;
		}
		if(caractere.equals("o")){
		String cripto15=senhaCripto.replace("o", "ú");
		senhaCripto=cripto15;
		}
		if(caractere.equals("p")){
		String cripto16=senhaCripto.replace("p", "á");
		senhaCripto=cripto16;
		}
		if(caractere.equals("q")){
		String cripto17=senhaCripto.replace("q", "ó");
		senhaCripto=cripto17;
		}
		if(caractere.equals("r")){
		String cripto18=senhaCripto.replace("r", "í");
		senhaCripto=cripto18;
		}
		if(caractere.equals("s")){
		String cripto19=senhaCripto.replace("s", "é");
		senhaCripto=cripto19;
		}
		if(caractere.equals("t")){
		String cripto20=senhaCripto.replace("t", "ã");
		senhaCripto=cripto20;
		}
		if(caractere.equals("u")){
		String cripto21=senhaCripto.replace("u", "õ");
		senhaCripto=cripto21;
		}
		if(caractere.equals("v")){
		String cripto22=senhaCripto.replace("v", "¨");
		senhaCripto=cripto22;
		}
		if(caractere.equals("x")){
		String cripto23=senhaCripto.replace("x", "}");
		senhaCripto=cripto23;
		}
		if(caractere.equals("w")){
		String cripto24=senhaCripto.replace("w","(");
		senhaCripto=cripto24;
		}
		if(caractere.equals("y")){
		String cripto25=senhaCripto.replace("y", ")");
		senhaCripto=cripto25;
		}
		if(caractere.equals("z")){
		String cripto26=senhaCripto.replace("z", "`");
		senhaCripto=cripto26;
		}
		
		//--------------CRIPTOGRAFAND NUMEROS
		if(caractere.equals("0")){
		String cripto27=senhaCripto.replace("0", "´");
		senhaCripto=cripto27;
		}
		if(caractere.equals("1")){
		String cripto28=senhaCripto.replace("1", "^");
		senhaCripto=cripto28;
		}
		if(caractere.equals("2")){
		String cripto29=senhaCripto.replace("2", "[");
		senhaCripto=cripto29;
		}
		if(caractere.equals("3")){
		String cripto30=senhaCripto.replace("3", "]");
		senhaCripto=cripto30;
		}
		if(caractere.equals("4")){
		String cripto31=senhaCripto.replace("4", "=");
		senhaCripto=cripto31;
		}
		if(caractere.equals("5")){
		String cripto32=senhaCripto.replace("5", "-");
		senhaCripto=cripto32;
		}
		if(caractere.equals("6")){
		String cripto33=senhaCripto.replace("6", ";");
		senhaCripto=cripto33;
		}
		if(caractere.equals("7")){
		String cripto34=senhaCripto.replace("7", ":");
		senhaCripto=cripto34;
		}
		if(caractere.equals("8")){
		String cripto35=senhaCripto.replace("8", "'");
		senhaCripto=cripto35;
		}
		if(caractere.equals("9")){
		String cripto36=senhaCripto.replace("9", "{");
		senhaCripto=cripto36;
		}
		
		}
		
	String cripto = senhaCripto;	
		
				
return cripto;
	}
	
	public static String descripto (String senha){
	// DESCRIPTOGRANFO LETRAS ! 
	
	String descripto1=senha.replace("*", "a");
	String fraseDescripto=descripto1;
	
	String descripto2=fraseDescripto.replace("#", "b");
	String fraseDescripto1=descripto2;
	
	String descripto3=fraseDescripto1.replace("ê", "c");
	String fraseDescripto2=descripto3;
	
	String descripto4=fraseDescripto2.replace("ô", "d");
	String fraseDescripto3=descripto4;
	
	String descripto5=fraseDescripto3.replace("$", "e");
	String fraseDescripto4=descripto5;
	
	String descripto6=fraseDescripto4.replace("!", "f");
	String fraseDescripto5=descripto6;
	
	String descripto7=fraseDescripto5.replace("?", "g");
	String fraseDescripto6=descripto7;
	
	String descripto8=fraseDescripto6.replace("~", "h");
	String fraseDescripto7=descripto8;
	
	String descripto9=fraseDescripto7.replace("@", "i");
	String fraseDescripto8=descripto9;
	
	String descripto10=fraseDescripto8.replace("¬", "j");
	String fraseDescripto9=descripto10;
	
	String descripto11=fraseDescripto9.replace("&", "k");
	String fraseDescripto10=descripto11;
	
	String descripto12=fraseDescripto10.replace("+", "l");
	String fraseDescripto11=descripto12;
	
	String descripto13=fraseDescripto11.replace("â", "m");
	String fraseDescripto12=descripto13;
	
	String descripto14=fraseDescripto12.replace("%", "n");
	String fraseDescripto13=descripto14;
	
	String descripto15=fraseDescripto13.replace("ú", "o");
	String fraseDescripto14=descripto15;
	
	String descripto16=fraseDescripto14.replace("á", "p");
	String fraseDescripto15=descripto16;
	
	String descripto17=fraseDescripto15.replace("ó", "q");
	String fraseDescripto16=descripto17;
	
	String descripto18=fraseDescripto16.replace("í", "r");
	String fraseDescripto17=descripto18;
	
	String descripto19=fraseDescripto17.replace("é", "s");
	String fraseDescripto18=descripto19;
	
	String descripto20=fraseDescripto18.replace("ã", "t");
	String fraseDescripto19=descripto20;
	
	String descripto21=fraseDescripto19.replace("õ", "u");
	String fraseDescripto20=descripto21;
	
	String descripto22=fraseDescripto20.replace("¨", "v");
	String fraseDescripto21=descripto22;
	
	String descripto23=fraseDescripto21.replace("}", "x");
	String fraseDescripto22=descripto23;
	
	String descripto24=fraseDescripto22.replace("(", "w");
	String fraseDescripto23=descripto24;
	
	String descripto25=fraseDescripto23.replace(")", "y");
	String fraseDescripto24=descripto25;

	String descripto26=fraseDescripto24.replace("`", "z");
	String fraseDescripto25=descripto26;
	
	//-------------------------DESCRIPTOGRAFANDO NUMEROS
	
	String descripto27=fraseDescripto25.replace("´", "0");
	String fraseDescripto26=descripto27;

	String descripto28=fraseDescripto26.replace("^", "1");
	String fraseDescripto27=descripto28;

	String descripto29=fraseDescripto27.replace("[", "2");
	String fraseDescripto28=descripto29;

	String descripto30=fraseDescripto28.replace("]", "3");
	String fraseDescripto29=descripto30;

	String descripto31=fraseDescripto29.replace("=", "4");
	String fraseDescripto30=descripto31;

	String descripto32=fraseDescripto30.replace("-", "5");
	String fraseDescripto31=descripto32;

	String descripto33=fraseDescripto31.replace(";", "6");
	String fraseDescripto32=descripto33;

	String descripto34=fraseDescripto32.replace(":", "7");
	String fraseDescripto33=descripto34;

	String descripto35=fraseDescripto33.replace("'", "8");
	String fraseDescripto34=descripto35;

	String descripto36=fraseDescripto34.replace("{", "9");
	String fraseDescripto35=descripto36;
	
	
	senhaDescripto = fraseDescripto35;
	
	return senhaDescripto;
	
	
}
}