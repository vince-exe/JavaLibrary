package ui;

import database.*;
import uiUtils.AddBookChecker;
import uiUtils.BookFinals;
import uiUtils.UpdtBookChecker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpdtBookWindow {

	private JFrame frmUpdateBook;
	private JTextField authorFBox;
	private JTextField authorLBox;
	private JTextField ISBNBox;
	private JTextField priceBox;
	private JTextField bookTitleBox;
	private JLabel lblNewLabel;
	
	private static Book bookToUpdate;
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, Book book) {
		bookToUpdate = book;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdtBookWindow window = new UpdtBookWindow();
					window.frmUpdateBook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdtBookWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUpdateBook = new JFrame();
		frmUpdateBook.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ViewBooks.enableWindow();
			}
		});
		frmUpdateBook.setTitle("Update Book");
		frmUpdateBook.setResizable(false);
		frmUpdateBook.setAutoRequestFocus(false);
		frmUpdateBook.setIconImage(Toolkit.getDefaultToolkit().getImage(UpdtBookWindow.class.getResource("/ui/resources/icon.png")));
		frmUpdateBook.getContentPane().setBackground(new Color(105, 50, 12));
		frmUpdateBook.getContentPane().setLayout(null);
		
		JLabel lblUpdateBook = new JLabel("Update Book");
		lblUpdateBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateBook.setForeground(new Color(222, 222, 222));
		lblUpdateBook.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblUpdateBook.setBounds(97, 11, 236, 51);
		frmUpdateBook.getContentPane().add(lblUpdateBook);
		
		authorFBox = new JTextField();
		authorFBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(authorFBox.getText());
				if(!pwd.equals(bookToUpdate.getAuthorFName())) {
					return;
				}
				authorFBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(authorFBox.getText());
				if(pwd.length() == 0) {
					authorFBox.setText(bookToUpdate.getAuthorFName());
				}
			}
		});
		authorFBox.setText(bookToUpdate.getAuthorFName());
		authorFBox.setHorizontalAlignment(SwingConstants.CENTER);
		authorFBox.setForeground(new Color(222, 222, 222));
		authorFBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		authorFBox.setColumns(10);
		authorFBox.setCaretColor(new Color(222, 222, 222));
		authorFBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		authorFBox.setBackground(new Color(145, 74, 23));
		authorFBox.setBounds(40, 87, 165, 42);
		frmUpdateBook.getContentPane().add(authorFBox);
		
		authorLBox = new JTextField();
		authorLBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(authorLBox.getText());
				if(!pwd.equals(bookToUpdate.getAuthorLName())) {
					return;
				}
				authorLBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(authorLBox.getText());
				if(pwd.length() == 0) {
					authorLBox.setText(bookToUpdate.getAuthorLName());
				}
			}
		});
		authorLBox.setText(bookToUpdate.getAuthorLName());
		authorLBox.setHorizontalAlignment(SwingConstants.CENTER);
		authorLBox.setForeground(new Color(222, 222, 222));
		authorLBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		authorLBox.setColumns(10);
		authorLBox.setCaretColor(new Color(222, 222, 222));
		authorLBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		authorLBox.setBackground(new Color(145, 74, 23));
		authorLBox.setBounds(234, 87, 165, 42);
		frmUpdateBook.getContentPane().add(authorLBox);
		
		ISBNBox = new JTextField();
		ISBNBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(ISBNBox.getText());
				if(!pwd.equals(bookToUpdate.getISBN())) {
					return;
				}
				ISBNBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(ISBNBox.getText());
				if(pwd.length() == 0) {
					ISBNBox.setText(bookToUpdate.getISBN());
				}
			}
		});
		ISBNBox.setText(bookToUpdate.getISBN());
		ISBNBox.setHorizontalAlignment(SwingConstants.CENTER);
		ISBNBox.setForeground(new Color(222, 222, 222));
		ISBNBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		ISBNBox.setColumns(10);
		ISBNBox.setCaretColor(new Color(222, 222, 222));
		ISBNBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		ISBNBox.setBackground(new Color(145, 74, 23));
		ISBNBox.setBounds(40, 154, 165, 42);
		frmUpdateBook.getContentPane().add(ISBNBox);
		
		priceBox = new JTextField();
		priceBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(priceBox.getText());
				if(!pwd.equals(Double.toString(bookToUpdate.getPrice()))) {
					return;
				}
				priceBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(priceBox.getText());
				if(pwd.length() == 0) {
					priceBox.setText(Double.toString(bookToUpdate.getPrice()));
				}
			}
		});
		priceBox.setText(Double.toString(bookToUpdate.getPrice()));
		priceBox.setHorizontalAlignment(SwingConstants.CENTER);
		priceBox.setForeground(new Color(222, 222, 222));
		priceBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		priceBox.setColumns(10);
		priceBox.setCaretColor(new Color(222, 222, 222));
		priceBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		priceBox.setBackground(new Color(145, 74, 23));
		priceBox.setBounds(234, 154, 165, 42);
		frmUpdateBook.getContentPane().add(priceBox);
		
		bookTitleBox = new JTextField();
		bookTitleBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = new String(bookTitleBox.getText());
				if(!pwd.equals(bookToUpdate.getTitle())) {
					return;
				}
				bookTitleBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pwd = new String(bookTitleBox.getText());
				if(pwd.length() == 0) {
					bookTitleBox.setText(bookToUpdate.getTitle());
				}
			}
		});
		bookTitleBox.setText(bookToUpdate.getTitle());
		bookTitleBox.setHorizontalAlignment(SwingConstants.CENTER);
		bookTitleBox.setForeground(new Color(222, 222, 222));
		bookTitleBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		bookTitleBox.setColumns(10);
		bookTitleBox.setCaretColor(new Color(222, 222, 222));
		bookTitleBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		bookTitleBox.setBackground(new Color(145, 74, 23));
		bookTitleBox.setBounds(82, 223, 272, 42);
		frmUpdateBook.getContentPane().add(bookTitleBox);
		
		lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(40, 351, 348, 19);
		frmUpdateBook.getContentPane().add(lblNewLabel);
		
		JButton updateBtn = new JButton("Update Book");
		updateBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				updateBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				updateBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!UpdtBookChecker.handleName(authorFBox.getText(), "first", BookFinals.MIN_AUTHOR_NAME, BookFinals.MAX_AUTHOR_NAME) || 
				   !UpdtBookChecker.handleName(authorLBox.getText(), "last", BookFinals.MIN_AUTHOR_NAME, BookFinals.MAX_AUTHOR_NAME) || 
				   !BookFinals.handlePrice(priceBox.getText(), BookFinals.PRICE_MIN, BookFinals.PRICE_MAX) || 
				   !UpdtBookChecker.handleIsbnTitle(ISBNBox.getText(), bookTitleBox.getText(), BookFinals.ISBN_LEN_MIN, BookFinals.ISBN_LEN_MAX, BookFinals.TITLE_LEN_MIN, BookFinals.TITLE_LEN_MAX)) {
					return;
				}
				
				Book book = new Book(
						bookToUpdate.getId(),
						Double.parseDouble(priceBox.getText()),
						bookTitleBox.getText(),
						ISBNBox.getText(),
						authorFBox.getText(),
						authorLBox.getText()
				);
				
				if(book.equals(bookToUpdate)) {
					System.out.print("\nSono uguali");
				}
				else {
					System.out.print("\nNon sono uguali");
				}
			}
		});
		updateBtn.setForeground(new Color(222, 222, 222));
		updateBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		updateBtn.setFocusPainted(false);
		updateBtn.setContentAreaFilled(false);
		updateBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		updateBtn.setBackground(new Color(145, 74, 23));
		updateBtn.setBounds(131, 287, 186, 50);
		frmUpdateBook.getContentPane().add(updateBtn);
		frmUpdateBook.setBounds(100, 100, 450, 420);
		frmUpdateBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
