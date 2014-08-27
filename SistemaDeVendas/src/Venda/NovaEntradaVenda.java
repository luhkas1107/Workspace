package Venda;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.TableView.TableCell;
import javax.swing.text.TableView.TableRow;
//import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Beans.PedidoBean;
import Beans.ProdutoBean;
import Beans.RelatorioBean;
import Beans.VendaBean;
import Conexao.exception;
import Dao.PedidoDao;
import Dao.ProdutoDao;
import Dao.VendaDao;
import apresentacao.Login;
import javax.swing.JSeparator;
import java.awt.Canvas;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JTable;
import javax.swing.JFormattedTextField;

import org.hibernate.transaction.BTMTransactionManagerLookup;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class NovaEntradaVenda extends JDialog implements KeyListener {
	JPanel InformacoesProduto = new JPanel();
	JRadioButton rdbtnDinheiro = new JRadioButton("Dinheiro");
	JRadioButton rdbtnCarto = new JRadioButton("Cart\u00E3o");
	JRadioButton rdbtnEllo = new JRadioButton("Elo");
	JButton btnAdicionar = new JButton("Adicionar");
	private final JPanel contentPanel = new JPanel();
	public JTextField textCodProduto;
	public static JTable table = new JTable();
	JRadioButton rdbtnMastercard = new JRadioButton("MasterCard");
	JLabel lblIdCliente = new JLabel("");
	JLabel lblCodProduto = new JLabel("Codigo");
	JLabel lblValorPago4 = new JLabel("");
	JLabel lblTroco = new JLabel("");
	public static String cod_func =String.valueOf(Login.id_func);
	public static String nome_func =String.valueOf(Login.nome_func);
	String valor_total2;
	private JTable table_pesquisa;
	JScrollPane scrollPane_pesquisa = new JScrollPane();
	JPanel panel = new JPanel();
	JLabel lblValorDaCompra = new JLabel("Valor da Compra");
	JComboBox comboPesquisa = new JComboBox();
	JFormattedTextField formattedNumeroCartao = new JFormattedTextField();
	JLabel lblNomeproduto = new JLabel("");
	JLabel lblNomeFuncionario = new JLabel("New label");
	JFormattedTextField formattedData = new JFormattedTextField();
	JLabel label_3 = new JLabel();
	JFormattedTextField formattedHora = new JFormattedTextField();
	boolean resultadoTroco;
	JLabel lblValorTotal4 = new JLabel("");
	JFormattedTextField formattedDataVenc = new JFormattedTextField();
	JLabel lblValorTotalPanelDinheiro = new JLabel("");
	JLabel lblValortotal = new JLabel("VALOR TOTAL");
	JLabel lblPrecoUnidade = new JLabel("");
	JLabel lblEstoque = new JLabel("");
	public String cod_produto;
	public int quantidade; 
	public float troco_mestre=0;
	JLabel lblImage;
	public int id_produto;
	Panel Imagem_Produto = new Panel();
	JLabel ValorPanelCompra = new JLabel("");
	public String nome_produto;
	public float preco_produto;
	JPanel paneConsulta = new JPanel();
	public static float total;
	public float total_compra;
	public String cpf_func;
	String resultado2 ;
	String num_pedido;
	JFormattedTextField formattedCodSeguranca = new JFormattedTextField();
	JButton btnNewButton_1 = new JButton("Pagamento");
	JPanel Pagamento_Cartao = new JPanel();
	JPanel Pagamento_Dinheiro = new JPanel();
	public String caminho_imagem;
	public int id_produto2;
	public static int linhas2;
	JLabel lblValorCompra = new JLabel("");
	JButton btnConsultar = new JButton("Consulta");
	JRadioButton rdbtnHipercard = new JRadioButton("HiperCard");

	public static String lbltotalcompra;
	public static String nome_cliente;
	JButton btnFinalizar_1 = new JButton("Finalizar");
	private JTextField textQuantidade;
	JRadioButton rdbtnVisa = new JRadioButton("Visa");
	JLabel lblNewLabel = new JLabel("");
	public static Label lblValorTotal = new Label("");
	JButton btnConsulta = new JButton("Consulta");
	JLabel lblQuantidade = new JLabel("Quantidade");
	public float ValorTotalDaCompra;
	JScrollPane scrollPane = new JScrollPane();
	ProdutoBean objProd = new ProdutoBean();
	ProdutoDao produtodao = new ProdutoDao();
	String id_cliente8 = PesqCliente.dados[0];
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	public static String id_cliente=PesqCliente.cod_cliente;
	public static float valortotal;
	JButton btnFinalizar = new JButton("Finalizar");
//	
//	JFormattedTextField formattedNumero= new JFormattedTextField();
//	JFormattedTextField formattedData = new JFormattedTextField();
//	JFormattedTextField formattedData2 = new JFormattedTextField();
//	JFormattedTextField formattedHora2 = new JFormattedTextField();
//	
	Date dataHoje;
	Date horaHoje;
	SimpleDateFormat formataData;
	SimpleDateFormat formataHora;
	public String data;
	public String hora;

	JLabel label = new JLabel("");
	private JTextField textValorRecebido;
	private JTextField txtConsulta;
	private JTable table_1;
	private final JLabel lblCarto = new JLabel("Cart\u00E3o");
	private final JSeparator separator_8 = new JSeparator();
	private final JLabel lblDinheiro = new JLabel("Dinheiro");
	private final JLabel label_2 = new JLabel("");
	private final JSeparator separator_10 = new JSeparator();
	private final JLabel lblInformaesDoProduto = new JLabel("Informa\u00E7\u00F5es do Produto");
	private final JLabel lblInvalido = new JLabel("");
	private final JLabel lblValido = new JLabel("");
 
	
	void imprimir(){
			try{  
            @SuppressWarnings("rawtypes") 
            String id = (num_pedido);
//            JOptionPane.showMessageDialog(null,"ID> " +num_pedido);  
             			            
            List<RelatorioBean> objRela = new ArrayList<RelatorioBean>();
            objRela = Dao.RelatorioDao.gerarVenda(id);
            
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(objRela);
            HashMap<String, Object> map = new HashMap<String, Object>();  
            
            
            String FileJasper = "Relatorios/Venda02.jasper";  
            JasperPrint jpReport = JasperFillManager.fillReport(FileJasper, map, jrDataSource);  
            JasperViewer.viewReport(jpReport, false);
            
            
            int opcao;
			opcao = JOptionPane.showConfirmDialog(null,"Deseja fazer outra venda " +nome_func+" ?", "Nova venda", JOptionPane.YES_NO_OPTION);
            if(opcao==JOptionPane.YES_OPTION){
				PesqCliente pesquisa = new PesqCliente();
				setVisible(false);
				pesquisa.setVisible(true);
				
			}else{
				setVisible(false);
			}
        }catch(Exception errorOpenFile) {  
            
        	JOptionPane.showMessageDialog(null, errorOpenFile.getMessage()); 
        	
        } 	
		
	
	}
	public void pagamento_dinheiro(){
		horaHoje = new Date();
		dataHoje = new Date();
		formataHora = new SimpleDateFormat("HHmmss");
		formataData = new SimpleDateFormat("ddMMyyyy");
		
		data = formataData.format(dataHoje);
		hora = formataHora.format(horaHoje);
		
		formattedData.setText(data);
		formattedHora.setText(hora);
//		
		 String data1 = formattedData.getText().toString();
		 String hora1 = formattedHora.getText().toString();
//		 
//		JOptionPane.showMessageDialog(null, "Data de Hoje> " +data1);
//		JOptionPane.showMessageDialog(null, "Hora de Hoje> " +hora1 );
		
		NovaEntradaVenda venda = new NovaEntradaVenda();
//		ValorPanelCompra.setVisible(true);
		
		
	String	pagamento = "Dinheiro";
	
	String TirandoVirgula = lblValorTotalPanelDinheiro.getText().replace(",", ".");
	float	valor_total = Float.parseFloat(TirandoVirgula);
	float 	valor_pago = Float.parseFloat(textValorRecebido.getText());
//	troco_mestre = valor_pago;
	int	id_func = Integer.parseInt(cod_func);
	int	id_cliente2 =Integer.parseInt(id_cliente);
		
		
boolean verificacao;
		if(valor_pago<valor_total){
			verificacao = false;
				JOptionPane.showMessageDialog(null, "Dinheiro Insulficiente.");
		}else{
			verificacao = true;
		
		}
		VendaBean produto = new VendaBean();
		produto.setForma_pagamento(pagamento);
		produto.setId_cliente(id_cliente2);
		produto.setId_func(id_func);
		produto.setValortotal(valor_total);
		produto.setValorpago(valor_pago);
		produto.setData(data1);
		produto.setHora(hora1);
		
		if (verificacao==true ){
			
		try {
//			linhas2=NovaEntradaVenda.linhas2;
//			JOptionPane.showMessageDialog(null, "Linhas " +linhas2);
			VendaDao.inserirVenda(produto);
//			imprimir_dinheiro();
			

		}
		
		 catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro! Por favor verifique todos os campos, caso ao contrario, contate ao administrador.");
			
		}
		}


btnFinalizar.setVisible(false);
btnFinalizar_1.setVisible(false);
rdbtnCarto.setVisible(false);
rdbtnDinheiro.setVisible(false);
Pagamento_Dinheiro.setVisible(false);
btnConsulta.setVisible(false);
btnConsultar.setVisible(false);
paneConsulta.setVisible(false);

DecimalFormat df = new DecimalFormat();
df.applyPattern("#######.00");

lblValorTotal4.setText("R$ "+valor_total2);
//JOptionPane.showMessageDialog(null, "Troco Mestre" +troco_mestre);
lblValorPago4.setText("R$ "+troco_mestre);
	}
	
	public void pagamento_cartao(){
		horaHoje = new Date();
		dataHoje = new Date();
		formataHora = new SimpleDateFormat("HHmmss");
		formataData = new SimpleDateFormat("ddMMyyyy");
		
		data = formataData.format(dataHoje);
		hora = formataHora.format(horaHoje);
		
		formattedData.setText(data);
		formattedHora.setText(hora);
//		
		 String data1 = formattedData.getText().toString();
		 String hora1 = formattedHora.getText().toString();
		 
//		JOptionPane.showMessageDialog(null, "Data de Hoje> " +data1);
//		JOptionPane.showMessageDialog(null, "Hora de Hoje> " +hora1 );
		
		NovaEntradaVenda venda = new NovaEntradaVenda();
//		ValorPanelCompra.setVisible(true);
		
		
	String	pagamento = "Cartao";
	float	valor_total = valortotal;
	float 	valor_pago = valor_total;
	troco_mestre = 0;
	int	id_func = Integer.parseInt(cod_func);
	int	id_cliente2 =Integer.parseInt(id_cliente);
		
		
boolean verificacao;
		if(valor_pago<valor_total){
			verificacao = false;
				JOptionPane.showMessageDialog(null, "Dinheiro Insulficiente.");
		}else{
			verificacao = true;
		
		}
		VendaBean produto = new VendaBean();
		produto.setForma_pagamento(pagamento);
		produto.setId_cliente(id_cliente2);
		produto.setId_func(id_func);
		produto.setValortotal(valor_total);
		produto.setValorpago(valor_pago);
		produto.setData(data1);
		produto.setHora(hora1);
		if (verificacao==true ){
			
		try {
//			linhas2=NovaEntradaVenda.linhas2;
//			JOptionPane.showMessageDialog(null, "Linhas " +linhas2);
			VendaDao.inserirVenda(produto);


		}
		
		 catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro! Por favor verifique todos os campos, caso ao contrario, contate ao administrador.");
			
		}

btnFinalizar.setVisible(false);
btnFinalizar_1.setVisible(false);
rdbtnCarto.setVisible(false);
rdbtnDinheiro.setVisible(false);
Pagamento_Cartao.setVisible(false);
panel.setVisible(true);

//DecimalFormat df = new DecimalFormat();
//df.applyPattern("#######.00");
//String Virgula = df.format(valor_total2

lblValorTotal4.setText("R$ "+valor_total2);
		}
	
	}
	
	
	public void imprimir_dinheiro(){
		
		String id_produto2;
			
		String qtd_produto2;
			
		String preco_unitario2;
		
		String preco_total2;
		
		String pagamento = "Dinheiro";
		
		int id_func = Integer.parseInt((label_3.getText()));
		String id_cliente2 = lblIdCliente.getText();
		if(id_cliente2.equals("")){
			JOptionPane.showMessageDialog(null, "Falta a identificação do cliente.");
		}
		int id_cliente = Integer.parseInt(lblIdCliente.getText());
		VendaDao venda = new VendaDao();
		VendaBean objVenda = new VendaBean();
		
		try {
			objVenda=VendaDao.consultarUltimaVenda();
		} catch (exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String num_pedido3 =String.valueOf(objVenda.getId_venda());
		int num_pedido2 = Integer.parseInt(num_pedido3);
		int num_pedido1 = num_pedido2+1;
		num_pedido = String.valueOf(num_pedido1);
		
		linhas2 = ((DefaultTableModel) table.getModel()).getRowCount();
		
		
		for(int x = 0; x < linhas2;x++){
			PedidoBean pedido = new PedidoBean();
			
//			ProdutoDao produtoAtualizar = new ProdutoDao();
			ProdutoBean produtoPegar = new ProdutoBean();
		
//	        id_produto2 = String.valueOf(table.getValueAt(x, 0));  
	        int id_produto = Integer.parseInt(table.getValueAt(x, 0).toString());  
	        
	        qtd_produto2 = String.valueOf(((DefaultTableModel) table.getModel()).getValueAt(x, 3));
	        int qtd_produto = Integer.parseInt(qtd_produto2);
	        
	        preco_unitario2 = String.valueOf(((DefaultTableModel) table.getModel()).getValueAt(x, 2));  
	        String semVirgula2 = preco_unitario2.replace(",", ".");
	        float preco_unitario= Float.parseFloat(semVirgula2);  
	        
	        preco_total2 = String.valueOf(((DefaultTableModel) table.getModel()).getValueAt(x, 4));  
	        String semVirgula = preco_total2.replace(",", ".");
	        float preco_total = Float.parseFloat(semVirgula);  
	        
	        pedido.setNum_pedido(num_pedido);

	        pedido.setId_produto(id_produto);

	        pedido.setId_cliente(id_cliente);

	        pedido.setId_func(id_func);
	        
	        try {
				produtoPegar=Dao.ProdutoDao.consultarProdutoParaVenda(id_produto);
			} catch (exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        int quantidadeReal = produtoPegar.getQuantidade() - qtd_produto;
	        
	        pedido.setQtd_produto(qtd_produto);
	        
	        produtoPegar.setQuantidade(quantidadeReal);
	        produtoPegar.setId_produto(id_produto);
	        
	         try {
				Dao.ProdutoDao.atualizarEstoque(produtoPegar);
			} catch (exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        pedido.setPreco_produto_unitario(preco_unitario);

	        pedido.setPreco_produto_total(preco_total);

	        pedido.setForma_pagamento(pagamento);

	        
	        
	        PedidoDao pedido2 = new PedidoDao();
	        
	        try {
	        	
				pedido2.inserirPedido(pedido);
//				JOptionPane.showMessageDialog(null, "Compra efetuada com suscesso! Pedido" );
				
			} catch (exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
			}

public void imprimir_cartao(){
		
		String id_produto2;
			
		String qtd_produto2;
			
		String preco_unitario2;
		
		String preco_total2;
		
		String pagamento = "Cartao";
		

		if(lblIdCliente.equals("") || label_3.equals("")){
		JOptionPane.showMessageDialog(null, "Favor identificação do funcionário ou cliente.");
		}
		
		
		int id_func = Integer.parseInt((label_3.getText()));
		
		int id_cliente = Integer.parseInt(lblIdCliente.getText());
		
		
		VendaDao venda = new VendaDao();
		VendaBean objVenda = new VendaBean();
		int ultimaVenda;
		
		try {
			objVenda=VendaDao.consultarUltimaVenda();
			ultimaVenda = objVenda.getId_venda();
			
			if(ultimaVenda<=0){
				ultimaVenda=1;
			}
		} catch (exception e1) {
			
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
		
		String num_pedido3 =String.valueOf(objVenda.getId_venda());
		int num_pedido2 = Integer.parseInt(num_pedido3);
		int num_pedido1 = num_pedido2+1;
		num_pedido = String.valueOf(num_pedido1);
		
		linhas2 = ((DefaultTableModel) table.getModel()).getRowCount();
		
		
		for(int x = 0; x < linhas2;x++){
			PedidoBean pedido = new PedidoBean();
			
			ProdutoBean produtoPegar = new ProdutoBean();
		
//	        id_produto2 = String.valueOf(table.getValueAt(x, 0));  
	        int id_produto = Integer.parseInt(table.getValueAt(x, 0).toString());  
	        
	        qtd_produto2 = String.valueOf(((DefaultTableModel) table.getModel()).getValueAt(x, 3));
	        int qtd_produto = Integer.parseInt(qtd_produto2);
	        
	        preco_unitario2 = String.valueOf(((DefaultTableModel) table.getModel()).getValueAt(x, 2));
	        
	        String trocaVirgula1 = preco_unitario2.replace(",", ".");
//	        JOptionPane.showConfirmDialog(null, "PrecoUnitario sem Virgula" +trocaVirgula1);
	        float preco_unitario= Float.parseFloat(trocaVirgula1);  
	        
	        preco_total2 = String.valueOf(((DefaultTableModel) table.getModel()).getValueAt(x, 4));  
	        String trocaVirgula = preco_total2.replace(",", ".");
//	        JOptionPane.showConfirmDialog(null, "PrecoTotal sem Virgula" +trocaVirgula);
	        float preco_total = Float.parseFloat(trocaVirgula);  
	        
	        pedido.setNum_pedido(num_pedido);

	        pedido.setId_produto(id_produto);

	        pedido.setId_cliente(id_cliente);

	        pedido.setId_func(id_func);
	        
	        try {
				produtoPegar=Dao.ProdutoDao.consultarProdutoParaVenda(id_produto);
			} catch (exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        int quantidadeReal = produtoPegar.getQuantidade() - qtd_produto;
	        
	        pedido.setQtd_produto(qtd_produto);
	        
	        produtoPegar.setQuantidade(quantidadeReal);
	        produtoPegar.setId_produto(id_produto);
	        
	         try {
				Dao.ProdutoDao.atualizarEstoque(produtoPegar);
			} catch (exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        pedido.setPreco_produto_unitario(preco_unitario);
	        pedido.setPreco_produto_total(preco_total);
	        pedido.setForma_pagamento(pagamento);
	        
	        PedidoDao pedido2 = new PedidoDao();
	        
	        try {
	        	
				pedido2.inserirPedido(pedido);
//				JOptionPane.showMessageDialog(null, "Compra efetuada com suscesso! Pedido" );
				
			} catch (exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
			}

	
	
	public void atualizaFormulario(DefaultTableModel modelo){
		
		//lista.setVisible(false);
		//formulario.setVisible(true);
	
	
		modelo.addRow(new Object[]{cod_produto, nome_produto, quantidade, preco_produto, total_compra});


}
	
	
	
	
	void mostrarId_produto(int id_produto3){
		PesquisaProduto pesquisa1 = new PesquisaProduto();
		
		int id_produto = id_produto3;
		int id_fornecedor = pesquisa1.id_fornecedor;
		String nome_produto2 = pesquisa1.nome_produto;
		float preco = pesquisa1.preco;
		int quantidade = pesquisa1.quantidade;
//		JOptionPane.showMessageDialog(null, "ID _ PRODUTO >" +id_produto3);
		String id_produto2 = String.valueOf(id_produto);
		textCodProduto.setText(id_produto2);			
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

	
void pesquisa(String nome){
	
try {
	ProdutoDao.consultarProduto(nome);
	objProd.setNome(nome_produto);
	objProd.setId_produto(id_produto);
	objProd.setPreco(preco_produto);
	objProd.setQuantidade(quantidade);
	total_compra=quantidade*preco_produto;
	
	//table.addRowSelectionInterval(quantidade, nome_produto,)

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
			NovaEntradaVenda dialog = new NovaEntradaVenda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NovaEntradaVenda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NovaEntradaVenda.class.getResource("/Images/shopcart2.png")));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Nova Entrada de Venda");
		
		setBounds(100, 100, 1000, 542);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1000, 666);
	
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
		 
		
		lblNewLabel.setBounds(147, 316, 63, 34);
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblValorTotal.setForeground(Color.DARK_GRAY);
		lblValorTotal.setBackground(Color.LIGHT_GRAY);
		lblValorTotal.setAlignment(Label.CENTER);
		lblValorTotal.setBounds(370, 107, 191, 95);
		lblValorTotal.setFont(new Font("Exotc350 Bd BT", Font.BOLD, 40));
		contentPanel.add(lblValorTotal);
		
		{
			JLabel lblVenda = new JLabel("VENDA");
			lblVenda.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/shopcart_48x48.png")));
			lblVenda.setHorizontalAlignment(SwingConstants.CENTER);
			lblVenda.setBounds(0, 11, 990, 57);
			lblVenda.setFont(new Font("Tahoma", Font.BOLD, 20));
			contentPanel.add(lblVenda);
		}
		{
			textCodProduto = new JTextField();
			textCodProduto.setBounds(106, 157, 131, 17);
			textCodProduto.addKeyListener(this);
			contentPanel.add(textCodProduto);
			
			textCodProduto.setColumns(10);
		}
		{
			lblCodProduto = new JLabel("C\u00F3digo");
			lblCodProduto.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/icone_produtosOrig.jpg")));
			lblCodProduto.setBounds(18, 159, 89, 14);
			contentPanel.add(lblCodProduto);
		}
		btnAdicionar.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/shopcartadd_48x48.png")));
		
		
		
		
		
		
		btnAdicionar.setBounds(18, 422, 142, 57);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				valid_itemAdd();
			}
		});
		contentPanel.add(btnAdicionar);
		

		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#######.00");
		String totallbl = String.valueOf((df.format(total)));
		
		lblNewLabel.setText(totallbl);
		total_compra+=total;
		String totalcompra = String.valueOf(total_compra);
		lbltotalcompra = totalcompra;
		lblValorTotal.setText(totalcompra);
		
		textQuantidade = new JTextField();
		textQuantidade.setBounds(106, 185, 63, 17);
		contentPanel.add(textQuantidade);
		textQuantidade.setColumns(10);
		
		lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(18, 187, 77, 14);
		contentPanel.add(lblQuantidade);
				
		
		lblValorDaCompra.setBounds(405, 243, 131, 22);
		lblValorDaCompra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(lblValorDaCompra);
		
		rdbtnDinheiro.setBounds(260, 222, 82, 23);
		contentPanel.add(rdbtnDinheiro);
	
				
		rdbtnCarto.setBounds(18, 222, 69, 23);
		contentPanel.add(rdbtnCarto);
		rdbtnCarto.setSelected(true);
		
//		rdbtnVisa = new JRadioButton("Visa");
					rdbtnVisa.setBackground(Color.LIGHT_GRAY);
					rdbtnVisa.setBounds(258, 157, 50, 23);
					Pagamento_Cartao.add(rdbtnVisa);
					rdbtnVisa.setSelected(true);
					
					
//					rdbtnMastercard = new JRadioButton("MasterCard");
					rdbtnMastercard.setBackground(Color.LIGHT_GRAY);
					rdbtnMastercard.setBounds(153, 157, 103, 23);
					Pagamento_Cartao.add(rdbtnMastercard);
					
//					rdbtnHipercard = new JRadioButton("HiperCard");
					rdbtnHipercard.setBackground(Color.LIGHT_GRAY);
					rdbtnHipercard.setBounds(62, 157, 89, 23);
					Pagamento_Cartao.add(rdbtnHipercard);
					
//					rdbtnEllo = new JRadioButton("Ello");
					rdbtnEllo.setBackground(Color.LIGHT_GRAY);
					rdbtnEllo.setBounds(10, 157, 50, 23);
					Pagamento_Cartao.add(rdbtnEllo);
				
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtnDinheiro);
		grupo.add(rdbtnCarto);
		
		ButtonGroup grupo2 = new ButtonGroup();
		grupo2.add(rdbtnEllo);
		grupo2.add(rdbtnHipercard);
		grupo2.add(rdbtnMastercard);
		grupo2.add(rdbtnVisa);
		btnNewButton_1.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/cash_register.png")));
		
		
		
		
		btnNewButton_1.setBounds(184, 422, 162, 57);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				int NumeroDeVendas = ((DefaultTableModel) table.getModel()).getRowCount();
				if(NumeroDeVendas<=0){
					JOptionPane.showMessageDialog(null, "Pedidos insulficiente, favor verificar os produtos comprados!","Cuidado!!!", JOptionPane.ERROR_MESSAGE);
				}else{
				valor_total2 = lblValorTotal.getText();
				finalizarvenda();
				}
			}
		});
		contentPanel.add(btnNewButton_1);
		lblValortotal.setIcon(null);
		
		
		
		lblValortotal.setBounds(370, 79, 191, 22);
		lblValortotal.setForeground(Color.RED);
		lblValortotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPanel.add(lblValortotal);
		
		JLabel lblCodfunc = new JLabel("C\u00F3digo Funcin\u00E1rio");
		lblCodfunc.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/funcionario.png")));
		lblCodfunc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCodfunc.setBounds(18, 121, 171, 25);
		contentPanel.add(lblCodfunc);
		
		cpf_func = Login.cpf_func;
		
		
		label_3 = new JLabel(cod_func);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(233, 126, 89, 14);
		contentPanel.add(label_3);
		
		JLabel lblCodcliente = new JLabel("C\u00F3digo Cliente");
		lblCodcliente.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/icone_cliente2.png")));
		lblCodcliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCodcliente.setBounds(18, 97, 151, 23);
		contentPanel.add(lblCodcliente);
		
		
										
										JSeparator separator_1 = new JSeparator();
										separator_1.setBounds(0, 11, 990, 2);
										contentPanel.add(separator_1);
										
										JSeparator separator = new JSeparator();
										separator.setBounds(0, 71, 1190, 2);
										contentPanel.add(separator);
										
										JSeparator separator_3 = new JSeparator();
										separator_3.setBounds(10, 84, 1, 408);
										separator_3.setOrientation(SwingConstants.VERTICAL);
										contentPanel.add(separator_3);
										
										JSeparator separator_4 = new JSeparator();
										separator_4.setBounds(10, 84, 341, 2);
										contentPanel.add(separator_4);
										
										JSeparator separator_6 = new JSeparator();
										separator_6.setBounds(10, 146, 341, 2);
										contentPanel.add(separator_6);
										
										
										JScrollPane scrollPane = new JScrollPane();
										scrollPane.setBounds(570, 79, 420, 413);
										contentPanel.add(scrollPane);
										//scrollPane.add(table);
										
										String[][] dados = new String[][] {
											//	{"2521312", "Helvetica", "1111"},
											//	{"26123123", "Joao", "1111"}
										};
										String[] colunas = new String[] {
												"Id_Produto", "Nome", "Preço","Quantidade","Valor_Total","Excluir"
											};	
										
										table = new JTable();
										table.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent arg0) {

												int linha = table.getSelectedRow();
												int coluna = table.getSelectedColumn();
												
												String id_cliente2 = (String) table.getValueAt(linha,1);
												String id_cliente = id_cliente2;
												
												
												//Integer mat = Integer.parseInt(cpf);
//												JOptionPane.showMessageDialog(null, "Coluna" +coluna);
												if(coluna == 5){
													int opcao;
													
													opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o produto: "+id_cliente ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
													   if(opcao == JOptionPane.YES_OPTION){  
														   int linha2 = table.getSelectedRow();
														   int coluna2 = 4;
														   String valor1 = (String) table.getValueAt(linha2, 4);
														   String valor2 = valor1.replace(",", ".");
														   
														   float valor = Float.parseFloat(valor2);
														   
														   somar(valor);
														   ((DefaultTableModel) table.getModel()).removeRow(linha);
															  
															lblImage.setIcon(null);
															lblNomeproduto.setText("");
															lblPrecoUnidade.setText("");
															lblEstoque.setText("");
															lblValorCompra.setText("");
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
												"Identificação", "Nome", "Pre\u00E7o", "Vendido", "Valor_Total", "Excluir"
											}
										) {
											boolean[] columnEditables = new boolean[] {
												false, false, false, false, false, false
											};
											public boolean isCellEditable(int row, int column) {
												return columnEditables[column];
											}
										});
								         table.setBounds(39, 175, 530, 232);

								         
										scrollPane.setViewportView(table);
										
										
										
								
										
										lblValorCompra.setBackground(Color.GRAY);
										lblValorCompra.setHorizontalAlignment(SwingConstants.CENTER);
										lblValorCompra.setForeground(Color.DARK_GRAY);
										lblValorCompra.setFont(new Font("Tahoma", Font.BOLD, 18));
										
										
										lblValorCompra.setBounds(405, 278, 117, 33);
										contentPanel.add(lblValorCompra);
										
										id_cliente8 = PesqCliente.dados[0];
										nome_cliente = PesqCliente.nome_cliente;
										lblIdCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
//										lblIdCliente = new JLabel();
										lblIdCliente.setText(id_cliente8);
										lblIdCliente.setBounds(233, 101, 89, 14);
										contentPanel.add(lblIdCliente);
										

										MaskFormatter numerocartao = new MaskFormatter();
										MaskFormatter data = new MaskFormatter();
										MaskFormatter data2 = new MaskFormatter();
										MaskFormatter hora = new MaskFormatter();
										MaskFormatter codigoSeguranca = new MaskFormatter();
											{
												try {
													numerocartao.setMask("####.####.####.####");
													data.setMask("##/##");
													data2.setMask("##/##/####");
													hora.setMask("##:##:##");
													codigoSeguranca.setMask("###");
												} catch (ParseException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
																
											}
										
										String nome_cliente = PesqCliente.nome_cliente;
										
										formattedData = new JFormattedTextField(data2);
										formattedData.setBounds(150, 13, 6, 20);
										formattedData.setVisible(false);
										contentPanel.add(formattedData);
										
										formattedHora = new JFormattedTextField(hora);
										formattedHora.setBounds(106, 11, 6, 20);
										formattedHora.setVisible(false);
										contentPanel.add(formattedHora);
		
		
		try {
			
			atualizaLista(table);
			
			JSeparator separator_2 = new JSeparator();
			scrollPane.setColumnHeaderView(separator_2);
			
			JSeparator separator_9 = new JSeparator();
			separator_9.setOrientation(SwingConstants.VERTICAL);
			separator_9.setBounds(350, 86, 1, 406);
			contentPanel.add(separator_9);
			
			JSeparator separator_5 = new JSeparator();
			separator_5.setBounds(10, 490, 560, 2);
			contentPanel.add(separator_5);
			
			btnConsulta = new JButton("Consulta");
			btnConsulta.setBounds(245, 153, 96, 23);
			contentPanel.add(btnConsulta);
			btnConsulta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
					
				InformacoesProduto.setVisible(false);
				rdbtnCarto.setVisible(false);
				rdbtnDinheiro.setVisible(false);
				lblCodProduto.setVisible(false);
				lblQuantidade.setVisible(false);
				btnConsulta.setVisible(false);
				textCodProduto.setVisible(false);
				textQuantidade.setVisible(false);
				btnNewButton_1.setVisible(false);
				btnAdicionar.setVisible(false);
				
				paneConsulta.setVisible(true);
			
				
				
				
				
				}
			});
			Pagamento_Cartao.setBounds(20, 260, 318, 221);
			contentPanel.add(Pagamento_Cartao);
			Pagamento_Cartao.setBackground(Color.LIGHT_GRAY);
			Pagamento_Cartao.setVisible(false);
			Pagamento_Cartao.setLayout(null);
			
			
			
			
			formattedNumeroCartao = new JFormattedTextField(numerocartao);
			formattedNumeroCartao.setBounds(120, 58, 188, 17);
			Pagamento_Cartao.add(formattedNumeroCartao);
			
			formattedDataVenc = new JFormattedTextField(data);
			formattedDataVenc.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
				
				String data = formattedDataVenc.getText().toString();
				
				if(validDataCartao(data)){
					lblInvalido.setVisible(false);
					lblValido.setVisible(true);
				}else{
					lblValido.setVisible(false);
					lblInvalido.setVisible(true);
				}
				
				
				
				}
			});
			formattedDataVenc.setBounds(120, 89, 50, 17);
			Pagamento_Cartao.add(formattedDataVenc);
			
			formattedCodSeguranca = new JFormattedTextField(codigoSeguranca);
			formattedCodSeguranca.setBounds(120, 117, 50, 17);
			Pagamento_Cartao.add(formattedCodSeguranca);
			
			
			
			JLabel lblNumeroCarto = new JLabel("N\u00FAmero Cart\u00E3o");
			lblNumeroCarto.setBounds(10, 61, 100, 14);
			Pagamento_Cartao.add(lblNumeroCarto);
			
			JLabel lblDataVenc = new JLabel("Data Venc.");
			lblDataVenc.setBounds(10, 91, 73, 14);
			Pagamento_Cartao.add(lblDataVenc);
			
			JLabel lblCodSegurana = new JLabel("C\u00F3d. Seguran\u00E7a");
			lblCodSegurana.setBounds(10, 119, 100, 14);
			Pagamento_Cartao.add(lblCodSegurana);
			lblCarto.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblCarto.setBounds(124, 21, 63, 14);
			
			Pagamento_Cartao.add(lblCarto);
			
			JLabel lblValorTotal_1 = new JLabel("Valor Total:");
			lblValorTotal_1.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblValorTotal_1.setBounds(208, 86, 100, 14);
			Pagamento_Cartao.add(lblValorTotal_1);
			ValorPanelCompra.setForeground(Color.RED);
			ValorPanelCompra.setFont(new Font("Tahoma", Font.BOLD, 14));
			

			
			
			ValorPanelCompra.setBounds(218, 111, 100, 23);
			ValorPanelCompra.setVisible(false);
			Pagamento_Cartao.add(ValorPanelCompra);
			
			JButton btnVoltar = new JButton("Voltar");
			btnVoltar.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/back.png")));
			btnVoltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Pagamento_Cartao.setVisible(false);
					InformacoesProduto.setVisible(true);
					btnAdicionar.setVisible(true);
					btnNewButton_1.setVisible(true);
					lblValortotal.setVisible(true);
					Imagem_Produto.setVisible(true);
					lblValorTotal.setVisible(true);
					lblValorCompra.setVisible(true);
					lblValorDaCompra.setVisible(true);
					
				
				}
			});
			btnVoltar.setBounds(10, 187, 103, 23);
			Pagamento_Cartao.add(btnVoltar);
			btnFinalizar.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/shopcartapply_128x128.png")));
			
			
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String data = formattedDataVenc.getText().toString();
					if(formattedNumeroCartao.equals("") || formattedDataVenc.equals("") || formattedCodSeguranca.equals("") 
					|| validDataCartao(data)==false){
						JOptionPane.showMessageDialog(null, "Por favor verifique todos os campos corretamente.");
					}else{
					imprimir_cartao();
					pagamento_cartao();
					imprimir();
					}
				}
			});
			btnFinalizar.setBounds(163, 181, 144, 29);
			Pagamento_Cartao.add(btnFinalizar);
			
			JSeparator separator_7 = new JSeparator();
			separator_7.setBounds(0, 45, 318, 2);
			Pagamento_Cartao.add(separator_7);
			
			JLabel label_1 = new JLabel("");
			label_1.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/credit_card2.png")));
			label_1.setBounds(182, 21, 44, 25);
			Pagamento_Cartao.add(label_1);
			lblInvalido.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/icon_excluir.png")));
			lblInvalido.setBounds(180, 91, 22, 14);
			lblInvalido.setVisible(false);
			Pagamento_Cartao.add(lblInvalido);
			
			lblValido.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/check-big.png")));
			lblValido.setBounds(180, 91, 22, 14);
			lblValido.setVisible(false);
			Pagamento_Cartao.add(lblValido);
			
			
			panel.setBounds(18, 243, 328, 236);
			contentPanel.add(panel);
			
			
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setLayout(null);
			panel.setVisible(false);
			
			JLabel lblValorTotal_3 = new JLabel("VALOR TOTAL");
			lblValorTotal_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblValorTotal_3.setBounds(0, 5, 328, 30);
			panel.add(lblValorTotal_3);
			lblValorTotal_3.setForeground(Color.RED);
			lblValorTotal_3.setFont(new Font("Tahoma", Font.BOLD, 18));
			
			JLabel lblValorPago_1 = new JLabel("TROCO");
			lblValorPago_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblValorPago_1.setForeground(Color.RED);
			lblValorPago_1.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblValorPago_1.setBounds(0, 108, 328, 30);
			panel.add(lblValorPago_1);
			
	
			lblValorTotal4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 40));
			lblValorTotal4.setHorizontalAlignment(SwingConstants.CENTER);
			lblValorTotal4.setBounds(0, 35, 328, 72);
			panel.add(lblValorTotal4);
			
			
			lblValorPago4.setHorizontalAlignment(SwingConstants.CENTER);
			lblValorPago4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 40));
			lblValorPago4.setBounds(0, 146, 328, 72);
			panel.add(lblValorPago4);
			
			
			InformacoesProduto.setBounds(18, 252, 318, 182);
			contentPanel.add(InformacoesProduto);
			InformacoesProduto.setLayout(null);
			
			JLabel lblProduto = new JLabel("Produto");
			lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblProduto.setBounds(0, 70, 64, 17);
			InformacoesProduto.add(lblProduto);
			
			JLabel lblPreoUnidade = new JLabel("Pre\u00E7o Unidade");
			lblPreoUnidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPreoUnidade.setBounds(0, 95, 124, 17);
			InformacoesProduto.add(lblPreoUnidade);
			
			JLabel lblEmEstoque = new JLabel("Em Estoque");
			lblEmEstoque.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblEmEstoque.setBounds(0, 120, 92, 26);
			InformacoesProduto.add(lblEmEstoque);
			lblNomeproduto.setFont(new Font("Tahoma", Font.BOLD, 13));
			
			lblNomeproduto.setBounds(128, 70, 180, 17);
			InformacoesProduto.add(lblNomeproduto);
			lblPrecoUnidade.setFont(new Font("Tahoma", Font.BOLD, 13));
			
			
			lblPrecoUnidade.setBounds(128, 95, 180, 17);
			InformacoesProduto.add(lblPrecoUnidade);
			lblEstoque.setFont(new Font("Tahoma", Font.BOLD, 13));
			
		
			lblEstoque.setBounds(128, 129, 180, 17);
			InformacoesProduto.add(lblEstoque);
			separator_10.setBounds(0, 48, 318, 2);
			
			InformacoesProduto.add(separator_10);
			lblInformaesDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblInformaesDoProduto.setHorizontalAlignment(SwingConstants.CENTER);
			lblInformaesDoProduto.setBounds(0, 11, 318, 26);
			
			InformacoesProduto.add(lblInformaesDoProduto);
			Pagamento_Dinheiro.setBounds(20, 260, 318, 175);
			contentPanel.add(Pagamento_Dinheiro);
			Pagamento_Dinheiro.setBackground(Color.LIGHT_GRAY);
			Pagamento_Dinheiro.setVisible(false);
			Pagamento_Dinheiro.setLayout(null);
			
			JLabel lblValorPago = new JLabel("Valor Pago");
			lblValorPago.setBounds(10, 55, 76, 14);
			Pagamento_Dinheiro.add(lblValorPago);
			
			textValorRecebido = new JTextField();
			textValorRecebido.setBounds(116, 55, 86, 17);
			Pagamento_Dinheiro.add(textValorRecebido);
			textValorRecebido.setColumns(10);
			
			JLabel lblValorTotal_2 = new JLabel("Valor Total");
			lblValorTotal_2.setBounds(30, 88, 76, 14);
			Pagamento_Dinheiro.add(lblValorTotal_2);
			lblValorTotalPanelDinheiro.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblValorTotalPanelDinheiro.setForeground(Color.RED);
			
			
			lblValorTotalPanelDinheiro.setBounds(27, 113, 109, 17);
			Pagamento_Dinheiro.add(lblValorTotalPanelDinheiro);
			
			JLabel lblVisualizacaoTroco = new JLabel("Troco");
			lblVisualizacaoTroco.setBounds(218, 88, 76, 14);
			Pagamento_Dinheiro.add(lblVisualizacaoTroco);
			lblTroco.setFont(new Font("Tahoma", Font.BOLD, 13));
			
			
			lblTroco.setBounds(209, 113, 109, 17);
			Pagamento_Dinheiro.add(lblTroco);
			
			JButton btnVoltar_1 = new JButton("Voltar");
			btnVoltar_1.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/back.png")));
			btnVoltar_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					Pagamento_Dinheiro.setVisible(false);
					InformacoesProduto.setVisible(true);
					btnAdicionar.setVisible(true);
					btnNewButton_1.setVisible(true);
					
					lblValortotal.setVisible(true);
					Imagem_Produto.setVisible(true);
					lblValorTotal.setVisible(true);
					lblValorCompra.setVisible(true);
					lblValorDaCompra.setVisible(true);
				
				
				
				}
			});
			btnVoltar_1.setBounds(10, 141, 109, 23);
			Pagamento_Dinheiro.add(btnVoltar_1);
			btnFinalizar_1.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/shopcartapply_128x128.png")));
			
			
			btnFinalizar_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					troco();
					if (resultadoTroco==true){
						imprimir_dinheiro();
						pagamento_dinheiro();
						imprimir();
					}else{
						JOptionPane.showMessageDialog(null, "Dinheiro Insulficiente!");
					}
				
				}
			});
			btnFinalizar_1.setBounds(183, 131, 125, 33);
			Pagamento_Dinheiro.add(btnFinalizar_1);
			
			JButton btnCalcular = new JButton("Calcular");
			btnCalcular.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					troco();
				
				}
			});
			separator_8.setBounds(0, 42, 318, 2);
			
			Pagamento_Dinheiro.add(separator_8);
			btnCalcular.setBounds(219, 53, 89, 23);
			Pagamento_Dinheiro.add(btnCalcular);
			lblDinheiro.setHorizontalAlignment(SwingConstants.CENTER);
			lblDinheiro.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblDinheiro.setBounds(0, 11, 318, 33);
			
			Pagamento_Dinheiro.add(lblDinheiro);
			label_2.setIcon(new ImageIcon(NovaEntradaVenda.class.getResource("/Images/coins2.png")));
			label_2.setBounds(202, 11, 46, 31);
			
			Pagamento_Dinheiro.add(label_2);
			
	
			paneConsulta.setBackground(Color.LIGHT_GRAY);
			paneConsulta.setBounds(18, 157, 328, 322);
			contentPanel.add(paneConsulta);
			paneConsulta.setVisible(false);
			
			JButton btnVoltar_2 = new JButton("Voltar");
			btnVoltar_2.setBounds(120, 283, 89, 23);
			btnVoltar_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
		
					InformacoesProduto.setVisible(true);
					rdbtnCarto.setVisible(true);
					rdbtnDinheiro.setVisible(true);
					lblCodProduto.setVisible(true);
					lblQuantidade.setVisible(true);
					btnConsulta.setVisible(true);
					textCodProduto.setVisible(true);
					textQuantidade.setVisible(true);
					btnAdicionar.setVisible(true);
					btnNewButton_1.setVisible(false);
					
					
					paneConsulta.setVisible(false);
				
				}
			});
			paneConsulta.setLayout(null);
			paneConsulta.add(btnVoltar_2);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(0, 71, 328, 203);
			paneConsulta.add(scrollPane_1);
			
			
			table_1 = new JTable();
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					int linha = table_1.getSelectedRow();
					int coluna = table_1.getSelectedColumn();
					
					//textField.setText((String) table.getValueAt(linha, 0));
					
					String nome = (txtConsulta.getText());
					
					
					
					ProdutoDao objProd = new ProdutoDao();
					
					
					
					
					
					try {
						ProdutoDao.consultarProduto(nome);
						
						List<ProdutoBean> listaProd = new ArrayList<ProdutoBean>();
//						txtNome.setText((String) table.getValueAt(linha, 1));					
						String nome3 = txtConsulta.getText();
						listaProd= ProdutoDao.consultarProduto(nome3);
						
				 		for (ProdutoBean obj : listaProd) {
							int id_produto2= obj.getId_produto();
							int id_fornecedor2 = obj.getId_fornecedor();
							String nome_produto2 = obj.getNome();
							int quantidade2 = obj.getQuantidade();
							float preco2 = obj.getPreco();
							String caminho = caminho_imagem = obj.getCaminho();

							
							String id3=(String) table_1.getValueAt(linha, 0);
							textCodProduto.setText(id3);
							InformacoesProduto.setVisible(true);
							rdbtnCarto.setVisible(true);
							rdbtnDinheiro.setVisible(true);
							lblCodProduto.setVisible(true);
							lblQuantidade.setVisible(true);
							btnConsulta.setVisible(true);
							textCodProduto.setVisible(true);
							textQuantidade.setVisible(true);
							btnAdicionar.setVisible(true);
							btnNewButton_1.setVisible(true);
							
							
							paneConsulta.setVisible(false);
							
							if (!caminho.equals("")){
								
								ImageIcon  imagem = new ImageIcon(caminho);
							    imagem.setImage(imagem.getImage().getScaledInstance(83,83,100));
							    lblImage.setIcon(imagem);
							    
							}else if (caminho.equals("")){


								ImageIcon  imagem1 = new ImageIcon("/Images/semFoto.jpg");
							      imagem1.setImage(imagem1.getImage().getScaledInstance(83,83,100));
							      lblImage.setIcon(imagem1);
								
							}
								
							
							
				 		}
					} catch (exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				
					
					String id3=(String) table_1.getValueAt(linha, 0);
					textCodProduto.setText(id3);
					InformacoesProduto.setVisible(true);
					rdbtnCarto.setVisible(true);
					rdbtnDinheiro.setVisible(true);
					lblCodProduto.setVisible(true);
					lblQuantidade.setVisible(true);
					btnConsulta.setVisible(true);
					textCodProduto.setVisible(true);
					textQuantidade.setVisible(true);
					btnAdicionar.setVisible(true);
					btnNewButton_1.setVisible(true);
					
					
					paneConsulta.setVisible(false);
					
					
//					JOptionPane.showMessageDialog(null, "id3 " +id3);
				
					
				
				
				
				}
			});
			table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Identifica\u00E7\u00E3o", "Nome", "Pre\u00E7o"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_1.setBounds(0, 0, 318, 16);
