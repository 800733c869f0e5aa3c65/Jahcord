package systems.kvnx.jahcord;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * 
 * @author kvnx
 * This class handles logging in and redirecting to the client
 *
 */

public class JahcordMain {
	
	static String token;
	
	public static void main(String[] args) {
		
		FlatLaf theme = new FlatLightLaf();
		
		LoginForm loginForm = new LoginForm(theme);
		loginForm.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {
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
			public void windowOpened(WindowEvent arg0) {}
			
		});
		
		
	}

}
