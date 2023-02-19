package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Model.AccessBoughtCDs;
import Model.AccessCD;
import Model.AccessGenres;
import Model.AccessSuppliers;
import Model.BoughtProducts;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddNewCDScene {
	Label TitleLabel;
	// idLabel, nameLabel, genreLabel, singerLabel, priceLabel, quantityLabel;
	TextField id, name, singer, price, quantity;
	TextField buyingPrice;
	DatePicker datePick;
	Scene scene;
	Stage AddNewCDScene;
	VBox layout;
	HBox buttonBox;
	Button add;
	ChoiceBox<Object> genre;
	ComboBox<String> supplier;
	Button cancel;
	VBox firstRow;
	ImageView imv;

	public void showAddNewCdScene() throws FileNotFoundException {
		AccessCD cdFile = new AccessCD();
		AccessGenres aGen = new AccessGenres();
		AccessBoughtCDs acbd = new AccessBoughtCDs();
		AccessSuppliers aSupp = new AccessSuppliers();
		firstRow = new VBox(20);
		TitleLabel = new Label("Add New CD ");
		TitleLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: Bold; -fx-font-size: 14;");
		imv = new ImageView(new Image(new FileInputStream("sources/Images/addNewVinyl.png")));
		imv.setFitHeight(50);
		imv.setFitWidth(50);

		id = new TextField();
		id.setPromptText("Enter product ID");
		id.setStyle("-fx-background-color: #4d75ab; -fx-prompt-text-fill: #ffffff; -fx-text-fill: #ffffff");
		firstRow.getChildren().addAll(imv, TitleLabel);
		firstRow.setAlignment(Pos.CENTER);
		name = new TextField();
		name.setPromptText("Enter product name");
		name.setStyle("-fx-background-color: #4d75ab; -fx-prompt-text-fill: #ffffff; -fx-text-fill: #ffffff");
		singer = new TextField();
		singer.setStyle("-fx-background-color: #4d75ab;  -fx-prompt-text-fill: #ffffff; -fx-text-fill: #ffffff");
		singer.setPromptText("CD singer");
		buyingPrice = new TextField();
		buyingPrice.setPromptText("Buying price");
		buyingPrice.setStyle("-fx-background-color: #4d75ab;  -fx-prompt-text-fill: #ffffff; -fx-text-fill: #ffffff");
		price = new TextField();

		price.setStyle("-fx-background-color: #4d75ab; -fx-prompt-text-fill: #ffffff; -fx-text-fill: #ffffff");
		price.setPromptText("Price");
		quantity = new TextField();
		quantity.setStyle("-fx-background-color: #4d75ab; -fx-prompt-text-fill: #ffffff; -fx-text-fill: #ffffff");
		quantity.setPromptText("Quantity");

		// ObservableList<Genres> list =
		// FXCollections.observableArrayList(accGenres.getGenreList());
		genre = new ChoiceBox<>();
		genre.getItems().addAll(aGen.toStringArrayList());
		genre.setValue("Choose the genre");

		supplier = new ComboBox<String>();
		supplier.setPromptText("Suppliers");
		//ObservableList<Supplier> SuppList =FXCollections.observableArrayList(aSupp.getSuppliers());
		supplier.getItems().addAll(aSupp.toStringArrayList());
		supplier.setValue("choolse supplier");
		supplier.setPrefWidth(Double.MAX_VALUE);
		buttonBox = new HBox(30);
		add = new Button("Add CD");

		cancel = new Button("Cancel");
		add.setStyle("-fx-background-color: #3FA550; -fx-text-fill: #ffffff;");
		add.setOnAction(EventHandler -> {
			try {
				String nm = name.getText();
				String sing = singer.getText();
				double pr = Double.parseDouble(price.getText());
				int quant = Integer.parseInt(quantity.getText());
				String ID =id.getText();
				String supp = (String) supplier.getValue();
				String genr = genre.getValue().toString();
				double buyingP = Double.parseDouble(buyingPrice.getText());
				// int id, String name, String genre, double cdPrice, int quant, String supplier
				cdFile.addCD(new CD(ID, nm, genr, pr, quant, supp, sing, buyingP));
				//String name, String barcode, double boughtPrice, String supplier, int quantity
				acbd.addProd(new BoughtProducts(nm, ID, buyingP, supp, quant));
				Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText("Added");
				a.show();
				a.setOnCloseRequest(actionEvent -> {
					this.AddNewCDScene.close();
					new ItemsScene();

				});
			} catch (Exception e) {
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setContentText("Invalid input!");
			}
		});
		cancel = new Button("Cancel");
		cancel.setStyle("-fx-background-color: #F53030; -fx-text-fill: #ffffff; ");
		cancel.setOnAction(EventHandler -> {
			AddNewCDScene.close();
		});
		buttonBox.getChildren().addAll(cancel, add);
		buttonBox.setAlignment(Pos.CENTER);

		layout = new VBox(30);
		layout.getChildren().addAll(firstRow, id, name, genre, singer, price, quantity, supplier,buyingPrice, buttonBox);

		layout.setPadding(new Insets(0, 50, 5, 50));
		layout.setStyle("-fx-background-color:  #172e4d;");

		scene = new Scene(layout, 400, 600);
		AddNewCDScene = new Stage();
		AddNewCDScene.setResizable(false);
		AddNewCDScene.setScene(scene);
		AddNewCDScene.show();

	}

}
