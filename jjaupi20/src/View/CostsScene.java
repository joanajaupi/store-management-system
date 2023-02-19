package View;

import Model.AccessBoughtCDs;
import Model.AccessCD;
import Model.AccessUsers;
import Model.BoughtProducts;
import Model.CD;
import Model.User;
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

public class CostsScene {

	TableView<BoughtProducts> table;
	Label lblForCds;
	TableView<User> table2;
	Label lblForSalaries;
	ObservableList<BoughtProducts> list;
	Button close;
	ObservableList<User> userss;
	Stage stg;

	public CostsScene(Stage stage) {
		this.stg = stage;
		this.show();
	}

	public void show() {
		AccessCD acCD = new AccessCD();
		AccessUsers acUser = new AccessUsers();
		AccessBoughtCDs abcd = new AccessBoughtCDs();
		list = FXCollections.observableArrayList(abcd.get());
		userss = FXCollections.observableArrayList(acUser.getUsers());
		setTable(list);
		double totalCostOfCds = 0;
		for (BoughtProducts c : abcd.get()) {
			totalCostOfCds += c.getTotal();
		}
		String st = "Total Cost of CDs is: " + totalCostOfCds + "$";
		Label lbl = new Label("CDs");
		lbl.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14;");
		Label lbl2 = new Label("Salaries");
		lbl2.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14;");
		lblForCds = new Label(st);
		lblForCds.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14;");
		setTable2(userss);
		double totalCostForSalaries = 0;
		for (User u : acUser.getUsers()) {
			totalCostForSalaries += u.getSalary();
		}
		String salaries = "Total cost for salaries: " + totalCostForSalaries + "$";
		lblForSalaries = new Label(salaries);
		lblForSalaries.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14;");
		double total = totalCostOfCds + totalCostForSalaries;
		String totall = "Total costs: " + total + " $";
		Label tot = new Label(totall);
		tot.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14;");
		close = new Button("close");
		close.setStyle("-fx-background-color: #A72F48; -fx-text-fill: #ffffff;-fx-font-weight: bold;");
		close.setOnAction(actionEvent -> {
			new IncomesOrCostsScene();
			this.stg.close();
		});
		VBox layout = new VBox(10);
		layout.setStyle("-fx-background-color: #172e4d;");
		layout.setPadding(new Insets(30, 30, 30, 30));
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(lbl, table, lblForCds, lbl2, table2, lblForSalaries, tot, close);
		Scene sc = new Scene(layout, 500, 600);
		stg.setScene(sc);
		stg.show();
	}

	public void setTable(ObservableList<BoughtProducts> list) {
		table = new TableView<BoughtProducts>();
		TableColumn<BoughtProducts, String> nameOfCD = new TableColumn<>("Product name");
		nameOfCD.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<BoughtProducts, Integer> quantity = new TableColumn<>("quantity");
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		TableColumn<BoughtProducts, Double> buyingPr = new TableColumn<>("buying price");
		buyingPr.setCellValueFactory(new PropertyValueFactory<>("boughtPrice"));
		TableColumn<BoughtProducts, Double> total = new TableColumn<>("total");
		total.setCellValueFactory(new PropertyValueFactory<>("total"));
		TableColumn<BoughtProducts, Double> date = new TableColumn<>("date");
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		if (list == null) {
			table.setPlaceholder(new Label("Nothing to display"));
		} else
			table.setItems(list);
		table.getColumns().addAll(nameOfCD, quantity, buyingPr, total, date);
	}

	public void setTable2(ObservableList<User> list2) {
		table2 = new TableView<User>();
		TableColumn<User, String> userName = new TableColumn<>("name");
		userName.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<User, String> usersurName = new TableColumn<>("surname");
		usersurName.setCellValueFactory(new PropertyValueFactory<>("surname"));
		TableColumn<User, Integer> lvl = new TableColumn<>("access level");
		lvl.setCellValueFactory(new PropertyValueFactory<>("lvl"));
		TableColumn<User, Double> salary = new TableColumn<>("salary");
		salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		if (list == null) {
			table2.setPlaceholder(new Label("Nothing to display"));
		} else {
			table2.setItems(list2);
		}
		table2.getColumns().addAll(userName, usersurName, lvl, salary);
	}
}
