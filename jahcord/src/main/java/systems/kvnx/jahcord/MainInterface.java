package systems.kvnx.jahcord;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;

public class MainInterface extends JFrame {

	private JPanel contentPane;
	
	private JDA client;
	private JTextField input;
	
	public JTextArea chat;
	public Guild guild;
	public TextChannel channel;
	private JMenuBar menu;

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public MainInterface(JDA client) throws FontFormatException, IOException {
		
		this.client = client;
		this.guild = client.getGuilds().get(0);
		this.channel = guild.getTextChannels().get(0);
		
		setTitle("Jahcord | " + client.getSelfUser().getAsTag());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		
		InputStream is = LoginForm.class.getClassLoader().getResourceAsStream("FiraCode-Regular.ttf");
		Font fira = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(12f);
		
		JButton btnSend = new JButton("Send");
		btnSend.setFont(fira);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				channel.sendMessage(input.getText()).queue();
				input.setText("");
			}
		});
		
		input = new JTextField();
		input.setFont(fira);
		input.setColumns(10);
		
		chat = new JTextArea();
		chat.setLineWrap(true);
		chat.setEditable(false);
		chat.setFont(fira);
		JScrollPane scroll = new JScrollPane(chat);
		List<Message> mh = channel.getHistory().retrievePast(10).complete();
		for (int i = mh.size() - 1; i >= 0; i--) {
			
			Message m = mh.get(i);
			String text = String.format("%#s: %s%n", m.getAuthor(), m.getContentDisplay());
			chat.append(text);
				
		}
		
		JLabel lblHeader = new JLabel("[" + guild.getName() + "] #" + channel.getName() + ((channel.getTopic() != null) ? " | " + channel.getTopic() : ""));
		lblHeader.setFont(fira);
		
		menu = new JMenuBar();
		menu.setFont(fira);
		setJMenuBar(menu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JMenu channelMenu = new JMenu("Channel");
		menu.add(channelMenu);
		for (TextChannel x : guild.getTextChannels()) {
			
			JMenuItem channelOption = new JMenuItem(x.getName());
			channelOption.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					chat.setText("");
					
					channel = x;
					System.out.println("Channel changed to " + x.getName());
					lblHeader.setText("[" + guild.getName() + "] #" + channel.getName() + ((channel.getTopic() != null) ? " | " + channel.getTopic() : ""));
					
					if (!channel.canTalk()) {
						
						input.setEnabled(false);
						input.setText("No permission for this channel");
						
					} else {
						
						input.setText("");
						input.setEnabled(true);
						
					}
					
					List<Message> mh = channel.getHistory().retrievePast(10).complete();
					for (int i = mh.size() - 1; i >= 0; i--) {
						
						Message m = mh.get(i);
						String text = String.format("%#s: %s%n", m.getAuthor(), m.getContentDisplay());
						chat.append(text);
							
					}
					
				}
				
			});
			channelMenu.add(channelOption);
			
		}
		
		JMenu serverMenu = new JMenu("Server");
		menu.add(serverMenu);
		for (Guild x : client.getGuilds()) {
			
			JMenuItem guildOption = new JMenuItem(x.getName());
			guildOption.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					chat.setText("");
					
					guild = x;
					channel = guild.getTextChannels().get(0);
					System.out.println("Channel changed to " + x.getName());
					System.out.println("Guild changed to " + x.getName());
					// change header text
					lblHeader.setText("[" + guild.getName() + "] #" + channel.getName() + ((channel.getTopic() != null) ? " | " + channel.getTopic() : ""));
					// reset the channel list on the menu
					channelMenu.removeAll();
					for (TextChannel x : guild.getTextChannels()) {
						
						JMenuItem channelOption = new JMenuItem(x.getName());
						channelOption.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								chat.setText("");
								
								channel = x;
								System.out.println("Channel changed to " + x.getName());
								lblHeader.setText("[" + guild.getName() + "] #" + channel.getName() + " | " + channel.getTopic());
								
								if (!channel.canTalk()) {
									
									input.setEnabled(false);
									input.setText("No permission for this channel");
									
								} else {
									
									input.setText("");
									input.setEnabled(true);
									
								}
								
								List<Message> mh = channel.getHistory().retrievePast(10).complete();
								for (int i = mh.size() - 1; i >= 0; i--) {
									
									Message m = mh.get(i);
									String text = String.format("%#s: %s%n", m.getAuthor(), m.getContentDisplay());
									chat.append(text);
										
								}
								
							}
							
						});
						channelMenu.add(channelOption);
						
					}
					
					if (!channel.canTalk()) {
						
						input.setEnabled(false);
						input.setText("No permission for this channel");
						
					} else {
						
						input.setText("");
						input.setEnabled(true);
						
					}
					
					List<Message> mh = channel.getHistory().retrievePast(10).complete();
					for (int i = mh.size() - 1; i >= 0; i--) {
						
						Message m = mh.get(i);
						String text = String.format("%#s: %s%n", m.getAuthor(), m.getContentDisplay());
						chat.append(text);
							
					}
					
				}
				
			});
			serverMenu.add(guildOption);
			
		}
		
		GroupLayout layout = new GroupLayout(contentPane);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(input, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSend))
				.addComponent(scroll, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
				.addComponent(lblHeader, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblHeader)
					.addGap(8)
					.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSend)
						.addComponent(input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(layout);
		
		setVisible(true);
		
	}
}
