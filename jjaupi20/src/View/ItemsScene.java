package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import Model.AccessCD;
import Model.CD;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ItemsScene {

	Stage stage;
	Button add;
	TableView<CD> tableOfallItems;
	ObservableList<CD> cdList;
	Button exit;
	Button edit;
	Label lbl;
	ImageView imv;

	public ItemsScene() {
		try {
			showScene();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void showScene() throws FileNotFoundException {
		HBox firstRow = new HBox(20);
		Image img = new Image(new FileInputStream("sources/Images/addNewVinyl.png"));
		imv = new ImageView(img);
		imv.setFitHeight(50);
		imv.setFitWidth(50);
		lbl = new Label("Manage CDs");
		lbl.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 15; ");
		firstRow.getChildren().addAll(imv, lbl);
		// a table
		// a add cdButton
		AccessCD accesscd = new AccessCD();
		cdList = FXCollections.observableArrayList(accesscd.getCds());
		tableOfallItems = new TableView<CD>();
		TableColumn<CD, String> nameOfCD = new TableColumn<>("Product name");
		nameOfCD.setCellValueFactory(new PropertyValueFactory<>("cdName"));
		TableColumn<CD, Double> unitPrice = new TableColumn<>("unit price");
		unitPrice.setCellValueFactory(new PropertyValueFactory<>("cdPrice"));
		TableColumn<CD, Integer> quantity = new TableColumn<>("in stock");
		quantity.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
		TableColumn<CD, String> supplier = new TableColumn<>("Supplier");
		supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
		TableColumn<CD, String> genre = new TableColumn<>("genre");
		genre.setCellValueFactory(new PropertyValueFactory<>("cdGenre"));
		TableColumn<CD, String> id = new TableColumn<>("barcode");
		id.setCellValueFactory(new PropertyValueFactory<>("cdID"));
		if (cdList == null) {
			tableOfallItems.setPlaceholder(new Label("No rows to display"));
		} else
			tableOfallItems.setItems(cdList);
		// ID, nm, genr, pr, quant, supp
		tableOfallItems.getColumns().addAll(nameOfCD, genre, id, unitPrice, quantity, supplier);

		add = new Button("add CD");
		add.setStyle("-fx-background-color: #53A854; -fx-text-fill: #ffffff;-fx-font-weight: bold;");
		add.setPrefWidth(70);
		add.setOnAction(actionEvent -> {
			try {
				stage.close();
				(new AddNewCDScene()).showAddNewCdScene();
				tableOfallItems.refresh();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File not found");
				e.printStackTrace();
			}
		});
		exit = new Button("Exit");
		exit.setStyle("-fx-background-color: #A72F48; -fx-text-fill: #ffffff;-fx-font-weight: bold;");
		exit.setPrefWidth(70);


		edit = new Button("edit");
		edit.setPrefWidth(70);
		edit.setStyle("-fx-background-color: #53A854; -fx-text-fill: #ffffff;-fx-font-weight: bold");
		edit.setOnAction(actionEvent -> {
			try {

				CD cdToEdit = (CD) tableOfallItems.getSelectionModel().getSelectedItem();
				// call the constructor of edit user with method to open view
				if (cdToEdit != null)
					new editCDinfoScene(cdToEdit, stage);
				else {
					Alert a = new Alert(AlertType.WARNING);
					a.setContentText("nothing selected to be edited");
					a.show();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Button remove = new Button("Remove");
		remove.setPrefWidth(70);
		remove.setStyle("-fx-background-color: #A72F48; -fx-text-fill: #ffffff;-fx-font-weight: bold;");
		remove.setOnAction(actionEvent -> {

			if (accesscd.getCds() == null) {
				System.out.println("no items to remove");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("nothing to remove");
				alert.show();
			} else {
				CD toRemove = (CD) tableOfallItems.getSelectionModel().getSelectedItem();
				tableOfallItems.getItems().remove(toRemove);
				accesscd.remove(toRemove);

			}
		});
		HBox buttonBox = new HBox(30);
		buttonBox.setPadding(new Insets(10, 0, 10, 130));
		buttonBox.getChildren().addAll(add, edit, remove, exit);
		VBox layout = new VBox(15);
		layout.setPadding(new Insets(50));
		layout.getChildren().addAll(firstRow,tableOfallItems, buttonBox);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #172e4d;");
		Scene scene = new Scene(layout, 600, 400);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Manage items");
		stage.show();
		exit.setOnAction(actionEvent -> {
			stage.close();
		});
	}

	public void addTable() {

	}

}
