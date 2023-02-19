package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Model.AccessCD;
import Model.AccessUsers;
import Model.Bill;
import Model.Cashier;
import Model.User;
import Model.accessCashiers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMenuScene {
	static Stage stg;
	static Label l1;
	static Button loginButton;
	static Button ClearButton;
	static TextField usernameField;
	static PasswordField passwordField;
	static HBox loginExitButtons;
	static HBox box1;
	static VBox v;
	static VBox credentials;
	static Scene scene;
	static Pane pane;
	static Button exit;
	static ImageView imv;
	static ImageView avatar;

	public void start(Stage primaryStage) throws FileNotFoundException {
		stg = primaryStage;
		l1 = new Label("\nRetro music store \nmanagment software");
		l1.setStyle("-fx-text-fill: white; -fx-font-weight: bold; ");
		l1.setAlignment(Pos.CENTER);

		Image image = new Image(new FileInputStream("sources/Images/vinyl.jpg"));
		imv = new ImageView(image);
		imv.setPreserveRatio(true);
		imv.setFitWidth(300);

		// Image image2 = new Image("Images/avatar.png");
		Image image2 = new Image(new FileInputStream("sources/Images/avatar.png"));
		avatar = new ImageView(image2);
		avatar.setFitHeight(70);
		avatar.setFitWidth(70);
		loginButton = new Button("Login");
		loginButton.setPrefSize(90, 20);
		loginButton.setStyle("-fx-background-color: #86175a; -fx-text-fill: #ffffff;");

		ClearButton = new Button("clear");
		ClearButton.setStyle("-fx-background-color: #86175a; -fx-text-fill: #ffffff;");

		exit = new Button("Exit");
		exit.setPrefSize(90, 20);
		exit.setStyle("-fx-background-color: #994646; -fx-text-fill: #ffffff;");
		exit.setOnAction(actionEvent -> {
			primaryStage.close();

			// save database
		});
		usernameField = new TextField();
		usernameField.setStyle("-fx-background-color: #12243B  ; -fx-text-fill: #ffffff;");
		usernameField.setPromptText("username");
		passwordField = new PasswordField();
		passwordField.setPromptText("Password");
		passwordField.setStyle("-fx-background-color: #12243B ; -fx-text-fill: #ffffff;");
		// setting layouts
		box1 = new HBox();
		box1.getChildren().addAll(avatar, l1);

		credentials = new VBox(20);
		credentials.getChildren().addAll(usernameField, passwordField);

		loginExitButtons = new HBox(50);
		loginExitButtons.setPadding(new Insets(30, 0, 0, 0));
		loginExitButtons.getChildren().addAll(exit, loginButton);

		v = new VBox(15);
		v.setPadding(new Insets(50, 50, 50, 50));
		v.getChildren().addAll(box1, credentials, loginExitButtons);

		loginButton.setOnAction(actionEvent -> {
			try {
				AccessUsers a = new AccessUsers();

				if (a != null) {
					User us = a.checkUser(usernameField.getText(), passwordField.getText());

					if (us == null) {
						if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
							new AdminScene(us).showScene(primaryStage);
						} else {
							Alert alert = new Alert(AlertType.WARNING);
							alert.setContentText("Invalid name or password");
							alert.show();

						}
					} else {

						if (us.getLvl() == 2) {
							new MenagerScene(us).showScene(primaryStage);
							System.out.println("Manager login");
						} else if (us.getLvl() == 3) {
							System.out.println("cashier login");
							accessCashiers acCash = new accessCashiers();
							for (Cashier c : acCash.getCashiers()) {
								if (c.getName().matches(us.getName()) && c.getSurname().matches(us.getSurname())) {
									new CashierScene(c).start(primaryStage);
								}
							}
							// new CashierScene(us).start(primaryStage);
						} else if (us.getLvl() == 1) {
							new AdminScene(us).showScene(primaryStage);

						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("nothing found");
			}

		});

		GridPane layout = new GridPane();
		layout.add(imv, 0, 0);
		layout.add(v, 1, 0);

		layout.setStyle("-fx-background-color: #172e4d");
		imv.fitHeightProperty().bind(layout.heightProperty());
		imv.fitWidthProperty().bind(layout.widthProperty());

		scene = new Scene(layout, 650, 400);
		primaryStage.setScene(scene);

		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(new FileInputStream("sources/Images/icon.png")));
		primaryStage.setTitle("store managment system");
		primaryStage.show();

	}

}