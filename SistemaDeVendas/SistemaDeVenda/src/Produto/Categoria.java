package Produto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;

import Beans.CategoriaBean;
import Conexao.exception;
import Dao.CategoriaDao;
import Produto.CadProduto.JTableRenderer;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Categoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblId;
	

	
	void alterar(){

		CategoriaBean objCategoria = new CategoriaBean();
		
		objCategoria.setNome_categoria(txtNome.getText());
		objCategoria.setId_categoria(Integer.parseInt(lblId.getText().toString()));
		CategoriaDao categoriaDao = new CategoriaDao();
		
		try {
			categoriaDao.atualizarCategoria(objCategoria);
			JOptionPane.showMessageDialog(null, "Alterado com Suscesso");
			atualizaLista(table, "");
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	void pegar(){
	CategoriaBean objCategoria = new CategoriaBean();
	objCategoria.setNome_categoria(txtNome.getText());
	
	CategoriaDao categoriaDao = new CategoriaDao();
	
	try {
		categoriaDao.inserirCategoria(objCategoria);
		JOptionPane.showMessageDialog(null, "Cadastrado com Suscesso");
		atualizaLista(table, "");
	} catch (exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
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

	public static void main(String[] args) {
		try {
			Categoria dialog = new Categoria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Categoria() {
		setBounds(100, 100, 233, 478);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 203, 230);
		contentPanel.add(scrollPane);
		
		
		String[][] dados = new String[][] {
			//	{"2521312", "Helvetica", "1111"},
			//	{"26123123", "Joao", "1111"}
		};
		String[] colunas = new String[] {
				"Identificação", "Categoria","Excluir"
			};

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			
				
				
				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();
				
				//textField.setText((String) table.getValueAt(linha, 0));
				
				String nome = (txtNome.getText());
				
				
				
				CategoriaDao objClie = new CategoriaDao();
				
				
				
				
				
				try {
					Dao.CategoriaDao.consultarCategoria(nome);
					
					List<CategoriaBean> listaProd = new ArrayList<CategoriaBean>();
					txtNome.setText((String) table.getValueAt(linha, 1));					
					String nome3 = txtNome.getText();
					listaProd = Dao.CategoriaDao.consultarCategoria(nome3);
					
			 		for (CategoriaBean obj : listaProd) {
						String id_categoria= String.valueOf(obj.getId_categoria());
						String nome2 = obj.getNome_categoria();
						
						
						
					txtNome.setText((String) table.getValueAt(linha, 1));
					lblId.setText(id_categoria);
					
					}
				} catch (exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
				
				String id_produto = (String) table.getValueAt(linha,0);
				int id_produto1=Integer.parseInt(id_produto);
				//Integer mat = Integer.parseInt(cpf); 
				if(coluna == 2){
					int opcao;
					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o registro de matricula: "+id_produto,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
					   if(opcao == JOptionPane.YES_OPTION){  
						   try {
							Dao.CategoriaDao.excluirCategoria(id_produto1);
							atualizaLista(table,"");
							
						} catch (exception e) {
				//					 TODO Auto-generated catch block
							e.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
					   }
				}

			
			
			}
		});
		DefaultTableModel model = new DefaultTableModel(dados,colunas);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Identificação", "Categoria","Excluir"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(389, 103, 343, 322);
//		contentPanel.add(table);
		 try {
				atualizaLista(table,"");
		} catch (exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scrollPane.setViewportView(table);
			
			lblNome = new JLabel("Nome");
			lblNome.setBounds(10, 98, 46, 14);
			contentPanel.add(lblNome);
			
			txtNome = new JTextField();
			txtNome.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					String nome = txtNome.getText();

					try {
						atualizaLista(table, nome);
					} catch (exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	
								
				}
			});
			txtNome.setBounds(69, 95, 144, 17);
			contentPanel.add(txtNome);
			txtNome.setColumns(10);
			
			JButton btnCadastrar = new JButton("Cadastrar");
			btnCadastrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				pegar();
				}
			});
			btnCadastrar.setBounds(10, 132, 101, 23);
			contentPanel.add(btnCadastrar);
			
			JButton btnAlterar = new JButton("Alterar");
			btnAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				alterar();
				}
			});
			btnAlterar.setBounds(136, 133, 77, 23);
			contentPanel.add(btnAlterar);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 64, 223, 2);
			contentPanel.add(separator);
			
			JLabel lblCategoria = new JLabel("Categoria");
			lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategoria.setBounds(0, 0, 223, 63);
			contentPanel.add(lblCategoria);
			
			lblId = new JLabel("");
			lblId.setBounds(167, 64, 46, 14);
			lblId.setVisible(false);
			
			contentPanel.add(lblId);
			
			JButton btnNewButton = new JButton("Voltar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
				try {
					CadProduto produto = new CadProduto();
					produto.atualizaComboCategoria("");
					setVisible(false);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				}
			});
			btnNewButton.setBounds(69, 406, 89, 23);
			contentPanel.add(btnNewButton);

		
	}
public void atualizaLista(JTable lista,String nome) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();
		
        ImageIcon editar = new ImageIcon(CadProduto.class.getResource("/Images/editar.gif"));  
        ImageIcon excluir = new ImageIcon(CadProduto.class.getResource("/Images/icon_excluir.png"));
		
        
//		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();		
		
//		renderer.setValue(editar);
//		renderer.setHorizontalAlignment(JLabel.CENTER);
//		columnModel.getColumn(3).setCellRenderer(renderer);
//		
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(2).setCellRenderer(renderer1);

        dtm.setRowCount(0);
		
        List<CategoriaBean> listaProd = new ArrayList<CategoriaBean>();
//        CategoriaDao forn = new CategoriaDao();
		listaProd = CategoriaDao.consultarCategoria(nome);
		String dados[] = new String[2]; 
//		CategoriaDao.consultarFornecedor(nome); 		
 		
		for (CategoriaBean obj : listaProd) {
			
			dados[0] = String.valueOf(obj.getId_categoria());
			dados[1] = obj.getNome_categoria();
			
			((DefaultTableModel) table.getModel()).addRow(dados);
			
		
		}
		table.repaint();
	
}
}
