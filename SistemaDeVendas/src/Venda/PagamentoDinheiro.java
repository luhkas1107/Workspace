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
import apresentacao.Login;


public class PagamentoDinheiro extends JDialog {
	public static String nome_func =String.valueOf(Login.nome_func);
	public static String cod_func = String.valueOf(Login.id_func);
	public static String cod_clie ;
	public static String totalpago;
	JLabel lblTrocoTotal = new JLabel("");
	public static float valortotal;
	private final JPanel contentPanel = new JPanel();
	JFormattedTextField formattedNumero = new JFormattedTextField();
	JFormattedTextField formattedData = new JFormattedTextField();
	JLabel lblTotal = new JLabel("");
	private JTextField txtValorPago;
	JLabel lblNomeCliente = new JLabel("");
	JLabel lblFunc = new JLabel("");
	public String pagamento ;
	public float valor_total;
	public float valor_pago ;
	public int id_func; 
	public int id_cliente;
	public int linhas2;
	Date dataHoje;
	Date horaHoje;
	SimpleDateFormat formataData;
	SimpleDateFormat formataHora;
	public String data;
	public String hora;
	boolean troco;
	JFormattedTextField formattedData3123 = new JFormattedTextField();
	JFormattedTextField formattedHora = new JFormattedTextField();
	
	void pegarVenda(){
		
		
		horaHoje = new Date();
		dataHoje = new Date();
		formataHora = new SimpleDateFormat("HHmmss");
		formataData = new SimpleDateFormat("ddMMyyyy");
		
		data = formataData.format(dataHoje);
		hora = formataHora.format(horaHoje);
		
		formattedData3123.setText(data);
		formattedHora.setText(hora);
		
		 String data1 = formattedData3123.getText().toString();
		 String hora1 = formattedHora.getText().toString();
		 
//		JOptionPane.showMessageDialog(null, "Hora de Hoje> " +data );
		
		NovaEntradaVenda venda = new NovaEntradaVenda();
		
		
		pagamento = "Dinheiro";
		valor_total = Float.parseFloat(lblTotal.getText());
		valor_pago = Float.parseFloat(txtValorPago.getText());
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
			linhas2=NovaEntradaVenda.linhas2;
			
			
			
			VendaDao.inserirVenda(produto);
			JOptionPane.showMessageDialog(null, "Compra efetuada com suscesso!");
			setVisible(false);

			
			venda.imprimir_dinheiro();
		

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
			PagamentoDinheiro dialog = new PagamentoDinheiro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PagamentoDinheiro() {
		setTitle("Pagamento Dinheiro");
		setBounds(100, 100, 410, 348);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		
		MaskFormatter numerocartao = new MaskFormatter();
		MaskFormatter data = new MaskFormatter();
		MaskFormatter maskhora = new MaskFormatter();
		
								
			{
				try {
					numerocartao.setMask("####.####.####.####");
					data.setMask("##/##");
					maskhora.setMask("##:##:##");
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
								
			}
		
		JLabel lblValorTotal = new JLabel("Valor Total");
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblValorTotal.setBounds(10, 176, 121, 14);
		contentPanel.add(lblValorTotal);
		
		try {
			data.setMask("##/##/####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 282, 89, 23);
		contentPanel.add(btnCancelar);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			troco();
		
			pegarVenda();
		
			
			}
		});
		btnFinalizar.setBounds(301, 282, 89, 23);
		contentPanel.add(btnFinalizar);
		
		valortotal = NovaEntradaVenda.valortotal;
		lblTotal = new JLabel(String.valueOf(valortotal));
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(42, 201, 79, 42);
		contentPanel.add(lblTotal);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 69, 56, 14);
		contentPanel.add(lblCliente);
		
		lblFunc = new JLabel(nome_func);
		lblFunc.setBounds(85, 94, 137, 14);
		contentPanel.add(lblFunc);
		
		JLabel lblFuncionario = new JLabel("Funcion\u00E1rio");
		lblFuncionario.setBounds(10, 94, 77, 14);
		contentPanel.add(lblFuncionario);
		
		JLabel lblValorPago = new JLabel("Valor Pago");
		lblValorPago.setBounds(10, 119, 77, 14);
		contentPanel.add(lblValorPago);
		
		JLabel lblTroco = new JLabel("Troco");
		lblTroco.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTroco.setBounds(305, 176, 56, 14);
		contentPanel.add(lblTroco);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 46, 400, 2);
		contentPanel.add(separator);
		
		JLabel lblPagamentoDinheiro = new JLabel("Pagamento Dinheiro");
		lblPagamentoDinheiro.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPagamentoDinheiro.setBounds(124, 11, 189, 24);
		contentPanel.add(lblPagamentoDinheiro);
		
		String nome_cliente = PesqCliente.nome_cliente;
		
//		lblNomeCliente = new JLabel(cod_clie);
		lblNomeCliente.setText(nome_cliente);
		lblNomeCliente.setBounds(85, 69, 148, 14);
		contentPanel.add(lblNomeCliente);
		
		txtValorPago = new JTextField();
		txtValorPago.setBounds(85, 116, 103, 17);
		contentPanel.add(txtValorPago);
		txtValorPago.setColumns(10);
		
		
		lblTrocoTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTrocoTotal.setForeground(new Color(154, 205, 50));
		lblTrocoTotal.setBounds(298, 201, 79, 42);
		contentPanel.add(lblTrocoTotal);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			troco();
			}
		});
		btnCalcular.setBounds(288, 115, 89, 23);
		contentPanel.add(btnCalcular);
		
		 formattedData3123 = new JFormattedTextField(data);
		formattedData3123.setBounds(334, 66, 6, 20);
		formattedData3123.setVisible(false);
		contentPanel.add(formattedData3123);
		
		formattedHora = new JFormattedTextField(maskhora);
		formattedHora.setBounds(307, 66, 6, 20);
		formattedHora.setVisible(false);
		contentPanel.add(formattedHora);
		
	}
	void troco(){
		float valorrecebido = Float.parseFloat(txtValorPago.getText());
		float valortotal =Float.parseFloat( lblTotal.getText());
		float resultado;		
		resultado=valorrecebido-valortotal;
		String resultado1 = String.valueOf(resultado);
		lblTrocoTotal.setText(resultado1);
		
		totalpago = String.valueOf(valorrecebido);
		if (valorrecebido<valortotal){
			JOptionPane.showMessageDialog(null, "Dinheiro insulficiente!");
			troco = false;
		}else{
		troco = true;	
		}
		
	}
}
