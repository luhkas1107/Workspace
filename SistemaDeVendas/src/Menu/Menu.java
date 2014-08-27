package Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import Backup.CriarBackup;
import Backup.RestaurarBackup;
import Beans.FuncionarioBean;
import Beans.ProdutoBean;
import Cliente.CadCliente;
import Cliente.EmailLote1;
import Conexao.exception;
import Dao.BackupDao;
import Fornecedor.CadFornecedor;
import Funcionario.FuncionarioCadastro;
import Funcionario.FuncionarioMes;
import Funcionario.PagamentoFuncionario;
import Funcoes.Contador;
import Funcoes.SwingUtil;
import Log.Gerenciamento_Log;
import Log.Log; //CLASSE QUE IRÁ GERAR O LOG
import Produto.CadProduto;
import Relatorio.RelatorioSimples;
import Venda.PesqCliente;
import apresentacao.Login;

public class Menu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
    private JTextField textField_1;
    public String nome_func = Login.nome_func;
    JLabel lblAviso = new JLabel("");
    JPanel panel = new JPanel();
    private JPanel panelProduto;
    /**
     * 
     */
    JTextArea txtAviso = new JTextArea();
    private JTextArea txtAvisoprod;
    Timer timer = new Timer();  
    JPanel panelFuncionario = new JPanel();
    JTextArea txtAvisofunc = new JTextArea();
//    Date dataHoje;
// 		SimpleDateFormat formataData;	
 		File arquivo, dir; 
 		String caminho, caminhoImagem;
 		String nomeArquivo = "";
 		String extensaoArquivo =".bak";
 		String arquivo2 =null;
 		String txtcaminho;
 		int id;
		String nome;
		String caminho2;
		String email;
		String telefone;
		static Date dataHoje = new Date();
		static SimpleDateFormat formataData = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss:SSSS");
		static SimpleDateFormat formataDataArq = new SimpleDateFormat("ddMMyyyyHH");
		static String agora = formataData.format(dataHoje);
		static String nomeArq = formataDataArq.format(dataHoje);
		static Logger logger = Logger.getLogger(Menu.class);  // Classe que ta sendo feito o LOG
		public static String tela = "Menu"; // Nome da Tela

    
	/**
	 * Launch the application.
	 */
		/*
		 * @throws IOException
		 */
		
	public static void main(String[] args) throws Exception  {
		try {
			Menu dialog = new Menu();
			   dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			   
			   Login login = new Login();
		       login.setVisible(true);
		       login.setModal(true);
		       
		     
			
		} catch (Exception e) {
			e.printStackTrace();
			log(e,tela);
			logger.error(agora+" - Error "+ e.getMessage());


		}
	}

	/**
	 * Create the dialog.
	 */
	
	static void encerrarContagem(){
		System.out.println("Encerrar Contagem!");
	}
	
	
	
	
	public Menu() throws IOException {
		
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		

			Appender fileAppender = new FileAppender(  
			new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN), "Log\\"+tela+"_"+nomeArq+"_Service.log");  
			logger.addAppender(fileAppender);  
			logger.info(" ------------- Iniciando serviço MENU -------------- ");

		
		Dimension screen1 = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0, (screen1.width), (screen1.height));
		
		
		
		panelProduto = new JPanel();
		panelProduto.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelProduto.setBackground(SystemColor.info);
		panelProduto.setBounds(10, 12, 298, 197);
		panelProduto.setVisible(false);
		
		
		
		panelFuncionario = new JPanel();
		panelFuncionario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelFuncionario.setBackground(SystemColor.info);
		panelFuncionario.setBounds(318, 12, 298, 197);
		panelFuncionario.setVisible(false);
		contentPanel.add(panelFuncionario);
		panelFuncionario.setLayout(null);
		
		JLabel lblAvisosFuncionrio = new JLabel("AVISOS - FUNCION\u00C1RIO");
		lblAvisosFuncionrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvisosFuncionrio.setForeground(Color.RED);
		lblAvisosFuncionrio.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblAvisosFuncionrio.setBounds(10, 12, 278, 25);
		panelFuncionario.add(lblAvisosFuncionrio);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 59, 278, 2);
		panelFuncionario.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 177, 278, 2);
		panelFuncionario.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(8, 59, 1, 120);
		panelFuncionario.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(287, 60, 1, 116);
		panelFuncionario.add(separator_7);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 278, 116);
		panelFuncionario.add(scrollPane);
		
		txtAvisofunc = new JTextArea();
		scrollPane.setViewportView(txtAvisofunc);
		txtAvisofunc.setRows(20);
		txtAvisofunc.setLineWrap(true);
		txtAvisofunc.setFont(new Font("Arial", Font.BOLD, 13));
		txtAvisofunc.setEditable(false);
		txtAvisofunc.setColumns(1);
		contentPanel.add(panelProduto);
		panelProduto.setLayout(null);
		
		JLabel lblAvisos_1 = new JLabel("AVISOS - PRODUTO");
		lblAvisos_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvisos_1.setForeground(Color.RED);
		lblAvisos_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblAvisos_1.setBounds(10, 12, 278, 25);
		panelProduto.add(lblAvisos_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 59, 278, 2);
		panelProduto.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 177, 278, 2);
		panelProduto.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(287, 60, 1, 120);
		panelProduto.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(10, 59, 1, 120);
		panelProduto.add(separator_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 59, 278, 114);
		panelProduto.add(scrollPane_1);
		
		txtAvisoprod = new JTextArea();
		scrollPane_1.setViewportView(txtAvisoprod);
		txtAvisoprod.setFont(new Font("Arial", Font.BOLD, 13));
		txtAvisoprod.setColumns(1);
		txtAvisoprod.setRows(20);
		txtAvisoprod.setEditable(false);
		txtAvisoprod.setLineWrap(true);
		
		JLabel lblWallpaper = new JLabel("");
		lblWallpaper.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
			
			try {
				logger.info(" -- Rodando atualização de informações sobre Produto/Funcionário.");
				atualizaProduto();
				atualizaFuncionario();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					log(e,tela);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				logger.error(agora+" - Error "+ e.getMessage());


			}
			
			}
		});
		lblWallpaper.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			try {
				atualizaProduto();
				atualizaFuncionario();
			} catch (Exception e) {
				try {
					log(e,tela);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				logger.error(agora+" - Error "+ e.getMessage());


				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		});
		lblWallpaper.setIcon(new ImageIcon(Menu.class.getResource("/Images/TESTE.jpg")));
		lblWallpaper.setBounds(0, 0, 1152, 711);
		contentPanel.add(lblWallpaper);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BasedoMenu.class.getResource("/Images/arroba.jpg")));
		SwingUtil.lookWindows(this);
		setTitle("Menu Principal -			"+     nome_func     +     "   - InfoTec");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
