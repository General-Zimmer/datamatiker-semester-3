package sockets.minisocketprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Objects;

public class NameServer {

    private static final HashMap<String, String> nameServer = new HashMap<>();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(469);

        try (serverSocket){
            while (!serverSocket.isClosed()) {
                Socket connectionSocket = serverSocket.accept();
                new NameServerThread(connectionSocket).start();
            }
        }

    }

    private static class NameServerThread extends Thread {
        private final Socket connectionSocket;
        public NameServerThread(Socket sock) {
            this.connectionSocket = sock;
        }

        public void run() {
            try {
                connectionSocket.setSoTimeout(10000);

                System.out.println("Connection established");
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                String message = inputFromClient.readLine();
                if (message.startsWith("Register")) {
                    String nameAndIP = message.substring(9);
                    String[] nameAndIPArray = nameAndIP.split("n");
                    nameServer.put(nameAndIPArray[0], nameAndIPArray[1]);
                    outToClient.writeBytes("Registered" + '\n');
                    System.out.println("Registered " + nameAndIPArray[0] + " with IP " + nameAndIPArray[1]);

                } else if (message.startsWith("Lookup")) {
                    String name = message.substring(7);
                    String ip = nameServer.get(name);
                    outToClient.writeBytes(Objects.requireNonNullElse(ip, "Client not found") + '\n');
                    System.out.println("Looked up " + name + " and found IP " + ip);
                } else if (message.startsWith("Unregister")) {
                    String name = message.substring(11);
                    String ip = nameServer.remove(name);
                    outToClient.writeBytes(Objects.requireNonNullElse(ip, "Client not found") + '\n');
                    System.out.println("Unregistered " + name + " with IP " + ip);

                }else {
                    outToClient.writeBytes("Invalid request" + '\n');
                }
            connectionSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
