package exception;

public class BuyItemFailedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BuyItemFailedException() {
		super("Not enough money!");
	}
}
