package Assinatura;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaDesativado extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaDesativado dialog = new TelaDesativado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaDesativado() {
		setTitle("N\u00C3O PERCA TEMPO!!!!!!!!!!!!!!!!");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 12, 418, 242);
		panel.setBackground(Color.LIGHT_GRAY);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaDesativado.class.getResource("/Images/ampulhetaRedimensionada_173_218.jpeg")));
		label.setBounds(235, 12, 173, 218);
		panel.add(label);
		
		JLabel lblNoPercaMais = new JLabel("N\u00E3o perca mais tempo!!!!!");
		lblNoPercaMais.setForeground(Color.RED);
		lblNoPercaMais.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNoPercaMais.setBounds(10, 12, 229, 41);
		panel.add(lblNoPercaMais);
		
		JEditorPane dtrpnAssineLogoEste = new JEditorPane();
		dtrpnAssineLogoEste.setBackground(Color.LIGHT_GRAY);
		dtrpnAssineLogoEste.setEditable(false);
		dtrpnAssineLogoEste.setFont(new Font("Segoe UI", Font.BOLD, 17));
		dtrpnAssineLogoEste.setText("Assine logo este software para desfrutar de todos os seus beneficios!!!");
		dtrpnAssineLogoEste.setBounds(10, 85, 215, 98);
		panel.add(dtrpnAssineLogoEste);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setModal(false);
			setVisible(false);
			
			}
		});
		btnVoltar.setBounds(62, 207, 95, 23);
		panel.add(btnVoltar);
	}
}
