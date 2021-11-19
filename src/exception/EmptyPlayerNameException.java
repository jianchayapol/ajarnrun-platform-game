package exception;

public class EmptyPlayerNameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 12324234312L;

	public EmptyPlayerNameException() {
		super("Player's Name cannot be blank!");
	}
}
