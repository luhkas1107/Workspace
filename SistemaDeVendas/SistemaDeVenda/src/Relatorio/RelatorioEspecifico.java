package Relatorio;

import java.awt.BorderLayout;
import java.text.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
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

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import Beans.PedidoBean;
import Cliente.CadCliente;
import Conexao.exception;
import Dao.PedidoDao;
import javax.swing.SwingConstants;

public class RelatorioEspecifico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dao.FornecedorDao FornecedorDao = new Dao.FornecedorDao();
	
	JLabel lblEstoque = new JLabel("");
	JLabel lblQuantidadeComprado = new JLabel("");
	JLabel lblValorUnitario = new JLabel("");
	JLabel lblValorTotalProduto = new JLabel("");
	
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JTextField txtPesquisaNome;
	JLabel lblId_Venda = new JLabel(RelatorioSimples.id_venda);
	JLabel lblNomeProduto = new JLabel("");
	String opcao;
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
			RelatorioEspecifico dialog = new RelatorioEspecifico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RelatorioEspecifico() {
		setTitle("Relat\u00F3rio Detalhado");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RelatorioEspecifico.class.getResource("/Images/invoice2.png")));
		setBounds(100, 100, 752, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 71, 742, 2);
		contentPanel.add(separator);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

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
		separator_6.setBounds(0, 438, 742, 2);
		contentPanel.add(separator_6);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 103, 342, 337);
		contentPanel.add(scrollPane);
		//scrollPane.add(table);
		
		String[][] dados = new String[][] {
			//	{"2521312", "Helvetica", "1111"},
			//	{"26123123", "Joao", "1111"}
		};
		String[] colunas = new String[] {
				"Número Produto","Número Venda", "Produto", "Quantidade Comprada", "Valor Unitário", "Valor Final"
			};
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				limpar();
				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();
				
