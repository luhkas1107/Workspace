package Beans;

public class AssinaturaBean {

	private int id_assinatura;
	private String situacao,empresa,cnpj,telefone,email,atualizacao;
	private int fichas;
	private int contagem;
	
	
	
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAtualizacao() {
		return atualizacao;
	}
	public void setAtualizacao(String atualizacao) {
		this.atualizacao = atualizacao;
	}
	public int getContagem() {
		return contagem;
	}
	public void setContagem(int contagem) {
		this.contagem = contagem;
	}
	public int getId_assinatura() {
		return id_assinatura;
	}
	public void setId_assinatura(int id_assinatura) {
		this.id_assinatura = id_assinatura;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public int getFichas() {
		return fichas;
	}
	public void setFichas(int fichas) {
		this.fichas = fichas;
	}
	
	
	
	
	
	
}
