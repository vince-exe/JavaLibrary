package ui;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class LoginWindow implements FocusListener {	
	private JFrame window;
	private JLabel titleLabel;
	private JTextField emailBox;
	private JPasswordField passwordBox;
	
	private final Color WINDOW_FOREGROUND_COLOR = new Color(0xdedede);
	
	public LoginWindow() {
		window = new JFrame("Login");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(400, 500);
		window.setResizable(false);
		window.setLocation(500, 220);
		window.setLayout(null);
		window.getContentPane().setBackground(new Color(0x69320c));
		window.setIconImage(new ImageIcon("src/ui/resources/icon.png").getImage());
		
		titleLabel = new JLabel("Login");
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 40));
		titleLabel.setForeground(WINDOW_FOREGROUND_COLOR);
		titleLabel.setBounds(135, 10, 150, 45);
		
		emailBox = new JTextField("email@gmail.com", 20);
		emailBox.setBounds(46, 90, 300, 50);
		emailBox.setBorder(new LineBorder(new Color(0x40260b),4));
		emailBox.setBackground(new Color(0x914a17));
		emailBox.setHorizontalAlignment(JTextField.CENTER);
		emailBox.setFont(new Font("Monospaced", Font.BOLD, 16));
		emailBox.setForeground(WINDOW_FOREGROUND_COLOR);
		emailBox.setCaretColor(WINDOW_FOREGROUND_COLOR);
		emailBox.addFocusListener(this);
	
		passwordBox = new JPasswordField("*  *  *  *  *", 20);
		passwordBox.setBounds(46, 170, 300, 50);
		passwordBox.setBorder(new LineBorder(new Color(0x40260b),4));
		passwordBox.setBackground(new Color(0x914a17));
		passwordBox.setHorizontalAlignment(JTextField.CENTER);
		passwordBox.setFont(new Font("Monospaced", Font.BOLD, 16));
		passwordBox.setForeground(WINDOW_FOREGROUND_COLOR);
		passwordBox.setCaretColor(WINDOW_FOREGROUND_COLOR);
		passwordBox.addFocusListener(this);
		
		window.add(titleLabel);
		window.add(emailBox);
		window.add(passwordBox);
		
		window.setVisible(true);
	}

	/* function to set the placeholder in the given JTextField */
	private static void setPlaceHolder(JTextField textField, String text, boolean gained) {
		if(gained && textField.getText().equals(text)) {
			textField.setText("");
		}
		else if(!gained && textField.getText().isEmpty()) {
			textField.setText(text);
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == emailBox) {
			setPlaceHolder(emailBox, "email@gmail.com", true);
		}	
		else if(e.getSource() == passwordBox) {
			setPlaceHolder(passwordBox, "*  *  *  *  *", true);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == emailBox) {
			setPlaceHolder(emailBox, "email@gmail.com", false);
		}
		else if(e.getSource() == passwordBox) {
			setPlaceHolder(passwordBox, "*  *  *  *  *", false);
		}
	}
}
