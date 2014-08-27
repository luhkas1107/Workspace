package Funcoes;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
  //
public class Wallpaper extends javax.swing.JPanel{  
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		BufferedImage b;  
        Rectangle2D rect;  
          
        public Wallpaper(){   
            try {  
                //se vocï¿½ pegar uma imagem dentro do mesmo jar/projeto  
                b = ImageIO.read(getClass().getResourceAsStream("/Images/InfotecTelaPrincipal.jpg"));                   
                
                //ou, se voce pegar uma imagem direto do sistema, use este
                
                //b = ImageIO.read(new File("C:\\Users\\Giovani\\Desktop\\Imagens\\33849.jpg"));  
                  
                //cria uma imagem do tamanho 130x130,   
                //que vai se repetir ao longo do fundo, o tamanho é voce quem escolhe.  
                rect  = new Rectangle(0,0,130,130);                                   
                  
            } catch (IOException ex) {  
                ex.printStackTrace(System.err);  
            }  
        }  
          
        @Override   
        public void paintComponent(Graphics g){    
            /* 
             * Se voce quiser que a imagem seja uma sï¿½ (extendida ao tamanho da tela, nï¿½o replicada 
             * tire os comentários da proxima linha 
             */  
              
          rect = new Rectangle(0,0,this.getWidth(),this.getHeight());  
              
              
            TexturePaint p = new TexturePaint(b,rect);  
            Graphics2D g2 = (Graphics2D) g;  
            g2.setPaint(p);  
            g2.fillRect(0,0,this.getWidth(),this.getHeight());  
   
        }  
        
        
    }  