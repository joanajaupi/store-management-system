package View;

import Model.AccessSuppliers;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Model.Supplier;

public class suppliersScene {
	TableView<Supplier> supplTab;
	AccessSuppliers acSup;
	Stage stg;
	Button add;
	Label lbl;
	
	public suppliersScene() {
		stg= new Stage();
		showSuppliersStage();
	}
	public suppliersScene(Stage stage) {
		this.stg =stage;
		showSuppliersStage();
	}

	public void showSuppliersStage() {
		
		acSup = new AccessSuppliers();
		lbl = new Label("Add a new supplier");
		lbl.setStyle("-fx-font-size: 15; -fx-text-fill: #ffffff;");
		TextField addSupplier = new TextField();
		addSupplier.setPromptText("add supplier name");
		addSupplier.setStyle("-fx-background-color: #12243B  ; -fx-text-fill: #ffffff;");
		
		add= new Button("add");
		add.setStyle("-fx-background-color: #56AB7A; -fx-text-fill: #ffffff;");
		add.setOnAction(actionEvent -> {
			if (addSupplier.getText().matches("")) {
				Alert a = new Alert(AlertType.WARNING);
				a.setContentText("no content entered");
				a.show();
				System.out.println(">>> no supplier entered");
				
			} else {
				String sup = addSupplier.getText();
				acSup.addSup(new Supplier(sup));
				System.out.println("Supplier added");
				addSupplier.clear();
				Alert al = new Alert(AlertType.INFORMATION);
				al.setContentText("Supplier added");
				al.show();
			}
		});
		
		Button viewSupp = new Button("view suppliers") ;
		viewSupp.setStyle("-fx-background-color: #12243B  ; -fx-text-fill: #ffffff; ");
		viewSupp.setOnAction(actionEvent ->{
			new viewSuppliers(stg);
		});
		Button canc = new Button("cancel");
		canc.setStyle("-fx-background-color:  #A72F48; -fx-text-fill: #ffffff; -fx-font-weight: bold; ");
		canc.setOnAction(actionEvent ->{
			stg.close();
		});
		
		VBox layout = new VBox(20);
		layout.setPadding(new Insets(10, 20, 10, 20));
		HBox buttonss = new HBox(10);
		buttonss.getChildren().addAll(canc, add, viewSupp);
		buttonss.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(lbl, addSupplier, buttonss);
		layout.setStyle("-fx-background-color: #172e4d;");
		layout.setAlignment(Pos.CENTER);
		Scene sc = new Scene(layout, 450, 200);
	
		
		stg.setScene(sc);
		stg.show();
	}

}

