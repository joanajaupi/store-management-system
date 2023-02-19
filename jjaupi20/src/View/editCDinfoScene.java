package View;

import java.io.FileNotFoundException;

import Model.AccessCD;
import Model.AccessGenres;
import Model.AccessSuppliers;
import Model.CD;
import Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class editCDinfoScene {
	// TextField id, name, singer, price, quantity;
	CD info;
	Button edit;
	TextField id;
	TextField name;
	TextField price;
	TextField singer;
	Label lbl, nameLb, idLb, priceLb, singLb, genreLb, suppLb;
	VBox layout;
	Button cancel;
	ChoiceBox genre;
	ComboBox supplier;
	Stage stage;

	public editCDinfoScene(CD toEdit, Stage stage) throws FileNotFoundException {
		this.stage = stage;
		info = toEdit;
		setLayout();
		setTheText();
		// super.add.setDisable(true);

	}

	public void setTheText() throws FileNotFoundException {

		AccessCD acCD = new AccessCD();

		if (info != null) {
			System.out.println(info.getCdID());
			id.setText(info.getCdID());
			name.setText(info.getCdName());
			price.setText(String.valueOf(info.getCdPrice()));
			singer.setText(info.getCdSinger());
			genre.setValue(info.getCdGenre());
			supplier.setValue(info.getSupplier());
		} else
			System.out.println("Still no cd to edit");

	}

	public void setLayout() {
		AccessSuppliers acSupp = new AccessSuppliers();
		AccessGenres aGen = new AccessGenres();
		id = new TextField();
		id.setStyle("-fx-background-color: #595959;-fx-text-fill: #ffffff");
		name = new TextField();
		name.setStyle("-fx-background-color:#595959;-fx-text-fill: #ffffff");
		price = new TextField();
		price.setStyle("-fx-background-color: #595959;-fx-text-fill: #ffffff");
		singer = new TextField();
		singer.setStyle("-fx-background-color: #595959;-fx-text-fill: #ffffff");
		lbl = new Label("Edit CD info");
		lbl.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 17;-fx-font-weight: bold ");
		edit = new Button("edit");
		edit.setPrefWidth(60);
		supplier = new ComboBox();
		supplier.setPrefWidth(Double.MAX_VALUE);
		supplier.setPromptText("Suppliers");
		ObservableList<Supplier> SuppList = FXCollections.observableArrayList(acSupp.getSuppliers());
		supplier.getItems().addAll(acSupp.toStringArrayList());
		supplier.setValue("choolse supplier");
		supplier.setStyle("-fx-background-color: #595959;-fx-text-fill: #ffffff");
		genre = new ChoiceBox<>();
		genre.setPrefWidth(Double.MAX_VALUE);
		genre.getItems().addAll(aGen.toStringArrayList());
		genre.setStyle("-fx-background-color: #595959;-fx-text-fill: #ffffff");
		layout = new VBox(10);
		nameLb = new Label("name: ");
		nameLb.setStyle("-fx-text-fill: #ffffff");
		idLb = new Label("barcode: ");
		idLb.setStyle("-fx-text-fill: #ffffff");
		priceLb = new Label("price: ");
		priceLb.setStyle("-fx-text-fill: #ffffff");
		singLb = new Label("Singer: ");
		singLb.setStyle("-fx-text-fill: #ffffff");
		genreLb = new Label("Genre: ");
		genreLb.setStyle("-fx-text-fill: #ffffff");
		suppLb = new Label("Supplier: ");
		suppLb.setStyle("-fx-text-fill: #ffffff;");
		layout.setPadding(new Insets(20, 10, 20, 10));
		// #86175a;
		layout.setStyle("-fx-background-color: #6e6e6e");
		cancel = new Button("Cancel");
		cancel.setStyle("-fx-background-color: #A34141; -fx-text-fill: #ffffff;");
		cancel.setOnAction(actionEvent -> {
			this.stage.close();
			System.out.println(">>>>>Canceled editing of CD");
		});
		HBox e = new HBox(15);
		e.getChildren().addAll(cancel, edit);
		e.setPadding(new Insets(10, 0, 0, 200));
		layout.getChildren().addAll(lbl, idLb, id, nameLb, name, priceLb, price, singLb, singer, suppLb, supplier,genreLb, genre, e);
		edit.setAlignment(Pos.CENTER);
		edit.setStyle("-fx-background-color: #55A341; -fx-text-fill: #ffffff");
		stage.setScene(new Scene(layout, 400, 550));

		edit.setOnAction(EventHandler -> {
			try {
				String nm = name.getText();
				String sing = singer.getText();
				double pr = Double.parseDouble(price.getText());
				String ID = id.getText();
				String supp = (String) supplier.getValue();
				String genr = genre.getValue().toString();
				// int id, String name, String genre, double cdPrice, int quant, String supplier
				CD newCd = new CD(ID, nm, genr, pr, info.getStockQuantity(), supp, sing, info.getByingPrice());
				AccessCD acCD = new AccessCD();

				acCD.editCD(acCD.getPosition(info), newCd);
				System.out.println(">>edited successfully");
				this.stage.close();
				new ItemsScene();

			} catch (Exception ex) {
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setContentText("Invalid input!");
			}
		});
		stage.show();
		stage.setTitle("EDIT");
	}
}
