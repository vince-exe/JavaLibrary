package ui;

import java.sql.SQLException;

import javax.swing.JFrame;

public class CredentialsChecker {
	/* include the "@gmail.com" */
	private static final int MIN_LEN_EMAIL = 12;
	private static final int MAX_LEN_EMAIL = 40;
	
	private static final String EMAIL_TYPE = "@gmail.com";
	private static final String PASSWORD_TYPE = "* * * * *";
	
	private static final int MIN_LEN_PSW = 8;
	private static final int MAX_LEN_PSW = 30;
	
	private static final int MIN_LEN_NAME = 5;
	private static final int MAX_LEN_NAME = 20;
	
	private static final int MAX_LEN_USERNAME = 20;
	private static final int MIN_LEN_USERNAME = 6;
	
	public static boolean handleEmail(String value, String  diffFrom, JFrame frame) {
		if(value.length() > MAX_LEN_EMAIL || value.length() < MIN_LEN_EMAIL) {
			DialogsHandler.emailLengthErr(frame, MIN_LEN_EMAIL, MAX_LEN_EMAIL);
			return false;
		}
		
		if(!value.endsWith(EMAIL_TYPE)) { 
			DialogsHandler.emailSuffixErr(frame, EMAIL_TYPE);
			return false;
		}
		
		if(value.equals(diffFrom)) {
			System.out.print("\nHello");
			DialogsHandler.emailDiffFrom(frame);
			return false;
		}
		
		return true;
	}
	
	public static boolean handlePwd(String value, String  diffFrom, JFrame frame) {
		if(value.length() > MAX_LEN_PSW || value.length() < MIN_LEN_PSW) {
			DialogsHandler.pwdLengthErr(frame, MAX_LEN_PSW, MAX_LEN_EMAIL);
			return false;
		}
		
		if(value.equals(diffFrom)) {
			DialogsHandler.pwdDiffFrom(frame);
			return false;
		}
		
		return true;
	}
	
	public static boolean handleNames(String value, String diffFrom, JFrame frame) {
		if(value.length() < MIN_LEN_NAME || value.length() > MAX_LEN_NAME) {
			DialogsHandler.namesLenghtErr(frame, MAX_LEN_NAME, MIN_LEN_EMAIL);
			return false;
		}
		
		if(value.equals(diffFrom)) {
			DialogsHandler.namesDiffFrom(frame);
			return false;
		}
		
		return true;
	}
	
	public static boolean handleUsername(String value, String diffFrom, JFrame frame) {
		if(value.length() < MIN_LEN_USERNAME || value.length() > MAX_LEN_USERNAME) {
			DialogsHandler.nickLenErr(frame, MAX_LEN_USERNAME, MIN_LEN_USERNAME);
			return false;
		}
		
		if(value.equals(diffFrom)) {
			DialogsHandler.nickDiffFrom(frame);
			return false;
		}
		
		return true;
	}
	
	public static boolean handleLogin(String email, String pwd, JFrame frame) {
		try {
			if(!CredentialsChecker.handleEmail(email, "email@gmail.com", frame) || !CredentialsChecker.handlePwd(pwd, PASSWORD_TYPE, frame)) {
				return false;
			}
			
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
	
	public static boolean handleRegistration(String fN, String lN, String usr, String birdD, String email, String pwd, JFrame frame) {
		if(!CredentialsChecker.handleNames(fN, "first name", frame) || !CredentialsChecker.handleNames(lN, "last name", frame)) {
			return false;
		}
		
		if(!CredentialsChecker.handleUsername(usr, "username", frame)) {
			return false;
		}
		
		if(!CredentialsChecker.handleEmail(email, "email@gmail.com", frame)) {
			return false;
		}
		
		if(!CredentialsChecker.handlePwd(pwd, PASSWORD_TYPE, frame)) {
			return false;
		}
		
		return true;
	}
}
