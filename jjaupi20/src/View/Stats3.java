package View;

import Model.AccessSoldProducts;
import Model.SoldProducts;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Stats3 {

	Scene sc;
	VBox box;
	Stage stg;
	public Stats3() {
		this.show();
	}
	public void show() {
		
		final CategoryAxis xAxi = new CategoryAxis();
		final NumberAxis yAxi = new NumberAxis();
		final BarChart<String, Number> bc2 = new BarChart<>(xAxi, yAxi);
		bc2.setTitle("insights for sold cds");
		xAxi.setLabel("cd name");
		yAxi.setLabel("quantity sold");
		XYChart.Series series2 = new XYChart.Series<>();
		AccessSoldProducts acPro = new AccessSoldProducts();
		for (SoldProducts p : acPro.getCdsSold()) {
			series2.getData().add(new XYChart.Data<>(p.getSoldProductName(), p.getQuantity()));
			
		}
		
		bc2.getData().add(series2);
		box = new VBox(20);
		box.getChildren().addAll(bc2);
		sc = new Scene(box, 700, 500);
		stg = new Stage();
		stg.setScene(sc);
		stg.show();
	}
}
