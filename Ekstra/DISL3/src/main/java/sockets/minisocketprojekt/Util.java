package sockets.minisocketprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Util {

    public static String sendCommandToNameServer(String command, String args)  {
        try {
            Socket nameServerSuck = new Socket("localhost", 469);

            DataOutputStream outToNameServer = new DataOutputStream(nameServerSuck.getOutputStream());
            BufferedReader inputFromNameServer = new BufferedReader(new InputStreamReader(nameServerSuck.getInputStream()));
            outToNameServer.writeBytes(command + " " +  args + '\n');
            String nameServerRespond = inputFromNameServer.readLine();

            nameServerSuck.close();
            return nameServerRespond;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
