package com.web.tcp;

import java.io.*;
import java.net.*;

class ClientSystem {
    public static void main(String[] args) {
        String hostname = "127.0.0.1";
        int port = 1234;

        Socket clientsocket = null;
        DataOutputStream output = null;
        BufferedReader input = null;

        try {
            clientsocket = new Socket(hostname, port);
            output = new DataOutputStream(clientsocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        } catch (Exception e) {
            System.out.println("Error occured" + e);
        }

        try {
            while (true) {
                System.out.println("Enter input string ('exit' to terminate connection): ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String inputstring = br.readLine();
                output.writeBytes(inputstring + "\n");

                //int n = Integer.parseInt(inputstring);
                if (inputstring.equals("exit"))
                    break;

                String response = input.readLine();
                System.out.println("Reversed string is: " + response);

            }

            output.close();
            input.close();
            clientsocket.close();

        } catch (Exception e) {
            System.out.println("Error occured." + e);
        }
            /*finally
            {
                    output.close();
                    input.close();
                    clientsocket.close();
            }*/

    }
}