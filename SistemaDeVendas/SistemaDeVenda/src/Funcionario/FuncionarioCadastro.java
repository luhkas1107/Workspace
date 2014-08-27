package Funcionario;

import java.awt.BorderLayout;
import java.io.IOException;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import Log.Log; //CLASSE QUE IRÁ GERAR O LOG
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Beans.FuncionarioBean;
import Conexao.exception;
import Dao.FuncionarioDao;
import Funcoes.Contador_Mask;
public class FuncionarioCadastro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public	String id2;
	public String nome2;
	public String senha2;
	public String telefone2 = PesquisarFuncionario.telefone;
	public String endereco2 ;
	public String numero2  ;
	public String complemento2 ;
	public String bairro2 ;
	public String cidade2 ;
	public String uf2  ;
	public String cpf2 ;
	public String num_matricula_func2 ;
	public String funcao_func2 ;
	public String inicio_func2 ;
	public String termino_func2;
	public float salario_func2 ;
	public String comissao_func2 ;
	JLabel lblValido3 = new JLabel("");
	public String cpts2;
	public String sexo_func2 ;
	public String email2;
	public boolean clicar;
	public JTextField textNome;
	public JTextField textEndereco;
	public JTextField textComplemento;
	public JTextField textNumero;
	public JTextField textBairro;
	public JTextField textCidade;
	public JTextField textEmail;
	public JTextField textSalario;
	public JPasswordField passwordField;
	public JPasswordField passwordField_1;
	private JFormattedTextField formattedDataInicio = new JFormattedTextField();
	JLabel lblValido = new JLabel("");
	JLabel lblInvalido = new JLabel("");
	private JFormattedTextField formattedTerminoContrato = new JFormattedTextField();
	private JFormattedTextField formattedCTPS = new JFormattedTextField();
	private JFormattedTextField formattedTextField = new JFormattedTextField();
	private JFormattedTextField formattedCPF;
	private JFormattedTextField formattedTelefone = new JFormattedTextField();
	JLabel lblValido2 = new JLabel("");
	JLabel lblInvalido3 = new JLabel("");
	JLabel lblInvalido2 = new JLabel("");
	private Dao.FuncionarioDao funcDao = new Dao.FuncionarioDao();
	JLabel lblImage;
