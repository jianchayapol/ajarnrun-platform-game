package application.utility;

import exception.WrongFormatPlayerNameException;
import view.EnterNameScene;

public class NameInputUtility {
	
	public static boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }
	
	public static boolean checkEnteredName(String name) {
		try {
			if (name.isBlank()) {
				EnterNameScene.setErrorMessage("PlayerName cannot be blank!");
				throw new WrongFormatPlayerNameException("PlayerName cannot be blank!");
			} else if (name.length() > 8) {
				EnterNameScene.setErrorMessage("PlayerName Cannot Exceed 8 Characters");
				throw new WrongFormatPlayerNameException("PlayerName Cannot Exceed 8 Characters");
			} else if(!isAlphaNumeric(name)) {
				EnterNameScene.setErrorMessage("PlayerName can contain only\nletters (a-z), numbers (0-9)");
				throw new WrongFormatPlayerNameException("PlayerName Cannot Exceed 8 Characters");
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