//				opcao = itenPedidos.getSelectedItem().toString();
				
					
//				String id = textPesquisaId.getText();
					 
				try {
//						Dao.VendaDao.consultarVendaData(id);
						RelatorioSimples relatorio = new RelatorioSimples();
						List<PedidoBean> listaVenda = new ArrayList<PedidoBean>();
						lblId_Venda.setText(relatorio.id_venda);
						
//						JOptionPane.showMessageDialog(null,lblId_Venda.getText());
						
						int id2 = Integer.parseInt(lblId_Venda.getText());
						listaVenda = Dao.PedidoDao.consultarPedido(id2);
						
				 		for (PedidoBean obj : listaVenda) {
				 			DecimalFormat df = new DecimalFormat();
				 			df.applyPattern("#####.00");
							int id_pedido = obj.getId_pedido();
							int  num_pedido = obj.getId_cliente();
							int  id_produto = obj.getId_produto();
							int id_cliente= obj.getId_cliente();
							int id_func = obj.getId_func();
							int qtd_produto = obj.getQtd_produto();
							float preco_unitario = obj.getPreco_produto_unitario();
							float preco_total = obj.getPreco_produto_total();
							String forma_pagamento = obj.getForma_pagamento();
							String nome_produto = obj.getNome_produto();
					 		String caminho_imagem = obj.getCaminho_imagem();
					 		int quantidade_estoque = obj.getQuantidade_estoque();
					 		
						lblId_Venda.setText((String) table.getValueAt(linha, 1));
						lblNomeProduto.setText(nome_produto);
						lblEstoque.setText(String.valueOf(quantidade_estoque));
						lblQuantidadeComprado.setText((String) table.getValueAt(linha, 3));
						lblValorUnitario.setText((String) table.getValueAt(linha, 4));
						lblValorTotalProduto.setText((String) table.getValueAt(linha, 5));
						
						
						
						String caminho_vazio = "/Images/semFoto.jpg";
					
						
				 		}
					} catch (exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					
				
				
//				
//				String id = textPesquisaId.getText();
//				try {
//					Dao.VendaDao.consultarVenda(id);
//					
//					List<VendaBean> listaVenda = new ArrayList<VendaBean>();
//					lblId_Venda.setText((String) table.getValueAt(linha, 0));
//					String id2 = lblId_Venda.getText();
//					listaVenda = Dao.VendaDao.consultarVenda(id2);
//					
//			 		for (VendaBean obj : listaVenda) {
//						int id_venda= obj.getId_venda();
//						int  id_cliente= obj.getId_cliente();
//						int  id_func= obj.getId_func();
//						float valortotal= obj.getValortotal();
//						float valorpago = obj.getValorpago();
//						String forma_pagamento = obj.getForma_pagamento();
//						String data = obj.getData();
//						String hora = obj.getHora();
//						
//				 			
//					lblNomeProduto.setText((String) table.getValueAt(linha, 1));
//					lblEstoque.setText();
//					lblValorRecebido.setText((String) table.getValueAt(linha, 3));
//					lblValorTotal.setText((String) table.getValueAt(linha, 4));
//					lblFormaPagamento.setText(forma_pagamento);
//					lblData.setText((String) table.getValueAt(linha, 5));
//					lblHora.setText(hora);
//					
//					
//					FuncionarioBean listaFunc = new FuncionarioBean();
//			 		listaFunc = Dao.FuncionarioDao.consultarFuncionariosIDvenda(id_func);
//			 		 		
//			 		
//			 		String caminho = listaFunc.getCaminho();
//			 		String caminho_vazio = "/Images/semFoto.jpg";
//					
//					
//			 		}
//				} catch (exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
				
				
				String id_venda1 = (String) table.getValueAt(linha,0);
				Integer id_venda = Integer.parseInt(id_venda1);
//				if(coluna == 6){
//					int opcao;
//					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o registro do produto : "+id_venda ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
//					   if(opcao == JOptionPane.YES_OPTION){  
//						   try {
//							PedidoDao.excluirPedido(id_venda);
//							atualizaLista(table);
//							limpar();
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
						"Número Produto","Número Venda", "Produto", "Quantidade Comprada", "Valor Unitário", "Valor Final"
					}
				) {
					/**
					 * 
					 */
		
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false//, true
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		         table.setBounds(39, 175, 530, 232);
		         try {
					atualizaLista(table);
				} catch (exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scrollPane.setViewportView(table);
				
				JLabel lblIdVenda = new JLabel("N\u00FAmero da Venda");
				lblIdVenda.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblIdVenda.setBounds(10, 113, 116, 14);
				contentPanel.add(lblIdVenda);
				
				JLabel lblFuncionrio = new JLabel("Nome do Produto");
				lblFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblFuncionrio.setBounds(10, 165, 128, 14);
				contentPanel.add(lblFuncionrio);
				
				JLabel lblCliente = new JLabel("Em Estoque");
				lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCliente.setBounds(10, 190, 116, 14);
				contentPanel.add(lblCliente);
				
				JLabel lblValorTotal2 = new JLabel("Comprado");
				lblValorTotal2.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblValorTotal2.setBounds(10, 215, 70, 14);
				contentPanel.add(lblValorTotal2);
				
				JLabel lblValorRecebibo = new JLabel("Valor Unit\u00E1rio");
				lblValorRecebibo.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblValorRecebibo.setBounds(10, 240, 96, 14);
				contentPanel.add(lblValorRecebibo);
				
				JLabel lblFormaDePagamento = new JLabel("Valor Total");
				lblFormaDePagamento.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblFormaDePagamento.setBounds(10, 265, 137, 14);
				contentPanel.add(lblFormaDePagamento);
				lblId_Venda.setForeground(new Color(255, 0, 0));
				
				lblId_Venda.setBounds(148, 110, 204, 17);
				contentPanel.add(lblId_Venda);
				lblNomeProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				lblNomeProduto.setForeground(new Color(255, 0, 0));
				
				
				lblNomeProduto.setBounds(148, 162, 204, 17);
				contentPanel.add(lblNomeProduto);
				
				JLabel lblRelatrioSimples = new JLabel("Relat\u00F3rio Anal\u00EDtico");
				lblRelatrioSimples.setIcon(new ImageIcon(RelatorioEspecifico.class.getResource("/Images/bar_chart.png")));
				lblRelatrioSimples.setHorizontalAlignment(SwingConstants.CENTER);
				lblRelatrioSimples.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblRelatrioSimples.setBounds(0, 11, 732, 62);
				contentPanel.add(lblRelatrioSimples);

				try {
					atualizaLista(table);
					
				} catch (exception e) {
					e.printStackTrace();
				}
				scrollPane.setViewportView(table);
	
				
				lblEstoque.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				lblEstoque.setForeground(Color.RED);
				lblEstoque.setBounds(148, 187, 204, 17);
				contentPanel.add(lblEstoque);
				
				
				
				lblQuantidadeComprado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				lblQuantidadeComprado.setForeground(Color.RED);
				lblQuantidadeComprado.setBounds(148, 212, 204, 17);
				contentPanel.add(lblQuantidadeComprado);
				
	
				lblValorUnitario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				lblValorUnitario.setForeground(Color.RED);
				lblValorUnitario.setBounds(148, 237, 204, 17);
				contentPanel.add(lblValorUnitario);
				
				
				lblValorTotalProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				lblValorTotalProduto.setForeground(Color.RED);
				lblValorTotalProduto.setBounds(148, 262, 204, 17);
				contentPanel.add(lblValorTotalProduto);
				
				JLabel lbl231 = new JLabel("Valor da Compra");
				lbl231.setFont(new Font("Tahoma", Font.BOLD, 16));
				lbl231.setBounds(128, 290, 154, 26);
				contentPanel.add(lbl231);
				
				float valor_total = RelatorioSimples.valor_total;
				DecimalFormat df = new DecimalFormat();
				df.applyPattern("######.00");
				String valor_total2 = df.format(valor_total);
				JLabel lblValorDaCompra = new JLabel("R$ " +valor_total2);
				lblValorDaCompra.setForeground(Color.RED);
				lblValorDaCompra.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
				lblValorDaCompra.setBounds(138, 318, 118, 46);
				contentPanel.add(lblValorDaCompra);
				
				JButton btnImprimirRelatorioCompleto = new JButton("Imprimir Relat\u00F3rio");
				btnImprimirRelatorioCompleto.setIcon(new ImageIcon(RelatorioEspecifico.class.getResource("/Images/Print.png")));
				btnImprimirRelatorioCompleto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					


						 
						try{  
							@SuppressWarnings("rawtypes") 
			                String id = lblId_Venda.getText();
			                List<PedidoBean> objPedido = new ArrayList<PedidoBean>();
			                objPedido = Dao.RelatorioDao.gerarRelatorioCompleto(id);
			                
			                JRDataSource jrDataSource = new JRBeanCollectionDataSource(objPedido);
			                HashMap map = new HashMap();  
			                String FileJasper = "Relatorios/RelatorioCompleto02.jasper";  
			                JasperPrint jpReport = JasperFillManager.fillReport(FileJasper, map, jrDataSource);  
			                JasperViewer.viewReport(jpReport, false);
			                
			            }catch(Exception errorOpenFile) {  
			            	
			            	JOptionPane.showMessageDialog(null,errorOpenFile.getMessage());          	
			            	
			                JOptionPane.showMessageDialog(null,"Não foi possível exibir o relatório!"  
			                        + '\n' + "Verifique se o arquivo .jasper"  
			                        + '\n' + "encontra-se no diretório:"  
			                        + '\n' + "<drive> : C\\Users\\Luhkas\\Documents\\Relatorio_Java\\Relatorios_InfoTec\\RelatorioCompleto02.jasper!","JERP - Atenção!",JOptionPane.ERROR_MESSAGE);  
			            }
					
					
					
					
				
					}
				});
				btnImprimirRelatorioCompleto.setBounds(185, 407, 194, 26);
				contentPanel.add(btnImprimirRelatorioCompleto);
				
				JButton btnVoltar = new JButton("Voltar");
				btnVoltar.setIcon(new ImageIcon(RelatorioEspecifico.class.getResource("/Images/back.png")));
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					setVisible(false);
					
					}
				});
				btnVoltar.setBounds(17, 410, 96, 23);
				contentPanel.add(btnVoltar);
				
		
	}
