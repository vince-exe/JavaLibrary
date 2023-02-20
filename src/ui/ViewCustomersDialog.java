package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import database.Book;
import uiUtils.DialogsHandler;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
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
			dialog.setModal(true);
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
		getContentPane().setBackground(new Color(105, 50, 12));
		setAutoRequestFocus(false);
		setResizable(false);
		setTitle("Customers Menu");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewCustomersDialog.class.getResource("/ui/resources/icon.png")));
		setBounds(100, 100, 697, 388);
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
		dateLabel.setBounds(524, 266, 131, 44);
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
		});

		btnUpdate.setForeground(new Color(222, 222, 222));
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnUpdate.setBackground(new Color(145, 74, 23));
		btnUpdate.setBounds(217, 266, 129, 47);
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
		btnRemove.setBounds(380, 266, 129, 47);
		getContentPane().add(btnRemove);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(158, 324, 348, 19);
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
	    
	    JButton moreInfoBtn = new JButton("More Info");
	    moreInfoBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		moreInfoBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		moreInfoBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
	    	}
	    });
	    moreInfoBtn.setForeground(new Color(222, 222, 222));
	    moreInfoBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
	    moreInfoBtn.setFocusPainted(false);
	    moreInfoBtn.setContentAreaFilled(false);
	    moreInfoBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
	    moreInfoBtn.setBackground(new Color(145, 74, 23));
	    moreInfoBtn.setBounds(47, 266, 129, 47);
	    getContentPane().add(moreInfoBtn);
	    
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