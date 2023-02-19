package View;

import Model.Bill;
import Model.Cashier;
import Model.accessCashiers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Stats2 {

	DatePicker d1;
	DatePicker d2;
	Button reset;
	Button close;

	public Stats2(Stage stage) {
		this.show(stage);
	}

	public void show(Stage stage) {

		Label lbl = new Label("From: ");
		d1 = new DatePicker();
		Label lbl2 = new Label("To: ");
		d2 = new DatePicker();
		close = new Button("Close");
		close.setStyle("-fx-background-color:#A72F48; -fx-text-fill: #ffffff;");
		reset = new Button("Reset values");
		reset.setStyle("-fx-background-color: #12243B  ; -fx-text-fill: #ffffff;");
		Button filter = new Button("Filter results");
		filter.setStyle("-fx-background-color: #12243B  ; -fx-text-fill: #ffffff;");
		HBox b1 = new HBox(20);
		b1.setPadding(new Insets(5, 5, 5, 150));
		b1.getChildren().addAll(lbl, d1,lbl2, d2, filter, reset, close);
		accessCashiers acCash = new accessCashiers();

		final CategoryAxis xAxi = new CategoryAxis();
		final NumberAxis yAxi = new NumberAxis();
		final BarChart<String, Number> bc2 = new BarChart<>(xAxi, yAxi);
		bc2.setTitle("Money Generated");
		xAxi.setLabel("Cashiers");
		yAxi.setLabel("Money generated");
		XYChart.Series series2 = new XYChart.Series<>();

		for (Cashier cashier : acCash.getCashiers()) {
			double total = 0;
			for (Bill bill : cashier.getBills()) {

				total += bill.getPrice();
			}
			series2.getData().add(new XYChart.Data<>(cashier.getName(), total));
		}
		filter.setOnAction(actionEvent -> {
			if (d1.getValue() != null && d2.getValue() != null) {
				for (Cashier cashier : acCash.getCashiers()) {
					double tot = 0;
					for (Bill bill : cashier.getBills()) {

						if (d1.getValue().isBefore(bill.getDatePrinted())
								&& d2.getValue().isAfter(bill.getDatePrinted())) {
							tot += bill.getPrice();
						}
					}
					series2.getData().add(new XYChart.Data<>(cashier.getName(), tot));
				}
			}
		});

		reset.setOnAction(actionEvent -> {
			for (Cashier cashier : acCash.getCashiers()) {
				double total = 0;
				for (Bill bill : cashier.getBills()) {

					total += bill.getPrice();
				}
				series2.getData().add(new XYChart.Data<>(cashier.getName(), total));
			}
		});

		close.setOnAction(actionEvent -> {
			stage.close();
			new CashiersPerformance();
		});
		bc2.getData().add(series2);

		VBox v = new VBox();
		//v.setAlignment(Pos.TOP_RIGHT);
		// v.setStyle("-fx-background-color: #F7AE82;");
		v.setPadding(new Insets(20, 40, 20, 40));
		v.getChildren().addAll(b1, bc2);
		Scene sc = new Scene(v, 1000, 500);
		stage.setScene(sc);
		stage.show();

	}
}
