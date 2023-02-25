package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import database.Admin;
import uiUtils.DialogsHandler;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewAdminDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private String columnsName[] = {"First Name", "Last Name", "BirthDate", "Username", "Email", "Password"};
	private TableColumnModel columnModel;
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args) {
		try {
			ViewAdminDialog dialog = new ViewAdminDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModalityType(DEFAULT_MODALITY_TYPE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean fetchAdmins(JTable table) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		ArrayList<database.Admin> array = database.Database.getAdmins();
		
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
	public ViewAdminDialog() {
		getContentPane().setBackground(new Color(105, 50, 12));
		setTitle("Admin Menu");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewAdminDialog.class.getResource("/ui/resources/icon.png")));
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 697, 388);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 681, -51);
		contentPanel.setBackground(new Color(105, 50, 12));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		table = new JTable();
		table.setBounds(15, 11, 640, 237);
		contentPanel.add(table);
		
		JLabel dateLabel = new JLabel("20/02/2023");
		dateLabel.setBounds(524, 266, 131, 44);
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(222, 222, 222));
		dateLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		contentPanel.add(dateLabel);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(327, 266, 129, 47);
		btnRemove.setForeground(new Color(222, 222, 222));
		btnRemove.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnRemove.setFocusPainted(false);
		btnRemove.setContentAreaFilled(false);
		btnRemove.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnRemove.setBackground(new Color(145, 74, 23));
		contentPanel.add(btnRemove);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(116, 266, 129, 47);
		btnUpdate.setForeground(new Color(222, 222, 222));
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnUpdate.setBackground(new Color(145, 74, 23));
		contentPanel.add(btnUpdate);
		
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
	    
	    if(!fetchAdmins(table)) {
	    	DialogsHandler.SQLErr(null, "The application failed to read the customers.");
	    	dispose();
	    }
	    
	    JLabel dateLabel_1 = new JLabel("20/02/2023");
	    dateLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    dateLabel_1.setForeground(new Color(222, 222, 222));
	    dateLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
	    dateLabel_1.setBounds(524, 266, 131, 44);
	    getContentPane().add(dateLabel_1);
	    
	    JButton delBtn = new JButton("Remove");
	    delBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		delBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		delBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
	    	}
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() == -1) {
					DialogsHandler.invalidRow(null);
					return;
				}
				String emailUsr = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
				
				if(emailUsr.equals(AdminWindow.getAdmin().getEmail())) {
					DialogsHandler.generalWarning(null, "Invalid Delete", "You can't delete yourself");
					return;
				}
				
				int resp = DialogsHandler.YesNoDialog(null, "Confirm Box", "Are you sure that you want to remove the user \"" + emailUsr + "\"");
				if(resp != 0) {
					return;
				}
				
				int usrId = database.Database.getUserID(emailUsr);
				if(usrId == -1) {
					DialogsHandler.SQLErr(null, "The application failed to remove the admin");
					return;
				}
				
				if(!database.Database.deleteAdmin(usrId)) {
					DialogsHandler.SQLErr(null, "The application failed to remove the admin");
					return;
				}
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);

				if(!fetchAdmins(table)) {
			    	DialogsHandler.SQLErr(null, "The application failed to read the customers.");
			    	dispose();
				}
	    	}
	    });
	    delBtn.setForeground(new Color(222, 222, 222));
	    delBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
	    delBtn.setFocusPainted(false);
	    delBtn.setContentAreaFilled(false);
	    delBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
	    delBtn.setBackground(new Color(145, 74, 23));
	    delBtn.setBounds(218, 266, 129, 47);
	    getContentPane().add(delBtn);
	    
	    JButton updateBtn = new JButton("Update");
	    updateBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() == -1) {
					DialogsHandler.invalidRow(null);
					return;
				}
				
				String emailUsr = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
				
				if(emailUsr.equals(AdminWindow.getAdmin().getEmail())) {
					DialogsHandler.generalWarning(null, "Invalid Update", "You can't update yourself");
					return;
				}
				
				int usrId = database.Database.getUserID(emailUsr);
				if(usrId == -1) {
					DialogsHandler.SQLErr(null, "The application failed to open the update window");
					return;
				}
				
				Admin admin = database.Database.getAdmin(usrId);
				if(admin == null) {
			    	DialogsHandler.SQLErr(null, "The application failed to update.");
			    	return;
				}
				
				UpdtAdminDialog.startWindow(null, admin);
				if(UpdtAdminDialog.successUpdate) {
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setRowCount(0);

					if(!fetchAdmins(table)) {
				    	DialogsHandler.SQLErr(null, "The application failed to read the admins.");
				    	dispose();
					}
				}
	    	}
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		updateBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		updateBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
	    	}
	    });
	    updateBtn.setForeground(new Color(222, 222, 222));
	    updateBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
	    updateBtn.setFocusPainted(false);
	    updateBtn.setContentAreaFilled(false);
	    updateBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
	    updateBtn.setBackground(new Color(145, 74, 23));
	    updateBtn.setBounds(25, 266, 129, 47);
	    getContentPane().add(updateBtn);
	    
	    JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
	    lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setForeground(new Color(186, 186, 186));
	    lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
	    lblNewLabel.setBounds(323, 321, 348, 19);
	    getContentPane().add(lblNewLabel);
	}
}