//			paneConsulta.add(table_1);
			scrollPane_1.setViewportView(table_1);
//			scrollPane_1.setColumnHeaderView(table_1);
			btnConsultar.setBounds(222, 22, 96, 23);
			paneConsulta.add(btnConsultar);
			
			
			txtConsulta = new JTextField();
			txtConsulta.setBounds(87, 23, 96, 20);
			paneConsulta.add(txtConsulta);
			txtConsulta.setColumns(10);
			
			comboPesquisa = new JComboBox();
			comboPesquisa.setBounds(0, 23, 66, 23);
			comboPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Nome", "C\u00F3digo"}));
			paneConsulta.add(comboPesquisa);
			Imagem_Produto.setBounds(422, 352, 87, 87);
			contentPanel.add(Imagem_Produto);
			

			
			Imagem_Produto.setBackground(Color.DARK_GRAY);
			Imagem_Produto.setLayout(null);
			{
				lblImage = new JLabel("");
				lblImage.setBounds(0, 0, 87, 87);
				Imagem_Produto.add(lblImage);
			}
			
			
			
			btnConsultar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String opcao = comboPesquisa.getSelectedItem().toString();
					String nome = txtConsulta.getText();
					
//					JOptionPane.showMessageDialog(null, "Opcção " +opcao);
					
						 if(opcao.equals("Nome")){
							 try {
								atualizaListaPesquisaNome(table_pesquisa, nome);
							} catch (exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 } if(opcao.equals("Código")){
							 try {
								int id = Integer.parseInt(nome);
								atualizaListaPesquisaCod(table_pesquisa, id);
							} catch (exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 }
					
					
					
						}
			});
			}
		 catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		try {
			atualizaListaPesquisaNome(table_pesquisa, "");
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
public void adicionarItem(int id2) throws exception{
		
		List<ProdutoBean> listaProd = new ArrayList<ProdutoBean>();
		ProdutoBean obj = new ProdutoBean();
		
		try {



		
		obj = ProdutoDao.consultaProdutoVenda(id2);
 		
// 		obj = listaProd.get(0);
 		
 		
 		
 		
 		
 		
		String nome = obj.getNome();
		float preco = obj.getPreco();
		int quantidade = obj.getQuantidade();
		int id_produto = obj.getId_produto();
		int id_fornecedor = obj.getId_fornecedor();
		float preco_venda = obj.getPreco_venda();
		
		
		int quantidadevendida2 = Integer.valueOf(textQuantidade.getText().toString());
		if (quantidadevendida2==0){
			JOptionPane.showMessageDialog(null, "Favor colocar a quantidade de produto a ser vendida." +JOptionPane.ERROR_MESSAGE);
		}
		
		String dados[] = new String[5]; 
		
			dados[0] = String.valueOf(obj.getId_produto());
			dados[1] = obj.getNome();
			dados[2] = String.valueOf(preco_venda);
			dados[3] = String.valueOf(quantidadevendida2);
			String caminho = obj.getCaminho();


			ImageIcon  imagem = new ImageIcon("/Images/semFoto.jpg");
		      imagem.setImage(imagem.getImage().getScaledInstance(83,83,100));
		      lblImage.setIcon(imagem);
			
			
			
			if (!caminho.equals("")){
				
				ImageIcon  imagem1 = new ImageIcon(caminho);
			    imagem1.setImage(imagem1.getImage().getScaledInstance(83,83,100));
			    lblImage.setIcon(imagem1);
			    
			}
//			    else{


//				ImageIcon  imagem = new ImageIcon("/Images/semFoto.jpg");
//			      imagem.setImage(imagem.getImage().getScaledInstance(83,83,100));
//			      lblImage.setIcon(imagem);
				
//			}
			
			int quantidadeestoque = obj.getQuantidade();
			
//			JOptionPane.showMessageDialog(null, "Quantidade em estoque> "+quantidadeestoque);			
					
			
			if(quantidadevendida2<=quantidadeestoque){

				DecimalFormat df = new DecimalFormat();
				df.applyPattern("#######.00");
				float resultado = Float.parseFloat((dados[2])) * quantidadevendida2;
				dados[4] = String.valueOf((df.format(resultado)));
				float resultadoAntigo = Float.parseFloat(lblValorTotal.getText().toString().replace(",", "."));
				
				float resultado1 =+  resultadoAntigo + resultado;
				resultado2 = String.valueOf((df.format(resultado1)));
				
				
				lblValorTotal.setText(resultado2);
				String valorcompra = String.valueOf(df.format((resultado)));
				lblValorCompra.setText(valorcompra);
				lblNomeproduto.setText(nome);
				lblPrecoUnidade.setText(""+df.format((preco_venda)));
				lblEstoque.setText(""+quantidade);
				((DefaultTableModel) table.getModel()).addRow(dados);
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Estoque insulficiente! " +
						"\nProduto em estoque :" +quantidadeestoque);
			}
		}catch (exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			JOptionPane.showMessageDialog(null, "Item inexistente.");
					e.printStackTrace();
					
			 }		 
table.repaint();
	}

