package sharedObject;

import java.io.FileInputStream;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class FontLoader {

	public static void setFont(Label label, FontType type, int size) {
		String fontPath;
		switch (type) {
		case COURIER:
			fontPath = "res/font/Courier.ttf";
		case YANONE:
			fontPath = "res/font/YanoneKaffeesatz-SemiBold.ttf";
		}
		try {
			label.setFont(Font.loadFont(new FileInputStream("res/font/Courier.ttf"), size));
		} catch (Exception e) {
			label.setFont(Font.font("Verdana", size));
		}
	}

	public static void setFont(TextField text, FontType type, int size) {
		String fontPath;
		switch (type) {
		case COURIER:
			fontPath = "res/font/Courier.ttf";
		case YANONE:
			fontPath = "res/font/YanoneKaffeesatz-SemiBold.ttf";
		}
		try {
			text.setFont(Font.loadFont(new FileInputStream("res/font/Courier.ttf"), size));
		} catch (Exception e) {
			text.setFont(Font.font("Verdana", size));
		}
	}
}
