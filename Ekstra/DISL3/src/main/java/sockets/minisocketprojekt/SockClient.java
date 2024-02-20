package sockets.minisocketprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SockClient {
    public static void main(String[] args) throws IOException {
        String sentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket= new Socket("localhost",6969);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        Thread autoPrintThread = new PrintInputThread(clientSocket, "Server");
        autoPrintThread.start();

        try (clientSocket) {
            do {
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
            } while (!sentence.equalsIgnoreCase("quit"));
            System.out.println("Closing socket");
        } finally {
            autoPrintThread.interrupt();
        }
    }}
