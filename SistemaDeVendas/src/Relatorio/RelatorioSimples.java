package Relatorio;

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
import Beans.FuncionarioBean;
import Beans.VendaBean;
import Cliente.CadCliente;
import Conexao.exception;
import Dao.VendaDao;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class RelatorioSimples extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dao.FornecedorDao FornecedorDao = new Dao.FornecedorDao();
	
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JTextField txtPesquisaNome;
	JComboBox itenPedidos = new JComboBox();
	JFormattedTextField textPesquisaId = new JFormattedTextField();
	JFormattedTextField txtPesquisaId2 = new JFormattedTextField();
	JLabel lblId_Venda = new JLabel("");
	JLabel lblValorTotal = new JLabel("");
	JLabel lblValorRecebido = new JLabel("");
	JLabel lblFormaPagamento = new JLabel("");
	JLabel lblNomeFunc = new JLabel("");
	JLabel lblNomeCliente = new JLabel("");
	JLabel lblHora = new JLabel("");
	String opcao;

	
	JLabel lblData = new JLabel("");
	public static float valor_total;
	public static String id_venda;
	
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
			RelatorioSimples dialog = new RelatorioSimples();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RelatorioSimples() {
		setTitle("Relat\u00F3rio de Vendas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RelatorioSimples.class.getResource("/Images/invoice2.png")));
		setBounds(100, 100, 752, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
		contentPanel.setLayout(null);
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 103, 342, 322);
		contentPanel.add(scrollPane);
		//scrollPane.add(table);
		
		String[][] dados = new String[][] {
			//	{"2521312", "Helvetica", "1111"},
			//	{"26123123", "Joao", "1111"}
		};
		String[] colunas = new String[] {
				"Id_venda", "Funcionário", "Cliente","Valor total","Valor pago","Forma_pagamento","Excluir"
			};
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				limpar();
				
				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();
				
				opcao = itenPedidos.getSelectedItem().toString();
				
				if (opcao.equals("Data (dd/MM/aaaa)")){
					
				String id = textPesquisaId.getText();
					 
				try {
						Dao.VendaDao.consultarVendaData(id);
						
						List<VendaBean> listaVenda = new ArrayList<VendaBean>();
						
						lblId_Venda.setText((String) table.getValueAt(linha, 0));
						String id2 = lblId_Venda.getText();
						listaVenda = Dao.VendaDao.consultarVenda(id2);
						
				 		for (VendaBean obj : listaVenda) {
							int id_venda= obj.getId_venda();
							int  id_cliente= obj.getId_cliente();
							int  id_func= obj.getId_func();
							float valortotal= obj.getValortotal();
							float valorpago = obj.getValorpago();
							String forma_pagamento = obj.getForma_pagamento();
							String data = obj.getData();
							String hora = obj.getHora();
							
					 			
						lblNomeFunc.setText((String) table.getValueAt(linha, 1));
						lblNomeCliente.setText((String) table.getValueAt(linha, 2));
						lblValorRecebido.setText((String) table.getValueAt(linha, 3));
						lblValorTotal.setText((String) table.getValueAt(linha, 4));
						lblFormaPagamento.setText(forma_pagamento);
						lblData.setText((String) table.getValueAt(linha, 5));
						lblHora.setText(hora);
						
						
//						FuncionarioBean listaFunc = new FuncionarioBean();
//				 		listaFunc = Dao.FuncionarioDao.consultarFuncionariosIDvenda(id_func);
				 		 		
				 		
//				 		String caminho = listaFunc.getCaminho();
//				 		String caminho_vazio = "/Images/semFoto.jpg";
						
						
				 		}
					} catch (exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					
				}else{
				
				
				String id = textPesquisaId.getText();
				
				try {
					Dao.VendaDao.consultarVenda(id);
					
					List<VendaBean> listaVenda = new ArrayList<VendaBean>();
					lblId_Venda.setText((String) table.getValueAt(linha, 0));
					String id2 = lblId_Venda.getText();
					listaVenda = Dao.VendaDao.consultarVenda(id2);
					
			 		for (VendaBean obj : listaVenda) {
						int id_venda= obj.getId_venda();
						int  id_cliente= obj.getId_cliente();
						int  id_func= obj.getId_func();
						float valortotal= obj.getValortotal();
						float valorpago = obj.getValorpago();
						String forma_pagamento = obj.getForma_pagamento();
						String data = obj.getData();
						String hora = obj.getHora();
						
				 			
					lblNomeFunc.setText((String) table.getValueAt(linha, 1));
					lblNomeCliente.setText((String) table.getValueAt(linha, 2));
					lblValorRecebido.setText((String) table.getValueAt(linha, 3));
					lblValorTotal.setText((String) table.getValueAt(linha, 4));
					lblFormaPagamento.setText(forma_pagamento);
					lblData.setText((String) table.getValueAt(linha, 5));
					lblHora.setText(hora);
					
					
//					FuncionarioBean listaFunc = new FuncionarioBean();
//			 		listaFunc = Dao.FuncionarioDao.consultarFuncionariosIDvenda(id_func);
//			 		 		
//			 		
//			 		String caminho = listaFunc.getCaminho();
//			 		String caminho_vazio = "/Images/semFoto.jpg";
					
					
			 		}
				} catch (exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				
				String id_venda1 = (String) table.getValueAt(linha,0);
				Integer id_venda = Integer.parseInt(id_venda1);
				if(coluna == 6){
					int opcao;
					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o registro de venda: "+id_venda ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
					   if(opcao == JOptionPane.YES_OPTION){  
						   try {
							VendaDao.excluirVenda(id_venda);
							atualizaLista(table,"");
							limpar();
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
					},
					new String[] {
						"Identifica\u00E7\u00E3o", "Funcion\u00E1rio", "Cliente", "Valor total", "Valor pago", "Data", "Excluir"
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false, true
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
				
//								contentPanel.add(table_1);
//				PanelDetalhe.add(table_1);
				
				JLabel lblIdVenda = new JLabel("C\u00F3digo");
				lblIdVenda.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblIdVenda.setBounds(10, 180, 61, 14);
				contentPanel.add(lblIdVenda);
				
				JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
				lblFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblFuncionrio.setBounds(10, 205, 70, 14);
				contentPanel.add(lblFuncionrio);
				
				JLabel lblCliente = new JLabel("Cliente");
				lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCliente.setBounds(10, 230, 46, 14);
				contentPanel.add(lblCliente);
				
				JLabel lblValorTotal2 = new JLabel("Valor Total");
				lblValorTotal2.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblValorTotal2.setBounds(10, 255, 70, 14);
				contentPanel.add(lblValorTotal2);
				
				JLabel lblValorRecebibo = new JLabel("Valor Recebibo");
				lblValorRecebibo.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblValorRecebibo.setBounds(10, 280, 96, 14);
				contentPanel.add(lblValorRecebibo);
				
				JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento");
				lblFormaDePagamento.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblFormaDePagamento.setBounds(10, 305, 137, 14);
				contentPanel.add(lblFormaDePagamento);
				lblId_Venda.setForeground(new Color(255, 0, 0));
				
				lblId_Venda.setBounds(148, 177, 204, 17);
				contentPanel.add(lblId_Venda);
				lblNomeFunc.setForeground(new Color(255, 0, 0));
				
				
				lblNomeFunc.setBounds(148, 202, 204, 17);
				contentPanel.add(lblNomeFunc);
				lblNomeCliente.setForeground(new Color(255, 0, 0));
				
				
				lblNomeCliente.setBounds(148, 227, 204, 17);
				contentPanel.add(lblNomeCliente);
				lblValorRecebido.setForeground(new Color(255, 0, 0));
				
				
				lblValorRecebido.setBounds(148, 255, 205, 17);
				contentPanel.add(lblValorRecebido);
				lblFormaPagamento.setForeground(new Color(255, 0, 0));
				
				
				lblFormaPagamento.setBounds(148, 302, 205, 17);
				contentPanel.add(lblFormaPagamento);
				lblValorTotal.setForeground(new Color(255, 0, 0));
				
				
				lblValorTotal.setBounds(148, 277, 205, 17);
				contentPanel.add(lblValorTotal);
				
				JLabel lblRelatrioSimples = new JLabel("Relat\u00F3rio Sint\u00E9tico");
				lblRelatrioSimples.setIcon(new ImageIcon(RelatorioSimples.class.getResource("/Images/line_chart.png")));
				lblRelatrioSimples.setHorizontalAlignment(SwingConstants.CENTER);
				lblRelatrioSimples.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblRelatrioSimples.setBounds(0, 11, 732, 62);
				contentPanel.add(lblRelatrioSimples);

				MaskFormatter data2 = new MaskFormatter();
				
				try {
					
					data2.setMask("##/##/####");

					
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				
				try {
					atualizaLista(table,"");
					
				} catch (exception e) {
					e.printStackTrace();
				}
				scrollPane.setViewportView(table);
				
				JLabel lblLabelData = new JLabel("Data");
				lblLabelData.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblLabelData.setBounds(10, 330, 46, 14);
				contentPanel.add(lblLabelData);
				
				JLabel lblLabelHora = new JLabel("Hora");
				lblLabelHora.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblLabelHora.setBounds(10, 355, 46, 14);
				contentPanel.add(lblLabelHora);
				lblData.setForeground(new Color(255, 0, 0));
				
				
				lblData.setBounds(148, 327, 96, 17);
				contentPanel.add(lblData);
				lblHora.setForeground(new Color(255, 0, 0));
				
				lblHora.setBounds(148, 352, 96, 17);
				contentPanel.add(lblHora);
				itenPedidos.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						String opcao = itenPedidos.getSelectedItem().toString();
						
						if(opcao.equals("Data (dd/MM/aaaa)")){
						textPesquisaId.setVisible(false);
						txtPesquisaId2.setVisible(true);		
						
						
					}else{
						textPesquisaId.setVisible(true);
						txtPesquisaId2.setVisible(false);
//						textPesquisaId.setText("Trokou pro 1");

						
					}
					
					
					}
				});
//				itenPedidos.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo venda", "C\u00F3digo funcion\u00E1rio", "C\u00F3digo cliente", "Data (dd/MM/aaaa)"}));
//				itenPedidos.setModel(new DefaultComboBoxModel(new String[] {"Codigo venda", "Codigo funcion\u00E1rio", "Codigo cliente", "Data (dd/MM/aaaa)"}));
				itenPedidos.setBounds(10, 110, 137, 20);
				contentPanel.add(itenPedidos);
				itenPedidos.addItem("Código venda");
				itenPedidos.addItem("Código funcionário");
				itenPedidos.addItem("Código cliente");
				itenPedidos.addItem("Data (dd/MM/aaaa)");
//				data();
				JButton btnVoltar = new JButton("Voltar");
				btnVoltar.setIcon(new ImageIcon(RelatorioSimples.class.getResource("/Images/back.png")));
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					}
				});
				btnVoltar.setBounds(10, 389, 96, 23);
				contentPanel.add(btnVoltar);
				
				JButton btnDetalhes = new JButton("Detalhes");
				btnDetalhes.setIcon(new ImageIcon(RelatorioSimples.class.getResource("/Images/editar.gif")));
				btnDetalhes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					id_venda = lblId_Venda.getText().toString();
					valor_total = Float.parseFloat(lblValorRecebido.getText());
					RelatorioEspecifico relatorioDetalhe = new RelatorioEspecifico();
					relatorioDetalhe.setVisible(true);
					
					}
				});
				btnDetalhes.setBounds(230, 389, 148, 23);
				contentPanel.add(btnDetalhes);
				textPesquisaId.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
				

						String id = textPesquisaId.getText().toString();
						String opcao3= itenPedidos.getSelectedItem().toString();
							
						
						if(opcao3.equals("Código venda")){
							
						
							try {
								atualizaLista(table,id);
							} catch (exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
						}	if(opcao3.equals("Código funcionário")){
							
						
							try {
								atualizaCodFunc(table,id);
							} catch (exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
						}if(opcao3.equals("Código cliente")){
							
						
							try {
								atualizaCodClie(table,id);
							} catch (exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
						}if(opcao3.equals("Data (dd/MM/aaaa)")){
							
						String id2 = txtPesquisaId2.getText().toString();
							try {
								atualizaData(table,id2);
							} catch (exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
						}
					
					}
				});
				
				textPesquisaId.setBounds(157, 111, 153, 17);
				contentPanel.add(textPesquisaId);
				


				
				txtPesquisaId2 = new JFormattedTextField(data2);
				txtPesquisaId2.setBounds(157, 111, 153, 17);
				txtPesquisaId2.setVisible(false);
				contentPanel.add(txtPesquisaId2);
				
				JLabel label = new JLabel("");
				label.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
					
						


					
					}
					@Override
					public void keyTyped(KeyEvent arg0) {
					

						String caractere = "1234567890";
						String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
						if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
							arg0.consume();
						}
					
					
					}
				});
				label.setIcon(new ImageIcon(RelatorioSimples.class.getResource("/Images/21-spotlight.png")));
				label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						

						String id = textPesquisaId.getText().toString();
						String opcao3= itenPedidos.getSelectedItem().toString();
							
						
						if(opcao3.equals("Código venda")){
							
						
							try {
								atualizaLista(table,id);
							} catch (exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
						}	if(opcao3.equals("Código funcionário")){
							
						
							try {
								atualizaCodFunc(table,id);
							} catch (exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
						}if(opcao3.equals("Código cliente")){
							
						
							try {
								atualizaCodClie(table,id);
							} catch (exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
						}if(opcao3.equals("Data (dd/MM/aaaa)")){
							
						String id2 = txtPesquisaId2.getText().toString();
							try {
								atualizaData(table,id2);
							} catch (exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
						}

					}
				});
				label.setBounds(320, 109, 35, 21);
				contentPanel.add(label);


			
				
		
	}
