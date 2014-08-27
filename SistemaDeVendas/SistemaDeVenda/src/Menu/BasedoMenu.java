package Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Backup.CriarBackup;
import Backup.RestaurarBackup;
import Beans.ProdutoBean;
import Cliente.CadCliente;
import Conexao.exception;
import Fornecedor.CadFornecedor;
import Funcionario.FuncionarioCadastro;
import Funcionario.FuncionarioMes;
import Funcionario.PagamentoFuncionario;
import Funcoes.SwingUtil;
import Funcoes.Wallpaper;
import Produto.CadProduto;
import Relatorio.RelatorioSimples;
import Venda.PesqCliente;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;

import apresentacao.Login;
//import br.com.sani.util.Wallpaper;
//import java.io.File;
//import java.io.IOException;
@SuppressWarnings("serial")  
      
    public class BasedoMenu extends JFrame {     
    
//        private JPanel frame;
		private JFrame Menu = new JFrame();
        private JTextField textField;
        private JTextField textField_1;
        public String nome_func = Login.nome_func;
        public JPanel panelAvisos = new JPanel();
        public JLabel lblAvisos = new JLabel("");
        private final JLabel lblNewLabel = new JLabel("New label");
        
        public BasedoMenu() throws IOException {     
    	   
  
//Configurações do Frame         
super("Aplicação JAVA");
setBackground(Color.WHITE);
setIconImage(Toolkit.getDefaultToolkit().getImage(BasedoMenu.class.getResource("/Images/arroba.jpg")));
SwingUtil.lookWindows(this);
setTitle("Menu Principal -			"+nome_func+"- InfoTec");
getContentPane().setBackground(Color.LIGHT_GRAY);
getContentPane().setLayout(null);


//lblNewLabel.setBounds(158, 161, 98, 31);
getContentPane().add(Menu);
//       setSize(722, 431);     
//		setSize(1199, 697);     
       setExtendedState(Menu.MAXIMIZED_BOTH);
       setLocation(180,20);  
       setContentPane(Menu);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
       this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
      
       atualizaProduto();
       
       
//       frame = new Wallpaper();
//       frame.setBorder(new EmptyBorder(5, 5, 5, 5));
//       setContentPane(frame);
//       setVisible(false);
//       frame.setLayout(null);
       
       panelAvisos = new JPanel();
       panelAvisos.setBackground(Color.WHITE);
       panelAvisos.setBounds(0, 0, 342, 231);
       panelAvisos.setVisible(false);
       Menu.add(panelAvisos);
       
       JLabel lblWallpaper = new JLabel("New label");
       lblWallpaper.setBounds(0, 0, 1366, 685);
       lblWallpaper.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/InfotecTelaPrincipal.jpg")));
       Menu.add(panelAvisos);
       
       lblAvisos = new JLabel("dasdasdasdasddas");
       lblAvisos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
       lblAvisos.setBackground(Color.GRAY);
       lblAvisos.setBounds(10, 59, 322, 160);
//       Menu.add(panelAvisos);
       panelAvisos.add(lblAvisos);
       
       JLabel labelAvisos = new JLabel("AVISOS");
       labelAvisos.setHorizontalAlignment(SwingConstants.CENTER);
       labelAvisos.setFont(new Font("Segoe UI", Font.PLAIN, 17));
       labelAvisos.setForeground(Color.RED);
       labelAvisos.setBounds(10, 12, 322, 24);
       panelAvisos.add(labelAvisos);

       
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
			produto.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			pagamento.setVisible(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			} catch (exception | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				log(e,tela);
//				logger.error(agora+" - Error "+ e.getMessage());

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
     
     JMenu mnLogoff = new JMenu("Sair") ;
     mnLogoff.setIcon(new ImageIcon(BasedoMenu.class.getResource("/Images/icon_excluir.png")));
     mnLogoff.addMouseListener(new MouseAdapter() {
     	@Override
     	public void mouseClicked(MouseEvent arg0)  {
     		
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
			Desktop.getDesktop().open(pdf);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
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
     barra.add(mnLogoff);
     
     int tamanho = getHeight();
     int altura = getWidth();
     
     String caminho = "/Images/Infotec.jpg";
     
     ImageIcon image = new ImageIcon(BasedoMenu.class.getResource("/Images/Infotec.jpg"));
     }     
    
    public static void main(String[] args) throws IOException, SQLException, exception{     
       BasedoMenu teste = new BasedoMenu();     
       Login login = new Login();
       login.setVisible(true);
       login.setModal(true);
       

     
 	   
       
       
    }     
    
    void atualizaProduto(){

    		List<ProdutoBean> listaRela = new ArrayList<ProdutoBean>();
    		try {
				listaRela = Dao.ProdutoDao.consultarProduto("");
			} catch (exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		boolean resultado = false;
     		
     		
     		String dados[] = new String[8];
     		
    		for (ProdutoBean obj : listaRela)
    		{

    			dados[1] = String.valueOf(obj.getId_produto());
    			dados[2] = String.valueOf(obj.getNome());
    			dados[3] = String.valueOf(obj.getQuantidade());
    			
    			int quantidade = Integer.parseInt(dados[3]);
    			if(quantidade<5){
    				resultado = true;
    			}
    			
    		}
    		
    		if(resultado){
    			
    			
    		panelAvisos.setVisible(true);
    		String aviso = lblAvisos.getText().toString();
    		System.out.println("Entrou no atualiza Produto");
    		aviso += "\n Verifique seu estoque pois existem produto com baixa quantidade.";
    		System.out.println(aviso);
    		
    		lblAvisos.setText("3123123123123123123123123123");
    		
    		}else{
    			panelAvisos.setVisible(false);
    		}
    		
    	}
}   