package Backup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.JSpinner;

import Conexao.exception;
import Dao.BackupDao;
import java.awt.Toolkit;

public class RestaurarBackup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCaminho;

	
	Date dataHoje;
	SimpleDateFormat formataData;	
	File arquivo, dir; 
	String caminho, caminhoImagem;
	String nomeArquivo = "";
	String extensaoArquivo =".bak";
	
	String arquivo2 =null;
	
	public static void main(String[] args) {
		try {
			RestaurarBackup dialog = new RestaurarBackup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RestaurarBackup() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RestaurarBackup.class.getResource("/Images/data_restore.png")));
		setTitle("Restaurar Backup");
		setBounds(100, 100, 450, 212);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		JButton btnSalvar = new JButton("Restaurar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String text = txtCaminho.getText();
				caminho = Class.class.getResource("/").toString();	
            	caminho = text.replace("/","\\\\");
            	caminho = text.replace("file:\\\\","");
				caminho = text.replace("C:","c:");
				

        		System.out.println(arquivo2);
        		System.out.println(caminho);
        		
            		
        					
 						try {
 							boolean resultado;
							resultado=BackupDao.backup_restore(caminho);
							JOptionPane.showMessageDialog(null, "Backup restaurado com suscesso!");
//	        				
						} catch (exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e.getMessage());
							e.printStackTrace();
						}
        					
        					
        					
        					

			
			}
		});
		btnSalvar.setBounds(10, 145, 104, 23);
		contentPanel.add(btnSalvar);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser arquivo = new JFileChooser();
				
				arquivo2 =null;
				
				arquivo.setVisible(true);
				
				
				int result = arquivo.showOpenDialog(null);
				
			if(result == JFileChooser.APPROVE_OPTION){
            		arquivo2 = arquivo.getSelectedFile().toString();

            		txtCaminho.setText(arquivo2);
            	
            		
            		File file = new File(arquivo2);
            		System.out.println(arquivo2);
            		System.out.println(file);


            	}
			
			}
		});
		btnProcurar.setBounds(341, 89, 89, 23);
		contentPanel.add(btnProcurar);
		 
		txtCaminho = new JTextField();
		txtCaminho.setBounds(65, 90, 266, 20);
		contentPanel.add(txtCaminho);
		
		txtCaminho.setColumns(10);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(10, 93, 56, 14);
		contentPanel.add(lblDestino);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 54, 440, 2);
		contentPanel.add(separator);
		
		JLabel lblBackup = new JLabel("Backup");
		lblBackup.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackup.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBackup.setBounds(0, 11, 440, 32);
		contentPanel.add(lblBackup);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			
			}
		});
		btnVoltar.setBounds(341, 145, 89, 23);
		contentPanel.add(btnVoltar);
	}
}