//	Date dataHoje;
//	SimpleDateFormat formataData;	
	File arquivo, dir; 
	String caminho="";
	String caminhoImagem="";
	String nomeArquivo = "";
	String extensaoArquivo ="";
	public float comissao;
	boolean etapa1 = false;
	boolean etapa2 = false;
	boolean resultadoetapa1;
	boolean resultadoetapa2;
	Timer timer = new Timer();  
	   
	JLabel lblInvalido4 = new JLabel("");
	JRadioButton rdbtnDesativado = new JRadioButton("Desativado");
	JRadioButton rdbtnAtivado = new JRadioButton("Ativado");
	JLabel lblValido4 = new JLabel("");
	public float salario ;
	public String senha;
	public String sexo;
	public String c_senha;
	JLabel lblID = new JLabel();
	ButtonGroup grupo3 = new ButtonGroup();
	ButtonGroup grupo2 = new ButtonGroup();
	ButtonGroup grupo = new ButtonGroup();
	JRadioButton radioButton_1 = new JRadioButton("Feminino");
	JRadioButton radioButton = new JRadioButton("Masculino");
	JRadioButton rdbtnFisica = new JRadioButton("Fisica");
	JComboBox comboFuncao = new JComboBox();
	JComboBox comboUF = new JComboBox();
	JLabel lblCorreto = new JLabel("");
	JLabel lblCorreto2 = new JLabel("");
	JLabel lblIncorreto = new JLabel("");
	JLabel lblIncorreto2 = new JLabel("");
	JRadioButton rdbtnJuridica = new JRadioButton("Juridica");
	MaskFormatter CPF = new MaskFormatter();
	static Date dataHoje = new Date();
	static SimpleDateFormat formataData = new SimpleDateFormat(
		"dd/MM/yyyy HH:mm:ss:SSSS");
	static SimpleDateFormat formataDataArq = new SimpleDateFormat("ddMMyyyyHH");
	static String agora = formataData.format(dataHoje);
	static String nomeArq = formataDataArq.format(dataHoje);
	static Logger logger = Logger.getLogger(FuncionarioCadastro.class);  // Classe que ta sendo feito o LOG
	public static String tela = "Funcionario"; // Nome da Tela

	
	 String retiraMascara(String mascarado){
			mascarado = mascarado.replace(".", "");
			mascarado = mascarado.replace("-", "");  
			  
			  return mascarado;
			  
		  }
		  
	
	 public static boolean validEmail(String email) {
		    System.out.println("Metodo de validacao de email");
		    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
		    Matcher m = p.matcher(email); 
		    if (m.find()){
//		      System.out.println("O email "+email+" é valido");
		      return true;
		    }
		    else{
//		      JOptionPane.showMessageDialog(null, "O E-mail "+email+" é inválido");
		      return false;
		    } 
		}
	 public static boolean validata (String data) throws ParseException, IOException{  
	        try {  
	              
	            String dia, mes, ano;  
	            int diaint, mesint, anoint;  
	            int verificarano; 	//variável usada para ver se o ano termina em 00  
	            String verano;		//variável usada para capturar os dois dígitos finais do ano  
	            int bissexto=0; 	//igual à zero = bissexto, diferente disto ano normal  
	                         
	            dia = data.substring(0,2);//captura apenas o dia  
	            mes = data.substring(3,5);//captura apenas o mês  
	            ano = data.substring(6, 10);//captura apenas o ano  
	              
	            diaint = Integer.parseInt(dia);//transforma o valor de String para int  
	            mesint = Integer.parseInt(mes);  
	            anoint = Integer.parseInt(ano);  
	         
	            //Verificar se ano é bissexto  
	            int valorano = anoint%4;//captura o valor do resto  
	              
	            if(valorano==0){//Anos em que o resto será zero  
	                verano = data.substring(8,10);//captura os dois dígitos finais do ano  
	                verificarano = Integer.parseInt(verano);//transforma verano em int  
	                bissexto=0;//aciona ano bissexto, senão tiver esta linha os anos que são bissexto mas não termina em 00, serão anos normais  
	                if(verificarano==0){  
	                    //***validação de datas para anos com fim 00  
	                    valorano = anoint%400;  
	                    if(valorano==0){  
	                        bissexto=0;//ano bissexto  
	                    }  
	                    else{  
	                        bissexto=1;//ano normal  
	                    }  
	                    //***fim da validação de datas para anos com fim 00          
	                }//fim do verificarano==0  
	            }//fim do valorano==0  
	            else{//Anos em que o resto não será zero  
	                bissexto=1;//ano normal  
	            }  
	              
	            //verificar validade do mês  
	            if(mesint>0 && mesint<=12){  
	            //verificar se o mês é fevereiro  
	            if(mesint==02){  
	                if(diaint>=30 && bissexto==0){//ano bissexto  
//	                    JOptionPane.showMessageDialog(null,"Dia inválido.","Erro!!!",0);  
	                }  
	                else if(diaint>=29 && bissexto==1){//ano normal  
//	                    JOptionPane.showMessageDialog(null,"Dia inválido.","Erro!!!",0);  
	                }                  
	                else{  
//	                	JOptionPane.showMessageDialog(null,"Dia válido.","Ok!!!",0);
	                    return true;  
	                }  
	            }  
	            else if(mesint==1 || mesint==3 || mesint==5 || mesint==7 || mesint==8 ||   
	                    mesint==10 || mesint==12){  
	                if(diaint>31){  
//	                    JOptionPane.showMessageDialog(null,"Dia inválido.","Erro!!!",0);  
	                }  
	                else{
//	                	JOptionPane.showMessageDialog(null,"Dia válido.","Ok!!!",0);
	                    return true;  
	                }  
	            }  
	            else if(mesint==4 || mesint==6 || mesint==9 || mesint==11){  
	                if(diaint>30){  
//	                    JOptionPane.showMessageDialog(null,"Dia inválido.","Erro!!!",0);  
	                }  
	                else{  
//	                	 JOptionPane.showMessageDialog(null,"Dia válido.","Ok!!!",0);
	                    return true;  
	                }  
	            }  
	              
	            }//fim do mesint>12 || mesint <=0  
	            else{  
//	                JOptionPane.showMessageDialog(null,"Mês inválido.","Erro!!!",0);  
	            }  
	            
	    
	        } catch (Exception e) {  
//	            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro!", 0);              
	        	log(e,tela);
	        	logger.error(agora+" - Error "+ e.getMessage());

	        }  
	        return false;  
	          
	    } 
	 
		boolean validacpf(String cpf){
		
				// considera-se erro CPF's formados por uma sequencia de numeros iguais
				    if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
				        cpf.equals("22222222222") || cpf.equals("33333333333") ||
				        cpf.equals("44444444444") || cpf.equals("55555555555") ||
				        cpf.equals("66666666666") || cpf.equals("77777777777") ||
				        cpf.equals("88888888888") || cpf.equals("99999999999") ||
				       (cpf.length() != 11))
				       return(false);

				    char dig10, dig11;
				    int sm, i, r, num, peso;

				// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
				    try {
				// Calculo do 1o. Digito Verificador
				      sm = 0;
				      peso = 10;
				      for (i=0; i<9; i++) {              
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0         
				// (48 eh a posicao de '0' na tabela ASCII)         
				        num = (int)(cpf.charAt(i) - 48); 
				        sm = sm + (num * peso);
				        peso = peso - 1;
				      }

				      r = 11 - (sm % 11);
				      if ((r == 10) || (r == 11))
				         dig10 = '0';
				      else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

				// Calculo do 2o. Digito Verificador
				      sm = 0;
				      peso = 11;
				      for(i=0; i<10; i++) {
				        num = (int)(cpf.charAt(i) - 48);
				        sm = sm + (num * peso);
				        peso = peso - 1;
				      }

				      r = 11 - (sm % 11);
				      if ((r == 10) || (r == 11))
				         dig11 = '0';
				      else dig11 = (char)(r + 48);

				// Verifica se os digitos calculados conferem com os digitos informados.
				      if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
				         return(true);
				      else return(false);
				    } catch (InputMismatchException erro) {
				        return(false);
				    
				    }
				  }

				  public static String imprimeCPF(String CPF) {
				    return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
				      CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
				  }
			

	
	 
	 
	 
 void verificacao1 (){
		String nome = textNome.getText();
		String endereco = textEndereco.getText();
		String numero = textNumero.getText();
		String bairro = textBairro.getText();
		String cidade = textCidade.getText();
		String salario1 = textSalario.getText();
		String cpf = formattedCPF.getText().toString();
		String uf = comboUF.getSelectedItem().toString();  
		String funcao = comboFuncao.getSelectedItem().toString();
		String dataInicio = formattedDataInicio.getText().toString();
		String cpts = formattedCTPS.getText().toString();
		
		
		senha = new String(passwordField.getPassword());
		c_senha = new String(passwordField_1.getPassword());
						
		
	if ( !nome.equals("") && !endereco.equals("") && !cpf.equals("") && !numero.equals("") && !bairro.equals("")
	&& !cidade.equals("") && !salario1.equals("") && !uf.equals("") && !funcao .equals("")&& !dataInicio .equals("")&& !cpts.equals("")){
		salario = Float.parseFloat(salario1); 

			etapa1 = true;
			
	}				
		else {
		etapa1 = false;
		
	}
	resultadoetapa1=etapa1;
	
	}

 