void troco (){
	DecimalFormat df = new DecimalFormat();
	df.applyPattern("#######.00");
	String total = lblValorTotal.getText();
	String tirandoVirgula = total.replace(",", ".");
	float total2 = Float.parseFloat(tirandoVirgula);
	float total1 = Float.parseFloat(textValorRecebido.getText());
	float resultado = (total1 - total2);
	String troco = String.valueOf(df.format(resultado));
	lblTroco.setText(troco);
	String tirandoVirgula3 = troco.replace(",", ".");
	troco_mestre = Float.parseFloat(tirandoVirgula3);
	if (total1 <total2){
		resultadoTroco = false;
	}else{
		resultadoTroco=true;
	}
}
void somar(float valor){
//	int linha2 = ((DefaultTableModel) table.getModel()).getRowCount();
//	int coluna
	String valor1 = lblValorTotal.getText().replace(",", ".");
	
	float valortotal = Float.parseFloat(valor1);
	float resultado = valortotal - valor;
//	lblValorCompra.setText("");

DecimalFormat df = new DecimalFormat();
df.applyPattern("#######.00");
	
	lblValorTotal.setText(String.valueOf(df.format((resultado))));//
	 
	 }
void finalizarvenda(){
	
	id_cliente=lblIdCliente.getText();
	
	String valorPassagem = lblValorTotal.getText().replace(",", ".");
	valortotal = Float.parseFloat(valorPassagem);
	
	boolean radio;
	radio=rdbtnDinheiro.isSelected();

	
	id_cliente=lblIdCliente.getText();
	
	
		
	if (radio == true){
		linhas2=((DefaultTableModel) table.getModel()).getRowCount();
		
	PagamentoDinheiro dinheiro = new PagamentoDinheiro();
	Pagamento_Cartao.setVisible(false);
	Pagamento_Dinheiro.setVisible(true);
	InformacoesProduto.setVisible(false);
	btnNewButton_1.setVisible(false);
	btnAdicionar.setVisible(false);
	lblValorTotalPanelDinheiro.setText(lblValorTotal.getText().toString());
	lblNomeFuncionario = new JLabel(nome_cliente);
	lblNomeFuncionario.setText(nome_func);
	ValorPanelCompra.setText("" +resultado2);
//	lblValortotal.setVisible(false);
	Imagem_Produto.setVisible(false);
	
//	lblValorTotal.setVisible(false);
//	lblValorCompra.setVisible(false);
//	lblValorDaCompra.setVisible(false);
	
//	dinheiro.setVisible(true);
//	dinheiro.setModal(true);
	
	}else{
	String valorTotal = lblValorTotal.getText().toString();
		
	linhas2=((DefaultTableModel) table.getModel()).getRowCount();
//	PagamentoCartao cartao = new PagamentoCartao();
	Pagamento_Dinheiro.setVisible(false);
	Pagamento_Cartao.setVisible(true);
	InformacoesProduto.setVisible(false);
	btnNewButton_1.setVisible(false);
	btnAdicionar.setVisible(false);
	ValorPanelCompra.setVisible(true);
	ValorPanelCompra.setText("" +resultado2);
//	lblValortotal.setVisible(false);
//	Imagem_Produto.setVisible(false);
//	lblValorTotal.setVisible(false);
//	lblValorCompra.setVisible(false);
//	lblValorDaCompra.setVisible(false);
	
//	pagamento= "Cartao";
//	cartao.setVisible(true);
//	cartao.setModal(true);
	}

	


}
void codproduto(int id){
//	JOptionPane.showMessageDialog(null, id_produto2);
	
	textCodProduto.setText(String.valueOf(id_produto2));
}

