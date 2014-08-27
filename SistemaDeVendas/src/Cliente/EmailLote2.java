package Cliente;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;

import org.apache.tools.ant.taskdefs.condition.IsSet;

public class EmailLote2 extends JDialog {
	JLabel lblTotalEmail = new JLabel("");
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	public static int num_email = EmailLote1.num_email;
	public static String remetente[] = EmailLote1.email;
	public String nome[] = new String[num_email];
	public String assunto;
	public String corpo;
	JTextArea textField = new JTextArea();
	boolean html;
	ButtonGroup grupo = new ButtonGroup();
	JRadioButton rdbtnTexto = new JRadioButton("Texto");
	JRadioButton rdbtnHtml = new JRadioButton("HTML");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmailLote2 dialog = new EmailLote2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EmailLote2() {
		setTitle("E-mail em Lote (Cliente)");
		setBounds(100, 100, 570, 451);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnConfirmar = new JButton("Enviar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				enviar();

			}
		});
		btnConfirmar.setBounds(455, 379, 89, 23);
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

		JLabel lblAssunto = new JLabel("Assunto");
		lblAssunto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssunto.setBounds(0, 72, 554, 14);
		contentPanel.add(lblAssunto);

		textField_1 = new JTextField();
		textField_1.setBounds(190, 97, 179, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(455, 97, 46, 14);
		contentPanel.add(lblTotal);

		num_email = EmailLote1.num_email;
		lblTotalEmail = new JLabel("" + num_email);
		lblTotalEmail.setBounds(498, 97, 46, 14);
		contentPanel.add(lblTotalEmail);
		
		textField = new JTextArea();
		textField.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "E-mail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField.setBounds(10, 129, 534, 238);
		contentPanel.add(textField);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 127, 534, 241);
		contentPanel.add(panel);
		
		rdbtnHtml = new JRadioButton("HTML");
		rdbtnHtml.setBounds(10, 96, 66, 23);
		contentPanel.add(rdbtnHtml);
		
		rdbtnTexto = new JRadioButton("Texto");
		rdbtnTexto.setSelected(true);
		rdbtnTexto.setBounds(105, 96, 77, 23);
		contentPanel.add(rdbtnTexto);
		
		grupo.add(rdbtnTexto);
		grupo.add(rdbtnHtml);
	}

	public void conferir() {

		System.out.println("\n\n\nNúmero de informações recebidas: "
				+ num_email);
		EmailLote1 email = new EmailLote1();
		System.out.println("-------------------- CONFERIR DADOS -------------------- ");
//		int x = 0;
//		while (x<=num_email){
		for (int x = 0; x <= num_email; x++) {
			System.out.println("Qtdade e-mail: " +num_email);
			System.out.println(EmailLote1.email[x]);
		x++;
		}
		
		System.out.println("-------------------- ENCERRANDO -------------------- ");

	}

	public void enviar() {

		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");


		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(
								"lucas.microlins2013@gmail.com",
								"20110923sindelpaula");
					}
				});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {
			int x=0;
			EmailLote1 email = new EmailLote1();
			
			while(x<=num_email){
			
			String remetente = "teste@teste.com.br";
			String assunto = textField_1.getText();
			corpo = textField.getText();
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remetente)); //Remetente

			Address[] toUser = InternetAddress
					.parse(email.email[x]); // Destinatário(s)

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assunto);// Assunto
			if (rdbtnHtml.isSelected()){
			message.setContent(corpo, "text/html");
			System.out.println("Texto em HTML");
			}else{
			message.setText(corpo);
			System.out.println("Texto normal.");
			}
			MimeBodyPart mbp2 = new MimeBodyPart();
			
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp2);

			/** Método para enviar a mensagem criada */
			Transport.send(message);
			x++;
			JOptionPane.showMessageDialog(null,
					"Mensagem enviada com suscesso!");
			}
		} catch (MessagingException e) {

			JOptionPane.showMessageDialog(null,
					"Favor verificar conexão com a internet.");

			throw new RuntimeException(e);

		
		}
	}
}
