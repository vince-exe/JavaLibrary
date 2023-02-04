package ui;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AdminWindow {
	private JFrame window;
	private JLabel mainTitle;
	private JLabel citationLabel;
	
	public AdminWindow() throws IOException {
		window = new JFrame("Admin Section");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(900, 500);
		window.setResizable(false);
		window.setLocation(280, 220);
		window.setLayout(null);
		window.getContentPane().setBackground(new Color(0x69320c));
		window.setIconImage(new ImageIcon("src/ui/resources/icon.png").getImage());
		
        mainTitle = new JLabel("Java Library");
        mainTitle.setFont(new Font("Monospaced", Font.BOLD, 50));
        mainTitle.setForeground(new Color(0xdedede));
        mainTitle.setBounds(270, 1, 500, 60);
        
        citationLabel = new JLabel("\"Non ci sono amicizie più rapide di quelle tra persone che amano gli stessi libri\"");
        citationLabel.setFont(new Font("Monospaced", Font.ITALIC, 16));
        citationLabel.setForeground(new Color(0xdedede));
        citationLabel.setBounds(50, 80, 850, 30);
        
        window.add(mainTitle);
        window.add(citationLabel);
        
        window.setVisible(true);
	}
}
