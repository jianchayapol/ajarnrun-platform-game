package button;

import java.io.FileInputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import sharedObject.AudioLoader;
import view.ViewManager;

public class MainButton extends Button {
	private final String FONT_PATH = "res/font/YanoneKaffeesatz-SemiBold.ttf";
	private final String BUTTON_PRESSED = " -fx-background-color: transparent; -fx-background-image: url('/button/yellow_button01.png')";
	private final String BUTTON_UNPRESSED = " -fx-background-color: transparent; -fx-background-image: url('/button/yellow_button00.png')";

	public MainButton(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setButtonUnpressedStyle();
		initializeButtonListener();
	}

	private void setButtonFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (Exception e) {
			setFont(Font.font("Verdana", 23));
		}
	}

	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}

	private void setButtonUnpressedStyle() {
		setStyle(BUTTON_UNPRESSED);
		setPrefHeight(45);
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
				AudioLoader.Mouse_Click.play();
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
