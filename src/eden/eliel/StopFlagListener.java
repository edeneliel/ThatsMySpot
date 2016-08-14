package eden.eliel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Eden on 8/14/2016.
 */
public class StopFlagListener extends Thread implements Runnable,TCPListener {
    private ServerSocket _welcomeSocket;
    private Socket _connectionSocket;
    private String _str;

    public StopFlagListener(){
        try {
            _welcomeSocket = new ServerSocket(6789);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequest(String str) {
        System.out.println("Got :"+ str);
    }

    @Override
    public void run() {
        while (true){
            try {
                _connectionSocket = _welcomeSocket.accept();
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(_connectionSocket.getInputStream()));
                _str = inFromClient.readLine();
                inFromClient.close();
                onRequest(_str);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
