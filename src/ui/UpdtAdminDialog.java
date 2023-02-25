package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Admin;
import database.User;
import uiUtils.CredentialsChecker;
import uiUtils.DialogsHandler;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UpdtAdminDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private static Admin adminToUpdate;
	public static boolean successUpdate;
	
	private JTextField firstNameBox;
	private JTextField lastNameBox;
	private JTextField usrBox;
	private JTextField dateBox;
	private JTextField emailBox;
	private JTextField pwdBox;
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, Admin admin) {
		adminToUpdate = admin;
		try {
			UpdtAdminDialog dialog = new UpdtAdminDialog();
			successUpdate = false;
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
	public UpdtAdminDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdtAdminDialog.class.getResource("/ui/resources/icon.png")));
		setResizable(false);
		setTitle("Update Admin");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAutoRequestFocus(false);
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
		lblUpdate.setBounds(113, 11, 162, 51);
		contentPanel.add(lblUpdate);
		
		firstNameBox = new JTextField();
		firstNameBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(firstNameBox.getText());
				if(pwd.length() == 0) {
					firstNameBox.setText(adminToUpdate.getFirstName());
				}
			}
		});
		firstNameBox.setText(adminToUpdate.getFirstName());
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
					lastNameBox.setText(adminToUpdate.getLastName());
				}
			}
		});
		lastNameBox.setText(adminToUpdate.getLastName());
		lastNameBox.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameBox.setForeground(new Color(222, 222, 222));
		lastNameBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		lastNameBox.setColumns(10);
		lastNameBox.setCaretColor(new Color(222, 222, 222));
		lastNameBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		lastNameBox.setBackground(new Color(145, 74, 23));
		lastNameBox.setBounds(212, 95, 165, 42);
		contentPanel.add(lastNameBox);
		
		usrBox = new JTextField();
		usrBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(usrBox.getText());
				if(pwd.length() == 0) {
					usrBox.setText(adminToUpdate.getUsername());
				}
			}
		});
		usrBox.setText(adminToUpdate.getUsername());
		usrBox.setHorizontalAlignment(SwingConstants.CENTER);
		usrBox.setForeground(new Color(222, 222, 222));
		usrBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		usrBox.setColumns(10);
		usrBox.setCaretColor(new Color(222, 222, 222));
		usrBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		usrBox.setBackground(new Color(145, 74, 23));
		usrBox.setBounds(29, 168, 165, 42);
		contentPanel.add(usrBox);
		
		dateBox = new JTextField("");
		dateBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(dateBox.getText());
				if(pwd.length() == 0) {
					dateBox.setText(adminToUpdate.getBDay());
				}
			}
		});
		dateBox.setText(adminToUpdate.getBDay());
		dateBox.setHorizontalAlignment(SwingConstants.CENTER);
		dateBox.setForeground(new Color(222, 222, 222));
		dateBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		dateBox.setColumns(10);
		dateBox.setCaretColor(new Color(222, 222, 222));
		dateBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		dateBox.setBackground(new Color(145, 74, 23));
		dateBox.setBounds(212, 168, 165, 42);
		contentPanel.add(dateBox);
		
		emailBox = new JTextField();
		emailBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(emailBox.getText());
				if(pwd.length() == 0) {
					emailBox.setText(adminToUpdate.getEmail());
				}
			}
		});
		emailBox.setText(adminToUpdate.getEmail());
		emailBox.setHorizontalAlignment(SwingConstants.CENTER);
		emailBox.setForeground(new Color(222, 222, 222));
		emailBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		emailBox.setColumns(10);
		emailBox.setCaretColor(new Color(222, 222, 222));
		emailBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		emailBox.setBackground(new Color(145, 74, 23));
		emailBox.setBounds(58, 240, 288, 44);
		contentPanel.add(emailBox);
		
		pwdBox = new JTextField();
		pwdBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(pwdBox.getText());
				if(pwd.length() == 0) {
					pwdBox.setText(adminToUpdate.getPassword());
				}
			}
		});
		pwdBox.setText(adminToUpdate.getPassword());
		pwdBox.setHorizontalAlignment(SwingConstants.CENTER);
		pwdBox.setForeground(new Color(222, 222, 222));
		pwdBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		pwdBox.setColumns(10);
		pwdBox.setCaretColor(new Color(222, 222, 222));
		pwdBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		pwdBox.setBackground(new Color(145, 74, 23));
		pwdBox.setBounds(58, 304, 288, 44);
		contentPanel.add(pwdBox);
		
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
				if(!CredentialsChecker.handleNames(firstNameBox.getText(), null) ||
				   !CredentialsChecker.handleNames(lastNameBox.getText(), null) ||
				   !CredentialsChecker.handleUsername(usrBox.getText(), null) ||
				   !CredentialsChecker.handleBirdDate(dateBox.getText(), null) ||
				   !CredentialsChecker.handleEmail(emailBox.getText(), null) ||
				   !CredentialsChecker.handlePwd(pwdBox.getText(), null)) {
					return;
				}
				
				Admin admin = new Admin(
						adminToUpdate.getIdUser(),
						adminToUpdate.getId(),
						firstNameBox.getText(),
						lastNameBox.getText(),
						dateBox.getText(),
						emailBox.getText(),
						pwdBox.getText(),
						usrBox.getText()
					);
				
				if(adminToUpdate.equals(admin)) {
					dispose();
					return;
				}
				
				if(!admin.getEmail().equals(adminToUpdate.getEmail()) && CredentialsChecker.handleEmailAlreadyExist(admin.getEmail(), null) == -1) {
					DialogsHandler.generalWarning(null, "Invalid Email", "There is already an account with this email");
					return;
				}
				
				if(!admin.getUsername().equals(adminToUpdate.getUsername()) && !CredentialsChecker.usernameAlreadyExist(admin.getUsername(), null)) {
					DialogsHandler.generalWarning(null, "Invalid Username", "There is already an account with this username");
					return;
				}
				
				database.User usr = database.Database.getUser(admin.getEmail());
				if(usr == null) {
					DialogsHandler.SQLErr(null, "The application failed to update the admin");
					return;
				}
				
				usr.setFirstName(admin.getFirstName());
				usr.setLastName(admin.getLastName());
				usr.setBirdDDAte(admin.getBDay());
				usr.setIdUser(admin.getIdUser());
				usr.setEmail(admin.getEmail());
				usr.setPassword(admin.getPassword());
				usr.setUsername(admin.getUsername());

				if(!database.Database.updateUsr(usr)) {
					DialogsHandler.SQLErr(null, "The application failed to update the users");
					return;
				}

				DialogsHandler.infoSuccess(null, "Success Update", "The application successfully updated the user");
				successUpdate = true;
				dispose();
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
	}
}
