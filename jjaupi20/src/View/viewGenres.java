package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Model.AccessGenres;
import Model.Genres;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class viewGenres {

	Button remove;
	ListView genreTable;
	ObservableList<Genres> list;
	Button close;
	Label lbl;

	public viewGenres() {
		showScene();
	}

	@SuppressWarnings("unchecked")
	public void showScene() {
		Stage s = new Stage();

		AccessGenres aGen = new AccessGenres();
		lbl = new Label("List of genres");
		lbl.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 14;");
		list = FXCollections.observableArrayList(aGen.getGenreList());
		setTable(list);
		remove = new Button("Remove");
		remove.setPrefWidth(120);
		remove.setStyle("-fx-background-color: #B13333; -fx-text-fill: #ffffff");
		remove.setOnAction(actionEvent -> {
			Genres removeGenre = (Genres) genreTable.getSelectionModel().getSelectedItem();
			aGen.removeGenre(removeGenre);
			list = FXCollections.observableArrayList(aGen.getGenreList());
			setTable(list);

		});
		close = new Button("close");
		close.setPrefWidth(120);
		close.setStyle("-fx-background-color: #B13333; -fx-text-fill: #ffffff");
		close.setOnAction(actionEvent -> {
			s.close();
			new AddNewGenreScene();
		});
		VBox pane = new VBox(20);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(20, 40, 20, 40));
		pane.setStyle("-fx-background-color:#272727;");
		pane.getChildren().addAll(genreTable, remove, close);
		s.setScene(new Scene(pane, 300, 300));

		try {
			s.getIcons().add(new Image(new FileInputStream("sources/Images/icon.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.setTitle("Table of genres");
		s.show();

	}

	public void setTable(ObservableList<Genres> list) {
		genreTable = new ListView();

		if (list == null) {
			genreTable.setPlaceholder(new Label("No rows to display"));
		} else
			genreTable.setItems(list);
	}
}
