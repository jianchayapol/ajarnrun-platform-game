package gui.button;

import application.logic.GameManager;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import sharedObject.AudioLoader;
import sharedObject.FontLoader;
import sharedObject.FontType;

/**
 * MainButton class extends from Button. It's a button that present in the
 * viewManager (main menu)
 * 
 * @author Mos
 *
 */
public class MainButton extends Button {

	/**
	 * button's width
	 */
	private final int WIDTH = 190;
	/**
	 * button's height
	 */
	private final int HEIGHT = 49;
	/**
	 * String of style for BUTTON_PRESSED style
	 */
	private final String BUTTON_PRESSED = " -fx-background-color: transparent; -fx-background-image: url('/button/yellow_button01.png')";
	/**
	 * String of style for BUTTON_UNPRESSED style
	 */
	private final String BUTTON_UNPRESSED = " -fx-background-color: transparent; -fx-background-image: url('/button/yellow_button00.png')";

	/**
	 * This Constructors construct the MainButton object by setting text to the
	 * given text from parameter and use method setFont() from FontLoader. Setting
	 * font to FontType.YANONE and size 26. Set PrefSize to DTH and HEIGHT,
	 * setButtonUnpressedStyle() and initialize the button event listener by calling
	 * initializeButtonListener(). Setting its opacity to 0.98
	 * 
	 * @param text
	 */
	public MainButton(String text) {
		setText(text);
		FontLoader.setFont(this, FontType.YANONE, 26);
		setPrefWidth(WIDTH);
		setPrefHeight(HEIGHT);
		setButtonUnpressedStyle();
		initializeButtonListener();
		this.setOpacity(0.98);
	}

	/**
	 * This method is for setting the style of pressed button
	 */
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED);
		setPrefHeight(HEIGHT - 4);
		setLayoutY(getLayoutY() + 4);
	}

	/*
	 * This method is for setting the style of unpressed button
	 */
	private void setButtonUnpressedStyle() {
		setStyle(BUTTON_UNPRESSED);
		setPrefHeight(HEIGHT - 4);
		setLayoutY(getLayoutY() - 4);
	}

	/**
	 * This method is to initialize the event listener by setOnMouseEnter to make a
	 * cursor.HAND and DropShadow Effect. SetMouseExit to dispose the effect and set
	 * back cursor to default, setOnMousePressed to make a sound of clicking mouse
	 * by using AudioLoader.Mouse_Click.play() (in case the game isn't mute) and
	 * setButtonPressedStyle(), setOnMouseReleased with the
	 * setButtonUnpressedStyle().
	 */
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
				if (!GameManager.isMute())
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
