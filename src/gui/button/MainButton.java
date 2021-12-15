package gui.button;

import java.io.FileInputStream;

import application.logic.GameManager;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import sharedObject.AudioLoader;
import sharedObject.FontLoader;
import sharedObject.FontType;
import view.ViewManager;

public class MainButton extends Button {

	private final int WIDTH = 190;
	private final int HEIGHT = 49;
	private final String BUTTON_PRESSED = " -fx-background-color: transparent; -fx-background-image: url('/button/yellow_button01.png')";
	private final String BUTTON_UNPRESSED = " -fx-background-color: transparent; -fx-background-image: url('/button/yellow_button00.png')";

	public MainButton(String text) {
		setText(text);
		FontLoader.setFont(this, FontType.YANONE, 26);
		setPrefWidth(WIDTH);
		setPrefHeight(HEIGHT);
		setButtonUnpressedStyle();
		initializeButtonListener();
		this.setOpacity(0.98);
	}

	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED);
		setPrefHeight(HEIGHT-4);
		setLayoutY(getLayoutY() + 4);
	}

	private void setButtonUnpressedStyle() {
		setStyle(BUTTON_UNPRESSED);
		setPrefHeight(HEIGHT-4);
		setLayoutY(getLayoutY() - 4);
	}

	private void initializeButtonListener() {

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MainButton.this.getParent().setCursor(Cursor.HAND);
				setEffect(new DropShadow());
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MainButton.this.getParent().setCursor(Cursor.DEFAULT);
				setEffect(null);
			}
		});

		setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(!GameManager.getIsMute()) AudioLoader.Mouse_Click.play();
				setButtonPressedStyle();
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				setButtonUnpressedStyle();
			}
		});
	}
}
