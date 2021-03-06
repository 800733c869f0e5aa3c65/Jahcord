package systems.kvnx.jahcord;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author kvnx
 *
 */

public class LoginForm extends JFrame {

	private JPanel content;
	private JTextField textFieldToken;

	private String token;

	public LoginForm() throws FontFormatException, IOException {

		setTitle("Login - Jahcord");
		ImageIcon icon = new ImageIcon(MainInterface.class.getClassLoader().getResource("jahcord_icon.png"));
		setIconImage(icon.getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 150);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		
		InputStream is = LoginForm.class.getClassLoader().getResourceAsStream("FiraCode-Regular.ttf");
		Font fira = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(12f);

		JLabel lblToken = new JLabel("Token");
		lblToken.setFont(fira);

		textFieldToken = new JTextField();
		textFieldToken.setColumns(10);
		textFieldToken.setFont(fira);

		JButton btnLogin = new JButton("Log in");
		btnLogin.setFont(fira);
		GroupLayout gl_content = new GroupLayout(content);
		gl_content.setHorizontalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_content.createSequentialGroup()
							.addComponent(textFieldToken, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_content.createSequentialGroup()
							.addGroup(gl_content.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_content.createSequentialGroup()
									.addGap(188)
									.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(187))
								.addComponent(lblToken, Alignment.LEADING))
							.addGap(14))))
		);
		gl_content.setVerticalGroup(
			gl_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_content.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblToken)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldToken, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(7))
		);
		content.setLayout(gl_content);

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				token = textFieldToken.getText().trim();
				if (token.matches("[MN][A-Za-z\\d]{23}\\.[\\w-]{6}\\.[\\w-]{27}")) {
					setVisible(false);
					Login login = new Login(token);
					if (!login.init()) {
						login = null;
						JOptionPane.showMessageDialog(null, "Invalid token.", "Error", JOptionPane.PLAIN_MESSAGE);
						setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Invalid token format.", "Error", JOptionPane.PLAIN_MESSAGE);
				}
			}

		});

		setVisible(true);

	}

	public String getToken() {
		return token;
	}

}
