package gui;

import gamelogic.Command;

import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FXGraphic extends Application implements iGui {

	// logger
	private static final Logger log = Logger.getLogger(FXGraphic.class.getName());

	/**
	 * starts a Gui Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * 
	 * First: Username and IP
	 * 
	 * Second: Connect to Server /wait for player
	 * 
	 * Third: Load Gamefield
	 * 
	 * 
	 */
	
	
	/**
	 * 
	 */
	public boolean performAction(Command cmd) {
		return false;
	}
	@Override
	public void start(Stage stage) throws Exception {
	
	//	final StackPane stackPane = new StackPane();
		
	//	final Node lableNode = new Label("Hello World");
	//	stackPane.getChildren().add(lableNode);
	//	stage.setScene(new Scene(stackPane, 300,300));
		
		final Parent root = FXMLLoader.load(getClass().getResource("template/login.fxml"));
		stage.setScene(new Scene(root, 500,500));
		stage.setTitle("teest");
		stage.show();
				
	}
}
