package sockets.minisocketprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SockServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6969);
        Socket connectionSocket = serverSocket.accept();
        System.out.println("Connection established");
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));

        String response = inputFromClient.readLine();

        System.out.println("Client says: " + response);
        String clientName = "client";
        if (response.startsWith("Snakke ") && response.length() > 7) {
            clientName = response.substring(7);
            System.out.println(clientName + " wants to talk. Ja/Nej?");
            String noYes = inFromServer.readLine();

            if (noYes.equals("Nej")) {
                outToClient.writeBytes("Nej" + '\n');
            } else if (noYes.equals("Ja")) {
                outToClient.writeBytes("Ja" + '\n');
                System.out.println("Type to send message");
            } else {
                System.out.println("Invalid response. Saying Ja");
                outToClient.writeBytes("Ja" + '\n');
            }
        } else {
            outToClient.writeBytes("Invalid. Please follow the protocol." + '\n');
            System.out.println("Recipient did not follow protocol. Closing connection");
            connectionSocket.close();
        }


        PrintInputThread autoPrintThread = new PrintInputThread(connectionSocket, clientName, inputFromClient, true);
        autoPrintThread.start();



        while (!connectionSocket.isClosed()) {
            String sentence = "";
            if (inFromServer.ready())
                sentence = inFromServer.readLine();
            if (!sentence.isBlank())
                outToClient.writeBytes(sentence + '\n');
        }
    }
}
