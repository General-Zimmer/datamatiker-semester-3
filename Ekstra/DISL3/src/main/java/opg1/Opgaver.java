package opg1;

import java.net.*;
import java.io.*;
import java.lang.*;

public class Opgaver {
    public static void main(String[] args) {
        try {

            opg3();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void opg1() throws Exception {
        URL url = new URL("http://www.google.dk");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Line: " + line);
        }
    }
    public static void opg2() throws Exception {
        Socket socket = new Socket("www.google.dk", 80);
        PrintStream out = new PrintStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println("GET / HTTP/1.1");
        out.println("Host: www.google.dk");
        out.println();
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println("Line: " + line);
        }
    }

    public static void opg3() throws IOException {
        URL url = new URL("https://m.valutakurser.dk");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Line: " + line);
        }
    }
}

