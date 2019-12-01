package systems.kvnx.jahcord;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LoginFormWindowListener implements WindowListener {
	
	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {
		System.out.println("Login form closed.");
		System.exit(1);
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

}
