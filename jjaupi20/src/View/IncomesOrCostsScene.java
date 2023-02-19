package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IncomesOrCostsScene {
	
	Button viewCosts;
	Button viewIncomes;
	ImageView imv;
	Button close;
	Stage stage;
	public IncomesOrCostsScene() {
		try {
			this.show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void show() throws FileNotFoundException {
		stage = new Stage();
		Image img = new Image(new FileInputStream("sources/Images/revenue.gif"));
		imv = new ImageView(img);
		imv.setFitHeight(100);
		imv.setFitWidth(100);
		viewCosts = new Button("view Costs");
		viewCosts.setPrefWidth(130);
		viewCosts.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		
		viewCosts.setOnAction(actionEvent ->{
			new CostsScene(stage);
		});
		viewIncomes = new Button("view Incomes");
		viewIncomes.setPrefWidth(130);
		viewIncomes.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		viewIncomes.setOnAction(actionEvent ->{
			this.stage.close();
			new IncomesScene();
		});
		Button revenue = new Button("Revenue");
		revenue.setPrefWidth(130);
		revenue.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		revenue.setOnAction(actionEvent ->{
			this.stage.close();
			new Revenue();
		});
		close = new Button("Close");
		close.setPrefWidth(130);
		close.setStyle("-fx-background-color: #A72F48; -fx-text-fill: #ffffff;-fx-font-weight: bold;");
		close.setOnAction(actionEvent ->{
			this.stage.close();
		});
		VBox layout = new VBox(20);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #172e4d;");
		layout.getChildren().addAll(imv, viewCosts, viewIncomes,revenue, close);
		stage.setScene(new Scene(layout, 300, 300));
		stage.setTitle("Incomes and costs");
		stage.show();
		stage.setResizable(false);
	}

}
