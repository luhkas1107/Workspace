package Cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import Beans.ClienteBean;
import Conexao.exception;
import Dao.ClienteDao;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.event.MouseMotionAdapter;
import java.awt.Cursor;
import javax.swing.DefaultComboBoxModel;

public class CadCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtnumero;
	private JTextField txtcomplemento;
	private JTextField txtbairro;
	private JTextField txtcidade;
	private JTextField txtemail;
	JLabel lblID = new JLabel("");
	JFormattedTextField txtTelefone = new JFormattedTextField();
	JFormattedTextField txtCpf = new JFormattedTextField();
	boolean	resultado;
	JComboBox comboBox = new JComboBox();
	JLabel lblValido = new JLabel();
	JLabel lblInvalido = new JLabel("");
	JLabel lblValido2 = new JLabel("");
	private JTable table;
	private JTextField txtPesquisaNome;
	private JTextField textField;
	private JTextField textField_1;
	JLabel lblInvalido2 = new JLabel("");
	JComboBox comboBox_1 = new JComboBox();



	public static  JFormattedTextField hideMascara(JFormattedTextField txt){  
	    try{  
	       txt.setFormatterFactory(null);                        
	        }catch(Exception ex){
	        	JOptionPane.showMessageDialog(null, "Erro ao formatar dados!");
	        }  
	    return txt;  
	}

	
	
	  class JTableRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public void setValue(Object value) {
			if (value instanceof ImageIcon) {
				if (value != null) {
					ImageIcon d = (ImageIcon) value;
					setIcon(d);
				}
			} else {
				super.setValue(value);
			}
		}
	}	
	  
	
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
//		      System.out.println("O email "+email+" e valido");
		      return true;
		    }
		    else{
//		      JOptionPane.showMessageDialog(null, "O E-mail "+email+" é inválido");
		      return false;
		    } 
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
		
	void limparpesquisa(){
		try {
			txtPesquisaNome.setText("");
			atualizaLista(table,txtPesquisaNome.getText().toString());
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void atualizarcliente(){
		ClienteBean objClie = new ClienteBean ();
			
		objClie.setNome(txtNome.getText());
		
		ClienteDao obj = new ClienteDao();
		try {
			ClienteDao.atualizarCliente(objClie);
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void atualizaFormulario(ClienteBean objClie1){
		int matr = objClie1.getId_cliente();
		txtNome.setText(objClie1.getNome());
		txtCpf.setText(objClie1.getCpf());
		txtTelefone.setText(objClie1.getTelefone());
		txtEndereco.setText(objClie1.getEndereco());
		txtnumero.setText(objClie1.getNum_residencia());
		txtcomplemento.setText(objClie1.getComplemento());
		txtbairro.setText(objClie1.getBairro());
		txtcidade.setText(objClie1.getCidade());
		comboBox.setSelectedItem(objClie1.getUf());
		txtemail.setText(objClie1.getEmail());
		
		
		//textField_1.setText(matr.toString());
		//int id_cliente = objClie1.getId_cliente();
		
		//textField.setText(matr.toString());
	//	if(objFunc.getSexo().equals("F")){
	//		radioButton_1.setSelected(true);
	//	}else{
	//		radioButton.setSelected(true);
	//	}
		setVisible(false);
		setVisible(true);
		}
	
	
	
	void limpar(){
		txtCpf.setText("");
		txtNome.setText("");
		txtEndereco.setText("");
		txtnumero.setText("");
		txtbairro.setText("");
		txtcidade.setText("");
		txtTelefone.setText("");
		txtemail.setText("");
		txtcomplemento.setText("");
		comboBox.setSelectedIndex(0);
		lblID.setText("");
	}
	
	void verificacao (){
		String cpf=txtCpf.getText().toString();
		String nome = txtNome.getText();
		String endereco = txtEndereco.getText();
		String numero = txtnumero.getText();
		String bairro = txtbairro.getText();
		String cidade = txtcidade.getText();
		String telefone = txtTelefone.getText().toString();
		String uf = comboBox.getSelectedItem().toString();
		
		
		
		if (!cpf.equals("") && !nome.equals("") && !endereco.equals("") && !numero.equals("") && !bairro.equals("") && !telefone.equals("")
				&& !cidade.equals("") && !uf.equals("")){				
			
			resultado = true;
			
			
		}else{
			resultado = false;
		}
		
		
		
		
		
	}
	void alterar(){
		
		ClienteBean cliente = new ClienteBean();
		
		cliente .setNome(txtNome.getText());
		cliente .setCpf(txtCpf.getText());
		cliente .setTelefone(txtTelefone.getText().toString());
		cliente .setEndereco(txtEndereco.getText());
		cliente .setNum_residencia(txtnumero.getText());	
		cliente .setComplemento(txtcomplemento.getText());
		cliente .setBairro(txtbairro.getText());
		cliente .setCidade(txtcidade.getText());
		cliente .setUf(comboBox.getSelectedItem().toString());
		cliente .setEmail(txtemail.getText());
		
		try {
			String email = txtemail.getText();
			int matr = Integer.parseInt(textField_1.getText()); 
			cliente.setId_cliente(matr);
			
			if (email.equals("")){
			ClienteDao.atualizarCliente(cliente);
			JOptionPane.showMessageDialog(null, "Dados atualizados com suscesso!");
			atualizaLista(table,"");
			limpar();
			}else if (validEmail(email)==true){
				ClienteDao.atualizarCliente(cliente);
				JOptionPane.showMessageDialog(null, "Dados atualizados com suscesso!");
				atualizaLista(table,"");
				limpar();
			}
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		
	
	void pegar (){
		
	
		
		ClienteBean obj = new ClienteBean();
		obj.setNome(txtNome.getText());
		obj.setCpf(txtCpf.getText().toString());
		obj.setTelefone(txtTelefone.getText().toString());
		obj.setEndereco(txtEndereco.getText());
		obj.setNum_residencia(txtnumero.getText());				
		obj.setComplemento(txtcomplemento.getText());
		obj.setBairro(txtbairro.getText());
		obj.setCidade(txtcidade.getText());
		obj.setEmail(txtemail.getText());
		obj.setUf(comboBox.getSelectedItem().toString());
		
		ClienteDao objDAO = new ClienteDao();
		if(txtCpf.getText().equals("")  || txtNome.getText().equals("") || txtTelefone.getText().equals("") || txtEndereco.getText().equals("")
		|| txtnumero.getText().equals("") || txtbairro.getText().equals("") || txtcidade.getText().equals("")||comboBox.getSelectedItem().toString().equals("")){
			JOptionPane.showMessageDialog(rootPane, "Campo Vazio!");
		}else{
			
			//atualizaLista(table, null);
			
		
		try {
			ClienteDao.inserirCliente(obj);
			JOptionPane.showMessageDialog(null, "Cliente Cadastrado!");
			limparpesquisa();
			limpar();
			
			
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadCliente dialog = new CadCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadCliente.class.getResource("/Images/icone_cliente2.png")));
		setTitle("Cadastro Cliente");
		setBounds(100, 100, 752, 508);
		//setBounds(100, 100, 355, 364);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		
		MaskFormatter CPF = new MaskFormatter();
		MaskFormatter telefone = new MaskFormatter();
								
			{
				try {
					telefone.setMask("(##) - ####-####");
					CPF.setMask("###.###.###-##");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
								
			}
			
			
			
			
		
		
		
		
		
		JLabel lblNome = new JLabel("*Nome");
		lblNome.setBounds(10, 170, 46, 14);
		contentPanel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(101, 167, 234, 17);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("*CPF");
		lblCpf.setBounds(10, 195, 46, 14);
		contentPanel.add(lblCpf);
		
		JLabel lblTelefone = new JLabel("*Telefone");
		lblTelefone.setBounds(10, 220, 67, 14);
		contentPanel.add(lblTelefone);
		
		JLabel lblEndereo = new JLabel("*Endere\u00E7o");
		lblEndereo.setBounds(10, 245, 67, 14);
		contentPanel.add(lblEndereo);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(101, 242, 128, 17);
		contentPanel.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblN = new JLabel("*N\u00BA");
		lblN.setBounds(239, 244, 46, 14);
		contentPanel.add(lblN);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(10, 270, 92, 14);
		contentPanel.add(lblComplemento);
		
		txtnumero = new JTextField();
		txtnumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			
				String caractere = "1234567890";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			
			}
		});
		txtnumero.setBounds(268, 242, 67, 17);
		contentPanel.add(txtnumero);
		txtnumero.setColumns(10);
		
		JLabel lblBairro = new JLabel("*Bairro");
		lblBairro.setBounds(10, 295, 46, 14);
		contentPanel.add(lblBairro);
		
		JLabel lblCidade = new JLabel("*Cidade");
		lblCidade.setBounds(10, 320, 46, 14);
		contentPanel.add(lblCidade);
		
		JLabel lblUf = new JLabel("*UF");
		lblUf.setBounds(10, 348, 46, 14);
		contentPanel.add(lblUf);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 376, 46, 14);
		contentPanel.add(lblEmail);
		
		txtcomplemento = new JTextField();
		txtcomplemento.setBounds(101, 268, 128, 17);
		contentPanel.add(txtcomplemento);
		txtcomplemento.setColumns(10);
		
		txtbairro = new JTextField();
		txtbairro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			}
		});
		txtbairro.setBounds(101, 293, 167, 17);
		contentPanel.add(txtbairro);
		txtbairro.setColumns(10);
		
		txtcidade = new JTextField();
		txtcidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			
				String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			}
		});
		txtcidade.setBounds(101, 318, 153, 17);
		contentPanel.add(txtcidade);
		txtcidade.setColumns(10);
		
		
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"SP"}));
		comboBox.setBounds(101, 345, 67, 17);
		contentPanel.add(comboBox);
		comboBox.addItem("");
		comboBox.addItem("AC");
		comboBox.addItem("AL");
		comboBox.addItem("AP");
		comboBox.addItem("AM");
		comboBox.addItem("BA");
		comboBox.addItem("CE");
		comboBox.addItem("DF");
		comboBox.addItem("ES");
		comboBox.addItem("GO");
		comboBox.addItem("MA");
		comboBox.addItem("MT");
		comboBox.addItem("MS");
		comboBox.addItem("MG");
		comboBox.addItem("PA");
		comboBox.addItem("PB");
		comboBox.addItem("PR");
		comboBox.addItem("PE");
		comboBox.addItem("PI");
		comboBox.addItem("RJ");
		comboBox.addItem("RN");
		comboBox.addItem("RS");
		comboBox.addItem("RO");
		comboBox.addItem("RR");
		comboBox.addItem("SC");
		comboBox.addItem("SP");
		comboBox.addItem("SE");
		comboBox.addItem("TO");
	
		
		txtemail = new JTextField();
		txtemail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				String email = txtemail.getText();
				
				lblValido2.setText("");
				lblInvalido2.setText("");
				
				if(validEmail(email)){
					lblValido2.setText("Válido");	
							
					}else{
						lblInvalido2.setText("Inválido");
					}
			}
		});
		txtemail.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
			
			
			}
		});
		txtemail.setBounds(101, 373, 184, 17);
		contentPanel.add(txtemail);
		txtemail.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(CadCliente.class.getResource("/Images/icone_cliente2.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				verificacao();
				
				String cpf = txtCpf.getText().toString();
				String email = txtemail.getText().toString(); 

				if (resultado == false){
				JOptionPane.showMessageDialog(null, "Verifique todos os campos!");
					
				}
				else if (resultado == true){
					if(validacpf(retiraMascara(cpf))){
						
						if(email.equals("")){
							pegar();	
						}
						
					else{
						if(validEmail(email)==true){
					pegar();
					}
						}
						}else{
						JOptionPane.showMessageDialog(null, "Cpf inválido!");
						
					}
				}
				
				
				
			}
		});
		btnCadastrar.setBounds(607, 435, 123, 23);
		contentPanel.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(CadCliente.class.getResource("/Images/Clipboard_1.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			limpar();
			
			}
		});
		btnLimpar.setBounds(20, 435, 125, 23);
		contentPanel.add(btnLimpar);
		
		{
			txtCpf=new JFormattedTextField(CPF);
			txtCpf.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent arg0) {
				
					String cpf = txtCpf.getText().toString();
					
					lblValido.setText("");
					lblInvalido.setText("");
					
					if(validacpf(retiraMascara(cpf))){
						lblValido.setText("Válido");	
						
						
						}else{
							lblInvalido.setText("Inválido");
						}
				}
			});
			txtCpf.setBounds(101, 192, 153, 17);
			contentPanel.add(txtCpf);
		}
		
		txtTelefone = new JFormattedTextField(telefone);
		txtTelefone.setBounds(101, 217, 153, 17);
		contentPanel.add(txtTelefone);
		
		JLabel lblobrigatorio = new JLabel("*Obrigat\u00F3rio");
		lblobrigatorio.setForeground(Color.RED);
		lblobrigatorio.setBounds(10, 401, 77, 14);
		contentPanel.add(lblobrigatorio);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 103, 342, 322);
		contentPanel.add(scrollPane);
		//scrollPane.add(table);
		
		String[][] dados = new String[][] {
			//	{"2521312", "Helvetica", "1111"},
			//	{"26123123", "Joao", "1111"}
		};
		String[] colunas = new String[] {
				"CPf", "Nome", "Telefone","CPF","Excluir"
			};
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
		

				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();
				
				//textField.setText((String) table.getValueAt(linha, 0));
				
				String nome = (txtNome.getText());
				
				
				
				ClienteDao objClie = new ClienteDao();
				
				
				
				
				
				try {
					objClie.consultarCliente(nome);
					
					List<ClienteBean> listaClie = new ArrayList<ClienteBean>();
					txtNome.setText((String) table.getValueAt(linha, 1));					
					String nome3 = txtNome.getText();
					listaClie = ClienteDao.consultarCliente(nome3);
					
			 		for (ClienteBean obj : listaClie) {
						String id= String.valueOf(obj.getId_cliente());
						String nome2 = obj.getNome();
						String telefone = obj.getTelefone();
						String cpf = obj.getCpf();
						String endereco = obj.getEndereco();
						String numero = obj.getNum_residencia();
						String complemento = obj.getComplemento();
						String bairro = obj.getBairro();
						String cidade = obj.getCidade();
						String uf = obj.getUf();
						String email = obj.getEmail();
						
					ClienteBean clientebean = new ClienteBean();
//					JOptionPane.showMessageDialog(null, " CPF : " +cpf);
					txtNome.setText((String) table.getValueAt(linha, 1));
					txtcomplemento.setText(complemento);
					txtbairro.setText(bairro);
					txtcidade.setText(cidade);
					txtemail.setText(email);
					comboBox.setSelectedItem(uf);
					txtCpf.setText(cpf);
					txtEndereco.setText(endereco);
					txtnumero.setText(numero);
					txtCpf.setText(cpf);
					txtTelefone.setText(telefone);
					textField_1.setText(id);
					lblID.setText(id);
					}
				} catch (exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				String id_cliente2 = (String) table.getValueAt(linha,0);
				String id_cliente = id_cliente2;
				
				//Integer mat = Integer.parseInt(cpf); 
				if(coluna == 4){
					int opcao;
					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o registro de matricula: "+id_cliente ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
					   if(opcao == JOptionPane.YES_OPTION){  
						   try {
							ClienteDao.excluirCliente(id_cliente2);
							atualizaLista(table,"");
							limpar();
							JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
						} catch (exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
					   }
				}
				if (coluna == 3){
					ClienteBean objClie1 = new ClienteBean();
					
					try {
						//JOptionPane.showMessageDialog(null, "
						objClie1 = ClienteDao.consultarClienteID(id_cliente);
						atualizaFormulario(objClie1);
					} catch (exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = new DefaultTableModel(dados,colunas);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Identificação", "Nome", "Telefone", "CPF", "Excluir"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
         table.setBounds(39, 175, 530, 232);
         try {
			atualizaLista(table,"");
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 71, 742, 2);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 11, 742, 2);
		contentPanel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 103, 742, 2);
		contentPanel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 138, 390, 2);
		contentPanel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(389, 103, 1, 322);
		contentPanel.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(389, 423, 1, 2);
		contentPanel.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 423, 742, 2);
		contentPanel.add(separator_6);
		
		JLabel lblIdcliente = new JLabel("C\u00F3digo");
		lblIdcliente.setBounds(10, 145, 77, 14);
		contentPanel.add(lblIdcliente);
		
		txtPesquisaNome = new JTextField();
		txtPesquisaNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
