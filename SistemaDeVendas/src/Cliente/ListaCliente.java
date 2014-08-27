package Cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ListaCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaCliente dialog = new ListaCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaCliente() {
		setTitle("Lista de Cliente");
		setBounds(100, 100, 559, 372);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		textField = new JTextField();
		textField.setBounds(226, 33, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(253, 11, 46, 14);
		contentPanel.add(lblNome);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.setBounds(10, 127, 102, 23);
		contentPanel.add(btnNewButton);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(253, 62, 25, 14);
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
			CadCliente cliente = new CadCliente();
			cliente.setVisible(true);
			
			
			}
		});
		btnAlterar.setBounds(226, 127, 89, 23);
		contentPanel.add(btnAlterar);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID_Cliente", "Nome_Cliente", "CPF_Cliente"
			}
		));
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 529, 154);
		contentPanel.add(scrollPane);
		//scrollPane.add(table);
		
		table.setBounds(10, 171, 529, 154);
		contentPanel.add(table);
		scrollPane.add(table);
	}
}
