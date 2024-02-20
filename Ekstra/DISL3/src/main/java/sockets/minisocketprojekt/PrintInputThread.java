package sockets.minisocketprojekt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PrintInputThread extends Thread {
    private final Socket sock;
    private final String inputName;
    private final BufferedReader input;
    private boolean canCloseSocket = false;
    public PrintInputThread(Socket sock, String inputName) throws IOException {
        this.sock = sock;
        input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        this.inputName = inputName;
    }
    public PrintInputThread(Socket sock, String inputName, boolean canCloseSocket) throws IOException {
        this(sock, inputName);
        this.canCloseSocket = canCloseSocket;
    }

    public void run() {
        synchronized (this) {
            try {
                do {
                    String inputString = input.readLine();
                    if (inputString.isBlank()) {
                        wait(500);
                    } else if (inputString.equals("quit") && canCloseSocket) {
                        sock.close();
                    } else {
                        System.out.println("Message from " + inputName + ": " + inputString);
                    }
                } while (!sock.isClosed());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                System.out.println("thread stopped");
            }
        }
    }
}