public void atualizaLista(JTable lista,String nome) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();
		
//        ImageIcon editar = new ImageIcon(CadCliente.class.getResource("/Images/editar.gif"));  
        ImageIcon excluir = new ImageIcon(CadCliente.class.getResource("/Images/icon_excluir.png"));
		
        
//		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();		
		
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		
		columnModel.getColumn(6).setCellRenderer(renderer1);


        dtm.setRowCount(0);
		List<VendaBean> listaRela = new ArrayList<VendaBean>();
 		listaRela = Dao.VendaDao.consultarVenda(nome);

		
 		
 		
 		String dados[] = new String[7];
 		
		for (VendaBean obj : listaRela)
		{
			dados[0] = String.valueOf(obj.getId_venda());
			dados[3] = String.valueOf(obj.getValortotal());
			dados[4] = String.valueOf(obj.getValorpago());
			dados[5] = obj.getData();
			
			
	 		
	 		String id_cliente = String.valueOf(obj.getId_cliente());
	 		ClienteBean listaClie = new ClienteBean();
	 		listaClie = Dao.ClienteDao.consultarClienteID(id_cliente);
//	 		ClienteBean clieBean = new ClienteBean();
			dados[2] = listaClie.getNome();
			
			int id_func = obj.getId_func();
			FuncionarioBean listaFunc = new FuncionarioBean();
			
	 		listaFunc = Dao.FuncionarioDao.consultarFuncionariosIDvenda(id_func);
	 	 		
	 		dados[1] = listaFunc.getNome_func();
//	 		dados[6] = listaFunc.get
			
	 		((DefaultTableModel) table.getModel()).addRow(dados);
	 		
	 		

	 		
	 		
	 		
		} 
		table.repaint();
	}
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
//void data(){
//	String data = itenPedidos.getSelectedItem().toString();
//	if (data.equals("Data (dd/MM/aaaa)")){
//		MaskFormatter data2 = new MaskFormatter();
//		
//								
//			{
//				try {
//					
//					data2.setMask("##/##/####");
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				textPesquisaId = new JFormattedTextField(data2);
//				
//								
//			}
//	}
//}


