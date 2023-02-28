package ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import uiUtils.DialogsHandler;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;

public class ViewCustomersDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String columnsName[] = {"First Name", "Last Name", "BirthDate", "Username", "Email", "Password"};
	private JTable table;
	private TableColumnModel columnModel;	
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args) {
		try {
			ViewCustomersDialog dialog = new ViewCustomersDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModalityType(DEFAULT_MODALITY_TYPE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean fetchUsers(JTable table) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		ArrayList<database.User> array = database.Database.getUsers();
		
		if(array == null) {
			return false;
		}
		
		for(int i = 0; i < array.size(); i++) {
			String[] data = new String[6];
			
			data[0] = array.get(i).getFirstName();
			data[1] = array.get(i).getLastName();
			data[2] = array.get(i).getBDay();
			data[3] = array.get(i).getUsername();
			data[4] = array.get(i).getEmail();
			data[5] = array.get(i).getPassword();
			
			tableModel.addRow(data);
		}
		table.setModel(tableModel);
		return true;
	}
	
	/**
	 * Create the dialog.
	 */
	public ViewCustomersDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(105, 50, 12));
		setAutoRequestFocus(false);
		setResizable(false);
		setTitle("Customers Menu");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewCustomersDialog.class.getResource("/ui/resources/icon.png")));
		setBounds(100, 100, 697, 437);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBackground(new Color(105, 50, 12));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel dateLabel = new JLabel("20/02/2023");
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(222, 222, 222));
		dateLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		dateLabel.setBounds(533, 343, 131, 44);
		getContentPane().add(dateLabel);
		
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
				if(table.getSelectedRow() == -1) {
					DialogsHandler.invalidRow(null);
					return;
				}
				String emailUsr = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
				
				if(emailUsr.equals(AdminWindow.getAdmin().getEmail())) {
					DialogsHandler.generalWarning(null, "Invalid Customer", "You can't update yourself");
					return;
				}
				
				database.User usr = database.Database.getUser(emailUsr);
				if(usr == null) {
					DialogsHandler.SQLErr(null, "The application failed to update the customer");
					return;
				}
				
				UpdtCustomerDialog.successUpdate = false;
				UpdtCustomerDialog.startWindow(columnsName, usr);
				
				if(UpdtCustomerDialog.successUpdate) {
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setRowCount(0);

					if(!fetchUsers(table)) {
				    	DialogsHandler.SQLErr(null, "The application failed to read the customers.");
				    	dispose();
					}
				}
			}
		});

		btnUpdate.setForeground(new Color(222, 222, 222));
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnUpdate.setBackground(new Color(145, 74, 23));
		btnUpdate.setBounds(363, 292, 97, 47);
		getContentPane().add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRemove.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRemove.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() == -1) {
					DialogsHandler.invalidRow(null);
					return;
				}
				String emailUsr = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
				
				if(emailUsr.equals(AdminWindow.getAdmin().getEmail())) {
					DialogsHandler.generalWarning(null, "Invalid Customer", "You can't delete yourself");
					return;
				}
				
				int resp = DialogsHandler.YesNoDialog(null, "Confirm Box", "Are you sure that you want to remove the user \"" + emailUsr + "\"");
				if(resp != 0) {
					return;
				}
				
				int userId = database.Database.getUserID(emailUsr);
				if(userId == -1) {
					DialogsHandler.SQLErr(null, "The application failed to remove the user");
					return;
				}
				
				if(!database.Database.deleteUser(userId) || !database.Database.deletePerson(userId)) {
					DialogsHandler.SQLErr(null, "The application failed to remove the user");
					return;
				}
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);

				if(!fetchUsers(table)) {
			    	DialogsHandler.SQLErr(null, "The application failed to read the customers.");
			    	dispose();
				}
			}
		});

		btnRemove.setForeground(new Color(222, 222, 222));
		btnRemove.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnRemove.setFocusPainted(false);
		btnRemove.setContentAreaFilled(false);
		btnRemove.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnRemove.setBackground(new Color(145, 74, 23));
		btnRemove.setBounds(524, 292, 97, 47);
		getContentPane().add(btnRemove);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(175, 368, 348, 19);
		getContentPane().add(lblNewLabel);
		
		table = new JTable();
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
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getViewport().setBackground(new Color(105, 50, 12));
		scrollPane.setEnabled(false);
		scrollPane.getViewport().getView().setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		scrollPane.setBackground(new Color(105, 50, 12));
		scrollPane.getVerticalScrollBar().setBackground(new Color(145, 74, 23));
		scrollPane.setBounds(15, 11, 640, 237);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportView(table);
		
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 38));
		
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(60);
		columnModel.getColumn(0).setResizable(false);
		columnModel.getColumn(0).setCellRenderer(rightRenderer);
	    
		columnModel.getColumn(1).setPreferredWidth(60);
		columnModel.getColumn(1).setResizable(false);
		columnModel.getColumn(1).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(2).setPreferredWidth(40);
		columnModel.getColumn(2).setResizable(false);
		columnModel.getColumn(2).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(3).setResizable(false);
		columnModel.getColumn(3).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(4).setResizable(false);
		columnModel.getColumn(4).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(5).setResizable(false);
		columnModel.getColumn(5).setCellRenderer(rightRenderer);

		
	    Class<?> col_class = table.getColumnClass(0);
	    table.setDefaultEditor(col_class, null);  
	    getContentPane().add(scrollPane);
	    
	    JButton moreInfoBtn = new JButton("More");
	    moreInfoBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		moreInfoBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		moreInfoBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
	    	}
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() == -1) {
					DialogsHandler.invalidRow(null);
					return;
				}
				String emailUsr = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
				
				int usrId = database.Database.getUserID(emailUsr);
				int nOrders = database.Database.getNOrders(usrId);
				
				database.User usr = database.Database.getUser(emailUsr);
				
				if(usr == null || usrId == -1 || nOrders == -1) {
					DialogsHandler.SQLErr(null, "The application failed to update the customer");
					return;
				}
				
				MoreInfoDialog.startWindow(null, usr, nOrders);
	    	}
	    });
	    moreInfoBtn.setForeground(new Color(222, 222, 222));
	    moreInfoBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
	    moreInfoBtn.setFocusPainted(false);
	    moreInfoBtn.setContentAreaFilled(false);
	    moreInfoBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
	    moreInfoBtn.setBackground(new Color(145, 74, 23));
	    moreInfoBtn.setBounds(208, 292, 97, 47);
	    getContentPane().add(moreInfoBtn);
	    
	    JButton makeAdmin = new JButton("Make Admin");
	    makeAdmin.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() == -1) {
					DialogsHandler.invalidRow(null);
					return;
				}
				String emailUsr = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
				
				if(emailUsr.equals(AdminWindow.getAdmin().getEmail())) {
					DialogsHandler.generalWarning(null, "Invalid Customer", "You can't promote yourself");
					return;
				}
				
				int resp = DialogsHandler.YesNoDialog(null, "Confirm Box", "Are you sure that you want to promote the user \"" + emailUsr + "\"");
				if(resp != 0) {
					return;
				}
				
				int usrId = database.Database.getUserID(emailUsr);
				if(usrId == -1) {
					DialogsHandler.SQLErr(null, "The application failed to promote the customer");
					return;
				}
				
				int isAdmin = database.Database.isAnAdmin(usrId);
				if(isAdmin == -1) {
					DialogsHandler.SQLErr(null, "The application failed to promote the customer");
					return;
				}
				if(isAdmin == 1) {
					DialogsHandler.SQLErr(null, "The user \"" + emailUsr + "\" is already an admin");
					return;
				}
				
				if(database.Database.insertAdmin(usrId)) {
					DialogsHandler.infoSuccess(null, "Promotion Success", "Successfully promoted \"" + emailUsr + "\" to admin");
				}
				else {
					DialogsHandler.SQLErr(null, "The application failed to promote the customer");
				}
	    	}
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		makeAdmin.setBorder(new LineBorder(new Color(64, 38, 11), 5));
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		makeAdmin.setBorder(new LineBorder(new Color(64, 38, 11), 4));
	    	}
	    });
	    makeAdmin.setForeground(new Color(222, 222, 222));
	    makeAdmin.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
	    makeAdmin.setFocusPainted(false);
	    makeAdmin.setContentAreaFilled(false);
	    makeAdmin.setBorder(new LineBorder(new Color(64, 38, 11), 4));
	    makeAdmin.setBackground(new Color(145, 74, 23));
	    makeAdmin.setBounds(15, 266, 140, 47);
	    getContentPane().add(makeAdmin);
	    
	    JButton viewOrdersBtn = new JButton("View Orders");
	    viewOrdersBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() == -1) {
					DialogsHandler.invalidRow(null);
					return;
				}
				
				String emailUsr = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
				
				int userId = database.Database.getUserID(emailUsr);
				if(userId == -1) {
					DialogsHandler.SQLErr(null, "The application failed to remove the user");
					return;
				}
				
				ViewOrdersDialog.startWindow(null, userId);
	    	}
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
	    viewOrdersBtn.setBounds(15, 324, 140, 47);
	    getContentPane().add(viewOrdersBtn);
	    
	    if(!fetchUsers(table)) {
	    	DialogsHandler.SQLErr(null, "The application failed to read the customers.");
	    	dispose();
	    }
	    
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");  
		LocalDateTime now = LocalDateTime.now();  
		
		dateLabel = new JLabel(dtf.format(now));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		dateLabel.setForeground(new Color(222, 222, 222));
		dateLabel.setBounds(524, 266, 131, 44);
	}
}
