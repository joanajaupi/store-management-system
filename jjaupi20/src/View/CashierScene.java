package View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Optional;
import Model.AccessCD;
import Model.AccessGenres;
import Model.AccessSoldProducts;
import Model.Bill;
import Model.CD;
import Model.Cashier;
import Model.ProductOnCart;
import Model.SoldProducts;
import Model.User;
import Model.accessCashiers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CashierScene {

	TableView<ProductOnCart> inCart;
	Button print;
	Button Filter;
	TableView<CD> allItems;
	Label allItemsLabel;
	Button remove;
	Label billLabel;
	ImageView cashierImage;
	Label cashierName;
	VBox halfLayout;
	VBox otherHalf;
	Label quantity;
	Button edit;
	TextField quantityField;
	// cashier cash;
	Stage window;
	Button logOut;
	Button add;
	AccessCD accessCds;
	ChoiceBox<String> filterByGenre;
	HBox cashierHbx;
	ObservableList<CD> list;
	ObservableList<ProductOnCart> cart;
	Cashier cashier;

	public CashierScene(User us) {
		String cashNam = "cashier: " + us.getName() + " " + us.getSurname();
		cashierName = new Label(cashNam);
		cashierName.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 15;");
		cashier = (Cashier) us;
		System.out.println("name" + cashier.getName());
	}

	public CashierScene() {

	}

	@SuppressWarnings("unchecked")
	public void start(Stage stage) throws FileNotFoundException {

		window = stage;
		accessCds = new AccessCD();
		AccessGenres aGen = new AccessGenres();
		cart = FXCollections.observableArrayList();
		list = FXCollections.observableArrayList(accessCds.getCds());

		allItemstable(list);

		filterByGenre = new ChoiceBox<String>();
		filterByGenre.getItems().add("all");
		filterByGenre.getItems().addAll(aGen.toStringArrayList());
		filterByGenre.setValue("all");
		filterByGenre.setStyle("-fx-text-fill: #ffffff");

		Image img = new Image(new FileInputStream("sources/Images/cashierLogo.png"));
		cashierImage = new ImageView(img);
		cashierImage.setFitHeight(80);
		cashierImage.setFitWidth(80);
		cashierHbx = new HBox(20);
		cashierHbx.getChildren().addAll(cashierImage, cashierName);

		Filter = new Button("Filter results");
		Filter.setStyle("-fx-background-color: #5986c2; -fx-font-weight: bold;");
		Filter.setOnAction(new a());

		quantity = new Label("Quantity");
		quantity.setStyle("-fx-text-fill: #ffffff");
		quantityField = new TextField();
		quantityField.setPromptText("quantity");

		add = new Button("Add to cart");
		add.setStyle("-fx-background-color: #33B13C; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		add.setOnAction(actionEvent -> {

			CD productToAdd = (CD) allItems.getSelectionModel().getSelectedItem();
			if (productToAdd != null) {
				int quant = 0;
				try {
					quant = Integer.parseInt(quantityField.getText());
				} catch (Exception e) {
					System.out.println("invalid quantity");
					Alert b = new Alert(AlertType.WARNING);
					b.setContentText("invalid quantity");
					b.show();
				}
				// lol make sure quantity is bigger than zero
				if (quant > 0 && quant <= productToAdd.getStockQuantity()) {
					ProductOnCart p = new ProductOnCart(productToAdd);
					p.setQuantity(quant);
					// bill.addCdTolist(p);
					cart.add(p);
					accessCds.editCD(accessCds.getPosition(productToAdd), productToAdd);// to edit the cds count
					allItems.refresh();
					remove.setDisable(false);
					print.setDisable(false);
				} else {
					Alert alert2 = new Alert(AlertType.WARNING);
					alert2.setContentText("Invalid quantity");
					alert2.show();
					remove.setDisable(false);
					print.setDisable(false);
				}
				// productToAdd.removeFromStock(quant);

			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("Please select a product");
				alert.show();
			}
		});

		HBox filterBox = new HBox(10);
		filterBox.getChildren().addAll(filterByGenre, Filter);
		HBox quantityBox = new HBox(10);
		quantityBox.getChildren().addAll(quantity, quantityField, add);

		allItemsLabel = new Label("All items");
		allItemsLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14;");

		halfLayout = new VBox(20);
		halfLayout.setAlignment(Pos.CENTER);
		halfLayout.setPadding(new Insets(10, 50, 40, 20));
		halfLayout.getChildren().addAll(cashierHbx, filterBox, allItemsLabel, allItems, quantityBox);
		setBillLayout();

		HBox layout = new HBox(20);
		layout.setStyle("-fx-background-color: #172e4d;");
		layout.getChildren().addAll(halfLayout, otherHalf);
		Scene scene = new Scene(layout, 850, 550);
		// scene.getStylesheets().add("style.css");

		stage.setScene(scene);
		stage.setResizable(true);
		stage.show();

	}

	public void setBillLayout() {
		Label billLabel = new Label("shopping cart");
		billLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14;");
		incartTable();

		remove = new Button("Remove");
		remove.setPrefWidth(70);
		remove.setDisable(true);
		remove.setOnAction(actionEvent -> {
			if (cart.size() > 0) {
				ProductOnCart productToRemove = (ProductOnCart) inCart.getSelectionModel().getSelectedItem();
				if (productToRemove != null) {
					for (CD c : list) {
						if (c.getCdName() == productToRemove.getName()) {
							c.addQuantityBack(productToRemove.getQuantity());
							accessCds.editCD(accessCds.getPosition(c), c);
							cart.remove(productToRemove);
							inCart.refresh();
							allItems.refresh();
							break;

						}
					}
				} else {
					Alert a = new Alert(AlertType.WARNING);
					a.setContentText("Please select a row to remove the product!");
					a.setHeaderText("Invalid operation");
					a.show();
				}
			}

			else {
				remove.setDisable(true);
				print.setDisable(true);
			}
		});
		print = new Button("print");
		print.setPrefWidth(70);
		remove.setDisable(true);
		print.setDisable(true);
		print.setStyle("-fx-background-color: #33B13C; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		print.setOnAction(actionEvent -> {
			double total = 0;
			for (ProductOnCart c : cart) {
				total += c.getTotalPrice();
			}
			Label label = new Label();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete File");
			alert.setHeaderText("Confirm bill");
			alert.setContentText("Bill total: " + total + "$");

			// option != null.
			Optional<ButtonType> option = alert.showAndWait();

			if (option.get() == null) {
				label.setText("No selection!");
			} else if (option.get() == ButtonType.OK) {
				label.setText("Confirmed!!");
				remove.setDisable(true);
				print.setDisable(true);
				incartTable();
				this.printBill();
				
			} else if (option.get() == ButtonType.CANCEL) {
				label.setText("Cancelled!");
			} else {
				label.setText("-");
			}

		});
		logOut = new Button("Log out");
		logOut.setStyle("-fx-background-color: #B13333; -fx-text-fill: #ffffff");
		logOut.setPrefWidth(70);
		logOut.setOnAction(actionEvent -> {
			try {

				new MainMenuScene().start(window);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});
		VBox v = new VBox(10);
		v.getChildren().addAll(print, logOut);
		HBox b = new HBox(20);
		b.setPadding(new Insets(10, 0, 10, 150));
		b.getChildren().addAll(remove, v);
		otherHalf = new VBox(10);
		otherHalf.setPadding(new Insets(30, 10, 30, 5));
		otherHalf.getChildren().addAll(billLabel, inCart, b);

	}

	public void allItemstable(ObservableList<CD> list) {
		allItems = new TableView<CD>();
		TableColumn<CD, Integer> column0 = new TableColumn<>("Cd id");
		column0.setCellValueFactory(new PropertyValueFactory<>("cdID"));
		TableColumn<CD, String> column1 = new TableColumn<>("Cd name");
		column1.setCellValueFactory(new PropertyValueFactory<>("cdName"));
		TableColumn<CD, Double> column2 = new TableColumn<>("Unit price");
		column2.setCellValueFactory(new PropertyValueFactory<>("cdPrice"));
		TableColumn<CD, String> column3 = new TableColumn<>("Cdgenre");
		column3.setCellValueFactory(new PropertyValueFactory<>("cdGenre"));
		TableColumn<CD, Integer> column4 = new TableColumn<>("in Stock");
		column4.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

		allItems.setItems(list);

		allItems.getColumns().add(column0);
		allItems.getColumns().add(column1);
		allItems.getColumns().add(column2);
		allItems.getColumns().add(column3);
		allItems.getColumns().add(column4);

	}

	public void incartTable() {
		inCart = new TableView();
		TableColumn<ProductOnCart, String> column1 = new TableColumn<>("Product name");
		column1.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<ProductOnCart, Double> column2 = new TableColumn<>("unit price");
		column2.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<ProductOnCart, Double> column4 = new TableColumn<>("Total price");
		column4.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		TableColumn<ProductOnCart, Integer> column3 = new TableColumn<>("quantity");
		column3.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		inCart.getColumns().addAll(column1, column2, column3, column4);
		inCart.setItems(cart);
	}

	public void printBill() {
		accessCashiers acCashier = new accessCashiers();
		if (cart.size() > 0) {
			// Create new bill
			Bill bill = new Bill();
			// Get the price of the products and set it to the added bill. Also add products
			// sold to cashier's soldProducts.
			double totalBillPrice = 0;
			AccessSoldProducts acSold = new AccessSoldProducts();
			for (ProductOnCart pInCart : cart) {
				bill.addProduct(pInCart);
				totalBillPrice += pInCart.getTotalPrice();
				
				
				boolean doesProdE = false;
				for (SoldProducts c : acSold.getCdsSold()) {
					if (pInCart.getName().equals(c.getSoldProductName())) {
						doesProdE = true;
						c.addQuantity(pInCart.getQuantity());
						acSold.write();
					}
					
				}
				if (!doesProdE) {
					acSold.add(new SoldProducts(pInCart.getName(), pInCart.getPrice(), pInCart.getQuantity()));
				}
				
			}
			bill.setPrice(totalBillPrice);
			cashier.addNewBill(bill);
			// Put bill on txt document
			String uhomefolder = System.getProperty("user.home");
			File billDirectory = new File(uhomefolder + "\\Desktop\\BillFolder");
			billDirectory.mkdirs();

			String billName = "bill" + bill.getBillNo() + ".txt";
			File billPath = new File(billDirectory.getAbsolutePath() + "\\" + billName);
			try {
				FileWriter fw = new FileWriter(billPath);
				BufferedWriter bw = new BufferedWriter(fw);

				for (ProductOnCart pInCart : cart)
					bw.write(pInCart.getName() + " | Quantity: " + pInCart.getQuantity() + " | Unit Price: "
							+ pInCart.getPrice() + "$ | Total Price: " + pInCart.getTotalPrice() + "$\n");
				bw.write("Total price: " + bill.getPrice() + "$");

				bw.close();
				fw.close();
			} catch (Exception e) {
				System.out.println("Something went wrong while printing the bill");
			}

			// Clear the cart
			cart.clear();
			remove.setDisable(true);
			print.setDisable(true);
		
		}
	}

	class a implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			if (filterByGenre.getValue() != "all") {
				ObservableList<CD> filteredData = FXCollections.observableArrayList();
				for (CD c : accessCds.getCds()) {
					if (c.getCdGenre().matches((String) filterByGenre.getValue())) {
						filteredData.add(c);
						System.out.println(">>>> filtered items");
					}

				}

				allItems.getItems().clear();
				allItems.getItems().addAll(filteredData);
				System.out.println("Filter pressed");
				allItems.refresh();

			} else {
				ObservableList<CD> allItem = FXCollections.observableArrayList(accessCds.getCds());
				allItems.getItems().clear();
				allItems.getItems().addAll(allItem);
				System.out.println(">>all items");

			}
		}

	}

	/*public void help() {
		for (Bill b : cashier.getBills()) {
			System.out.println("Bill number " + b.getBillNo());
			for (ProductOnCart p : b.getProducts()) {
				System.out.println(p.getName() + " unit price: " + p.getPrice() + " quantity: " + p.getQuantity()
						+ " price:" + p.getTotalPrice());

			}
			System.out.println("Bill price: " + b.getPrice());
		}
	}*/

}
