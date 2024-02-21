package sockets.minisocketprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SockClient {
    public static void main(String[] args) throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Gib your name: ");
        String name = inFromUser.readLine();
        Socket clientSocket = new Socket("localhost",6969);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Connected to server");
        outToServer.writeBytes("Snakke " + name + "\n");

        String response = inputFromServer.readLine();
        if (response.equals("Nej")) {
            clientSocket.close();
        } else if (response.equals("Ja")) {
            System.out.println("Connection successful");
            System.out.println("Type to send message");
        } else {
            System.out.println("Invalid response. Closing connection");
            clientSocket.close();
        }

        Thread autoPrintThread = new PrintInputThread(clientSocket, "Server", inputFromServer);
        autoPrintThread.start();

        try (clientSocket) {
            String sentence;
            do {
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
            } while (!sentence.equalsIgnoreCase("quit"));
            System.out.println("Closing socket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
