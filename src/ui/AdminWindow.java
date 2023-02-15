package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import database.User;

public class AdminWindow {

	private JFrame frmAdmin;
	
	private static User admin;
	
	private static AdminWindow __window;
	
	private JButton infoBtn;
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, User admin_) {
		admin = admin_;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow window = new AdminWindow();
					__window = window;
					window.frmAdmin.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AdminWindow() {
		initialize();
	}
	
	public static void enableWindow() {
		__window.frmAdmin.setEnabled(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdmin = new JFrame();
		frmAdmin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ui.LoginWindow.enableWindow();
			}
		});
		frmAdmin.getContentPane().setBackground(new Color(105, 50, 12));
		frmAdmin.getContentPane().setLayout(null);
		
		JLabel lblAdminPage = new JLabel("Admin Page");
		lblAdminPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminPage.setForeground(new Color(222, 222, 222));
		lblAdminPage.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblAdminPage.setBounds(20, 11, 236, 51);
		frmAdmin.getContentPane().add(lblAdminPage);
		
		JLabel nicknameLabel = new JLabel("Logged As: " + admin.getUsername());
		nicknameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nicknameLabel.setForeground(new Color(222, 222, 222));
		nicknameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		nicknameLabel.setBounds(294, 25, 179, 39);
		frmAdmin.getContentPane().add(nicknameLabel);
		
		JButton addBookBtn = new JButton("Add Book");
		addBookBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addBookBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addBookBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAdmin.setEnabled(false);
				AddBookWindow.startWindow(null);
			}
		});
		addBookBtn.setForeground(new Color(222, 222, 222));
		addBookBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		addBookBtn.setFocusPainted(false);
		addBookBtn.setContentAreaFilled(false);
		addBookBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		addBookBtn.setBackground(new Color(145, 74, 23));
		addBookBtn.setBounds(55, 97, 172, 50);
		frmAdmin.getContentPane().add(addBookBtn);
		
		JButton viewBooksBtn = new JButton("View Books");
		viewBooksBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				viewBooksBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				viewBooksBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAdmin.setEnabled(false);
				ViewBooks.startWindow(null);
			}
		});
		viewBooksBtn.setForeground(new Color(222, 222, 222));
		viewBooksBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		viewBooksBtn.setFocusPainted(false);
		viewBooksBtn.setContentAreaFilled(false);
		viewBooksBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		viewBooksBtn.setBackground(new Color(145, 74, 23));
		viewBooksBtn.setBounds(265, 97, 172, 50);
		frmAdmin.getContentPane().add(viewBooksBtn);
		
		JButton addCustomerBtn = new JButton("Add Customer");
		addCustomerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addCustomerBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addCustomerBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] args = {"root"};
				RegistrationWindow.startWindow(args);
				frmAdmin.setEnabled(false);
			}
		});
		addCustomerBtn.setForeground(new Color(222, 222, 222));
		addCustomerBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		addCustomerBtn.setFocusPainted(false);
		addCustomerBtn.setContentAreaFilled(false);
		addCustomerBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		addCustomerBtn.setBackground(new Color(145, 74, 23));
		addCustomerBtn.setBounds(55, 173, 172, 50);
		frmAdmin.getContentPane().add(addCustomerBtn);
		
		JButton viewCustomersBtn = new JButton("View Customers");
		viewCustomersBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				viewCustomersBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				viewCustomersBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
		});
		viewCustomersBtn.setForeground(new Color(222, 222, 222));
		viewCustomersBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		viewCustomersBtn.setFocusPainted(false);
		viewCustomersBtn.setContentAreaFilled(false);
		viewCustomersBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		viewCustomersBtn.setBackground(new Color(145, 74, 23));
		viewCustomersBtn.setBounds(265, 173, 172, 50);
		frmAdmin.getContentPane().add(viewCustomersBtn);
		
		JButton addAdminBtn = new JButton("Add Admin");
		addAdminBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addAdminBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addAdminBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] args = {"rootS"};
				RegistrationWindow.startWindow(args);
				frmAdmin.setEnabled(false);
			}
		});
		addAdminBtn.setForeground(new Color(222, 222, 222));
		addAdminBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		addAdminBtn.setFocusPainted(false);
		addAdminBtn.setContentAreaFilled(false);
		addAdminBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		addAdminBtn.setBackground(new Color(145, 74, 23));
		addAdminBtn.setBounds(55, 248, 172, 50);
		frmAdmin.getContentPane().add(addAdminBtn);
		
		JButton viewAdminsBtn = new JButton("View Admins");
		viewAdminsBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				viewAdminsBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				viewAdminsBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
		});
		viewAdminsBtn.setForeground(new Color(222, 222, 222));
		viewAdminsBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		viewAdminsBtn.setFocusPainted(false);
		viewAdminsBtn.setContentAreaFilled(false);
		viewAdminsBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		viewAdminsBtn.setBackground(new Color(145, 74, 23));
		viewAdminsBtn.setBounds(265, 248, 172, 50);
		frmAdmin.getContentPane().add(viewAdminsBtn);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(77, 390, 348, 19);
		frmAdmin.getContentPane().add(lblNewLabel);
		
		infoBtn = new JButton("Info");
		infoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				infoBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				infoBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAdmin.setEnabled(false);
				ui.InfoWindow.startWindow(null, admin);
			}
		});

		infoBtn.setForeground(new Color(222, 222, 222));
		infoBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		infoBtn.setFocusPainted(false);
		infoBtn.setContentAreaFilled(false);
		infoBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		infoBtn.setBackground(new Color(145, 74, 23));
		infoBtn.setBounds(163, 323, 172, 50);
		frmAdmin.getContentPane().add(infoBtn);
		frmAdmin.setIconImage(Toolkit.getDefaultToolkit().getImage(AdminWindow.class.getResource("/ui/resources/icon.png")));
		frmAdmin.setTitle("Admin");
		frmAdmin.setResizable(false);
		frmAdmin.setBounds(100, 100, 511, 456);
		frmAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
