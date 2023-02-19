package View;

import java.util.ArrayList;

import Model.AccessCD;
import Model.AccessUsers;
import Model.CD;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class stats {

	public stats() {
		this.show();
	}

	public void show() {
		AccessUsers acus = new AccessUsers();
		AccessCD acCD = new AccessCD();
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for(User u: acus.getUsers()) {
			pieChartData.add(new PieChart.Data(u.getName(), u.getSalary()));
			
		}
		/*for(CD c: acCD.getCds()) {
			pieChartData.add(new PieChart.Data(c.getCdName(), c.getTotalBuyingPrice()));
		}*/
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("costs");
				
	VBox b = new VBox(12);

	Scene sc = new Scene(chart, 400, 400);
	Stage st = new Stage();st.setScene(sc);st.show();
}

}
