package gui.element;

import application.logic.GameManager;
import gui.button.ImageButton;
import gui.button.ImageButtonType;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

/**
 * LevelEndingBox extends AnchorPane is class of level ending user interface
 * which are the level completed and level failed.
 * 
 * @author jianchayapol
 *
 */
public class LevelEndingBox extends AnchorPane {

	/**
	 * This Rectangle bgRec is a dark background of this pane.
	 */
	private static Rectangle bgRec;
	/**
	 * AnchorPane which is a mainPane containing all components.
	 */
	private static AnchorPane mainPane;
	/**
	 * VBox containing money and level Label
	 */
	private static VBox infoBox;
	/**
	 * VBox containing continue button and infoBox
	 */
	private static VBox controlBox;
	/**
	 * A Label that showing player's money
	 */
	private static Label moneyPlusLabel;
	/**
	 * A Label that showing the latest ending level
	 */
	private static Label levelUpLabel;
	/**
	 * Image of LevelEnding Background
	 */
	private static Image bg;
	/**
	 * ImageButton with ImageButtonType.CONTINUE_TO_SHOP and
	 * ImageButtonType.EXIT_GAME
	 */
	private static ImageButton imgButton;
	/**
	 * Rectangle size 280x380 to be filled with image background
	 */
	private Rectangle rec = new Rectangle(280, 380);

	/**
	 * LevelEndingBox's height
	 */
	private static final int HEIGHT = 600;
	/**
	 * LevelEndingBox's width
	 */
	private static final int WIDTH = 800;

	/**
	 * Constructor constructs and initialize by each type given from the parameter.
	 * add all the components rec, controlBox to the LevelEndingBox setting up
	 * Rectangle bgRec, a dark background of the LevelEndingBox with the size of the
	 * LevelEndingBox. SetFill to be black with opacity 0.8 set mainPane's
	 * LayoutX=245 and LayoutY=120 Adding all bgRec and mainPane to LevelEndingBox
	 */
	public LevelEndingBox(LevelEndingType type) {
		mainPane = new AnchorPane();
		initComponents(type);
		mainPane.getChildren().addAll(rec, controlBox);
		controlBox.setLayoutX(75);
		controlBox.setLayoutY(173);
		bgRec = new Rectangle(WIDTH, HEIGHT);
		bgRec.setFill(Color.BLACK);
		bgRec.setOpacity(0.8);

		mainPane.setLayoutX(245);
		mainPane.setLayoutY(120);

		this.getChildren().addAll(bgRec, mainPane);
	}

	/**
	 * method for initializing components by type given from the parameter Case
	 * COMPLETED call the method setLevelUpLabel() to set the level label initialize
	 * imgButton with Image ImageButtonType.CONTINUE_TO_SHOP by using
	 * RenderableHolder class to access public static Image field Case FAILED call
	 * the method setLevelLabel() to set the level label initialize imgButton with
	 * ImageButtonType.EXIT_GAME by using RenderableHolder class to access public
	 * static Image field Then, set fill rec with the image by using new
	 * ImagePattern(bg) Setting upLabel by calling setMoneyPlusLabel() and
	 * setupCenterBox()
	 * 
	 * @param type type of level ending which are LevelEndingType.COMPLETED and
	 *             LevelEndingType.FAILED
	 */
	private void initComponents(LevelEndingType type) {
		switch (type) {
		case COMPLETED:
			setLevelUpLabel();
			imgButton = new ImageButton(ImageButtonType.CONTINUE_TOSHOP);
			bg = RenderableHolder.level_passed_Image;
			break;
		case FAILED:
			setLevelLabel();
			imgButton = new ImageButton(ImageButtonType.EXIT_GAME);
			bg = RenderableHolder.level_failed_Image;
			break;
		default:
			break;
		}
		rec.setFill(new ImagePattern(bg));

		setMoneyPlusLabel();
		setupCenterBox();
	}

	// ====================== private static method(s) ============================

	/**
	 * method for Setting up infoBox to be new VBox with spacing = 28 and Alignment
	 * CENTER. Add the two label, moneyPlusLabel and levelUpLabel to the infoBox.
	 * Set LayoutX of imgButton to 50. Setting up controlBox to be new VBox with
	 * spacing = 50 and Alignment BOTTOM CENTER. Add all the infoBox, imgButton to
	 * controlBox.
	 */
	private static void setupCenterBox() {
		infoBox = new VBox(28);
		infoBox.setAlignment(Pos.CENTER);
		infoBox.getChildren().addAll(moneyPlusLabel, levelUpLabel);
		imgButton.setLayoutX(50);
		controlBox = new VBox(50);
		controlBox.getChildren().addAll(infoBox, imgButton);
		controlBox.setAlignment(Pos.BOTTOM_CENTER);
	}

