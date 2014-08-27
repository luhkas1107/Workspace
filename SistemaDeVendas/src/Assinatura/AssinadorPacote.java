package Assinatura;

import groovy.model.FormModel;
import Log.Log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Conexao.exception;
import Dao.AssinaturaDao;
import apresentacao.Login;

import java.util.Timer;
import java.awt.SystemColor;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class AssinadorPacote extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField txt1;
	private JFormattedTextField txt2;
	private JFormattedTextField txt3;
	JFormattedTextField formattedCnpj = new JFormattedTextField();
	JFormattedTextField formattedTelefone = new JFormattedTextField();
	JFormattedTextField formattedTextField_2 = new JFormattedTextField();

	JCheckBox CheckAtualizacao = new JCheckBox("Receber Atualiza\u00E7\u00F5es");
	
	
	static JLabel label_2 = new JLabel("");
	JPanel panel = new JPanel();
	Timer timer = new Timer();  
	static JPanel panel_Principal = new JPanel();
	private JTextField txtEmpresa;
	
	public static String tela = "AssinadorPacote";
	public static String aux ;
	public static String empresa,cnpj,telefone,email,atualizacao,chave;
	

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		try {
			AssinadorPacote dialog = new AssinadorPacote();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			log(e,tela);
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public AssinadorPacote() throws IOException {
		setTitle("Assinatura");
		setBounds(100, 100, 450, 361);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		  Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	        this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		
	        MaskFormatter mask4 = new MaskFormatter ();
	        MaskFormatter mask3 = new MaskFormatter ();
	        MaskFormatter maskTelefone = new MaskFormatter();
	        MaskFormatter maskCnpj = new MaskFormatter();
	        try {
				mask3.setMask("AAAA");
	        	mask4.setMask("AAAAA");
	        	maskTelefone.setMask("(##) ####-####");
	        	maskCnpj.setMask("##.###.###/####-##");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				log(e1,tela);

				e1.printStackTrace();
			}
	        
	        
	        
		{
			
			
			panel_Principal = new JPanel();
			panel_Principal.setBounds(0, 0, 434, 323);
			contentPanel.add(panel_Principal);
			
			panel_Principal.setLayout(null);
			
				
				JButton btnAssinarPacote = new JButton("Assinar Pacote");
				btnAssinarPacote.setBounds(10, 227, 242, 77);
				panel_Principal.add(btnAssinarPacote);
				btnAssinarPacote.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
				
//				Pegando a chave digitada
				aux=txt1.getText(); // Pegando primeira parte
				aux+=txt2.getText();//Pegando a segunda parte
				aux+=txt3.getText();//Pegando a terceira parte
				
				if(CheckAtualizacao.isSelected()){
					atualizacao = "S";						// Pegando o valor do checkBox 
				}else{
					atualizacao = "N";						// Pegando o valor do checkBox 
				}
				
				System.out.println("atualizacao> " +atualizacao);
				
				cnpj = formattedCnpj.getText().toString(); 			//Pegando CNPJ
				empresa = txtEmpresa.getText().toString(); 			//Pegando Empresa
				telefone = formattedTelefone.getText().toString(); 	//Pegando telefone
				email = formattedTextField_2.getText().toString();	//Pegando E-mail
				chave = aux;
				System.out.println(cnpj+"--"+empresa+"--"+telefone+"--"+email);
				
				
				if (!cnpj.equals("  .   .   /    -  ")
						&&!empresa.equals("")
						&&!telefone.equals("(  )     -    ")
						&&!email.equals("")){  

						cnpj = cnpj.replace('.', ' ');//onde há ponto coloca espaço  
		                cnpj = cnpj.replace('/', ' ');//onde há barra coloca espaço  
		                cnpj = cnpj.replace('-', ' ');//onde há traço coloca espaço  
		                cnpj = cnpj.replaceAll(" ", "");//retira espaço  
		                
		                System.out.println("Chave: "+aux);
		                System.out.println("CNPJ: "+cnpj);
						
		                tempo();
						    
				}else{
					JOptionPane.showMessageDialog(null,"Favor verificar todos os campos!!");
				}
				
				
				
				
				
				
				
				
				
//				assinar();
					
					}
				});
				
				
				
				btnAssinarPacote.setFont(new Font("Segoe UI", Font.PLAIN, 24));
				{
					JLabel lblAssinarPacote = new JLabel("Assinar Pacote");
					lblAssinarPacote.setBounds(0, 0, 434, 66);
					panel_Principal.add(lblAssinarPacote);
					lblAssinarPacote.setFont(new Font("Segoe UI", Font.PLAIN, 32));
					lblAssinarPacote.setHorizontalAlignment(SwingConstants.CENTER);
				}
				{
					JButton btnVoltar = new JButton("Voltar");
					btnVoltar.setBounds(281, 274, 116, 30);
					panel_Principal.add(btnVoltar);
					
							
							txt1 = new JFormattedTextField(mask4);
							txt1.setBounds(10, 101, 125, 30);
							panel_Principal.add(txt1);
							txt1.setHorizontalAlignment(SwingConstants.CENTER);
							txt1.setFont(new Font("Tahoma", Font.BOLD, 20));
							txt1.setColumns(4);
							{
								txt2 = new JFormattedTextField(mask4);
								txt2.setBounds(163, 101, 135, 30);
								panel_Principal.add(txt2);
								txt2.setHorizontalAlignment(SwingConstants.CENTER);
								txt2.setFont(new Font("Tahoma", Font.BOLD, 20));
								txt2.setColumns(10);
							}
							{
								txt3 = new JFormattedTextField(mask3);
								txt3.setBounds(324, 101, 100, 30);
								panel_Principal.add(txt3);
								txt3.setHorizontalAlignment(SwingConstants.CENTER);
								txt3.setFont(new Font("Tahoma", Font.BOLD, 20));
								txt3.setColumns(10);
							}
							
							JLabel label = new JLabel("-");
							label.setBounds(288, 106, 46, 14);
							panel_Principal.add(label);
							label.setHorizontalAlignment(SwingConstants.CENTER);
							
							JLabel label_1 = new JLabel("-");
							label_1.setBounds(127, 107, 46, 14);
							panel_Principal.add(label_1);
							label_1.setHorizontalAlignment(SwingConstants.CENTER);
							
							JSeparator separator = new JSeparator();
							separator.setBounds(0, 64, 434, 2);
							panel_Principal.add(separator);
							
							JLabel lblEmpresa = new JLabel("Empresa");
							lblEmpresa.setBounds(10, 168, 58, 14);
							panel_Principal.add(lblEmpresa);
							
							txtEmpresa = new JTextField();
							txtEmpresa.setBounds(78, 165, 152, 20);
							panel_Principal.add(txtEmpresa);
							txtEmpresa.setColumns(10);
							
							JSeparator separator_1 = new JSeparator();
							separator_1.setBounds(0, 142, 434, 2);
							panel_Principal.add(separator_1);
							
							formattedCnpj = new JFormattedTextField(maskCnpj);
							formattedCnpj.setBounds(78, 196, 152, 20);
							panel_Principal.add(formattedCnpj);
							
							JLabel lblCnpj = new JLabel("CNPJ");
							lblCnpj.setBounds(10, 199, 46, 14);
							panel_Principal.add(lblCnpj);
							
							JLabel lblTelefone = new JLabel("Telefone");
							lblTelefone.setBounds(252, 168, 58, 14);
							panel_Principal.add(lblTelefone);
							
							formattedTelefone = new JFormattedTextField(maskTelefone);
							formattedTelefone.setBounds(308, 165, 116, 20);
							panel_Principal.add(formattedTelefone);
							
							JLabel lblEmail = new JLabel("E-mail");
							lblEmail.setBounds(252, 202, 46, 14);
							panel_Principal.add(lblEmail);
							
							formattedTextField_2 = new JFormattedTextField();
							formattedTextField_2.setBounds(308, 196, 116, 20);
							panel_Principal.add(formattedTextField_2);
							
							CheckAtualizacao = new JCheckBox("Receber Atualiza\u00E7\u00F5es");
							CheckAtualizacao.setBounds(269, 235, 165, 23);
							panel_Principal.add(CheckAtualizacao);
							CheckAtualizacao.setSelected(true);
							
							label_2 = new JLabel("");
							label_2.setHorizontalAlignment(SwingConstants.CENTER);
							label_2.setIcon(new ImageIcon("C:\\Users\\Luhkas\\Documents\\Workspace\\SistemaDeVendas\\SistemaDeVendas\\src\\Images\\carregando.gif"));
							label_2.setBounds(0, 64, 434, 30);
							label_2.setVisible(false);
							panel_Principal.add(label_2);
							
							btnVoltar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						System.out.println(" ------------------- Finalizando Assinatura() ------------------- ");
						Login login;
						try {
							login = new Login();
							setModal(false);
							setVisible(false);
							login.setModal(true);
							login.setVisible(true);
							
						} catch (IOException | SQLException | exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
					});
				}
		}
	}
	
	
	 static void assinar() throws IOException{
		label_2.setVisible(false);
		System.out.println("- Entrando no metodo assinar()");
		System.out.println("Empresa "+empresa+"\nCNPJ: "+cnpj+"\nTelefone: "+telefone+"\nEmail: "+email);
		
		
		try {
			System.out.println("- Fazendo assinatura do pacote.");
			
			AssinaturaDao.atualizarAssinaturaAtivado(empresa,cnpj,telefone,email,atualizacao,chave);
			
			System.out.println("- Assinatura do pacote Finalizada");
			JOptionPane.showMessageDialog(null, "Pacote Assinado com sucesso!");
			
		} catch (exception e) {
//			 TODO Auto-generated catch block
			log(e,tela);

			e.printStackTrace();
		}
		
		
	}
	
	 public void tempo(){
		   label_2.setVisible(true);
	  	   Thread_Analisar segundos = new Thread_Analisar();
		   
	  	   segundos.init();
		   
		   
	
	
	
}
	 public static void Keyinvalida(){
		 label_2.setVisible(false);
		 
		 
		 JOptionPane.showMessageDialog(null, "Chave Inválida!");
		 System.out.println("Chave inválida!");
		 
	 }


private static void log(Exception e, String tela) throws IOException {
	// TODO Auto-generated method stub
	Log logging = new Log();
	Log.gerarLogApp(e, tela);
}
}