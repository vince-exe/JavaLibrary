package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import database.User;

import javax.swing.JPasswordField;

public class MoreInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField usrBox;
	private JTextField emailBox;
	private JTextField pswBox;
	private JTextField moneyBox;
	private JTextField ordersBox;
	
	private static User user;
	private static int nOrders;
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, User usr, int orders) {
		user = usr;
		nOrders = orders;
		try {
			MoreInfoDialog dialog = new MoreInfoDialog();
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
	public MoreInfoDialog() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAutoRequestFocus(false);
		setTitle("Info Customer");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MoreInfoDialog.class.getResource("/ui/resources/icon.png")));
		setBounds(100, 100, 420, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(105, 50, 12));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(new Color(222, 222, 222));
		lblInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblInfo.setBounds(151, 11, 98, 51);
		contentPanel.add(lblInfo);
		
		usrBox = new JTextField();
		usrBox.setText(user.getUsername());
		usrBox.setEditable(false);
		usrBox.setHorizontalAlignment(SwingConstants.CENTER);
		usrBox.setForeground(new Color(222, 222, 222));
		usrBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		usrBox.setColumns(10);
		usrBox.setCaretColor(new Color(222, 222, 222));
		usrBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		usrBox.setBackground(new Color(145, 74, 23));
		usrBox.setBounds(58, 92, 288, 44);
		contentPanel.add(usrBox);
		
		emailBox = new JTextField();
		emailBox.setText(user.getEmail());
		emailBox.setEditable(false);
		emailBox.setHorizontalAlignment(SwingConstants.CENTER);
		emailBox.setForeground(new Color(222, 222, 222));
		emailBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		emailBox.setColumns(10);
		emailBox.setCaretColor(new Color(222, 222, 222));
		emailBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		emailBox.setBackground(new Color(145, 74, 23));
		emailBox.setBounds(58, 158, 288, 44);
		contentPanel.add(emailBox);
		
		pswBox = new JTextField();
		pswBox.setText(user.getPassword());
		pswBox.setEditable(false);
		pswBox.setHorizontalAlignment(SwingConstants.CENTER);
		pswBox.setForeground(new Color(222, 222, 222));
		pswBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		pswBox.setColumns(10);
		pswBox.setCaretColor(new Color(222, 222, 222));
		pswBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		pswBox.setBackground(new Color(145, 74, 23));
		pswBox.setBounds(58, 228, 288, 44);
		contentPanel.add(pswBox);
		
		moneyBox = new JTextField();
		moneyBox.setText("Money: " + Double.toString(user.getMoney()));
		moneyBox.setEditable(false);
		moneyBox.setHorizontalAlignment(SwingConstants.CENTER);
		moneyBox.setForeground(new Color(222, 222, 222));
		moneyBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		moneyBox.setColumns(10);
		moneyBox.setCaretColor(new Color(222, 222, 222));
		moneyBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		moneyBox.setBackground(new Color(145, 74, 23));
		moneyBox.setBounds(58, 297, 288, 44);
		contentPanel.add(moneyBox);
		
		ordersBox = new JTextField();
		ordersBox.setText("Orders: " + Integer.toString(nOrders));
		ordersBox.setEditable(false);
		ordersBox.setHorizontalAlignment(SwingConstants.CENTER);
		ordersBox.setForeground(new Color(222, 222, 222));
		ordersBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		ordersBox.setColumns(10);
		ordersBox.setCaretColor(new Color(222, 222, 222));
		ordersBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		ordersBox.setBackground(new Color(145, 74, 23));
		ordersBox.setBounds(58, 366, 288, 44);
		contentPanel.add(ordersBox);
	}
}
