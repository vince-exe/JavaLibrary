package ui;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogsHandler {
	public static void emailLengthErr(JFrame frame, int maxLen, int minLen) {
		JOptionPane.showMessageDialog(
				frame,
				"The email has to be greater then " + minLen + " characters and lesser then " + maxLen + " characters",
				"Wrong Email", JOptionPane.WARNING_MESSAGE);
    }
	
	public static void emailSuffixErr(JFrame frame, String emailType) {
		JOptionPane.showMessageDialog(
			frame,
			"The email has to end with '" + emailType +"'",
			"Wrong Email", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void pwdLengthErr(JFrame frame, int maxLen, int minLen) {
		JOptionPane.showMessageDialog(
				frame,
				"The password has to be greater then " + minLen + " characters and lesser then " + maxLen + " characters",
				"Wrong Password", JOptionPane.WARNING_MESSAGE);
    }
	
	public static void loginFail(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"There is no account with this email and password",
				"Wrong Login", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void loginErr(JFrame frame, SQLException e) {
		JOptionPane.showMessageDialog(
				frame,
				"Login Error: " + e.getMessage(),
				"Fatal Error", JOptionPane.ERROR_MESSAGE);
	}
}
