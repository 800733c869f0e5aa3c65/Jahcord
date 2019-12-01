package systems.kvnx.jahcord;

import java.io.IOException;

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
	
	private static String token;
	
	public static void main(String[] args) throws ParseException, IOException {
		
		FlatLaf theme = IntelliJTheme.createLaf(JahcordMain.class.getClassLoader().getResourceAsStream("Material Deep Ocean.theme.json"));
		LoginForm loginForm = new LoginForm(theme);
		loginForm.addWindowListener(new LoginFormWindowListener());
		
	}

}
