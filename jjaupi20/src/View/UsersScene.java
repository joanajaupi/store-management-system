package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import Model.AccessUsers;
import Model.Cashier;
import Model.User;
import Model.accessCashiers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UsersScene {

	Button addUser;
	Button editUser;
	Button removeUser;
	Label allUsers;
	Button closeButton;
	TableView<User> tableView;
	VBox buttonBox;
	Stage stage;
	Button exit;
	HBox lay;

	public void showUsersScene() {
		stage = new Stage();

		AccessUsers userFile = new AccessUsers();
		accessCashiers acCashier = new accessCashiers();
		ObservableList<User> userList = FXCollections.observableArrayList(userFile.getUsers());
		tableView = new TableView<User>();
		// tableView.setEditable(true);
		TableColumn<User, Integer> userId = new TableColumn("id");
		userId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
		TableColumn<User, String> name = new TableColumn("name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<User, String> surname = new TableColumn("surname");
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		TableColumn<User, String> phoneNumber = new TableColumn("phone number");
		phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		TableColumn<User, String> email = new TableColumn("email ");
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		TableColumn<User, Date> bday = new TableColumn("birthday ");
		bday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
		TableColumn<User, String> username = new TableColumn("username");
		username.setCellValueFactory(new PropertyValueFactory<>("username"));
		TableColumn<User, String> pass = new TableColumn("password");
		pass.setCellValueFactory(new PropertyValueFactory<>("password"));
		TableColumn<User, Integer> lvl = new TableColumn("Access Level");
		lvl.setCellValueFactory(new PropertyValueFactory<>("lvl"));
		TableColumn<User, Double> salary = new TableColumn("salary");
		salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		tableView.setItems(userList);
		tableView.getColumns().addAll(userId, name, surname, phoneNumber, bday, email, username, pass, lvl, salary);

		addUser = new Button("Add employee");
		addUser.setPrefSize(130, 20);
		addUser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				try {
					new AddUserScene().showUserStage();
					stage.close();

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		allUsers = new Label("All users");
		allUsers.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14");
		addUser.setStyle("-fx-background-color: #4d75ab; -fx-text-fill: #ffffff;");
		editUser = new Button("Edit credentials");
		editUser.setPrefSize(130, 20);
		editUser.setStyle("-fx-background-color: #4d75ab;-fx-text-fill: #ffffff;");
		Alert a = new Alert(AlertType.WARNING);
		a.setContentText("No item selected");
		editUser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				User userToEdit = (User) tableView.getSelectionModel().getSelectedItem();
				if (userToEdit != null) {
					new editUserScene(userToEdit);
					stage.close();
				} else {

					a.show();
				}

			}

		});
		removeUser = new Button("Remove employee");
		removeUser.setPrefSize(130, 20);
		removeUser.setStyle("-fx-background-color: #CB3232;-fx-text-fill: #ffffff;");
		removeUser.setOnAction(actionEvent -> {

			User user = (User) tableView.getSelectionModel().getSelectedItem();
			tableView.getItems().remove(user);
			userFile.remove(user);
			// to remove it from list of cashiers
			if (user != null) {
				if (user.getLvl() == 3) {
					for (Cashier c : acCashier.getCashiers()) {
						if (user.getName().matches(c.getName()) && user.getID().matches(c.getID())) {
							acCashier.removeCashier(c);
							break;
						}
					}
				}
			} else {
				a.show();
			}

		});

		exit = new Button("exit");
		exit.setPrefSize(130, 20);
		exit.setStyle("-fx-background-color: #CB3232;-fx-text-fill: #ffffff;");
		exit.setOnAction(actionEvent -> {
			new AdminScene().showScene(stage);
		});
		Image img = null;
		ImageView imv = null;
		try {
			img = new Image(new FileInputStream("sources/Images/cashierLogo.png"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (img != null) {
			imv = new ImageView(img);
			imv.setFitHeight(80);
			imv.setFitWidth(80);
		}else System.out.println("no img found");
		buttonBox = new VBox(20);
		buttonBox.setPadding(new Insets(30, 0, 0, 0));
		buttonBox.getChildren().addAll(imv, addUser, editUser, removeUser, exit);

		VBox v = new VBox(10);
		v.getChildren().addAll(allUsers, tableView);
		HBox layout = new HBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #172e4d;");
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(v, buttonBox);
		Scene scene = new Scene(layout, 1050, 400);

		stage.setScene(scene);
		try {
			stage.getIcons().add(new Image(new FileInputStream("sources/Images/icon.png")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stage.setResizable(false);
		stage.show();

	}

}
