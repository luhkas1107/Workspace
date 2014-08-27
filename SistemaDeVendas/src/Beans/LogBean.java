package Beans;

import java.sql.Date;

import jxl.write.DateTime;

public class LogBean {

public String id_log;
public String tela;
public String data_log;
public String mensagem;


public String getMensagem() {
	return mensagem;
}
public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
}
public String getId_log() {
	return id_log;
}
public void setId_log(String id_log) {
	this.id_log = id_log;
}
public String getTela() {
	return tela;
}
public void setTela(String tela) {
	this.tela = tela;
}
public String getData_log() {
	return data_log;
}
public void setData_log(String data_log) {
	this.data_log = data_log;
}





}
