package Venda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Beans.VendaBean;
import Conexao.exception;
import Dao.VendaDao;

public class PagamentoCartao extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	public static String pagamento;

	public static float valor_total;
	public static float valor_pago;
	public static int id_func;
	public static int id_cliente ;
	
	JLabel lblTotalPago = new JLabel("");
	public static String total_pago = NovaEntradaVenda.lblValorTotal.getText();
	
	JFormattedTextField formattedNumero= new JFormattedTextField();
	JFormattedTextField formattedData = new JFormattedTextField();
	JFormattedTextField formattedData2 = new JFormattedTextField();
	JFormattedTextField formattedHora2 = new JFormattedTextField();
	
	Date dataHoje;
	Date horaHoje;
	SimpleDateFormat formataData;
	SimpleDateFormat formataHora;
	public String data;
	public String hora;

void pegarVenda(){
		
		
		horaHoje = new Date();
		dataHoje = new Date();
		formataHora = new SimpleDateFormat("HHmmss");
		formataData = new SimpleDateFormat("ddMMyyyy");
		
		data = formataData.format(dataHoje);
		hora = formataHora.format(horaHoje);
		
		formattedData2.setText(data);
		formattedHora2.setText(hora);
		
		 String data1 = formattedData2.getText().toString();
		 String hora1 = formattedHora2.getText().toString();
		 
//		JOptionPane.showMessageDialog(null, "Hora de Hoje> " +data );
		
		NovaEntradaVenda venda = new NovaEntradaVenda();
		
		
		pagamento = "Cartao";
		valor_total = venda.valortotal;
		valor_pago = valor_total;
		id_func = Integer.parseInt(venda.cod_func);
		id_cliente =Integer.parseInt(venda.id_cliente);
		
		
boolean verificacao;
		if(valor_pago<valor_total){
			verificacao = false;
//				JOptionPane.showMessageDialog(null, "Dinheiro Insulficiente.");
		}else{
			verificacao = true;
		
		}
		VendaBean produto = new VendaBean();
		produto.setForma_pagamento(pagamento);
		produto.setId_cliente(id_cliente);
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
			JOptionPane.showMessageDialog(null, "Compra efetuada com suscesso!");
			setVisible(false);

			
//			venda.imprimir_dinheiro();
		

		}
		
		 catch (exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro! Por favor verifique todos os campos, caso ao contrario, contate ao administrador.");
			
		}
		
		}
	
		
		
		
		
		
	}

	
	
	public static void main(String[] args) {
		try {
			PagamentoCartao dialog = new PagamentoCartao();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PagamentoCartao() {
		setTitle("Pagamento Cart\u00E3o");
		setBounds(100, 100, 451, 208);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		
		MaskFormatter numerocartao = new MaskFormatter();
		MaskFormatter data = new MaskFormatter();
		MaskFormatter data2 = new MaskFormatter();
		MaskFormatter hora = new MaskFormatter();						
			{
				try {
					numerocartao.setMask("####.####.####.####");
					data.setMask("##/##");
					data2.setMask("##/##/####");
					hora.setMask("##:##:##");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
								
			}
		
		formattedNumero= new JFormattedTextField(numerocartao);
		formattedNumero.setBounds(124, 65, 307, 17);
		contentPanel.add(formattedNumero);
		
		formattedData2 = new JFormattedTextField(data2);
		formattedData2.setBounds(385, 15, 6, 20);
		formattedData2.setVisible(false);
		contentPanel.add(formattedData2);
		

		formattedHora2 = new JFormattedTextField(hora);
		formattedHora2.setBounds(369, 15, 6, 20);
		formattedHora2.setVisible(false);
		contentPanel.add(formattedHora2);
		

		
		formattedData = new JFormattedTextField(data);
		formattedData.setBounds(124, 88, 45, 17);
		contentPanel.add(formattedData);
		
		textField = new JTextField();
		textField.setBounds(124, 114, 45, 17);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNumeroCarto = new JLabel("Numero Cart\u00E3o");
		lblNumeroCarto.setBounds(10, 67, 104, 14);
		contentPanel.add(lblNumeroCarto);
		
		JLabel lblDataVenc = new JLabel("Data Venc.");
		lblDataVenc.setBounds(10, 90, 81, 14);
		contentPanel.add(lblDataVenc);
		
		JLabel lblCodSegurana = new JLabel("Cod. Seguran\u00E7a");
		lblCodSegurana.setBounds(10, 116, 104, 15);
		contentPanel.add(lblCodSegurana);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 142, 89, 23);
		contentPanel.add(btnCancelar);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ARMAZENAR NA DAO
				
				
			pegarVenda();
			
			
			}
		});
		btnFinalizar.setBounds(342, 142, 89, 23);
		contentPanel.add(btnFinalizar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 46, 441, 2);
		contentPanel.add(separator);
		
		JLabel lblPagamentoCarto = new JLabel("Pagamento Cart\u00E3o");
		lblPagamentoCarto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPagamentoCarto.setBounds(139, 11, 176, 24);
		contentPanel.add(lblPagamentoCarto);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(269, 101, 46, 17);
		contentPanel.add(lblTotal);
		
		
		
		lblTotalPago.setText(total_pago);
		lblTotalPago.setForeground(new Color(154, 205, 50));
		lblTotalPago.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalPago.setBounds(342, 104, 89, 14);
		contentPanel.add(lblTotalPago);
		
	}
}
