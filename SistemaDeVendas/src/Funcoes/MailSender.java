package Funcoes;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import Beans.SmtpMail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
 
public class MailSender {
    //cria as propriedades necessárias para o envio de email
    public void senderSmtpMail(final SmtpMail mail) throws UnsupportedEncodingException, MessagingException {
        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.host", mail.getHost());
        if(mail.getAutenticacao())
            prop.put("mail.smtp.auth", "true");    
        else
            prop.put("mail.smtp.auth", "false");
 
        prop.put("mail.smtp.port", mail.getPorta().toString());
 
        if(mail.getConexaoSegura())
            prop.put("mail.smtp.starttls.enable", "true");    
        else
            prop.put("mail.smtp.starttls.enable", "false");
 
        prop.put("mail.smtp.socketFactory.port", mail.getPorta().toString());    
        prop.put("mail.smtp.socketFactory.fallback", "false");    
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
      
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(mail.getUsuario(), mail.getSenha());
            }
        };
 
        Session session = Session.getInstance(prop, auth);
        //caso queira habilitar o modo de Debug
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mail.getEmailRemetente(), mail.getNomeRemetente()));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getEmailDestinatario(), mail.getNomeDestinatario()));
        message.setSubject(mail.getAssunto());
        
        // ---
        
        MimeMultipart mimeMultipart = new MimeMultipart("mixed");
        MimeMultipart mimeMultipartContent = new MimeMultipart("alternative");
        MimeBodyPart contentPartRoot = new MimeBodyPart();  
        contentPartRoot.setContent(mimeMultipartContent);  
        mimeMultipart.addBodyPart(contentPartRoot);
         
        //enviando o texto
        MimeBodyPart mbp1 = new MimeBodyPart();
        mbp1.setText(mail.getCorpo());
        mimeMultipartContent.addBodyPart(mbp1);
         
        //enviando o anexo
        if(mail.getAnexo() != null && mail.getAnexo().exists() && mail.getAnexo().isFile()){
            MimeBodyPart mbp3 = new MimeBodyPart();  
            DataSource fds = new FileDataSource(mail.getAnexo());
            mbp3.setDisposition(Part.ATTACHMENT);
            mbp3.setDataHandler(new DataHandler(fds));
            mbp3.setFileName(mail.getAnexo().getName());
            mimeMultipart.addBodyPart(mbp3);
        }
                 
        message.setContent(mimeMultipart);
        message.saveChanges();
 
        Transport.send(message);
    }
}