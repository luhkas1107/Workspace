package Funcoes;

public class Desconto {

	
	public float DecPreco(float valor, float desc){
		float valor_final = 0;
		float valor_desc = desc/100;
		float aux = valor*valor_desc;
		valor_final = aux+valor;
		
		return valor_final;
			 	
	}
	public float DecPrecoInverso(float valor_final, float valor_inicial){
		
		float div= valor_final/valor_inicial;
		div = div*100;
		float desc = div-100;
		return desc;
			 	
	}
}
