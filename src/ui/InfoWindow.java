package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InfoWindow {

	private JFrame frmInfo;
	private JTextField firstNameBox;
	private JTextField lastNameBox;
	private JTextField usernameBox;
	private JTextField emailBox;
	private JTextField passwordBox;
	
	private static database.User user;
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, database.User user_) {
		user = user_;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoWindow window = new InfoWindow();
					window.frmInfo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InfoWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInfo = new JFrame();
		frmInfo.setResizable(false);
		frmInfo.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				AdminWindow.enableWindow();
			}
		});
		frmInfo.getContentPane().setBackground(new Color(105, 50, 12));
		frmInfo.getContentPane().setLayout(null);
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(new Color(222, 222, 222));
		lblInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblInfo.setBounds(137, 11, 104, 51);
		frmInfo.getContentPane().add(lblInfo);
		
		firstNameBox = new JTextField();
		firstNameBox.setEditable(false);
		firstNameBox.setToolTipText("");
		firstNameBox.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameBox.setForeground(new Color(222, 222, 222));
		firstNameBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		firstNameBox.setColumns(10);
		firstNameBox.setCaretColor(new Color(222, 222, 222));
		firstNameBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		firstNameBox.setBackground(new Color(145, 74, 23));
		firstNameBox.setBounds(10, 82, 165, 42);
		frmInfo.getContentPane().add(firstNameBox);
		
		lastNameBox = new JTextField();
		lastNameBox.setEditable(false);
		lastNameBox.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameBox.setForeground(new Color(222, 222, 222));
		lastNameBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		lastNameBox.setColumns(10);
		lastNameBox.setCaretColor(new Color(222, 222, 222));
		lastNameBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		lastNameBox.setBackground(new Color(145, 74, 23));
		lastNameBox.setBounds(208, 82, 165, 42);
		frmInfo.getContentPane().add(lastNameBox);
		
		usernameBox = new JTextField();
		usernameBox.setEditable(false);
		usernameBox.setHorizontalAlignment(SwingConstants.CENTER);
		usernameBox.setForeground(new Color(222, 222, 222));
		usernameBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		usernameBox.setColumns(10);
		usernameBox.setCaretColor(new Color(222, 222, 222));
		usernameBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		usernameBox.setBackground(new Color(145, 74, 23));
		usernameBox.setBounds(10, 142, 165, 42);
		frmInfo.getContentPane().add(usernameBox);
		
		JTextField dateBox = new JTextField("");
		dateBox.setEditable(false);
		dateBox.setHorizontalAlignment(SwingConstants.CENTER);
		dateBox.setForeground(new Color(222, 222, 222));
		dateBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		dateBox.setColumns(10);
		dateBox.setCaretColor(new Color(222, 222, 222));
		dateBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		dateBox.setBackground(new Color(145, 74, 23));
		dateBox.setBounds(208, 142, 165, 42);
		frmInfo.getContentPane().add(dateBox);
		
		emailBox = new JTextField();
		emailBox.setEditable(false);
		emailBox.setHorizontalAlignment(SwingConstants.CENTER);
		emailBox.setForeground(new Color(222, 222, 222));
		emailBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		emailBox.setColumns(10);
		emailBox.setCaretColor(new Color(222, 222, 222));
		emailBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		emailBox.setBackground(new Color(145, 74, 23));
		emailBox.setBounds(48, 206, 288, 44);
		frmInfo.getContentPane().add(emailBox);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(26, 337, 327, 19);
		frmInfo.getContentPane().add(lblNewLabel);
		
		passwordBox = new JTextField();
		passwordBox.setHorizontalAlignment(SwingConstants.CENTER);
		passwordBox.setForeground(new Color(222, 222, 222));
		passwordBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		passwordBox.setEditable(false);
		passwordBox.setColumns(10);
		passwordBox.setCaretColor(new Color(222, 222, 222));
		passwordBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		passwordBox.setBackground(new Color(145, 74, 23));
		passwordBox.setBounds(48, 272, 288, 44);
		frmInfo.getContentPane().add(passwordBox);
		frmInfo.setTitle("Info");
		frmInfo.setIconImage(Toolkit.getDefaultToolkit().getImage(InfoWindow.class.getResource("/ui/resources/icon.png")));
		frmInfo.setBounds(100, 100, 399, 406);
		frmInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		firstNameBox.setText(user.getFirstName());
		lastNameBox.setText(user.getLastName());
		usernameBox.setText(user.getUsername());
		dateBox.setText(user.getBDay());
		emailBox.setText(user.getEmail());
		passwordBox.setText(user.getPassword());
	}
}
