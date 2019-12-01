package systems.kvnx.jahcord;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.json.ParseException;

/**
 * 
 * @author kvnx
 * This class handles logging in and loading configurations
 *
 */

public class JahcordMain {

	public static void main(String[] args) throws ParseException, IOException, FontFormatException {
		
		String themeName = "/Material Lighter.theme.json";
		
		InputStream file = JahcordMain.class.getResourceAsStream("/config.txt");
		if (file != null) {
			Scanner cfg = new Scanner(JahcordMain.class.getResourceAsStream("/config.txt"));
			themeName = cfg.nextLine().trim();
			cfg.close();
		}

		FlatLaf theme = IntelliJTheme.createLaf(JahcordMain.class.getResourceAsStream(themeName));
		InputStream is = JahcordMain.class.getResourceAsStream("/FiraCode-Regular.ttf");
		Font fira = Font.createFont(Font.TRUETYPE_FONT, is);

		try {
			UIManager.setLookAndFeel(theme);
			UIManager.put("OptionPane.messageFont", fira.deriveFont(12f));
			UIManager.put("OptionPane.buttonFont", fira.deriveFont(12f));
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		// check internet connection by trying to connect to discord.
		try {
			URL url = new URL("https://discordapp.com/api");
			URLConnection connection = url.openConnection();
			connection.connect();
			System.out.println("Internet is connected");
		} catch (MalformedURLException e) {
			System.out.println("Internet is not connected");
		} catch (IOException e) {
			System.out.println("Internet is not connected");
			JOptionPane.showMessageDialog(null, "Check internet connection.", "Error", JOptionPane.PLAIN_MESSAGE);
			System.exit(1);
		}
		
		LoginForm loginForm = new LoginForm();
		loginForm.addWindowListener(new LoginFormWindowListener());

	}

}
