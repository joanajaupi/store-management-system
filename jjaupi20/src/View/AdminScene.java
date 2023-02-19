package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminScene extends MenagerScene {

	public AdminScene(User us) {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminScene() {
	}
	Button returnButton;
	VBox layout;
	ImageView AdminLogo;
	Button usersButton;
	Button incomesCosts;
	Button stats;
	Label adminLabel;

	@Override
	public void showScene(Stage stage) {
		super.setLayout(stage);
		try {
			this.setAdminLayout(stage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setAdminLayout(Stage stage) throws FileNotFoundException {
		firstRow = new HBox(20);
		firstRow.setPadding(new Insets(10));
		AdminLogo = new ImageView(new Image(new FileInputStream("sources/Images/adminLogo.png")));
		AdminLogo.setFitHeight(100);
		AdminLogo.setFitWidth(100);
		adminLabel = new Label("  Admin");
		adminLabel.setStyle("-fx-text-fill: #ffffff; -fx-text-alignment: center; -fx-font-size: 20;");
		VBox adminBox = new VBox(20);
		adminBox.setPadding(new Insets(70, 10, 10, 30));
		adminBox.getChildren().addAll(AdminLogo, adminLabel);
		
		
		//the two columns of buttons similar to the menager layout 
		
		usersButton = new Button("Manage users");
		usersButton.setPrefSize(130, 20);
		usersButton.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		class userButtonHandler implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e) {
				
				new UsersScene().showUsersScene();
				stage.close();
				
			}
		}
		usersButton.setOnAction(new userButtonHandler());
		incomesCosts = new Button("Incomes and Costs");
		incomesCosts.setPrefSize(130, 20);
		incomesCosts.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		incomesCosts.setOnAction(actionEvent ->{
			new IncomesOrCostsScene();
		});
		
		stats = new Button("Statistics");
		stats.setPrefSize(130, 20);
		stats.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		stats.setOnAction(actionEvent->{
			new preStats();
		});
		layout = new VBox(20);
		layout.getChildren().addAll(usersButton, incomesCosts, stats, super.logOutButton);

		overAllLayout.getChildren().add(layout);
		
		lt = new VBox(20);
		lt.setPadding(new Insets(10, 10, 10, 10));
		lt.setAlignment(Pos.CENTER);
		lt.getChildren().addAll(this.firstRow,  overAllLayout);
		HBox f = new HBox(20);
		f.setStyle("-fx-background-color: #172e4d;");
		f.getChildren().addAll(adminBox,lt);
		Scene scene = new Scene(f, 650, 300);
		stage.setScene(scene);

	}

}
