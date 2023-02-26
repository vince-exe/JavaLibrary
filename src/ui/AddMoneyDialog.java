package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import database.User;
import uiUtils.DialogsHandler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddMoneyDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField moneyBox;
	
	private static final int valueToScale = 20;
	private static final int maxMoneyValue = 500;
	private static User currentUser;
	
	public static boolean success;
	public static double moneyAdded;
	
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, User user) {
		currentUser = user;
		success = false;
		try {
			AddMoneyDialog dialog = new AddMoneyDialog();
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
	public AddMoneyDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddMoneyDialog.class.getResource("/ui/resources/icon.png")));
		setTitle("Add Money");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAutoRequestFocus(false);
		setResizable(false);
		setBounds(100, 100, 450, 281);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(105, 50, 12));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAddMoney = new JLabel("Add Money");
			lblAddMoney.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddMoney.setForeground(new Color(222, 222, 222));
			lblAddMoney.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
			lblAddMoney.setBounds(78, 11, 266, 51);
			contentPanel.add(lblAddMoney);
		}
		{
			moneyBox = new JTextField();
			moneyBox.setEditable(false);
			moneyBox.setText("20");
			moneyBox.setHorizontalAlignment(SwingConstants.CENTER);
			moneyBox.setForeground(new Color(222, 222, 222));
			moneyBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
			moneyBox.setColumns(10);
			moneyBox.setCaretColor(new Color(222, 222, 222));
			moneyBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			moneyBox.setBackground(new Color(145, 74, 23));
			moneyBox.setBounds(104, 95, 226, 42);
			contentPanel.add(moneyBox);
		}
		{
			JButton addMoneyBtn = new JButton("+");
			addMoneyBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int currentValue = Integer.parseInt(moneyBox.getText());
					if(!((currentValue + valueToScale) > maxMoneyValue)) {
						moneyBox.setText(Integer.toString(currentValue + 20));
					}
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					addMoneyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					addMoneyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
				}
			});
			addMoneyBtn.setForeground(new Color(222, 222, 222));
			addMoneyBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			addMoneyBtn.setFocusPainted(false);
			addMoneyBtn.setContentAreaFilled(false);
			addMoneyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			addMoneyBtn.setBackground(new Color(145, 74, 23));
			addMoneyBtn.setBounds(345, 95, 52, 42);
			contentPanel.add(addMoneyBtn);
		}
		{
			JButton rmvMoneyBtn = new JButton("-");
			rmvMoneyBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int currentValue = Integer.parseInt(moneyBox.getText());
					if(!((currentValue - valueToScale) < valueToScale)) {
						moneyBox.setText(Integer.toString(currentValue - 20));
					}
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					rmvMoneyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					rmvMoneyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
				}
			});
			rmvMoneyBtn.setForeground(new Color(222, 222, 222));
			rmvMoneyBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			rmvMoneyBtn.setFocusPainted(false);
			rmvMoneyBtn.setContentAreaFilled(false);
			rmvMoneyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			rmvMoneyBtn.setBackground(new Color(145, 74, 23));
			rmvMoneyBtn.setBounds(31, 95, 52, 42);
			contentPanel.add(rmvMoneyBtn);
		}
		{
			lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
			lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setForeground(new Color(186, 186, 186));
			lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
			lblNewLabel.setBounds(49, 215, 348, 19);
			contentPanel.add(lblNewLabel);
		}
		{
			JButton addMoneyBtn = new JButton("Add Money");
			addMoneyBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					addMoneyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					addMoneyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					double currentValue = Double.parseDouble(moneyBox.getText());
					
					int resp = DialogsHandler.YesNoDialog(null, "Confirm Box", "Are you sure that you want to add " + currentValue + " to your account?");
					if(resp != 0) {
						return;
					}
					
					if(!database.Database.updateMoney(currentUser.getIdPerson(), currentUser.getMoney() + currentValue)) {
						DialogsHandler.SQLErr(null, "The application failed to update your money");
					}
					else {
						DialogsHandler.infoSuccess(null, "Success Operation", "The application successfully added " + currentValue + " to your account");
						
						success = true;
						moneyAdded = currentUser.getMoney() + currentValue;
						
						dispose();
					}
				}
			});
			addMoneyBtn.setForeground(new Color(222, 222, 222));
			addMoneyBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			addMoneyBtn.setFocusPainted(false);
			addMoneyBtn.setContentAreaFilled(false);
			addMoneyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			addMoneyBtn.setBackground(new Color(145, 74, 23));
			addMoneyBtn.setBounds(126, 159, 186, 42);
			contentPanel.add(addMoneyBtn);
		}
	}

}
