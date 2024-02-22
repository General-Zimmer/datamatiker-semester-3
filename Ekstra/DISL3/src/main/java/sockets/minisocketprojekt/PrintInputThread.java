package sockets.minisocketprojekt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class PrintInputThread extends Thread {
    private final Socket sock;
    private final String inputName;
    private final BufferedReader input;
    public PrintInputThread(Socket sock, String inputName, BufferedReader input) {
        this.sock = sock;
        this.input = input;
        this.inputName = inputName;
    }

    public synchronized void run() {
        try {
            String inputString;
            do {
                inputString = input.readLine();
                if (inputString == null) {
                    sock.close();
                } else {
                    System.out.println("Message from " + inputName + ": " + inputString);
                }
            } while (!sock.isClosed());
        } catch (IOException e) {
            if (e.getMessage().equals("Connection reset")) {
                closeSocket();
            }
            System.out.println(e.getMessage());
        }
    }

    private void closeSocket() {
        try {
            sock.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
