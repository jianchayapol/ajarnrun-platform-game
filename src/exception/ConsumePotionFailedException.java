package exception;

public class ConsumePotionFailedException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ConsumePotionFailedException(){
		super("Cannot Consume this Potion !");
	}
}
