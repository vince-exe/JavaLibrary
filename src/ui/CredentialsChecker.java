package ui;

import java.sql.SQLException;

import javax.swing.JFrame;

public class CredentialsChecker {
	/* include the "@gmail.com" */
	private static final int MIN_LEN_EMAIL = 12;
	private static final int MAX_LEN_EMAIL = 40;
	
	private static final String EMAIL_TYPE = "@gmail.com";
	
	private static final int MIN_LEN_PSW = 8;
	private static final int MAX_LEN_PSW = 30;
	
	public static boolean handleEmail(String value, JFrame frame) {
		if(value.length() > MAX_LEN_EMAIL || value.length() < MIN_LEN_EMAIL) {
			DialogsHandler.emailLengthErr(frame, MIN_LEN_EMAIL, MAX_LEN_EMAIL);
			return false;
		}
		
		if(!value.endsWith(EMAIL_TYPE)) { 
			DialogsHandler.emailSuffixErr(frame, EMAIL_TYPE);
			return false;
		}
		
		return true;
	}
	
	public static boolean handlePwd(String value, JFrame frame) {
		if(value.length() > MAX_LEN_PSW || value.length() < MIN_LEN_PSW) {
			DialogsHandler.pwdLengthErr(frame, MAX_LEN_PSW, MAX_LEN_EMAIL);
			return false;
		}
	
		return true;
	}
	
	public static boolean handleLogin(String email, String pwd, JFrame frame) {
		try {
			if(!database.Database.isLogged(email, pwd)) {
				DialogsHandler.loginFail(frame);
				return false;
			}
			return true;
			
		} catch (SQLException e) {
			DialogsHandler.loginErr(frame, e);
			return false;
		}
	}
}
