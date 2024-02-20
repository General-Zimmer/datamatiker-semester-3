package sockets.miniprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Sock {

    public static void main(String[] args) {

        try {
            // Start server in a separate thread
            new Thread(() -> {
                try {
                    runServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            runClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runServer() throws IOException {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6969);
        Socket connectionSocket = welcomeSocket.accept();
        while (!welcomeSocket.isClosed()) {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);

        }
    }

    private static void runClient() throws IOException {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket= new Socket("localhost",6969);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        try (clientSocket) {
            sentence = inFromUser.readLine();
            while (!sentence.equalsIgnoreCase("quit")) {
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();
                System.out.println("FROM SERVER: " + modifiedSentence);
                sentence = inFromUser.readLine();
            }
            System.out.println("Closing connection");
        }
    }
}
