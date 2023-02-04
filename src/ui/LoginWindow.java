package ui;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class LoginWindow implements FocusListener, MouseListener {	
	private JFrame window;
	
	private JLabel titleLabel;
	private JLabel text1;
	private JLabel text2;
	private JLabel copyRight;
	
	private JTextField emailBox;
	private JPasswordField passwordBox;

	private JButton loginBtn;
	
	private final Color WINDOW_FOREGROUND_COLOR = new Color(0xdedede);
	
	public LoginWindow() {
		window = new JFrame("Login");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(400, 420);
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
		
		text1 = new JLabel("Non hai un account?");
		text1.setFont(new Font("Monospaced", Font.BOLD, 15));
		text1.setForeground(WINDOW_FOREGROUND_COLOR);
		text1.setBounds(40, 240, 190, 30);
		
		text2 = new JLabel("Registrati ora");
		text2.setFont(new Font("Monospaced", Font.BOLD, 15));
		text2.setForeground(WINDOW_FOREGROUND_COLOR);
		text2.setBounds(215, 240, 200, 30);
		text2.addMouseListener(this);
		
		loginBtn = new JButton("Login");
		loginBtn.setBorder(new LineBorder(new Color(0x40260b),4));
		loginBtn.setBackground(new Color(0x914a17));
		loginBtn.setFont(new Font("Monospaced", Font.BOLD, 18));
		loginBtn.setBounds(95, 285, 200, 50);
		loginBtn.setFocusable(false);
		loginBtn.addMouseListener(this);
		loginBtn.setFocusPainted(false);
		loginBtn.setForeground(WINDOW_FOREGROUND_COLOR);
		loginBtn.setContentAreaFilled(false);
		
		copyRight = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		copyRight.setFont(new Font("MV Bold", Font.BOLD, 11));
		copyRight.setForeground(new Color(0xbababa));
		copyRight.setBounds(34, 353, 420, 20);
		
		window.add(titleLabel);
		window.add(emailBox);
		window.add(passwordBox);
		window.add(text1);
		window.add(text2);
		window.add(loginBtn);
		window.add(copyRight);
		
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
			emailBox.setBorder(new LineBorder(new Color(0x40260b),5));
			setPlaceHolder(emailBox, "email@gmail.com", true);
		}	
		else if(e.getSource() == passwordBox) {
			passwordBox.setBorder(new LineBorder(new Color(0x40260b),5));
			setPlaceHolder(passwordBox, "*  *  *  *  *", true);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == emailBox) {
			emailBox.setBorder(new LineBorder(new Color(0x40260b),4));
			setPlaceHolder(emailBox, "email@gmail.com", false);
		}
		else if(e.getSource() == passwordBox) {
			passwordBox.setBorder(new LineBorder(new Color(0x40260b),4));
			setPlaceHolder(passwordBox, "*  *  *  *  *", false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == text2) {
			System.out.print("\nRegistration");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == loginBtn) {
			loginBtn.setBorder(new LineBorder(new Color(0x40260b),5));
		}
		else if(e.getSource() == text2) {
			text2.setFont(new Font("Monospaced", Font.BOLD, 17));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == loginBtn) {
			loginBtn.setBorder(new LineBorder(new Color(0x40260b),4));
		}
		else if(e.getSource() == text2) {
			text2.setFont(new Font("Monospaced", Font.BOLD, 15));
		}
	}
}
