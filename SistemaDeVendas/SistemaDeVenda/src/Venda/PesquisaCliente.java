package Venda;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import Beans.ClienteBean;
import Conexao.exception;
import Dao.ClienteDao;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

public class PesquisaCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JLabel lblProduto;
	private JTextField txtPesquisaNome;
	public  int id_cliente;
	public String nome4;
	public String cpf ;
	public String telefone;
	public String endereco;
	public String num_residencia;
	public String complemento;
	public String bairro;
	public String cidade;
	public String uf;
	public String email;
	JComboBox comboBox = new JComboBox();
	

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

	
	void pesquisar (String nome){
		//String id = txtPesquisaId.getText().toString();
		NovaEntradaVenda venda = new NovaEntradaVenda();
		 
		try {
			atualizaLista(table,nome);
		
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
			PesquisaCliente dialog = new PesquisaCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PesquisaCliente() {
		setTitle("Pesquisar Cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PesquisaCliente.class.getResource("/Images/icone_cliente.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);



		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 115, 440, 153);
		contentPanel.add(scrollPane);
		//scrollPane.add(table);
		
		String[][] dados = new String[][] {
			//	{"2521312", "Helvetica", "1111"},
			//	{"26123123", "Joao", "1111"}
		};
		String[] colunas = new String[] {
				"Identificação", "Nome", "Telefone","CPF","UF"
			};
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();
				
				//textField.setText((String) table.getValueAt(linha, 0));
				
				String nome = (txtPesquisaNome.getText());
				
				
				
				ClienteDao objClie = new ClienteDao();
				
				
				
				
				
				try {
					ClienteDao.consultarCliente(nome);
					
					List<ClienteBean> listaClie = new ArrayList<ClienteBean>();
//					txtNome.setText((String) table.getValueAt(linha, 1));					
					String nome3 = txtPesquisaNome.getText();
					listaClie= ClienteDao.consultarCliente(nome3);
					
			 		for (ClienteBean obj : listaClie) {
			 			
			 			
						id_cliente= obj.getId_cliente();
						nome4 = obj.getNome();
						cpf = obj.getCpf();
						telefone = obj.getTelefone();
						endereco = obj.getEndereco();
						num_residencia = obj.getNum_residencia();
						complemento = obj.getComplemento();
						bairro = obj.getBairro();
						cidade = obj.getCidade();
						uf = obj.getUf();
						email = obj.getEmail();
						
			 		}
				} catch (exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				PesqCliente cliente = new PesqCliente();
				String id3=(String) table.getValueAt(linha, 0);
				cliente.textocliente = (String) table.getValueAt(linha, 0);
				cliente.setVisible(false);
				cliente.setVisible(true);
				setVisible(false);
				cliente.pegartexto();


				
				String id_produto0 = (String) table.getValueAt(linha,0);
				int id_produto1 = Integer.parseInt(id_produto0);
				//Integer mat = Integer.parseInt(cpf); 
//				if(coluna == 4){
//					int opcao;
//					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o registro de matricula: "+id_produto0 ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
//					   if(opcao == JOptionPane.YES_OPTION){  
//						   try {
//							ProdutoDao.excluirProduto(id_produto1);
//							atualizaLista(table,"");
//							
//						} catch (exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//							JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
//					   }
//				}
				

			
			
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = new DefaultTableModel(dados,colunas);
				
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Identificação", "Nome", "Telefone","CPF","UF"
					}
				));
		         table.setBounds(39, 175, 530, 232);
		         try {
					atualizaLista(table,"");
				} catch (exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scrollPane.setViewportView(table);
				
				lblProduto = new JLabel("Cliente");
				lblProduto.setIcon(new ImageIcon(PesquisaCliente.class.getResource("/Images/icone_cliente.png")));
				lblProduto.setHorizontalAlignment(SwingConstants.CENTER);
				lblProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
//				lblProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblProduto.setBounds(0, 4, 434, 57);
				contentPanel.add(lblProduto);
				
				txtPesquisaNome = new JTextField();
				txtPesquisaNome.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
					
						String opcao = comboBox.getSelectedItem().toString();
						String nome = txtPesquisaNome.getText().toString();
						if(opcao.equals("Nome")){
						
						try {
							atualizaLista(table,nome);
						
						} catch (exception e) {
						
							// TODO Auto-generated catch block
							
							e.printStackTrace();
						}
					}else{
						int id = Integer.parseInt(nome);
						try {
							atualizaListaCod(table, id);
						} catch (exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
				});
				txtPesquisaNome.setBounds(146, 84, 202, 20);
				contentPanel.add(txtPesquisaNome);
				txtPesquisaNome.setColumns(10);
				
				JSeparator separator = new JSeparator();
				separator.setBounds(0, 62, 440, 2);
				contentPanel.add(separator);
				
				comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nome", "C\u00F3digo"}));
				comboBox.setBounds(10, 84, 108, 20);
				contentPanel.add(comboBox);
				
				JLabel label = new JLabel("");
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
					

						String nome = txtPesquisaNome.getText().toString();
						
						pesquisar(nome);
					
					}
				});
				label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label.setIcon(new ImageIcon(PesquisaCliente.class.getResource("/Images/21-spotlight.png")));
				label.setBounds(378, 75, 46, 29);
				contentPanel.add(label);
				
				
	
}
	
	
public void atualizaLista(JTable lista,String nome) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

       	
		TableColumnModel columnModel = table.getColumnModel();
		
        
	
		dtm.setRowCount(0);
		List<ClienteBean> listaProd = new ArrayList<ClienteBean>();
 		listaProd = ClienteDao.consultarCliente(nome);
 		
 		
 		String dados[] = new String[5]; 
		for (ClienteBean obj : listaProd) {
			dados[0] = String.valueOf(obj.getId_cliente());
			dados[1] = obj.getNome();
			dados[2] = String.valueOf(obj.getCpf());
			dados[3] = String.valueOf(obj.getTelefone());
			dados[4] = String.valueOf(obj.getUf());
			
			((DefaultTableModel) table.getModel()).addRow(dados); 
		} 
		table.repaint();
	}

public void atualizaListaCod(JTable lista,int id) throws exception{
	
	DefaultTableModel dtm = (DefaultTableModel) table.getModel();

   	
	TableColumnModel columnModel = table.getColumnModel();
	
    

	dtm.setRowCount(0);
	List<ClienteBean> listaProd = new ArrayList<ClienteBean>();
		listaProd = ClienteDao.consultarClienteID2(id);
		
		
		String dados[] = new String[5]; 
	for (ClienteBean obj : listaProd) {
		dados[0] = String.valueOf(obj.getId_cliente());
		dados[1] = obj.getNome();
		dados[2] = String.valueOf(obj.getCpf());
		dados[3] = String.valueOf(obj.getTelefone());
		dados[4] = String.valueOf(obj.getUf());
		
		((DefaultTableModel) table.getModel()).addRow(dados); 
	} 
	table.repaint();
}

//void pesquisaID(int id){
//	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
//
//	dtm.setRowCount(0);
//	List<ClienteBean> listaProd = new ArrayList<ClienteBean>();
//		try {
//			listaProd = ClienteDao.consultarProdutoID(id);
//		} catch (exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		String dados[] = new String[4]; 
//	for (ProdutoBean obj : listaProd) {
//		dados[0] = String.valueOf(obj.getId_produto());
//		dados[1] = obj.getNome();
//		dados[2] = String.valueOf(obj.getPreco());
//		dados[3] = String.valueOf(obj.getQuantidade());
//		
//		((DefaultTableModel) table.getModel()).addRow(dados); 
//	} 
//	table.repaint();
//}

	}
