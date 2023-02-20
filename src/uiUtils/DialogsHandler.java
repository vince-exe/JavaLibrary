package uiUtils;

import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogsHandler {
	public static final ImageIcon infoIcon = new ImageIcon("src/ui/resources/infoIcon.png");
	public static final ImageIcon warningIcon = new ImageIcon("src/ui/resources/warningIcon.png");
	public static final ImageIcon errorIcon = new ImageIcon("src/ui/resources/errorIcon.png");
	public static final ImageIcon questionIcon = new ImageIcon("src/ui/resources/questionIcon.png");

	public static void emailLengthErr(JFrame frame, int maxLen, int minLen) {
		JOptionPane.showMessageDialog(
				frame,
				"The email has to be greater then " + minLen + " characters and lesser then " + maxLen + " characters",
				"Invalid Email", JOptionPane.WARNING_MESSAGE, warningIcon);
    }
	
	public static void emailDiffFrom(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid email",
				"Invalid Email", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void invalidRow(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"You have to selecte a row.",
				"Invalid Selection", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void pwdDiffFrom(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid password",
				"Invalid Password", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void SQLErr(JFrame frame, String error) {
		JOptionPane.showMessageDialog(
				frame,
				error,
				"Database Error", JOptionPane.ERROR_MESSAGE, errorIcon);
	}
	
	public static void registrationErr(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"The application failed to register your account",
				"Fatal Error", JOptionPane.ERROR_MESSAGE, errorIcon);
	}
	
	public static void registrationSuccess(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Successfully registered, go in the login page to process the login :)",
				"Registration Success", JOptionPane.INFORMATION_MESSAGE, infoIcon);
	}
	
	public static void emailExistErr(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"There is already an account with this email",
				"Invalid Email", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void birdDateErr(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid birday date ( YYYY / MM / DD ).",
				"Invalid Date", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void emailSuffixErr(JFrame frame, String emailType) {
		JOptionPane.showMessageDialog(
			frame,
			"The email has to end with '" + emailType +"'",
			"Invalid Email", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void pwdLengthErr(JFrame frame, int maxLen, int minLen) {
		JOptionPane.showMessageDialog(
				frame,
				"The password has to be greater then " + minLen + " characters and lesser then " + maxLen + " characters",
				"Invalid Password", JOptionPane.WARNING_MESSAGE, warningIcon);
    }
	
	public static void namesLenghtErr(JFrame frame, int maxLen, int minLen) {
		JOptionPane.showMessageDialog(
				frame,
				"The first / last name has to be greater then " + minLen + " characters and lesser then " + maxLen + " characters",
				"Invalid Names", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void nickLenErr(JFrame frame, int maxLen, int minLen) {
		JOptionPane.showMessageDialog(
				frame,
				"The username has to be greater then " + minLen + " characters and lesser then " + maxLen + " characters",
				"Invalid Username", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void namesDiffFrom(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid first / last name",
				"Invalid Names", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void nickDiffFrom(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid username",
				"Invalid Username", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void loginFail(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"There is no account with this email and password",
				"Invalid Login", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void loginErr(JFrame frame, SQLException e) {
		JOptionPane.showMessageDialog(
				frame,
				"Login Error: " + e.getMessage(),
				"Fatal Error", JOptionPane.ERROR_MESSAGE, errorIcon);
	}
	
	public static void readSettingsJson() {
		JOptionPane.showMessageDialog(
				null,
				"System Error: The application failed to read the settings.json file",
				"Fatal Error", JOptionPane.ERROR_MESSAGE, errorIcon);
	}
	
	public static void readSettingsJsonIO() {
		JOptionPane.showMessageDialog(
				null,
				"System Error: I / O Error.",
				"Fatal Error", JOptionPane.ERROR_MESSAGE, errorIcon);
	}
	
	public static void authorName(JFrame frame, String subject, int min, int max) {
		JOptionPane.showMessageDialog(
				frame,
				"Invalid author " + subject + " name" + "[ min: " + min + " max: " + max + " ]",
				"Invalid " + subject + " name", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void priceErr(JFrame frame, int min, int max) {
		JOptionPane.showMessageDialog(
				frame,
				"Invalid price [ min: " + min + " max: " + max + " ]",
				"Invalid Price", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void isbnERR(JFrame frame, int min, int max) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid ISBN, it doesn't contain spaces and has [ min: " + min + " max: " + max + " ]",
				"Invalid ISBN", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void titleErr(JFrame frame, int min, int max) {
		JOptionPane.showMessageDialog(
				frame,
				"Please insert a valid title, it doesn't contain spaces and has [ min: " + min + " max: " + max + " ]",
				"Invalid ISBN", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void bookExist(JFrame frame) {
		JOptionPane.showMessageDialog(
				frame,
				"There is already a book with this ISBN or title",
				"Invalid Price", JOptionPane.WARNING_MESSAGE, warningIcon);
	}
	
	public static void infoSuccess(JFrame frame, String title, String msg) {
		JOptionPane.showMessageDialog(
				frame,
				msg,
				title, JOptionPane.INFORMATION_MESSAGE, infoIcon);
	}
	
	public static void generalWarning(JFrame frame, String title, String msg) {
		JOptionPane.showMessageDialog(
				frame,
				msg,
				title, JOptionPane.INFORMATION_MESSAGE, warningIcon);
	}
	
	public static int YesNoDialog(JFrame frame, String title, String message) {
		return JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, questionIcon);
	}
}
