package exception;

public class WrongFormatPlayerNameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// you CAN add SerialVersionID if eclipse gives you warning
	
	public WrongFormatPlayerNameException(String message) {
		super(message);
	}
}
