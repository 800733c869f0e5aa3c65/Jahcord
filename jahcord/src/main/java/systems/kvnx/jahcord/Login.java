package systems.kvnx.jahcord;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Login {
	
	private String token;
	public Login (String token) {
		
		this.token = token;
		
	}
	
	/**
	 * 
	 * @return false if unsuccessful
	 */
	public boolean init() {
		
		try {
			JDA jda = new JDABuilder(AccountType.CLIENT).setToken(token).addEventListeners(new MessageListener()).build().awaitReady();
		} catch (LoginException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return true;
		
	}

}
