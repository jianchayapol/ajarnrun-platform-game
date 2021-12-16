package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

/**
 * Main class of the game.
 * Ajarn Run is a tile-base platformer game. This game has only three states. The first state is Main Menu, which is the state where you get to see a the beautful background and a soft music.
 * The second state is where you type your name and press start. The third state which is the last one is gameplay state.
 * The game will mainly focus on the third state since it is where you get to play the game.
 * If we lose, die, or win in the third state, the only option you can do is to exit the game.
 * @author Mos
 *
 */
public class Main extends Application {
	private ViewManager viewManager;
	
	/**
	 * start method that need to be override.
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