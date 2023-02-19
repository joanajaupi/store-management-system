package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import Model.Bill;
import Model.Cashier;
import Model.ProductOnCart;
import Model.accessCashiers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IncomesScene {

	Scene scene;
	Stage stg;
	Button close;
	DatePicker from;
	DatePicker to;
	Label fr;
	Label t;
	Button filter;
	Scene sc;
	ObservableList<Bill> list;
	TableView<Bill> table;
	ArrayList<Bill> b;
	Label lbl;

	public IncomesScene() {
		this.show();
	}

	public void show() {
		accessCashiers acCash = new accessCashiers();
		b = new ArrayList<>();
		for (Cashier c : acCash.getCashiers()) {
			b.addAll(c.getBills());
		}
		list = FXCollections.observableArrayList();
		list.addAll(b);
		fr = new Label("from");
		fr.setStyle("-fx-text-fill: #ffffff;");
		t = new Label("To");
		t.setStyle("-fx-text-fill: #ffffff;");
		from = new DatePicker();
		to = new DatePicker();
		lbl = new Label();

		filter = new Button("Filter");
		filter.setOnAction(actionEvent -> {
			ObservableList<Bill> list2 = FXCollections.observableArrayList();
			if (from.getValue() != null && to.getValue() != null) {
				for (Cashier c : acCash.getCashiers()) {
					for (Bill b : c.getBills()) {
						if (from.getValue().isBefore(b.getDatePrinted()) && to.getValue().isAfter(b.getDatePrinted())) {
							list2.add(b);
						}
					}
				}

			}
			table.getItems().clear();
			table.getItems().addAll(list2);
			double t = 0;
			for(Bill b: list2) {
				t+= b.getPrice();
			}
			lbl.setText("total incomes "+ t);
		});
		close = new Button("Close");
		close.setStyle("-fx-background-color: #A72F48; -fx-text-fill: #ffffff;-fx-font-weight: bold;");
		close.setOnAction(actionEvent -> {
			this.stg.close();
			new IncomesOrCostsScene();
		});
		double tot = 0 ;
		for(Cashier c: acCash.getCashiers()) {
			for(Bill b: c.getBills()) {
				tot+=b.getPrice();
			}
		}

		lbl.setText("total incomes " + tot);
		lbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #ffffff;");
		setTable(list);
		HBox h = new HBox(20);
		h.getChildren().addAll(fr, from, t, to, filter, close);
		VBox v = new VBox(20);
		Image img = null;
		try {
			img = new Image(new FileInputStream("sources/Images/i.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView imv = new ImageView(img);
		imv.setFitHeight(70);
		imv.setFitWidth(70);
		v.getChildren().addAll(imv, h, table, lbl);
		v.setAlignment(Pos.CENTER);
		v.setPadding(new Insets(30));
		v.setStyle("-fx-background-color: #172e4d;");
		//v.setAlignment(Pos.CENTER_RIGHT);
		sc = new Scene(v, 700, 350);
		stg = new Stage();
		stg.setScene(sc);
		stg.show();
		stg.setTitle("Incomes");
	}

	public void setTable(ObservableList<Bill> billList) {

		table = new TableView();
		TableColumn<Bill, Integer> c1 = new TableColumn<>("billNo");
		c1.setCellValueFactory(new PropertyValueFactory("billNo"));
		TableColumn<Bill, Date> c2 = new TableColumn<>("date");
		c2.setCellValueFactory(new PropertyValueFactory("datePrinted"));
		TableColumn<Bill, Double> c3 = new TableColumn<>("price");
		c3.setCellValueFactory(new PropertyValueFactory("price"));
		table.setItems(billList);
		table.getColumns().addAll(c1, c2, c3);

	}
}