//				String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
//				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
//				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
//					arg0.consume();
//				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			

				String opcao = comboBox_1.getSelectedItem().toString();
				String nome = txtPesquisaNome.getText().toString();
				if(opcao.equals("Nome")){
				try {
					atualizaLista(table, nome);
				
				} catch (exception e) {
				
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				}
				else{

					try {
						int id = Integer.parseInt(txtPesquisaNome.getText());
						atualizaListaCod(table, id);
					} catch (exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			}
		});
		txtPesquisaNome.setBounds(101, 111, 184, 17);
		contentPanel.add(txtPesquisaNome);
		txtPesquisaNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(350, 34, 110, 14);
		contentPanel.add(lblNewLabel);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(CadCliente.class.getResource("/Images/editar.gif")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				alterar();
			
				
				
			}
		});
		btnAlterar.setBounds(328, 435, 110, 23);
		contentPanel.add(btnAlterar);
		
		textField = new JTextField();
		contentPanel.add(textField);
		textField.setVisible(false);
		textField.setText("");
		
		textField_1 = new JTextField();
		textField_1.setBounds(16, 24, 0, 0);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		
		lblID.setBounds(101, 142, 46, 14);
		contentPanel.add(lblID);
		
	
		lblInvalido.setForeground(Color.RED);
		lblInvalido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInvalido.setBounds(268, 195, 67, 14);
		contentPanel.add(lblInvalido);
		
		
		lblValido.setForeground(new Color(0, 0, 128));
		lblValido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValido.setBounds(268, 195, 46, 14);
		
		contentPanel.add(lblValido);
		
		JLabel label = new JLabel("");
		
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				String opcao = comboBox.getSelectedItem().toString();
				String nome = txtPesquisaNome.getText().toString();
				
				
				try {
					atualizaLista(table, nome);
				
				} catch (exception e) {
				
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				
				
				}
			
			}
		});
		label.setIcon(new ImageIcon(CadCliente.class.getResource("/Images/21-spotlight.png")));
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setBounds(304, 103, 46, 27);
		contentPanel.add(label);
		
		
		lblValido2.setForeground(new Color(0, 0, 128));
		lblValido2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValido2.setBounds(304, 376, 63, 14);
		contentPanel.add(lblValido2);
		
		
		lblInvalido2.setForeground(Color.RED);
		lblInvalido2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInvalido2.setBounds(304, 376, 63, 14);
		contentPanel.add(lblInvalido2);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(CadCliente.class.getResource("/Images/icone_cliente.png")));
		label_1.setBounds(287, 11, 63, 62);
		contentPanel.add(label_1);
		
	
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Nome", "C\u00F3digo"}));
		comboBox_1.setBounds(10, 110, 85, 23);
		contentPanel.add(comboBox_1);
		textField_1.setVisible(false);
		
		
		
	}


	protected void atualizaCliente(JTable table2) {
		// TODO Auto-generated method stub
		
	}
	
