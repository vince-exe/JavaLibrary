package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import database.Book;
import database.Orders;
import database.User;
import uiUtils.DialogsHandler;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewOrdersDialog extends JDialog {
	private JTable table;
	private String columnsName[] = {"Id", "Title", "Quantity", "Price", "ISBN"	};
	private static User currentUser;
	
	private TableColumnModel columnModel;
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args, User user) {
		currentUser = user;
		try {
			ViewOrdersDialog dialog = new ViewOrdersDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModalityType(DEFAULT_MODALITY_TYPE);
			dialog.setVisible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean fetchOrders(JTable table, int userId) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		ArrayList<Orders> array = database.Database.getOrders(userId);
		if(array == null) {
			return false;
		}
		
		for(int i = 0; i < array.size(); i++) {
			String[] data = new String[5];
			
			data[0] = Integer.toString(array.get(i).getId());
			data[1] = array.get(i).getBookObj().getTitle();
			data[2] = Integer.toString(array.get(i).getQuantity());
			data[3] = Double.toString(array.get(i).getBookObj().getPrice());
			data[4] = array.get(i).getBookObj().getISBN();

			tableModel.addRow(data);
		}
		
		table.setModel(tableModel);
		return true;
	}
	
	/**
	 * Create the dialog.
	 */
	public ViewOrdersDialog() {
		getContentPane().setBackground(new Color(105, 50, 12));
		getContentPane().setLayout(null);
		
		JLabel dateLabel = new JLabel("28/02/2023");
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(222, 222, 222));
		dateLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		dateLabel.setBounds(512, 11, 131, 44);
		getContentPane().add(dateLabel);
		
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
		table.setBounds(15, 11, 640, 237);
		table.getTableHeader().setBackground(new Color(145, 74, 23));
		table.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		table.getTableHeader().setForeground(new Color(222, 222, 222));
		table.getTableHeader().setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, new Color(64, 38, 11)));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(new Color(105, 50, 12));
		scrollPane.setEnabled(false);
		scrollPane.getViewport().getView().setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		scrollPane.setBackground(new Color(105, 50, 12));
		scrollPane.getVerticalScrollBar().setBackground(new Color(145, 74, 23));
		scrollPane.setBounds(15, 76, 640, 237);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 38));
		
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
		columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(25);
		columnModel.getColumn(0).setResizable(false);
		columnModel.getColumn(0).setCellRenderer(rightRenderer);
	    
		columnModel.getColumn(1).setPreferredWidth(230);
		columnModel.getColumn(1).setResizable(false);
		columnModel.getColumn(1).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(2).setPreferredWidth(25);
		columnModel.getColumn(2).setResizable(false);
		columnModel.getColumn(2).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(3).setPreferredWidth(50);
		columnModel.getColumn(3).setResizable(false);
		columnModel.getColumn(3).setCellRenderer(rightRenderer);
		
		columnModel.getColumn(4).setResizable(false);
		columnModel.getColumn(4).setCellRenderer(rightRenderer);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
	    Class<?> col_class = table.getColumnClass(0);
	    table.setDefaultEditor(col_class, null);  
	    
		JButton btnRemove = new JButton("Remove");
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() == -1) {
					DialogsHandler.invalidRow(null);
					return;
				}
				int orderId = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				String bookName = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
				
				int resp = DialogsHandler.YesNoDialog(null, "Confirm Box", "Do you want to remove the order with the book \"" + bookName + "\"");
				if(resp != 0) {
					return;
				}
				
				if(!database.Database.deleteOrder(orderId)) {
					DialogsHandler.SQLErr(null, "The application failed to delete the order.");
					return;
				}
				
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
				
				if(!fetchOrders(table, currentUser.getIdUser())) {
			    	DialogsHandler.SQLErr(null, "The application failed to read the books.");
			    	dispose();
			    	return;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRemove.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRemove.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
		});
		btnRemove.setForeground(new Color(222, 222, 222));
		btnRemove.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnRemove.setFocusPainted(false);
		btnRemove.setContentAreaFilled(false);
		btnRemove.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnRemove.setBackground(new Color(145, 74, 23));
		btnRemove.setBounds(50, 11, 129, 47);
		getContentPane().add(btnRemove);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(158, 324, 348, 19);
		getContentPane().add(lblNewLabel);
		setResizable(false);
		setTitle("View Orders");
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewOrdersDialog.class.getResource("/ui/resources/icon.png")));
		setBounds(100, 100, 697, 388);
		
		if(!fetchOrders(table, currentUser.getIdUser())) {
	    	DialogsHandler.SQLErr(null, "The application failed to read the books.");
	    	dispose();
	    	return;
		}
	}
}
