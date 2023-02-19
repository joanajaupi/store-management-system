package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Model.AccessBoughtCDs;
import Model.AccessCD;
import Model.BoughtProducts;
import Model.CD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageQuantityScene {

	TableView<CD> table;
	Button editQuantity;
	AccessCD acCd;
	TextField txtF;
	ObservableList<CD> cds;
	Label lbl;
	Stage stage;
	Button close;
	ImageView imv;
	public ManageQuantityScene() {
		try {
			show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void show() throws FileNotFoundException {
		
		Image img = new Image(new FileInputStream("sources/Images/storage.png"));
		imv = new ImageView(img);
		imv.setFitHeight(60);
		imv.setFitWidth(60);
		acCd = new AccessCD();
		cds = FXCollections.observableArrayList(acCd.getCds());
		setTable();
		lbl = new Label("Enter quantity");
		lbl.setStyle("-fx-font-size: 14; -fx-text-fill: #ffffff");
		txtF = new TextField();
		txtF.setPromptText("enter quantity");
		txtF.setPrefWidth(150);
		txtF.setStyle("-fx-background-color: #12243B  ; -fx-text-fill: #ffffff;");
		editQuantity = new Button("Edit quantity");
		editQuantity.setStyle("-fx-background-color:  #53A854; -fx-text-fill: #ffffff");
		editQuantity.setOnAction(actionEvent -> {
			CD selectedItem = (CD) table.getSelectionModel().getSelectedItem();
			Alert a = new Alert(AlertType.WARNING);
			a.setContentText("Invalid input, please enter a valid integer");
			try {
				int quantity = Integer.parseInt(txtF.getText());
				if (quantity > 0 && selectedItem != null) {
					selectedItem.addQuantityBack(quantity);
					acCd.editCD(acCd.getPosition(selectedItem), selectedItem);
					AccessBoughtCDs acbd = new AccessBoughtCDs();
					//String name, String barcode, double boughtPrice, String supplier, int quantity
					acbd.addProd(new BoughtProducts(selectedItem.getCdName(), selectedItem.getCdID(), selectedItem.getByingPrice(), selectedItem.getSupplier(), quantity));
					table.refresh();
				} else
					a.show();
			} catch (Exception e) {
				System.out.println("invalid input");

				a.show();
			}
		});

		close = new Button("Close");
		close.setStyle("-fx-background-color:  #A72F48; -fx-text-fill: #ffffff;");
		close.setOnAction(actionEvent -> {
			stage.close();
		});
		HBox box = new HBox(20);
		box.setPadding(new Insets(5, 20, 5, 20));
		box.getChildren().addAll(txtF, editQuantity);
		VBox layout = new VBox(20);
		layout.setPadding(new Insets(20, 30, 20, 30));
		layout.setStyle("-fx-background-color: #172e4d;");
		layout.getChildren().addAll(imv,lbl, table, box, close);
		layout.setAlignment(Pos.CENTER);
		stage = new Stage();
		stage.setScene(new Scene(layout, 380, 450));
		stage.setTitle("Quantity management");
		Image icon = new Image(new FileInputStream("sources/Images/icon.png"));
		stage.getIcons().add(icon);
		stage.setResizable(false);
		stage.show();

	}

	public void setTable() {

		table = new TableView<CD>();

		TableColumn<CD, String> cl1 = new TableColumn<>("CD name");
		cl1.setCellValueFactory(new PropertyValueFactory<>("cdName"));
		TableColumn<CD, Integer> cl2 = new TableColumn<>("Quantity");
		cl2.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

		if (cds == null) {
			table.setPlaceholder(new Label("No rows to display"));
		} else
			table.setItems(cds);
		table.getColumns().addAll(cl1, cl2);
	}

}
