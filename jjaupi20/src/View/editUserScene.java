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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class editUserScene {

	Button edit;
	TextField name;
	TextField surname;
	DatePicker birthday;
	TextField email;
	TextField username;
	TextField password;
	TextField salary;
	TextField ID;
	TextField confirmPassword;
	TextField phoneNumber;
	Button cancel;
	VBox layout;
	HBox ButtonRow;
	VBox firstRow;
	ImageView im;
	Stage stage;

	public editUserScene(User u) {
		try {
			this.showUserScene(u);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void setTheText(User userToEdit) {
		name.setText(userToEdit.getName());
		surname.setText(userToEdit.getSurname());
		birthday.setValue(userToEdit.getBirthday());
		email.setText(userToEdit.getEmail());
		username.setText(userToEdit.getUsername());
		salary.setText(String.valueOf(userToEdit.getSalary()));
		ID.setText(userToEdit.getID());
		phoneNumber.setText(userToEdit.getPhoneNumber());
		password.setText(userToEdit.getPassword());
		confirmPassword.setText(userToEdit.getPassword());

	}

	public void showUserScene(User userToEdit) throws FileNotFoundException {
		stage = new Stage();
		im = new ImageView(new Image(new FileInputStream("sources/Images/newUser.png")));
		im.setFitHeight(80);
		im.setFitWidth(80);
		firstRow = new VBox();

		Label label = new Label("Enter users Credentials");
		label.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		firstRow.getChildren().addAll(im, label);
		firstRow.setAlignment(Pos.CENTER);

		name = new TextField();
		name.setPromptText("Name");
		surname = new TextField();
		surname.setPromptText("Surname");
		birthday = new DatePicker();
		username = new TextField();
		username.setPromptText("username");
		email = new TextField();
		email.setPromptText("Email : example@example.com");
		password = new TextField();
		password.setPromptText("Password");
		confirmPassword = new TextField();
		confirmPassword.setPromptText("Confirm Password");
		salary = new TextField();
		salary.setPromptText("Salary");
		ID = new TextField();
		ID.setPromptText("ID : A123456789B");
		phoneNumber = new TextField();
		phoneNumber.setPromptText("ex: 0691234567");
		ButtonRow = new HBox(180);
		cancel = new Button("Cancel");
		cancel.setStyle("-fx-background-color: #C84B4B; -fx-text-fill: #ffffff");
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				stage.close();
				new UsersScene().showUsersScene();

			}

		});
		edit = new Button("Edit");
		edit.setOnAction(actionEvent -> {
			edit(userToEdit);

		});
		ButtonRow.getChildren().addAll(cancel, edit);
		setTheText(userToEdit);
		layout = new VBox(20);
		layout.setStyle("-fx-background-color: #ffffff");
		layout.setPadding(new Insets(20, 50, 20, 50));
		layout.getChildren().addAll(firstRow, name, surname, birthday, ID, phoneNumber, email, username, password,
				confirmPassword, salary, ButtonRow);
		Scene scene = new Scene(layout, 400, 700);
		stage.setScene(scene);
		stage.show();

	}

	public void edit(User userToEdit) {

		//if user is a cashier
		int cashPos = 0;
		accessCashiers acCashier = new accessCashiers();
		if (userToEdit.getLvl() == 3) {
			
			for (Cashier c : acCashier.getCashiers()) {
				if (userToEdit.getName().equals(c.getName()) || userToEdit.getSurname().equals(c.getSurname())) {
					System.out.println("true");
					cashPos = acCashier.getPosition(c);
				}
			}
		}
		LocalDate birth = birthday.getValue();
		String name1 = name.getText();
		String surname1 = surname.getText();
		String email1 = email.getText();
		String username1 = username.getText();
		String password1 = password.getText();
		String password2 = confirmPassword.getText();
		double salary1 = Double.parseDouble(salary.getText());
		String id2 = ID.getText();
		String phonNo = phoneNumber.getText();
		//check the edited info if they are according to specified requirements 
		if (checkInputs.checkEmail(email1) && checkInputs.checkPersonID(id2) && checkInputs.checkPhoneNumbers(phonNo)) {
			//password = confirmation password
			if (password1.matches(password2)) {
				AccessUsers userFile = new AccessUsers();
				User newUs = new User(name1, surname1, birth, phonNo, id2, email1, userToEdit.getLvl(), username1,
						password1, salary1);
				userFile.editUser(userFile.getPosition(userToEdit), newUs);
				System.out.println(userFile.getPosition(newUs));

				// if user is a cashier edit it to cashier list too
				if (newUs.getLvl() == 3) {
					Cashier toEditCashier = new Cashier(name1, surname1, birth, phonNo, id2, email1, 3, phonNo, phonNo, salary1);
					acCashier.editCashier(cashPos, toEditCashier);
					System.out.println("Edited: " + cashPos + toEditCashier.toString());
					
				}
				Alert al = new Alert(AlertType.INFORMATION);
				al.setContentText("Succesfully added");
				stage.close();
				try {
					stage.getIcons().add(new Image(new FileInputStream("sources/Images/icon.png")));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new UsersScene().showUsersScene();
			} else {
				System.out.println("password dont match");
				Alert alert1 = new Alert(AlertType.WARNING);
				alert1.setContentText("Confirmation password dont match");
			}
		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Invalid inputs");
			a.show();
		}
	}

}