public boolean  verificacaoSenha (String Senha1, String senha2){
	senha = passwordField.getText().toString();
	c_senha = passwordField_1.getText().toString();
	
	
	
	if (senha.equals(c_senha) && !senha.equals("") && !c_senha.equals("")){
		

		etapa2 = true;
		return true;
	}
	else {
		etapa2 = false;
		return false;
	}
	
}

 
 
void alterar () throws IOException{
	FuncionarioBean funcionario = new FuncionarioBean();
//	JOptionPane.showMessageDialog(null, "EstouVivo");
	String situacao;
	FuncionarioBean objFunc = new FuncionarioBean();
	if (radioButton.isSelected()){
		sexo = "M";
	}else{
		sexo = "F";
	}
	if(rdbtnAtivado.isSelected()){
situacao="Ativado";
	}else{
		situacao = "Desativado";
	}
	objFunc.setBairro_func(textBairro.getText());
	objFunc.setNome_func(textNome.getText());
	objFunc.setEndereco_func(textEndereco.getText());
	objFunc.setComple_func(textComplemento.getText());
	objFunc.setNum_func(textNumero.getText());
	objFunc.setBairro_func(textBairro.getText());	
	objFunc.setCidade_func(textCidade.getText());
	objFunc.setEmail(textEmail.getText());
	objFunc.setSalario_func(Float.parseFloat(textSalario.getText()));
	objFunc.setUf_func(comboUF.getSelectedItem().toString());
	//objFunc.setPorcentagem_comissao_func(comboComissao.getSelectedItem().toString());
	objFunc.setFuncao_func(comboFuncao.getSelectedItem().toString());
	objFunc.setInicio_func(formattedDataInicio.getText().toString());
	objFunc.setTermino_func(formattedTerminoContrato.getText().toString());
	objFunc.setTelefone_func(formattedTelefone.getText().toString());
	objFunc.setSenha_func(passwordField.getText().toString());
	objFunc.setCpf_func(formattedCPF.getText().toString());
	objFunc.setCpts(formattedCTPS.getText().toString());
	objFunc.setSexo_func(sexo);
	objFunc.setSituacao(situacao);
//	if (caminhoImagem.equals("")){
//	objFunc.setSexo_func(PesquisarFuncionario.caminho_imagem);	
//	}else{
	objFunc.setCaminho(caminhoImagem);
//	}

	
	int matr = Integer.parseInt(lblID.getText());
	

	objFunc.setId_func(matr);
	try {
		
		FuncionarioDao.atualizarFuncionario(objFunc);
		JOptionPane.showMessageDialog(null, "Dados atualizados com suscesso!");
		limpar();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		log(e,tela);
		logger.error(agora+" - Error "+ e.getMessage());

	}

	
	
}
void verificacao2 (){
	senha = passwordField.getText().toString();
	c_senha = passwordField_1.getText().toString();
	
	
	
	if (senha.equals(c_senha) && !senha.equals("") && !c_senha.equals("")){
		
		
//		int quantidadeDeCaracteres2 = senha.length();
//		int quantidadeDeCaracteres= quantidadeDeCaracteres2-1;
		
//		for(int x=0;x<=quantidadeDeCaracteres; x++){
			
//			String caractere = String.valueOf(senha.charAt(x));
			
//			if(caractere.equals(" ")){
//				JOptionPane.showMessageDialog(null, "Sua senha não deverá conter espaços em branco.");
//				quantidadeDeCaracteres ++;
//				etapa2 = false;
//			}
//		}
		etapa2 = true;
		
	}
	else {
		etapa2 = false;
	}
	resultadoetapa2 = etapa2;
//	JOptionPane.showMessageDialog(null, "Resultado:" +resultadoetapa2);
	}

void limpar (){
		textNome.setText("");
		textEndereco.setText("");
		textComplemento.setText("");
		textNumero.setText("");
		textBairro.setText("");
		textCidade.setText("");
		textEmail.setText("");
		textSalario.setText("");
		formattedTerminoContrato.setText("");
		formattedDataInicio.setText("");
		formattedCTPS.setText("");
		formattedTelefone.setText("");
		formattedCPF.setText("");
		passwordField.setText("");
		passwordField_1.setText("");
		comboUF.setSelectedIndex(0);
		comboFuncao.setSelectedIndex(0);
		lblID.setText("");
		lblImage.setIcon(null);
	}
	
void pegar () throws IOException{
	//LoginFuncionario novologin = new LoginFuncionario();
	//login = novologin.login;	
	//senha = novologin.senha;
	
	String situacao ;
		if (radioButton.isSelected()){
			sexo = "M";
		}else{
			sexo = "F";
		}
		if(rdbtnAtivado.isSelected()){
			situacao="Ativado";
		}else{
			situacao="Desativado";
		}
		
		
		
		FuncionarioBean funcionario = new FuncionarioBean();
		
		funcionario.setBairro_func(textBairro.getText());
		funcionario.setNome_func(textNome.getText());
		funcionario.setEndereco_func(textEndereco.getText());
		funcionario.setComple_func(textComplemento.getText());
		funcionario.setNum_func(textNumero.getText());
		funcionario.setBairro_func(textBairro.getText());	
		funcionario.setCidade_func(textCidade.getText());
		funcionario.setEmail(textEmail.getText());
		funcionario.setSalario_func(salario);
		funcionario.setUf_func(comboUF.getSelectedItem().toString());
		//funcionario.setPorcentagem_comissao_func(comboComissao.getSelectedItem().toString());
		funcionario.setFuncao_func(comboFuncao.getSelectedItem().toString());
		funcionario.setInicio_func(formattedDataInicio.getText().toString());
		funcionario.setTermino_func(formattedTerminoContrato.getText().toString());
		funcionario.setTelefone_func(formattedTelefone.getText().toString());
		funcionario.setSenha_func(passwordField.getText().toString());
		funcionario.setCpf_func(formattedCPF.getText().toString());
		funcionario.setCpts(formattedCTPS.getText().toString());
		funcionario.setCaminho(caminhoImagem);
		funcionario.setSexo_func(sexo);
		funcionario.setSituacao(situacao);
//		JOptionPane.showMessageDialog(null, caminhoImagem);
		try {
			funcDao.inserirFuncionarios(funcionario);
			
			JOptionPane.showMessageDialog(null, "Cadastrado com Suscesso!");
			limpar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Já existe usuário cadastrado com o mesmo CPF ou mesmo número da sua matrícula!");
			log(e,tela);
			logger.error(agora+" - Error "+ e.getMessage());

		}
		//JOptionPane.showMessageDialog(null, "ID:"); // Aqui iremos puxar o id gerado pelo banco!
		
		}

