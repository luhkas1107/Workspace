package apresentacao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import Assinatura.AssinadorPacote;
import Assinatura.TelaDesativado;
import Beans.AssinaturaBean;
import Beans.FuncionarioBean;
import Conexao.exception;
import Dao.AssinaturaDao;
import Dao.FuncionarioDao;
import EsqueciSenha.EsqueciSenha;
import Log.Log;
import Menu.Menu;
import Menu.Menu_Nivel1;
import Menu.Menu_Nivel2;

import java.awt.Font;
import java.io.IOException;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class Login extends JDialog implements KeyListener {

	static Date dataHoje = new Date();
	static SimpleDateFormat formataData = new SimpleDateFormat(
	"dd/MM/yyyy HH:mm:ss:SSSS");
	static SimpleDateFormat formataDataArq = new SimpleDateFormat(
			"ddMMyyyyHH");
	static String agora = formataData.format(dataHoje);
	static String nomeArq = formataDataArq.format(dataHoje);
	
	static Logger logger = Logger.getLogger(Login.class);  
	public static String tela = "Login";	

	
	JFormattedTextField txtCpf1 = new JFormattedTextField();

	
	
	
	private final JPanel contentPanel = new JPanel();
	private JPasswordField txtSenha;
	public static String nome_func;
	public static int id_func;
	public String senha_func;
	public static String cpf_func;
	boolean autenticacao1;
	boolean autenticacao2;
	JLabel lblStatus = new JLabel("");
	JLabel label = new JLabel("");
	JLabel lblFichas = new JLabel("");
	public static int contador;
	public static String situacao;
	public static int fichas;
	JLabel lblDesativado = new JLabel("Desativado");
	JLabel lblAtivado = new JLabel("Ativado");
	JButton btnAssinar = new JButton("Assinar");
	
	/*
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		try {
			logger.info(agora+" - Entrando no Splach");
			SplashScreen teste = new SplashScreen();
			teste.initSplash();

			for (int i = 0; i < 500000; i++) {

				if (i >= 0 && i < 50000) {
					teste.lblIniciandoAplicao
							.setText("Iniciando a aplicação...");
					System.out.println(i);
					teste.setProgresso(i);
				}
				if (i >= 50000 && i < 100000) {
					teste.lblIniciandoAplicao.setText("");
					teste.lblConectandoComO
							.setText("Conectando com o Banco de Dados...");
					System.out.println(i);
					teste.setProgresso(i);
				}
				if (i > 100000 && i < 290000) {
					teste.lblConectandoComO.setText("");
					teste.lblAtualizandoDadosDo
							.setText("Atualizando Banco de Dados ...");
					System.out.println(i);
					teste.setProgresso(i);
				}
				if (i > 290000 && i < 500000) {
					teste.lblAtualizandoDadosDo.setText("");
					teste.lblPreparandoParaIniciar
							.setText("Iniciando a aplicação...");
					System.out.println(i);
					teste.setProgresso(i);
				}

			}
			logger.info(agora+" - Saindo do Splach");

			Login frame = new Login();
			frame.setVisible(true);
			teste.fechaSplash();

			// Login dialog = new Login();
			// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			// dialog.setVisible(true);
			// dialog.setModal(true);

		} catch (Exception e) {
			log(e,tela);
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws exception 
	 * @throws SQLException 
	 */
	public Login() throws IOException, SQLException, exception {
		Appender fileAppender = new FileAppender(  
		 		new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN), "Log\\"+tela+"_"+nomeArq+"_Service.log");  
				logger.addAppender(fileAppender); 

		logger.info(agora+" - - Carregando configurações iniciais (Layout)...");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
		Login.class.getResource("/Images/diagram_v2-08.png")));
		
		setTitle("Login");
		setBounds(100, 100, 308, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
			}
		});
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation((screen.width - this.getSize().width) / 2,
				(screen.height - this.getSize().height) / 2);
		logger.info(agora+" - Finalizando Configurações iniciais (Layout)");
		MaskFormatter CPF = new MaskFormatter();
		try {
			logger.info(agora+" - - Carregando Mascáras");
			CPF.setMask("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			log(e1,tela);
			e1.printStackTrace();
		}
		
		JButton btnLogin = new JButton("Login");

		btnLogin.setBounds(193, 293, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info(agora+" -  ------------------- logar() ------------------- ");
				try {
					logar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					try {
						log(e1,tela);
					} catch (IOException | SQLException | exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					e1.printStackTrace();
					

				}
			}
		});
		contentPanel.setLayout(null);

		System.out.println("- Carregando panel1");
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(101, 36, 91, 87);
		contentPanel.add(panel);

		System.out.println("- Carregando panel2");
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setIcon(new ImageIcon(Login.class
				.getResource("/Images/funcionarioOrg.png")));
		label.setBounds(0, 0, 91, 87);
		panel.add(label);
		contentPanel.add(btnLogin);

		System.out.println("- Carregando panel3");
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(39, 144, 26, 14);
		contentPanel.add(lblCpf);

		System.out.println("- Carregando panel4");
		txtSenha = new JPasswordField();
		txtSenha.setBounds(39, 200, 218, 17);
		txtSenha.addKeyListener(this);
		contentPanel.add(txtSenha);

		System.out.println("- Carregando panel5");
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(39, 187, 46, 14);
		contentPanel.add(lblSenha);

		System.out.println("- Carregando panel6");
		txtCpf1 = new JFormattedTextField(CPF);
		txtCpf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