public void atualizaLista(JTable lista,String nome) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();
		
//        ImageIcon editar = new ImageIcon(CadCliente.class.getResource("/Images/editar.gif"));  
        ImageIcon excluir = new ImageIcon(CadCliente.class.getResource("/Images/icon_excluir.png"));
		
        
		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();		
		
//		renderer.setValue(editar);
		renderer.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(0).setCellRenderer(renderer);
		
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(4).setCellRenderer(renderer1);

        dtm.setRowCount(0);
		List<ClienteBean> listaClie = new ArrayList<ClienteBean>();
 		listaClie = ClienteDao.consultarCliente(nome);
 		String dados[] = new String[4]; 
 		
		for (ClienteBean obj : listaClie) {
			dados[0] = String.valueOf(obj.getId_cliente());
			dados[1] = obj.getNome();
			dados[2] = obj.getTelefone();
			dados[3] = obj.getCpf();
			
			((DefaultTableModel) table.getModel()).addRow(dados); 
		} 
		table.repaint();
	}

public void atualizaListaCod(JTable lista,int id) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();
		
//        ImageIcon editar = new ImageIcon(CadCliente.class.getResource("/Images/editar.gif"));  
        ImageIcon excluir = new ImageIcon(CadCliente.class.getResource("/Images/icon_excluir.png"));
		
        
        
		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();		
		
//		renderer.setValue(editar);
//		renderer.setHorizontalAlignment(JLabel.CENTER);
//		columnModel.getColumn(0).setCellRenderer(renderer);
		
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(4).setCellRenderer(renderer1);

        dtm.setRowCount(0);
		List<ClienteBean> listaClie = new ArrayList<ClienteBean>();
 		listaClie = ClienteDao.consultarClienteID2(id);
 		String dados[] = new String[4]; 
 		
		for (ClienteBean obj : listaClie) {
			dados[0] = String.valueOf(obj.getId_cliente());
			dados[1] = obj.getNome();
			dados[2] = obj.getTelefone();
			dados[3] = obj.getCpf();
			
			((DefaultTableModel) table.getModel()).addRow(dados); 
		} 
		table.repaint();
	}
}
