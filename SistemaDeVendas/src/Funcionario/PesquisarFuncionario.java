package Funcionario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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

import Beans.FuncionarioBean;
import Conexao.exception;
import Dao.FuncionarioDao;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PesquisarFuncionario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static String id;
	public static String nome2;
	public static String telefone;
	public static String cpf ;
	public static String  endereco ;
	public static String numero ;
	public static String complemento ;
	public static String bairro ;
	public static String cidade ;
	public static String uf ;
	public static String email ;
	public static String caminho_imagem="";
	public static String num_matricula_func ;
	public static String funcao_func ;
	public static String inicio_func ;
	public static String termino_func ;
	public static float salario_func ;
	public static String comissao_func ;
	public static String cpts ;
	public static String sexo_func ;
	public static String senha;
	public static String situacao;
	public static boolean clicar;
	JComboBox comboBox = new JComboBox();
	
	
	private JTextField txtPesquisaNome;
	private JTable table;
	
	
	

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

	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PesquisarFuncionario dialog = new PesquisarFuncionario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PesquisarFuncionario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PesquisarFuncionario.class.getResource("/Images/funcionario.png")));
		setTitle("Pesquisar Funcion\u00E1rio");
		setBounds(100, 100, 752, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);


		{
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 73, 742, 2);
			contentPanel.add(separator);
		}
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 169, 745, 2);
		contentPanel.add(separator_1);
		

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFuncionrio.setBounds(324, 22, 120, 14);
		contentPanel.add(lblFuncionrio);
		
		txtPesquisaNome = new JTextField();
		txtPesquisaNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			
				String opcao =  comboBox.getSelectedItem().toString();
				String nome2 = txtPesquisaNome.getText().toString();
				
