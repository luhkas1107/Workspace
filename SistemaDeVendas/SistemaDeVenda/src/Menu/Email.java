package Menu;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Email extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String mensagem;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField textField;
	JTextPane textPane = new JTextPane();
	JLabel lblValido = new JLabel("");
	JLabel lblInvalido = new JLabel("");
	public static boolean validEmail(String email) {
	    System.out.println("Metodo de validacao de email");
	    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
	    Matcher m = p.matcher(email); 
	    if (m.find()){
//	      System.out.println("O email "+email+" e valido");
	      return true;
	    }
	    else{
//	      JOptionPane.showMessageDialog(null, "O E-mail "+email+" é inválido");
	      return false;
	    } 
	}	
	
	public static void main(String[] args) {
		try {
			Email dialog = new Email();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Email() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Email.class.getResource("/Images/Mail_add2.png")));
		setTitle("Cr\u00EDticas e Sugest\u00F5es");
		setBounds(100, 100, 706, 397);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 52, 696, 2);
		contentPanel.add(separator);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setIcon(new ImageIcon(Email.class.getResource("/Images/Mails.png")));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(0, 0, 696, 54);
		contentPanel.add(lblEmail);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(Email.class.getResource("/Images/Clipboard_1.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("");
			
			}
		});
		btnLimpar.setBounds(10, 331, 106, 23);
		contentPanel.add(btnLimpar);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setIcon(new ImageIcon(Email.class.getResource("/Images/Mail_add2.png")));
		btnEnviar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				
			if(txtNome.equals("") || textField.equals("") || validEmail(email)==false){
				JOptionPane.showMessageDialog(null, "Favor digite todos os campos corretamente.","Cuidado!!", JOptionPane.ERROR_MESSAGE);
			}else{
			pegarmsg();
			enviaremail();
			}
			}
		});
		btnEnviar.setBounds(586, 331, 100, 23);
		contentPanel.add(btnEnviar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 149, 696, 2);
		contentPanel.add(separator_1);
		
		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			
				String caractere = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ ";
				String especial = "!@#$%¨&*()<>:?~][?ºª/;.ª§-¬¢£³²¹";
				if(!caractere.contains(arg0.getKeyChar()+"")||especial.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			
			}
		});
		txtNome.setBounds(66, 65, 165, 17);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 67, 46, 14);
		contentPanel.add(lblNome);
		
		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setBounds(465, 68, 46, 14);
		contentPanel.add(lblEmail_1);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			
			String email = txtEmail.getText();
			
			if(validEmail(email)){
				lblInvalido.setVisible(false);
				lblValido.setVisible(true);
			}else{

				lblValido.setVisible(false);
				lblInvalido.setVisible(true);
				
			}
			
			}
		});
		txtEmail.setColumns(10);
		txtEmail.setBounds(521, 65, 133, 17);
		contentPanel.add(txtEmail);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 157, 676, 167);
		contentPanel.add(panel);
		panel.setLayout(null);
		
	
		textPane.setBounds(10, 11, 656, 145);
		panel.add(textPane);
		
		JLabel lblAssunto = new JLabel("Assunto");
		lblAssunto.setBounds(330, 87, 58, 14);
		contentPanel.add(lblAssunto);
		
		textField = new JTextField();
		textField.setBounds(247, 112, 209, 17);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		
		lblValido.setIcon(new ImageIcon(Email.class.getResource("/Images/check-big.png")));
		lblValido.setBounds(661, 64, 19, 23);
		lblValido.setVisible(false);
		contentPanel.add(lblValido);
		
		
		lblInvalido.setIcon(new ImageIcon(Email.class.getResource("/Images/icon_excluir.png")));
		lblInvalido.setBounds(664, 67, 16, 14);
		lblInvalido.setVisible(false);
		contentPanel.add(lblInvalido);
	}
	void pegarmsg(){
	mensagem=textPane.getText();
	}
	
	void enviaremail() {
		
		
		Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        
//        try {
//        	FileInputStream in = new FileInputStream("C:\\Backup_SistemaVenda\\223251.bak");
//    		props.load(new java.io.FileInputStream("c:\\Backup_SistemaVenda\\225456.bak"));
        
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
        
        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected javax.mail.PasswordAuthentication getPasswordAuthentication() 
                         {
                               return new javax.mail.PasswordAuthentication("lucas.microlins2013@gmail.com", "20110923sindelpaula");
                         }
                    });

        
        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {
        	  String remetente = txtEmail.getText();
        	  String nome = txtNome.getText();
        	  String assunto = textField.getText();
        	  Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress(remetente)); //Remetente
              
              Address[] toUser = InternetAddress.parse("lucas.microlins2013@gmail.com");             //Destinatário(s)
  
 
              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject("Critica e Sugestão - " +nome+ " - " +remetente+ " - " +assunto);//Assunto
              message.setText(mensagem);
//            message.setFileName("Backup do dia 17/12 ás 22:32");
              
              
              
              MimeBodyPart mbp2 = new MimeBodyPart();    
              FileDataSource fds = new FileDataSource("c:\\Backup_SistemaVenda\\225456.bak");          
              mbp2.setDataHandler(new DataHandler(fds));          
               mbp2.setFileName(fds.getName());         
   
              Multipart mp = new MimeMultipart();          
              mp.addBodyPart(mbp2);          
             
                   

               Transport.send(message);        
              
              
              
              /**Método para enviar a mensagem criada*/
              Transport.send(message);
              
              JOptionPane.showMessageDialog(null, "Mensagem enviada com suscesso!");

         } catch (MessagingException e) {
        	 
        	 JOptionPane.showMessageDialog(null, "Favor verificar conexão com a internet.");
        	 
              throw new RuntimeException(e);
              
         }


}
}