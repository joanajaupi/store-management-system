package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Model.AccessCD;
import Model.AccessGenres;
import Model.CD;
import Model.Genres;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddNewGenreScene {

	TextField nameOfGenre;
	Scene layout;
	Stage newGenreStage;
	Button addGenreButton;
	Button cancel;
	Label label;
	VBox box;
	Button view;

	public AddNewGenreScene() {
		this.showNewGenreStage();
	}

	private void showNewGenreStage() {
		AccessGenres accsGen = new AccessGenres();
		// ArrayList<Genres> list = accessCds.getGenreList();

		nameOfGenre = new TextField();
		nameOfGenre.setPromptText("Enter name of genre");
		nameOfGenre.setPrefSize(100, 20);
		nameOfGenre.setStyle("-fx-background-color: #646464  ; -fx-text-fill: #ffffff;");
		nameOfGenre.setAlignment(Pos.CENTER);
		label = new Label("Add new Genre");
		label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 12");
		label.setAlignment(Pos.CENTER);

		addGenreButton = new Button("Add new Genre");
		addGenreButton.setPrefSize(150, 20);
		addGenreButton.setStyle("-fx-background-color: #33B13C; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		addGenreButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (nameOfGenre.getText().matches("")) {
					System.out.println("nothing inserted");
					Alert a = new Alert(AlertType.WARNING);
					a.setContentText("nothing inserted");
					a.show();
				} else if (accsGen.checkGenre(nameOfGenre.getText()) == null) {
					String genr = nameOfGenre.getText();
					accsGen.addGenre(new Genres(genr));
					Alert b = new Alert(AlertType.INFORMATION);
					b.setContentText("Successful");
					b.show();
					b.setOnCloseRequest(actionEvent -> {
						nameOfGenre.clear();
					});
				} else {
					Alert a = new Alert(AlertType.WARNING);
					a.setContentText("this genre already exists");
					a.show();

				}

			}
		});
		cancel = new Button("Cancel");
		cancel.setPrefSize(150, 20);
		cancel.setStyle("-fx-background-color: #F53030; -fx-text-fill: #ffffff; ");
		cancel.setOnAction(EventHandler -> {
			newGenreStage.close();
		});

		view = new Button("View List");
		view.setPrefSize(150, 20);
		view.setStyle("-fx-background-color: #373737; -fx-text-fill: #ffffff; ");
		view.setOnAction(actionEvent -> {
			this.newGenreStage.close();
			new viewGenres();
		});

		box = new VBox(20);
		box.setStyle("-fx-background-color:#272727");
		box.setPadding(new Insets(20, 20, 20, 20));
		HBox cnc = new HBox();
		cnc.setPadding(new Insets(5, 5, 5, 200));
		cnc.getChildren().add(cancel);
		box.getChildren().addAll(cnc, label, nameOfGenre, addGenreButton, view);
		box.setAlignment(Pos.CENTER);
		layout = new Scene(box, 300, 300);
		newGenreStage = new Stage();
		newGenreStage.setTitle("add genre");
		newGenreStage.setScene(layout);
		try {
			newGenreStage.getIcons().add(new Image(new FileInputStream("sources/Images/icon.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newGenreStage.setResizable(false);
		newGenreStage.show();

	}

}
