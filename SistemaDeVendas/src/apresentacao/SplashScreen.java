package apresentacao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SplashScreen extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar progressBar = new JProgressBar();
	JLabel lblIniciandoAplicao = new JLabel("");
	JLabel lblConectandoComO = new JLabel("");
	JLabel lblAtualizandoDadosDo = new JLabel("");
	JLabel lblPreparandoParaIniciar = new JLabel("");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public SplashScreen() {
        
		
		int width = 450;
        int height =300;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setBackground(new Color(51, 153, 225));
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.LIGHT_GRAY);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel.add(progressBar);
		
		
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setToolTipText("");
		lblLogo.setBounds(10, 28, 430, 188);
		lblLogo.setIcon(new ImageIcon(SplashScreen.class.getResource("/Images/InfotecSplash.jpg")));
		panel.add(lblLogo);
		setContentPane(contentPane);
		
		JLabel lblVersion = new JLabel("Vers\u00E3o 0.3.4");
		lblVersion.setForeground(SystemColor.activeCaptionBorder);
		lblVersion.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblVersion.setBounds(10, 243, 101, 23);
		panel.add(lblVersion);
		
		JLabel lblTodosOsDireitos = new JLabel("Todos os Direitos Reservados \u00A9 2011-2013 \u00A9");
		lblTodosOsDireitos.setForeground(SystemColor.activeCaptionBorder);
		lblTodosOsDireitos.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblTodosOsDireitos.setBounds(132, 243, 323, 23);
		panel.add(lblTodosOsDireitos);
		
		
		lblIniciandoAplicao.setForeground(SystemColor.activeCaptionBorder);
		lblIniciandoAplicao.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblIniciandoAplicao.setBounds(10, 209, 287, 23);
		panel.add(lblIniciandoAplicao);
		
		
		lblConectandoComO.setForeground(SystemColor.activeCaptionBorder);
		lblConectandoComO.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblConectandoComO.setBounds(10, 209, 316, 23);
		panel.add(lblConectandoComO);
		
		
		lblAtualizandoDadosDo.setForeground(SystemColor.activeCaptionBorder);
		lblAtualizandoDadosDo.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblAtualizandoDadosDo.setBounds(10, 209, 316, 23);
		panel.add(lblAtualizandoDadosDo);
		
		
		lblPreparandoParaIniciar.setForeground(SystemColor.activeCaptionBorder);
		lblPreparandoParaIniciar.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblPreparandoParaIniciar.setBounds(10, 209, 345, 23);
		panel.add(lblPreparandoParaIniciar);
		
		JLabel lblClose = new JLabel("");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		//lblClose.setIcon(new ImageIcon(SplashScreen.class.getResource("/br/com/images/close.png")));
	//	lblClose.setBackground(new Color(240, 240, 240));
	//	lblClose.setBounds(415, 11, 25, 25);
//		panel.add(lblClose);
		
		
	}

	public void initSplash(){
		this.setVisible(true);
	}
	
	public void setProgresso(int i) {
		progressBar.setValue(i);
		progressBar.setBorderPainted(false);
		progressBar.setString("Carregando...");
		progressBar.setMinimum(0);
		progressBar.setMaximum(500000);
		progressBar.setIndeterminate(false);
		progressBar.setBackground(Color.white);
		progressBar.setForeground(Color.gray);
		progressBar.setFont(new Font("Verdana", Font.PLAIN, 11));
		progressBar.setStringPainted(true);
		progressBar.setBounds(0, 264, 444, 29);		
Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

	}
	
	public void  fechaSplash() {
		this.dispose();
	}
}