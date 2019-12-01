package systems.kvnx.jahcord;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.json.ParseException;

/**
 * 
 * @author kvnx
 * This class handles logging in and redirecting to the client
 *
 */

public class JahcordMain {
	
	private static String token;
	
	public static void main(String[] args) throws ParseException, IOException {
		
		
		FlatLaf theme = IntelliJTheme.createLaf(JahcordMain.class.getClassLoader().getResourceAsStream("Material Oceanic.theme.json"));
		LoginForm loginForm = new LoginForm(theme);
		loginForm.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {
				System.out.println("Login form closed.");
				token = loginForm.getToken();
				Login login = new Login(token);
			}

			@Override
			public void windowClosing(WindowEvent arg0) {}
			@Override
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			@Override
			public void windowIconified(WindowEvent arg0) {} 
			@Override
			public void windowOpened(WindowEvent arg0) {
				System.out.println("Login form opened.");
			}
			
		});
		
		
	}

}
