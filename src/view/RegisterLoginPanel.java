package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.GridBagLayout;
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
public class RegisterLoginPanel extends JPanel {

	public RegisterLoginPanel() {
		
		GridBagLayout gridLayout = new GridBagLayout();
		
		//gridLayout
		
		setLayout(gridLayout);

		JLabel welcome = new JLabel("Welkom bij Kolonisten van Catan");
		welcome.setFont(new Font("Arial", Font.PLAIN, 30));

		Register register = new Register();
		Login login = new Login();

		
		
		
		add(welcome);
		add(register);
		add(login);

	}

	class Register extends JPanel {

		private Register() {

			//setBorder(BorderFactory.createLineBorder(Color.black));
			
			JLabel title = new JLabel("Registreren");
			title.setFont(new Font("Arial", Font.PLAIN, 18));
			add(title);

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			JLabel userLabel = new JLabel("Gebruikersnaam");
			userLabel.setBounds(10, 10, 80, 25);
			add(userLabel);

			JTextField userText = new JTextField(20);
			userText.setBounds(100, 10, 160, 25);
			add(userText);

			JLabel passwordLabel = new JLabel("Wachtwoord");
			passwordLabel.setBounds(10, 40, 80, 25);
			add(passwordLabel);

			JPasswordField passwordText = new JPasswordField(20);
			passwordText.setBounds(100, 40, 160, 25);
			add(passwordText);

			JLabel passwordAgainLabel = new JLabel("Wachtwoord opnieuw invoeren");
			passwordAgainLabel.setBounds(10, 40, 80, 25);
			add(passwordAgainLabel);

			JPasswordField passwordAgainText = new JPasswordField(20);
			passwordAgainText.setBounds(100, 40, 160, 25);
			add(passwordAgainText);

			JButton registerButton = new JButton("Registreren");
			registerButton.setBounds(10, 80, 80, 25);
			add(registerButton);

			registerButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	class Login extends JPanel {

		private Login() {

			//setBorder(BorderFactory.createLineBorder(Color.black));
			
			
			JLabel title = new JLabel("Inloggen");
			title.setFont(new Font("Arial", Font.PLAIN, 18));
			add(title);

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			JLabel userLabel = new JLabel("Gebruikersnaam");
			userLabel.setBounds(10, 10, 80, 25);
			add(userLabel);

			JTextField userText = new JTextField(20);
			userText.setBounds(100, 10, 160, 25);
			add(userText);

			JLabel passwordLabel = new JLabel("Wachtwoord");
			passwordLabel.setBounds(10, 40, 80, 25);
			add(passwordLabel);

			JPasswordField passwordText = new JPasswordField(20);
			passwordText.setBounds(100, 40, 160, 25);
			add(passwordText);

			JButton loginButton = new JButton("Inloggen");
			loginButton.setBounds(10, 80, 80, 25);
			add(loginButton);

			loginButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
	}
}
