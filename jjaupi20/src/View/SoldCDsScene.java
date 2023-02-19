package View;

import java.util.ArrayList;

import Model.Bill;
import Model.CD;
import Model.Cashier;
import Model.ProductOnCart;
import Model.accessCashiers;
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

public class SoldCDsScene {

	ObservableList<ProductOnCart> list;
	TableView table;
	Button close;
	Stage stage;
	ArrayList<ProductOnCart> cds;
	public SoldCDsScene() {
		this.show();
	}
	public void show() {
		accessCashiers acCash = new accessCashiers();
		cds = new ArrayList<ProductOnCart>();
		for(Cashier c: acCash.getCashiers()) {
			for(Bill b: c.getBills()) {
				for(ProductOnCart cd: b.getProducts()) {
					cds.add(cd);
				}
			}
		}
		double d=0;
		for(Cashier c: acCash.getCashiers()) {
			for(Bill b: c.getBills()) {
				d+=b.getPrice();
			}
		}
		Label totalPrice = new Label("Total sold price: " + d);
		totalPrice.setStyle("-fx-text-fill: #ffffff;");
	
		close = new Button("Close");
		close.setStyle("-fx-background-color: #d62727; -fx-text-fill: #ffffff;");
		close.setOnAction(actionEvent ->{
			this.stage.close();
		});
		list = FXCollections.observableArrayList(cds);
		setTable();
		VBox v = new VBox(20);
		v.setPadding(new Insets(20, 30, 20, 30));
		v.setAlignment(Pos.CENTER_RIGHT);
		v.getChildren().addAll(table,totalPrice, close);
		v.setStyle("-fx-background-color: #172e4d;");
		Scene sc = new Scene(v, 400, 400);
		stage = new Stage();
		stage.setScene(sc);
		stage.show();
		
	}
	public void setTable() {
		table = new TableView();
		TableColumn<ProductOnCart, String> column1 = new TableColumn<>("Product name");
		column1.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<ProductOnCart, Double> column2 = new TableColumn<>("unit price");
		column2.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<ProductOnCart, Double> column4 = new TableColumn<>("Total price");
		column4.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		TableColumn<ProductOnCart, Integer> column3 = new TableColumn<>("quantity");
		column3.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		table.getColumns().addAll(column1, column2, column3, column4);
		table.setItems(list);
		
	}
}
