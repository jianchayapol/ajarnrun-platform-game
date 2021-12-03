package exception;

public class EquipItemFailedException extends Exception {
	private static final long serialVersionUID = 1L;
	// you CAN add SerialVersionID if eclipse gives you warning
	
	public EquipItemFailedException() {
		super("Cannot Equip the Item!");
	}
}
