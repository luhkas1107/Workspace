package Beans;

import javax.swing.JOptionPane;

public class ProdutoBean {

	public int id_produto;
	public int id_fornecedor;
	public String nome;
	public float preco;
	public int quantidade;
	public String nome_fornecedor;
	public String caminho;
	public float preco_venda;
	public String categoria;
	public String empresa_fornecedor;
	public String possuiLista;
		
	//Lista1
	public String lista1;
	public float preco_custo1;
	public float percent_lucro1;
	//Lista2
	public String lista2;
	public float preco_custo2;
	public float percent_lucro2;
	//Lista3
	public String lista3;
	public float preco_custo3;
	public float percent_lucro3;
	//Promoção lista 1
	public String promo_lista1;
	public int id_promo1;
	//Promoção lista 2
	public String promo_lista2;
	public int id_promo2;
	//Promoção lista 3
	public String promo_lista3;
	public int id_promo3;
	
	public boolean autenticacao(){
		
		boolean result = false;
		
		if(nome.equals("")){ 
			JOptionPane.showMessageDialog(null,"Digite o nome!");
			result = false;
			if(quantidade==0){ 

			JOptionPane.showMessageDialog(null,"Digite o quantidade!");
			result = false;
			if(preco==0.0){

			JOptionPane.showMessageDialog(null,"Digite o preço!");
			result = false;
			if(lista1.equals("N")){

			JOptionPane.showMessageDialog(null,"Marque pelo menos 1 lista de preço!");
			result = false;
			if (categoria.equals("")){ 

			JOptionPane.showMessageDialog(null,"Digite uma categoria");
			
			result = false;
		}
			}
				}
					}
		}else{
			result = true;
			System.out.println("Preço: "+preco);
		}
		
		
		return result;
	}
	
	public void inserirDado(){
		
		
			
		
	}
	
	
	public String getPossuiLista() {
		return possuiLista;
	}

	public void setPossuiLista(String possuiLista) {
		this.possuiLista = possuiLista;
	}

	public String getLista1() {
		return lista1;
	}
	public void setLista1(String lista1) {
		this.lista1 = lista1;
	}
	public float getPreco_custo1() {
		return preco_custo1;
	}
	public void setPreco_custo1(float preco_custo1) {
		this.preco_custo1 = preco_custo1;
	}
	public float getPercent_lucro1() {
		return percent_lucro1;
	}
	public void setPercent_lucro1(float percent_lucro1) {
		this.percent_lucro1 = percent_lucro1;
	}
	public String getLista2() {
		return lista2;
	}
	public void setLista2(String lista2) {
		this.lista2 = lista2;
	}
	public float getPreco_custo2() {
		return preco_custo2;
	}
	public void setPreco_custo2(float preco_custo2) {
		this.preco_custo2 = preco_custo2;
	}
	public float getPercent_lucro2() {
		return percent_lucro2;
	}
	public void setPercent_lucro2(float percent_lucro2) {
		this.percent_lucro2 = percent_lucro2;
	}
	public String getLista3() {
		return lista3;
	}
	public void setLista3(String lista3) {
		this.lista3 = lista3;
	}
	public float getPreco_custo3() {
		return preco_custo3;
	}
	public void setPreco_custo3(float preco_custo3) {
		this.preco_custo3 = preco_custo3;
	}
	public float getPercent_lucro3() {
		return percent_lucro3;
	}
	public void setPercent_lucro3(float percent_lucro3) {
		this.percent_lucro3 = percent_lucro3;
	}
	public String getPromo_lista1() {
		return promo_lista1;
	}
	public void setPromo_lista1(String promo_lista1) {
		this.promo_lista1 = promo_lista1;
	}
	public int getId_promo1() {
		return id_promo1;
	}
	public void setId_promo1(int id_promo1) {
		this.id_promo1 = id_promo1;
	}
	public String getPromo_lista2() {
		return promo_lista2;
	}
	public void setPromo_lista2(String promo_lista2) {
		this.promo_lista2 = promo_lista2;
	}
	public int getId_promo2() {
		return id_promo2;
	}
	public void setId_promo2(int id_promo2) {
		this.id_promo2 = id_promo2;
	}
	public String getPromo_lista3() {
		return promo_lista3;
	}
	public void setPromo_lista3(String promo_lista3) {
		this.promo_lista3 = promo_lista3;
	}
	public int getId_promo3() {
		return id_promo3;
	}
	public void setId_promo3(int id_promo3) {
		this.id_promo3 = id_promo3;
	}
	public String getEmpresa_fornecedor() {
		return empresa_fornecedor;
	}
	public void setEmpresa_fornecedor(String empresa_fornecedor) {
		this.empresa_fornecedor = empresa_fornecedor;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public float getPreco_venda() {
		return preco_venda;
	}
	public void setPreco_venda(float preco_venda) {
		this.preco_venda = preco_venda;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public int getId_fornecedor() {
		return id_fornecedor;
	}
	public void setId_fornecedor(int id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getNome_fornecedor() {
		return nome_fornecedor;
	}
	public void setNome_fornecedor(String nome_fornecedor) {
		this.nome_fornecedor = nome_fornecedor;
	}

	
	
	
	
	
	
}
