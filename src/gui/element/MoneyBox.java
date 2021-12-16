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

public class MoneyBox extends StackPane {

	private ImageView moneyTag;
	private static Label moneyText;

	public MoneyBox() {
		super();
		setupMoneyTag();
		setupPriceTag();
		this.getChildren().addAll(moneyTag, moneyText);
		this.setAlignment(Pos.CENTER);
	}

	private void setupMoneyTag() {
		moneyTag = new ImageView(RenderableHolder.money_tag_Image);
		moneyTag.setFitHeight(36);
		moneyTag.setFitWidth(106);
	}

	private void setupPriceTag() {
		moneyText = new Label();
		updateMoneyText();
	}

	public static void updateMoneyText() {
		int coin = GameManager.getPlayerCoin();
		moneyText.setText(" " + String.valueOf(coin));
		FontLoader.setFont(moneyText, FontType.TELEGRAMA, 17);
		moneyText.setTextFill(Color.WHITE);
	}

}
