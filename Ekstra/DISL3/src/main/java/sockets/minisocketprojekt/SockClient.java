package sockets.minisocketprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SockClient {
    public static void main(String[] args) throws IOException {
        // setup things
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Gib your name: ");
        String name = inFromUser.readLine();

        // Connect to server
        Socket clientSocket = new Socket("10.10.139.215",6969);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Connected to server");
        // Send snakke request to server
        outToServer.writeBytes("Snakke " + name + "\n");

        // Check if server wants to talk
        String response = inputFromServer.readLine();
        if (response.equals("Nej")) {
            clientSocket.close();
            System.out.println("Connection refused. Closing connection");
            return;
        } else if (response.equals("Ja")) {
            System.out.println("Connection successful");
            System.out.println("Type to send message");
        } else {
            System.out.println("Invalid response. Closing connection");
            clientSocket.close();
            return;
        }

        // Start thread to receive messages
        new PrintInputThread(clientSocket, "Server", inputFromServer).start();

        // Send messages to server and stop snak logic
        try (clientSocket) {
            String sentence = "";
            do {
                sentence = "";
                if (inFromUser.ready())
                    sentence = inFromUser.readLine();
                if (!sentence.isBlank())
                    outToServer.writeBytes(sentence + '\n');
            } while (!sentence.equalsIgnoreCase("quit") && !clientSocket.isClosed());
            System.out.println("Closing socket");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
