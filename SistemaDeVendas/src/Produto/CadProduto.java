package Produto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton; //CLASSE QUE IRÁ GERAR O LOG
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.jdesktop.swingx.JXDatePicker;

import Beans.CategoriaBean;
import Beans.FornecedorBean;
import Beans.ProdutoBean;
import Conexao.exception;
import Dao.CategoriaDao;
import Dao.FornecedorDao;
import Dao.ProdutoDao;
import Funcoes.Desconto;
import Log.Log;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class CadProduto extends JDialog {
	JPanel panelPromocao = new JPanel();
	private JPanel panelPromocaoPreco1;
	JCheckBox chkPromocao = new JCheckBox("Habilitar produto para promo\u00E7\u00E3o");
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtQuantidade;
	public int quantidade_produto;
	public Float preco_produto;
	public String nome_produto;
	public float resultado;
	boolean resultado1;
	private Dao.ProdutoDao ProdutoDao = new Dao.ProdutoDao();
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JTextField textPesquisaNome;
	JComboBox comboCategoria = new JComboBox();
	JComboBox comboBox = new JComboBox();
	JLabel lblID = new JLabel("");
	JLabel lblImagem = new JLabel("");
	JLabel lblImage;
	File arquivo, dir;
	String caminho, caminhoImagem;
	String nomeArquivo = "";
	String extensaoArquivo = "";
	JLabel labelTotal = new JLabel("");
	JComboBox comboBox_1 = new JComboBox();
	JLabel lblAt = new JLabel("At\u00E9:");
	private JPanel AbaGeral;
	static Date dataHoje = new Date();
	static SimpleDateFormat formataData = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss:SSSS");
	static SimpleDateFormat formataDataArq = new SimpleDateFormat("ddMMyyyyHH");
	static String agora = formataData.format(dataHoje);
	static String nomeArq = formataDataArq.format(dataHoje);
	static Logger logger = Logger.getLogger(CadProduto.class); // Classe que ta sendo feito o LOG
	public static String tela = "Gerenciamento_Produto"; // Nome da Tela
	private JFormattedTextField txtPreco;
	private JFormattedTextField txtPrecoVenda;
	private JFormattedTextField txtDesc;
	private JFormattedTextField txtPreco2;
	private JFormattedTextField txtDesc2;
	private JFormattedTextField txtPrecoVenda2;
	private JFormattedTextField txtPreco3;
	private JFormattedTextField txtDesc3;
	private JFormattedTextField txtPrecoVenda3;
	private JFormattedTextField txtPromPorcDesc1;
	JCalendar calendar = new JCalendar();
	static JXDatePicker datePicker = new JXDatePicker();
    static JLabel label = new JLabel();
    JFrame frame;
    private JFormattedTextField txtPromFinal1;
    private JFormattedTextField txtPromHoraDe;
    private JFormattedTextField txtPromHoraAte;
    ButtonGroup grupoPromo1 = new ButtonGroup();
    ButtonGroup grupoPromo2 = new ButtonGroup();
    ButtonGroup grupoPromo3 = new ButtonGroup();
    JRadioButton radioDesc = new JRadioButton("% Desconto");
    private JRadioButton radioDesc1;
    JRadioButton radioValor = new JRadioButton("Valor Final(R$)");
    JRadioButton radioValor2 = new JRadioButton("Valor Final(R$)");
	JRadioButton radioDesc2 = new JRadioButton("% Desconto");
    private JRadioButton radioValor1;
    private JFormattedTextField txtPromPorcDesc2;
    private JFormattedTextField txtPromFinal2;
    private JTextField textField_2;
    private JTextField textField_3;
	JTabbedPane TabbedPromocao = new JTabbedPane(JTabbedPane.TOP);
	JCheckBox chkLista1 = new JCheckBox("");
	JCheckBox chkLista3 = new JCheckBox("");
	JCheckBox chkLista2 = new JCheckBox("");
	String list1;
	String list2;
	String list3;
	
	
	public void atualizaFormulario(ProdutoBean objProd1) {
		int matr = objProd1.getId_produto();
		txtNome.setText(objProd1.getNome());
		txtPreco.setText(String.valueOf(objProd1.getPreco()));
		txtQuantidade.setText(String.valueOf(objProd1.getQuantidade()));
		comboBox.setSelectedItem(objProd1.id_produto);
		setVisible(false);
		setVisible(true);
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

	void limpar() {

		txtNome.setText("");
		txtPreco.setText("");
		txtQuantidade.setText("");
		lblID.setText("");
		comboBox.setSelectedIndex(0);
		lblImagem.setIcon(null);
		labelTotal.setText("");
		txtPrecoVenda.setText("");
		comboCategoria.setSelectedIndex(0);
	}

	void alterar() throws IOException {

		txtNome.getText().replace(",", ".");
		txtPrecoVenda.getText().replace(",", ".");

		String preco1 = txtPreco.getText().replace(",", ".");
		String preco2 = txtPrecoVenda.getText().replace(",", ".");

		String nome = txtNome.getText();
		int quantidade = Integer.parseInt(txtQuantidade.getText());
		float preco = Float.parseFloat(preco1);
		String id_fornecedor = comboBox.getSelectedItem().toString();
		int id_produto = Integer.parseInt(lblID.getText());
		float preco_venda = Float.parseFloat(preco2);
		System.out.println(preco_venda);
		String categoria = comboCategoria.getSelectedItem().toString();

		// JOptionPane.showMessageDialog(null, "id_produto "+id_produto);

		int j = 1;
		int i;

		if (quantidade == 0) {
			quantidade = 1;
		}

		String id_fornecedor0 = id_fornecedor.substring(0, 3);
		String id_fornecedor3 = id_fornecedor0.trim();
		String id_fornecedor1 = id_fornecedor3.replace(" -", "");

		boolean verificacao;
		if (id_fornecedor1.equals(null) && categoria.equals("")) {
			verificacao = false;
		} else {
			verificacao = true;
		}
		int id_fornecedor2 = Integer.parseInt(id_fornecedor1);

		ProdutoBean produto = new ProdutoBean();

		produto.setId_fornecedor(id_fornecedor2);

		System.out.println(id_fornecedor2);

		produto.setNome(nome);
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);
		produto.setId_produto(id_produto);
		produto.setCaminho(caminhoImagem);
		produto.setPreco_venda(preco_venda);
		produto.setCategoria(categoria);
		try {

			Dao.ProdutoDao.atualizarProduto(produto);
			JOptionPane.showMessageDialog(null, "Atualizado com Suscesso!");
			limpar();
			atualizaLista(table, "");

		} catch (exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			// JOptionPane.showMessageDialog(null,
			// "Erro! Por favor verifique todos os campos, caso ao contrario, contate ao administrador.");
			e.printStackTrace();
			log(e, tela);
			logger.error(agora + " - Error " + e.getMessage());

		}

	}

	void verificacao() {
		String combo = comboBox.getSelectedItem().toString();
		if (!lblID.equals(null) && txtNome.equals("") && combo.equals("")
				&& txtPreco.equals("") && txtQuantidade.equals("")
				|| txtQuantidade.equals("0") || txtPreco.equals("0")) {

			resultado1 = false;
		} else {

			resultado1 = true;
		}

	}

	
	void autentic(){
		list1 = "N";
		list2 = "N";
		list3 = "N";
	
		if(chkLista1.isSelected()){
		list1="S";
		}
		if(chkLista2.isSelected()){
		list2="S";
		}
		if(chkLista3.isSelected()){
		list3 = "S";
		}
		
		ProdutoBean produto = new ProdutoBean();
		if(list1.equals("S") || list2.equals("S") || list3.equals("S")){
		
		produto.setNome(txtNome.getText().toString());
		produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
		produto.setPreco(Float.parseFloat(txtPreco.getText()));
		produto.setId_fornecedor(Integer.parseInt(comboBox.getSelectedItem().toString().substring(0,1)));
		produto.setPreco_venda(Float.parseFloat(txtPrecoVenda.getText().replace(",", ".")));
		produto.setCategoria(comboCategoria.getSelectedItem().toString());
		produto.setLista1(list1);
		produto.setLista1(list2);
		produto.setLista1(list3);
		
		
		boolean resultado = produto.autenticacao();
		
		if (resultado){
				System.out.println("TEVE ALGUM + ");
			}else{
			System.out.println("TEVE ALGUM - ");
		}
	}else{
		JOptionPane.showMessageDialog(null, "Produto deverá ter pelo menos cadastrado uma lista de preço");
	}
	}
	void pegar() throws exception {
		ProdutoBean produto = new ProdutoBean();
		autentic();
//		String nome = txtNome.getText();
//		int quantidade = Integer.parseInt(txtQuantidade.getText());
//		float preco = Float.parseFloat(txtPreco.getText());
//		String id_fornecedor = comboBox.getSelectedItem().toString();
//		float preco_venda = Float.parseFloat(txtPrecoVenda.getText());
//		lblImagem.setIcon(null);
//		String categoria = comboCategoria.getSelectedItem().toString();
		int j = 1;
		int i;

//		if (quantidade == 0) {
//			quantidade = 1;
//		}

//		String id_fornecedor1 = id_fornecedor.substring(0, 1);
//		int id_fornecedor2 = Integer.parseInt(id_fornecedor1);
		boolean verificacao;
//		if (id_fornecedor1.equals("") && categoria.equals("")) {
//			verificacao = false;
//			JOptionPane.showMessageDialog(null,"Erro! Por favor verifique todos os campos, caso ao contrario, contate ao administrador.");
//		} else {
//			verificacao = true;
//
//		}
		
//		produto.setId_fornecedor(id_fornecedor2);
//		produto.setNome(nome);
//		produto.setPreco(preco);
//		produto.setQuantidade(quantidade);
//		produto.setCaminho(caminhoImagem);
//		produto.setPreco_venda(preco_venda);
//		produto.setCategoria(categoria);
//		if (verificacao == true) {

//			try {
				ProdutoDao.inserirProduto(produto);
//				JOptionPane.showMessageDialog(null, "Cadastrado com Suscesso!");
//				limpar();
//				atualizaLista(table, "");
//			} catch (exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				JOptionPane
//						.showMessageDialog(
//								null,
//								"Erro! Por favor verifique todos os campos, caso ao contrario, contate ao administrador.");
//
//			}
//
//		} else {
//			JOptionPane
//					.showMessageDialog(
//							null,
//							"Erro! Por favor verifique todos os campos, caso ao contrario, contate ao administrador.");
//
//		}

	}

	public static void main(String[] args) {
		try {
			CadProduto dialog = new CadProduto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public CadProduto() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				CadProduto.class.getResource("/Images/icone_produtos.png")));
		setTitle("Cadastro de Produto");
		setBounds(100, 100, 782, 601);
		getContentPane().setLayout(new BorderLayout());

		Appender fileAppender = new FileAppender(new PatternLayout(
				PatternLayout.TTCC_CONVERSION_PATTERN), "Log\\" + tela + "_"
				+ nomeArq + "_Service.log");
		logger.addAppender(fileAppender);

		logger.info(" ---------------- Inicio do serviço ---------------- ");

		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2,
				(screen.height - this.getSize().height) / 2);

		JTabbedPane CadastroProduto = new JTabbedPane(JTabbedPane.TOP);
		CadastroProduto.setBounds(0, 135, 430, 362);
		contentPanel.add(CadastroProduto);

		AbaGeral = new JPanel();
		CadastroProduto.addTab("Geral", null, AbaGeral, null);
		AbaGeral.setLayout(null);

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(45, 294, 130, 29);
		AbaGeral.add(btnProcurar);
		btnProcurar.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/icone-cadastro-proin2.gif")));

		JLabel label = new JLabel("");
		label.setBounds(364, 59, 23, 23);
		AbaGeral.add(label);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				logger.info(" --- Iniciando tela de categoria.");
				Categoria categoria = new Categoria();
				categoria.setVisible(true);

			}
		});
		label.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/diagram_v2-13.png")));
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logger.info(" --- Abrindo Explorer, para consulta de imagem.");
				caminho = Class.class.getResource("/").toString();
				caminho = caminho.replace("/", "\\\\");
				caminho = caminho.replace("file:\\\\", "");

				JFileChooser arquivo = new JFileChooser();
				int retorno = arquivo.showOpenDialog(null);
				String caminhoArquivo = "";

				if (retorno == JFileChooser.APPROVE_OPTION) {
					logger.info(" - Pegando dados do arquivo.");

					caminhoArquivo = arquivo.getSelectedFile()
							.getAbsolutePath();
					// pega a extensão do arquivo
					int i = caminhoArquivo.lastIndexOf(".");
					extensaoArquivo = caminhoArquivo.substring(i,
							caminhoArquivo.length());
					logger.info(" - Fazendo leitura da extensão do arquivo.");

					// gera o nome do arquivo
					dataHoje = new Date();
					formataData = new SimpleDateFormat("yyyyMMddHHmmssSSS");
					nomeArquivo = formataData.format(dataHoje);
					logger.info(" - Criando nome do arquivo para gravação de dados..");

					InputStream in = null;
					OutputStream out = null;
					try {
						logger.info(" - Separando arquivo selecionado para copia.");
						in = new FileInputStream(caminhoArquivo);
						caminhoImagem = caminho + "br\\com\\temp\\Prod\\"
								+ nomeArquivo + extensaoArquivo;
						caminhoImagem = caminhoImagem.replace("%20", " "); // Créditos
						// LucasToddy
						// !
						// rs'
						logger.info(agora
								+ " - Gerando arquivo de saida para a pasta= '"
								+ caminhoImagem + "'");

						out = new FileOutputStream(caminhoImagem);
						logger.info(" --- Concluindo copia do arquivo de foto com sucesso! ");

					} catch (FileNotFoundException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Erro ao salvar arquivo!\n" + e.getMessage());

						logger.error(agora + " - Error " + e.getMessage());

					}
					byte[] buf = new byte[1024];
					int len;
					try {
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
						out.close();
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
						logger.error(agora + " - Error " + e.getMessage());

					}

					ImageIcon imagem = new ImageIcon(caminhoImagem);
					imagem.setImage(imagem.getImage().getScaledInstance(89, 89,
							100));
					lblImagem.setIcon(imagem);

				}

			}
		});

		labelTotal.setText("" + resultado);
		labelTotal.setForeground(Color.BLACK);
		labelTotal.setHorizontalAlignment(SwingConstants.CENTER);
		labelTotal.setFont(new Font("Tahoma", Font.BOLD, 19));

		lblImagem.setBounds(0, 0, 87, 85);

		JPanel AbaPreço = new JPanel();
		CadastroProduto.addTab("Preço", null, AbaPreço, null);
		AbaPreço.setLayout(null);
		
		JLabel lblfinal = new JLabel("*Final");
		lblfinal.setBounds(303, 36, 46, 14);
		AbaPreço.add(lblfinal);
		
		txtPreco = new JFormattedTextField();
		txtPreco.setEnabled(false);
		txtPreco.setHorizontalAlignment(JFormattedTextField.RIGHT);
		txtPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				Desconto desconto = new Desconto();
			 if(txtDesc.equals("")){
				float valor_final1 = Float.parseFloat(txtPrecoVenda.getText());
				float valor_inicial1 = Float.parseFloat(txtPreco.getText());
				float desc1 = desconto.DecPrecoInverso(valor_final1, valor_inicial1);
				txtDesc.setText(""+desc1);
				
			 }if (!txtDesc.equals("")){
			float inicial = Float.parseFloat(txtPreco.getText());
			float desc = Float.parseFloat(txtDesc.getText());
			float valor_final = desconto.DecPreco(inicial, desc);
			txtPrecoVenda.setText(""+valor_final);
		}
		
			
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			
				String caractere = "0123456789."; //só ira pegar estes caracteres
				String especial = "abcdefghijklmnoqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%¨&*()<>:?~][?ºª/;ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
	
			
			}
		});
		txtPreco.setColumns(10);
		txtPreco.setBounds(89, 34, 68, 17);
		AbaPreço.add(txtPreco);
		
		JLabel lblpreo_2 = new JLabel("*Pre\u00E7o 1");
		lblpreo_2.setBounds(36, 36, 81, 14);
		AbaPreço.add(lblpreo_2);
		
		txtPrecoVenda = new JFormattedTextField();
		txtPrecoVenda.setEnabled(false);
		txtPrecoVenda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				Desconto desconto = new Desconto();
				 if(!txtDesc.equals("")){
					float valor_final1 = Float.parseFloat(txtPrecoVenda.getText());
					float valor_inicial1 = Float.parseFloat(txtPreco.getText());
					float desc1 = desconto.DecPrecoInverso(valor_final1, valor_inicial1);
					txtDesc.setText(""+desc1);
					
				 }
			
			}
		});
		txtPrecoVenda.setColumns(10);
		txtPrecoVenda.setBounds(347, 33, 68, 17);
		AbaPreço.add(txtPrecoVenda);
		
		JLabel lblLucro = new JLabel("% Lucro");
		lblLucro.setBounds(167, 36, 53, 14);
		AbaPreço.add(lblLucro);
	
		
		txtDesc = new JFormattedTextField();
		txtDesc.setEnabled(false);
		txtDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				DecimalFormat df = new DecimalFormat();
				df.applyPattern("#######.00");

				Desconto desconto = new Desconto();

				if(txtDesc.equals("")){
				float valor_final1 = Float.parseFloat(df.format(txtPrecoVenda.getText()));
				float valor_inicial1 = Float.parseFloat(df.format(txtPreco.getText()));
				float desc1 = desconto.DecPrecoInverso(valor_final1, valor_inicial1);
				txtDesc.setText(""+df.format(desc1));
				
				
				
				
			 }if (!txtDesc.equals("")){
			float inicial = Float.parseFloat(txtPreco.getText());
			float desc = Float.parseFloat(txtDesc.getText());
			float valor_final = desconto.DecPreco(inicial, desc);
			txtPrecoVenda.setText(""+df.format(valor_final));
		}
			
			}
		});
		txtDesc.setBounds(230, 33, 51, 17);
		AbaPreço.add(txtDesc);
		txtDesc.setColumns(10);
		
		JLabel lblpreo = new JLabel("*Pre\u00E7o 2");
		lblpreo.setBounds(36, 64, 81, 14);
		AbaPreço.add(lblpreo);
		
		txtPreco2 = new JFormattedTextField();
		txtPreco2.setEnabled(false);
		txtPreco2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			
				Desconto desconto = new Desconto();
				 if(txtDesc2.equals("")){
					float valor_final1 = Float.parseFloat(txtPrecoVenda2.getText());
					float valor_inicial1 = Float.parseFloat(txtPreco2.getText());
					float desc1 = desconto.DecPrecoInverso(valor_final1, valor_inicial1);
					txtDesc2.setText(""+desc1);
					
				 }if (!txtDesc2.equals("")){
				float inicial = Float.parseFloat(txtPreco2.getText());
				float desc = Float.parseFloat(txtDesc2.getText());
				float valor_final = desconto.DecPreco(inicial, desc);
				txtPrecoVenda2.setText(""+valor_final);
			}
			
			}
		});
		txtPreco2.setColumns(10);
		txtPreco2.setBounds(89, 62, 68, 17);
		AbaPreço.add(txtPreco2);
		
		JLabel label_4 = new JLabel("% Lucro");
		label_4.setBounds(167, 64, 53, 14);
		AbaPreço.add(label_4);
		
		txtDesc2 = new JFormattedTextField();
		txtDesc2.setEnabled(false);
		txtDesc2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			
				Desconto desconto = new Desconto();
				 if(txtDesc2.equals("")){
					float valor_final1 = Float.parseFloat(txtPrecoVenda2.getText());
					float valor_inicial1 = Float.parseFloat(txtPreco2.getText());
					float desc1 = desconto.DecPrecoInverso(valor_final1, valor_inicial1);
					txtDesc2.setText(""+desc1);
					
				 }if (!txtDesc2.equals("")){
					 float inicial = Float.parseFloat(txtPreco2.getText());
				float desc = Float.parseFloat(txtDesc.getText());
				float valor_final = desconto.DecPreco(inicial, desc);
				txtPrecoVenda2.setText(""+valor_final);
			}
			
			
			}
		});
		txtDesc2.setColumns(10);
		txtDesc2.setBounds(230, 61, 51, 17);
		AbaPreço.add(txtDesc2);
		
		JLabel label_5 = new JLabel("*Final");
		label_5.setBounds(303, 64, 46, 14);
		AbaPreço.add(label_5);
		
		txtPrecoVenda2 = new JFormattedTextField();
		txtPrecoVenda2.setEnabled(false);
		txtPrecoVenda2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			
				
				Desconto desconto = new Desconto();
				 if(!txtDesc2.equals("")){
					float valor_final1 = Float.parseFloat(txtPrecoVenda2.getText());
					float valor_inicial1 = Float.parseFloat(txtPreco2.getText());
					float desc1 = desconto.DecPrecoInverso(valor_final1, valor_inicial1);
					txtDesc2.setText(""+desc1);
					
				 }
			
			
			}
		});
		txtPrecoVenda2.setColumns(10);
		txtPrecoVenda2.setBounds(347, 61, 68, 17);
		AbaPreço.add(txtPrecoVenda2);
		
		JLabel lblpreo_1 = new JLabel("*Pre\u00E7o 3");
		lblpreo_1.setBounds(36, 92, 81, 14);
		AbaPreço.add(lblpreo_1);
		
		txtPreco3 = new JFormattedTextField();
		txtPreco3.setEnabled(false);
		txtPreco3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			
				Desconto desconto = new Desconto();
				 if(txtDesc3.equals("")){
					float valor_final1 = Float.parseFloat(txtPrecoVenda3.getText());
					float valor_inicial1 = Float.parseFloat(txtPreco3.getText());
					float desc1 = desconto.DecPrecoInverso(valor_final1, valor_inicial1);
					txtDesc3.setText(""+desc1);
					
				 }if (!txtDesc3.equals("")){
				float inicial = Float.parseFloat(txtPreco3.getText());
				float desc = Float.parseFloat(txtDesc3.getText());
				float valor_final = desconto.DecPreco(inicial, desc);
				txtPrecoVenda3.setText(""+valor_final);
			}
			}
		});
		txtPreco3.setColumns(10);
		txtPreco3.setBounds(89, 90, 68, 17);
		AbaPreço.add(txtPreco3);
		
		JLabel label_7 = new JLabel("% Lucro");
		label_7.setBounds(167, 92, 53, 14);
		AbaPreço.add(label_7);
		
		txtDesc3 = new JFormattedTextField();
		txtDesc3.setEnabled(false);
		txtDesc3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Desconto desconto = new Desconto();
				 if(txtDesc3.equals("")){
					float valor_final1 = Float.parseFloat(txtPrecoVenda3.getText());
					float valor_inicial1 = Float.parseFloat(txtPreco3.getText());
					float desc1 = desconto.DecPrecoInverso(valor_final1, valor_inicial1);
					txtDesc3.setText(""+desc1);
					
				 }if (!txtDesc3.equals("")){
				float inicial = Float.parseFloat(txtPreco3.getText());
				float desc = Float.parseFloat(txtDesc3.getText());
				float valor_final = desconto.DecPreco(inicial, desc);
				txtPrecoVenda3.setText(""+valor_final);
			}
			
			}
		});
		txtDesc3.setColumns(10);
		txtDesc3.setBounds(230, 89, 51, 17);
		AbaPreço.add(txtDesc3);
		
		JLabel label_8 = new JLabel("*Final");
		label_8.setBounds(303, 92, 46, 14);
		AbaPreço.add(label_8);
		
		txtPrecoVenda3 = new JFormattedTextField();
		txtPrecoVenda3.setEnabled(false);
		txtPrecoVenda3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			
				
				Desconto desconto = new Desconto();
				 if(!txtDesc3.equals("")){
					float valor_final1 = Float.parseFloat(txtPrecoVenda3.getText());
					float valor_inicial1 = Float.parseFloat(txtPreco3.getText());
					float desc1 = desconto.DecPrecoInverso(valor_final1, valor_inicial1);
					txtDesc3.setText(""+desc1);
					
				 }
			
			
			}
		});
		txtPrecoVenda3.setColumns(10);
		txtPrecoVenda3.setBounds(347, 89, 68, 17);
		AbaPreço.add(txtPrecoVenda3);
		
		
		TabbedPromocao = new JTabbedPane(JTabbedPane.TOP);
		TabbedPromocao.setBounds(10, 125, 405, 198);
		TabbedPromocao.setVisible(false);
		AbaPreço.add(TabbedPromocao);

		chkPromocao = new JCheckBox("Habilitar produto para promo\u00E7\u00E3o");
		chkPromocao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if (chkPromocao.isSelected()){
				TabbedPromocao.setVisible(true);
				}else{
				TabbedPromocao.setVisible(false);
				}
			
			}
		});
		chkPromocao.setBounds(205, 111, 214, 23);
		AbaPreço.add(chkPromocao);

		
		
		panelPromocaoPreco1 = new JPanel();
		TabbedPromocao.addTab("Preço 1", null, panelPromocaoPreco1, null);
		panelPromocaoPreco1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelPromocaoPreco1.setBackground(Color.LIGHT_GRAY);
		panelPromocaoPreco1.setLayout(null);
		panelPromocaoPreco1.setVisible(false);
		
		JLabel lblPromoo = new JLabel("Promo\u00E7\u00E3o (Pre\u00E7o 1)");
		lblPromoo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPromoo.setBounds(0, 0, 400, 26);
		panelPromocaoPreco1.add(lblPromoo);
		lblPromoo.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtPromPorcDesc1 = new JFormattedTextField();
		txtPromPorcDesc1.setEnabled(false);
		txtPromPorcDesc1.setBounds(112, 69, 44, 20);
		panelPromocaoPreco1.add(txtPromPorcDesc1);
		txtPromPorcDesc1.setColumns(10);
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setBounds(10, 111, 27, 14);
		panelPromocaoPreco1.add(lblDe);
		
		lblAt = new JLabel("At\u00E9:");
		lblAt.setBounds(10, 139, 33, 14);
		panelPromocaoPreco1.add(lblAt);
		
		txtPromFinal1 = new JFormattedTextField();
		txtPromFinal1.setEnabled(false);
		txtPromFinal1.setBounds(324, 70, 71, 20);
		panelPromocaoPreco1.add(txtPromFinal1);
		txtPromFinal1.setColumns(10);
		
		JDateChooser dateChooserDe = new JDateChooser();
		dateChooserDe.setBounds(45, 108, 145, 20);
		panelPromocaoPreco1.add(dateChooserDe);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(210, 111, 33, 14);
		panelPromocaoPreco1.add(lblHora);
		
		txtPromHoraDe = new JFormattedTextField();
		txtPromHoraDe.setBounds(247, 108, 103, 20);
		panelPromocaoPreco1.add(txtPromHoraDe);
		txtPromHoraDe.setColumns(10);
		
		JDateChooser dateChosseAte = new JDateChooser();
		dateChosseAte.setBounds(45, 139, 145, 20);
		panelPromocaoPreco1.add(dateChosseAte);
		
		JLabel label_1 = new JLabel("Hora");
		label_1.setBounds(210, 139, 33, 14);
		panelPromocaoPreco1.add(label_1);
		
		txtPromHoraAte = new JFormattedTextField();
		txtPromHoraAte.setColumns(10);
		txtPromHoraAte.setBounds(247, 139, 103, 20);
		panelPromocaoPreco1.add(txtPromHoraAte);
		
		
		
		radioDesc1 = new JRadioButton("% Desconto");
		radioDesc1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			if(radioDesc1.isSelected()){
				txtPromPorcDesc1.setEnabled(true);
				txtPromFinal1.setEnabled(false);
			}else{
				txtPromPorcDesc1.setEnabled(false);
			}
			
			}
		});
		radioDesc1.setBackground(Color.LIGHT_GRAY);
		radioDesc1.setBounds(10, 69, 96, 23);
		panelPromocaoPreco1.add(radioDesc1);
		
		JLabel lblOu = new JLabel("Ou");
		lblOu.setBounds(179, 72, 33, 14);
		panelPromocaoPreco1.add(lblOu);
		
		radioValor1 = new JRadioButton("Valor Final(R$)");
		radioValor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				

					if(radioValor1.isSelected()){
						txtPromFinal1.setEnabled(true);
						txtPromPorcDesc1.setEnabled(false);
					}else{
						txtPromFinal1.setEnabled(false);
					}
					
				
			
			
			}
		});
		radioValor1.setBackground(Color.LIGHT_GRAY);
		radioValor1.setBounds(210, 68, 112, 23);
		panelPromocaoPreco1.add(radioValor1);
		
		
		//GRUPO DE RADIO DA PROMOÇÃO
		grupoPromo1.add(radioDesc1);
		grupoPromo1.add(radioValor1);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(10, 37, 385, 2);
		panelPromocaoPreco1.add(separator_7);
		
		JCheckBox chkAtivadoPromo1 = new JCheckBox("Ativar Promo\u00E7\u00E3o para Pre\u00E7o 1");
		chkAtivadoPromo1.setFont(new Font("Tahoma", Font.BOLD, 11));
		chkAtivadoPromo1.setBackground(Color.LIGHT_GRAY);
		chkAtivadoPromo1.setBounds(9, 46, 203, 23);
		panelPromocaoPreco1.add(chkAtivadoPromo1);
		
		JPanel panelPromocaoPreco2 = new JPanel();
		panelPromocaoPreco2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPromocaoPreco2.setBackground(Color.LIGHT_GRAY);
		TabbedPromocao.addTab("Preço 2", null, panelPromocaoPreco2, null);
		panelPromocaoPreco2.setLayout(null);
		
		JLabel lblPromoopromoo = new JLabel("Promo\u00E7\u00E3o (Pre\u00E7o 2)");
		lblPromoopromoo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPromoopromoo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPromoopromoo.setBounds(0, 0, 400, 26);
		panelPromocaoPreco2.add(lblPromoopromoo);
		
		txtPromPorcDesc2 = new JFormattedTextField();
		txtPromPorcDesc2.setEnabled(false);
		txtPromPorcDesc2.setColumns(10);
		txtPromPorcDesc2.setBounds(112, 69, 44, 20);
		panelPromocaoPreco2.add(txtPromPorcDesc2);
		
		JLabel label_6 = new JLabel("De:");
		label_6.setBounds(10, 111, 27, 14);
		panelPromocaoPreco2.add(label_6);
		
		JLabel label_9 = new JLabel("At\u00E9:");
		label_9.setBounds(10, 139, 33, 14);
		panelPromocaoPreco2.add(label_9);
		
		txtPromFinal2 = new JFormattedTextField();
		txtPromFinal2.setEnabled(false);
		txtPromFinal2.setColumns(10);
		txtPromFinal2.setBounds(324, 70, 71, 20);
		panelPromocaoPreco2.add(txtPromFinal2);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(45, 108, 145, 20);
		panelPromocaoPreco2.add(dateChooser);
		
		JLabel label_10 = new JLabel("Hora");
		label_10.setBounds(210, 111, 33, 14);
		panelPromocaoPreco2.add(label_10);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setColumns(10);
		formattedTextField.setBounds(247, 108, 103, 20);
		panelPromocaoPreco2.add(formattedTextField);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(45, 139, 145, 20);
		panelPromocaoPreco2.add(dateChooser_1);
		
		JLabel label_11 = new JLabel("Hora");
		label_11.setBounds(210, 139, 33, 14);
		panelPromocaoPreco2.add(label_11);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setColumns(10);
		formattedTextField_1.setBounds(247, 139, 103, 20);
		panelPromocaoPreco2.add(formattedTextField_1);
		
		radioDesc2 = new JRadioButton("% Desconto");
		radioDesc2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			

				if(radioDesc2.isSelected()){
					txtPromPorcDesc2.setEnabled(true);
					txtPromFinal2.setEnabled(false);
				}else{
					txtPromPorcDesc2.setEnabled(false);
				}
				
			
			}
		});
		radioDesc2.setBackground(Color.LIGHT_GRAY);
		radioDesc2.setBounds(10, 69, 96, 23);
		panelPromocaoPreco2.add(radioDesc2);
		
		JLabel label_12 = new JLabel("Ou");
		label_12.setBounds(179, 72, 33, 14);
		panelPromocaoPreco2.add(label_12);
		
		radioValor2 = new JRadioButton("Valor Final(R$)");
		radioValor2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			

				if(radioValor2.isSelected()){
					txtPromFinal2.setEnabled(true);
					txtPromPorcDesc2.setEnabled(false);
				}else{
					txtPromFinal2.setEnabled(false);
				}
			}
		});
		radioValor2.setBackground(Color.LIGHT_GRAY);
		radioValor2.setBounds(210, 68, 112, 23);
		panelPromocaoPreco2.add(radioValor2);
		
		
		grupoPromo2.add(radioValor2);
		grupoPromo2.add(radioDesc2);
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(10, 37, 385, 2);
		panelPromocaoPreco2.add(separator_8);
		
		JCheckBox chkAtivadoPromo2 = new JCheckBox("Ativar Promo\u00E7\u00E3o para Pre\u00E7o 2");
		chkAtivadoPromo2.setFont(new Font("Tahoma", Font.BOLD, 11));
		chkAtivadoPromo2.setBackground(Color.LIGHT_GRAY);
		chkAtivadoPromo2.setBounds(9, 46, 203, 23);
		panelPromocaoPreco2.add(chkAtivadoPromo2);
		
		JPanel panelPromocaoPreco3 = new JPanel();
		panelPromocaoPreco3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPromocaoPreco3.setBackground(Color.LIGHT_GRAY);
		TabbedPromocao.addTab("Preço 3", null, panelPromocaoPreco3, null);
		panelPromocaoPreco3.setLayout(null);
		
		JLabel lblPromoopromoo_1 = new JLabel("Promo\u00E7\u00E3o (Pre\u00E7o 3)");
		lblPromoopromoo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPromoopromoo_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPromoopromoo_1.setBounds(0, 0, 400, 26);
		panelPromocaoPreco3.add(lblPromoopromoo_1);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(112, 69, 44, 20);
		panelPromocaoPreco3.add(textField_2);
		
		JLabel label_14 = new JLabel("De:");
		label_14.setBounds(10, 111, 27, 14);
		panelPromocaoPreco3.add(label_14);
		
		JLabel label_15 = new JLabel("At\u00E9:");
		label_15.setBounds(10, 139, 33, 14);
		panelPromocaoPreco3.add(label_15);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		textField_3.setBounds(324, 70, 71, 20);
		panelPromocaoPreco3.add(textField_3);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(45, 108, 145, 20);
		panelPromocaoPreco3.add(dateChooser_2);
		
		JLabel label_16 = new JLabel("Hora");
		label_16.setBounds(210, 111, 33, 14);
		panelPromocaoPreco3.add(label_16);
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setColumns(10);
		formattedTextField_2.setBounds(247, 108, 103, 20);
		panelPromocaoPreco3.add(formattedTextField_2);
		
		JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setBounds(45, 139, 145, 20);
		panelPromocaoPreco3.add(dateChooser_3);
		
		JLabel label_17 = new JLabel("Hora");
		label_17.setBounds(210, 139, 33, 14);
		panelPromocaoPreco3.add(label_17);
		
		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setColumns(10);
		formattedTextField_3.setBounds(247, 139, 103, 20);
		panelPromocaoPreco3.add(formattedTextField_3);
		
		JRadioButton radioDesc3 = new JRadioButton("% Desconto");
		radioDesc3.setBackground(Color.LIGHT_GRAY);
		radioDesc3.setBounds(10, 69, 96, 23);
		panelPromocaoPreco3.add(radioDesc3);
		
		JLabel label_18 = new JLabel("Ou");
		label_18.setBounds(179, 72, 33, 14);
		panelPromocaoPreco3.add(label_18);
		
		JRadioButton radioValor3 = new JRadioButton("Valor Final(R$)");
		radioValor3.setBackground(Color.LIGHT_GRAY);
		radioValor3.setBounds(210, 68, 112, 23);
		panelPromocaoPreco3.add(radioValor3);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(10, 37, 385, 2);
		panelPromocaoPreco3.add(separator_9);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Ativar Promo\u00E7\u00E3o para Pre\u00E7o 3");
		chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxNewCheckBox.setBounds(9, 46, 203, 23);
		panelPromocaoPreco3.add(chckbxNewCheckBox);
		
		JLabel lblPreo = new JLabel("PRE\u00C7O");
		lblPreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPreo.setBounds(0, 0, 419, 22);
		AbaPreço.add(lblPreo);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBounds(0, 25, 425, 2);
		AbaPreço.add(separator_10);
		
		chkLista1 = new JCheckBox("");
		chkLista1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			liberarLista1();
			}
		});
		chkLista1.setBounds(10, 32, 21, 23);
		AbaPreço.add(chkLista1);
		
		chkLista2 = new JCheckBox("");
		chkLista2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			liberarLista2();
			}
		});
		chkLista2.setBounds(10, 60, 21, 23);
		AbaPreço.add(chkLista2);
		
		chkLista3 = new JCheckBox("");
		chkLista3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			liberarLista3();
			}
		});
		chkLista3.setBounds(10, 88, 21, 23);
		AbaPreço.add(chkLista3);
		

		JPanel AbaFiscal = new JPanel();
		CadastroProduto.addTab("New tab", null, AbaFiscal, null);
		AbaFiscal.setLayout(null);
		
		JLabel lblFisca = new JLabel("Fisca");
		lblFisca.setBounds(165, 11, 46, 14);
		AbaFiscal.add(lblFisca);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/icone_produtosOrig.jpg")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verificacao();

				if (resultado1 == true) {
					try {
						pegar();
					} catch (exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error(agora + " - Error " + e.getMessage());

					}
				} else if (resultado1 == false) {
					JOptionPane.showMessageDialog(null,
							"Verifique se todos os campos.");
				}

			}
		});
		btnCadastrar.setBounds(612, 508, 130, 23);
		contentPanel.add(btnCadastrar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/Clipboard_1.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();

			}
		});
		btnLimpar.setBounds(10, 508, 115, 23);
		contentPanel.add(btnLimpar);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 71, 772, 2);
		contentPanel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 11, 772, 2);
		contentPanel.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 103, 742, 2);
		contentPanel.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 138, 430, 2);
		contentPanel.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(389, 103, 1, 322);
		contentPanel.add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(389, 423, 1, 2);
		contentPanel.add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 495, 742, 2);
		contentPanel.add(separator_6);

		textPesquisaNome = new JTextField();
		textPesquisaNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				String nome = textPesquisaNome.getText().toString();

				String opcao = comboBox_1.getSelectedItem().toString();

				if (opcao.equals("Nome")) {

					try {
						atualizaLista(table, nome);

					} catch (exception e) {
						try {
							log(e, tela);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						logger.error(agora + " - Error " + e.getMessage());

						// TODO Auto-generated catch block

						e.printStackTrace();
					}
				}
				if (!nome.equals("")) {
					if (opcao.equals("Código")) {

						try {
							int id = Integer.parseInt(textPesquisaNome
									.getText());
							atualizaListaCod(table, id);

						} catch (exception e) {

							try {
								log(e, tela);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							logger.error(agora + " - Error " + e.getMessage());

							// TODO Auto-generated catch block

							e.printStackTrace();
						}
					}
				}

			}
		});
		textPesquisaNome.setBounds(101, 111, 181, 17);
		contentPanel.add(textPesquisaNome);
		textPesquisaNome.setColumns(10);

		// JButton btnAlterar = new JButton("Alterar");
		// btnAlterar.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		//
		// alterar();
		//
		//
		//
		// }
		//
		//
		// });
		// btnPesquisar.setBounds(270, 109, 109, 23);
		// contentPanel.add(btnPesquisar);

		JLabel lblNewLabel1 = new JLabel("Produto");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/icone_produtos.png")));
		lblNewLabel1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel1.setBounds(0, 11, 772, 62);
		contentPanel.add(lblNewLabel1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(430, 103, 342, 394);
		contentPanel.add(scrollPane);

		String[][] dados = new String[][] {
		// {"2521312", "Helvetica", "1111"},
		// {"26123123", "Joao", "1111"}
		};
		String[] colunas = new String[] { "CPf", "Nome", "Telefone", "CPF",
				"Excluir" };

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				limpar();

				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();

				// textField.setText((String) table.getValueAt(linha, 0));

				String id2 = ((String) table.getValueAt(linha, 0));
				int id = Integer.parseInt(id2);

				ProdutoDao objClie = new ProdutoDao();

				try {
					Dao.ProdutoDao
							.consultarProdutoParaPesquisaDoComboFornecedor(id);

					List<ProdutoBean> listaProd = new ArrayList<ProdutoBean>();
					// txtNome.setText((String) table.getValueAt(linha, 1));
					// String nome3 = txtNome.getText();
					listaProd = Dao.ProdutoDao
							.consultarProdutoParaPesquisaDoComboFornecedor(id);
					;

					for (ProdutoBean obj : listaProd) {
						String id_produto = String.valueOf(obj.getId_produto());
						String nome2 = obj.getNome();
						int id_fornecedor = obj.getId_fornecedor();
						int quantidade = obj.getQuantidade();
						double preco = obj.getPreco();
						double preco_venda = obj.getPreco_venda();
						String categoria = obj.getCategoria();
						String empresa_fornecedor = obj.getEmpresa_fornecedor();
						String caminho_imagem = obj.getCaminho();
						caminhoImagem = caminho_imagem;

						double resultado = quantidade * preco_venda;

						DecimalFormat df = new DecimalFormat();

						df.applyPattern("#####.00");

						String fornecedor = ("" + id_fornecedor + " - " + empresa_fornecedor);

						String preco_venda2 = df.format(preco_venda);

						labelTotal.setText("R$ " + df.format(resultado));
						txtNome.setText((String) table.getValueAt(linha, 1));
						txtPreco.setText(String.valueOf(df.format(preco)));
						txtQuantidade.setText(String.valueOf(quantidade));
						lblID.setText(id_produto);

						comboCategoria.setSelectedItem(categoria);

						comboBox.setSelectedItem(fornecedor); // dando erro
						txtPrecoVenda.setText("" + preco_venda2); // dando erro

						String caminho_sem_foto = ("/Images/editar.gif");

						if (caminho_imagem.equals(null)) {

							ImageIcon imagem = new ImageIcon(caminho_sem_foto);
							imagem.setImage(imagem.getImage()
									.getScaledInstance(87, 85, 100));
							lblImagem.setIcon(imagem);
						} else {
							ImageIcon imagem = new ImageIcon(caminhoImagem);
							imagem.setImage(imagem.getImage()
									.getScaledInstance(87, 85, 100));
							lblImagem.setIcon(imagem);
						}
						// int id_fornecedor2 = Integer.parseInt(id_fornecedor);
						// comboBox.setSelectedItem(id_fornecedor);

					}
				} catch (exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					try {
						log(e1, tela);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logger.error(agora + " - Error " + e1.getMessage());

				}

				String id_produto = (String) table.getValueAt(linha, 0);
				int id_produto1 = Integer.parseInt(id_produto);
				// Integer mat = Integer.parseInt(cpf);
				if (coluna == 4) {
					int opcao;
					opcao = JOptionPane.showConfirmDialog(null,
							"Deseja excluir o registro de matricula: "
									+ id_produto, "Cuidado!!",
							JOptionPane.YES_NO_OPTION);
					if (opcao == JOptionPane.YES_OPTION) {
						try {
							Dao.ProdutoDao.excluirProduto(id_produto1);
							atualizaLista(table, "");
							atualizaCombo("");
							limpar();
						} catch (exception | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							try {
								log(e, tela);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							logger.error(agora + " - Error " + e.getMessage());

						}
						JOptionPane.showMessageDialog(null,
								"Dados excluidos com sucesso!");
					}
				}
				// if (coluna == 3){
				// ProdutoBean objClie1 = new ProdutoBean();
				//
				// try {
				// //JOptionPane.showMessageDialog(null, "
				// objClie1 = ProdutoDao.consultarProdutoID(id_produto);
				// atualizaFormulario(objClie1);
				 // } catch (exception e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// }
			}

		});

		// table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = new DefaultTableModel(dados, colunas);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Identificação", "Produto", "Fornecedor", "Quantidade",
				"Excluir" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			public Component prepareRenderer(TableCellRenderer renderer,
					int rowIndex, int vColIndex) {

				DefaultTableModel m = (DefaultTableModel) table.getModel();
				Component c = table.prepareRenderer(renderer, rowIndex,
						vColIndex);

				// altera a cor de background da linha para vermelho e
				// foreground para branco
				// quando o valor da coluna 3 for igual a fechado

				if (m.getValueAt(rowIndex, 3).toString().equals("4")) {
					c.setBackground(new Color(192, 0, 0));
					c.setForeground(Color.white);
				} else {
					// mantem a cor padrão de foreground
					c.setForeground(getForeground());

					// determina a cor de background da linha selecionada
					if (table.isCellSelected(rowIndex, vColIndex)) {
						c.setBackground(new Color(184, 207, 229));
					} else {
						// linhas não selecionadas, manter cor de background
						// padrão
						c.setBackground(getBackground());
					}

				}
				return c;
			}

		});
		table.setBounds(389, 103, 343, 322);
		// contentPanel.add(table);
		try {
			atualizaLista(table, "");
			atualizaCombo("");
			atualizaComboCategoria("");
			table.updateUI();
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log(e, tela);
			logger.error(agora + " - Error " + e.getMessage());

		}
		scrollPane.setViewportView(table);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/editar.gif")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					alterar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnAlterar.setBounds(332, 508, 114, 23);
		contentPanel.add(btnAlterar);

		comboBox_1.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"Nome", "Código" }));
		comboBox_1.setBounds(10, 110, 81, 20);
		contentPanel.add(comboBox_1);

		JLabel label_2 = new JLabel("");
		label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String nome = textPesquisaNome.getText().toString();

				String opcao = comboBox_1.getSelectedItem().toString();

				if (opcao.equals("Nome")) {

					try {
						atualizaLista(table, nome);

					} catch (exception e) {

						// TODO Auto-generated catch block

						e.printStackTrace();
						try {
							log(e, tela);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						logger.error(agora + " - Error " + e.getMessage());

					}
				}

				else {

					try {

						int id = Integer.parseInt(textPesquisaNome.getText());

						atualizaListaCod(table, id);

					} catch (exception e) {

						// TODO Auto-generated catch block

						e.printStackTrace();
						try {
							log(e, tela);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						logger.error(agora + " - Error " + e.getMessage());

					}
				}
			}
		});
		label_2.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/21-spotlight.png")));
		label_2.setBounds(310, 103, 46, 33);
		contentPanel.add(label_2);

		JLabel lblIdcliente = new JLabel("C\u00F3digo");
		lblIdcliente.setBounds(10, 84, 77, 14);
		contentPanel.add(lblIdcliente);
		lblID.setBounds(101, 84, 46, 14);
		contentPanel.add(lblID);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProduto = new	 JMenu("Produto");
		menuBar.add(mnProduto);

		JMenuItem mntmNovo = new JMenuItem("Novo");
		mntmNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();

			}
		});
		mnProduto.add(mntmNovo);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		mnProduto.add(mntmSair);

	}

	public void atualizaLista(JTable lista, String nome) throws exception {

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();

		ImageIcon editar = new ImageIcon(
				CadProduto.class.getResource("/Images/editar.gif"));
		ImageIcon excluir = new ImageIcon(
				CadProduto.class.getResource("/Images/icon_excluir.png"));

		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();

		// renderer.setValue(editar);
		// renderer.setHorizontalAlignment(JLabel.CENTER);
		// columnModel.getColumn(3).setCellRenderer(renderer);
		//
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(4).setCellRenderer(renderer1);

		dtm.setRowCount(0);

		List<ProdutoBean> listaProd = new ArrayList<ProdutoBean>();
		FornecedorDao forn = new FornecedorDao();
		listaProd = ProdutoDao.consultarProduto(nome);
		String dados[] = new String[4];
		FornecedorDao.consultarFornecedor(nome);

		for (ProdutoBean obj : listaProd) {

			int id_fornecedor4 = obj.getId_fornecedor();
			dados[0] = String.valueOf(obj.getId_produto());
			dados[1] = obj.getNome();
			dados[2] = String.valueOf(obj.getId_fornecedor());
			dados[3] = String.valueOf(obj.getQuantidade());
			((DefaultTableModel) table.getModel()).addRow(dados);

		}
		table.repaint();

	}

	public void atualizaListaCod(JTable lista, int id) throws exception {

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableColumnModel columnModel = table.getColumnModel();

		ImageIcon excluir = new ImageIcon(
				CadProduto.class.getResource("/Images/icon_excluir.png"));

		JTableRenderer renderer1 = new JTableRenderer();

		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(4).setCellRenderer(renderer1);

		dtm.setRowCount(0);

		List<ProdutoBean> listaProd = new ArrayList<ProdutoBean>();
		FornecedorDao forn = new FornecedorDao();
		String nome = String.valueOf(id);

		int id1 = Integer.parseInt(textPesquisaNome.getText());

		listaProd = Dao.ProdutoDao.consultarProdutoID(id1);

		String dados[] = new String[4];
		FornecedorDao.consultarFornecedor(nome);

		for (ProdutoBean obj : listaProd) {

			int id_fornecedor4 = obj.getId_fornecedor();
			dados[0] = String.valueOf(obj.getId_produto());
			dados[1] = obj.getNome();
			dados[2] = String.valueOf(obj.getId_fornecedor());
			dados[3] = String.valueOf(obj.getQuantidade());
			((DefaultTableModel) table.getModel()).addRow(dados);

		}
		table.repaint();

	}

	public void atualizaCombo(String fornecedor) throws IOException {

		FornecedorDao fornecedordao = new FornecedorDao();
		List<FornecedorBean> listaForn = new ArrayList<FornecedorBean>();
		comboBox.setBounds(91, 11, 166, 17);
		AbaGeral.add(comboBox);
		comboBox.addItem("");
		try {
			listaForn = FornecedorDao.consultarFornecedor(fornecedor);
			for (FornecedorBean objForn : listaForn) {
				String empresa = objForn.getEmpresa();
				int id_fornecedor = objForn.getId_fornecedor();
				comboBox.addItem(id_fornecedor + " - " + empresa);

			}
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log(e, tela);
			logger.error(agora + " - Error " + e.getMessage());

		}

	}

	public void atualizaComboCategoria(String categoria) throws IOException {

		FornecedorDao fornecedordao = new FornecedorDao();
		List<CategoriaBean> listaForn = new ArrayList<CategoriaBean>();
		comboCategoria.setBounds(230, 63, 124, 18);
		AbaGeral.add(comboCategoria);
		comboCategoria.addItem("");

		JLabel lblCategoria = new JLabel("*Categoria");
		lblCategoria.setBounds(167, 65, 65, 14);
		AbaGeral.add(lblCategoria);

		JButton btnCalcular = new JButton("Calcular ");
		btnCalcular.setBounds(259, 297, 107, 23);
		AbaGeral.add(btnCalcular);
		btnCalcular.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/calculator.png")));

		JLabel lblFornecedor = new JLabel("*Fornecedor");
		lblFornecedor.setBounds(0, 14, 115, 14);
		AbaGeral.add(lblFornecedor);

		JLabel lblObrigatrio = new JLabel("* Obrigat\u00F3rio");
		lblObrigatrio.setBounds(0, 173, 74, 14);
		AbaGeral.add(lblObrigatrio);
		lblObrigatrio.setForeground(Color.RED);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(211, 206, 199, 25);
		AbaGeral.add(lblTotal);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblQuantidade = new JLabel("*Quantidade");
		lblQuantidade.setBounds(0, 65, 74, 14);
		AbaGeral.add(lblQuantidade);

		JPanel panel = new JPanel();
		panel.setBounds(211, 242, 199, 44);
		AbaGeral.add(panel);
		panel.add(labelTotal);

		txtNome = new JTextField();
		txtNome.setBounds(91, 39, 181, 17);
		AbaGeral.add(txtNome);
		txtNome.setColumns(10);

		txtQuantidade = new JTextField("0");
		txtQuantidade.setBounds(91, 65, 56, 17);
		AbaGeral.add(txtQuantidade);
		txtQuantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {

				String caractere = "1234567890";
				String especial = "!@#$%¨&*()<>.:?~][?ºª/;ª§-¬¢£³²¹";
				if (!caractere.contains(arg0.getKeyChar() + "")
						|| especial.contains(arg0.getKeyChar() + "")) {
					arg0.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				String quantidade1 = txtQuantidade.getText();
				String preco1 = txtPrecoVenda.getText();
				if (!quantidade1.equals(null) && !preco1.equals(null)) {
					quantidade_produto = Integer.parseInt(quantidade1);
					preco_produto = Float.parseFloat(preco1);
					resultado = quantidade_produto * preco_produto;
					labelTotal.setText("R$ " + resultado);
				}

			}
		});
		txtQuantidade.setColumns(10);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(391, 59, 29, 23);
		AbaGeral.add(label_1);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				comboCategoria.removeAllItems();
				try {
					atualizaComboCategoria("");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_1.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/cats.png")));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(73, 198, 87, 85);
		AbaGeral.add(panel_1);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setLayout(null);
		panel_1.add(lblImagem);

		JLabel lblNome = new JLabel("*Nome");
		lblNome.setBounds(0, 39, 46, 14);
		AbaGeral.add(lblNome);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(0, 124, 74, 14);
		AbaGeral.add(lblDescrio);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(91, 93, 324, 85);
		AbaGeral.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane_1.setViewportView(textArea);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCalcular.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String quantidade1 = txtQuantidade.getText();
				String preco1 = txtPrecoVenda.getText();
				if (!quantidade1.equals(null) && !preco1.equals(null)) {
					quantidade_produto = Integer.parseInt(quantidade1);
					resultado = quantidade_produto * preco_produto;
					preco_produto = Float.parseFloat(preco1);
					labelTotal.setText("R$ " + resultado);
				}
			}
		});
		try {
			listaForn = CategoriaDao.consultarCategoria(categoria);
			for (CategoriaBean objForn : listaForn) {
				int id = objForn.getId_categoria();
				String nome_categoria = objForn.getNome_categoria();
				comboCategoria.addItem(nome_categoria);
			}
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log(e, tela);
			logger.error(agora + " - Error " + e.getMessage());

		}

	}

	private static void log(Exception e, String tela) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Entrou no log");
		Log.GerarLog4J_WARN(e, tela);
	}
	
	void liberarLista1(){
	
		
		
		if(chkLista1.isSelected()){
			// LIBERA TODA LISTA 1 PARA SER EDITADO
			txtPreco.setEnabled(true);
			txtDesc.setEnabled(true);
			txtPrecoVenda.setEnabled(true);
		}else{
			//BLOQUEIA A LISTA 1
			txtPreco.setEnabled(false);
			txtDesc.setEnabled(false);
			txtPrecoVenda.setEnabled(false);
			
		}
			
	}
	void liberarLista2(){
		if (chkLista2.isSelected()){
			// LIBERA TODA LISTA 2 PARA SER EDITADO
			txtPreco2.setEnabled(true);
			txtDesc2.setEnabled(true);
			txtPrecoVenda2.setEnabled(true);
		}else{
			//BLOQUEIA A LISTA 2
			txtPreco2.setEnabled(false);
			txtDesc2.setEnabled(false);
			txtPrecoVenda2.setEnabled(false);
			
		}	
	
	}
	void liberarLista3(){
		if(chkLista3.isSelected()){
			// LIBERA TODA LISTA 3 PARA SER EDITADO
			txtPreco3.setEnabled(true);
			txtDesc3.setEnabled(true);
			txtPrecoVenda3.setEnabled(true);
		}else{
			//BLOQUEIA A LISTA 3
			txtPreco3.setEnabled(false);
			txtDesc3.setEnabled(false);
			txtPrecoVenda3.setEnabled(false);
			}
	
	}
	
}