public void atualizaLista(JTable lista) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();
		
//        ImageIcon editar = new ImageIcon(CadCliente.class.getResource("/Images/editar.gif"));  
//        ImageIcon excluir = new ImageIcon(CadCliente.class.getResource("/Images/icon_excluir.png"));
		
        
//		JTableRenderer renderer = new JTableRenderer();
//		JTableRenderer renderer1 = new JTableRenderer();		
		
//		renderer1.setValue(excluir);
//		renderer1.setHorizontalAlignment(JLabel.CENTER);
//		columnModel.getColumn(6).setCellRenderer(renderer1);


        dtm.setRowCount(0);
		List<PedidoBean> listaRela = new ArrayList<PedidoBean>();
		
		String id1 = lblId_Venda.getText();
//		JOptionPane.showMessageDialog(null, "ID> " +id1);
		int id3 = Integer.parseInt(id1);
 		listaRela = Dao.PedidoDao.consultarPedido(id3);

		
 		
 		
 		String dados[] = new String[8];
 		
		for (PedidoBean obj : listaRela)
		{
			dados[0] = String.valueOf(obj.getId_pedido());
			dados[1] = String.valueOf(obj.getNum_pedido());
			dados[2] = String.valueOf(obj.getNome_produto());
			dados[3] = String.valueOf(obj.getQtd_produto());
			dados[4] = String.valueOf(obj.getPreco_produto_unitario());
			dados[5] = String.valueOf(obj.getPreco_produto_total());
	 		
			
	 		((DefaultTableModel) table.getModel()).addRow(dados);
	 		
	 		

	 		
	 		
	 		
		} 
		table.repaint();
	}

