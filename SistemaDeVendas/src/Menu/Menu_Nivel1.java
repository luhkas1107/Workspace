package Menu;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import apresentacao.Login;
import Cliente.CadCliente;
import Conexao.exception;
import Relatorio.RelatorioSimples;
import Fornecedor.CadFornecedor;
import Funcionario.FuncionarioCadastro;
import Funcionario.FuncionarioMes;
import Funcionario.PagamentoFuncionario;
import Produto.CadProduto;
import Venda.PesqCliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.io.File;
//import java.io.IOException;
@SuppressWarnings("serial")  
      
    public class Menu_Nivel1 extends JFrame {     
    
        public JFrame frame;     
        private JTextField textField;
        private JTextField textField_1;
        public String nome_func = Login.nome_func;
       public Menu_Nivel1() {     
    	   
  
//Configurações do Frame         
     super("Aplicação JAVA");
     setBackground(Color.WHITE);
     
     
setIconImage(Toolkit.getDefaultToolkit().getImage(Menu_Nivel1.class.getResource("/Images/arroba.jpg")));
setTitle("Menu Principal -			"+nome_func+"- InfoTec");
getContentPane().setBackground(Color.LIGHT_GRAY);
getContentPane().setLayout(null);
       setSize(722, 431);     
       setExtendedState(MOVE_CURSOR);
       setLocation(180,20);  
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
       this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);


JLabel lblBack = new JLabel("");
lblBack.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/Infotec.jpg")));

lblBack.setBounds(0, 0, 712, 378);

getContentPane().add(lblBack);       
       
       
       JMenuBar barra = new JMenuBar();     
       setJMenuBar(barra);     
       
       JMenu mnInicio = new JMenu("Cadastro");
       barra.add(mnInicio);
       
       JMenuItem mntmCliente = new JMenuItem("Cliente");
       mntmCliente.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/icone_cliente2.png")));
       mntmCliente.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       	   	CadCliente cliente = new CadCliente ();
           	cliente.setVisible(true);
        
       	
       	}
       });
       mnInicio.add(mntmCliente);
       
       JMenuItem mntmProduto = new JMenuItem("Produto");
       mntmProduto.setEnabled(false);
       mntmProduto.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/icone_produtosOrig.jpg")));
       mntmProduto.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       	 CadProduto produto;
		try {
			produto = new CadProduto ();
			produto.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       	}
       });
       mnInicio.add(mntmProduto);
       
       JMenu mnNewMenu = new JMenu("Funcion\u00E1rio");
       mnNewMenu.setEnabled(false);
       mnNewMenu.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       	   	}
       });
       
       JMenuItem mntmFornecedor = new JMenuItem("Fornecedor");
       mntmFornecedor.setEnabled(false);
       mntmFornecedor.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/Fornecedor.jpg")));
       mntmFornecedor.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {

       		CadFornecedor fornecedor = new CadFornecedor();
       		fornecedor.setVisible(true);

       	}
       });
       mnInicio.add(mntmFornecedor);
       mnNewMenu.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/funcionario.png")));
       mnInicio.add(mnNewMenu);
       
       JMenuItem mntmPagamento = new JMenuItem("Pagamento");
       mntmPagamento.setEnabled(false);
       mntmPagamento.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/coins2.png")));
       mntmPagamento.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       	PagamentoFuncionario pagamento;
		try {
			pagamento = new PagamentoFuncionario();
			pagamento.setVisible(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	
       	
       	}
       });
       mnNewMenu.add(mntmPagamento);
       
       JMenuItem mntmCadastrarFuncionrio = new JMenuItem("Cadastrar Funcion\u00E1rio");
       mntmCadastrarFuncionrio.setEnabled(false);
       mntmCadastrarFuncionrio.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/users.png")));
       mntmCadastrarFuncionrio.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       		FuncionarioCadastro funcionario;
			try {
				funcionario = new FuncionarioCadastro();
				funcionario.setVisible(true);
			} catch (exception|IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				log(e,tela);
//				logger.error(agora+" - Error "+ e.getMessage());

			} 
        
       	
       	}
       });
       mnNewMenu.add(mntmCadastrarFuncionrio);
       
       JMenuItem mntmFuncionrioDoMs = new JMenuItem("Funcion\u00E1rio do M\u00EAs");
       mntmFuncionrioDoMs.setEnabled(false);
       mnNewMenu.add(mntmFuncionrioDoMs);
       mntmFuncionrioDoMs.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/funcionario.png")));
       mntmFuncionrioDoMs.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       		
       	FuncionarioMes funcionario = new FuncionarioMes();
       	funcionario.setVisible(true);
       	
       	}
       });
  
//Referênte aos Menus  
       JMenu menuVenda = new JMenu("Venda");     
       menuVenda.setEnabled(false);
       barra.add(menuVenda);
  
//MENU VENDA         
//Referênte a NOVA ENTRADA do menu VENDA  
       JMenuItem menuItemNovaEntrada = new JMenuItem("Nova Entrada de Venda");     
       menuItemNovaEntrada.setEnabled(false);
       menuItemNovaEntrada.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/shopcart2.png")));
       menuVenda.add(menuItemNovaEntrada);  
       
       JMenuItem mntmRelatrioDeVenda = new JMenuItem("Relat\u00F3rio de Venda ");
       mntmRelatrioDeVenda.setEnabled(false);
       menuVenda.add(mntmRelatrioDeVenda);
       mntmRelatrioDeVenda.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/invoice2.png")));
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
     
     JMenu mnLogoff = new JMenu("Sair");
     mnLogoff.addMouseListener(new MouseAdapter() {
     	@Override
     	public void mouseClicked(MouseEvent arg0) {
     		
     	int opcao;
		opcao = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair "+nome_func+" ? ", "Sair" , JOptionPane.YES_NO_OPTION);				
			if (opcao==JOptionPane.YES_OPTION){

		     	Login login;
				try {
					login = new Login();
					setVisible(false);
					login.setVisible(true);
					login.setModal(true);
				} catch (IOException | SQLException | exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     			
			}
     	
     	
     	}
     });
     
       JMenu menuCompra = new JMenu("Sobre");     
       barra.add(menuCompra);  
       
//MENU COMPRA        
//Refêrente ao Pedido de Compra do MENU Compra  
     JMenuItem menuItemPedidoDeCompra = new JMenuItem("Sobre SoftWord");     
     menuItemPedidoDeCompra.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/Sobre.jpg")));
     menuCompra.add(menuItemPedidoDeCompra);         
     
     JMenuItem mntmCrticasESugestes = new JMenuItem("Cr\u00EDticas e Sugest\u00F5es");
     mntmCrticasESugestes.setIcon(new ImageIcon(Menu_Nivel1.class.getResource("/Images/Mail_add2.png")));
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
			Desktop.getDesktop().open(pdf);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
               }
               
            });
     barra.add(mnLogoff);
     
     int tamanho = getHeight();
     int altura = getWidth();
     
     String caminho = "/Images/Infotec.jpg";
     
     ImageIcon image = new ImageIcon(Menu_Nivel1.class.getResource("/Images/Infotec.jpg"));
     }     
    
    public static void main(String[] args) throws Exception {     
       Menu_Nivel1 teste = new Menu_Nivel1();     
       Login login = new Login();
       //teste.setVisible(true);
       login.setVisible(true);
       login.setModal(true);
       
       
 	   
    }     
}   