boolean validDataCartao(String data){
	
	String dataSemBarra = data.replace("/","");
	String mes2 = dataSemBarra.substring(0, 2);
	String ano2 = dataSemBarra.substring(2, 4);
	
	int mes = Integer.parseInt(mes2);
	int ano = Integer.parseInt(ano2);
	
	if(mes<=12 && ano>=13 && ano<=99 && mes>=1){
		return true;
	}else{
		return false;
	}
}
public void atualizaListaPesquisaNome(JTable lista,String nome) throws exception{
	
	DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
    dtm.setRowCount(0);
    
   
	List<ProdutoBean> listaProd = new ArrayList<ProdutoBean>();
	listaProd = ProdutoDao.consultarProduto(nome);
		
		
		String dados[] = new String[5]; 
	for (ProdutoBean obj : listaProd) {
		dados[0] = String.valueOf(obj.getId_produto());
		dados[1] = obj.getNome();
		dados[2] = String.valueOf(obj.getPreco());
	
		((DefaultTableModel) table_1.getModel()).addRow(dados); 
	} 
	table_1.repaint();
    
}

public void atualizaListaPesquisaCod(JTable lista,int cod) throws exception{
	
	DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
    dtm.setRowCount(0);
    
   
	List<ProdutoBean> listaProd = new ArrayList<ProdutoBean>();
	listaProd = ProdutoDao.consultarProdutoID(cod);
		
		
		String dados[] = new String[5]; 
	for (ProdutoBean obj : listaProd) {
		dados[0] = String.valueOf(obj.getId_produto());
		dados[1] = obj.getNome();
		dados[2] = String.valueOf(obj.getPreco());
	
		((DefaultTableModel) table_1.getModel()).addRow(dados); 
	} 
	table_1.repaint();
    
}



