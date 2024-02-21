package sockets.minisocketprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SockClient {
    public static void main(String[] args) throws IOException {
        // setup things
        String IP = "localhost";
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Gib your name: ");
        String ourName = inFromUser.readLine();

        System.out.print("Gib name of who you wanna talk to: ");
        String Servername = inFromUser.readLine();

        Socket nameServerSuck = new Socket("localhost", 469);
        DataOutputStream outToNameServer = new DataOutputStream(nameServerSuck.getOutputStream());
        BufferedReader inputFromNameServer = new BufferedReader(new InputStreamReader(nameServerSuck.getInputStream()));
        outToNameServer.writeBytes("Lookup " + Servername + '\n');
        String nameServerRespond = inputFromNameServer.readLine();
        if (nameServerRespond.equals("Client not found")) {
            System.out.println("Client not found");
            return;
        } else {
            System.out.println("Found client with IP " + nameServerRespond);
            IP = nameServerRespond;
        }



        // Connect to server
        Socket clientSocket = new Socket( IP,6969);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Connected to server");
        // Send snakke request to server
        outToServer.writeBytes("Snakke " + ourName + "\n");

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