//				String id = txtPesquisaId.getText().toString();
				if(opcao.equals("Nome")){
				try {
					atualizaLista(table,nome2);
				
				} catch (exception e1) {
				
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}
				}else{
					try {
						int id = Integer.parseInt(nome2);
						atualizaListaCod(table, id);
					
					} catch (exception e1) {
					
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
					}	
				}
			
			}
		});
		txtPesquisaNome.setBounds(262, 111, 212, 20);
		contentPanel.add(txtPesquisaNome);
		txtPesquisaNome.setColumns(10);
		
		JButton btnNewButton = new JButton("Pequisar");
		btnNewButton.setIcon(new ImageIcon(PesquisarFuncionario.class.getResource("/Images/21-spotlight.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcao =  comboBox.getSelectedItem().toString();
				String nome2 = txtPesquisaNome.getText().toString();
				
//				String id = txtPesquisaId.getText().toString();
				if(opcao.equals("Nome")){
				try {
					atualizaLista(table,nome2);
				
				} catch (exception e1) {
				
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}
				}else{
					try {
						int id = Integer.parseInt(nome2);
						atualizaListaCod(table, id);
					
					} catch (exception e1) {
					
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
					}	
				}
				}
			
			
			
		});
		btnNewButton.setBounds(313, 142, 120, 23);
		contentPanel.add(btnNewButton);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 169, 732, 269);
		contentPanel.add(scrollPane);
		
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
			public void mouseClicked(MouseEvent e) {


				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();
				
				//textField.setText((String) table.getValueAt(linha, 0));
				
//				String nome = (txtPesquisaNome.getText());
				
				
				
				FuncionarioDao objClie = new FuncionarioDao();
				
				
				
				
				 String id_func = (String) table.getValueAt(linha,0);
				
				//Integer mat = Integer.parseInt(cpf); 
				if(coluna == 5){
					int opcao;
					opcao = JOptionPane.showConfirmDialog(null,"Deseja desativar o registro de matricula: "+id_func ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
					   if(opcao == JOptionPane.YES_OPTION){  
						   try {
							FuncionarioDao.excluirFuncionario(id_func);
							atualizaLista(table,"");
							JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
						} catch (exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							
					   }
				}
				
				try {
//					objClie.consultarFuncionarios(nome);
					
					List<FuncionarioBean> listaFunc = new ArrayList<FuncionarioBean>();
					txtPesquisaNome.setText((String) table.getValueAt(linha, 0));					
					String id4 = (String) table.getValueAt(linha, 0);
					listaFunc = FuncionarioDao.consultarFuncionariosID(Integer.parseInt(id4));
					
			 		for (FuncionarioBean obj : listaFunc) {
						id= String.valueOf(obj.getId_func());
						nome2 = obj.getNome_func();
						senha = obj.getSenha_func();
						telefone = obj.getTelefone_func();
						endereco = obj.getEndereco_func();
						numero = obj.getNum_func();
//						JOptionPane.showMessageDialog(null, "Numero :" +numero);	
						complemento = obj.getComple_func();
						bairro = obj.getBairro_func();
						cidade = obj.getCidade_func();
						uf = obj.getUf_func();
						cpf = obj.getCpf_func();
						num_matricula_func = obj.getNum_matricula_func();
						funcao_func = obj.getFuncao_func();
						inicio_func = obj.getInicio_func();
						termino_func = obj.getTermino_func();
						salario_func = obj.getSalario_func();
						//comissao_func = obj.getPorcentagem_comissao_func();
						cpts= obj.getCpts();
						sexo_func = obj.getSexo_func();
						caminho_imagem = obj.getCaminho();
						email = obj.getEmail();
						situacao = obj.getSituacao();
//						JOptionPane.showMessageDialog(null, "Caminho :" +caminho_imagem);	
						clicar = true;
						
						FuncionarioCadastro pesquisa = new FuncionarioCadastro();
						pesquisa.setVisible(true);	
						pesquisa.pesquisar(clicar);
						setVisible(false);

									
//						dispose();
							
						
						
//					FuncionarioBean funcionariobean= new FuncionarioBean();
//					JOptionPane.showMessageDialog(null, " NOME : " +nome2);
					
									
					
//					txtNome.setText((String) table.getValueAt(linha, 1));
//					txtcomplemento.setText(complemento);
//					txtbairro.setText(bairro);
//					txtcidade.setText(cidade);
//					txtemail.setText(email);
//					comboBox.setSelectedItem(uf);
//					txtCpf.setText(cpf);
//					txtEndereco.setText(endereco);
//					txtnumero.setText(numero);
//					txtCpf.setText(cpf);
//					txtTelefone.setText(telefone);
//					textField_1.setText(id);
					}

					
				} catch (exception | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
			
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = new DefaultTableModel(dados,colunas);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Identifica\u00E7\u00E3o", "Nome", "Telefone", "CPF", "Situa\u00E7\u00E3o", "Excluir"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true, true
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
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(PesquisarFuncionario.class.getResource("/Images/back.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			FuncionarioCadastro func;
			try {
				func = new FuncionarioCadastro();
				func.setVisible(true);
			} catch (exception | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btnVoltar.setBounds(10, 442, 105, 23);
		contentPanel.add(btnVoltar);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nome", "C\u00F3digo"}));
		comboBox.setBounds(324, 80, 89, 20);
		contentPanel.add(comboBox);
		
	}
public void atualizaLista(JTable lista,String nome) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();
//        ImageIcon editar = new ImageIcon(FuncionarioCadastro.class.getResource("/Images/editar.gif"));  
        ImageIcon excluir = new ImageIcon(FuncionarioCadastro.class.getResource("/Images/icon_excluir.png"));
		
        
//		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();		
		
		//renderer.setValue(editar);
		//renderer.setHorizontalAlignment(JLabel.CENTER);
		//columnModel.getColumn(3).setCellRenderer(renderer);
		
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(5).setCellRenderer(renderer1);

        dtm.setRowCount(0);
		List<FuncionarioBean> listaFunc= new ArrayList<FuncionarioBean>();
 		listaFunc = FuncionarioDao.consultarFuncionarios(nome);
 		String dados[] = new String[5]; 
		for (FuncionarioBean obj : listaFunc) {
			
			String situacao = obj.getSituacao();
			if(situacao.equals("Ativado")){
			dados[0] = String.valueOf(obj.getId_func());
			dados[1] = obj.getNome_func();
			dados[2] = obj.getTelefone_func();
			dados[3] = obj.getCpf_func();
			dados[4] = obj.getSituacao();
			((DefaultTableModel) table.getModel()).addRow(dados);
			}
		} 
		table.repaint();
	}
public void atualizaListaCod(JTable lista,int cod) throws exception{
	
	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
	TableColumnModel columnModel = table.getColumnModel();

//    ImageIcon editar = new ImageIcon(FuncionarioCadastro.class.getResource("/Images/editar.gif"));  
    ImageIcon excluir = new ImageIcon(FuncionarioCadastro.class.getResource("/Images/icon_excluir.png"));
	
    
//	JTableRenderer renderer = new JTableRenderer();
	JTableRenderer renderer1 = new JTableRenderer();		
	
	//renderer.setValue(editar);
	//renderer.setHorizontalAlignment(JLabel.CENTER);
	//columnModel.getColumn(3).setCellRenderer(renderer);
	
	renderer1.setValue(excluir);
	renderer1.setHorizontalAlignment(JLabel.CENTER);
	columnModel.getColumn(5).setCellRenderer(renderer1);

    dtm.setRowCount(0);
	List<FuncionarioBean> listaFunc= new ArrayList<FuncionarioBean>();
		listaFunc = FuncionarioDao.consultarFuncionariosID(cod);
		String dados[] = new String[5]; 
	for (FuncionarioBean obj : listaFunc) {
		
//		dados[4] = obj.getSituacao();
//		String situacao = dados[4];
		
//		if(situacao.equals("Ativado")){	
//			System.out.println("");
		dados[0] = String.valueOf(obj.getId_func());
		dados[1] = obj.getNome_func();
		dados[2] = obj.getTelefone_func();
		dados[3] = obj.getCpf_func();
		dados[4] = obj.getSituacao();
		((DefaultTableModel) table.getModel()).addRow(dados);
//		}
	} 
	table.repaint();
}
}
