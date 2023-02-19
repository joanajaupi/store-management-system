package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import Model.Bill;
import Model.Cashier;
import Model.ProductOnCart;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SelectedCashierInsights {

	Label lbl;
	Button close;
	TableView table;
	ObservableList<Bill> products;
	ImageView imv;
	Stage window;
	Label lbl2;
	Button viewBill;

	public SelectedCashierInsights(Cashier cashier, Stage stage) {
		System.out.println(cashier.getName() + " insights");
		try {
			show(cashier, stage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Bill c : cashier.getBills()) {
			System.out.println(c.getBillNo() + " " + c.getPrice() + " " + c.getDatePrinted());
		}
	}

	public void show(Cashier cashier, Stage stage) throws FileNotFoundException {
		window = stage;
		String cashierInfo = "cashier: " + cashier.getName() + " " + cashier.getSurname();
		lbl = new Label(cashierInfo);
		lbl.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 12");
		Image img = new Image(new FileInputStream("sources/Images/adminLogo.png"));
		imv = new ImageView(img);
		imv.setFitHeight(70);
		imv.setFitWidth(70);

		products = FXCollections.observableArrayList(cashier.getBills());
		table = new TableView<Bill>();
		TableColumn<Bill, Integer> c1 = new TableColumn<>("billNo");
		c1.setCellValueFactory(new PropertyValueFactory("billNo"));
		TableColumn<Bill, Date> c2 = new TableColumn<>("date");
		c2.setCellValueFactory(new PropertyValueFactory("datePrinted"));
		TableColumn<ProductOnCart, Double> c3 = new TableColumn<>("price");
		c3.setCellValueFactory(new PropertyValueFactory("price"));
		table.setItems(products);
		table.getColumns().addAll(c1, c2, c3);
		table.setPrefHeight(200);

		String totalBillsAndMoneyGenerated;
		double total = 0;
		for (Bill b : products) {
			total += b.getPrice();
		}
		totalBillsAndMoneyGenerated = "Total number of bills: " + cashier.getNumberOfBills() + "\n"
				+ "total money generated: " + total + "$";
		lbl2 = new Label(totalBillsAndMoneyGenerated);
		lbl2.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 12");

		close = new Button("close");
		close.setPrefSize(100, 20);
		close.setStyle("-fx-background-color: #B13333; -fx-text-fill: #ffffff;");
		close.setOnAction(actionEvent -> {
			window.close();
			new CashiersPerformance();
		});

		viewBill = new Button("View bill");
		
		viewBill.setPrefSize(100, 20);
		viewBill.setOnAction(actionEvent ->{
			 Bill  selectedRow = (Bill) table.getSelectionModel().getSelectedItem();
			 if(selectedRow!= null) {
				 new viewBill(selectedRow);
			 }
		});
		VBox layout = new VBox(10);

		layout.getChildren().addAll(imv, lbl, table, lbl2, viewBill, close);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color:#172e4d; ");
		layout.setPadding(new Insets(20, 40, 20, 40));

		Scene sc = new Scene(layout, 600, 600);
		window.setScene(sc);
		window.show();

	}
}
