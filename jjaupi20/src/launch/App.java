package launch;

import java.io.FileNotFoundException;

import Model.AccessUsers;
import Model.User;
import View.MainMenuScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{

	public void start(Stage primaryStage) throws FileNotFoundException {
		//AccessUsers b=new AccessUsers();
		help();
		new MainMenuScene().start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	
	}
	public static void help() {
		AccessUsers a = new AccessUsers();
		System.out.println("To access the software you can access through:");
		System.out.println("Default >>> username: admin password: admin \n or: ");
		for(User u: a.getUsers()) {
			System.out.println("Username: " + u.getUsername() +" password: " + u.getPassword());
			
		}
	}
}
