package gui.element;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import sharedObject.RenderableHolder;

public class MoneyBox extends StackPane{
	
	public MoneyBox(){
		super();
		
	}
	
	public void setup() {
		
		ImageView shop = new ImageView(RenderableHolder.money_tag_Image);
		shop.setFitHeight(400);
		shop.setFitWidth(300);
		this.getChildren().add(shop);
		this.setPrefSize(360,480);
		this.setAlignment(Pos.CENTER);
	}
}