//	     Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//	     this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
			
	       
	       JMenuBar barra = new JMenuBar();     
	       setJMenuBar(barra);     
	       
	       JMenu mnInicio = new JMenu("Cadastro");
	       mnInicio.setSelectedIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/funcionario.png")));
	       mnInicio.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/users.png")));
	       barra.add(mnInicio);
	       
	       JMenuItem mntmCliente = new JMenuItem("Cliente");
	       mntmCliente.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/icone_cliente2.png")));
	       mntmCliente.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       	   	CadCliente cliente = new CadCliente ();
	           	cliente.setVisible(true);
	        
	       	
	       	}
	       });
	       mnInicio.add(mntmCliente);
	       
	       JMenuItem mntmProduto = new JMenuItem("Produto");
	       mntmProduto.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/icone_produtosOrig.jpg")));
	       mntmProduto.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       	 
	       	 CadProduto produto;
			try {
				
				produto = new CadProduto ();
				logger.info(" --- Abrindo Tela de "+produto.tela+".");
				produto.setVisible(true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					log(e,tela);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				logger.error(agora+" - Error "+ e.getMessage());

				e.printStackTrace();
			}
	       	}
	       });
	       mnInicio.add(mntmProduto);
	       
	       JMenu mnNewMenu = new JMenu("Funcion\u00E1rio");
	       mnNewMenu.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       	   	}
	       });
	       
	       JMenuItem mntmFornecedor = new JMenuItem("Fornecedor");
	       mntmFornecedor.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/Fornecedor.jpg")));
	       mntmFornecedor.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {

	       		CadFornecedor fornecedor = new CadFornecedor();
	       		fornecedor.setVisible(true);

	       	}
	       });
	       mnInicio.add(mntmFornecedor);
	       mnNewMenu.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/funcionario.png")));
	       mnInicio.add(mnNewMenu);
	       
	       JMenuItem mntmPagamento = new JMenuItem("Pagamento");
	       mntmPagamento.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/coins2.png")));
	       mntmPagamento.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       	PagamentoFuncionario pagamento;
			try {
				
				pagamento = new PagamentoFuncionario();
				logger.info(" --- Abrindo tela de "+pagamento.tela+".");
				pagamento.setVisible(true);

				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					log(e,tela);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				logger.error(agora+" - Error "+ e.getMessage());


			}
	       	
	       	
	       	}
	       });
	       
	       JMenuItem mntmCadastrarFuncionrio = new JMenuItem("Cadastrar Funcion\u00E1rio");
	       mntmCadastrarFuncionrio.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/users.png")));
	       mntmCadastrarFuncionrio.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       		FuncionarioCadastro funcionario;
				try {
					funcionario = new FuncionarioCadastro();
					funcionario.setVisible(true);
				} catch (Exception e) {
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
	       mnNewMenu.add(mntmCadastrarFuncionrio);
	       mnNewMenu.add(mntmPagamento);
	       
	       JMenuItem mntmFuncionrioDoMs = new JMenuItem("Funcion\u00E1rio do M\u00EAs");
	       mnNewMenu.add(mntmFuncionrioDoMs);
	       mntmFuncionrioDoMs.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/funcionario.png")));
	       mntmFuncionrioDoMs.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       		
	       	FuncionarioMes funcionario = new FuncionarioMes();
	       	funcionario.setVisible(true);
	       	
	       	}
	       });
	  
	//Referênte aos Menus  
	       JMenu menuVenda = new JMenu("Venda");     
	       menuVenda.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/shopcartapply_128x128.png")));
	       barra.add(menuVenda);
	  
	//MENU VENDA         
	//Referênte a NOVA ENTRADA do menu VENDA  
	       JMenuItem menuItemNovaEntrada = new JMenuItem("Nova Entrada de Venda");     
	       menuItemNovaEntrada.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/shopcart2.png")));
	       menuVenda.add(menuItemNovaEntrada);  
	       
	       JMenuItem mntmRelatrioDeVenda = new JMenuItem("Relat\u00F3rio de Venda ");
	       menuVenda.add(mntmRelatrioDeVenda);
	       mntmRelatrioDeVenda.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/invoice2.png")));
	       mntmRelatrioDeVenda.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       	RelatorioSimples relatorio = new RelatorioSimples();
	       	relatorio.setVisible(true);
	       	
	       	
	       	}
	       });
	      
	    //Chama NOVA ENTRADA DE VENDA  
	       menuItemNovaEntrada.addActionListener(new ActionListener() {  
	           public void actionPerformed(ActionEvent e) {
	        	   PesqCliente venda = new PesqCliente();
	        	   venda.setVisible(true);
	        	   
	            
	           }     
	            });
	     
	       JMenu menuCompra = new JMenu("Sobre");     
	       menuCompra.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/Sobre.jpg")));
	       barra.add(menuCompra);  
	       
	//MENU COMPRA        
	//Refêrente ao Pedido de Compra do MENU Compra  
	     JMenuItem menuItemPedidoDeCompra = new JMenuItem("Sobre SoftWord");     
	     menuItemPedidoDeCompra.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/icons_fullset.png")));
	     menuCompra.add(menuItemPedidoDeCompra);         
	     
	     JMenuItem mntmCrticasESugestes = new JMenuItem("Cr\u00EDticas e Sugest\u00F5es");
	     mntmCrticasESugestes.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/Mail_add2.png")));
	     mntmCrticasESugestes.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     	Email email = new Email();
	     	email.setVisible(true);
	     	}
	     });
	     menuCompra.add(mntmCrticasESugestes);
	     
	         //Chama Pedido de Compra  
	           menuItemPedidoDeCompra.addActionListener(new ActionListener() {  
	               public void actionPerformed(ActionEvent e) {     
	               
	            File pdf = new File("src/Images/Sobre.pdf");
	               try {
	            	  
	      logger.info(" --- Abrindo arquivo PDF sobre a empresa.");
    	   
				Desktop.getDesktop().open(pdf);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				try {
					log(ex,tela);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				logger.error(agora+" - Error "+ ex.getMessage());


				
			}
	               }
	               
	            });
	     
	     JMenu mnFerramentas = new JMenu("Ferramentas");
	     mnFerramentas.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/diagram_v2-13.png")));
	     barra.add(mnFerramentas);
	     
	     JMenuItem mntmBackup = new JMenuItem("Criar Backup");
	     mntmBackup.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     	CriarBackup backup = new CriarBackup();
	     	backup.setVisible(true);
	     	
	     	}
	     });
	     mntmBackup.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/data_backup.png")));
	     mnFerramentas.add(mntmBackup);
	     
	     JMenuItem mntmRestore = new JMenuItem("Restaurar Backup");
	     mntmRestore.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     	
	     	RestaurarBackup restaurar = new RestaurarBackup();
	     	restaurar.setVisible(true);
	     	
	     	}
	     });
	     mntmRestore.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/data_restore.png")));
	     mnFerramentas.add(mntmRestore);
	     
	     JMenu mnEmailEmLotes = new JMenu("E-mail em Lotes");
	     mnFerramentas.add(mnEmailEmLotes);
	     
	     JMenuItem mntmEmailLotescliente = new JMenuItem("E-Mail Lotes (Cliente)");
	     mntmEmailLotescliente.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     	EmailLote1 lote = new EmailLote1();
	     	lote.setModal(true);
	     	lote.setVisible(true);
	     	
	     	
	     	}
	     });
	     mnEmailEmLotes.add(mntmEmailLotescliente);
	     
	     JMenuItem mntmEmailLotesfornecedor = new JMenuItem("E-mail Lotes (Fornecedor)");
	     mnEmailEmLotes.add(mntmEmailLotesfornecedor);
	     
	     JMenuItem mntmLog = new JMenuItem("Log");
	     mntmLog.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     	Gerenciamento_Log log = new Gerenciamento_Log();
	     	log.setVisible(true);
	     	log.setModal(true);     	
	     	
	     	}
	     });
	     mnFerramentas.add(mntmLog);
	     
	     JMenu mnSair = new JMenu("Sair");
	     mnSair.setIcon(new ImageIcon(Menu.class.getResource("/Images/icon_excluir.png")));
	     barra.add(mnSair);
	     
	     JMenuItem mntmFazerLogoff = new JMenuItem("Fazer Logoff");
	     mntmFazerLogoff.setIcon(new ImageIcon(Menu.class.getResource("/Images/users.png")));
	     mntmFazerLogoff.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     	

		     	int opcao;
				opcao = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair "+nome_func+" ? ", "Sair" , JOptionPane.YES_NO_OPTION);				
					if (opcao==JOptionPane.YES_OPTION){

				     	Login login;
						try {
	
							login = new Login();
							logger.info(" --- Abrindo tela de "+login.tela+".");
							setVisible(false);
							login.setVisible(true);
							login.setModal(true);
							timer.cancel();	
						} catch (IOException | SQLException | exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							try {
								log(e,tela);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							logger.error(agora+" - Error "+ e.getMessage());


						}
				     			
					}
	     	}
	     });
	     mntmFazerLogoff.addMouseListener(new MouseAdapter() {
	     	@Override
	     	public void mouseClicked(MouseEvent e) {
	     	
	     		
	     	}
	     });
	     mnSair.add(mntmFazerLogoff);
	     
	     JMenuItem mntmSairDoPrograma = new JMenuItem("Sair do Programa");
	     mntmSairDoPrograma.setIcon(new ImageIcon(Menu.class.getResource("/Images/icon_excluir.png")));
	     mntmSairDoPrograma.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     	int opcao;
				opcao = JOptionPane.showConfirmDialog(null,"Deseja fazer o backup " +nome_func+" ?", "Backup", JOptionPane.YES_NO_OPTION);
				logger.info("Automatização do Backup Cancelada!");
				if(opcao==JOptionPane.YES_OPTION){
	            	CriarBackup backup = new CriarBackup();
	            	setModal(false);
	            	backup.setModal(true);
	            	backup.setVisible(true);
	            	setVisible(false);
	            	timer.cancel();
	            
				}
	        if(opcao==JOptionPane.NO_OPTION){    
	     	timer.cancel();
	     	System.out.println("Encerrando a aplicação.");
	     	System.out.println("Aplicação encerrada!");
	     	System.exit(0);
	        }
	     	
	     	
	     	}
	     });
	     mnSair.add(mntmSairDoPrograma);
	     
	     int tamanho = getHeight();
	     int altura = getWidth();
	     
	     String caminho = "/Images/Infotec.jpg";
	     
	     ImageIcon image = new ImageIcon(BasedoMenu.class.getResource("/Images/Infotec.jpg"));
	       

