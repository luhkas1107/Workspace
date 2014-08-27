package Beans;

public class VendaBean {

	

public int id_venda;
public int id_func;
public int id_cliente;
public float valortotal;
public float valorpago;
public String forma_pagamento;
public String nome;
public String data; 
public String hora;
public int quantidade_estoque;
public String id_produto;





public String getId_produto() {
	return id_produto;
}
public void setId_produto(String id_produto) {
	this.id_produto = id_produto;
}
public int getQuantidade_estoque() {
	return quantidade_estoque;
}
public void setQuantidade_estoque(int quantidade_estoque) {
	this.quantidade_estoque = quantidade_estoque;
}
public String getHora() {
	return hora;
}
public void setHora(String hora) {
	this.hora = hora;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getForma_pagamento() {
	return forma_pagamento;
}
public void setForma_pagamento(String forma_pagamento) {
	this.forma_pagamento = forma_pagamento;
}
public float getValorpago() {
	return valorpago;
}
public void setValorpago(float valorpago) {
	this.valorpago = valorpago;
}
public float getValortotal() {
	return valortotal;
}
public void setValortotal(float valortotal) {
	this.valortotal = valortotal;
}
public int getId_venda() {
	return id_venda;
}
public void setId_venda(int id_venda) {
	this.id_venda = id_venda;
}
public int getId_func() {
	return id_func;
}
public void setId_func(int id_func) {
	this.id_func = id_func;
}
public int getId_cliente() {
	return id_cliente;
}
public void setId_cliente(int id_cliente) {
	this.id_cliente = id_cliente;
}

}
