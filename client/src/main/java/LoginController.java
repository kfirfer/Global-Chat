import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Main main;
    private ConnectServer connectServer;
    @FXML
    private TextField nameField;
    @FXML
    private TextField ipField;
    @FXML
    private TextField portField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nameField.setText("");
        ipField.setText("localhost");
        portField.setText("1500");
    }

    @FXML
    public void handleConnect() {
        connectServer = new ConnectServer(ipField.getText(), Integer.parseInt(portField.getText()));
        if (connectServer.isConnected()) {
            connectServer.sendMessage(nameField.getText());
            main.gotoLogged(connectServer);
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
