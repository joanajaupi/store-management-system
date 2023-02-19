
package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import Model.AccessUsers;
import Model.Cashier;
import Model.User;
import Model.accessCashiers;
import Model.checkInputs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddUserScene {
	Stage UserStage;
	TextField name;
	TextField surname;
	DatePicker birthday;
	TextField email;
	ComboBox chooseRole;
	TextField username;
	TextField password;
	TextField salary;
	TextField ID;

	TextField confirmPassword;
	TextField phoneNumber;
	Button Add;
	Button cancel;
	VBox layout;
	HBox ButtonRow;
	VBox firstRow;
	ImageView im;

	public void showUserStage() throws FileNotFoundException {
		UserStage = new Stage();
		im = new ImageView(new Image(new FileInputStream("sources/Images/cashierLogo.png")));
		im.setFitHeight(50);
		im.setFitWidth(50);
		firstRow = new VBox();

		Label label = new Label("Enter users Credentials");
		label.setStyle("-fx-font-weight: bold; -fx-font-size: 12; -fx-text-fill: #ffffff;");
		firstRow.getChildren().addAll(im, label);
		firstRow.setAlignment(Pos.CENTER);

		name = new TextField();
		name.setStyle("-fx-background-color:#4d75ab; -fx-text-fill: #ffffff;");
		name.setPromptText("Name");
		surname = new TextField();
		surname.setPromptText("Surname");
		surname.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");

		birthday = new DatePicker();
		birthday.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		username = new TextField();
		username.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		username.setPromptText("username");
		email = new TextField();
		email.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		email.setPromptText("Email : example@example.com");
		password = new TextField();
		password.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		password.setPromptText("Password");
		confirmPassword = new TextField();
		confirmPassword.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		confirmPassword.setPromptText("Confirm Password");
		salary = new TextField();
		salary.setStyle("-fx-background-color:#4d75ab; -fx-text-fill: #ffffff;");
		salary.setPromptText("Salary");
		ID = new TextField();
		ID.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		ID.setPromptText("ID : A123456789B");
		phoneNumber = new TextField();
		phoneNumber.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		phoneNumber.setPromptText("ex: 0691234567");
		ButtonRow = new HBox(20);
		addButton();
		cancel = new Button("Cancel");
		cancel.setStyle("-fx-background-color: #C84B4B; -fx-text-fill: #ffffff");
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				UserStage.close();
				new UsersScene().showUsersScene();

			}

		});
		chooseRole = new ComboBox();
		chooseRole.getItems().add("admin");
		chooseRole.getItems().add("manager");
		chooseRole.getItems().add("cashier");
		chooseRole.setPrefWidth(300);
		chooseRole.setPromptText("Choose users role");
		ButtonRow.getChildren().addAll(cancel, Add);

		layout = new VBox(20);
		layout.setStyle("-fx-background-color: #172e4d;");
		layout.setPadding(new Insets(20, 50, 20, 50));
		layout.getChildren().addAll(firstRow, name, surname, birthday, ID, phoneNumber, chooseRole, email, username,
				password, confirmPassword, salary, ButtonRow);
		Scene scene = new Scene(layout, 400, 650);
		UserStage.setScene(scene);
		UserStage.show();
		UserStage.setResizable(false);

	}

	public void addButton() {
		Add = new Button("Add user");
		Add.setStyle("-fx-background-color: #AF6AA9; -fx-text-fill: #ffffff");
		Add.setOnAction(e -> {
			try {
			int type = 3;
			LocalDate birth = birthday.getValue();
			String name1 = name.getText();
			String surname1 = surname.getText();
			String email1 = email.getText();
			String username1 = username.getText();
			String password1 = password.getText();
			String password2 = confirmPassword.getText();
			double salary1 = Double.parseDouble(salary.getText());
			String id2 = ID.getText();
			String value1 = (String) chooseRole.getValue();
			if (value1 == "admin")
				type = 1;
			else if (value1 == "manager")
				type = 2;
			else if (value1 == "cashier")
				type = 3;
			String phonNo = phoneNumber.getText();
			if (checkInputs.checkEmail(email1) && checkInputs.checkPersonID(id2) && checkInputs.checkPhoneNumbers(phonNo)) {
				if (password1.matches(password2)) {
					AccessUsers userFile = new AccessUsers();
					User newUs = new User(name1, surname1, birth, phonNo, id2, email1, type, username1, password1, salary1);
					userFile.addUser(newUs);
					//if user is a cashier add it to cashier list
					if(newUs.getLvl() == 3) {
						accessCashiers acCash = new accessCashiers();
						acCash.addCashier(new Cashier(name1, surname1, birth, phonNo, id2, email1, type, username1, password1, salary1));
						System.out.println("a new cashier added");
					}
					Alert al = new Alert(AlertType.INFORMATION);
					al.setContentText("Succesfully added");
					UserStage.close();
					new UsersScene().showUsersScene();
				} else {
					System.out.println("password dont match");
					Alert alert1 = new Alert(AlertType.WARNING);
					alert1.setContentText("Confirmation password dont match");
				}
			} else {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Invalid inputs");
				username.setStyle("-fx-background-color: red");
				a.show();
			}
			}catch(Exception ex) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Invalid inputs");
				username.setStyle("-fx-background-color: red");
				a.show();
				System.out.println("Exception");
			}
		});
	}

}
