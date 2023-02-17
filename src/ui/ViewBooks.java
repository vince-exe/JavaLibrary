package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import database.Book;
import main.MyMatrix;
import uiUtils.DialogsHandler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import javax.swing.JScrollBar;

public class ViewBooks extends JTable{

	private JFrame frmBooksMenu;
	private JTextField searchBox;
	private JButton searchBtn;
	private JButton resetBtn;
	
	private String columnsName[] = {"Id", "Price", "Title", "ISBN", "Author F.N", "Author L.N"};
	private TableColumnModel columnModel;
	private JButton btnUpdate;
	private JTable table;
	private JLabel dateLabel;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void startWindow(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooks window = new ViewBooks();
					window.frmBooksMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	private MyMatrix<String> fetchBooks() {
		ArrayList<Book> array = database.Database.getBooks();		
		
		if(array == null) {
			DialogsHandler.SQLErr(null, "The database failed to send the book");
			new WindowEvent(frmBooksMenu, WindowEvent.WINDOW_CLOSING);
		}
		
		MyMatrix<String> myMatrix = new MyMatrix<String>(array.size(), 6);
		
		for(int i = 0; i < array.size(); i++) {
			myMatrix.add(i, 0, Integer.toString(array.get(i).getId()));
			myMatrix.add(i, 1, Double.toString(array.get(i).getPrice()));
			myMatrix.add(i, 2, array.get(i).getTitle());
			myMatrix.add(i, 3, array.get(i).getISBN());
			myMatrix.add(i, 4, array.get(i).getAuthorFName());
			myMatrix.add(i, 5, array.get(i).getAuthorLName());
		}
		return myMatrix;
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
		
		searchBox = new JTextField();
		searchBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(!searchBox.getText().equals("Search Box")) {
					return;
				}
				searchBox.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(searchBox.getText().length() == 0) {
					searchBox.setText("Search Box");
				}
			}
		});
		searchBox.setText("Search Box");
		searchBox.setHorizontalAlignment(SwingConstants.CENTER);
		searchBox.setForeground(new Color(222, 222, 222));
		searchBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		searchBox.setColumns(10);
		searchBox.setCaretColor(new Color(222, 222, 222));
		searchBox.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		searchBox.setBackground(new Color(145, 74, 23));
		searchBox.setBounds(20, 22, 254, 44);
		frmBooksMenu.getContentPane().add(searchBox);
		
		searchBtn = new JButton("Go");
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				searchBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
		});
		searchBtn.setForeground(new Color(222, 222, 222));
		searchBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		searchBtn.setFocusPainted(false);
		searchBtn.setContentAreaFilled(false);
		searchBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		searchBtn.setBackground(new Color(145, 74, 23));
		searchBtn.setBounds(307, 22, 80, 44);
		frmBooksMenu.getContentPane().add(searchBtn);
		
		resetBtn = new JButton("Reset");
		resetBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				resetBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
		});
		resetBtn.setForeground(new Color(222, 222, 222));
		resetBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		resetBtn.setFocusPainted(false);
		resetBtn.setContentAreaFilled(false);
		resetBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		resetBtn.setBackground(new Color(145, 74, 23));
		resetBtn.setBounds(419, 22, 80, 44);
		frmBooksMenu.getContentPane().add(resetBtn);
		
		MyMatrix<String> myMatrix = fetchBooks();
	    
		table = new JTable(myMatrix.getMatrix(), columnsName);
		table.setSelectionBackground(new Color(170, 80, 19));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.print("\nRow: " + table.getSelectedRow());
			}
		});
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(new Color(222, 222, 222));
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		table.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		table.setBackground(new Color(145, 74, 23));
		table.setBounds(20, 90, 640, 237);
		table.getTableHeader().setBackground(new Color(145, 74, 23));
		table.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		table.getTableHeader().setForeground(table.getGridColor());
		table.getTableHeader().setBorder(BorderFactory.createMatteBorder(4, 4, 0, 4, new Color(64, 38, 11)));
			
		frmBooksMenu.setBounds(100, 100, 697, 472);
		frmBooksMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		scrollPane = new JScrollPane(table);
		scrollPane.setRequestFocusEnabled(false);
		scrollPane.getViewport().getView().setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		scrollPane.setBackground(new Color(105, 50, 12));
		scrollPane.getVerticalScrollBar().setBackground(new Color(145, 74, 23));
		scrollPane.setBounds(20, 90, 640, 237);
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
		});
		btnUpdate.setForeground(new Color(222, 222, 222));
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnUpdate.setBackground(new Color(145, 74, 23));
		btnUpdate.setBounds(179, 338, 129, 47);
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
		});
		btnRemove.setForeground(new Color(222, 222, 222));
		btnRemove.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnRemove.setFocusPainted(false);
		btnRemove.setContentAreaFilled(false);
		btnRemove.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		btnRemove.setBackground(new Color(145, 74, 23));
		btnRemove.setBounds(358, 338, 129, 47);
		frmBooksMenu.getContentPane().add(btnRemove);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2023 Vincenzo Caliendo. All rights reserved");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(186, 186, 186));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(172, 409, 348, 19);
		frmBooksMenu.getContentPane().add(lblNewLabel);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");  
		LocalDateTime now = LocalDateTime.now();  
		
		dateLabel = new JLabel(dtf.format(now));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
		dateLabel.setForeground(new Color(222, 222, 222));
		dateLabel.setBounds(529, 22, 131, 44);
		frmBooksMenu.getContentPane().add(dateLabel);
	}
}
