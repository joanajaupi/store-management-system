package View;

import Model.AccessSuppliers;
import Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class viewSuppliers {

	AccessSuppliers acSup = new AccessSuppliers();
	Button back;
	Button removeSupplier;
	ObservableList<Supplier> suppliers;
	Button viewProductTheySupply;

	public viewSuppliers(Stage stage) {
		this.show(stage);
	}

	public void show(Stage stage) {
		suppliers = FXCollections.observableArrayList(acSup.getSuppliers());

		TableView<Supplier> supplTab = new TableView<Supplier>();
		TableColumn<Supplier, String> supp = new TableColumn<>("Supplier");
		supp.setCellValueFactory(new PropertyValueFactory("name"));
		supplTab.setItems(suppliers);
		supplTab.getColumns().add(supp);

		back = new Button("Close");
		back.setStyle("-fx-background-color: #A72F48; -fx-text-fill: #ffffff;");
		back.setOnAction(actionEvent -> {
			new suppliersScene(stage);
		});
		removeSupplier = new Button("Remove supplier");
		removeSupplier.setStyle("-fx-background-color: #A72F48; -fx-text-fill: #ffffff;");
		removeSupplier.setOnAction(actionEvent -> {
			if (acSup.getSuppliers() == null) {
				System.out.println("no items to remove");
				Alert a = new Alert(AlertType.WARNING);
				a.setContentText("No items selected");
				a.show();
			} else {
				Supplier toRemove = (Supplier) supplTab.getSelectionModel().getSelectedItem();
				supplTab.getItems().remove(toRemove);
				acSup.remove(toRemove);
			}
		});

		viewProductTheySupply = new Button("view products");
		viewProductTheySupply.setStyle("-fx-background-color: #53A854; -fx-text-fill: #ffffff;");
		viewProductTheySupply.setOnAction(actionEvent -> {
			Supplier toViewProducts = (Supplier) supplTab.getSelectionModel().getSelectedItem();
			if (toViewProducts != null) {
				new toViewSuppliersProductsScene(toViewProducts);
				stage.close();
			} else {
				Alert b = new Alert(AlertType.WARNING);
				b.setContentText("no items selected");
				b.show();
			}
		});
		HBox box = new HBox(10);
		box.getChildren().addAll(removeSupplier, viewProductTheySupply);
		VBox v = new VBox(20);
		v.setStyle("-fx-background-color: #172e4d;");
		v.setPadding(new Insets(10, 20, 10, 20));
		// v.setStyle("-fx-background-color: #4d75ab;");
		v.getChildren().addAll(back, supplTab, box);
		stage.setScene(new Scene(v, 250, 250));
		stage.setTitle("Suppliers");
		stage.show();
	}
}
