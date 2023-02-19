package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Model.Cashier;
import Model.User;
import Model.accessCashiers;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CashiersPerformance {

	Label lbl;
	ImageView imv;
	ObservableList<Cashier> cashiers;
	Button close;
	Stage stage;
	Button accessCashiersPerformance;
	Button overall;
	ListView listV;

	public CashiersPerformance() {
		try {
			this.show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void show() throws FileNotFoundException {

		stage = new Stage();
		Image img = new Image(new FileInputStream("sources/Images/analytics.png"));
		imv = new ImageView(img);
		imv.setFitHeight(70);
		imv.setFitWidth(70);

		accessCashiers c = new accessCashiers();
		cashiers = FXCollections.observableArrayList(c.getCashiers());

		/*for (Cashier cashier : c.getCashiers()) {
			System.out.println("cashier : " + cashier.getName() + "has : " + cashier.getBills().size());
		}*/
		VBox layout = new VBox(20);
		setTable(cashiers);
		listV.setMinHeight(200);

		close = new Button("close");
		close.setAlignment(Pos.TOP_RIGHT);
		close.setStyle("-fx-background-color: #B13333; -fx-text-fill: #ffffff;");
		close.setOnAction(actionEvent -> {
			stage.close();

		});
		lbl = new Label("Cashiers performance \n Select a cashier to view individual performance");
		lbl.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 12");
		accessCashiersPerformance = new Button("selected cashier performance");
		accessCashiersPerformance.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		accessCashiersPerformance.setPrefWidth(Double.MAX_VALUE);
		accessCashiersPerformance.setOnAction(actionEvent -> {
			
			Cashier selectedRow = (Cashier) listV.getSelectionModel().getSelectedItem();
			if (selectedRow != null) {

				new SelectedCashierInsights(selectedRow, stage);
				// stage.close();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("no cashier selected");
				alert.show();
			}
		});
		
		overall = new Button("compare performance");
		overall.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		overall.setPrefWidth(Double.MAX_VALUE);
		overall.setOnAction(actionEvent ->{
			new Stats2(this.stage);
		});
		HBox firstRow = new HBox(120);
		firstRow.getChildren().addAll(imv, close);
		layout.setPadding(new Insets(20, 30, 30, 30));
		layout.getChildren().addAll(firstRow, lbl, listV,  accessCashiersPerformance, overall);

		// layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #172e4d;");
		Scene scene = new Scene(layout, 300, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		Image icon = new Image(new FileInputStream("sources/Images/icon.png"));
		stage.getIcons().add(icon);
		stage.show();

	}

	public void setTable(ObservableList<Cashier> cashiers) {
		listV = new ListView();
		listV.setItems(cashiers);
	}
}
