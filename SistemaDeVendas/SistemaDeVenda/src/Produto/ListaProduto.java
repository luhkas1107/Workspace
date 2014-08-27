package Produto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ListaProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaProduto dialog = new ListaProduto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaProduto() {
		setTitle("Lista de Produto");
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
		btnNewButton.setBounds(10, 127, 112, 23);
		contentPanel.add(btnNewButton);
		
		JLabel lblCpf = new JLabel("ID_Produto");
		lblCpf.setBounds(236, 62, 76, 14);
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
			CadProduto produto;
			try {
				produto = new CadProduto();
				produto.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			}
		});
		btnAlterar.setBounds(226, 127, 89, 23);
		contentPanel.add(btnAlterar);
	}
}
