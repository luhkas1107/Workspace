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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Beans.CategoriaBean;
import Beans.FornecedorBean;
import Beans.ProdutoBean;
import Conexao.exception;
import Dao.CategoriaDao;
import Dao.FornecedorDao;
import Dao.ProdutoDao;
import Log.Log; //CLASSE QUE IRÁ GERAR O LOG

public class CadProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtPreco;
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
	private JTextField txtPrecoVenda;
	JComboBox comboBox_1 = new JComboBox();
	private JPanel panel_2;
	static Date dataHoje = new Date();
	static SimpleDateFormat formataData = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss:SSSS");
	static SimpleDateFormat formataDataArq = new SimpleDateFormat("ddMMyyyyHH");
	static String agora = formataData.format(dataHoje);
	static String nomeArq = formataDataArq.format(dataHoje);
	static Logger logger = Logger.getLogger(CadProduto.class); // Classe que ta
	// sendo feito o
	// LOG
	public static String tela = "Gerenciamento_Produto"; // Nome da Tela

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

	void pegar() throws exception {

		String nome = txtNome.getText();
		int quantidade = Integer.parseInt(txtQuantidade.getText());
		float preco = Float.parseFloat(txtPreco.getText());
		String id_fornecedor = comboBox.getSelectedItem().toString();
		float preco_venda = Float.parseFloat(txtPrecoVenda.getText());
		lblImagem.setIcon(null);
		String categoria = comboCategoria.getSelectedItem().toString();
		int j = 1;
		int i;

		if (quantidade == 0) {
			quantidade = 1;
		}

		String id_fornecedor1 = id_fornecedor.substring(0, 1);
		int id_fornecedor2 = Integer.parseInt(id_fornecedor1);
		boolean verificacao;
		if (id_fornecedor1.equals("") && categoria.equals("")) {
			verificacao = false;
			JOptionPane
					.showMessageDialog(
							null,
							"Erro! Por favor verifique todos os campos, caso ao contrario, contate ao administrador.");
		} else {
			verificacao = true;

		}
		ProdutoBean produto = new ProdutoBean();
		produto.setId_fornecedor(id_fornecedor2);
		produto.setNome(nome);
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);
		produto.setCaminho(caminhoImagem);
		produto.setPreco_venda(preco_venda);
		produto.setCategoria(categoria);
		if (verificacao == true) {

			try {
				ProdutoDao.inserirProduto(produto);
				JOptionPane.showMessageDialog(null, "Cadastrado com Suscesso!");
				limpar();
				atualizaLista(table, "");
			} catch (exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane
						.showMessageDialog(
								null,
								"Erro! Por favor verifique todos os campos, caso ao contrario, contate ao administrador.");

			}

		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Erro! Por favor verifique todos os campos, caso ao contrario, contate ao administrador.");

		}

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
		setBounds(100, 100, 782, 528);
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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 135, 430, 289);
		contentPanel.add(tabbedPane);

		panel_2 = new JPanel();
		tabbedPane.addTab("Geral", null, panel_2, null);
		panel_2.setLayout(null);

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(46, 227, 130, 29);
		panel_2.add(btnProcurar);
		btnProcurar.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/icone-cadastro-proin2.gif")));

		JLabel label = new JLabel("");
		label.setBounds(364, 86, 23, 23);
		panel_2.add(label);
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

		JLabel lblPrecoVenda = new JLabel("*Pre\u00E7o de Venda");
		lblPrecoVenda.setBounds(220, 63, 107, 14);
		panel_2.add(lblPrecoVenda);
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

		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);

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
		btnCadastrar.setBounds(612, 435, 130, 23);
		contentPanel.add(btnCadastrar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/Clipboard_1.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();

			}
		});
		btnLimpar.setBounds(10, 435, 115, 23);
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
		separator_6.setBounds(0, 423, 742, 2);
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
		scrollPane.setBounds(430, 103, 342, 322);
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
		btnAlterar.setBounds(332, 435, 114, 23);
		contentPanel.add(btnAlterar);

		comboBox_1.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"Nome", "Código" }));
		comboBox_1.setBounds(10, 107, 81, 20);
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
		panel_2.add(comboBox);
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
		comboCategoria.setBounds(230, 90, 124, 18);
		panel_2.add(comboCategoria);
		comboCategoria.addItem("");

		JLabel lblCategoria = new JLabel("*Categoria");
		lblCategoria.setBounds(167, 92, 65, 14);
		panel_2.add(lblCategoria);

		JButton btnCalcular = new JButton("Calcular ");
		btnCalcular.setBounds(260, 233, 107, 23);
		panel_2.add(btnCalcular);
		btnCalcular.setIcon(new ImageIcon(CadProduto.class
				.getResource("/Images/calculator.png")));

		JLabel lblFornecedor = new JLabel("*Fornecedor");
		lblFornecedor.setBounds(0, 14, 115, 14);
		panel_2.add(lblFornecedor);

		txtPreco = new JTextField();
		txtPreco.setBounds(91, 64, 96, 17);
		panel_2.add(txtPreco);
		txtPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {

				String caractere = "1234567890.";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;ª§-¬¢£³²¹";
				if (!caractere.contains(arg0.getKeyChar() + "")
						|| especial.contains(arg0.getKeyChar() + "")) {
					arg0.consume();
				}

			}
		});
		txtPreco.setColumns(10);

		JLabel lblObrigatrio = new JLabel("* Obrigat\u00F3rio");
		lblObrigatrio.setBounds(0, 117, 74, 14);
		panel_2.add(lblObrigatrio);
		lblObrigatrio.setForeground(Color.RED);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(211, 139, 199, 25);
		panel_2.add(lblTotal);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtPrecoVenda = new JTextField();
		txtPrecoVenda.setBounds(322, 61, 68, 17);
		panel_2.add(txtPrecoVenda);
		txtPrecoVenda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String caractere = "1234567890.";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;ª§-¬¢£³²¹";
				if (!caractere.contains(arg0.getKeyChar() + "")
						|| especial.contains(arg0.getKeyChar() + "")) {
					arg0.consume();
				}

			}
		});
		txtPrecoVenda.setColumns(10);

		JLabel lblQuantidade = new JLabel("*Quantidade");
		lblQuantidade.setBounds(0, 92, 74, 14);
		panel_2.add(lblQuantidade);

		JPanel panel = new JPanel();
		panel.setBounds(211, 175, 199, 44);
		panel_2.add(panel);
		panel.add(labelTotal);

		txtNome = new JTextField();
		txtNome.setBounds(91, 39, 181, 17);
		panel_2.add(txtNome);
		txtNome.setColumns(10);

		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(91, 92, 56, 17);
		panel_2.add(txtQuantidade);
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
		label_1.setBounds(391, 86, 29, 23);
		panel_2.add(label_1);
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
		panel_1.setBounds(72, 139, 87, 85);
		panel_2.add(panel_1);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setLayout(null);
		panel_1.add(lblImagem);

		JLabel lblPreo = new JLabel("*Pre\u00E7o");
		lblPreo.setBounds(0, 64, 81, 14);
		panel_2.add(lblPreo);

		JLabel lblNome = new JLabel("*Nome");
		lblNome.setBounds(0, 39, 46, 14);
		panel_2.add(lblNome);
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

}
