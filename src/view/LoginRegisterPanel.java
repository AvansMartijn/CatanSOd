package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
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
		
		setPreferredSize(new Dimension(250, 250));
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

		Dimension preferredSize = new Dimension(100, 20);

		JLabel welcome = new JLabel("Welcome to Catan");
		welcome.setFont(new Font("Arial", Font.PLAIN, 30));
		add(welcome);

		messageLabel = new JLabel("Vul hieronder je gegevens in");
		add(messageLabel);
		messageLabel.setAlignmentY(LEFT_ALIGNMENT);

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

		add(registerButton);

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
