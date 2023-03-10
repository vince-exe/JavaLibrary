package ui;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;

import uiUtils.CredentialsChecker;
import database.User;

public class LoginWindow implements FocusListener, MouseListener {	
	private static JFrame window;
	
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
		titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 38));
		titleLabel.setForeground(WINDOW_FOREGROUND_COLOR);
		titleLabel.setBounds(150, 10, 150, 55);
		
		emailBox = new JTextField("email@gmail.com", 20);
		emailBox.setBounds(46, 90, 300, 50);
		emailBox.setBorder(new LineBorder(new Color(0x40260b),4));
		emailBox.setBackground(new Color(0x914a17));
		emailBox.setHorizontalAlignment(JTextField.CENTER);
		emailBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		emailBox.setForeground(WINDOW_FOREGROUND_COLOR);
		emailBox.setCaretColor(WINDOW_FOREGROUND_COLOR);
		emailBox.addFocusListener(this);
		emailBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(!emailBox.getText().equals("email@gmail.com")) {
					return;
				}
				emailBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(emailBox.getText().length() == 0) {
					emailBox.setText("email@gmail.com");
				}
			}
		});
		
		passwordBox = new JPasswordField("* * * * *", 20);
		passwordBox.setBounds(46, 170, 300, 50);
		passwordBox.setBorder(new LineBorder(new Color(0x40260b),4));
		passwordBox.setBackground(new Color(0x914a17));
		passwordBox.setHorizontalAlignment(JTextField.CENTER);
		passwordBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		passwordBox.setForeground(WINDOW_FOREGROUND_COLOR);
		passwordBox.setCaretColor(WINDOW_FOREGROUND_COLOR);
		passwordBox.addFocusListener(this);
		passwordBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(passwordBox.getPassword());
				if(!pwd.equals("* * * * *")) {
					return;
				}
				passwordBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(passwordBox.getPassword());
				if(pwd.length() == 0) {
					passwordBox.setText("* * * * *");
				}
			}
		});
		
		text1 = new JLabel("Non hai un account?");
		text1.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		text1.setForeground(WINDOW_FOREGROUND_COLOR);
		text1.setBounds(40, 240, 190, 30);
		
		text2 = new JLabel("Registrati ora");
		text2.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
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
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
		});
		
		copyRight = new JLabel("Copyright ?? 2023 Vincenzo Caliendo. All rights reserved");
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
	
	public static void enableWindow() {
		window.setEnabled(true);
	}
	
	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == text2) {
			window.setEnabled(false);
			
			String[] args = {"user"};
			RegistrationWindow.startWindow(args);
			
			return;
		}
		
		if(e.getSource() == loginBtn) {
			String pwd = new String(passwordBox.getPassword());
			
			int userId = CredentialsChecker.handleLogin(emailBox.getText().toLowerCase(), pwd, window);
			if(userId == -1) { return; }
			
			try {
				User usr = database.Database.getUser(userId);
				
				if(usr == null) {
					uiUtils.DialogsHandler.SQLErr(window, "The application failed to handle the login");
					return;
				}
						
				if(database.Database.isRoot(userId)) {
					passwordBox.setText("* * * * *");
					emailBox.setText("email@gmail.com");
					window.setEnabled(false);
					
					ui.AdminWindow.startWindow(null, usr);
					return;
				}
				else {
					passwordBox.setText("* * * * *");
					emailBox.setText("email@gmail.com");
					CustomerDialogMenu.startWindow(null, usr);
					return;
				}
				
			} 
			catch (SQLException e1) {
				uiUtils.DialogsHandler.SQLErr(window, e1.getMessage());
			}
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

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
