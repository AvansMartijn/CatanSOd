package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginRegisterPanel extends JPanel {
	public LoginRegisterPanel() {

		JLabel welcome = new JLabel("Welkom bij Kolonisten van Catan");
		welcome.setFont(new Font("Arial", Font.PLAIN, 30));
		add(welcome);
		
		 	
		
		JLabel title = new JLabel("Inloggen");
		title.setFont(new Font("Arial", Font.PLAIN, 18));
		add(title);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel userLabel = new JLabel("Gebruikersnaam");
		add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setPreferredSize(new Dimension(100,20));
		add(userText);

		JLabel passwordLabel = new JLabel("Wachtwoord");
		add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		add(passwordText);

		JButton loginButton = new JButton("Inloggen");
		loginButton.setPreferredSize(new Dimension(200,40));
		loginButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(loginButton);

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		JButton registerButton = new JButton("Registreren");
		registerButton.setPreferredSize(new Dimension(200,40));                     
		registerButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		
		add(registerButton, BorderLayout.PAGE_END);

		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	class Register extends JPanel {

		private Register() {

			// setBorder(BorderFactory.createLineBorder(Color.black));

			

			JLabel title = new JLabel("Registreren");
			title.setFont(new Font("Arial", Font.PLAIN, 18));
			add(title);

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			JLabel userLabel = new JLabel("Gebruikersnaam");
			add(userLabel);

			JTextField userText = new JTextField(20);
			add(userText);

			JLabel passwordLabel = new JLabel("Wachtwoord");
			add(passwordLabel);

			JPasswordField passwordText = new JPasswordField(20);
			add(passwordText);

			JLabel passwordAgainLabel = new JLabel("Wachtwoord opnieuw invoeren");
			add(passwordAgainLabel);

			JPasswordField passwordAgainText = new JPasswordField(20);

			add(passwordAgainText);

			JButton registerButton = new JButton("Registreren");

			add(registerButton);
			registerButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
	}
}
