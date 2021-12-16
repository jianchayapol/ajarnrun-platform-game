package exception;

/**
 * This BuyItemFailedException class extends from Exception. This exception is
 * thrown if the buy item process is Failed, in case, the player doesn't have
 * enough money to buy item.
 * 
 * @author jianchayapol
 *s
 */
public class BuyItemFailedException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor constructs by calling the super constructor with String
	 * errorMessage as a parameter.
	 */
	public BuyItemFailedException() {
		super("Not enough money!");
	}
}
