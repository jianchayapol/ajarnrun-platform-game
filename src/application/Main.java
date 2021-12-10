package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

public class Main extends Application {
	private ViewManager viewManager;
	@Override
	public void start(Stage primaryStage) throws Exception {
		viewManager = new ViewManager();
		primaryStage = viewManager.getStage();
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}