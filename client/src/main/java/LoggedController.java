import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedController implements Initializable {

    private ConnectServer conn;

    @FXML
    private TextField textMessage;

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listenFromServer();
    }

    private void listenFromServer() {
        new Thread(() -> {
            String msg;
            while (true) {
                try {
                    msg = (String) conn.getInput().readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                appendMsg(msg);
            }
        }).start();

    }

    @FXML
    public void handleSend() {
        conn.sendMessage(new ChatMessage(ChatMessage.MESSAGE, textMessage.getText()));
        textMessage.clear();
    }

    @FXML
    public void handleWhoIs() {
        conn.sendMessage(new ChatMessage(ChatMessage.WHOISIN, ""));
    }

    public void setConnection(ConnectServer conn) {
        this.conn = conn;
    }

    public void appendMsg(String string) {
        Platform.runLater(() -> textArea.appendText(string));
    }

}