void pesquisar(boolean clicar){
		
	PesquisarFuncionario pesquisa = new PesquisarFuncionario();
	if (clicar==true){
			FuncionarioBean obj = new FuncionarioBean();
		  lblID.setText(pesquisa.id);
		  textNome.setText(pesquisa.nome2) ;
		  passwordField.setText( pesquisa.senha) ;
		  formattedTelefone.setText(pesquisa.telefone);
		  textEndereco.setText(pesquisa.endereco );
		  textNumero.setText(pesquisa.numero );
		  textComplemento.setText(pesquisa.complemento );
		  textBairro.setText(pesquisa.bairro);
		  textCidade.setText(pesquisa.cidade);
		  comboUF.setSelectedItem(pesquisa.uf);
		  formattedCPF.setText(pesquisa.cpf);
		  comboFuncao.setSelectedItem(pesquisa.funcao_func);
		  formattedDataInicio.setText(pesquisa. inicio_func);
		  formattedTerminoContrato.setText(pesquisa.termino_func);
		  salario_func2 = pesquisa.salario_func;
		  String salario4 = String.valueOf(salario_func2);
		  textSalario.setText(salario4);
		  formattedCTPS.setText(pesquisa.cpts);
		  textEmail.setText(pesquisa.email);
		  caminhoImagem = pesquisa.caminho_imagem;

		  ImageIcon  imagem = new ImageIcon(caminhoImagem);
  	      imagem.setImage(imagem.getImage().getScaledInstance(87,87,100));
  	      
  	      lblImage.setIcon(imagem);
		  
		  
		  String sexo = pesquisa.sexo_func;
		  String situacao = pesquisa.situacao;
		  
		  if(sexo.equals("M")){
			  radioButton.setSelected(true);
		  }else{
			  radioButton_1.setSelected(true);
		  }
		  if(situacao.equals("Ativado")){
			  rdbtnAtivado.setSelected(true);
		  }else{
			  rdbtnDesativado.setSelected(true);
		  }
//		  radioButton.setText(pesquisa.sexo_func);
		  
		}else{
//			JOptionPane.showMessageDialog(null, "Falso");
			  
		}
		
	
	
	
}

	
	public static void main(String[] args) throws IOException {
		try {
			FuncionarioCadastro dialog = new FuncionarioCadastro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			log(e,tela);
			logger.error(agora+" - Error "+ e.getMessage());

		}
	}

	/**
	 * Create the dialog.
	 */
	public FuncionarioCadastro() throws exception, IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FuncionarioCadastro.class.getResource("/Images/funcionario.png")));
		setTitle("Cadastro Funcion\u00E1rio");
		setBounds(100, 100, 752, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
		Appender fileAppender = new FileAppender(  
		
		new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN), "Log\\"+tela+"_"+nomeArq+"_Service.log");  
		logger.addAppender(fileAppender);  
		logger.info(" ----------- Iniciando tela de Cadastro de Funcionário ------------ ");
			
		{
			JLabel lblNome = new JLabel("*Nome");
			lblNome.setBounds(10, 134, 102, 14);
			contentPanel.add(lblNome);
		}
		{
			JLabel lblTelefone = new JLabel("*Telefone");
			lblTelefone.setBounds(10, 159, 65, 14);
			contentPanel.add(lblTelefone);
		}
		{
			JLabel lblNewLabel = new JLabel("*Endere\u00E7o");
			lblNewLabel.setBounds(10, 184, 87, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblN = new JLabel("*N\u00BA");
			lblN.setBounds(278, 184, 21, 14);
			contentPanel.add(lblN);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Complemento");
			lblNewLabel_1.setBounds(10, 213, 102, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("*Bairro");
			lblNewLabel_2.setBounds(10, 238, 46, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("*Cidade");
			lblNewLabel_3.setBounds(10, 263, 46, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblUf = new JLabel("*UF");
			lblUf.setBounds(278, 263, 21, 14);
			contentPanel.add(lblUf);
		}
		{
			JLabel lblcpf = new JLabel("*CPF");
			lblcpf.setBounds(10, 288, 46, 14);
			contentPanel.add(lblcpf);
		}
		{
			JLabel lblFuno = new JLabel("*Fun\u00E7\u00E3o");
			lblFuno.setBounds(10, 338, 46, 14);
			contentPanel.add(lblFuno);
		}
		{
			logger.info("Carregado configurações de máscaras.");
			textNome = new JTextField();
			textNome.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
				
					String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
					String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
					if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
						arg0.consume();
					}
				}
			});
			textNome.setBounds(127, 131, 235, 17);
			contentPanel.add(textNome);
			textNome.setColumns(10);
		}
		{
			textEndereco = new JTextField();
			textEndereco.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
					String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
					if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
						arg0.consume();
					}
				}
			});
			textEndereco.setBounds(127, 182, 134, 17);
			contentPanel.add(textEndereco);
			textEndereco.setColumns(10);
		}
		{
			textComplemento = new JTextField();
			textComplemento.setBounds(127, 210, 158, 17);
			contentPanel.add(textComplemento);
			textComplemento.setColumns(10);
		}
		{
			textNumero = new JTextField();
			textNumero.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
				String caractere = "0123456789";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
				
				}
			});
			textNumero.setBounds(305, 182, 57, 17);
			contentPanel.add(textNumero);
			textNumero.setColumns(10);
		}
		{
			textBairro = new JTextField();
			textBairro.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
					String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
					if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
						arg0.consume();
					}
				}
			});
			textBairro.setBounds(127, 235, 158, 17);
			contentPanel.add(textBairro);
			textBairro.setColumns(10);
		}
		{
			textCidade = new JTextField();
			textCidade.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
					String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
					if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
						arg0.consume();
					}
				
				}
			});
			textCidade.setBounds(127, 260, 141, 17);
			contentPanel.add(textCidade);
			textCidade.setColumns(10);
		}
		{
			
			comboFuncao.setBounds(127, 335, 141, 20);
			contentPanel.add(comboFuncao);
			comboFuncao.addItem("");
			comboFuncao.addItem("Vendedor/Operador de Caixa");
			comboFuncao.addItem("Administrativo");
			comboFuncao.addItem("Tecnico");
			comboFuncao.addItem("Telemarketing");
			comboFuncao.addItem("Limpeza");
			}
		{
			
			comboUF.setBounds(305, 260, 57, 17);
			contentPanel.add(comboUF);
			comboUF.addItem("");
			comboUF.addItem("AC");
			comboUF.addItem("AL");
			comboUF.addItem("AP");
			comboUF.addItem("AM");
			comboUF.addItem("BA");
			comboUF.addItem("CE");
			comboUF.addItem("DF");
			comboUF.addItem("ES");
			comboUF.addItem("GO");
			comboUF.addItem("MA");
			comboUF.addItem("MT");
			comboUF.addItem("MS");
			comboUF.addItem("MG");
			comboUF.addItem("PA");
			comboUF.addItem("PB");
			comboUF.addItem("PR");
			comboUF.addItem("PE");
			comboUF.addItem("PI");
			comboUF.addItem("RJ");
			comboUF.addItem("RN");
			comboUF.addItem("RS");
			comboUF.addItem("RO");
			comboUF.addItem("RR");
			comboUF.addItem("SC");
			comboUF.addItem("SP");
			comboUF.addItem("SE");
			comboUF.addItem("TO");
		}
		{
			JLabel lblIniciocontra = new JLabel("*In\u00EDcio Contrato");
			lblIniciocontra.setBounds(10, 363, 102, 14);
			contentPanel.add(lblIniciocontra);
		}
		{
			JLabel lblTerminocontrato = new JLabel("T\u00E9rmino Contrato");
			lblTerminocontrato.setBounds(10, 388, 120, 14);
			contentPanel.add(lblTerminocontrato);
		}
		{
			JLabel lblSalarior = new JLabel("*Sal\u00E1rio(R$)");
			lblSalarior.setBounds(10, 413, 87, 14);
			contentPanel.add(lblSalarior);
			}
		{
			JLabel lblCpts = new JLabel("*CTPS");
			lblCpts.setBounds(10, 313, 46, 14);
			contentPanel.add(lblCpts);
		}
		
		{
			JLabel lblEmail = new JLabel("E-Mail");
			lblEmail.setBounds(10, 439, 46, 14);
			contentPanel.add(lblEmail);
		}
		{
			logger.info("Carregando validações automáticas.");
			textEmail = new JTextField();
			textEmail.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {

					String email = textEmail.getText();
					
					lblValido4.setText("");
					lblInvalido4.setText("");
					
					if(validEmail(email)){
						lblValido4.setText("Válido");	
						
						
						}else{
							lblInvalido4.setText("Inválido");
						}
				}
			});
			textEmail.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent arg0) {
				
				}
			});
			textEmail.setBounds(127, 437, 141, 17);
			contentPanel.add(textEmail);
			textEmail.setColumns(10);
		}
		
		
		{
			JLabel lblSexo = new JLabel("*Sexo");
			lblSexo.setBounds(381, 129, 46, 14);
			contentPanel.add(lblSexo);
		}
		{
		
					
       
        radioButton.setSelected(true);
        radioButton.setBounds(498, 125, 109, 23);
		contentPanel.add(radioButton);
		
		
		
		radioButton_1.setBounds(634, 125, 109, 23);
		contentPanel.add(radioButton_1);
		
		
		
		grupo.add(radioButton);
		grupo.add(radioButton_1);
		
		rdbtnJuridica = new JRadioButton("Juridica");
		rdbtnJuridica.setBounds(224, 75, 109, 23);
		contentPanel.add(rdbtnJuridica);
		
		rdbtnFisica = new JRadioButton("Fisica");
		rdbtnFisica.setBounds(44, 75, 109, 23);
		rdbtnFisica.setSelected(true);
		contentPanel.add(rdbtnFisica);
	
		
		grupo3.add(rdbtnFisica);
		grupo3.add(rdbtnJuridica);
	CPF = new MaskFormatter();
		if (rdbtnFisica.isSelected()){
			try {
				CPF.setMask("###.###.###-##");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log(e,tela);
				logger.error(agora+" - Error "+ e.getMessage());

			}
		}else{
			try {
				CPF.setMask("###.###.###/###-##");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log(e,tela);
				logger.error(agora+" - Error "+ e.getMessage());

			}
		}
		
		
		}
		
		
		{
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/funcionario.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {   //BOTAO CADASTRAR!
				logger.info("JButtonCadastrar - Metódo de Cadasto!");
	
				
			String cpf = formattedCPF.getText().toString();
			
				
				
			logger.info("------ 1a Validação ----- ");	
			verificacao1();
			logger.info("------ Fim 1a Validação ----- ");
			logger.info("------ 2a Validação ----- ");
			verificacao2();
			logger.info("------ Fim 2a Validação ----- ");
			logger.info("Resultado das Validações: \n"+resultadoetapa1+"\n"+resultadoetapa2);
			String email = textEmail.getText();
			String data1 = formattedDataInicio.getText();
			String data2 = formattedTerminoContrato.getText();
			
			logger.info("Entrando com as condições para cadastro do funcionário! ");
			if (resultadoetapa1 == true && resultadoetapa2 == true){
				
				if(validacpf(retiraMascara(cpf))){
					logger.info("Validação do CPF feita com sucesso!");	
					try {
						if(validata(data1)==true && validata(data2)==true){
							logger.info("Validação da Data feita com sucesso!");	
						if(email.equals("")){
							logger.info(" ---- Iniciando o método pega() ---- ");	
							pegar();
							logger.info(" ---- Finalizando o método pegar() ---- ");
						}
						
						else if(validEmail(email)==true){
							logger.info(" ---- Iniciando o método pega() ---- ");	
							pegar();
							logger.info(" ---- Iniciando o método pega() ---- ");	
							
						}
						

						
						}
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						try {
							log(e,tela);
							logger.error(agora+" - Error "+ e.getMessage());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						try {
							log(e,tela);
							logger.error(agora+" - Error "+ e.getMessage());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					logger.info("CPF NEGADO = "+cpf);	
					
					JOptionPane.showMessageDialog(null, "Cpf inválido!");
					
				}
			}
			
			if (resultadoetapa1 == false){
				JOptionPane.showMessageDialog(null, "Favor preencher todos os campos importantes!");
				
			}
			if (resultadoetapa2 == false){
				JOptionPane.showMessageDialog(null, "Verifique sua senha pois pode estar vazio ou diferente.");
			}
			
			
			}
		});
		btnCadastrar.setBounds(612, 434, 126, 23);
		contentPanel.add(btnCadastrar);
		
			{
			textSalario = new JTextField();
			textSalario.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					String caractere = "1234567890.";
					String especial = "!@#$%¨&*()<>:?~][?ºª/;ª§-¬¢£³²¹";
					if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
						arg0.consume();
					}
				
				}
			});
			textSalario.setBounds(127, 410, 141, 17);
			contentPanel.add(textSalario);
			textSalario.setColumns(10);
		}
		
		MaskFormatter telefone = new MaskFormatter();
		MaskFormatter data= new MaskFormatter();
		MaskFormatter CTPS = new MaskFormatter();
		try {						
			{
				telefone.setMask("(##) - ####-####");
				data.setMask("##/##/####");
				CTPS.setMask("#####-###");
				
			}
			
			
			
			{
				formattedCPF = new JFormattedTextField(CPF);
				formattedCPF.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
	String cpf = formattedCPF.getText().toString();
						
						lblValido.setText("");
						lblInvalido.setText("");
						
						if(validacpf(retiraMascara(cpf))){
							lblValido.setText("Válido");	
							
							
							}else{
								lblInvalido.setText("Inválido");
							}
					}
				});
				formattedCPF.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						
					}
				});
				formattedCPF.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent arg0) {
					
					
					}
				});
				formattedCPF.setBounds(127, 285, 141, 17);
				contentPanel.add(formattedCPF);
			}
			
			{			
			formattedTerminoContrato = new JFormattedTextField(data);
			formattedTerminoContrato.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {

					String	data = formattedTerminoContrato.getText().toString();
					
					lblValido3.setText("");
					lblInvalido3.setText("");
					
					try {
						if(validata(data)){
							lblValido3.setText("Válido");	
							
							
							}else{
								lblInvalido3.setText("Inválido");
							}
					} catch (ParseException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						try {
							log(e,tela);
							logger.error(agora+" - Error "+ e.getMessage());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				
				}
			});
			formattedTerminoContrato.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent arg0) {
				
				}
			});
			formattedTerminoContrato.setBounds(127, 388, 141, 17);
			contentPanel.add(formattedTerminoContrato);
			}
			{
				formattedTelefone = new JFormattedTextField(telefone);
				formattedTelefone.setBounds(127, 157, 139, 17);
				contentPanel.add(formattedTelefone);
			}
			
			{		
				
				formattedDataInicio = new JFormattedTextField(data);
				formattedDataInicio.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
String	data = formattedDataInicio.getText().toString();
						
						lblValido2.setText("");
						lblInvalido2.setText("");
						
						try {
							if(validata(data)){
								lblValido2.setText("Válido");	
								
								
								}else{
									lblInvalido2.setText("Inválido");
								}
						} catch (ParseException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							try {
								log(e,tela);
								logger.error(agora+" - Error "+ e.getMessage());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					
					
					}
				});
				formattedDataInicio.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent arg0) {
						
					}
				});
				formattedDataInicio.setBounds(127, 366, 141, 17);
				contentPanel.add(formattedDataInicio);	
				
			}
			{
				formattedCTPS = new JFormattedTextField(CTPS);
				formattedCTPS.setBounds(127, 311, 126, 17);
				contentPanel.add(formattedCTPS);
			}
			
			JButton btnLimpar = new JButton("Limpar");
			btnLimpar.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/Clipboard_1.png")));
			btnLimpar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				logger.info(" ---- Iniciando o método de limpeza dos campos (limpar()) ---- ");	
				limpar();
				
							 
				}
				
			});
			btnLimpar.setBounds(381, 434, 102, 23);
			contentPanel.add(btnLimpar);
						
			Panel panel = new Panel();
			panel.setBackground(Color.DARK_GRAY);
			panel.setBounds(516, 299, 91, 87);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				lblImage = new JLabel("");
