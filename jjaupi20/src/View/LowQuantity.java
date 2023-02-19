package View;

import Model.CD;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LowQuantity {
	Stage window;
	Label titleLabel;
	Button closeButton;
	TableView productsTable;
	Scene scene;
	Button addQuantity;
	ObservableList<CD> productList;

	public void showScene(ObservableList<CD> products) {

		productList = products;
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		titleLabel = new Label("The following products have a less than 5 quantity");
		titleLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16");
		setTable();

		closeButton = new Button("Close");
		closeButton.setOnAction(actionEvent -> window.close());
		closeButton.setPrefWidth(120);
		closeButton.setStyle("-fx-background-color: #B13333; -fx-text-fill: #ffffff");
		
		addQuantity = new Button("Add quantity");
		addQuantity.setPrefWidth(120);
		addQuantity.setStyle("-fx-background-color: #33B13C; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		addQuantity.setOnAction(actionEvent ->{
			new ManageQuantityScene();
		});
		HBox box = new HBox(20);
		box.getChildren().addAll(closeButton, addQuantity);
		VBox layout = new VBox(20);
		layout.getChildren().addAll(titleLabel, productsTable, box);
		layout.setPadding(new Insets(10, 30, 10, 30));
		layout.setStyle("-fx-background-color: #172e4d;");
		scene = new Scene(layout, 500, 300);
		window.setScene(scene);
		window.showAndWait();
	}

	void setTable() {
		TableColumn<CD, String> nameColumn = new TableColumn<>("Product");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("cdName"));

		TableColumn<CD, Integer> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setMinWidth(70);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

		productsTable = new TableView<>();
		productsTable.setItems(productList);
		productsTable.getColumns().addAll(nameColumn, quantityColumn);
	}

}
