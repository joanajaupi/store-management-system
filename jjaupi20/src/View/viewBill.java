package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Model.Bill;
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

public class viewBill {

	Bill b;
	TableView table;
	Button close;
	Label lbl;
	ImageView imv;
	ObservableList<ProductOnCart> p;
	Stage stg;
	Scene scene;

	public viewBill(Bill bill) {
		this.b = bill;
		try {
			show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("fNotfound");
		}
	}

	public void show() throws FileNotFoundException {
		Image img = new Image(new FileInputStream("sources/Images/bill.png"));
		imv = new ImageView(img);
		imv.setFitHeight(50);
		imv.setFitWidth(50);
		p = FXCollections.observableArrayList(b.getProducts());
		setTable();
		close = new Button("Close");
		close.setStyle("-fx-background-color:  #B13333; -fx-text-fill: #ffffff;");
		close.setOnAction(actionEvent -> {
			stg.close();

		});
		String txt = "Bill number" + b.getBillNo() + " date " + b.getDatePrinted() + " total price: " + b.getPrice();
		lbl = new Label(txt);
		lbl.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14");
		VBox layout = new VBox(30);
		layout.getChildren().addAll(imv, table, lbl, close);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(20, 50, 20, 50));
		layout.setStyle("-fx-background-color: #172e4d;");
		scene = new Scene(layout, 500, 550);
		stg = new Stage();
		stg.setScene(scene);
		stg.show();

	}

	public void setTable() {
		table = new TableView();
		TableColumn<ProductOnCart, String> col1 = new TableColumn<ProductOnCart, String>("name");
		col1.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn<ProductOnCart, Double> col2 = new TableColumn<ProductOnCart, Double>("unitPrice");
		col2.setCellValueFactory(new PropertyValueFactory("price"));
		TableColumn<ProductOnCart, Integer> col3 = new TableColumn<ProductOnCart, Integer>("quantity");
		col3.setCellValueFactory(new PropertyValueFactory("quantity"));
		TableColumn<ProductOnCart, Double> col4 = new TableColumn<ProductOnCart, Double>("totalprice");
		col4.setCellValueFactory(new PropertyValueFactory("totalPrice"));
		table.setItems(p);
		table.getColumns().addAll(col1, col2, col3, col4);
	}

}
