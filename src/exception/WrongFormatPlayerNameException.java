package exception;

/**
 * This WrongFormatPlayerNameException class is extends from the Exception class
 * It is thrown in InputNameUtility class to handle Wrong format of name input.
 * 
 * @author jianchayapol
 *
 */
public class WrongFormatPlayerNameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// you CAN add SerialVersionID if eclipse gives you warning

	/**
	 * This constructor constructs by calling the super constructor with String
	 * message as a parameter. There are 3 cases of the
	 * WrongFormatPlayerNameException to be thrown 1. input name are blank, 2.
	 * Exceeding 8 Character name, and 3. Non-AlphaNumeric name name
	 *
	 * @param message
	 */
	public WrongFormatPlayerNameException(String message) {
		super(message);
	}
}
