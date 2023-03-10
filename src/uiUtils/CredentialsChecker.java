package uiUtils;

import java.sql.SQLException;
import javax.swing.JFrame;

import java.text.ParseException;
import java.text.SimpleDateFormat;  

public class CredentialsChecker {
	/* include the "@gmail.com" */
	public static final int MIN_LEN_EMAIL = 12;
	public static final int MAX_LEN_EMAIL = 40;
	
	public static final String EMAIL_TYPE = "@gmail.com";
	public static final String PASSWORD_TYPE = "* * * * *";
	
	public static final int MIN_LEN_PSW = 8;
	public static final int MAX_LEN_PSW = 30;
	
	public static final int MIN_LEN_NAME = 1;
	public static final int MAX_LEN_NAME = 20;
	
	public static final int MAX_LEN_USERNAME = 20;
	public static final int MIN_LEN_USERNAME = 4;
	
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
	
	public static boolean hasANumber(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				return true;
			}
		}
		
		return false;
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
	
	public static boolean handlePwd(String value, JFrame frame) {
		if(value.length() > MAX_LEN_PSW || value.length() < MIN_LEN_PSW) {
			DialogsHandler.pwdLengthErr(frame, MAX_LEN_PSW, MAX_LEN_EMAIL);
			return false;
		}
		
		return true;
	}
	
	public static boolean handleNames(String value, String diffFrom, JFrame frame) {
		if(value.length() < MIN_LEN_NAME || value.length() > MAX_LEN_NAME) {
			DialogsHandler.namesLenghtErr(frame, MAX_LEN_NAME, MIN_LEN_NAME);
			return false;
		}
		
		if(value.equals(diffFrom)) {
			DialogsHandler.namesDiffFrom(frame);
			return false;
		}
		
		return true;
	}
	
	public static boolean handleNames(String value, JFrame frame) {
		if(value.length() < MIN_LEN_NAME || value.length() > MAX_LEN_NAME) {
			DialogsHandler.namesLenghtErr(frame, CredentialsChecker.MIN_LEN_NAME, CredentialsChecker.MAX_LEN_NAME);
			return false;
		}
			
		return true;
	}
	
	public static boolean handleUsername(String value, JFrame frame) {
		if(value.length() < MIN_LEN_USERNAME || value.length() > MAX_LEN_USERNAME) {
			DialogsHandler.nickLenErr(frame, MAX_LEN_USERNAME, MIN_LEN_USERNAME);
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
	
	public static boolean handleBirdDate(String value, JFrame frame) {  
	    try {
			new SimpleDateFormat("yyyy/MM/dd").parse(value);
			return true;
			
		} catch (ParseException e) {
			DialogsHandler.birdDateErr(frame);
			return false;
		}  
	}
	/**
	 *
	 * @return -1=Already Exist \ 0=Doesn't Exist  \ 1=SQL Exception
	 */
	public static int handleEmailAlreadyExist(String email, JFrame frame) {
		try {
			
			return (database.Database.emailExist(email)) ? -1 : 0;
			
		} catch (SQLException e) {
			DialogsHandler.SQLErr(frame, e.getMessage());
			return 1;
		}
	}
	
	/**
	 * 
	 * @return -1=FAIL 1=ROOT 0=USER
	 */
	public static int handleLogin(String email, String pwd, JFrame frame) {
		try {
			if(!CredentialsChecker.handleEmail(email, "email@gmail.com", frame) || !CredentialsChecker.handlePwd(pwd, PASSWORD_TYPE, frame)) {
				return -1;
			}
			
			int[] result = database.Database.isLogged(email, pwd);
			if(result[0] == 0) {
				DialogsHandler.loginFail(frame);
				return -1;
			}
			return result[1];
		
		} catch (SQLException e) {
			DialogsHandler.loginErr(frame, e);
			return -1;
		}
	}
	
	public static boolean usernameAlreadyExist(String username, JFrame frame) {
		int result = database.Database.existUsername(username);
		
		if(result == -1) {
			DialogsHandler.SQLErr(frame, "The application failed to check the username");
			return false;
		}
		else if(result == 0) {
			DialogsHandler.generalWarning(frame, "Invalid Username", "There is already an account with this username");
			return false;
		}
		
		return true;
	}
	
	public static boolean handleRegistration(String fN, String lN, String usr, String birdD, String email, String pwd, JFrame frame) {
		return handleNames(fN, "first name", frame) && handleNames(lN, "last name", frame) &&
			   handleUsername(usr, "username", frame) && handleEmail(email, "email@gmail.com", frame) &&
			   handlePwd(pwd, PASSWORD_TYPE, frame) && handleBirdDate(birdD, frame) &&
			   usernameAlreadyExist(usr, frame) &&
			   CredentialsChecker.handleEmailAlreadyExist(email, frame) == 0;
	}
}
