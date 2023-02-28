package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import database.Book;
import uiUtils.DialogsHandler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Toolkit;

public class ViewBooks {

	private JFrame frmBooksMenu;
	
	private String columnsName[] = {"Id", "Price", "Title", "ISBN", "Author F.N", "Author L.N"};
	private TableColumnModel columnModel;
	private JButton btnUpdate;
	public static JTable table;
	private JLabel dateLabel;
	
	private static ViewBooks __window;
	
	public static void enableWindow() {
		__window.frmBooksMenu.setEnabled(true);
	}
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooks window = new ViewBooks();
					__window = window;
					window.frmBooksMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static boolean fetchBooks(JTable table) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		ArrayList<Book> array = database.Database.getBooks();
		if(array == null) {
			return false;
		}
		
		for(int i = 0; i < array.size(); i++) {
			String[] data = new String[6];
			
			data[0] = Integer.toString(array.get(i).getId());
			data[1] = Double.toString(array.get(i).getPrice());
			data[2] = array.get(i).getTitle();
			data[3] = array.get(i).getISBN();
			data[4] = array.get(i).getAuthorFName();
			data[5] = array.get(i).getAuthorLName();
			
			tableModel.addRow(data);
		}
		table.setModel(tableModel);
		return true;
	}
	/**
	 * Create the application.
	 */
	public ViewBooks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBooksMenu = new JFrame();
		frmBooksMenu.setAutoRequestFocus(false);
		frmBooksMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(ViewBooks.class.getResource("/ui/resources/icon.png")));
		frmBooksMenu.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				AdminWindow.enableWindow();
			}
		});
		frmBooksMenu.setTitle("Books Menu");
		frmBooksMenu.setResizable(false);
		frmBooksMenu.getContentPane().setBackground(new Color(105, 50, 12));
		frmBooksMenu.getContentPane().setLayout(null);
	    
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
			
		frmBooksMenu.setBounds(100, 100, 697, 388);
		frmBooksMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(new Color(105, 50, 12));
		scrollPane.setEnabled(false);
		scrollPane.getViewport().getView().setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		scrollPane.setBackground(new Color(105, 50, 12));
		scrollPane.getVerticalScrollBar().setBackground(new Color(145, 74, 23));
		scrollPane.setBounds(15, 76, 640, 237);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportView(table);
		frmBooksMenu.getContentPane().add(scrollPane);
		
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
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
	    Class<?> col_class = table.getColumnClass(0);
	    table.setDefaultEditor(col_class, null);  
	    
	    if(!fetchBooks(table)) {
	    	DialogsHandler.SQLErr(frmBooksMenu, "The application failed to read the books.");
	    	frmBooksMenu.dispatchEvent(new WindowEvent(frmBooksMenu, WindowEvent.WINDOW_CLOSING));
	    }
	    
		btnUpdate = new JButton("Update");
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
				TableModel model = table.getModel();
				
				int selectedRow = table.getSelectedRow();
				if(selectedRow == -1) {
					DialogsHandler.invalidRow(frmBooksMenu);
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
				
				UpdtBookDialog.startWindow(columnsName, book);
				
				if(UpdtBookDialog.updateABook) {
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setRowCount(0);

					if(!fetchBooks(table)) {
				    	DialogsHandler.SQLErr(frmBooksMenu, "The application failed to read the books.");
				    	frmBooksMenu.dispatchEvent(new WindowEvent(frmBooksMenu, WindowEvent.WINDOW_CLOSING));
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
		btnUpdate.setBounds(36, 11, 129, 47);
		frmBooksMenu.getContentPane().add(btnUpdate);
		
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
					DialogsHandler.invalidRow(frmBooksMenu);
					return;
				}
				int value = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				String title = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
				
				int resp = DialogsHandler.YesNoDialog(frmBooksMenu, "Confirm Box", "Are you sure that you want to remove the book \"" + title + "\"");
				if(resp != 0) {
					return;
				}
				
				if(!database.Database.deleteBook(value)) {
					DialogsHandler.SQLErr(frmBooksMenu, "The application failed to delete the record.");
					return;
				}
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);

				if(!fetchBooks(table)) {
			    	DialogsHandler.SQLErr(frmBooksMenu, "The application failed to read the books.");
			    	frmBooksMenu.dispatchEvent(new WindowEvent(frmBooksMenu, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		btnRemove.setForeground(new Color(222, 222, 222));
		btnRemove.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnRemove.setFocusPainted(false);
		btnRemove.setContentAreaFilled(false);
		btnRemove.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnRemove.setBackground(new Color(145, 74, 23));
		btnRemove.setBounds(230, 11, 129, 47);
		frmBooksMenu.getContentPane().add(btnRemove);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(158, 324, 348, 19);
		frmBooksMenu.getContentPane().add(lblNewLabel);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");  
		LocalDateTime now = LocalDateTime.now();  
		
		dateLabel = new JLabel(dtf.format(now));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		dateLabel.setForeground(new Color(222, 222, 222));
		dateLabel.setBounds(512, 11, 131, 44);
		frmBooksMenu.getContentPane().add(dateLabel);
	}
}
