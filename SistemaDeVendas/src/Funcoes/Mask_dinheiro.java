package Funcoes;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import bsh.ParseException;

public class Mask_dinheiro {
	public static MaskFormatter setCampoValor() throws java.text.ParseException, ParseException { 

	
	MaskFormatter moeda= null; 
	moeda.setValidCharacters("0123456789"); 
	moeda.setPlaceholder(" ");
	return moeda; 
	
}
}
