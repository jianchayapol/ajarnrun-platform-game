package gui.element;

import application.logic.GameManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import sharedObject.RenderableHolder;

public class MoneyBox extends StackPane{
	
	private ImageView moneyTag;
	private Label priceText;
	
	public MoneyBox(){
		super();
		setupMoneyTag();
		setupPriceTag();
		this.getChildren().addAll(moneyTag,priceText);
		this.setAlignment(Pos.CENTER);
	}
	
	private void setupMoneyTag() {
		
		moneyTag = new ImageView(RenderableHolder.money_tag_Image);
		moneyTag.setFitHeight(36);
		moneyTag.setFitWidth(106);
	}
	
	private void setupPriceTag() {
		priceText = new Label();
		updatePriceText();
	}
	
	private void updatePriceText() {
		int coin = GameManager.getPlayerCoin();
		priceText.setText(String.valueOf(coin));
	}
	
	
	
	
}
