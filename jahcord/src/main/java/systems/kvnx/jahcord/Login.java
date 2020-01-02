package systems.kvnx.jahcord;

import java.awt.FontFormatException;
import java.io.IOException;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.utils.ChunkingFilter;

/**
 * 
 * @author kvnx
 *
 */

public class Login {
	
	private String token;
	private JDA jda;
	public Login (String token) {
		
		this.token = token;
		
	}
	
	/**
	 * 
	 * @return false if unsuccessful
	 */
	public boolean init() {
		
		try {
			jda = new JDABuilder(AccountType.CLIENT).setToken(token).setChunkingFilter(ChunkingFilter.NONE).build().awaitReady();
			MainInterface main = new MainInterface(jda);
			jda.addEventListener(new MessageListener(main));
			System.out.println("a");
		} catch (LoginException e) {
			e.printStackTrace();
			jda.shutdownNow();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return true;
		
	}
	
	public JDA getJDA() {
		return jda;
	}

}
