package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import database.*;

import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import uiUtils.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddBookWindow {

	private JFrame frmAddBook;
	private JTextField authorFBox;
	private JTextField authorLBox;
	private JTextField IsbnBox;
	private JTextField priceBox;
	private JTextField titleBox;
	private JButton addBookBtn;
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

	private void cleanBoxes() {
		authorFBox.setText("Author Name");
		authorLBox.setText("Author Surname");
		IsbnBox.setText("ISBN");
		priceBox.setText("Price");
		titleBox.setText("Book Title");
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddBook = new JFrame();
		frmAddBook.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				AdminWindow.enableWindow();
			}
		});
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
		
		authorFBox = new JTextField();
		authorFBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(authorFBox.getText());
				if(!pwd.equals("Author Name")) {
					return;
				}
				authorFBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(authorFBox.getText());
				if(pwd.length() == 0) {
					authorFBox.setText("Author Name");
				}
			}
		});
		authorFBox.setText("Author Name");
		authorFBox.setHorizontalAlignment(SwingConstants.CENTER);
		authorFBox.setForeground(new Color(222, 222, 222));
		authorFBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		authorFBox.setColumns(10);
		authorFBox.setCaretColor(new Color(222, 222, 222));
		authorFBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		authorFBox.setBackground(new Color(145, 74, 23));
		authorFBox.setBounds(40, 87, 165, 42);
		frmAddBook.getContentPane().add(authorFBox);
		
		authorLBox = new JTextField();
		authorLBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(authorLBox.getText());
				if(!pwd.equals("Author Surname")) {
					return;
				}
				authorLBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(authorLBox.getText());
				if(pwd.length() == 0) {
					authorLBox.setText("Author Surname");
				}
			}
		});
		authorLBox.setToolTipText("");
		authorLBox.setText("Author Surname");
		authorLBox.setHorizontalAlignment(SwingConstants.CENTER);
		authorLBox.setForeground(new Color(222, 222, 222));
		authorLBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		authorLBox.setColumns(10);
		authorLBox.setCaretColor(new Color(222, 222, 222));
		authorLBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		authorLBox.setBackground(new Color(145, 74, 23));
		authorLBox.setBounds(234, 87, 165, 42);
		frmAddBook.getContentPane().add(authorLBox);
		
		IsbnBox = new JTextField();
		IsbnBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(IsbnBox.getText());
				if(!pwd.equals("ISBN")) {
					return;
				}
				IsbnBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(IsbnBox.getText());
				if(pwd.length() == 0) {
					IsbnBox.setText("ISBN");
				}
			}
		});
		IsbnBox.setToolTipText("");
		IsbnBox.setText("ISBN");
		IsbnBox.setHorizontalAlignment(SwingConstants.CENTER);
		IsbnBox.setForeground(new Color(222, 222, 222));
		IsbnBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		IsbnBox.setColumns(10);
		IsbnBox.setCaretColor(new Color(222, 222, 222));
		IsbnBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		IsbnBox.setBackground(new Color(145, 74, 23));
		IsbnBox.setBounds(40, 154, 165, 42);
		frmAddBook.getContentPane().add(IsbnBox);
		
		priceBox = new JTextField();
		priceBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(priceBox.getText());
				if(!pwd.equals("Price")) {
					return;
				}
				priceBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(priceBox.getText());
				if(pwd.length() == 0) {
					priceBox.setText("Price");
				}
			}
		});
		priceBox.setToolTipText("");
		priceBox.setText("Price");
		priceBox.setHorizontalAlignment(SwingConstants.CENTER);
		priceBox.setForeground(new Color(222, 222, 222));
		priceBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		priceBox.setColumns(10);
		priceBox.setCaretColor(new Color(222, 222, 222));
		priceBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		priceBox.setBackground(new Color(145, 74, 23));
		priceBox.setBounds(234, 154, 165, 42);
		frmAddBook.getContentPane().add(priceBox);
		
		titleBox = new JTextField();
		titleBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(titleBox.getText());
				if(!pwd.equals("Book Title")) {
					return;
				}
				titleBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(titleBox.getText());
				if(pwd.length() == 0) {
					titleBox.setText("Book Title");
				}
			}
		});
		titleBox.setToolTipText("");
		titleBox.setText("Book Title");
		titleBox.setHorizontalAlignment(SwingConstants.CENTER);
		titleBox.setForeground(new Color(222, 222, 222));
		titleBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		titleBox.setColumns(10);
		titleBox.setCaretColor(new Color(222, 222, 222));
		titleBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		titleBox.setBackground(new Color(145, 74, 23));
		titleBox.setBounds(82, 223, 272, 42);
		frmAddBook.getContentPane().add(titleBox);
		
		addBookBtn = new JButton("Add Book");
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
				if(CredentialsChecker.hasANumber(authorFBox.getText()) || CredentialsChecker.hasANumber(authorLBox.getText())) {
					DialogsHandler.generalWarning(null, "Invalid Names", "First / Last name can't contains numbers");
					return;
				}
				
				if(!AddBookChecker.handleAddBook(authorFBox.getText(), authorLBox.getText(), priceBox.getText(), IsbnBox.getText(), titleBox.getText())) {
					return;
				}

				if(!Database.addBook(new Book(Double.parseDouble(priceBox.getText()), titleBox.getText(), IsbnBox.getText().toUpperCase(), authorFBox.getText(), authorLBox.getText()))) {
					DialogsHandler.SQLErr(frmAddBook, "The database failed to upload the book. Please try again");
					return;
				};
				DialogsHandler.infoSuccess(frmAddBook, "Success", "Successfully added the book to the database");
				cleanBoxes();
			}
		});
		addBookBtn.setForeground(new Color(222, 222, 222));
		addBookBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		addBookBtn.setFocusPainted(false);
		addBookBtn.setContentAreaFilled(false);
		addBookBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		addBookBtn.setBackground(new Color(145, 74, 23));
		addBookBtn.setBounds(131, 287, 186, 50);
		frmAddBook.getContentPane().add(addBookBtn);
		
		lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(40, 351, 348, 19);
		frmAddBook.getContentPane().add(lblNewLabel);
		frmAddBook.setBounds(100, 100, 450, 419);
		frmAddBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
