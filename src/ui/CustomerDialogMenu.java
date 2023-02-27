package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import database.Book;
import database.User;
import uiUtils.DialogsHandler;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerDialogMenu extends JDialog {

	private String columnsName[] = {"Id", "Price", "Title", "ISBN", "Author F.N", "Author L.N"};
	private TableColumnModel columnModel;
	private JTable table;
	private JLabel dateLabel;
	
	private static User currentUser;
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, User user) {
		currentUser = user;
		try {
			CustomerDialogMenu dialog = new CustomerDialogMenu();
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
	public CustomerDialogMenu() {
		getContentPane().setBackground(new Color(105, 50, 12));
		setTitle("Customer Menu");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerDialogMenu.class.getResource("/ui/resources/icon.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBackground(new Color(105, 50, 12));
		setAutoRequestFocus(false);
		setBounds(100, 100, 697, 450);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");  
		LocalDateTime now = LocalDateTime.now();  
		
		dateLabel = new JLabel(dtf.format(now));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		dateLabel.setForeground(new Color(222, 222, 222));
	    
		table = new JTable();
		table.setBounds(new Rectangle(20, 90, 690, 237));
	    DefaultTableModel contactTableModel = (DefaultTableModel) table.getModel();
	    contactTableModel.setColumnIdentifiers(columnsName);
		table.setSelectionBackground(new Color(170, 80, 19));
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(new Color(222, 222, 222));
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		table.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		table.setBackground(new Color(145, 74, 23));
		table.setBounds(20, 90, 640, 237);
		table.getTableHeader().setBackground(new Color(145, 74, 23));
		table.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		table.getTableHeader().setForeground(new Color(222, 222, 222));
		table.getTableHeader().setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, new Color(64, 38, 11)));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(new Rectangle(20, 90, 640, 237));
		scrollPane.getViewport().setBackground(new Color(105, 50, 12));
		scrollPane.setEnabled(false);
		scrollPane.getViewport().getView().setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		scrollPane.setBackground(new Color(105, 50, 12));
		scrollPane.getVerticalScrollBar().setBackground(new Color(145, 74, 23));
		getContentPane().setLayout(null);
		scrollPane.setBounds(20, 80, 640, 237);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		
		JLabel lblCustomerPage = new JLabel("Customer Page");
		lblCustomerPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerPage.setForeground(new Color(222, 222, 222));
		lblCustomerPage.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		lblCustomerPage.setBounds(20, 11, 266, 51);
		getContentPane().add(lblCustomerPage);
		
		JLabel dateLabel = new JLabel("26/02/2023");
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(222, 222, 222));
		dateLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		dateLabel.setBounds(326, 21, 131, 44);
		getContentPane().add(dateLabel);
		
		JButton infoBtn = new JButton("Info");
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
				InfoDialog.startWindow(null, currentUser);
			}
		});
		infoBtn.setForeground(new Color(222, 222, 222));
		infoBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		infoBtn.setFocusPainted(false);
		infoBtn.setContentAreaFilled(false);
		infoBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		infoBtn.setBackground(new Color(145, 74, 23));
		infoBtn.setBounds(505, 19, 131, 50);
		getContentPane().add(infoBtn);
		
		JButton buyBtn = new JButton("Buy");
		buyBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel model = table.getModel();
				
				int selectedRow = table.getSelectedRow();
				if(selectedRow == -1) {
					DialogsHandler.invalidRow(null);
					return;
				}
				
				Book book = new Book(
						Integer.parseInt(model.getValueAt(selectedRow, 0).toString()),
						Double.parseDouble(model.getValueAt(selectedRow, 1).toString()),
						model.getValueAt(selectedRow, 2).toString(),
						model.getValueAt(selectedRow, 3).toString(),
						model.getValueAt(selectedRow, 4).toString(),
						model.getValueAt(selectedRow, 5).toString()
				);
				
				BuyBookDialog.startWindow(null, book, currentUser);
			}
		});
		buyBtn.setForeground(new Color(222, 222, 222));
		buyBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		buyBtn.setFocusPainted(false);
		buyBtn.setContentAreaFilled(false);
		buyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		buyBtn.setBackground(new Color(145, 74, 23));
		buyBtn.setBounds(40, 335, 131, 50);
		getContentPane().add(buyBtn);
		
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
				AddMoneyDialog.startWindow(null, currentUser);
				
				if(AddMoneyDialog.success) {
					currentUser.setMoney(AddMoneyDialog.moneyAdded);
				}
			}
		});
		addMoneyBtn.setForeground(new Color(222, 222, 222));
		addMoneyBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		addMoneyBtn.setFocusPainted(false);
		addMoneyBtn.setContentAreaFilled(false);
		addMoneyBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		addMoneyBtn.setBackground(new Color(145, 74, 23));
		addMoneyBtn.setBounds(269, 335, 131, 50);
		getContentPane().add(addMoneyBtn);
		
		JButton viewOrdersBtn = new JButton("View Orders");
		viewOrdersBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				viewOrdersBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				viewOrdersBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
		});
		viewOrdersBtn.setForeground(new Color(222, 222, 222));
		viewOrdersBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		viewOrdersBtn.setFocusPainted(false);
		viewOrdersBtn.setContentAreaFilled(false);
		viewOrdersBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		viewOrdersBtn.setBackground(new Color(145, 74, 23));
		viewOrdersBtn.setBounds(505, 335, 131, 50);
		getContentPane().add(viewOrdersBtn);
		
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 38));
		
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        columnModel = table.getColumnModel();
        
		columnModel.getColumn(0).setPreferredWidth(20);
		columnModel.getColumn(0).setResizable(false);
		columnModel.getColumn(0).setCellRenderer(rightRenderer);
	    
		columnModel.getColumn(1).setPreferredWidth(40);
		columnModel.getColumn(1).setResizable(false);
		columnModel.getColumn(1).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(2).setPreferredWidth(230);
		columnModel.getColumn(2).setResizable(false);
		columnModel.getColumn(2).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(3).setPreferredWidth(120);
		columnModel.getColumn(3).setResizable(false);
		columnModel.getColumn(3).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(4).setResizable(false);
		columnModel.getColumn(4).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(5).setResizable(false);
		columnModel.getColumn(5).setCellRenderer(rightRenderer);
		
	    if(!ViewBooks.fetchBooks(table)) {
	    	DialogsHandler.SQLErr(null, "The application failed to read the books.");
	    	dispatchEvent(new WindowEvent(null, WindowEvent.WINDOW_CLOSING));
	    }
	}
}
