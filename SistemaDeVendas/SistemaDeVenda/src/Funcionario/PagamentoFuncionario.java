package Funcionario;

import java.awt.BorderLayout;
import java.io.IOException;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import Log.Log; //CLASSE QUE IRÁ GERAR O LOG


import java.text.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import Beans.FuncionarioBean;
import Conexao.exception;
import Dao.FuncionarioDao;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
public class PagamentoFuncionario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField formattedDataInicio = new JFormattedTextField();
	private JFormattedTextField formattedTerminoContrato = new JFormattedTextField();
	private JFormattedTextField formattedTextField = new JFormattedTextField();
	private JFormattedTextField formattedTelefone = new JFormattedTextField();
	private Dao.FuncionarioDao funcDao = new Dao.FuncionarioDao();
	JLabel lblImage;
//	Date dataHoje;
//	SimpleDateFormat formataData;	
	File arquivo, dir; 
	String caminho, caminhoImagem;
	String nomeArquivo = "";
	String extensaoArquivo ="";
	public float comissao;
	ButtonGroup grupo = new ButtonGroup();
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	
	private JTextField textNomePesquisa;
	JLabel lblTermino = new JLabel("");
	JLabel lblFuncao = new JLabel("");
	JLabel lblInicio = new JLabel("");
	JLabel lblNome1 = new JLabel("");
	JLabel lblId = new JLabel("");
	JLabel lblCtps = new JLabel("");
	JLabel lblSalario = new JLabel("");
	private JTextField textNomePesquisa2;
	JComboBox comboPesquisa = new JComboBox();
	
	static Date dataHoje = new Date();
	static SimpleDateFormat formataData = new SimpleDateFormat(
		"dd/MM/yyyy HH:mm:ss:SSSS");
	static SimpleDateFormat formataDataArq = new SimpleDateFormat("ddMMyyyyHH");
	static String agora = formataData.format(dataHoje);
	static String nomeArq = formataDataArq.format(dataHoje);
	static Logger logger = Logger.getLogger(PagamentoFuncionario.class);  // Classe que ta sendo feito o LOG
	public static String tela = "Pagamento_Funcionario"; // Nome da Tela

	
	
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
			PagamentoFuncionario dialog = new PagamentoFuncionario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PagamentoFuncionario() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PagamentoFuncionario.class.getResource("/Images/cash_register.png")));
		setTitle("Pagamento Funcion\u00E1rio");
		setBounds(100, 100, 752, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

			JLabel lblNome = new JLabel("Nome");
			lblNome.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNome.setBounds(10, 262, 102, 17);
			contentPanel.add(lblNome);
		
			JLabel lblFuno = new JLabel("Fun\u00E7\u00E3o");
			lblFuno.setFont(new Font("Dialog", Font.BOLD, 12));
			lblFuno.setBounds(10, 340, 46, 14);
			contentPanel.add(lblFuno);
		
			JLabel lblIniciocontra = new JLabel("In\u00EDcio Contrato");
			lblIniciocontra.setFont(new Font("Dialog", Font.BOLD, 12));
			lblIniciocontra.setBounds(10, 367, 102, 14);
			contentPanel.add(lblIniciocontra);
	
			JLabel lblTerminocontrato = new JLabel("T\u00E9rmino Contrato");
			lblTerminocontrato.setFont(new Font("Dialog", Font.BOLD, 12));
			lblTerminocontrato.setBounds(10, 394, 120, 14);
			contentPanel.add(lblTerminocontrato);
		
			JLabel lblSalarior = new JLabel("Sal\u00E1rio(R$)");
			lblSalarior.setFont(new Font("Dialog", Font.BOLD, 12));
			lblSalarior.setBounds(10, 290, 87, 14);
			contentPanel.add(lblSalarior);
			
			JLabel lblCpts = new JLabel("CTPS");
			lblCpts.setFont(new Font("Dialog", Font.BOLD, 12));
			lblCpts.setBounds(10, 315, 46, 14);
			contentPanel.add(lblCpts);
		
		JButton btnCadastrar = new JButton("Gerar Folha");
		btnCadastrar.setIcon(new ImageIcon(PagamentoFuncionario.class.getResource("/Images/Print.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {   //BOTAO CADASTRAR!
			    
				try{
					int id = 0;
		            
	                String id2 = (lblId.getText().toString());
	                if(id2.equals("")){
	                	
	                }else{
	                	id = Integer.parseInt(id2);
		                
	                }
	                
	                List<FuncionarioBean> objFunc = new ArrayList<FuncionarioBean>();
	                objFunc = Dao.RelatorioDao.gerarPagamento(id);
	                
	                JRDataSource jrDataSource = new JRBeanCollectionDataSource(objFunc);
	                HashMap<String, Object> map = new HashMap<String, Object>();  
//	                String FileJasper = "C:\\Users\\Luhkas\\Documents\\Relatorio_Java\\Relatorios_InfoTec\\FolhaPagamentoFunc.jasper";  

	                String FileJasper = "Relatorios\\FolhaPagamentoFunc.jasper";
	                JasperPrint jpReport = JasperFillManager.fillReport(FileJasper, map, jrDataSource);  
	                JasperViewer.viewReport(jpReport, false);
	                
	            }catch(Exception errorOpenFile) {  
	                JOptionPane.showMessageDialog(null,"Não foi possível exibir o relatório!"  
	                        + '\n' + "Verifique se o arquivo .jasper"  
	                        + '\n' + "encontra-se no diretório:"  
	                        + '\n' + "<drive> : C:\\Users\\Luhkas\\Documents\\Relatorio_Java\\Relatorios_InfoTec\\FolhaPagamentoFunc.jasper!","JERP - Atenção!",JOptionPane.ERROR_MESSAGE);  
	            } 			
			
			}
		});
		btnCadastrar.setBounds(233, 437, 129, 23);
		contentPanel.add(btnCadastrar);
		Panel panel = new Panel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(135, 125, 87, 87);
		contentPanel.add(panel);
		panel.setLayout(null);
		
			lblImage = new JLabel("");
			lblImage.setBounds(0, 0, 87, 87);
			panel.add(lblImage);
		
		
		
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 64, 742, 2);
			contentPanel.add(separator);
		
		
		JSeparator separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setBounds(372, 105, 2, 371);
		contentPanel.add(separator1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 105, 755, 2);
		contentPanel.add(separator_1);
		
		JLabel lblIdfuncionario = new JLabel("Identifica\u00E7\u00E3o");
		lblIdfuncionario.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIdfuncionario.setBounds(10, 234, 87, 17);
		contentPanel.add(lblIdfuncionario);
		
		JLabel lblFuncionrio = new JLabel("Folha de Pagamento");
		lblFuncionrio.setIcon(new ImageIcon(PagamentoFuncionario.class.getResource("/Images/cash_register.png")));
		lblFuncionrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFuncionrio.setBounds(0, 0, 742, 66);
		contentPanel.add(lblFuncionrio);

			
			lblNome1.setBackground(Color.GRAY);
			lblNome1.setBounds(142, 260, 220, 17);
			contentPanel.add(lblNome1);
		
			
			lblId.setBackground(Color.DARK_GRAY);
			lblId.setBounds(142, 232, 163, 17);
			contentPanel.add(lblId);
		
			
			lblCtps.setBackground(Color.GRAY);
			lblCtps.setBounds(142, 312, 163, 17);
			contentPanel.add(lblCtps);
		
			
			lblSalario.setBackground(Color.GRAY);
			lblSalario.setBounds(142, 287, 178, 17);
			contentPanel.add(lblSalario);
		
			
			lblFuncao.setBackground(Color.GRAY);
			lblFuncao.setBounds(142, 337, 163, 17);
			contentPanel.add(lblFuncao);
		
			
			lblInicio.setBackground(Color.GRAY);
			lblInicio.setBounds(142, 364, 163, 17);
			contentPanel.add(lblInicio);
		
			
			lblTermino.setBackground(Color.GRAY);
			lblTermino.setBounds(142, 391, 163, 17);
			contentPanel.add(lblTermino);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(372, 105, 370, 371);
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
				public void mouseClicked(MouseEvent arg0) {

					int linha = table.getSelectedRow();
					int coluna = table.getSelectedColumn();
					
					//textField.setText((String) table.getValueAt(linha, 0));
					
					String nome = (textNomePesquisa2.getText());
					
					
					
					FuncionarioDao objClie = new FuncionarioDao();
					
					
					
					
					
					try {
						FuncionarioDao.consultarFuncionarios(nome);
						
						List<FuncionarioBean> listaFunc = new ArrayList<FuncionarioBean>();
//						textNomePesquisa2.setText((String) table.getValueAt(linha, 0));
						
						String nome3 = (String) table.getValueAt(linha, 0);
						listaFunc = FuncionarioDao.consultarFuncionariosID(Integer.parseInt(nome3));
						
				 		for (FuncionarioBean obj : listaFunc) {

							String caminho_imagem = obj.getCaminho();
							caminhoImagem = caminho_imagem;
							DecimalFormat df = new DecimalFormat();
							df.applyPattern("#####.00");
							
				 			
				 			lblId.setText(String.valueOf(obj.getId_func()));
							lblNome1.setText(obj.getNome_func());
							lblSalario.setText(String.valueOf(df.format(obj.getSalario_func())));
							lblCtps.setText(obj.getCpts());
							lblFuncao.setText(obj.getFuncao_func());
							lblInicio.setText(obj.getInicio_func());
							lblTermino.setText(obj.getTermino_func());
							
							String caminho_sem_foto = "/Images/editar.gif";
							
							if (caminho_imagem.equals(null)){
								ImageIcon  imagem = new ImageIcon(caminho_sem_foto);
						  	    imagem.setImage(imagem.getImage().getScaledInstance(87,85,100));
						  	    lblImage.setIcon(imagem);
							}
							else{
							ImageIcon  imagem = new ImageIcon(caminhoImagem);
					  	     imagem.setImage(imagem.getImage().getScaledInstance(87,85,100));
					  	     lblImage.setIcon(imagem);
							}
							
							

						}
					} catch (exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					 String id_func = (String) table.getValueAt(linha,0);
					
	
					
				}

				
			});
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			table.setEditingRow(0);
			table.setEditingColumn(0);
			
			DefaultTableModel model = new DefaultTableModel(dados,colunas);
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Identifica\u00E7\u00E3o", "Nome", "CPF", "CPTS", "Fun\u00E7\u00E3o"
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
		    
			
			
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(PagamentoFuncionario.class.getResource("/Images/back.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 437, 102, 23);
		contentPanel.add(btnVoltar);
		
		 try {
				atualizaLista(table,"");
			} catch (exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scrollPane.setViewportView(table);		
			
			textNomePesquisa2 = new JTextField();
			textNomePesquisa2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {

					String opcao = comboPesquisa.getSelectedItem().toString();
					String nome = textNomePesquisa2.getText();
					
					
					if(opcao.equals("Nome")){
						try {
							atualizaLista(table, nome);
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
			textNomePesquisa2.setBounds(99, 77, 149, 17);
			contentPanel.add(textNomePesquisa2);
			textNomePesquisa2.setColumns(10);
			
			comboPesquisa = new JComboBox();
			comboPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Nome", "C\u00F3digo"}));
			comboPesquisa.setBounds(10, 77, 79, 20);
			contentPanel.add(comboPesquisa);
			
			JLabel label = new JLabel("");
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				

					String opcao = comboPesquisa.getSelectedItem().toString();
					String nome = textNomePesquisa2.getText();
					
					
					if(opcao.equals("Nome")){
						try {
							atualizaLista(table, nome);
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
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label.setIcon(new ImageIcon(PagamentoFuncionario.class.getResource("/Images/21-spotlight.png")));
			label.setBounds(274, 64, 46, 43);
			contentPanel.add(label);


		
	}
		
	
	
	
public void atualizaLista(JTable lista,String nome) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

        ImageIcon editar = new ImageIcon(PagamentoFuncionario.class.getResource("/Images/editar.gif"));  
        ImageIcon excluir = new ImageIcon(PagamentoFuncionario.class.getResource("/Images/icon_excluir.png"));
		
        
		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();		
		
		//renderer.setValue(editar);
		//renderer.setHorizontalAlignment(JLabel.CENTER);
		//columnModel.getColumn(3).setCellRenderer(renderer);
//		
//		renderer1.setValue(excluir);
//		renderer1.setHorizontalAlignment(JLabel.CENTER);
//		columnModel.getColumn(4).setCellRenderer(renderer1);

        dtm.setRowCount(0);
		List<FuncionarioBean> listaForn = new ArrayList<FuncionarioBean>();
 		listaForn = FuncionarioDao.consultarFuncionarios(nome);
 		
 		
 		String dados[] = new String[5]; 
		for (FuncionarioBean obj : listaForn) {
			dados[0] = String.valueOf(obj.getId_func());
			dados[1] = obj.getNome_func();
			dados[2] = obj.getCpf_func();
			dados[3] = obj.getCpts();
			dados[4] = obj.getFuncao_func();
			((DefaultTableModel) table.getModel()).addRow(dados); 
		} 
		table.repaint();
	}
public void atualizaFormulario(FuncionarioBean objForn){
//	textNome.setText(objForn.getNome_func());
	Integer matr = objForn.getId_func();
	
	setVisible(false);
	setVisible(true);
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
	columnModel.getColumn(4).setCellRenderer(renderer1);

    dtm.setRowCount(0);
	List<FuncionarioBean> listaFunc= new ArrayList<FuncionarioBean>();
		listaFunc = FuncionarioDao.consultarFuncionariosID(cod);
		String dados[] = new String[4]; 
	for (FuncionarioBean obj : listaFunc) {
		dados[0] = String.valueOf(obj.getId_func());
		dados[1] = obj.getNome_func();
		dados[2] = obj.getTelefone_func();
		dados[3] = obj.getCpf_func();
		((DefaultTableModel) table.getModel()).addRow(dados); 
	} 
	table.repaint();
}

}