public void atualizaCodFunc(JTable lista,String id_func) throws exception{


		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();
		
//        ImageIcon editar = new ImageIcon(CadCliente.class.getResource("/Images/editar.gif"));  
        ImageIcon excluir = new ImageIcon(CadCliente.class.getResource("/Images/icon_excluir.png"));
		
        
//		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();		
		
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		
		columnModel.getColumn(6).setCellRenderer(renderer1);


        dtm.setRowCount(0);
		List<VendaBean> listaRela = new ArrayList<VendaBean>();
		
		if(id_func.equals("")){
			listaRela = Dao.VendaDao.consultarVendaNomeFuncionario(id_func);
	
		}else{
		int id_func2 = Integer.parseInt(id_func);
		listaRela = Dao.VendaDao.consultarVendaFuncionario(id_func2);
		}
		
 		
 		
 		String dados[] = new String[7];
 		
		for (VendaBean obj : listaRela)
		{
			dados[0] = String.valueOf(obj.getId_venda());
			dados[3] = String.valueOf(obj.getValortotal());
			dados[4] = String.valueOf(obj.getValorpago());
			dados[5] = obj.getData();
			
			
	 		
	 		String id_cliente = String.valueOf(obj.getId_cliente());
	 		ClienteBean listaClie = new ClienteBean();
	 		listaClie = Dao.ClienteDao.consultarClienteID(id_cliente);
//	 		ClienteBean clieBean = new ClienteBean();
			dados[2] = listaClie.getNome();
			
			int id_func1 = obj.getId_func();
			FuncionarioBean listaFunc = new FuncionarioBean();
			
	 		listaFunc = Dao.FuncionarioDao.consultarFuncionariosIDvenda(id_func1);
		
	 		dados[1] = listaFunc.getNome_func();
//	 		dados[6] = listaFunc.get
			
	 		((DefaultTableModel) table.getModel()).addRow(dados);
	 		
	 		

	 		
	 		
	 		
		} 
		table.repaint();
	}

