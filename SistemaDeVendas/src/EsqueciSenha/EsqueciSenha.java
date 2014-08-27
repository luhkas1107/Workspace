package EsqueciSenha;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Beans.FuncionarioBean;
import Conexao.exception;
import Dao.FuncionarioDao;
import apresentacao.Login;

public class EsqueciSenha extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JFormattedTextField formattedCPF = new JFormattedTextField();
	JFormattedTextField formattedCTPS = new JFormattedTextField();
	private JTextField textId;
	JLabel label = new JLabel("");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EsqueciSenha dialog = new EsqueciSenha();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EsqueciSenha() {
		setTitle("Esqueci Senha - Informa\u00E7\u00F5es Pessoais");
		setBounds(100, 100, 299, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(90, 23, 91, 87);
		contentPanel.add(panel);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 91, 87);
		label.setIcon(new ImageIcon(Login.class.getResource("/Images/funcionarioOrg.png")));
		panel.add(label);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(47, 170, 35, 14);
		contentPanel.add(lblCpf);
		
		JLabel lblCtps = new JLabel("CTPS");
		lblCtps.setBounds(47, 212, 35, 14);
		contentPanel.add(lblCtps);
		
		
		
		MaskFormatter CPF = new MaskFormatter();
		MaskFormatter CTPS = new MaskFormatter();
		
				try {
			
					CPF.setMask("###.###.###-##");
					CTPS.setMask("#####-###");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	formattedCPF = new JFormattedTextField(CPF);
	formattedCPF.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
		
			String cpf = formattedCPF.getText().toString();
			String ctps = formattedCTPS.getText().toString();
			int id = Integer.parseInt(textId.getText());
			FuncionarioBean objFunc = new FuncionarioBean();
			try {
				objFunc =  FuncionarioDao.EsqueceuSenha(cpf, ctps, id);
			} catch (exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(objFunc.getId_func() > 0){
				
				String caminho = objFunc.getCaminho();	
				
				ImageIcon  imagem = new ImageIcon(caminho);
         	    imagem.setImage(imagem.getImage().getScaledInstance(87,87,100));
         	    label.setIcon(imagem);
		
			}else{
				label.setIcon(new ImageIcon(Login.class.getResource("/Images/funcionarioOrg.png")));
			}
		
		}
	});
				formattedCPF.setBounds(47, 184, 171, 17);
				contentPanel.add(formattedCPF);
				
	formattedCTPS = new JFormattedTextField(CTPS);
	formattedCTPS.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
		
			String cpf = formattedCPF.getText().toString();
			String ctps = formattedCTPS.getText().toString();
			int id = Integer.parseInt(textId.getText());
			FuncionarioBean objFunc = new FuncionarioBean();
			try {
				objFunc =  FuncionarioDao.EsqueceuSenha(cpf, ctps, id);
			} catch (exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(objFunc.getId_func() > 0){
				
				String caminho = objFunc.getCaminho();	
				
				ImageIcon  imagem = new ImageIcon(caminho);
         	    imagem.setImage(imagem.getImage().getScaledInstance(87,87,100));
         	    label.setIcon(imagem);
		
			}else{
				label.setIcon(new ImageIcon(Login.class.getResource("/Images/funcionarioOrg.png")));
			}
		
		}
	});
				formattedCTPS.setBounds(47, 226, 171, 17);
				contentPanel.add(formattedCTPS);
		JButton btnGerarNovaSenha = new JButton("Gerar Nova Senha");
		btnGerarNovaSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			String 	cpf = formattedCPF.getText().toString();
			String ctps = formattedCTPS.getText().toString();
			int id = Integer.parseInt(textId.getText());

					
					FuncionarioBean objFunc = new FuncionarioBean();
					try {
						objFunc =  FuncionarioDao.EsqueceuSenha(cpf, ctps, id);
					} catch (exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					if(objFunc.getId_func() > 0){
						int opcao;
						opcao = JOptionPane.showConfirmDialog(null,"Deseja receber uma nova senha para o registro: "+objFunc.getId_func()+ "?" ,"Cuidado!!",JOptionPane.YES_NO_OPTION);
						if(opcao == JOptionPane.YES_OPTION){
							String senhaNova="";
							for (int numSenha=1;numSenha<=5;numSenha++){
								int num = (int) (Math.random() * 9);
								senhaNova += num;
							}
							boolean resultado;
							try {
								resultado = FuncionarioDao.atualizarLogin(senhaNova, objFunc.getId_func());
								JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!" +
										"\nSua nova senha é: " +senhaNova);
								
							} catch (exception e) {
								JOptionPane.showMessageDialog(null, "Não foi possível alterar sua senha" +
										"\nFavor contatar o administrador");
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Favor digite as informações corretamente.");
					}
				
			}
		});
		btnGerarNovaSenha.setBounds(131, 308, 142, 23);
		contentPanel.add(btnGerarNovaSenha);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					setVisible(false);
					Login login;
					login = new Login();
					login.setVisible(true);
					
				} catch (IOException | SQLException | exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVoltar.setBounds(10, 308, 89, 23);
		contentPanel.add(btnVoltar);
		
		JLabel lblIdentificao = new JLabel("Identifica\u00E7\u00E3o");
		lblIdentificao.setBounds(47, 252, 83, 14);
		contentPanel.add(lblIdentificao);
		
		textId = new JTextField();
		textId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			
				String caractere = "0123456789";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			
			
				String cpf = formattedCPF.getText().toString();
				String ctps = formattedCTPS.getText().toString();
				int id = Integer.parseInt(textId.getText());
				FuncionarioBean objFunc = new FuncionarioBean();
				try {
					objFunc =  FuncionarioDao.EsqueceuSenha(cpf, ctps, id);
				} catch (exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(objFunc.getId_func() > 0){
					
					String caminho = objFunc.getCaminho();	
					
					ImageIcon  imagem = new ImageIcon(caminho);
	         	    imagem.setImage(imagem.getImage().getScaledInstance(87,87,100));
	         	    label.setIcon(imagem);
			
				}else{
					label.setIcon(new ImageIcon(Login.class.getResource("/Images/funcionarioOrg.png")));
				}
			
			
			}
		});
		textId.setBounds(47, 266, 83, 20);
		contentPanel.add(textId);
		textId.setColumns(10);
		
		JLabel lblEsqueciMinhaSenha = new JLabel("Esqueci Minha Senha");
		lblEsqueciMinhaSenha.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEsqueciMinhaSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsqueciMinhaSenha.setBounds(0, 125, 283, 34);
		contentPanel.add(lblEsqueciMinhaSenha);
	}
}