public void atualizaLista(JTable table) throws exception{
	
	DefaultTableModel dtm = (DefaultTableModel) table.getModel();

//    ImageIcon editar = new ImageIcon(NovaEntradaVenda.class.getResource("/Images/editar.gif"));  
    ImageIcon excluir = new ImageIcon(NovaEntradaVenda.class.getResource("/Images/icon_excluir.png"));
	
	TableColumnModel columnModel = table.getColumnModel();
	
    
//	JTableRenderer renderer = new JTableRenderer();
	JTableRenderer renderer1 = new JTableRenderer();		
	
	//renderer.setValue(editar);
	//renderer.setHorizontalAlignment(JLabel.CENTER);
	//columnModel.getColumn(3).setCellRenderer(renderer);
	
	renderer1.setValue(excluir);
	renderer1.setHorizontalAlignment(JLabel.CENTER);
	columnModel.getColumn(5).setCellRenderer(renderer1);
	
    dtm.setRowCount(0);
	table.repaint();
}
public void keyPressed(KeyEvent event) {
	if(event.getSource() == textCodProduto){
		if(event.getKeyCode() == 10){  
			int id = Integer.parseInt(textCodProduto.getText().toString());
			valid_itemAdd();
//				adicionarItem(id);
		
		}
}
}

@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}


boolean ValidProduto(int id){
	int linha = ((DefaultTableModel) table.getModel()).getRowCount();
	boolean resultado=true;
	
	for(int x = 0; x < linha;x++){
	
		int idTable = Integer.parseInt(table.getValueAt(x, 0).toString());
		
		if(id==idTable){
			resultado=false;
			x=linha+1;
			break;
//			resultado=true;
			
		}
	   
}
	if(resultado==true){
//		imprimir_dinheiro();
//		JOptionPane.showMessageDialog(null, "Ta dando certo!.","Cuidado!!!", JOptionPane.ERROR_MESSAGE);
		return true;
	}else{
		JOptionPane.showMessageDialog(null, "Já existe o mesmo produto sendo comprado.","Cuidado!!!", JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
}

public void valid_itemAdd(){
String id2  =textCodProduto.getText().toString();
if(id2.equals("")){
	JOptionPane.showMessageDialog(null, "Favor digite o código do produto!");
}else{
int id = Integer.parseInt(textCodProduto.getText().toString());
		try {
//			JOptionPane.showMessageDialog(null, "Id " +id);
			
			if(ValidProduto(id)){
				adicionarItem(id);
			}
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
}