package application.utility;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {
	
	private static ArrayList<KeyCode> keypressed = new ArrayList<KeyCode>();

	public static void setKeyPressed(KeyCode keyCode, boolean pressed) {
		if (pressed) {
			if (!keypressed.contains(keyCode)) {
				keypressed.add(keyCode);
			} else {
				keypressed.remove(keyCode);
			}
		}
	}

	public static void removeKeyPressed() {
		keypressed.clear();
	}

	public static ArrayList<KeyCode> getKeypressed() {
		return keypressed;
	}
}
