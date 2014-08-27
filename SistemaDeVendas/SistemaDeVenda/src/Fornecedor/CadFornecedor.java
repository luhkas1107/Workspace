package Fornecedor;

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

import Beans.FornecedorBean;
import Cliente.CadCliente;
import Conexao.exception;
import Dao.FornecedorDao;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class CadFornecedor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmpresa;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEmail;
	JLabel lblValido = new JLabel("V\u00E1lido");
	JLabel lblInvalido = new JLabel("Inv\u00E1lido");
	
	JComboBox comboPesquisa = new JComboBox();
	
	JLabel lblID = new JLabel("");
	boolean resultado = false;
	JFormattedTextField txtTelefone = new JFormattedTextField();
	JFormattedTextField txtCnpj = new JFormattedTextField();
	private Dao.FornecedorDao FornecedorDao = new Dao.FornecedorDao();
	JComboBox comboBox = new JComboBox();
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JTextField txtPesquisaNome;


	
	
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
	
	  void alterar(){
		  
		  FornecedorBean fornecedor = new FornecedorBean();
//			JOptionPane.showMessageDialog(null, "EstouVivo");
			
			FornecedorBean objForn = new FornecedorBean();

			
			objForn.setNome(txtNome.getText());
			objForn.setEmpresa(txtEmpresa.getText());
			objForn.setCnpj(txtCnpj.getText().toString());
			objForn.setTelefone(txtTelefone.getText());
			objForn.setEndereco(txtEndereco.getText());
			objForn.setNum_empresa(txtNumero.getText());	
			objForn.setComplemento(txtComplemento.getText());
			objForn.setCidade(txtCidade.getText());
			objForn.setBairro(txtBairro.getText());
			objForn.setUf(comboBox.getSelectedItem().toString());
			objForn.setEmail(txtEmail.getText());
			int matr = Integer.parseInt(lblID.getText());
			objForn.setId_fornecedor(matr);
			try {
				String opcao = comboPesquisa.getSelectedItem().toString();
				FornecedorDao.atualizarFornecedor(objForn);
				JOptionPane.showMessageDialog(null, "Dados atualizados com suscesso!");
//				atualizaFormulario(objForn);
				limpar();
				atualizaLista(table, "",opcao);
			} catch (exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		  
		  
	  }
	  
	  
	  
	  
	  
	void validacpf(String cpf){
		
	}
	
	
		
	

			public void atualizaFormulario(FornecedorBean objForn){
			txtNome.setText(objForn.getNome());
			txtCnpj.setText(objForn.getCnpj());
			txtTelefone.setText(objForn.getTelefone());
			txtEndereco.setText(objForn.getEndereco());
			txtNumero.setText(objForn.getNum_empresa());
			txtComplemento.setText(objForn.getComplemento());
			txtBairro.setText(objForn.getBairro());
			txtCidade.setText(objForn.getCidade());
			comboBox.setSelectedItem(objForn.getUf());
			txtEmail.setText(objForn.getEmail());
			Integer matr = objForn.getId_fornecedor();
			
			txtPesquisaNome.setText(matr.toString());
		
			setVisible(false);
			setVisible(true);
			}
		
		
	  
	
	
	void limpar (){
		txtEmpresa.setText("");
		txtCnpj.setText("");
		txtNome.setText("");
		txtEndereco.setText("");
		txtNumero.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		txtComplemento.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		comboBox.setSelectedIndex(0);
		lblID.setText("");
	}
	
	
	
	void verificacao(){
		String empresa = txtEmpresa.getText();
		String cnpj = txtCnpj.getText().toString();
		String nome = txtNome.getText();
		String endereco = txtEndereco.getText();
		String numero = txtNumero.getText();
		String bairro = txtBairro.getText();
		String cidade = txtCidade.getText();
		String uf = comboBox.getSelectedItem().toString();
		String telefone = txtTelefone.getText().toString();
		
		if(!empresa.equals("") && !cnpj.equals("") && !nome.equals("") && !endereco.equals("") && !numero.equals("") &&
				!bairro.equals("") && !cidade.equals("") && !uf.equals("") && !telefone.equals("")){
			resultado = true;
			
			
		}else{
			resultado = false;
			
		}
		
	}
	
	
	void pegar(){
		

		FornecedorBean fornecedor = new FornecedorBean();
	
		fornecedor.setNome(txtNome.getText());
		fornecedor.setEmpresa(txtEmpresa.getText());
		fornecedor.setCnpj(txtCnpj.getText().toString());
		fornecedor.setTelefone(txtTelefone.getText().toString());
		fornecedor.setEndereco(txtEndereco.getText());
		fornecedor.setNum_empresa(txtNumero.getText());	
		fornecedor.setComplemento(txtComplemento.getText());
		fornecedor.setBairro(txtBairro.getText());
		fornecedor.setCidade(txtCidade.getText());
		fornecedor.setUf(comboBox.getSelectedItem().toString());
		fornecedor.setEmail(txtEmail.getText());
		
		try {
			FornecedorDao.inserirFornecedor(fornecedor);
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso.");	
			String opcao = comboPesquisa.getSelectedItem().toString();
			atualizaLista(table, "",opcao);
limpar();
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadFornecedor dialog = new CadFornecedor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadFornecedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadFornecedor.class.getResource("/Images/Fornecedor.jpg")));
		setTitle("Cadastrar Fornecedor");
		setBounds(100, 100, 752, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		JLabel lblNome = new JLabel("*Supervisor");
		lblNome.setBounds(10, 170, 81, 14);
		contentPanel.add(lblNome);
		
		JLabel lblNomeempresa = new JLabel("*Empresa");
		lblNomeempresa.setBounds(10, 198, 96, 14);
		contentPanel.add(lblNomeempresa);
		
		JLabel lblNewLabel = new JLabel("*CNPJ");
		lblNewLabel.setBounds(10, 223, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*Telefone");
		lblNewLabel_1.setBounds(10, 248, 57, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblEndereco = new JLabel("*Endere\u00E7o");
		lblEndereco.setBounds(10, 273, 70, 14);
		contentPanel.add(lblEndereco);
		
		JLabel lblN = new JLabel("*N\u00BA");
		lblN.setBounds(259, 273, 46, 14);
		contentPanel.add(lblN);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(10, 298, 96, 14);
		contentPanel.add(lblComplemento);
		
		JLabel lblBairro = new JLabel("*Bairro");
		lblBairro.setBounds(10, 348, 46, 14);
		contentPanel.add(lblBairro);
		
		JLabel lblCidade = new JLabel("*Cidade");
		lblCidade.setBounds(10, 323, 46, 14);
		contentPanel.add(lblCidade);
		
		
		
		comboBox.setBounds(287, 345, 64, 20);
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
	
		
		MaskFormatter CNPJ = new MaskFormatter();
		MaskFormatter telefone = new MaskFormatter();
		

		try {
			telefone.setMask("(##) - ####-####");
			CNPJ.setMask("##.###.###/####-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 373, 46, 14);
		contentPanel.add(lblEmail);
		
		txtEmpresa = new JTextField();
		txtEmpresa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			
			}
		});
		txtEmpresa.setBounds(101, 195, 250, 17);
		contentPanel.add(txtEmpresa);
		txtEmpresa.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			
				String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			
			}
		});
		txtNome.setBounds(101, 168, 250, 17);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			
				String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			}
		});
		txtEndereco.setBounds(101, 271, 148, 17);
		contentPanel.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			
				String caractere = "1234567890";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			}
		});
		txtNumero.setBounds(287, 271, 64, 17);
		contentPanel.add(txtNumero);
		txtNumero.setColumns(10);
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(101, 295, 250, 17);
		contentPanel.add(txtComplemento);
		txtComplemento.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			}
		});
		txtBairro.setBounds(101, 346, 118, 17);
		contentPanel.add(txtBairro);
		txtBairro.setColumns(10);
		
		txtCidade = new JTextField();
		txtCidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			}
		});
		txtCidade.setBounds(101, 321, 250, 17);
		contentPanel.add(txtCidade);
		txtCidade.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			
				String email = txtEmail.getText();
				
				if(validEmail(email)){
					lblInvalido.setVisible(false);
					lblValido.setVisible(true);
					
					}else{
						lblValido.setVisible(false);
						lblInvalido.setVisible(true);
					}
			
			
			
			}
		});
		txtEmail.setBounds(101, 370, 148, 17);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(CadFornecedor.class.getResource("/Images/Fornecedor.jpg")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			verificacao();
			
			if (resultado != false){
				pegar();
			}
			else if (resultado == false){
				JOptionPane.showMessageDialog(null, "Verifique todos os campos.");	
			}
			
			}
		});
		btnCadastrar.setBounds(614, 436, 118, 23);
		contentPanel.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(CadFornecedor.class.getResource("/Images/Clipboard_1.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			limpar();
			
			}
		});
		btnLimpar.setBounds(10, 436, 118, 23);
		contentPanel.add(btnLimpar);
		
		JLabel lbluf = new JLabel("*UF");
		lbluf.setBounds(259, 348, 46, 14);
		contentPanel.add(lbluf);
		
		
		txtTelefone = new JFormattedTextField(telefone);
		txtTelefone.setBounds(101, 245, 148, 17);
		contentPanel.add(txtTelefone);
		
		txtCnpj=new JFormattedTextField(CNPJ);
		txtCnpj.setBounds(101, 221, 169, 17);
		contentPanel.add(txtCnpj);
		
		JLabel lblObrigatrio = new JLabel("* Obrigat\u00F3rio");
		lblObrigatrio.setForeground(Color.RED);
		lblObrigatrio.setBounds(10, 403, 81, 14);
		contentPanel.add(lblObrigatrio);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 71, 742, 2);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 11, 742, 2);
		contentPanel.add(separator_1);
		
		JSeparator separator_6 = new JSeparator();
		separator.setBounds(0, 71, 742, 2);
		contentPanel.add(separator);
		
		JSeparator separator_5 = new JSeparator();
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
		
		JSeparator separator_7 = new JSeparator();
		separator_5.setBounds(389, 423, 1, 2);
		contentPanel.add(separator_5);
		
		JSeparator separator_8 = new JSeparator();
		separator_6.setBounds(0, 423, 742, 2);
		contentPanel.add(separator_6);
		
		JLabel lblIdcliente = new JLabel("C\u00F3digo");
		lblIdcliente.setBounds(10, 145, 96, 14);
		contentPanel.add(lblIdcliente);
		
		txtPesquisaNome = new JTextField();
		txtPesquisaNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {


				String opcao = comboPesquisa.getSelectedItem().toString();
				String nome = txtPesquisaNome.getText().toString();
				
				//String id = txtPesquisaId.getText().toString();
				
				try {
					atualizaLista(table,txtPesquisaNome.getText().toString(),opcao);
					
				} catch (exception e) {
				
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
			}
		});
		txtPesquisaNome.setBounds(139, 110, 131, 20);
		contentPanel.add(txtPesquisaNome);
		txtPesquisaNome.setColumns(10);
		
		JLabel lblCliente = new JLabel("Fornecedor");
		lblCliente.setIcon(new ImageIcon(CadFornecedor.class.getResource("/Images/FornecedorOrig4.png")));
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCliente.setBounds(0, 11, 732, 62);
		contentPanel.add(lblCliente);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(CadFornecedor.class.getResource("/Images/editar.gif")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			alterar();
			
			}
		});
		btnAlterar.setBounds(330, 436, 105, 23);
		contentPanel.add(btnAlterar);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 103, 342, 322);
		contentPanel.add(scrollPane);
		//scrollPane.add(table);
		
		String[][] dados = new String[][] {
			//	{"2521312", "Helvetica", "1111"},
			//	{"26123123", "Joao", "1111"}
		};
		String[] colunas = new String[] {
				"Id_fornecedor", "Supervirsor", "Empresa","Telefone","Excluir"
			};
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {


				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();
				
				//textField.setText((String) table.getValueAt(linha, 0));
				
				String nome = (txtNome.getText());
				
				
				
				FornecedorDao objForn = new FornecedorDao();
				
				
				
				
				
				try {
					objForn.consultarFornecedor(nome);
					
					List<FornecedorBean> listaForn = new ArrayList<FornecedorBean>();
					txtNome.setText((String) table.getValueAt(linha, 1));					
					String nome3 = txtNome.getText();
					listaForn = FornecedorDao.consultarFornecedor(nome3);
					
			 		for (FornecedorBean obj : listaForn) {
						Integer id= obj.getId_fornecedor();
						String empresa = obj.getEmpresa();
						String nome2 = obj.getNome();
						String telefone = obj.getTelefone();
						String cnpj = obj.getCnpj();
						String endereco = obj.getEndereco();
						String numero = obj.getNum_empresa();
						String complemento = obj.getComplemento();
						String bairro = obj.getBairro();
						String cidade = obj.getCidade();
						String uf = obj.getUf();
						String email = obj.getEmail();
						
					txtNome.setText((String) table.getValueAt(linha, 1));
					txtEmpresa.setText(empresa);
					txtComplemento.setText(complemento);
					txtBairro.setText(bairro);
					txtCidade.setText(cidade);
					txtEmail.setText(email);
					comboBox.setSelectedItem(uf);
					txtCnpj.setText(cnpj);
					txtEndereco.setText(endereco);
					txtNumero.setText(numero);
					txtTelefone.setText(telefone);
					String id_forn = String.valueOf(id);
					lblID.setText(id_forn);
//					JOptionPane.showMessageDialog(null, " CNPJ : " +cnpj);
					
			 		}
				} catch (exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				String id_fornecedor1 = (String) table.getValueAt(linha,0);
				Integer id_fornecedor = Integer.parseInt(id_fornecedor1);
				//Integer mat = Integer.parseInt(cpf); 
				if(coluna == 4){
					int opcao;
					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o registro de matricula: "+id_fornecedor ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
					   if(opcao == JOptionPane.YES_OPTION){  
						   try {
							FornecedorDao.excluirFornecedor(id_fornecedor);
							atualizaLista(table,"","");
							limpar();
						} catch (exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
					   }
				}
				if (coluna == 3){
					FornecedorBean objForn1 = new FornecedorBean();
					
					try {
						objForn1 = FornecedorDao.consultarFornecedorID(id_fornecedor);
						atualizaFormulario(objForn1);
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
						"Identificação", "Supervisor", "Empresa", "Telefone", "Excluir"
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
		        
				scrollPane.setViewportView(table);
				
				
				lblID.setBounds(101, 143, 46, 14);
				contentPanel.add(lblID);
				
				try {
						atualizaLista(table,"","");
					} catch (exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
	
				comboPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Supervisor", "Empresa"}));
				comboPesquisa.setBounds(10, 109, 105, 23);
				contentPanel.add(comboPesquisa);

				 try {
					 String opcao = comboPesquisa.getSelectedItem().toString();
						atualizaLista(table,"",opcao);
					} catch (exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				
				JLabel label = new JLabel("");
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
					

						String opcao = comboPesquisa.getSelectedItem().toString();
						String nome = txtPesquisaNome.getText().toString();
						
						//String id = txtPesquisaId.getText().toString();
						
						try {
							atualizaLista(table,txtPesquisaNome.getText().toString(),opcao);
							
						} catch (exception e) {
						
							// TODO Auto-generated catch block
							
							e.printStackTrace();
						}
					
					}
				});
				label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label.setIcon(new ImageIcon(CadFornecedor.class.getResource("/Images/21-spotlight.png")));
				label.setBounds(305, 103, 46, 37);
				contentPanel.add(label);
				
				lblValido = new JLabel("V\u00E1lido");
				lblValido.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblValido.setForeground(new Color(0, 0, 128));
				lblValido.setBounds(259, 373, 46, 14);
				lblValido.setVisible(false);
				contentPanel.add(lblValido);
				
				lblInvalido = new JLabel("Inv\u00E1lido");
				lblInvalido.setForeground(Color.RED);
				lblInvalido.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblInvalido.setBounds(259, 373, 57, 14);
				lblInvalido.setVisible(false);
				contentPanel.add(lblInvalido);
				
				 
}
	
	
public void atualizaLista(JTable lista,String nome,String opcao) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();
		
        ImageIcon editar = new ImageIcon(CadCliente.class.getResource("/Images/editar.gif"));  
        ImageIcon excluir = new ImageIcon(CadCliente.class.getResource("/Images/icon_excluir.png"));
		
        
		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();		
		
		//renderer.setValue(editar);
		//renderer.setHorizontalAlignment(JLabel.CENTER);
		//columnModel.getColumn(3).setCellRenderer(renderer);
		
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(4).setCellRenderer(renderer1);

        dtm.setRowCount(0);
        if(opcao.equals("Supervisor")){
		List<FornecedorBean> listaForn = new ArrayList<FornecedorBean>();
 		listaForn = Dao.FornecedorDao.consultarFornecedor(nome);
 		
 		
 		String dados[] = new String[4]; 
		for (FornecedorBean obj : listaForn) {
			dados[0] = String.valueOf(obj.getId_fornecedor());
			dados[1] = obj.getNome();
			dados[2] = obj.getEmpresa();
			dados[3] = obj.getTelefone();
			
			((DefaultTableModel) table.getModel()).addRow(dados); 
		} 
		table.repaint();
	}
if(opcao.equals("Empresa")){
	List<FornecedorBean> listaForn = new ArrayList<FornecedorBean>();
		listaForn = Dao.FornecedorDao.consultarFornecedor02(nome);
		
		
		String dados[] = new String[4]; 
	for (FornecedorBean obj : listaForn) {
		dados[0] = String.valueOf(obj.getId_fornecedor());
		dados[1] = obj.getNome();
		dados[2] = obj.getEmpresa();
		dados[3] = obj.getTelefone();
		
		((DefaultTableModel) table.getModel()).addRow(dados); 
	} 
	table.repaint();	
 		}

}
public static boolean validEmail(String email) {
    System.out.println("Metodo de validacao de email");
    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
    Matcher m = p.matcher(email); 
    if (m.find()){
//      System.out.println("O email "+email+" é valido");
      return true;
    }
    else{
//      JOptionPane.showMessageDialog(null, "O E-mail "+email+" é inválido");
      return false;
    } 
}


}
