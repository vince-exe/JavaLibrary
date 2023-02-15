package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import database.Book;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ViewBooks {

	private JFrame frmBooksMenu;
	private JTextField searchBox;
	private JButton searchBtn;
	private JButton resetBtn;
	private JTable table;
	
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
		
		JButton saveExitBtn = new JButton("Save | Exit");
		saveExitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				saveExitBtn.setBorder(new LineBorder(new Color(64, 38, 11), 5));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				saveExitBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
			}
		});
		saveExitBtn.setForeground(new Color(222, 222, 222));
		saveExitBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		saveExitBtn.setFocusPainted(false);
		saveExitBtn.setContentAreaFilled(false);
		saveExitBtn.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		saveExitBtn.setBackground(new Color(145, 74, 23));
		saveExitBtn.setBounds(20, 19, 129, 47);
		frmBooksMenu.getContentPane().add(saveExitBtn);
		
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
		searchBox.setBounds(179, 22, 254, 44);
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
		searchBtn.setBounds(460, 22, 73, 44);
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
		resetBtn.setBounds(550, 22, 73, 44);
		frmBooksMenu.getContentPane().add(resetBtn);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(new Color(222, 222, 222));
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		table.setBorder(new LineBorder(new Color(64, 38, 11), 4));
		table.setBackground(new Color(145, 74, 23));
		table.setBounds(20, 90, 544, 237);
		frmBooksMenu.getContentPane().add(table);
		frmBooksMenu.setBounds(100, 100, 697, 394);
		frmBooksMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		ArrayList<database.Book> books = database.Database.getBooks();
		for(Book book : books) {
			System.out.print("\nID: " + book.getId());
			System.out.print("\nPRICE: " + book.getPrice());
			System.out.print("\nTITLE: " + book.getTitle());
			System.out.print("\nISBN: " + book.getISBN());
			System.out.print("\nFirst Name " + book.getAuthorFName());
			System.out.print("\nLast Name: " + book.getAuthorLName());
			System.out.println();
		}
	}
}
