package application.utility;

import exception.WrongFormatPlayerNameException;
import view.EnterNameScene;

/**
 * This public class is an utility that provides useful static methods to handle
 * user's text input to be in the correct format
 * 
 * @author jianchayapol
 *
 */
public class NameInputUtility {

	/**
	 * This method used to check whether the String s is an AlphaNumeric or not.
	 * String contains only Alphabets and Numbers ( a-z, A-Z, and 0-9 )
	 * 
	 * @param s
	 * @return
	 */
	private static boolean isAlphaNumeric(String s) {
		return (s = s.strip()) != null && s.matches("^[a-zA-Z0-9]*$");
	}

	/**
	 * This method is for checking the user's input that it is in the correct format
	 * or not and @throws WrongFormatPlayerNameException to handle 3 cases of Wrong
	 * Input Format: input name are blank, Exceed 8 Character name, Non-AlphaNumeric
	 * name, then throw the WrongFormatPlayerNameException [ Note: use static method
	 * isBlank() isBlank() in the String class to check if String consists of only
	 * whitespace. ]
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkEnteredName(String name) {
		try {
			if (name.isBlank()) {
				EnterNameScene.setErrorMessage("PlayerName cannot be blank!");
				throw new WrongFormatPlayerNameException("PlayerName cannot be blank!");
			} else if (name.length() > 8) {
				EnterNameScene.setErrorMessage("PlayerName Cannot Exceed 8 Characters");
				throw new WrongFormatPlayerNameException("PlayerName Cannot Exceed 8 Characters");
			} else if (!isAlphaNumeric(name)) {
				EnterNameScene.setErrorMessage("PlayerName can contain only\nletters (a-z), numbers (0-9)");
				throw new WrongFormatPlayerNameException("PlayerName can contain only letters (a-z), numbers (0-9)");
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
