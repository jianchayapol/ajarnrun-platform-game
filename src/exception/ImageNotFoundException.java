package exception;
/**
 * This ImageNotFoundException class extends from the Exception class.
 * It is thrown whenever the load image process in RenderableHolder class is Failed.
 * @author jianchayapol
 *
 */
public class ImageNotFoundException extends Exception {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * This constructor constructs by calling the super constructor with String
	 * errorMessage in the format of "Image: " + fileName + " NOT FOUND!".
	 *
	 * @param fileName
	 */
	public ImageNotFoundException(String fileName) {
		super("Image: " + fileName + " NOT FOUND!");
	}
}
