package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import database.Book;
import database.Orders;
import database.User;
import uiUtils.DialogsHandler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BuyBookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField titleBox;
	private JTextField quantityBox;
	
	private static Book bookToBuy;
	private static User currentUser;
	
	private static final int MAX_NUM_BOOKS = 100;
	private static final int SCALE_NUMBER = 1;
	private JTextField priceBox;
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, Book book, User user) {
		currentUser = user;
		bookToBuy = book;
		try {
			BuyBookDialog dialog = new BuyBookDialog();
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
	public BuyBookDialog() {
		setResizable(false);
		setTitle("Buy Book");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuyBookDialog.class.getResource("/ui/resources/icon.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAutoRequestFocus(false);
		setBounds(100, 100, 450, 392);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(105, 50, 12));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblBuyBook = new JLabel("Buy Book");
		lblBuyBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyBook.setForeground(new Color(222, 222, 222));
		lblBuyBook.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblBuyBook.setBounds(80, 11, 266, 51);
		contentPanel.add(lblBuyBook);
		
		titleBox = new JTextField();
		titleBox.setText(bookToBuy.getTitle());
		titleBox.setHorizontalAlignment(SwingConstants.CENTER);
		titleBox.setForeground(new Color(222, 222, 222));
		titleBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		titleBox.setEditable(false);
		titleBox.setColumns(10);
		titleBox.setCaretColor(new Color(222, 222, 222));
		titleBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		titleBox.setBackground(new Color(145, 74, 23));
		titleBox.setBounds(80, 85, 266, 42);
		contentPanel.add(titleBox);
		
		quantityBox = new JTextField();
		quantityBox.setText("1");
		quantityBox.setHorizontalAlignment(SwingConstants.CENTER);
		quantityBox.setForeground(new Color(222, 222, 222));
		quantityBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		quantityBox.setEditable(false);
		quantityBox.setColumns(10);
		quantityBox.setCaretColor(new Color(222, 222, 222));
		quantityBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		quantityBox.setBackground(new Color(145, 74, 23));
		quantityBox.setBounds(171, 144, 78, 42);
		contentPanel.add(quantityBox);
		
		JButton rmvBookBtn = new JButton("-");
		rmvBookBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int currentValue = Integer.parseInt(quantityBox.getText());
				
				if(!((currentValue - SCALE_NUMBER) < SCALE_NUMBER)) {
					quantityBox.setText(Integer.toString(currentValue - SCALE_NUMBER));
					
					double tmp = (double)((currentValue - SCALE_NUMBER) * bookToBuy.getPrice());
					tmp = round(tmp, 2);
					
					priceBox.setText(Double.toString(tmp));
				}
			}
		});
		rmvBookBtn.setForeground(new Color(222, 222, 222));
		rmvBookBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		rmvBookBtn.setFocusPainted(false);
		rmvBookBtn.setContentAreaFilled(false);
		rmvBookBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		rmvBookBtn.setBackground(new Color(145, 74, 23));
		rmvBookBtn.setBounds(80, 144, 52, 42);
		contentPanel.add(rmvBookBtn);
		
		JButton addBookBtn = new JButton("+");
		addBookBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int currentValue = Integer.parseInt(quantityBox.getText());
				
				if(!((currentValue + SCALE_NUMBER) > MAX_NUM_BOOKS)) {
					quantityBox.setText(Integer.toString(currentValue + SCALE_NUMBER));
					
					double tmp = (double)((currentValue + SCALE_NUMBER) * bookToBuy.getPrice());
					tmp = round(tmp, 2);
					
					priceBox.setText(Double.toString(tmp));
				}
			}
		});
		addBookBtn.setForeground(new Color(222, 222, 222));
		addBookBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		addBookBtn.setFocusPainted(false);
		addBookBtn.setContentAreaFilled(false);
		addBookBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		addBookBtn.setBackground(new Color(145, 74, 23));
		addBookBtn.setBounds(294, 144, 52, 42);
		contentPanel.add(addBookBtn);
		
		JButton btnBuyBook = new JButton("Buy Book");
		btnBuyBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuyBook.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBuyBook.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				double totalPrice = Double.parseDouble(quantityBox.getText()) * bookToBuy.getPrice();

				if(currentUser.getMoney() < totalPrice) {
					DialogsHandler.generalWarning(null, "Warning", "You don't have the right amount of money to complete this operation");
					return;
				}
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");  
				LocalDateTime now = LocalDateTime.now();  
				
				Orders order = new Orders(Integer.parseInt(quantityBox.getText()), bookToBuy.getId(), currentUser.getIdUser(), dtf.format(now));
				if(!database.Database.createOrders(order)) {
					DialogsHandler.generalWarning(null, "Warning", "The application failed to create the order");
					return;
				}
				currentUser.setMoney(currentUser.getMoney() - totalPrice);
				if(!database.Database.updateMoney(currentUser.getIdPerson(), currentUser.getMoney())) {
					DialogsHandler.generalWarning(null, "Warning", "The application failed to subtract the money from your account");
					return;
				}
			
				DialogsHandler.infoSuccess(null, "Success", "Successfully bought the book");
				dispose();
			}
		});
		btnBuyBook.setForeground(new Color(222, 222, 222));
		btnBuyBook.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnBuyBook.setFocusPainted(false);
		btnBuyBook.setContentAreaFilled(false);
		btnBuyBook.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnBuyBook.setBackground(new Color(145, 74, 23));
		btnBuyBook.setBounds(117, 270, 186, 42);
		contentPanel.add(btnBuyBook);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(43, 323, 348, 19);
		contentPanel.add(lblNewLabel);
		
		priceBox = new JTextField();
		priceBox.setText(Double.toString(bookToBuy.getPrice()));
		priceBox.setHorizontalAlignment(SwingConstants.CENTER);
		priceBox.setForeground(new Color(222, 222, 222));
		priceBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		priceBox.setEditable(false);
		priceBox.setColumns(10);
		priceBox.setCaretColor(new Color(222, 222, 222));
		priceBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		priceBox.setBackground(new Color(145, 74, 23));
		priceBox.setBounds(133, 208, 158, 42);
		contentPanel.add(priceBox);
	}
}
