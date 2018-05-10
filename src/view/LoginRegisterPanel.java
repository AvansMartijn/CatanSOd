package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginRegisterPanel extends JPanel {
	private JButton loginButton;
	private JButton registerButton;
	private JTextField usernameText;
	private JTextField passwordText;
	private JLabel messageLabel;

	public LoginRegisterPanel() {
		Dimension preferredSize = new Dimension(100, 20);

		JLabel welcome = new JLabel("Welcome to Catan");
		welcome.setFont(new Font("Arial", Font.PLAIN, 30));
		add(welcome);
		messageLabel = new JLabel("Fill in your Credentials below");
		add(messageLabel);

		JLabel title = new JLabel("Inloggen");
		title.setFont(new Font("Arial", Font.PLAIN, 18));
		add(title);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel userLabel = new JLabel("Gebruikersnaam");
		add(userLabel);

		usernameText = new JTextField(20);
		usernameText.setPreferredSize(preferredSize);
		add(usernameText);

		JLabel passwordLabel = new JLabel("Wachtwoord");
		add(passwordLabel);

		passwordText = new JTextField(20);
		passwordText.setPreferredSize(preferredSize);
		add(passwordText);

		loginButton = new JButton("Inloggen");
		loginButton.setPreferredSize(new Dimension(200, 40));
		loginButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(loginButton);

		registerButton = new JButton("Registreren");
		registerButton.setPreferredSize(new Dimension(200, 40));
		registerButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		add(registerButton, BorderLayout.PAGE_END);

	}
	
	public JButton getInlogButton() {
		return loginButton;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public JTextField getUsernameText() {
		return usernameText;
	}

	public JTextField getPasswordText() {
		return passwordText;
	}

	public void setMessagelabel(String str) {
		messageLabel.setText(str);
	}
}
