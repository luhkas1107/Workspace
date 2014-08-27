package Funcoes;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Painter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class SwingUtil {

	public static void lookNimbus(Component comp) {
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo look :looks) {
			if (look.getClassName().matches("(?i).*nimbus.*")) {
				try {
					UIManager.setLookAndFeel(look.getClassName());
					
//					 UIManager.put("control", Color.decode("#cccccc"));
					 UIManager.put("info", Color.decode("#ff9900"));
//					 UIManager.put("nimbusAlertYellow", Color.decode("#ff0000"));
//					 UIManager.put("nimbusBase", Color.decode("#4e5a66"));
//					 UIManager.put("nimbusDisabledText", Color.decode("#9900ff"));
					 UIManager.put("nimbusFocus", Color.decode("#ff9933"));
//					 UIManager.put("nimbusGreen", new Color(130, 133, 37));
//					 UIManager.put("nimbusInfoBlue", Color.decode("#9900ff"));
//					 UIManager.put("nimbusLightBackground", Color.decode("#9900ff"));
//					 UIManager.put("nimbusOrange", new Color(191, 98, 4));
//					 UIManager.put("nimbusRed", new Color(169, 46, 34));
//					 UIManager.put("nimbusSelectedText", Color.decode("#ff00ff"));
					 UIManager.put("nimbusSelectionBackground", Color.decode("#465059"));
//					 UIManager.put("text", new Color(0, 0, 0));
					 
					 UIManager.put("nimbusSelection", Color.decode("#465059"));
//					 UIManager.put("Menu.background", Color.decode("#0066ff"));
//					 UIManager.put("Menu[Enabled+Selected].backgroundPainter", Color.BLUE);
					 
					SwingUtilities.updateComponentTreeUI(comp);
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void lookWindows(Component comp) {
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo look :looks) {
			if (look.getClassName().matches("(?i).*windows.*")) {
				try {
					UIManager.setLookAndFeel(look.getClassName());
//					UIManager.put("ProgressBar.background", Color.DARK_GRAY); 
//					UIManager.put("ProgressBar.foreground", Color.DARK_GRAY);
//					UIManager.put("ProgressBar.selectionBackground",Color.DARK_GRAY); 
//					UIManager.put("ProgressBar.selectionForeground",Color.DARK_GRAY); 
//					UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
//					UIManager.put("Synthetica.window.opaque", false);
//					UIManager.put("Synthetica.window.shape", "");
//					UIManager.put("Panel.background", Color.decode("#a5ddfb"));
//					UIManager.put("RadioButton.background", Color.decode("#a5ddfb"));
//					UIManager.put("CheckBox.background", Color.decode("#a5ddfb"));
//					UIManager.put("MenuBar.background", Color.decode("#a5ddfb"));

					SwingUtilities.updateComponentTreeUI(comp);
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void lookLinux(Component comp) {
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo look :looks) {
			if (look.getClassName().matches("(?i).*linux.*")) {
				try {
					UIManager.setLookAndFeel(look.getClassName());

					SwingUtilities.updateComponentTreeUI(comp);
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
