package ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.User;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdtCustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static User userToUpdate;
	private JTextField firstNameBox;
	private JTextField lastNameBox;
	private JTextField usrField;
	private JTextField dateField;
	private JTextField emailField;
	
	public static boolean alreadyCreated = false;
	private JTextField passwordBox;

	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, User user) {
		try {
			userToUpdate = user;
			UpdtCustomerDialog dialog = new UpdtCustomerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModalityType(DEFAULT_MODALITY_TYPE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdtCustomerDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				alreadyCreated = false;
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAutoRequestFocus(false);
		setBounds(new Rectangle(0, 0, 420, 500));
		setTitle("Customer Update");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdtCustomerDialog.class.getResource("/ui/resources/icon.png")));
		setResizable(false);
		setBounds(100, 100, 420, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(105, 50, 12));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setForeground(new Color(222, 222, 222));
		lblUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblUpdate.setBounds(127, 16, 145, 51);
		contentPanel.add(lblUpdate);
		
		firstNameBox = new JTextField();
		firstNameBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(firstNameBox.getText());
				if(pwd.length() == 0) {
					firstNameBox.setText(userToUpdate.getFirstName());
				}
			}
		});
		firstNameBox.setText(userToUpdate.getFirstName());
		firstNameBox.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameBox.setForeground(new Color(222, 222, 222));
		firstNameBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		firstNameBox.setColumns(10);
		firstNameBox.setCaretColor(new Color(222, 222, 222));
		firstNameBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		firstNameBox.setBackground(new Color(145, 74, 23));
		firstNameBox.setBounds(29, 95, 165, 42);
		contentPanel.add(firstNameBox);
		
		lastNameBox = new JTextField();
		lastNameBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(lastNameBox.getText());
				if(pwd.length() == 0) {
					lastNameBox.setText(userToUpdate.getLastName());
				}
			}
		});
		lastNameBox.setText(userToUpdate.getLastName());
		lastNameBox.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameBox.setForeground(new Color(222, 222, 222));
		lastNameBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		lastNameBox.setColumns(10);
		lastNameBox.setCaretColor(new Color(222, 222, 222));
		lastNameBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		lastNameBox.setBackground(new Color(145, 74, 23));
		lastNameBox.setBounds(212, 95, 165, 42);
		contentPanel.add(lastNameBox);
		
		usrField = new JTextField();
		usrField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(usrField.getText());
				if(pwd.length() == 0) {
					usrField.setText(userToUpdate.getUsername());
				}
			}
		});
		usrField.setText(userToUpdate.getUsername());
		usrField.setHorizontalAlignment(SwingConstants.CENTER);
		usrField.setForeground(new Color(222, 222, 222));
		usrField.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		usrField.setColumns(10);
		usrField.setCaretColor(new Color(222, 222, 222));
		usrField.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		usrField.setBackground(new Color(145, 74, 23));
		usrField.setBounds(29, 168, 165, 42);
		contentPanel.add(usrField);
		
		dateField = new JTextField("");
		dateField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(dateField.getText());
				if(pwd.length() == 0) {
					dateField.setText(userToUpdate.getBDay());
				}
			}
		});
		dateField.setText(userToUpdate.getBDay());
		dateField.setHorizontalAlignment(SwingConstants.CENTER);
		dateField.setForeground(new Color(222, 222, 222));
		dateField.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		dateField.setColumns(10);
		dateField.setCaretColor(new Color(222, 222, 222));
		dateField.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		dateField.setBackground(new Color(145, 74, 23));
		dateField.setBounds(212, 168, 165, 42);
		contentPanel.add(dateField);
		
		emailField = new JTextField();
		emailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(emailField.getText());
				if(pwd.length() == 0) {
					emailField.setText(userToUpdate.getEmail());
				}
			}
		});
		emailField.setText(userToUpdate.getEmail());
		emailField.setHorizontalAlignment(SwingConstants.CENTER);
		emailField.setForeground(new Color(222, 222, 222));
		emailField.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		emailField.setColumns(10);
		emailField.setCaretColor(new Color(222, 222, 222));
		emailField.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		emailField.setBackground(new Color(145, 74, 23));
		emailField.setBounds(58, 248, 288, 44);
		contentPanel.add(emailField);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUpdate.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnUpdate.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnUpdate.setForeground(new Color(222, 222, 222));
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnUpdate.setBackground(new Color(145, 74, 23));
		btnUpdate.setBounds(103, 370, 200, 50);
		contentPanel.add(btnUpdate);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(29, 434, 348, 19);
		contentPanel.add(lblNewLabel);
		
		passwordBox = new JTextField();
		passwordBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(passwordBox.getText());
				if(pwd.length() == 0) {
					passwordBox.setText(userToUpdate.getPassword());
				}
			}
		});
		passwordBox.setText(userToUpdate.getPassword());
		passwordBox.setHorizontalAlignment(SwingConstants.CENTER);
		passwordBox.setForeground(new Color(222, 222, 222));
		passwordBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		passwordBox.setColumns(10);
		passwordBox.setCaretColor(new Color(222, 222, 222));
		passwordBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		passwordBox.setBackground(new Color(145, 74, 23));
		passwordBox.setBounds(58, 304, 288, 44);
		contentPanel.add(passwordBox);
	}
}
