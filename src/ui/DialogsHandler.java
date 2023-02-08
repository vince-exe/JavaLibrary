package ui;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogsHandler {
	public static void emailLengthErr(JFrame frame, int maxLen, int minLen) {
		JOptionPane.showMessageDialog(
				frame,
				"The email has to be greater then " + minLen + " characters and lesser then " + maxLen + " characters",
				"Invalid Email", JOptionPane.WARNING_MESSAGE);
    }
	
	public static void emailDiffFrom(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid email",
				"Invalid Email", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void pwdDiffFrom(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid password",
				"Invalid Password", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void SQLErr(JFrame frame, String error) {
		JOptionPane.showMessageDialog(
				frame,
				error,
				"Database Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void registrationErr(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"The application failed to register your account",
				"Fatal Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void registrationSuccess(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Successfully registered, go in the login page to process the login :)",
				"Registration Success", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void emailExistErr(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"There is already an account with this email",
				"Invalid Email", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void birdDateErr(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid birday date ( YYYY / MM / DD ).",
				"Invalid Date", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void emailSuffixErr(JFrame frame, String emailType) {
		JOptionPane.showMessageDialog(
			frame,
			"The email has to end with '" + emailType +"'",
			"Invalid Email", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void pwdLengthErr(JFrame frame, int maxLen, int minLen) {
		JOptionPane.showMessageDialog(
				frame,
				"The password has to be greater then " + minLen + " characters and lesser then " + maxLen + " characters",
				"Invalid Password", JOptionPane.WARNING_MESSAGE);
    }
	
	public static void namesLenghtErr(JFrame frame, int maxLen, int minLen) {
		JOptionPane.showMessageDialog(
				frame,
				"The first / last name has to be greater then " + minLen + " characters and lesser then " + maxLen + " characters",
				"Invalid Names", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void nickLenErr(JFrame frame, int maxLen, int minLen) {
		JOptionPane.showMessageDialog(
				frame,
				"The username has to be greater then " + minLen + " characters and lesser then " + maxLen + " characters",
				"Invalid Username", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void namesDiffFrom(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid first / last name",
				"Invalid Names", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void nickDiffFrom(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid username",
				"Invalid Username", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void loginFail(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"There is no account with this email and password",
				"Invalid Login", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void loginErr(JFrame frame, SQLException e) {
		JOptionPane.showMessageDialog(
				frame,
				"Login Error: " + e.getMessage(),
				"Fatal Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void readSettingsJson() {
		JOptionPane.showMessageDialog(
				null,
				"System Error: The application failed to read the settings.json file",
				"Fatal Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void readSettingsJsonIO() {
		JOptionPane.showMessageDialog(
				null,
				"System Error: I / O Error.",
				"Fatal Error", JOptionPane.ERROR_MESSAGE);
	}
}
