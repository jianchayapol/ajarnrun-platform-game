package gui.element;

import application.logic.GameManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import sharedObject.FontLoader;
import sharedObject.FontType;
import sharedObject.RenderableHolder;

/**
 * class MoneyBox extends StackPane is a box containing moneyTag which is a
 * picture and moneyLabel which show the player's coin.
 * 
 * @author jianchayapol
 *
 */
public class MoneyBox extends StackPane {

	/**
	 * ImageView of moneyBox's Background
	 */
	private ImageView moneyTag;
	/**
	 * Label text shows player's coin
	 */
	private static Label moneyText;
	/**
	 * constructors constructs by setting up all components by calling methods, setupMoneyTag() and setupPriceTag()
	 * Add all components, moneyTag and moneyText to the moneyBox and set the alignment to CENTER.
	 * 
	 */
	public MoneyBox() {
		super();
		setupMoneyTag();
		setupPriceTag();
		this.getChildren().addAll(moneyTag, moneyText);
		this.setAlignment(Pos.CENTER);
	}

	/**
	 * method to setup the MoneyTag by creating new ImageView by using RenderableHolder class to access public static Image field
	 * setFitWidth and FitHeight to 106,36 respectively. 
	 */
	private void setupMoneyTag() {
		moneyTag = new ImageView(RenderableHolder.money_tag_Image);
		moneyTag.setFitHeight(36);
		moneyTag.setFitWidth(106);
	}

	/**
	 * method to setup price tag by creating new Label and set the moneyText by calling updateMoneyText().
	 */
	private void setupPriceTag() {
		moneyText = new Label();
		updateMoneyText();
	}

	/**
	 * method for updating money text by getting coin by calling GameManager.getPlayerCoin()
	 * set the label's font by using static method setFont() from FontLoader
	 * to set font to FontType.TELEGRAMA and fontSize 17, and set textFill to white
	 */
	public static void updateMoneyText() {
		int coin = GameManager.getPlayerCoin();
		moneyText.setText(" " + String.valueOf(coin));
		FontLoader.setFont(moneyText, FontType.TELEGRAMA, 17);
		moneyText.setTextFill(Color.WHITE);
	}

}