//				lblImage.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent arg0) {
//					
//					}
//					});
//				ImageIcon  imagem = new ImageIcon("src/Images/semFoto.jpg");
//         	    imagem.setImage(imagem.getImage().getScaledInstance(89,80,100));
//         	    lblImage.setIcon(imagem);
				lblImage.setBounds(0, 0, 87, 87);
				panel.add(lblImage);
			}
			
			{
				JSeparator separator = new JSeparator();
				separator.setBounds(0, 64, 742, 2);
				contentPanel.add(separator);
			}
			{
				JLabel lblObrigatorio = new JLabel("* Obrigat\u00F3rio");
				lblObrigatorio.setForeground(Color.RED);
				lblObrigatorio.setBounds(380, 274, 87, 14);
				contentPanel.add(lblObrigatorio);
			}
			
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setBounds(372, 105, 2, 371);
			contentPanel.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 105, 755, 2);
			contentPanel.add(separator_1);
			
			JLabel lblIdfuncionario = new JLabel("C\u00F3digo");
			lblIdfuncionario.setBounds(10, 109, 102, 14);
			contentPanel.add(lblIdfuncionario);
			
			JButton btnPesquisar = new JButton("Pesquisar");
			btnPesquisar.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/21-spotlight.png")));
			btnPesquisar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			logger.info(" ---- Iniciando a tela de pesquisa de funcionario (PesquisaFuncionario()) ---- ");	
						
				PesquisarFuncionario pesquisa = new PesquisarFuncionario();
				setVisible(false);
				pesquisa.setVisible(true);
				boolean clicar = pesquisa.clicar;
				pesquisar(clicar);
				
				
				
				
				}
			});
			btnPesquisar.setBounds(487, 71, 134, 23);
			contentPanel.add(btnPesquisar);
			
			JButton btnImagem = new JButton("Procurar");
			btnImagem.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/Consulta.jpg")));
			btnImagem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
			logger.info(" ---- Capturando Arquivo ---- ");	
					
            		caminho = Class.class.getResource("/").toString();	
                	caminho = caminho.replace("/","\\\\");
                	caminho = caminho.replace("file:\\\\","");
                	
                  	JFileChooser arquivo = new JFileChooser();
                	int retorno = arquivo.showOpenDialog(null);
                	String caminhoArquivo = "";
                	
                	
                	if(retorno == JFileChooser.APPROVE_OPTION){
                		caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
                		//pega a extensão do arquivo
                		int i = caminhoArquivo.lastIndexOf(".");
                		extensaoArquivo = caminhoArquivo.substring(i,caminhoArquivo.length());
                	    //gera o nome do arquivo
                		dataHoje = new Date();
                		formataData = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                		nomeArquivo = formataData.format(dataHoje);
                		
                		InputStream in = null;
						OutputStream out = null;							
						try {
								
							in = new FileInputStream(caminhoArquivo);
							caminhoImagem = caminho+"br\\com\\temp\\Func\\"+nomeArquivo+extensaoArquivo;
							caminhoImagem = caminhoImagem.replace("%20", " "); // Créditos
							logger.info(" ---- Capturando: "+caminho+"br\\com\\temp\\Func\\"+nomeArquivo+extensaoArquivo+" ---- ");	
							out = new FileOutputStream(caminhoImagem);
						
						
						} catch (FileNotFoundException e) {
								e.printStackTrace();
								try {
									log(e,tela);
									logger.error(agora+" - Error "+ e.getMessage());
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

						}
                	    byte[] buf = new byte[1024];
                	    int len;
                	    try {
                	    	while ((len = in.read(buf)) > 0) {
                	    		out.write(buf, 0, len);
							}
                    	    out.close();
                    	    in.close();
						} catch (IOException e) {
							e.printStackTrace();
							try {
								log(e,tela);
								logger.error(agora+" - Error "+ e.getMessage());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}                	        

                		
                	    ImageIcon  imagem = new ImageIcon(caminhoImagem);
                	    imagem.setImage(imagem.getImage().getScaledInstance(87,87,100));
                	    lblImage.setIcon(imagem);
                	}
				}		
				
				
				
				
			});
			btnImagem.setBounds(498, 388, 126, 29);
			contentPanel.add(btnImagem);
			
			JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
			lblFuncionrio.setHorizontalAlignment(SwingConstants.CENTER);
			lblFuncionrio.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/funcionarioOrg.png")));
			lblFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblFuncionrio.setBounds(0, 0, 736, 66);
			contentPanel.add(lblFuncionrio);
			{
				lblID.setBounds(127, 105, 46, 14);
				contentPanel.add(lblID);
			}
			{
				JButton btnAlterar = new JButton("Alterar");
				btnAlterar.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/editar.gif")));
				btnAlterar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						logger.info(" ---- Iniciando o método de alteração (alterar()) ---- ");	
						
						try {
							alterar();
						logger.info(" ---- Finalizando o método de alteração (alterar()) ---- ");	
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							try {
								log(e1,tela);
								logger.error(agora+" - Error "+ e1.getMessage());
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}

						}
					}
				});
				btnAlterar.setBounds(498, 435, 102, 23);
				contentPanel.add(btnAlterar);
			}
			
			
			lblValido.setFont(new Font("Dialog", Font.BOLD, 13));
			lblValido.setForeground(new Color(0, 128, 0));
			lblValido.setBounds(278, 285, 55, 16);
			contentPanel.add(lblValido);
			
			
			lblInvalido.setForeground(new Color(255, 0, 0));
			lblInvalido.setFont(new Font("Dialog", Font.BOLD, 13));
			lblInvalido.setBounds(278, 285, 55, 16);
			contentPanel.add(lblInvalido);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(Color.GRAY);
			panel_1.setBounds(381, 194, 349, 68);
			contentPanel.add(panel_1);
			panel_1.setLayout(null);
			{
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/diagram_v2-08.png")));
				label.setBounds(320, 0, 29, 68);
				panel_1.add(label);
			}
			
			lblCorreto = new JLabel("");
			lblCorreto.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/check-big.png")));
			lblCorreto.setBounds(295, 12, 19, 16);
			lblCorreto.setVisible(false);
			panel_1.add(lblCorreto);
			
			lblCorreto2 = new JLabel("");
			lblCorreto2.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/check-big.png")));
			lblCorreto2.setBounds(295, 40, 19, 16);
			lblCorreto2.setVisible(false);
			panel_1.add(lblCorreto2);
			{
				
				lblIncorreto = new JLabel("");
				lblIncorreto.setBounds(293, 12, 21, 16);
				panel_1.add(lblIncorreto);
				lblIncorreto.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/icon_excluir.png")));
				lblIncorreto.setVisible(false);
			}
			{
				
				lblIncorreto2 = new JLabel("");
				lblIncorreto2.setBounds(295, 40, 21, 16);
				panel_1.add(lblIncorreto2);
				lblIncorreto2.setIcon(new ImageIcon(FuncionarioCadastro.class.getResource("/Images/icon_excluir.png")));
				{
					passwordField_1 = new JPasswordField();
					passwordField_1.setBounds(129, 39, 158, 17);
					panel_1.add(passwordField_1);
					
					JLabel lblConfirmeASenha = new JLabel("*Confirmar Senha");
					lblConfirmeASenha.setBounds(12, 41, 129, 14);
					panel_1.add(lblConfirmeASenha);
					
					JLabel lblSenha = new JLabel("*Senha");
					lblSenha.setBounds(12, 17, 46, 14);
					panel_1.add(lblSenha);
					{
						passwordField = new JPasswordField();
						passwordField.setBounds(129, 14, 158, 17);
						panel_1.add(passwordField);
						passwordField.addKeyListener(new KeyAdapter() {
							@Override
							public void keyReleased(KeyEvent arg0) {
								String senha1 = passwordField.getText().toString();
								if(!senha1.equals("")){
									lblIncorreto.setVisible(false);
									lblCorreto.setVisible(true);
								}else{
									lblCorreto.setVisible(false);
									lblIncorreto.setVisible(true);
								}
							
							}
						});
					}
					passwordField_1.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent arg0) {
							String senha1 = passwordField.getText().toString();
							String senha2 = passwordField_1.getText().toString();
								if(verificacaoSenha(senha1,senha2)==true){
								lblIncorreto2.setVisible(false);	
								lblCorreto2.setVisible(true);
							}else{
								lblCorreto2.setVisible(false);
								lblIncorreto2.setVisible(true);
							}
							
						}
					});
				}
				lblIncorreto2.setVisible(false);
			}
			
			lblValido2.setFont(new Font("Dialog", Font.BOLD, 13));
			lblValido2.setForeground(new Color(0, 128, 0));
			lblValido2.setBounds(278, 367, 81, 16);
			contentPanel.add(lblValido2);
			
	
			lblInvalido2.setForeground(Color.RED);
			lblInvalido2.setFont(new Font("Dialog", Font.BOLD, 13));
			lblInvalido2.setBounds(278, 367, 76, 16);
			contentPanel.add(lblInvalido2);
			{
			
				lblValido3.setForeground(new Color(0, 128, 0));
				lblValido3.setFont(new Font("Dialog", Font.BOLD, 13));
				lblValido3.setBounds(278, 389, 81, 16);
				contentPanel.add(lblValido3);
			}
			{
				
				lblInvalido3.setForeground(Color.RED);
				lblInvalido3.setFont(new Font("Dialog", Font.BOLD, 13));
				lblInvalido3.setBounds(278, 389, 76, 16);
				contentPanel.add(lblInvalido3);
			}
			{
				
				lblValido4.setForeground(new Color(0, 128, 0));
				lblValido4.setFont(new Font("Dialog", Font.BOLD, 13));
				lblValido4.setBounds(278, 438, 84, 16);
				contentPanel.add(lblValido4);
			}
			{
				lblInvalido4.setForeground(Color.RED);
				lblInvalido4.setFont(new Font("Dialog", Font.BOLD, 13));
				lblInvalido4.setBounds(278, 438, 76, 16);
				contentPanel.add(lblInvalido4);
			}
			
			JLabel lblsituao = new JLabel("*Situa\u00E7\u00E3o");
			lblsituao.setBounds(381, 158, 76, 14);
			contentPanel.add(lblsituao);
			
			
			rdbtnDesativado = new JRadioButton("Desativado");
			rdbtnDesativado.setBounds(634, 154, 121, 24);
			contentPanel.add(rdbtnDesativado);
			
			rdbtnAtivado = new JRadioButton("Ativado");
			rdbtnAtivado.setSelected(true);
			rdbtnAtivado.setBounds(498, 154, 121, 24);
			contentPanel.add(rdbtnAtivado);
			
			grupo2.add(rdbtnAtivado);
			grupo2.add(rdbtnDesativado);
			
		
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log(e,tela);
			logger.error(agora+" - Error "+ e.getMessage());

		} // Numeros
		
		
		
	}
}
	 public void tempo(){
		 
//	   Date data = new Date();
//	   Contador segundos = new Contador();
	   if(Contador_Mask.encerrar){
		   
		   System.out.println("Aplicação Encerrada!");
		   

	   }
	   timer.schedule(new Contador_Mask(), 1000, 1000);
	   
	 
     

	   
	 
	 }
		private static void log(Exception e, String tela) 	

				throws IOException {
						// TODO Auto-generated method stub
						System.out.println("Entrou no log");
						Log.GerarLog4J_WARN(e,tela);
					}	

}