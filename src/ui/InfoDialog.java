package ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InfoDialog extends JDialog {

	private JTextField firstNameBox;
	private JTextField lastNameBox;
	private JTextField usernameBox;
	private JTextField emailBox;
	private JTextField passwordBox;
	
	private static database.User user;
	private JTextField moneyBox;
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, database.User user_) {
		user = user_;
		try {
			InfoDialog dialog = new InfoDialog();
			dialog.setModalityType(DEFAULT_MODALITY_TYPE);
			dialog.setVisible(true);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public InfoDialog() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
		});
		getContentPane().setBackground(new Color(105, 50, 12));
		getContentPane().setLayout(null);
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(new Color(222, 222, 222));
		lblInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblInfo.setBounds(137, 11, 104, 51);
		getContentPane().add(lblInfo);
		
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
		getContentPane().add(firstNameBox);
		
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
		getContentPane().add(lastNameBox);
		
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
		getContentPane().add(usernameBox);
		
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
		getContentPane().add(dateBox);
		
		emailBox = new JTextField();
		emailBox.setEditable(false);
		emailBox.setHorizontalAlignment(SwingConstants.CENTER);
		emailBox.setForeground(new Color(222, 222, 222));
		emailBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		emailBox.setColumns(10);
		emailBox.setCaretColor(new Color(222, 222, 222));
		emailBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		emailBox.setBackground(new Color(145, 74, 23));
		emailBox.setBounds(48, 260, 288, 44);
		getContentPane().add(emailBox);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(26, 370, 327, 19);
		getContentPane().add(lblNewLabel);
		
		passwordBox = new JTextField();
		passwordBox.setHorizontalAlignment(SwingConstants.CENTER);
		passwordBox.setForeground(new Color(222, 222, 222));
		passwordBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		passwordBox.setEditable(false);
		passwordBox.setColumns(10);
		passwordBox.setCaretColor(new Color(222, 222, 222));
		passwordBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		passwordBox.setBackground(new Color(145, 74, 23));
		passwordBox.setBounds(48, 315, 288, 44);
		getContentPane().add(passwordBox);
		setTitle("Info");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InfoDialog.class.getResource("/ui/resources/icon.png")));
		setBounds(100, 100, 399, 439);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		moneyBox = new JTextField((String) null);
		moneyBox.setHorizontalAlignment(SwingConstants.CENTER);
		moneyBox.setForeground(new Color(222, 222, 222));
		moneyBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		moneyBox.setEditable(false);
		moneyBox.setColumns(10);
		moneyBox.setCaretColor(new Color(222, 222, 222));
		moneyBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		moneyBox.setBackground(new Color(145, 74, 23));
		moneyBox.setBounds(74, 203, 230, 42);
		getContentPane().add(moneyBox);
		
		firstNameBox.setText(user.getFirstName());
		lastNameBox.setText(user.getLastName());
		usernameBox.setText(user.getUsername());
		dateBox.setText(user.getBDay());
		emailBox.setText(user.getEmail());
		passwordBox.setText(user.getPassword());
		moneyBox.setText(Double.toString(user.getMoney()));
	}
}
