package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RegisterLoginPanel extends JPanel {

	public RegisterLoginPanel() {

		

		setLayout(new FlowLayout());
		
		Register register = new Register();
		Login login = new Login();

		
		add(register, BorderLayout.LINE_START);
		add(login, BorderLayout.CENTER);

	}

	class Register extends JPanel {

		private Register() {

			// setBorder(BorderFactory.createLineBorder(Color.black));

			//setPreferredSize(new Dimension(400,400));
			
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

	class Login extends JPanel {

		private Login() {
			
			//setPreferredSize(new Dimension(400,400));

			// setBorder(BorderFactory.createLineBorder(Color.black));

			JLabel title = new JLabel("Inloggen");
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

			JButton loginButton = new JButton("Inloggen");
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