//
//void atualizar_id_func(String id_func){
//
//	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
//	dtm.setRowCount(0);
//	
//	List<VendaBean> listaRela = new ArrayList<VendaBean>();
//	try {
//		listaRela = Dao.VendaDao.consultarVendaFuncionario(id_func);
//	} catch (exception e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	String dados[] = new String[7];
//	
//for (VendaBean obj : listaRela)
//{
//	dados[0] = String.valueOf(obj.getId_venda());
//	dados[3] = String.valueOf(obj.getValortotal());
//	dados[4] = String.valueOf(obj.getValorpago());
//	dados[5] = obj.getData();
//	
//	
//		
//		String id_cliente2 = String.valueOf(obj.getId_cliente());
//		ClienteBean listaClie = new ClienteBean();
//		try {
//		listaClie = Dao.ClienteDao.consultarClienteID(id_cliente2);
//	} catch (exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	dados[2] = listaClie.getNome();
//	int id_func1 = obj.getId_func();
//	FuncionarioBean listaFunc = new FuncionarioBean();
//		try {
//		listaFunc = Dao.FuncionarioDao.consultarFuncionariosIDvenda(id_func1);
//	} catch (exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	 		
//		dados[1] = listaFunc.getNome_func();
//
//	
//		((DefaultTableModel) table.getModel()).addRow(dados);
//		
//		
//} 
//table.repaint();
//	
//}
//void atualizar_data(String data){
//	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
//	dtm.setRowCount(0);
//	
//	List<VendaBean> listaRela = new ArrayList<VendaBean>();
//	try {
//		listaRela = Dao.VendaDao.consultarVendaData(data);
//	} catch (exception e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	String dados[] = new String[7];
//	
//for (VendaBean obj : listaRela)
//{
//	dados[0] = String.valueOf(obj.getId_venda());
//	dados[3] = String.valueOf(obj.getValortotal());
//	dados[4] = String.valueOf(obj.getValorpago());
//	dados[5] = obj.getData();
//	
//	
//		
//		String id_cliente2 = String.valueOf(obj.getId_cliente());
//		ClienteBean listaClie = new ClienteBean();
//		try {
//		listaClie = Dao.ClienteDao.consultarClienteID(id_cliente2);
//	} catch (exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	dados[2] = listaClie.getNome();
//	int id_func1 = obj.getId_func();
//	FuncionarioBean listaFunc = new FuncionarioBean();
//		try {
//		listaFunc = Dao.FuncionarioDao.consultarFuncionariosIDvenda(id_func1);
//	} catch (exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	 		
//		dados[1] = listaFunc.getNome_func();
//
//	
//		((DefaultTableModel) table.getModel()).addRow(dados);
//		
//		
//} 
//table.repaint();
//
//}
//
//void atualizar_id_cliente(String id_cliente){
//
//	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
//	dtm.setRowCount(0);
//	
//	List<VendaBean> listaRela = new ArrayList<VendaBean>();
//	try {
//		listaRela = Dao.VendaDao.consultarVendaCliente(id_cliente);
//	} catch (exception e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	String dados[] = new String[7];
//	
//for (VendaBean obj : listaRela)
//{
//	dados[0] = String.valueOf(obj.getId_venda());
//	dados[3] = String.valueOf(obj.getValortotal());
//	dados[4] = String.valueOf(obj.getValorpago());
//	dados[5] = obj.getData();
//	
//	
//		
//		String id_cliente2 = String.valueOf(obj.getId_cliente());
//		ClienteBean listaClie = new ClienteBean();
//		try {
//		listaClie = Dao.ClienteDao.consultarClienteID(id_cliente2);
//	} catch (exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	dados[2] = listaClie.getNome();
//	int id_func1 = obj.getId_func();
//	FuncionarioBean listaFunc = new FuncionarioBean();
//		try {
//		listaFunc = Dao.FuncionarioDao.consultarFuncionariosIDvenda(id_func1);
//	} catch (exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	 		
//		dados[1] = listaFunc.getNome_func();
//
//	
//		((DefaultTableModel) table.getModel()).addRow(dados);
//		
//		
//} 
//table.repaint();
//
//}
void limpar(){
	lblNomeProduto.setText("");
	lblEstoque.setText("");
	lblQuantidadeComprado.setText("");
	lblValorUnitario.setText("");
	lblValorTotalProduto.setText("");

	

}
public void gerar() throws exception, JRException {  
    JasperPrint rel = null; 
    String id = lblId_Venda.getText().toString();
    List<PedidoBean> objPedido = new ArrayList<PedidoBean>();
    try {
		objPedido = Dao.RelatorioDao.gerarRelatorioCompleto(id);
		
		
		  JRDataSource jrDataSource = new JRBeanCollectionDataSource(objPedido);
          HashMap map = new HashMap();  
          String FileJasper = "C:/Users/Luhkas/Documents/Relatorio_Java/Relatorios_InfoTec/RelatorioPedido.jasper";  
          JasperPrint jpReport = JasperFillManager.fillReport(FileJasper, map, jrDataSource);  
          JasperViewer.viewReport(jpReport, false);  
         } catch (exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();

        JOptionPane.showMessageDialog(null,e1.getMessage());
	}
      
}
}