	/**
	 * method for setting up Money Label, getting player's coin by calling
	 * GameManager.getPlayerCoin(), creating new Label with text, " $ " + player's
	 * coin. set the label's font by using static method setFont() from FontLoader
	 * to set font to FontType.TELEGRAMA and fontSize 18, and set textFill to white
	 */
	private static void setMoneyPlusLabel() {
		moneyPlusLabel = new Label("  $ " + String.valueOf(GameManager.getPlayerCoin()));
		FontLoader.setFont(moneyPlusLabel, FontType.TELEGRAMA, 18);
		moneyPlusLabel.setTextFill(Color.WHITE);
	}

	/**
	 * method for setting up Level Label, getting player's level by calling
	 * GameManager.getLevelCount() + 1), creating new Label with text, " lv. " +
	 * player's level. set the label's font by using static method setFont() from
	 * FontLoader to set font to FontType.TELEGRAMA and fontSize 19, and set
	 * textFill to INDIANRED
	 */
	private static void setLevelLabel() {
		levelUpLabel = new Label("  lv." + String.valueOf(GameManager.getLevelCount() + 1));
		FontLoader.setFont(levelUpLabel, FontType.TELEGRAMA, 19);
		levelUpLabel.setTextFill(Color.INDIANRED);
	}

	/**
	 * method for setting up Level Label, getting player's level by calling
	 * GameManager.getLevelCount() + 1), creating new Label with text, " lv. " +
	 * player's level. set the label's font by using static method setFont() from
	 * FontLoader to set font to FontType.TELEGRAMA and fontSize 19, and set
	 * textFill to PALEGGREEN.
	 */
	private static void setLevelUpLabel() {
		setLevelLabel();
		levelUpLabel.setTextFill(Color.PALEGREEN);
	}

	// ================== GETTERS - SETTERS ========================

	/**
	 * Method for Getting moneyPlusLabel
	 * 
	 * @return moneyPlusLabel Label represents money text in format of " $ "+money
	 */
	public Label getMoneyPlusLabel() {
		return moneyPlusLabel;
	}

	/**
	 * Method for Setting moneyPlusLabel
	 * 
	 * @param moneyPlusLabel Label represents money text in format of " $ "+money
	 */
	public void setMoneyPlusLabel(Label moneyPlusLabel) {
		LevelEndingBox.moneyPlusLabel = moneyPlusLabel;
	}

	/**
	 * Method for Getting levelUpLabel
	 * 
	 * @return levelUpLabel Label represents level text in format of " lv "+ level
	 */
	public Label getLevelUpLabel() {
		return levelUpLabel;
	}

	/**
	 * Method for Setting levelUpLabel
	 * 
	 * @param levelUpLabel Label represents level text in format of " lv "+ level
	 */
	public void setLevelUpLabel(Label levelUpLabel) {
		LevelEndingBox.levelUpLabel = levelUpLabel;
	}

	/**
	 * Method for Getting level ending image
	 * 
	 * @return bg an Image of level ending
	 */
	public Image getBg() {
		return bg;
	}

	/**
	 * Method for Setting level ending image
	 * 
	 * @param bg an Image of level ending
	 */
	public void setBg(Image bg) {
		LevelEndingBox.bg = bg;
	}

	/**
	 * Method for Getting ImgButton
	 * 
	 * @return imgButton ImageButton of level ending (continue and exit)
	 */
	public ImageButton getImgButton() {
		return imgButton;
	}

	/**
	 * Method for Setting ImgButton
	 * 
	 * @param imgButton ImageButton of level ending (continue and exit)
	 */
	public void setImgButton(ImageButton imgButton) {
		LevelEndingBox.imgButton = imgButton;
	}

	/**
	 * Method for Getting Rectangle
	 * 
	 * @return rec an dark Rectangle Background
	 */
	public Rectangle getRec() {
		return rec;
	}

	/**
	 * Method for Setting Rectangle
	 * 
	 * @param rec an dark Rectangle Background
	 */
	public void setRec(Rectangle rec) {
		this.rec = rec;
	}

	/**
	 * method for setting EventHandler MouseEvent to set the DropShadow effect by
	 * using setEffect(new DropShadow()) while mouse entering and set null while
	 * mouse exit.
	 */
	private void setUpMouseEnter() {

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				LevelEndingBox.this.rec.setEffect(new DropShadow());
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				LevelEndingBox.this.rec.setEffect(null);
			}
		});
	}

}