public void atualizaCodClie(JTable lista,String id_cliente) throws exception{

	
	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
	TableColumnModel columnModel = table.getColumnModel();
	
//    ImageIcon editar = new ImageIcon(CadCliente.class.getResource("/Images/editar.gif"));  
    ImageIcon excluir = new ImageIcon(CadCliente.class.getResource("/Images/icon_excluir.png"));
	
    
//	JTableRenderer renderer = new JTableRenderer();
	JTableRenderer renderer1 = new JTableRenderer();		
	
	renderer1.setValue(excluir);
	renderer1.setHorizontalAlignment(JLabel.CENTER);
	
	columnModel.getColumn(6).setCellRenderer(renderer1);


    dtm.setRowCount(0);
	List<VendaBean> listaRela = new ArrayList<VendaBean>();
	
	if(id_cliente.equals("")){
		listaRela = Dao.VendaDao.consultarVendaNomeFuncionario(id_cliente);

	}else{
//	int id_cliente2 = Integer.parseInt(id_cliente);
	listaRela = Dao.VendaDao.consultarVendaCliente(id_cliente);
	}
	
		
		
		String dados[] = new String[7];
		
	for (VendaBean obj : listaRela)
	{
		dados[0] = String.valueOf(obj.getId_venda());
		dados[3] = String.valueOf(obj.getValortotal());
		dados[4] = String.valueOf(obj.getValorpago());
		dados[5] = obj.getData();
		
		
 		
 		String id_cliente1 = String.valueOf(obj.getId_cliente());
 		ClienteBean listaClie = new ClienteBean();
 		listaClie = Dao.ClienteDao.consultarClienteID(id_cliente1);
// 		ClienteBean clieBean = new ClienteBean();
		dados[2] = listaClie.getNome();
		
		int id_func1 = obj.getId_func();
		FuncionarioBean listaFunc = new FuncionarioBean();
		
 		listaFunc = Dao.FuncionarioDao.consultarFuncionariosIDvenda(id_func1);
	
 		dados[1] = listaFunc.getNome_func();
// 		dados[6] = listaFunc.get
		
 		((DefaultTableModel) table.getModel()).addRow(dados);
 		
 		

 		
 		
 		
	} 
	table.repaint();
}

