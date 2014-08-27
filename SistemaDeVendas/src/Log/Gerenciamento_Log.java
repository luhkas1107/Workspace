package Log;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Beans.LogBean;
import Beans.ProdutoBean;
import Conexao.exception;
import Dao.FornecedorDao;
import Dao.LogDao;
import Produto.CadProduto;

public class Gerenciamento_Log extends JDialog {

	private final JPanel contentPanel = 
			new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Gerenciamento_Log dialog = new Gerenciamento_Log();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Gerenciamento_Log() {
		setBounds(100, 100, 785, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 88, 769, 2);
		contentPanel.add(separator);
		
		JLabel lblGerenciamentoDeLog = new JLabel("Gerenciamento de Log");
		lblGerenciamentoDeLog.setBounds(10, 11, 749, 45);
		lblGerenciamentoDeLog.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGerenciamentoDeLog.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblGerenciamentoDeLog);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 749, 332);
		contentPanel.add(scrollPane);

		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();  
		centro.setHorizontalAlignment(SwingConstants.CENTER);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Tela", "DataLog", "Mensagem"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(389);
		table.setRowHeight(60);
		table.getColumnModel().getColumn(3).setCellRenderer(centro);  
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		
		table.layout();
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("");
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setBounds(738, 55, 31, 28);
		contentPanel.add(label);
		label.setIcon(new ImageIcon(CadProduto.class.getResource("/Images/diagram_v2-13.png")));
		
		try {
			consultarLog(table, " ");
		} catch (exception | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	
	public void consultarLog(JTable lista,String tela1) throws exception, IOException{
		

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();
		
	  
		JTableRenderer renderer1 = new JTableRenderer();		
		
			    dtm.setRowCount(0);
		
	    List<LogBean> listaLog = new ArrayList<LogBean>();
	    LogDao log = new LogDao();
	    listaLog = Dao.LogDao.consultarLog(tela1);
	    
	    
	    String dados[] = new String[4]; 
		LogDao.consultarLog(tela1); 		
			
		for (LogBean obj : listaLog) {
			dados[0] = String.valueOf(obj.getId_log());
			System.out.println("dados[0]" +dados[0]);
			
			dados[1] = String.valueOf(obj.getTela());
			System.out.println("Dados[1]" +dados[1]);
			dados[2] = String.valueOf(obj.getData_log());
			System.out.println("Dados[2]" +dados[2]);
			dados[3] = obj.getMensagem();//getMensagem é a coluna com retorno de +-400 caracteres     
			System.out.println("Dados[3]"+ dados[3]);
			String parte1="";     
			String parte2="";  
			StringBuilder str = new StringBuilder();  
			if (dados[3].length()>120)  
			{  
			str.append("<html>");     
			parte1=dados[3].substring(0,120);  
			str.append(parte1);  
			str.append("<br>");    
			parte2=dados[3].substring(121,dados[3].length());  
			str.append(parte2);  
			str.append("</html>");   
			}else str.append(dados[3]);  
			    dados[3] = str.toString();    

//			((DefaultTableModel) table.getModel()).addRow(dados[0],str.toString());    
			((DefaultTableModel) table.getModel()).addRow(dados);
			}  
			
			
		
		table.repaint();
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
	

