package Cliente;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Beans.ClienteBean;
import Conexao.exception;
import java.awt.Window.Type;
import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;

public class EmailLote1 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableOrigem;
	private JTable tableDestino;
	
//	public String dest[] = new String[200];
//	public String reme="lucasfeitosa13@hotmail.com";
//	public String assunto[] = new String[200];
//	public String texto[] = new String[200];
	public static int num_email;
	public static String email[] = new String[num_email];
	public static List<Integer> listaEmail = new ArrayList<Integer>();
//	public qtda
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmailLote1 dialog = new EmailLote1();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EmailLote1() {
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setTitle("E-mail em Lote (Cliente)");
		setBounds(100, 100, 570, 451);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 216, 277);
		contentPanel.add(scrollPane);
		
		tableOrigem = new JTable();
		tableOrigem.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "E-mail"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableOrigem.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableOrigem.getColumnModel().getColumn(1).setPreferredWidth(89);
		scrollPane.setViewportView(tableOrigem);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(328, 81, 216, 277);
		contentPanel.add(scrollPane_1);
		
		tableDestino = new JTable();
		tableDestino.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "E-mail"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(tableDestino);
		
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if (tableOrigem.getSelectedRowCount()!=0 ){ //Verifica se existe linha selecionada para não dar erro na hora de pegar os valores  
				
					if (tableOrigem.getSelectedRowCount()>1){
						int cont = tableOrigem.getRowCount();
						
						for (int x=0;x<=cont;x++){

						    //Pega os models das listas, para fazer as inserções e remoções  
						    DefaultTableModel modelOrigem = (DefaultTableModel) tableOrigem.getModel();  
						    DefaultTableModel modelDestino = (DefaultTableModel) tableDestino.getModel();  
						  
						    //Cria uma linha para ser incluida na tabela de destino, no meu caso tem duas colunas, adapte para as suas tabelas  
						    Object[] obj = {tableOrigem.getValueAt(tableOrigem.getSelectedRow(), 0),tableOrigem.getValueAt(tableOrigem.getSelectedRow(), 1)};  
						  
						    //Adiciona no destino e remove da origem  
						    modelDestino.addRow(obj);  
						    modelOrigem.removeRow(tableOrigem.getSelectedRow());  
						
						}				
						
					}else if (tableOrigem.getSelectedRowCount()==1) {
				    //Pega os models das listas, para fazer as inserções e remoções  
				    DefaultTableModel modelOrigem = (DefaultTableModel) tableOrigem.getModel();  
				    DefaultTableModel modelDestino = (DefaultTableModel) tableDestino.getModel();  
				  
				    //Cria uma linha para ser incluida na tabela de destino, no meu caso tem duas colunas, adapte para as suas tabelas  
				    Object[] obj = {tableOrigem.getValueAt(tableOrigem.getSelectedRow(), 0),tableOrigem.getValueAt(tableOrigem.getSelectedRow(), 1)};  
				  
				    //Adiciona no destino e remove da origem  
				    modelDestino.addRow(obj);  
				    modelOrigem.removeRow(tableOrigem.getSelectedRow());  
				} 
				}
				else {
					JOptionPane.showMessageDialog(null, "Verifique se foi selecionado alguma linha.");
				//Não tem nenhuma linha selecionada na tabela de origem, faça um aviso para o usuário ou algo do tipo.  
				}  
			
			
			
			}
		});
		button.setBounds(236, 126, 82, 23);
		contentPanel.add(button);
		
		JButton btnNewButton = new JButton(">>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					int cont_num_linhas = tableOrigem.getRowCount();
					
					tableOrigem.getSelectionModel().setSelectionInterval(0, cont_num_linhas); 
					
					if (tableOrigem.getSelectedRowCount()>1){
						int cont = tableOrigem.getRowCount();
						
						for (int x=0;x<=cont;x++){

						    //Pega os models das listas, para fazer as inserções e remoções  
						    DefaultTableModel modelOrigem = (DefaultTableModel) tableOrigem.getModel();  
						    DefaultTableModel modelDestino = (DefaultTableModel) tableDestino.getModel();  
						  
						    //Cria uma linha para ser incluida na tabela de destino, no meu caso tem duas colunas, adapte para as suas tabelas  
						    Object[] obj = {tableOrigem.getValueAt(tableOrigem.getSelectedRow(), 0),tableOrigem.getValueAt(tableOrigem.getSelectedRow(), 1)};  
						  
						    //Adiciona no destino e remove da origem  
						    modelDestino.addRow(obj);  
						    modelOrigem.removeRow(tableOrigem.getSelectedRow());  
						
						}				
						
					}else if (tableOrigem.getSelectedRowCount()==1) {
				    //Pega os models das listas, para fazer as inserções e remoções  
				    DefaultTableModel modelOrigem = (DefaultTableModel) tableOrigem.getModel();  
				    DefaultTableModel modelDestino = (DefaultTableModel) tableDestino.getModel();  
				  
				    //Cria uma linha para ser incluida na tabela de destino, no meu caso tem duas colunas, adapte para as suas tabelas  
				    Object[] obj = {tableOrigem.getValueAt(tableOrigem.getSelectedRow(), 0),tableOrigem.getValueAt(tableOrigem.getSelectedRow(), 1)};  
				  
				    //Adiciona no destino e remove da origem  
				    modelDestino.addRow(obj);  
				    modelOrigem.removeRow(tableOrigem.getSelectedRow());  
				} 
				
				else {
					JOptionPane.showMessageDialog(null, "Verifique se foi selecionado alguma linha.");
				//Não tem nenhuma linha selecionada na tabela de origem, faça um aviso para o usuário ou algo do tipo.  
				}  
			
			
			}
		});
		btnNewButton.setBounds(236, 160, 82, 23);
		contentPanel.add(btnNewButton);
		
		JButton button_1 = new JButton("<<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				int cont_num_linhas = tableDestino.getRowCount();
				
				tableDestino.getSelectionModel().setSelectionInterval(0, cont_num_linhas); 
				
				if (tableDestino.getSelectedRowCount()>1){
					int cont = tableDestino.getRowCount();
					
					for (int x=0;x<=cont;x++){

					    //Pega os models das listas, para fazer as inserções e remoções  
					    DefaultTableModel modelOrigem = (DefaultTableModel) tableDestino.getModel();  
					    DefaultTableModel modelDestino = (DefaultTableModel) tableOrigem.getModel();  
					  
					    //Cria uma linha para ser incluida na tabela de destino, no meu caso tem duas colunas, adapte para as suas tabelas  
					    Object[] obj = {tableDestino.getValueAt(tableDestino.getSelectedRow(), 0),tableDestino.getValueAt(tableDestino.getSelectedRow(), 1)};  
					  
					    //Adiciona no destino e remove da origem  
					    modelDestino.addRow(obj);  
					    modelOrigem.removeRow(tableDestino.getSelectedRow());  
					
					}				
					
				}else if (tableDestino.getSelectedRowCount()==1) {
			    //Pega os models das listas, para fazer as inserções e remoções  
			    DefaultTableModel modelOrigem = (DefaultTableModel) tableDestino.getModel();  
			    DefaultTableModel modelDestino = (DefaultTableModel) tableOrigem.getModel();  
			  
			    //Cria uma linha para ser incluida na tabela de destino, no meu caso tem duas colunas, adapte para as suas tabelas  
			    Object[] obj = {tableDestino.getValueAt(tableDestino.getSelectedRow(), 0),tableDestino.getValueAt(tableDestino.getSelectedRow(), 1)};  
			  
			    //Adiciona no destino e remove da origem  
			    modelDestino.addRow(obj);  
			    modelOrigem.removeRow(tableDestino.getSelectedRow());  
			} 
			
			else {
				JOptionPane.showMessageDialog(null, "Verifique se foi selecionado alguma linha.");
			//Não tem nenhuma linha selecionada na tabela de origem, faça um aviso para o usuário ou algo do tipo.  
			}  
			
			
			}
		});
		button_1.setBounds(236, 269, 82, 23);
		contentPanel.add(button_1);
		
		JButton button_2 = new JButton("<");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			
				if (tableDestino.getSelectedRowCount()!=0 ){ //Verifica se existe linha selecionada para não dar erro na hora de pegar os valores  
					
					if (tableDestino.getSelectedRowCount()>1){
						int cont = tableDestino.getRowCount();
						
						for (int x=0;x<=cont;x++){

						    //Pega os models das listas, para fazer as inserções e remoções  
						    DefaultTableModel modelOrigem = (DefaultTableModel) tableDestino.getModel();  
						    DefaultTableModel modelDestino = (DefaultTableModel) tableOrigem.getModel();  
						  
						    //Cria uma linha para ser incluida na tabela de destino, no meu caso tem duas colunas, adapte para as suas tabelas  
						    Object[] obj = {tableDestino.getValueAt(tableDestino.getSelectedRow(), 0),tableDestino.getValueAt(tableDestino.getSelectedRow(), 1)};  
						  
						    //Adiciona no destino e remove da origem  
						    modelDestino.addRow(obj);  
						    modelOrigem.removeRow(tableDestino.getSelectedRow());  
						
						}				
						
					}else if (tableDestino.getSelectedRowCount()==1) {
				    //Pega os models das listas, para fazer as inserções e remoções  
				    DefaultTableModel modelOrigem = (DefaultTableModel) tableDestino.getModel();  
				    DefaultTableModel modelDestino = (DefaultTableModel) tableOrigem.getModel();  
				  
				    //Cria uma linha para ser incluida na tabela de destino, no meu caso tem duas colunas, adapte para as suas tabelas  
				    Object[] obj = {tableDestino.getValueAt(tableDestino.getSelectedRow(), 0),tableDestino.getValueAt(tableDestino.getSelectedRow(), 1)};  
				  
				    //Adiciona no destino e remove da origem  
				    modelDestino.addRow(obj);  
				    modelOrigem.removeRow(tableDestino.getSelectedRow());  
				} 
				}
				else {
					JOptionPane.showMessageDialog(null, "Verifique se foi selecionado alguma linha.");
				//Não tem nenhuma linha selecionada na tabela de origem, faça um aviso para o usuário ou algo do tipo.  
				}  
			
			
			
			
			
			}
		});
		button_2.setBounds(236, 235, 82, 23);
		contentPanel.add(button_2);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				num_email = tableDestino.getRowCount();
				
				
				
				if(num_email>0){
					enviarInformacoes();
					
				EmailLote2 email = new EmailLote2 ();
				System.out.println("Qtdade de informações a ser enviada para 'EmailLote2': "+num_email);
				setModal(false);
				email.setVisible(true);
				email.setModal(true);
				email.conferir();
				}else{
					JOptionPane.showMessageDialog(null, "Sem e-mail selecionado.");
				}
			}
		});
		btnConfirmar.setBounds(433, 379, 111, 23);
		contentPanel.add(btnConfirmar);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 379, 89, 23);
		contentPanel.add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 59, 554, 2);
		contentPanel.add(separator);
		
		JLabel lblEmailEmLote = new JLabel("E-mail em Lote para Cliente");
		lblEmailEmLote.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmailEmLote.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailEmLote.setBounds(10, 11, 534, 37);
		contentPanel.add(lblEmailEmLote);
		
		try {
			atualizaLista(tableOrigem, "");
		} catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enviarInformacoes(){
		EmailLote2 parte2 = new EmailLote2 ();
		int cont=1;
		email = new String[num_email];
		System.out.println("Numero de Emails> " +num_email);
		for (int x=0;x<=num_email;x++){
			if(cont<=num_email){
		email[x] = (String) tableDestino.getValueAt(x, 1);
		System.out.println("E-mail> " +email[x]+" - " +x);
			}
		cont++;
			
		}
	}
	
	

public void atualizaLista(JTable lista,String nome) throws exception{
		
		DefaultTableModel dtm = (DefaultTableModel) tableOrigem.getModel();
		TableColumnModel columnModel = tableOrigem.getColumnModel();
		
        ImageIcon editar = new ImageIcon(CadCliente.class.getResource("/Images/editar.gif"));  
        ImageIcon excluir = new ImageIcon(CadCliente.class.getResource("/Images/icon_excluir.png"));
		
        
        dtm.setRowCount(0);
        List<ClienteBean> listaCliente = new ArrayList<ClienteBean>();
 		listaCliente = Dao.ClienteDao.consultarCliente("");
 		
 		int contador = Dao.ClienteDao.contagem();
 		String dados[] = new String[3]; 
		for (ClienteBean obj : listaCliente) {
			if (!obj.getEmail().equals("")){
			dados[0] = (obj.getNome());
			dados[1] = obj.getEmail();
			
			((DefaultTableModel) tableOrigem.getModel()).addRow(dados);
			}
		} 
		tableOrigem.repaint();
	

}
	
	
	
}
