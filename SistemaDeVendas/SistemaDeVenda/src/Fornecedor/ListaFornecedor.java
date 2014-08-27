package Fornecedor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ListaFornecedor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaFornecedor dialog = new ListaFornecedor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaFornecedor() {
		setTitle("Lista de Fornecedor");
		setBounds(100, 100, 559, 372);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		table = new JTable();
		table.setBounds(10, 161, 529, 164);
		contentPanel.add(table);
		
		textField = new JTextField();
		textField.setBounds(226, 33, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(253, 11, 46, 14);
		contentPanel.add(lblNome);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.setBounds(10, 127, 109, 23);
		contentPanel.add(btnNewButton);
		
		JLabel lblCpf = new JLabel("ID_Fornecedor");
		lblCpf.setBounds(236, 62, 86, 14);
		contentPanel.add(lblCpf);
		
		textField_1 = new JTextField();
		textField_1.setBounds(226, 87, 86, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			}
		});
		btnVoltar.setBounds(450, 127, 89, 23);
		contentPanel.add(btnVoltar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			CadFornecedor fornecedor = new CadFornecedor();
			fornecedor.setVisible(true);
			
			
			}
		});
		btnAlterar.setBounds(226, 127, 89, 23);
		contentPanel.add(btnAlterar);
	}
}
