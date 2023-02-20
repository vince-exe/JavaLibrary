package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import uiUtils.CredentialsChecker;
import uiUtils.DialogsHandler;
import database.Database;
import database.User;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;

public class RegistrationWindow  {

	private JFrame frmRegistration;
	private JTextField fnField;
	private JTextField lnField;
	private JTextField usrField;
	private JTextField dateField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	
	private static String[] args_;
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args) {
		args_ = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationWindow window = new RegistrationWindow();
					window.frmRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistration = new JFrame();
		frmRegistration.setAutoRequestFocus(false);
		frmRegistration.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(args_[0] == "root" || args_[0] == "rootS") {
					AdminWindow.enableWindow();
				}
				else if(args_[0] == "user") {
					LoginWindow.enableWindow();
				}
			}
		});
		
		frmRegistration.getContentPane().setFocusable(false);
		frmRegistration.setTitle("Registration");
		frmRegistration.setResizable(false);
		frmRegistration.setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrationWindow.class.getResource("/ui/resources/icon.png")));
		frmRegistration.getContentPane().setBackground(new Color(105, 50, 12));
		frmRegistration.setBounds(100, 100, 420, 500);
		frmRegistration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistration.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Registration");
		titleLabel.setForeground(new Color(222, 222, 222));
		titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(81, 16, 236, 51);
		frmRegistration.getContentPane().add(titleLabel);
		
		fnField = new JTextField();
		fnField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(!fnField.getText().equals("first name")) {
					return;
				}
				fnField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(fnField.getText().length() == 0) {
					fnField.setText("first name");
				}
			}
		});
		fnField.setText("first name");
		fnField.setToolTipText("");
		fnField.setHorizontalAlignment(SwingConstants.CENTER);
		fnField.setForeground(new Color(222, 222, 222));
		fnField.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		fnField.setCaretColor(new Color(222, 222, 222));
		fnField.setBackground(new Color(145, 74, 23));
		fnField.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		fnField.setBounds(29, 95, 165, 42);
		frmRegistration.getContentPane().add(fnField);
		fnField.setColumns(10);
		
		JButton registrationBtn = new JButton("Registration");
		registrationBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String pwd = new String(passwordField.getPassword());
				
				if(CredentialsChecker.hasANumber(fnField.getText()) || CredentialsChecker.hasANumber(lnField.getText())) {
					DialogsHandler.generalWarning(frmRegistration, "Invalid Names", "First / Last name can't contains numbers");
					return;
				}
				if(!CredentialsChecker.handleRegistration(
						fnField.getText(),
						lnField.getText(),
						usrField.getText(),
						dateField.getText(),
						emailField.getText().toLowerCase(),
						pwd,
						frmRegistration)) {
					
					return;
				}
					
				User user = new User(fnField.getText(), lnField.getText(), dateField.getText(), emailField.getText().toLowerCase(), pwd, usrField.getText(), 1000);
				if(database.Database.registration(user)) {
					
					/* try to registrate an admin */
					if(args_[0] == "rootS") {
						int lastUserId = Database.getLastUserID();
						if((lastUserId == -1) || !Database.registrationAdmin(lastUserId)) {
							DialogsHandler.SQLErr(frmRegistration, "The database failed to sign-up the new admin.");
							return;
						}
					}
					DialogsHandler.registrationSuccess(frmRegistration);
					frmRegistration.dispatchEvent(new WindowEvent(frmRegistration, WindowEvent.WINDOW_CLOSING));
				}
				else {
					DialogsHandler.registrationErr(frmRegistration);
				}
			}
		});
		
		registrationBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		registrationBtn.setForeground(new Color(222, 222, 222));
		registrationBtn.setBackground(new Color(145, 74, 23));
		registrationBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		registrationBtn.setBounds(103, 370, 200, 50);
		registrationBtn.setFocusPainted(false);
		registrationBtn.setContentAreaFilled(false);
		
		frmRegistration.getContentPane().add(registrationBtn);
		
		lnField = new JTextField();
		lnField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(!lnField.getText().equals("last name")) {
					return;
				}
				lnField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(lnField.getText().length() == 0) {
					lnField.setText("last name");
				}
			}
		});
		lnField.setText("last name");
		lnField.setHorizontalAlignment(SwingConstants.CENTER);
		lnField.setForeground(new Color(222, 222, 222));
		lnField.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		lnField.setColumns(10);
		lnField.setCaretColor(new Color(222, 222, 222));
		lnField.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		lnField.setBackground(new Color(145, 74, 23));
		lnField.setBounds(212, 95, 165, 42);
		frmRegistration.getContentPane().add(lnField);
		
		usrField = new JTextField();
		usrField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(!usrField.getText().equals("username")) {
					return;
				}
				usrField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(usrField.getText().length() == 0) {
					usrField.setText("username");
				}
			}
		});
		usrField.setText("username");
		usrField.setHorizontalAlignment(SwingConstants.CENTER);
		usrField.setForeground(new Color(222, 222, 222));
		usrField.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		usrField.setColumns(10);
		usrField.setCaretColor(new Color(222, 222, 222));
		usrField.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		usrField.setBackground(new Color(145, 74, 23));
		usrField.setBounds(29, 168, 165, 42);
		frmRegistration.getContentPane().add(usrField);
		
		dateField = new JTextField("YYYY/MM/DD");
		dateField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(!dateField.getText().equals("YYYY/MM/DD")) {
					return;
				}
				dateField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(dateField.getText().length() == 0) {
					dateField.setText("YYYY/MM/DD");
				}
			}
		});
		dateField.setText("YYYY/MM/DD");
		dateField.setHorizontalAlignment(SwingConstants.CENTER);
		dateField.setForeground(new Color(222, 222, 222));
		dateField.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		dateField.setColumns(10);
		dateField.setCaretColor(new Color(222, 222, 222));
		dateField.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		dateField.setBackground(new Color(145, 74, 23));
		dateField.setBounds(212, 168, 165, 42);
		frmRegistration.getContentPane().add(dateField);
		
		emailField = new JTextField();
		emailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(!emailField.getText().equals("email@gmail.com")) {
					return;
				}
				emailField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(emailField.getText().length() == 0) {
					emailField.setText("email@gmail.com");
				}
			}
		});
		emailField.setText("email@gmail.com");
		emailField.setHorizontalAlignment(SwingConstants.CENTER);
		emailField.setForeground(new Color(222, 222, 222));
		emailField.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		emailField.setColumns(10);
		emailField.setCaretColor(new Color(222, 222, 222));
		emailField.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		emailField.setBackground(new Color(145, 74, 23));
		emailField.setBounds(58, 240, 288, 44);
		frmRegistration.getContentPane().add(emailField);
		
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(passwordField.getPassword());
				if(!pwd.equals("* * * * *")) {
					return;
				}
				passwordField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(passwordField.getPassword());
				if(pwd.length() == 0) {
					passwordField.setText("* * * * *");
				}
			}
		});
		passwordField.setText("* * * * *");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setForeground(new Color(222, 222, 222));
		passwordField.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		passwordField.setCaretColor(new Color(222, 222, 222));
		passwordField.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		passwordField.setBackground(new Color(145, 74, 23));
		passwordField.setBounds(58, 304, 288, 44);
		frmRegistration.getContentPane().add(passwordField);
		
		lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(29, 434, 348, 19);
		frmRegistration.getContentPane().add(lblNewLabel);
	}
}
