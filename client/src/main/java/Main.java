package client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	private Stage stage;
	private LoginController login;
	private LoggedController logged;

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		gotoLogin();
		stage.show();
		stage.setTitle("chat");
		stage.setOnCloseRequest((WindowEvent event) -> {
			System.exit(0);
		});

	}

	private void gotoLogin() {

		String fxml = "Login.fxml";
		String css = "Login.css";

		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		Parent page = null;

		try {
			page = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		login = (LoginController) loader.getController();
		login.setMain(this);

		Scene scene = new Scene(page);
		scene.getStylesheets().add(getClass().getResource(css).toExternalForm());

		stage.setScene(scene);

	}

	public void gotoLogged(ConnectServer conn) {
		String fxml = "Logged.fxml";
		String css = "Login.css";

		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		Parent page = null;

		try {
			page = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logged = (LoggedController) loader.getController();
		logged.setConnection(conn);

		Scene scene = new Scene(page);
		scene.getStylesheets().add(getClass().getResource(css).toExternalForm());

		stage.setScene(scene);
	}

	public Stage getStage() {
		return this.stage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
