package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Sock {
    public static void main(String[] args) {
        try {
            // Start server in a separate thread

            // Start client
            new Thread(() -> {
                runClient();
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runClient() {
        try {
            String serverAddress;
            String sentence;
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Client: Enter the server address to talk to:");
            serverAddress = inFromUser.readLine();

            System.out.println("Client: Initiating talk. Enter your name:");
            sentence = inFromUser.readLine();

            // Connect to the server
            Socket clientSocket = new Socket(serverAddress, 6969); // Using port 6969 for Talk23Y
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Send talk request with name to the server
            outToServer.writeBytes("Snakke " + sentence + '\n'); // Sending "Snakke <name>" as per Talk23Y
            String modifiedSentence = inFromServer.readLine();

            if (modifiedSentence != null && modifiedSentence.equalsIgnoreCase("Ja")) {
                System.out.println("Talk started. You can start the conversation. Type 'quit' to end.");

                // Conversation loop
                while (true) {
                    // Read user input
                    System.out.print("You: ");
                    sentence = inFromUser.readLine();

                    // Send message to server
                    outToServer.writeBytes(sentence + '\n');

                    // Receive and display server's reply
                    String reply = inFromServer.readLine();
                    System.out.println("Server: " + reply);

                    // Receive and display server's reply
                    reply = inFromServer.readLine();
                    System.out.println("Server: " + reply);

                    // Check if conversation should end
                    if (sentence.equalsIgnoreCase("quit")) {
                        break;
                    }
                }
            } else {
                System.out.println("The other party declined to talk.");
            }

            // Close the connection
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void runServer() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(6969); // Using port 6969 for Talk23Y
            System.out.println("Server: Waiting for talk request...");
            while (true) {
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                BufferedReader inFromServerUser = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                String clientSentence = inFromClient.readLine();
                String reply = inFromServerUser.readLine();

                if (clientSentence != null && clientSentence.startsWith("Snakke ")) {
                    System.out.println("Server: Received talk request from: " + clientSentence.substring(8));
                    outToClient.writeBytes("Ja\n"); // Accepting the talk request
                    while (true) {
                        clientSentence = inFromClient.readLine();
                        if (clientSentence.equalsIgnoreCase("quit")) {
                            break;
                        }
                        System.out.println("FROM CLIENT: " + clientSentence);
                        // echoing the client's messages here
                        outToClient.writeBytes(clientSentence + '\n');

                        System.out.print("You: ");
                        reply = inFromServerUser.readLine();

                        // Send message to client
                        outToClient.writeBytes(reply + '\n');
                    }
                    System.out.println("Server: Talk ended.");
                } else {
                    System.out.println("Server: Invalid talk request.");
                    outToClient.writeBytes("Nej\n"); // Declining the talk request
                }

                connectionSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}