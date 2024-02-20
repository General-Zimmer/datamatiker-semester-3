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
        Thread autoPrintThread = new PrintInputThread(connectionSocket, "client", true);
        autoPrintThread.start();
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));
        String sentence;
        while (!serverSocket.isClosed()) {
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            sentence = inFromServer.readLine();
            outToClient.writeBytes(sentence + '\n');
        }
    }
}
