package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class preStats {
	
	Button stats1;
	Button stats2;
	VBox layout;
	ImageView imv;
	Scene sc;
	Button close;
	Stage stg;

	public preStats() {
		try {
			this.show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Img not found");
		}
	}
	public void show() throws FileNotFoundException {
		Image img = new Image(new FileInputStream("sources/Images/stats.png"));
		imv = new ImageView(img);
		imv.setFitHeight(100);
		imv.setFitWidth(100);
		stats1 = new Button("Employees salaries");
		stats1.setPrefWidth(130);
		stats1.setStyle("-fx-background-color: #e06e3d; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		stats1.setOnAction(actionEvent ->{
			new stats();
		});
		stats2 = new Button("Most sold products");
		stats2.setPrefWidth(130);
		stats2.setStyle("-fx-background-color: #e06e3d; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		stats2.setOnAction(actionEvent ->{
			new Stats3();
		});
		close = new Button("close");
		close.setPrefWidth(130);
		close.setStyle("-fx-background-color:#A72F48; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		close.setOnAction(actionEvent ->{
			this.stg.close();
		});
		layout = new VBox(20);
		layout.setPadding(new Insets(20, 70, 20, 70));
		layout.getChildren().addAll(imv, stats1, stats2,close);
		layout.setStyle("-fx-background-color: #172e4d;");
		sc = new Scene(layout, 270, 300);
		stg = new Stage();
		stg.setResizable(false);
		stg.setTitle("Statistics");
		stg.setScene(sc);
		stg.show();
	}
}
