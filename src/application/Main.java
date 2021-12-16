package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

/**
 * Main class of the game.
 * @author Mos
 *
 */
public class Main extends Application {
	private ViewManager viewManager;
	
	/**
	 * Initialize ViewManager object and obtain mainStage from viewManager, then show that stage.
	 * 
	 * @param primaryStage Default parameter as this method need to be override from extending JavaFx Application class
	 */
	public void start(Stage primaryStage) throws Exception {
		viewManager = new ViewManager();
		primaryStage = viewManager.getStage();
		primaryStage.show();
	}
	
	/**
	 * main method as this class is extended from JavaFx Application class.
	 * @param args Default parameter of main's method.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}