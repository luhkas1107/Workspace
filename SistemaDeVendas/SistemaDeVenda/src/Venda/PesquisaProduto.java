package Venda;

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

import Beans.ProdutoBean;
import Conexao.exception;
import Dao.ProdutoDao;

public class PesquisaProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JLabel lblProduto;
	private JTextField txtPesquisaNome;
	public  int id_produto;
	int id_fornecedor ;
	public String nome_produto ;
	public int quantidade;
	public String caminho_imagem;
	public float preco;
	

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
			PesquisaProduto dialog = new PesquisaProduto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PesquisaProduto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);



		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 61, 440, 207);
		contentPanel.add(scrollPane);
		//scrollPane.add(table);
		
		String[][] dados = new String[][] {
			//	{"2521312", "Helvetica", "1111"},
			//	{"26123123", "Joao", "1111"}
		};
		String[] colunas = new String[] {
				"Identificação", "Nome", "Preço","Quantidade","Excluir"
			};
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			

				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();
				
				//textField.setText((String) table.getValueAt(linha, 0));
				
				String nome = (txtPesquisaNome.getText());
				
				
				
				ProdutoDao objProd = new ProdutoDao();
				
				
				
				
				
				try {
					objProd.consultarProduto(nome);
					
					List<ProdutoBean> listaProd = new ArrayList<ProdutoBean>();
//					txtNome.setText((String) table.getValueAt(linha, 1));					
					String nome3 = txtPesquisaNome.getText();
					listaProd= ProdutoDao.consultarProduto(nome3);
					
			 		for (ProdutoBean obj : listaProd) {
						id_produto= obj.getId_produto();
						id_fornecedor = obj.getId_fornecedor();
						nome_produto = obj.getNome();
						quantidade = obj.getQuantidade();
						preco = obj.getPreco();
						caminho_imagem = obj.getCaminho();
						
			 		}
				} catch (exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				NovaEntradaVenda venda = new NovaEntradaVenda();
				
				String id3=(String) table.getValueAt(linha, 0);
				venda.id_produto2 = Integer.parseInt(id3);
				venda.textCodProduto.setText(id3);
				venda.caminho_imagem=caminho_imagem;
				venda.mostrarId_produto(Integer.parseInt(id3));
				setVisible(false);
//				JOptionPane.showMessageDialog(null, "id3 " +id3);
				
				venda.codproduto(Integer.parseInt(id3));
				
				
				String id_produto0 = (String) table.getValueAt(linha,0);
				int id_produto1 = Integer.parseInt(id_produto0);
				//Integer mat = Integer.parseInt(cpf); 
				if(coluna == 4){
					int opcao;
					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o registro de matricula: "+id_produto0 ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
					   if(opcao == JOptionPane.YES_OPTION){  
						   try {
							ProdutoDao.excluirProduto(id_produto1);
							atualizaLista(table,"");
							
						} catch (exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
					   }
				}
				
				
			
			
			}
		});
		
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				DefaultTableModel model = new DefaultTableModel(dados,colunas);
				
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null},
					},
					new String[] {
						"ID_Produto", "Nome", "Pre\u00E7o", "Quantidade", "Categoria"
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false
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
				
				lblProduto = new JLabel("Produto");
				lblProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblProduto.setBounds(183, 4, 101, 14);
				contentPanel.add(lblProduto);
				
				txtPesquisaNome = new JTextField();
				txtPesquisaNome.setBounds(66, 30, 163, 20);
				contentPanel.add(txtPesquisaNome);
				txtPesquisaNome.setColumns(10);
				
				JSeparator separator = new JSeparator();
				separator.setBounds(0, 23, 440, 2);
				contentPanel.add(separator);
				
				JButton btnFilt = new JButton("Filtrar");
				btnFilt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {


						String nome = txtPesquisaNome.getText().toString();
						
						pesquisar(nome);
						
						
						
						
										}
				});
				btnFilt.setBounds(341, 27, 89, 23);
				contentPanel.add(btnFilt);
				
				JLabel lblNome = new JLabel("Nome");
				lblNome.setBounds(10, 36, 46, 14);
				contentPanel.add(lblNome);
				
				
	
}
	
	
public void atualizaLista(JTable lista,String nome) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		dtm.setRowCount(0);
		List<ProdutoBean> listaProd = new ArrayList<ProdutoBean>();
 		listaProd = ProdutoDao.consultarProduto(nome);
 		
 		
 		String dados[] = new String[5]; 
		for (ProdutoBean obj : listaProd) {
			dados[0] = String.valueOf(obj.getId_produto());
			dados[1] = obj.getNome();
			dados[2] = String.valueOf(obj.getPreco());
			dados[3] = String.valueOf(obj.getQuantidade());
			dados[4] = obj.getCategoria();
			
			((DefaultTableModel) table.getModel()).addRow(dados); 
		} 
		table.repaint();
	}
void pesquisaID(int id){
	DefaultTableModel dtm = (DefaultTableModel) table.getModel();

	dtm.setRowCount(0);
	List<ProdutoBean> listaProd = new ArrayList<ProdutoBean>();
		try {
			listaProd = ProdutoDao.consultarProdutoID(id);
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String dados[] = new String[4]; 
	for (ProdutoBean obj : listaProd) {
		dados[0] = String.valueOf(obj.getId_produto());
		dados[1] = obj.getNome();
		dados[2] = String.valueOf(obj.getPreco());
		dados[3] = String.valueOf(obj.getQuantidade());
		
		((DefaultTableModel) table.getModel()).addRow(dados); 
	} 
	table.repaint();
}

	}
	

	
	
	
