import javafx.concurrent.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectServer {

    Service<Void> bgThread;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ConnectServer(String host, int port) {
        try {
            socket = new Socket(host, port);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("mabye server off");
            return;
        }
    }

    public void sendMessage(Object msg) {
        try {
            output.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        if (socket != null && socket.isConnected())
            return true;
        return false;
    }

    public ObjectInputStream getInput() {
        return input;
    }

}
