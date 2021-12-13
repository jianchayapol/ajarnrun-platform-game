package gui.element;

import application.utility.DrawStringUtility;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PauseGameLeaderBox extends StackPane{
	Rectangle rec;
	AnchorPane mainPane;
	public PauseGameLeaderBox() {
		setUpBackground();
		mainPane = new AnchorPane();
		mainPane.getChildren().add(rec);
		DrawStringUtility.fillLeaderBoard(mainPane);
		this.getChildren().add(mainPane);
		this.setAlignment(Pos.CENTER);
	}
	
	private void setUpBackground() {
		rec = new Rectangle(400,800);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.75);
	}
	
	
}