public void atualizaData(JTable lista,String data) throws exception{


	
	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
	TableColumnModel columnModel = table.getColumnModel();
	
//    ImageIcon editar = new ImageIcon(CadCliente.class.getResource("/Images/editar.gif"));  
    ImageIcon excluir = new ImageIcon(CadCliente.class.getResource("/Images/icon_excluir.png"));
	
    
//	JTableRenderer renderer = new JTableRenderer();
	JTableRenderer renderer1 = new JTableRenderer();		
	
	renderer1.setValue(excluir);
	renderer1.setHorizontalAlignment(JLabel.CENTER);
	
	columnModel.getColumn(6).setCellRenderer(renderer1);


    dtm.setRowCount(0);
	List<VendaBean> listaRela = new ArrayList<VendaBean>();
	
	if(data.equals("")){
		listaRela = Dao.VendaDao.consultarVendaNomeFuncionario(data);

	}else{
	listaRela = Dao.VendaDao.consultarVendaData(data);
	}
	
		
		
		String dados[] = new String[7];
		
	for (VendaBean obj : listaRela)
	{
		dados[0] = String.valueOf(obj.getId_venda());
		dados[3] = String.valueOf(obj.getValortotal());
		dados[4] = String.valueOf(obj.getValorpago());
		dados[5] = obj.getData();
		
		
 		
 		String id_cliente = String.valueOf(obj.getId_cliente());
 		ClienteBean listaClie = new ClienteBean();
 		listaClie = Dao.ClienteDao.consultarClienteID(id_cliente);
// 		ClienteBean clieBean = new ClienteBean();
		dados[2] = listaClie.getNome();
		
		int id_func1 = obj.getId_func();
		FuncionarioBean listaFunc = new FuncionarioBean();
		
 		listaFunc = Dao.FuncionarioDao.consultarFuncionariosIDvenda(id_func1);
	
 		dados[1] = listaFunc.getNome_func();
// 		dados[6] = listaFunc.get
		
 		((DefaultTableModel) table.getModel()).addRow(dados);
 		
 		

 		
 		
 		
	} 
	table.repaint();
}

void limpar(){
	lblNomeFunc.setText("");
	lblNomeCliente.setText("");
	lblValorRecebido.setText("");
	lblValorTotal.setText("");
	lblFormaPagamento.setText("");
//	lblImagem.setIcon(null);
	

}
}
