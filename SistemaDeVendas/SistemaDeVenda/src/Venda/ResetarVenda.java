package Venda;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ResetarVenda extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetarVenda frame = new ResetarVenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ResetarVenda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		
		JLabel lblDesejarIniciarUma = new JLabel("Desejar iniciar uma nova venda com o mesmo cliente?");
		lblDesejarIniciarUma.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesejarIniciarUma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDesejarIniciarUma.setBounds(0, 11, 379, 17);
		contentPane.add(lblDesejarIniciarUma);
		
		JButton btnSim = new JButton("Sim");
		btnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NovaEntradaVenda venda = new NovaEntradaVenda();
				
				String id_cliente = venda.lblIdCliente.getText().toString();
				String id_func = venda.label_3.getText().toString();
				
				venda.setVisible(false);
				venda.setVisible(true);
				venda.lblIdCliente.setText(id_cliente);
				venda.label_3.setText(id_func);
				setVisible(false);
				
			}
		});
		btnSim.setBounds(10, 57, 89, 23);
		contentPane.add(btnSim);
		
		JButton btnNo = new JButton("N\u00E3o");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			
			}
		});
		btnNo.setBounds(283, 57, 89, 23);
		contentPane.add(btnNo);
	}

}
