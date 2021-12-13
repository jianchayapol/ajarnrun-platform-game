package sharedObject;

import java.io.FileInputStream;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class FontLoader {

	public static void setFont(Label label, FontType type, int size) {
		try {
			switch (type) {
			case YANONE:
				label.setFont(Font.loadFont(new FileInputStream("res/font/YanoneKaffeesatz-SemiBold.ttf"), size));
			case TELEGRAMA:
				label.setFont(Font.loadFont(new FileInputStream("res/font/telegraw.ttf"), size));
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
				text.setFont(Font.loadFont(new FileInputStream("res/font/Courier.ttf"), size));
			case TELEGRAMA:
				text.setFont(Font.loadFont(new FileInputStream("res/font/YanoneKaffeesatz-SemiBold.ttf"), size));
			}
		} catch (Exception e) {
			text.setFont(Font.font("Verdana", size));
		}
	}
}
