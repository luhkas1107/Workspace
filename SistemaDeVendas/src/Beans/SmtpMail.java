package Beans;

import java.io.File;

public class SmtpMail {
    //indica se o formato de texto será texto ou html
    public static final String TYPE_TEXT_PLAIN = "text/plain";
    public static final String TYPE_TEXT_HTML = "text/html";
    //indica qual será o servidor de email(gmail, hotmail...)
    private String host;
    //indica a porta de acesso ao servidor
    private Integer porta;
    //indica se há a necessidade de autenticação no servidor
    private Boolean autenticacao;
    //indica se o servidor exige conexão segura (https)
    private Boolean conexaoSegura;
    //usuario do email (o mesmo que você usa para acessar no navegador)
    private String usuario;
    //senha do email
    private String senha;
    //email do remetente
    private String emailRemetente;
    //senha do email do remetente
    private String nomeRemetente;
    //assunto do email
    private String assunto;
    //corpo do email, onde estará o email propriamente dito
    private String corpo;
    //nome do destinatário
    private String nomeDestinatario;
    //email do destinatário
    private String emailDestinatario;
    //tipo do formato da mensagem, texto ou html
    private String typeText;
    //anexo
    private File anexo;
    
    
	public File getAnexo() {
		return anexo;
	}
	public void setAnexo(File anexo) {
		this.anexo = anexo;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPorta() {
		return porta;
	}
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	public Boolean getAutenticacao() {
		return autenticacao;
	}
	public void setAutenticacao(Boolean autenticacao) {
		this.autenticacao = autenticacao;
	}
	public Boolean getConexaoSegura() {
		return conexaoSegura;
	}
	public void setConexaoSegura(Boolean conexaoSegura) {
		this.conexaoSegura = conexaoSegura;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmailRemetente() {
		return emailRemetente;
	}
	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}
	public String getNomeRemetente() {
		return nomeRemetente;
	}
	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}
	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
	public String getEmailDestinatario() {
		return emailDestinatario;
	}
	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}
	public String getTypeText() {
		return typeText;
	}
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
	public static String getTypeTextPlain() {
		return TYPE_TEXT_PLAIN;
	}
	public static String getTypeTextHtml() {
		return TYPE_TEXT_HTML;
	}
 
    }
