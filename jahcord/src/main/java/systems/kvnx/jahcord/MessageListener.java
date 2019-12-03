package systems.kvnx.jahcord;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * 
 * @author kvnx
 * This class displays chat and accesses fields from Login's main interface.
 *
 */

public class MessageListener extends ListenerAdapter {
	
	private MainInterface main;
	
	public MessageListener(MainInterface main) {
		this.main = main;
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		if (event.isFromType(ChannelType.TEXT)) {
			String text = String.format("[%s][%s] %#s: %s%n", event.getGuild().getName(), event.getChannel().getName(),
					event.getAuthor(), event.getMessage().getContentDisplay());
			System.out.println(text);
			main.chat.setText(main.chat.getText() + text);
		} else {
			String text = String.format("[PM] %#s: %s%n", event.getAuthor(), event.getMessage().getContentDisplay());
			System.out.println(text);
			main.chat.setText(main.chat.getText() + text);
		}
		
	}
	
}
