package gui.element;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import sharedObject.RenderableHolder;

public class MoneyBox extends StackPane{
	
	public MoneyBox(){
		super();
		setup();
	}
	
	public void setup() {
		
		ImageView moneyTag = new ImageView(RenderableHolder.money_tag_Image);
		moneyTag.setFitHeight(36);
		moneyTag.setFitWidth(106);
		this.getChildren().add(moneyTag);
		this.setAlignment(Pos.CENTER);
	}
}
