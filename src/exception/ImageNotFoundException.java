package exception;

public class ImageNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ImageNotFoundException(String fileName) {
		super("Image: " + fileName + " NOT FOUND!");
	}
}
