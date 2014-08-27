package Produto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class ProdutoMaisVendido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProdutoMaisVendido dialog = new ProdutoMaisVendido();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProdutoMaisVendido() {
		setBounds(100, 100, 542, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 128, 46, 14);
		contentPanel.add(lblNome);
		
		JLabel lblQuantidadeVendida = new JLabel("Quantidade Vendida");
		lblQuantidadeVendida.setBounds(10, 167, 108, 14);
		contentPanel.add(lblQuantidadeVendida);
		
		JLabel lblPreoDeLucro = new JLabel("Pre\u00E7o de Lucro");
		lblPreoDeLucro.setBounds(10, 208, 108, 14);
		contentPanel.add(lblPreoDeLucro);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(86, 10, 91, 87);
		contentPanel.add(panel);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 87, 87);
		panel.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(276, 0, 2, 266);
		contentPanel.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(284, 46, 236, 208);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "Quantidade"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nome", "C\u00F3digo"}));
		comboBox.setBounds(288, 10, 80, 18);
		contentPanel.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(388, 10, 96, 18);
		contentPanel.add(textField);
		textField.setColumns(10);
	}
}
