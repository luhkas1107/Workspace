package Venda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Beans.ClienteBean;
import Conexao.exception;
import Dao.ClienteDao;
import java.awt.ComponentOrientation;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class PesqCliente extends JDialog implements  KeyListener {

	private final JPanel contentPanel = new JPanel();
//	JTextField txtId = new JTextField();
	public static String cod_cliente;
	public static String nome_cliente;
	public static String dados[] = new String[5]; 
	public static String textocliente;
	private JTextField txtId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PesqCliente dialog = new PesqCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void pegartexto(){
		PesquisaCliente pesquisa = new PesquisaCliente();
//		JOptionPane.showMessageDialog(null, textocliente);
		ClienteBean cliente = new ClienteBean();
		
		String teste = cliente.getNome();
		txtId.setText(textocliente);
		
		
	}

	/**	
	 * Create the dialog.g
	 */
	public PesqCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PesqCliente.class.getResource("/Images/icone_cliente.png")));
		setTitle("Cliente");
		setBounds(100, 100, 380, 186);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		
		{
			JLabel lblCodcliente = new JLabel("C\u00F3digo Cliente");
			lblCodcliente.setBounds(10, 77, 95, 14);
			contentPanel.add(lblCodcliente);
		}
		{
			JButton btnOk = new JButton("Ir Comprar");
			btnOk.setIcon(new ImageIcon(PesqCliente.class.getResource("/Images/shopcart2.png")));
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = txtId.getText().toString();
					
					pesquisar(id);
				}
			});
			btnOk.setBounds(201, 120, 159, 23);
			contentPanel.add(btnOk);
		}
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(PesqCliente.class.getResource("/Images/back.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 120, 95, 23);
		contentPanel.add(btnVoltar);
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 64, 370, 2);
			contentPanel.add(separator);
		}
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setIcon(new ImageIcon(PesqCliente.class.getResource("/Images/icone_cliente.png")));
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCliente.setBounds(0, 0, 360, 57);
		contentPanel.add(lblCliente);
		{
			txtId = new JTextField();
			txtId.setBounds(104, 74, 170, 17);
			txtId.addKeyListener(this);
			contentPanel.add(txtId);
			txtId.setColumns(10);
		}
		{
			JLabel label = new JLabel("");
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					PesquisaCliente pesquisa = new PesquisaCliente();
					setVisible(false);
					pesquisa.setVisible(true);
					
				}
			});
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label.setIcon(new ImageIcon(PesqCliente.class.getResource("/Images/21-spotlight.png")));
			label.setBounds(296, 66, 46, 35);
			contentPanel.add(label);
		}
	}
	
	void listacliente(String id){
		ClienteBean obj= new ClienteBean();
//		dados[0] = null;
//		dados[1] = null;
//		dados[2] = null;
//		dados[3] = null;
//		dados[4] = null;
 		try {
 			if(id.equals("")){
 				JOptionPane.showMessageDialog(null, "Favor digitar código válido.");
 			}else{
 			int id2 = Integer.parseInt(id);
 			obj = ClienteDao.consultarClienteID3(id2);
 			}
 			
 				
				dados[0] = String.valueOf(obj.getId_cliente());
				dados[1] = obj.getNome();
				dados[2] = String.valueOf(obj.getCpf());
				dados[3] = String.valueOf(obj.getTelefone());
				dados[4] = String.valueOf(obj.getUf());
				
				
		} catch (exception e) {
			JOptionPane.showMessageDialog(null, "Erro, favor verificar a identificação do cliente!");
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
// 		JOptionPane.showMessageDialog(null, "DADOS[1]" +dados[1]);
			
 			int id3 = Integer.parseInt(dados[0]);
 				
 			if(id3!=0) {
 				NovaEntradaVenda venda = new NovaEntradaVenda();
// 				JOptionPane.showMessageDialog(null, "Nome: " +nome_cliente+ "\nCod_cliente :" +cod_cliente);
 				nome_cliente = dados[1];
 				cod_cliente = dados[0];
 				
 				JOptionPane.showMessageDialog(null, "Nome: " +nome_cliente+ "\nIdentificação :" +cod_cliente);
 				
 				setVisible(false);
 				venda.setVisible(true);
 			}else{
 				JOptionPane.showMessageDialog(null, "Erro, favor verificar a identificação do cliente!");
 	 			
 			}
			
		
	}
	void pesquisar (String id){
		//String id = txtPesquisaId.getText().toString();
//		NovaEntradaVenda venda = new NovaEntradaVenda();
		 if (!txtId.equals(null)){
//			 JOptionPane.showMessageDialog(null, "Id " +id);
			 listacliente(id); 
		 }else{
			 JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
		 }
		 }
	
	public void keyPressed(KeyEvent event) {
		if(event.getSource() == txtId){
			if(event.getKeyCode() == 10){
				String id = txtId.getText().toString();
				
				pesquisar(id);
			}
	}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
