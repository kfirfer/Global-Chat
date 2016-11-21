package client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

	private Main main;
	private ConnectServer conn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameField.setText("");
		ipField.setText("localhost");
		portField.setText("1500");
	}

	@FXML
	private TextField nameField;

	@FXML
	private TextField ipField;

	@FXML
	private TextField portField;

	@FXML
	public void handleConnect() {
		conn = new ConnectServer(ipField.getText(), Integer.parseInt(portField.getText()));
		if (conn.isConnected()) {
			conn.sendMessage(nameField.getText());
			main.gotoLogged(conn);
		}
	}

	@FXML
	public void handleCancel() {
		main.getStage().close();
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
