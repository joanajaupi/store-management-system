package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Model.AccessCD;
import Model.CD;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenagerScene {

	HBox overAllLayout;
	VBox firstButtonColumn;
	VBox secondButtonColumn;
	Button addNewGenre;
	Button manageItems;
	Button addQuantity;
	Button checkCashierPerformance;
	Button suppliers;
	Button solds;
	ImageView menagerLogo;
	Label menagerInfo;
	HBox firstRow;
	Scene menagerScene;
	VBox lt;
	Button logOutButton;
	HBox lastRow;
	User user;

	public MenagerScene(User us) {
		String mngName = "manager: " + us.getName() + " " + us.getSurname();
		menagerInfo = new Label(mngName);
		user = us;

	}

	public MenagerScene() {

	}

	public void showScene(Stage stage) throws FileNotFoundException {
		firstRow = new HBox(20);
		firstRow.setPadding(new Insets(10));

		menagerLogo = new ImageView(new Image(new FileInputStream("sources/Images/menagerLogo.png")));
		menagerLogo.setFitHeight(100);
		menagerLogo.setFitWidth(100);
		menagerInfo.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

		firstRow.getChildren().addAll(menagerLogo, menagerInfo);
		firstRow.setAlignment(Pos.CENTER);
		addNewGenre = new Button("Add new Genre");
		addNewGenre.setPrefSize(130, 20);
		addNewGenre.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		addNewGenre.setOnAction(actionEvent -> {
			new AddNewGenreScene();
		});
		setLayout(stage);
		lt = new VBox(20);
		lt.setPadding(new Insets(10, 60, 10, 60));
		lt.setStyle("-fx-background-color: #172e4d;");
		lt.getChildren().addAll(firstRow, overAllLayout, lastRow);
		menagerScene = new Scene(lt, 450, 400);
		stage.setScene(menagerScene);

	}

	public void setLayout(Stage stage) {
	
		addNewGenre = new Button("Add new Genre");
		addNewGenre.setPrefSize(130, 20);
		addNewGenre.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		addNewGenre.setOnAction(actionEvent -> {
			new AddNewGenreScene();
		});

		manageItems = new Button("Manage items");
		manageItems.setPrefSize(130, 20);
		manageItems.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		manageItems.setOnAction(actionEvent -> {
			new ItemsScene();
		});
		addQuantity = new Button("Stock management");
		addQuantity.setPrefSize(130, 20);
		addQuantity.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		addQuantity.setOnAction(actionEvent -> {
			new ManageQuantityScene();
		});
		firstButtonColumn = new VBox(20);
		firstButtonColumn.getChildren().addAll(addNewGenre, manageItems, addQuantity);

		checkCashierPerformance = new Button("Cashier Performance");
		checkCashierPerformance.setStyle("-fx-background-color: #4d75ab;-fx-text-fill: #ffffff;");
		checkCashierPerformance.setPrefSize(130, 20);
		checkCashierPerformance.setOnAction(actionEvent ->{
			new CashiersPerformance();
		});
		suppliers = new Button("Suppliers");
		suppliers.setPrefSize(130, 20);
		suppliers.setStyle("-fx-background-color: #4d75ab;-fx-text-fill: #ffffff;");

		suppliers.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new suppliersScene();
			}
		});
		solds = new Button("Sold CDs");
		solds.setPrefSize(130, 20);
		solds.setOnAction(actionEvent ->{
			new SoldCDsScene();
		});
		solds.setStyle("-fx-background-color: #4d75ab;-fx-text-fill: #ffffff;");
		secondButtonColumn = new VBox(20);
		secondButtonColumn.getChildren().addAll(checkCashierPerformance, suppliers, solds);

		overAllLayout = new HBox(20);
		overAllLayout.setPadding(new Insets(20, 60, 20, 60));
		overAllLayout.getChildren().addAll(firstButtonColumn, secondButtonColumn);
		overAllLayout.setPadding(new Insets(10));

		lastRow = new HBox(20);
		lastRow.setPadding(new Insets(20, 20, 20, 250));
		logOutButton = new Button("Log Out");
		logOutButton.setPrefSize(130, 20);
		logOutButton.setStyle("-fx-background-color: #86175a; -fx-text-fill: #ffffff");
		logOutButton.setOnAction(actionEvent -> {
			try {
				new MainMenuScene().start(stage);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		lastRow.getChildren().add(logOutButton);
		
		checkQuantity();

	}
	public void checkQuantity() {
		AccessCD accessCd = new AccessCD();
		ObservableList<CD> productList = FXCollections.observableArrayList();
		for (CD c : accessCd.getCds()) {
			if (c.getStockQuantity() <= 5) {
				productList.add(c);
			}
		}
		if ((productList.size() > 0)) {
			new LowQuantity().showScene(productList);
		}
	}
}