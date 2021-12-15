package sharedObject;

import java.io.FileInputStream;

import gui.button.MainButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class FontLoader {

	public static void setFont(Label label, FontType type, int size) {
		try {
			switch (type) {
			case YANONE:
				label.setFont(Font.loadFont(new FileInputStream("res/font/YanoneKaffeesatz-SemiBold.ttf"), size));
				break;
			case TELEGRAMA:
				label.setFont(Font.loadFont(new FileInputStream("res/font/telegraw.ttf"), size));
				break;
			}
		} catch (Exception e) {
			label.setFont(Font.font("Verdana", size));
		}
	}

	public static void setFont(TextField text, FontType type, int size) {
		try {
			String fontPath = null;
			switch (type) {
			case YANONE:
				text.setFont(Font.loadFont(new FileInputStream("res/font/YanoneKaffeesatz-SemiBold.ttf"), size));
				break;
			case TELEGRAMA:
				text.setFont(Font.loadFont(new FileInputStream("res/font/telegraw.ttf"), size));
				break;
			case PSLCD:
				text.setFont(Font.loadFont(new FileInputStream("res/font/PSLCD3310.ttf"), size));
				break;
			}
		} catch (Exception e) {
			text.setFont(Font.font("Verdana", size));
		}
	}

	public static void setFont(MainButton mainButton, FontType type, int size) {
		try {
			String fontPath = null;
			switch (type) {
			case YANONE:
				mainButton.setFont(Font.loadFont(new FileInputStream("res/font/YanoneKaffeesatz-SemiBold.ttf"), size));
				break;
			case TELEGRAMA:
				mainButton.setFont(Font.loadFont(new FileInputStream("res/font/telegraw.ttf"), size));
				break;
			}
		} catch (Exception e) {
			mainButton.setFont(Font.font("Verdana", size));
		}
	}
}
