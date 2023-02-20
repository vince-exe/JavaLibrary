package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import database.Book;
import database.Database;
import uiUtils.BookFinals;
import uiUtils.DialogsHandler;
import uiUtils.UpdtBookChecker;

public class UpdtBookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField authorFBox;
	private JTextField authorLBox;
	private JTextField ISBNBox;
	private JTextField priceBox;
	private JTextField bookTitleBox;
	
	public static boolean updateABook = false;
	private static Book bookToUpdate;
	private static JDialog _dialog;
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, Book book) {
		bookToUpdate = book;
		try {
			updateABook = false;
			UpdtBookDialog dialog = new UpdtBookDialog();
			_dialog = dialog;
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdtBookDialog() {
		setAutoRequestFocus(false);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Update Book");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdtBookDialog.class.getResource("/ui/resources/icon.png")));
		setBounds(100, 100, 450, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(105, 50, 12));
		contentPanel.setBounds(new Rectangle(100, 100, 420, 450));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblUpdateBook = new JLabel("Update Book");
		lblUpdateBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateBook.setForeground(new Color(222, 222, 222));
		lblUpdateBook.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblUpdateBook.setBounds(97, 11, 236, 51);
		contentPanel.add(lblUpdateBook);
		
		authorFBox = new JTextField();
		authorFBox.addFocusListener(new FocusAdapter() {
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
		contentPanel.add(authorFBox);
		
		authorLBox = new JTextField();
		authorLBox.addFocusListener(new FocusAdapter() {
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
		contentPanel.add(authorLBox);
		
		ISBNBox = new JTextField();
		ISBNBox.addFocusListener(new FocusAdapter() {
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
		contentPanel.add(ISBNBox);
		
		priceBox = new JTextField();
		priceBox.addFocusListener(new FocusAdapter() {
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
		contentPanel.add(priceBox);
		
		bookTitleBox = new JTextField();
		bookTitleBox.addFocusListener(new FocusAdapter() {
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
		contentPanel.add(bookTitleBox);
		
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
					_dialog.dispose();
					return;
				}
				
				if(!Database.updateBook(bookToUpdate.getId(), book)) {
					DialogsHandler.SQLErr(null, "The application failed to update the book");
					return;
				}
				
				DialogsHandler.infoSuccess(null, "Update Success", "The application has successfully updated the book");
				updateABook = true;
				_dialog.dispose();
			}
		});
		updateBtn.setForeground(new Color(222, 222, 222));
		updateBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		updateBtn.setFocusPainted(false);
		updateBtn.setContentAreaFilled(false);
		updateBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		updateBtn.setBackground(new Color(145, 74, 23));
		updateBtn.setBounds(131, 287, 186, 50);
		contentPanel.add(updateBtn);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(40, 351, 348, 19);
		contentPanel.add(lblNewLabel);
	}
}
