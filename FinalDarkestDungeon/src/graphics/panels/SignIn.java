package graphics.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import graphics.MainFrame;

public class SignIn extends JPanel{
		
		private JLabel tittle;
		private JLabel passwordLabel;
		private JLabel usernameLabel;
		private JTextField usernameField;
		private JButton signUpButton;
		private JPasswordField passwordField;
		private JPanel panel;
		private JButton closeButton;
		private JButton loginButton;
		private JLabel backgroundPicture;
	    
		public SignIn() {
			setBackground(Color.GRAY);
			setBounds(0, 0, 689, 483);
			setOpaque(false);
			setLayout(null);
			
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(278, 41, 411, 440);
			add(panel);
			panel.setLayout(null);
			
			backgroundPicture = new JLabel("New label");
			backgroundPicture.setBounds(0, 41, 284, 440);
			add(backgroundPicture);
			backgroundPicture.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/dd2.png")));
			
			tittle = new JLabel("Sign In");
			tittle.setBounds(53, 34, 160, 76);
			panel.add(tittle);
			tittle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 40));
			
			passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(53, 231, 81, 22);
			panel.add(passwordLabel);
			passwordLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
				
			usernameLabel = new JLabel("Username");
			usernameLabel.setBounds(53, 143, 81, 22);
			panel.add(usernameLabel);
			usernameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
					
			usernameField = new JTextField();
			usernameField.setBounds(53, 169, 310, 39);
			panel.add(usernameField);
			usernameField.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
			usernameField.setText("Enter username");
			usernameField.setColumns(10);
			usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
						
			signUpButton = new JButton("Sign up");
			signUpButton.setBounds(303, 11, 97, 31);
			panel.add(signUpButton);
			signUpButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
			signUpButton.setForeground(new Color(0, 51, 204));
			signUpButton.setOpaque(false);
			signUpButton.setContentAreaFilled(false);
			signUpButton.setBorderPainted(false);
							
			passwordField = new JPasswordField();
			passwordField.setBounds(53, 254, 310, 39);
			panel.add(passwordField);
			passwordField.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
			passwordField.setText("Enter password");
			passwordField.setColumns(10);
			passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
								
			loginButton = new JButton("Sign in");
			loginButton.setBounds(53, 326, 310, 52);
			panel.add(loginButton);
			loginButton.setForeground(Color.WHITE);
			loginButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
			loginButton.setBackground(new Color(0, 51, 153));
			loginButton.setBorderPainted(false);
									
			closeButton = new JButton("");
			closeButton.setOpaque(false);
			closeButton.setContentAreaFilled(false);
			closeButton.setBorderPainted(false);
			closeButton.setIcon(new ImageIcon(SignIn.class.getResource("/assets/buttons/close.png")));
			closeButton.setBounds(0, 0, 48, 43);
			add(closeButton);
		}
		
		public JButton getCloseButton() {
			return closeButton;
		}
		
		public JButton getSignUp() {
			return signUpButton;
		}
		
		public JButton getSignInButton() {
			return loginButton;
		}
		
		public String getUsername() {
			return usernameField.getText();
		}
		
		@SuppressWarnings("deprecation")//Possible explotaition
		public String getPassword() {
			return passwordField.getText();
		}
}
