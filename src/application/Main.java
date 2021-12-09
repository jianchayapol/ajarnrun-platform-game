package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainViewManager;

public class Main extends Application {
	private MainViewManager mainViewManager;
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainViewManager = new MainViewManager();
		primaryStage = mainViewManager.getStage();
		primaryStage.show();
	}
	public static void main(String[] args) {
		System.out.println("hahaha");
		launch(args);
	}
}