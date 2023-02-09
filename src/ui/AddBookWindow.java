package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddBookWindow {

	private JFrame frmAddBook;
	private JTextField txtAuthorName;
	private JTextField txtAuthorSurname;
	private JTextField txtIsbn;
	private JTextField txtPrice;
	private JTextField txtBookTitle;
	private JButton btnAddBook;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookWindow window = new AddBookWindow();
					window.frmAddBook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddBookWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddBook = new JFrame();
		frmAddBook.setResizable(false);
		frmAddBook.setTitle("Add Book");
		frmAddBook.setIconImage(Toolkit.getDefaultToolkit().getImage(AddBookWindow.class.getResource("/ui/resources/icon.png")));
		frmAddBook.getContentPane().setBackground(new Color(105, 50, 12));
		frmAddBook.getContentPane().setLayout(null);
		
		JLabel lblAddBook = new JLabel("Add Book");
		lblAddBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddBook.setForeground(new Color(222, 222, 222));
		lblAddBook.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblAddBook.setBounds(97, 11, 236, 51);
		frmAddBook.getContentPane().add(lblAddBook);
		
		txtAuthorName = new JTextField();
		txtAuthorName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(txtAuthorName.getText());
				if(!pwd.equals("Author Name")) {
					return;
				}
				txtAuthorName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(txtAuthorName.getText());
				if(pwd.length() == 0) {
					txtAuthorName.setText("Author Name");
				}
			}
		});
		txtAuthorName.setText("Author Name");
		txtAuthorName.setHorizontalAlignment(SwingConstants.CENTER);
		txtAuthorName.setForeground(new Color(222, 222, 222));
		txtAuthorName.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		txtAuthorName.setColumns(10);
		txtAuthorName.setCaretColor(new Color(222, 222, 222));
		txtAuthorName.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		txtAuthorName.setBackground(new Color(145, 74, 23));
		txtAuthorName.setBounds(40, 87, 165, 42);
		frmAddBook.getContentPane().add(txtAuthorName);
		
		txtAuthorSurname = new JTextField();
		txtAuthorSurname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(txtAuthorSurname.getText());
				if(!pwd.equals("Author Surname")) {
					return;
				}
				txtAuthorSurname.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(txtAuthorSurname.getText());
				if(pwd.length() == 0) {
					txtAuthorSurname.setText("Author Surname");
				}
			}
		});
		txtAuthorSurname.setToolTipText("");
		txtAuthorSurname.setText("Author Surname");
		txtAuthorSurname.setHorizontalAlignment(SwingConstants.CENTER);
		txtAuthorSurname.setForeground(new Color(222, 222, 222));
		txtAuthorSurname.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		txtAuthorSurname.setColumns(10);
		txtAuthorSurname.setCaretColor(new Color(222, 222, 222));
		txtAuthorSurname.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		txtAuthorSurname.setBackground(new Color(145, 74, 23));
		txtAuthorSurname.setBounds(234, 87, 165, 42);
		frmAddBook.getContentPane().add(txtAuthorSurname);
		
		txtIsbn = new JTextField();
		txtIsbn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(txtIsbn.getText());
				if(!pwd.equals("ISBN")) {
					return;
				}
				txtIsbn.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(txtIsbn.getText());
				if(pwd.length() == 0) {
					txtIsbn.setText("ISBN");
				}
			}
		});
		txtIsbn.setToolTipText("");
		txtIsbn.setText("ISBN");
		txtIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		txtIsbn.setForeground(new Color(222, 222, 222));
		txtIsbn.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		txtIsbn.setColumns(10);
		txtIsbn.setCaretColor(new Color(222, 222, 222));
		txtIsbn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		txtIsbn.setBackground(new Color(145, 74, 23));
		txtIsbn.setBounds(40, 154, 165, 42);
		frmAddBook.getContentPane().add(txtIsbn);
		
		txtPrice = new JTextField();
		txtPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(txtPrice.getText());
				if(!pwd.equals("Price")) {
					return;
				}
				txtPrice.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(txtPrice.getText());
				if(pwd.length() == 0) {
					txtPrice.setText("Price");
				}
			}
		});
		txtPrice.setToolTipText("");
		txtPrice.setText("Price");
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setForeground(new Color(222, 222, 222));
		txtPrice.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		txtPrice.setColumns(10);
		txtPrice.setCaretColor(new Color(222, 222, 222));
		txtPrice.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		txtPrice.setBackground(new Color(145, 74, 23));
		txtPrice.setBounds(234, 154, 165, 42);
		frmAddBook.getContentPane().add(txtPrice);
		
		txtBookTitle = new JTextField();
		txtBookTitle.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(txtBookTitle.getText());
				if(!pwd.equals("Book Title")) {
					return;
				}
				txtBookTitle.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(txtBookTitle.getText());
				if(pwd.length() == 0) {
					txtBookTitle.setText("Book Title");
				}
			}
		});
		txtBookTitle.setToolTipText("");
		txtBookTitle.setText("Book Title");
		txtBookTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookTitle.setForeground(new Color(222, 222, 222));
		txtBookTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		txtBookTitle.setColumns(10);
		txtBookTitle.setCaretColor(new Color(222, 222, 222));
		txtBookTitle.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		txtBookTitle.setBackground(new Color(145, 74, 23));
		txtBookTitle.setBounds(82, 223, 272, 42);
		frmAddBook.getContentPane().add(txtBookTitle);
		
		btnAddBook = new JButton("Add Book");
		btnAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAddBook.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAddBook.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
		});
		btnAddBook.setForeground(new Color(222, 222, 222));
		btnAddBook.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnAddBook.setFocusPainted(false);
		btnAddBook.setContentAreaFilled(false);
		btnAddBook.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnAddBook.setBackground(new Color(145, 74, 23));
		btnAddBook.setBounds(131, 287, 186, 50);
		frmAddBook.getContentPane().add(btnAddBook);
		
		lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(40, 351, 348, 19);
		frmAddBook.getContentPane().add(lblNewLabel);
		frmAddBook.setBounds(100, 100, 450, 419);
		frmAddBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
