package sockets.minisocketprojekt;

import java.io.BufferedReader;
import java.io.IOException;
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

    public void run() {
        synchronized (this) {
            try {
                String inputString = "";
                do {
                    inputString = input.readLine();
                    if (inputString == null) {
                        sock.close();
                    } else if (inputString.isBlank()) {
                        wait(500);
                    } else {
                        System.out.println("Message from " + inputName + ": " + inputString);
                    }
                } while (!sock.isClosed());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("thread stopped");
                this.interrupt();
            }
        }
    }

}
