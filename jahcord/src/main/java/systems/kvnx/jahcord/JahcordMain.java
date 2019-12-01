package systems.kvnx.jahcord;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.json.ParseException;

/**
 * 
 * @author kvnx
 * This class handles logging in
 *
 */

public class JahcordMain {

	public static void main(String[] args) throws ParseException, IOException {

		FlatLaf theme = IntelliJTheme.createLaf(JahcordMain.class.getClassLoader().getResourceAsStream("Dracula.theme.json"));

		try {
			UIManager.setLookAndFeel(theme);
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
