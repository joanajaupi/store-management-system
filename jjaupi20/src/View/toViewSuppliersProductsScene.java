package View;

import Model.AccessCD;
import Model.CD;
import Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class toViewSuppliersProductsScene {

	TableView allItems;
	Stage stage;
	Button close;
	public toViewSuppliersProductsScene(Supplier supplier) {
		show(supplier);
	}

	private void show(Supplier supplier) {
		String name = supplier.getName();
		close = new Button("Close");
		close.setStyle("-fx-background-color: #A72F48; -fx-text-fill: #ffffff;");
		Label lbl = new Label("Supplier name: " + name);
		lbl.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14");
		AccessCD accessCds = new AccessCD();
		stage = new Stage();
		ObservableList<CD> suppliersList = FXCollections.observableArrayList();
		for (CD c : accessCds.getCds()) {
			if (c.getSupplier().matches(supplier.getName())) {
				suppliersList.add(c);
			}
		}
		setTable(suppliersList);
		close.setOnAction(actionEvent ->{
			this.stage.close();
			new viewSuppliers(this.stage);
		});
		VBox v = new VBox(20);
		v.getChildren().addAll(lbl, allItems,close);
		v.setAlignment(Pos.CENTER);
		v.setStyle("-fx-background-color: #172e4d;");
		v.setPadding(new Insets(10, 30, 10, 30));
		stage.setScene(new Scene(v, 400, 260));
		stage.setTitle("Products");
		stage.show();

	}

	void setTable(ObservableList<CD> list) {
		allItems = new TableView<CD>();
		TableColumn<CD, Integer> column0 = new TableColumn<>("Cd id");
		column0.setCellValueFactory(new PropertyValueFactory<>("cdID"));
		TableColumn<CD, String> column1 = new TableColumn<>("Cd name");
		column1.setCellValueFactory(new PropertyValueFactory<>("cdName"));
		TableColumn<CD, Double> column2 = new TableColumn<>("Unit price");
		column2.setCellValueFactory(new PropertyValueFactory<>("cdPrice"));
		TableColumn<CD, String> column3 = new TableColumn<>("Cdgenre");
		column3.setCellValueFactory(new PropertyValueFactory<>("cdGenre"));
		TableColumn<CD, Integer> column4 = new TableColumn<>("in Stock");
		column4.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

		allItems.setItems(list);

		allItems.getColumns().add(column0);
		allItems.getColumns().add(column1);
		allItems.getColumns().add(column2);
		allItems.getColumns().add(column3);
		allItems.getColumns().add(column4);
	}

}
