package Funcionario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import Beans.FuncionarioBean;
import Beans.RelatorioBean;
import Conexao.exception;
import Dao.RelatorioDao;

public class FuncionarioMes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JLabel lblImagem = new JLabel("");
	JLabel lblNomeFunc = new JLabel("");
	JLabel lblCpfFunc = new JLabel("");
	JLabel lblQtdVenda = new JLabel("");
	int id_func;
	JLabel lblR = new JLabel("R$");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FuncionarioMes dialog = new FuncionarioMes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FuncionarioMes() {
		setTitle("Funcion\u00E1rio do M\u00EAs");
		setBounds(100, 100, 395, 481);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		JLabel lblParabns = new JLabel("Funcion\u00E1rio do M\u00EAs");
		lblParabns.setHorizontalAlignment(SwingConstants.CENTER);
		lblParabns.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblParabns.setBounds(0, 21, 385, 30);
		contentPanel.add(lblParabns);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(0, 167, 385, 17);
		contentPanel.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCpf.setBounds(0, 220, 385, 17);
		contentPanel.add(lblCpf);
		lblNomeFunc.setForeground(new Color(75, 0, 130));
		lblNomeFunc.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		
		
		lblNomeFunc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeFunc.setBounds(0, 195, 385, 14);
		contentPanel.add(lblNomeFunc);
		lblCpfFunc.setForeground(new Color(75, 0, 130));
		lblCpfFunc.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		
		
		lblCpfFunc.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpfFunc.setBounds(0, 248, 385, 14);
		contentPanel.add(lblCpfFunc);
		
		JLabel lblNmeroDeVendas = new JLabel("N\u00FAmero de Vendas");
		lblNmeroDeVendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNmeroDeVendas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNmeroDeVendas.setBounds(0, 273, 385, 17);
		contentPanel.add(lblNmeroDeVendas);
		
		
		lblQtdVenda.setForeground(Color.RED);
		lblQtdVenda.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblQtdVenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdVenda.setBounds(0, 301, 385, 14);
		contentPanel.add(lblQtdVenda);
		
		JLabel lblBnus = new JLabel("B\u00F4nus");
		lblBnus.setHorizontalAlignment(SwingConstants.CENTER);
		lblBnus.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBnus.setBounds(0, 326, 385, 17);
		contentPanel.add(lblBnus);
		
		
		lblR.setForeground(new Color(30, 144, 255));
		lblR.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblR.setHorizontalAlignment(SwingConstants.CENTER);
		lblR.setBounds(125, 354, 139, 49);
		contentPanel.add(lblR);
		
		JButton btnGerarFolha = new JButton("Gerar Folha");
		btnGerarFolha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			

				try{  
	                int id = id_func;
	                List<FuncionarioBean> objFunc = new ArrayList<FuncionarioBean>();
	                objFunc = Dao.RelatorioDao.gerarPagamento(id);
	                
	                JRDataSource jrDataSource = new JRBeanCollectionDataSource(objFunc);
	                HashMap<String, Object> map = new HashMap<String, Object>();  
	                String FileJasper = "Relatorios/RelatorioVenda2.jasper";  
	                JasperPrint jpReport = JasperFillManager.fillReport(FileJasper, map, jrDataSource);  
	                JasperViewer.viewReport(jpReport, false);
	                
	            }catch(Exception errorOpenFile) {  
	                JOptionPane.showMessageDialog(null,"Não foi possível exibir o relatório!"  
	                        + '\n' + "Verifique se o arquivo .jasper"  
	                        + '\n' + "encontra-se no diretório:"  
	                        + '\n' + "<drive> : C:\\Users\\Luhkas\\Documents\\Relatorio_Java\\Relatorios_InfoTec\\RelatorioVenda2.jasper!","JERP - Atenção!",JOptionPane.ERROR_MESSAGE);  
	            }
			
			
			}
		});
		btnGerarFolha.setBounds(271, 415, 104, 23);
		contentPanel.add(btnGerarFolha);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			
			}
		});
		btnVoltar.setBounds(10, 415, 89, 23);
		contentPanel.add(btnVoltar);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(146, 71, 89, 85);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		
		lblImagem.setBounds(0, 0, 89, 85);
		panel.add(lblImagem);
		
		RelatorioBean obj = new RelatorioBean();
		
		try {
			obj = RelatorioDao.funcionarioMesPt2();
			int quantidade_venda = obj.qtd_vendas;
				
			if (quantidade_venda>=3 && quantidade_venda<7){
				lblR.setText("300.00");
			}else if(quantidade_venda>=7 && quantidade_venda<11){
				lblR.setText("600.00");
			}
			else if(quantidade_venda>=12){
			lblR.setText("1000.00");
			}else{
				lblR.setText("100.00");	
			}
			
			
			String quantidade_venda1 = String.valueOf(quantidade_venda);
			lblQtdVenda.setText(quantidade_venda1);
			obj = RelatorioDao.funcionarioMesPt1();
			String nome = obj.getNome_func();
			lblNomeFunc.setText(nome);
			String cpf = obj.getCpf_func();
			lblCpfFunc.setText(cpf);
			String caminho_imagem = obj.getCaminho_imagem();
			id_func = obj.getId_func();
//			JOptionPane.showMessageDialog(null, caminho_imagem);
			if(!caminho_imagem.equals("")){
			ImageIcon  imagem = new ImageIcon(caminho_imagem);
     	    imagem.setImage(imagem.getImage().getScaledInstance(89,85,100));
     	    lblImagem.setIcon(imagem);
			}else{
				JOptionPane.showMessageDialog(null, "Usuário não possue foto, favor cadastrar o quanto antes.");
			}
		} catch (exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
	}
}