//	       try {
	    	  
//	    	int x=0;
//	    	while(x==0){
//	    	this.validate();
//	    	this.repaint();
//	    	}
//			atualizaProduto();
//			tempo();
			
//		} catch (exception e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	       
	       
	       
	}
	 public void atualizaProduto() throws exception, IOException{
		 
		 
		 
 		List<ProdutoBean> listaRela = new ArrayList<ProdutoBean>();
 		try {
	logger.info(" --- Abrindo consulta ao banco de dados ! Dao.ProdutoDao.consultarProduto('')");

				listaRela = Dao.ProdutoDao.consultarProduto("");
			} catch (exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log(e,tela);
				logger.error(agora+" - Error "+ e.getMessage());


			}

 		boolean resultado = false;
  		
  		
  		String dados[] = new String[8];
  		String nomeProdBx = "";
 		for (ProdutoBean obj : listaRela)
 		{

 			dados[1] = String.valueOf(obj.getId_produto());
 			dados[2] = String.valueOf(obj.getNome());
 			dados[3] = String.valueOf(obj.getQuantidade());
 			
 			int quantidade = Integer.parseInt(dados[3]);
 			if(quantidade<5){
 				resultado = true;
 				System.out.println("Tem produto abaixa da quantidade...");
 				nomeProdBx=dados[2];
 			}else{
// 				nomeProdBx="";
 				resultado=false;
 				this.repaint();
 				this.validate();
 			}
 			
 		}
 		
 		String aviso2 = txtAvisoprod.getText().toString();
 		
 		if(resultado){
 			
 		this.validate();
 		this.repaint();
 		
 		if(aviso2.equals("")){
 		panelProduto.setVisible(true);

 		String aviso = txtAvisoprod.getText().toString();
 		System.out.println("Entrou no atualiza Produto");
 		aviso += "\n - Verifique seu estoque pois existe(m) produto(s) com baixa quantidade em estoque. ("+nomeProdBx+")";
 		System.out.println(aviso);
 		
 		txtAvisoprod.setText(aviso);
 		}
 		}else{
 			txtAvisoprod.setText("");
 			panelProduto.setVisible(false);
 			
 		}
 		
 	}
	 
	 public void atualizaFuncionario() throws exception, IOException{
		 
		 
		 
	 		List<FuncionarioBean> listaRela = new ArrayList<FuncionarioBean>();
	 		try {

	 			logger.info(" --- Abrindo consulta ao banco de dados ! Dao.FuncionarioDao.consultarFuncionarios('')");
					listaRela = Dao.FuncionarioDao.consultarFuncionarios("");
				} catch (exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log(e,tela);
					logger.error(agora+" - Error "+ e.getMessage());


				}

	 		boolean resultadoFoto = false;

	 		boolean resultadoEmail = false;

	 		boolean resultadoTelefone = false;

	 		String nomeEmail="";
	 		String nomeTelefone="";
	 		String nomeFoto="";
	 		
	 		
	 		
	 		for (FuncionarioBean obj : listaRela){
	 			String situacao = obj.getSituacao();
	 				if(situacao.equals("Ativado")){
	 			int id = obj.getId_func();
	 			nome = obj.getNome_func();
	 			caminho2 = obj.getCaminho();
	 			email = obj.getEmail();
	 			telefone = obj.getTelefone_func();
	 			
	 			
	 			if(caminho2.equals("")){
	 				resultadoFoto = true;
	 				System.out.println("\nExiste Funcionário sem foto cadastrada.");
	 				nomeFoto=nome;
	 			} if(email.equals("")){
	 				resultadoEmail = true;
	 				System.out.println("\nExiste Funcionário sem foto email cadastrado.");
	 				nomeEmail=nome;
	 				
	 			}if (telefone.equals("")){
	 				resultadoTelefone = true;
	 				System.out.println("\nExiste Funcionário sem telefone cadastrada.");

	 					nomeTelefone=nome;
		 			}
	 			
	 			
	 			else{
	 				this.repaint();
	 				this.validate();
	 			}
	 		
	 		}
	 		
	 		String aviso2 = txtAvisofunc.getText().toString();
	 		
	 			
	 			
	 		this.validate();
	 		this.repaint();
		 		
	 		if(resultadoFoto || resultadoEmail || resultadoTelefone){
		 			panelFuncionario.setVisible(true);
		 		if(aviso2.equals("")){
	 				if(resultadoFoto && caminho2.equals("")){
	 					String aviso = txtAvisofunc.getText().toString();
	 					System.out.println("Entrou no atualiza Funcionário");
	 					aviso += "\n - Existe(m) funcionário(s) sem foto cadastrado, favor cadastre quando possível.("+nomeFoto+").";
	 					System.out.println(aviso);
	 					txtAvisofunc.setText(aviso);
	 								}
	 				if(resultadoEmail && email.equals("")){
	 					String aviso = txtAvisofunc.getText().toString();
	 			 		System.out.println("Entrou no atualiza Funcionário");
	 			 		aviso += "\n - Existe(m) funcionário(s) sem email cadastrado, favor cadastre quando possível("+nomeEmail+").";
	 			 		System.out.println(aviso);
	 			 		txtAvisofunc.setText(aviso);			
	 								  }
	 				if(resultadoTelefone && telefone.equals("")){
	 					String aviso = txtAvisofunc.getText().toString();
	 			 		System.out.println("Entrou no atualiza Funcionário");
	 			 		aviso += "\n - Existe(m) funcionário(s) sem telefone cadastrado, favor cadastre quando possível.("+nomeTelefone+").";
	 			 		System.out.println(aviso);
	 			 		txtAvisofunc.setText(aviso);			
	 								  }
	 							}
	 		}
	 		
	 		else{
	 			txtAvisofunc.setText("");
	 			panelFuncionario.setVisible(false);
	 			
	 		}
	 		}
	 		
	 	}
	 
	 
	 
	 public void tempo(){
		 
//  	   Date data = new Date();
//	   Contador segundos = new Contador();
	   if(Contador.encerrar){
		   
		   System.out.println("Aplicação Encerrada!");
		   

	   }
	   timer.schedule(new Contador(), 1000, 1000);
	   
	 
       

	   
	 
	 }
	 public void backup() throws IOException{
		 logger.info(" --- Iniciando processo de Backup.");
			
	    	
	    	Date hora = new Date();
			String formato = "HHmmss";
			
			DateFormat dateformatMedium = new SimpleDateFormat(formato);
			
			String agora = dateformatMedium.format(hora);
			 
			txtcaminho = "c:\\Backup_SistemaVenda\\"+agora+".bak";
	    	
	    	String text = txtcaminho;
			caminho = Class.class.getResource("/").toString();	
	    	caminho = text.replace("/","\\\\");
	    	caminho = text.replace("file:\\\\","");
			caminho = text.replace("C:","c:");
			

//			System.out.println(arquivo2);
//			System.out.println(caminho);
						
					
						boolean resultado;
						try {
						logger.info("--- Processo de backup em andamento ...");
							
							resultado=BackupDao.backup_novo(caminho);

							logger.info("Backup realizado com suscesso!");
							
						} catch (exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log(e1,tela);
							logger.error(agora+" - Error "+ e1.getMessage());


						}
						
						Date agora2 = new Date();
						
						String formato2 = "dd/MM/yyyy";
						String formato3 = "HH:mm:ss";
						
						DateFormat dateformatMedium1 = new SimpleDateFormat(formato2);
						DateFormat dateformatMedium3 = new SimpleDateFormat(formato3);
						
						String data_hoje = dateformatMedium1.format(agora2);
						String hora_hoje = dateformatMedium3.format(agora2);
						
						System.out.println("Data de Hoje: " +data_hoje+ "" +
								"\n Hora de hoje: "+hora_hoje);
						
						
						
							try {
								logger.info("--- Iniciando processo de envio do Backup!");
								


								//usuario e senha do seu gmail
								final String usuario = "lucas.microlins2013@gmail.com";
								final String senha = "20110923sindelpaula";
								final String porta = "465";
								final boolean debug = true;
								final String host = "smtp.gmail.com";
								final boolean autentication = true;
								
								//config. do gmail
								Properties mailProps = new Properties();
								mailProps.put("mail.transport.protocol", "smtp");
						        mailProps.put("mail.smtp.starttls.enable","true");
						        mailProps.put("mail.smtp.host", host);
						        mailProps.put("mail.smtp.auth", true);
						        mailProps.put("mail.smtp.user", usuario);
						        mailProps.put("mail.debug", debug);
						        mailProps.put("mail.smtp.port", porta);
						        mailProps.put("mail.smtp.socketFactory.port", "465");
						        mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
						        mailProps.put("mail.smtp.socketFactory.fallback", "false");
						        
						        logger.info("Coletando informações: " +usuario+","+senha+","+porta+","+debug+","+host+","+autentication);
								
						        logger.info("...Autenticando ... ");
								
						        //eh necessario autenticar
								Session mailSession = Session.getInstance(mailProps, new Authenticator() {					
									public PasswordAuthentication getPasswordAuthentication(){				
										return new PasswordAuthentication(usuario, senha);
									}
								});
								mailSession.setDebug(false);

								//config. da mensagem 
								Message mailMessage = new MimeMessage(mailSession);
								logger.info(" --- Preparando config da mensagem.");
								
								//remetente
								mailMessage.setFrom(new InternetAddress(usuario));
								logger.info(" --- Preparando remetente '"+usuario+"'.");
								
								//destinatario
								mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario));		

								//mensagem que vai no corpo do email
								MimeBodyPart mbpMensagem = new MimeBodyPart();
								mbpMensagem.setText("Backup do dia: "+data_hoje+"\nHorario: "+hora_hoje);
								logger.info(" --- Preparando corpo da mensagem.");
								
								//partes do email
								Multipart mp = new MimeMultipart();
								mp.addBodyPart(mbpMensagem);
								
								//imagem que sera enviada em anexo, ta no mesmo diretorio da classe.
								FileDataSource source = new FileDataSource(caminho);

								//setando o anexo
								MimeBodyPart mbpAnexo = new MimeBodyPart();
								mbpAnexo.setDataHandler(new DataHandler(source));
								mbpAnexo.setFileName(caminho);
								mp.addBodyPart(mbpAnexo);
								logger.info(" --- Preparando config de anexo da mensagem '"+caminho+".");

								//assunto do email
								mailMessage.setSubject("Backup do dia: "+data_hoje+" Horario: "+hora_hoje);
								logger.info(" --- Configurando o assunto.");								

								
								//seleciona o conteudo
								mailMessage.setContent(mp);
								logger.info(" --- Selecionando o conteudo do e-mail.");
								
								//envia o email
								Transport.send(mailMessage);
								caminho="";
								logger.info("Mensagem Enviada com sucesso!");
							} catch (Exception e) {
								e.printStackTrace();
								log(e,tela);
								logger.error(agora+" - Error "+ e.getMessage());


							}
						}
		private static void log(Exception e, String tela) 	throws IOException {
			// TODO Auto-generated method stub
			System.out.println("Entrou no log");
			Log.GerarLog4J_WARN(e,tela);
		}	

		
}

