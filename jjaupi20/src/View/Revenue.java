package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Model.AccessBoughtCDs;
import Model.AccessCD;
import Model.AccessUsers;
import Model.Bill;
import Model.BoughtProducts;
import Model.CD;
import Model.Cashier;
import Model.User;
import Model.accessCashiers;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Revenue {
	
	Stage stage;
	ImageView imv;
	public Revenue() {
		try {
			this.show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("F not found");
		}
	}

	public void show() throws FileNotFoundException {
		accessCashiers acCash = new accessCashiers();
		
		Image img = new Image(new FileInputStream("sources/Images/revenue.gif"));
		imv = new ImageView(img);
		imv.setFitHeight(100);
		imv.setFitWidth(100);
		double tot = 0;
		
		for (Cashier c : acCash.getCashiers()) {
			for (Bill b : c.getBills()) {
				tot += b.getPrice();
			}
		}
		Label incomesLabel = new Label("Incomes : " + String.valueOf(tot));
		incomesLabel.setStyle("-fx-text-fill: #ffffff;");
		AccessBoughtCDs acbc = new AccessBoughtCDs();
		double totalCostOfCds = 0;
		for (BoughtProducts c : acbc.get()) {
			totalCostOfCds += c.getTotal();
		}
		Label totalForcd = new Label("Total costs for cds: " + totalCostOfCds);
		totalForcd.setStyle("-fx-text-fill: #ffffff");
		double totalCostForSalaries = 0;
		AccessUsers acUser = new AccessUsers();
		for (User u : acUser.getUsers()) {
			totalCostForSalaries += u.getSalary();
		}
		Label totalForSal = new Label("Total costs for employee salaries: " +totalCostForSalaries);
		totalForSal.setStyle("-fx-text-fill: #ffffff");
		double total = totalCostOfCds + totalCostForSalaries;
		Label totalCost = new Label("Total Costs: " + String.valueOf(total));
		totalCost.setStyle("-fx-text-fill: #ffffff;");
		
		double rev = tot - total;
		Label revenue = new Label("Total revenue:  " + String.valueOf(rev));
		revenue.setStyle("-fx-text-fill: #ffffff;");
		Button close = new Button("close");
		close.setStyle("-fx-background-color: #A72F48; -fx-text-fill: #ffffff;-fx-font-weight: bold;");
		close.setOnAction(actionEvent ->{
			this.stage.close();
			new IncomesOrCostsScene();
		});
	
		VBox v = new VBox(10);
		v.setStyle("-fx-background-color: #172e4d;");
		v.setAlignment(Pos.CENTER);
		v.getChildren().addAll(imv,incomesLabel, totalForSal,totalForcd, totalCost, revenue,close);
		Scene sc = new Scene(v, 400, 400);
		stage= new Stage();
		stage.setScene(sc);
		stage.show();
		stage.setOnCloseRequest(actionEvent ->{
			new IncomesOrCostsScene();
		});
		
	}

}