//				System.out.println("- Carregando KeyReleased");
				// senha_func = txtSenha.getText().toString();
				// cpf_func = txtCpf1.getText().toString();
				// // FuncionarioDao login = new FuncionarioDao();
				//
				//
				// FuncionarioBean objFunc = new FuncionarioBean();
				// try {
				// objFunc = FuncionarioDao.selecionarLogin(cpf_func,
				// senha_func);
				// } catch (exception e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				//
				//
				// if(objFunc.getId_func() > 0){
				//
				// nome_func=objFunc.getNome_func();
				// id_func=objFunc.getId_func();
				// String caminho = objFunc.getCaminho();
				// String situacao2 = objFunc.getSituacao();
				//
				// ImageIcon imagem = new ImageIcon(caminho);
				// imagem.setImage(imagem.getImage().getScaledInstance(91,87,100));
				// label.setIcon(imagem);
				//
				// if(situacao2.equals("Ativado")){
				// System.out.println("Ativado");
				// lblDesativado.setVisible(false);
				// lblAtivado.setVisible(true);
				// }else{
				//
				// System.out.println("Desativado");
				// lblAtivado.setVisible(false);
				// lblDesativado.setVisible(true);
				// }
				//
				// }else{
				// label.setIcon(new
				// ImageIcon(Login.class.getResource("/Images/funcionarioOrg.png")));
				// }
				//

			}
		});
		txtCpf1.setBounds(39, 158, 218, 17);
		contentPanel.add(txtCpf1);
		System.out.println("- Carregando panel7");
		JButton btnEsqueciASenha = new JButton("Esqueci a Senha");
		btnEsqueciASenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcao;
				opcao = JOptionPane.showConfirmDialog(null,
						"Esqueceu sua senha?", "Cuidado!!",
						JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					logger.info(agora+" -  ------------------- EsqueciSenha() ------------------- ");
					EsqueciSenha esqueceu = new EsqueciSenha();
					setVisible(false);
					esqueceu.setVisible(true);
				}
				
			}
		});
		btnEsqueciASenha.setBounds(10, 293, 133, 23);
		contentPanel.add(btnEsqueciASenha);

		btnAssinar = new JButton("Assinar");
		btnAssinar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssinadorPacote assinar;
				try {
				
					assinar = new AssinadorPacote();
					logger.info(agora+" -  ------------------- Assinatura() ------------------- ");
					setModal(false);
					setVisible(false);
					assinar.setModal(true);
					assinar.setVisible(true);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					try {
						log(e1,tela);
					} catch (IOException | SQLException | exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					e1.printStackTrace();
				}
				

			}
		});
		btnAssinar.setBounds(101, 243, 95, 23);
		contentPanel.add(btnAssinar);

		JLabel lbl1 = new JLabel("Status:");
		lbl1.setBounds(0, 0, 51, 15);
		contentPanel.add(lbl1);

		JLabel lbl2 = new JLabel("Fichas:");
		lbl2.setBounds(206, 0, 51, 15);
		contentPanel.add(lbl2);

		lblStatus = new JLabel("");
		lblStatus.setForeground(Color.RED);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(48, 0, 144, 14);
		contentPanel.add(lblStatus);

		lblFichas = new JLabel("");
		lblFichas.setForeground(Color.RED);
		lblFichas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFichas.setBounds(245, 0, 51, 15);
		contentPanel.add(lblFichas);

		lblDesativado = new JLabel("Desativado");
		lblDesativado.setForeground(Color.RED);
		lblDesativado.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblDesativado.setBounds(193, 219, 64, 15);
		lblDesativado.setVisible(false);
		contentPanel.add(lblDesativado);

		lblAtivado = new JLabel("Ativado");
		lblAtivado.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblAtivado.setForeground(Color.BLUE);
		lblAtivado.setBounds(193, 219, 51, 15);
		lblAtivado.setVisible(false);
		contentPanel.add(lblAtivado);

		try {
			assinatura();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void logar() throws IOException {
		senha_func = txtSenha.getText().toString();
		// senha_func = new String(txtSenha.getPassword().toString());
		cpf_func = txtCpf1.getText().toString();
		// FuncionarioDao login = new FuncionarioDao();
		try {

			FuncionarioBean objFunc = new FuncionarioBean();
			objFunc = FuncionarioDao.selecionarLogin(cpf_func, senha_func);

			if (cpf_func.equals("111.111.111-11") && senha_func.equals("admin")) {

				if (situacao.equals("Ativado")) {
					logger.info(agora+" - Situação: Sistema esta ativado.");
					JOptionPane.showMessageDialog(null,
							"Olá administrador Teste!");
					setModal(false);
					setVisible(false);
					Menu menu = new Menu();
					logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
					menu.setVisible(true);

				} else if (descontarUmaFicha()) {
					logger.info(agora+" - Sistema esta desativado.");
					JOptionPane.showMessageDialog(null,
							"Olá administrador Teste!");
					setModal(false);
					setVisible(false);
					Menu menu = new Menu();
					logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
					menu.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"Verifique suas fichas.");

					logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");

				}
			}

			if (objFunc.getId_func() > 0) {

				nome_func = objFunc.getNome_func();
				id_func = objFunc.getId_func();
				String situacao2 = objFunc.getSituacao();

				if (situacao2.equals("Ativado")) {
					if (objFunc.getFuncao_func().equals("Administrativo")) {
						if (situacao.equals("Ativado")) {
							logger.info(agora+" - Sistema esta ativado.");
							JOptionPane
									.showMessageDialog(
											null,
											"Olá administrador "
													+ nome_func
													+ "\n Seu código de identificação é: "
													+ id_func);
							setModal(false);
							setVisible(false);
							Menu menu = new Menu();
							logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
							menu.setVisible(true);
							menu.tempo();

						} else if (descontarUmaFicha()) {
							logger.info(agora+" - Sistema esta desativado.");
							JOptionPane
									.showMessageDialog(
											null,
											"Olá administrador "
													+ nome_func
													+ "\n Seu código de identificação é: "
													+ id_func);
							setModal(false);
							setVisible(false);
							Menu menu = new Menu();
							logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
							menu.setVisible(true);

						}

					} else if (objFunc.getFuncao_func().equals("Tecnico")) {
						if (situacao.equals("Ativado")) {
							logger.info(agora+" - Sistema esta ativado.");
							JOptionPane.showMessageDialog(null, "Olá tecnico "
									+ nome_func
									+ "\n Seu código de identificação é: "
									+ id_func);
							setModal(false);
							setVisible(false);
							Menu menu = new Menu();
							logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
							menu.setVisible(true);

						} else if (descontarUmaFicha()) {
							logger.info(agora+" - Sistema esta desativado.");
							JOptionPane.showMessageDialog(null, "Olá tecnico "
									+ nome_func
									+ "\n Seu código de identificação é: "
									+ id_func);
							setModal(false);
							setVisible(false);
							Menu menu = new Menu();
							logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
							menu.setVisible(true);

						}

					} else if (objFunc.getFuncao_func().equals(
							"Vendedor/Operador de Caixa")) {
						if (situacao.equals("Ativado")) {
							logger.info(agora+" - Sistema esta ativado.");
							JOptionPane.showMessageDialog(null, "Olá vendedor "
									+ nome_func
									+ "\n Seu código de identificação é: "
									+ id_func);
							setModal(false);
							setVisible(false);
							Menu_Nivel2 menu = new Menu_Nivel2();
							logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
							menu.setVisible(true);

						} else if (descontarUmaFicha()) {
							logger.info(agora+" - Sistema esta desativado.");
							JOptionPane.showMessageDialog(null, "Olá vendedor "
									+ nome_func
									+ "\n Seu código de identificação é: "
									+ id_func);
							setModal(false);
							setVisible(false);
							Menu_Nivel2 menu = new Menu_Nivel2();
							logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
							menu.setVisible(true);

						}

					} else if (objFunc.getFuncao_func().equals("Telemarketing")) {

						String situacao = lblStatus.getText().toString();

						if (situacao.equals("Ativado")) {
							logger.info(agora+" - Sistema esta ativado.");
							JOptionPane
									.showMessageDialog(
											null,
											"Olá atendente "
													+ nome_func
													+ "\n Seu código de identificação é: "
													+ id_func);
							setModal(false);
							setVisible(false);
							Menu_Nivel1 menu = new Menu_Nivel1();
							logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
							menu.setVisible(true);

						} else if (descontarUmaFicha()) {
							logger.info(agora+" - Sistema esta desativado.");
							JOptionPane
									.showMessageDialog(
											null,
											"Olá atendente "
													+ nome_func
													+ "\n Seu código de identificação é: "
													+ id_func);
							setModal(false);
							setVisible(false);
							Menu_Nivel1 menu = new Menu_Nivel1();
							logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
							menu.setVisible(true);

						} else {
							logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
							JOptionPane.showMessageDialog(null,
									"Verifique suas fichas.");
						}

					}

					else if (objFunc.getFuncao_func().equals("Limpeza")) {
						logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
						JOptionPane.showMessageDialog(null, "Olá funcionário "
								+ nome_func + ""
								+ "\n Verifique seu nível de acesso.");

					}

				} else {
					logger.info(agora+" -  ------------------- Encerrando Logar() ------------------- ");
					JOptionPane.showMessageDialog(null,
							"Senha e/ou CPF incorretos ou"
									+ "\nSua conta esta desativada. ");

				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();

		}

	}

	public void keyPressed(KeyEvent event) {
		if (event.getSource() == txtSenha) {
			if (event.getKeyCode() == 10) {
				logger.info(agora+" -  ------------------- Logar() ------------------- ");

				try {
					logar();
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		senha_func = txtSenha.getText().toString();
		cpf_func = txtCpf1.getText().toString();
		// FuncionarioDao login = new FuncionarioDao();

		//
		// FuncionarioBean objFunc = new FuncionarioBean();
		// try {
		// objFunc = FuncionarioDao.selecionarLogin(cpf_func, senha_func);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		//
		// if(objFunc.getId_func() > 0){
		//
		// nome_func=objFunc.getNome_func();
		// id_func=objFunc.getId_func();
		// String caminho = objFunc.getCaminho();
		//
		// ImageIcon imagem = new ImageIcon(caminho);
		// imagem.setImage(imagem.getImage().getScaledInstance(91,87,100));
		// label.setIcon(imagem);
		// String situacao2=objFunc.getSituacao();
		//
		// if(situacao2.equals("Ativado")){
		// System.out.println("Ativado");
		// lblDesativado.setVisible(false);
		// lblAtivado.setVisible(true);
		// }else{
		//
		// System.out.println("Desativado");
		// lblAtivado.setVisible(false);
		// lblDesativado.setVisible(true);
		// }
		//
		// }else{
		// label.setIcon(new
		// ImageIcon(Login.class.getResource("/Images/funcionarioOrg.png")));
		// }

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void assinatura() throws IOException {
		AssinaturaBean assinaturabean = new AssinaturaBean();

		logger.info(agora+" - - Carregando assinatura");

		try {
			assinaturabean = AssinaturaDao.Contagem();
			contador = assinaturabean.getContagem();

			System.out.println("contador do login: " + contador);

			if (contador==0) {
				
				AssinaturaDao.inserirAssinatura(0);
				pesquisaAssinatura();

			} else {
				System.out.println("- Já existe assinatura.");
				pesquisaAssinatura();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void pesquisaAssinatura() throws IOException {
		logger.info(agora+" -  ------------------- pesquisaAssinatura() ------------------- ");

		AssinaturaBean assinaturabean1 = new AssinaturaBean();
		try {
			assinaturabean1 = AssinaturaDao.consultarAssinatura();
			logger.info(agora+" -  ------------------- Pegando dados ------------------- ");
			logger.info(agora+" - Ficha: " + assinaturabean1.getFichas());
			logger.info(agora+" - Situação: " + assinaturabean1.getSituacao());
			logger.info(agora+" -  ------------------- Final dos dados  ------------------- ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(agora+" - Erro - "+e.getMessage());
			e.printStackTrace();
		}
		 logger.info(agora+" -  -------------------  Verificando Dados ------------------- ");
		situacao = assinaturabean1.getSituacao();
		logger.info(agora+" - Situação: "+situacao);
		fichas = assinaturabean1.getFichas();
		logger.info(agora+" - Fichas: "+fichas);
		logger.info(agora+" -  ------------------- Saindo da Verificação  ------------------- ");
		lblStatus.setText(situacao);
		lblFichas.setText("" + fichas);

		if (situacao.equals("Desativado")) {
			
			TelaDesativado tela = new TelaDesativado();
			tela.setModal(true);
			tela.setVisible(true);
		}else{
			System.out.println("-Assinatura Comum");
			btnAssinar.setEnabled(false);
			
		}

		if (fichas <= 0) {
			JOptionPane
					.showMessageDialog(
							null,
							"Infelizmente suas fichas acabaram, clique em assinar e adquira por tempo ilimitado!!!");
		}
logger.info(agora+" -  ------------------- Finalizando pesquisaAssinatura() ------------------- ");
	}

	public boolean descontarUmaFicha() throws IOException, SQLException, exception {
		AssinaturaBean assinaturabean1 = new AssinaturaBean();

		boolean resultado = true;

		try {
			assinaturabean1 = AssinaturaDao.consultarAssinatura();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		situacao = assinaturabean1.getSituacao();
		fichas = assinaturabean1.getFichas();

		if (fichas <= 0) {
			JOptionPane.showMessageDialog(null,
					"Infelizmente suas fichas acabaram"
							+ "\n Mas você pode renovar."
							+ "\n Adquira a versão ilimitada do software."
							+ "\n Clicando em 'Assinar'." + "\n APROVEITE!!!");
			resultado = false;
		} else {
			try {
				int fichas_final = fichas - 1;
				assinaturabean1.setFichas(fichas_final);
				AssinaturaDao.atualizarAssinaturaDesativado(assinaturabean1);
				logger.info(agora+" - Fichas Descontado com suscesso!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log(e,tela);
				e.printStackTrace();
			}
		}
		return resultado;

	}

private static void log(Exception e, String tela) throws IOException, SQLException, exception {
	// TODO Auto-generated method stub
//	Log logging = new Log();
//	Log.gerarLogApp(e, tela);
	System.out.println("Entrou no log");
	Log.GerarLog4J_WARN(e,tela);
}



//private static void logEvent(ActionEvent e, String tela) throws IOException {
	// TODO Auto-generated method stub
//	Log logging = new Log();
//	Log.gerarLogEvents(e, tela);
//}
}
