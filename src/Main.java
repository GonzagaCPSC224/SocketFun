import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        // we are going to write code to connect to the server
        String ipAddress = "192.168.1.100";
        int portNumber = 8080;

        try {
            Socket clientSocket = new Socket(ipAddress, portNumber);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); // like Scanner
            // tied to the keyboard

            while (true) {
                // read from stdIn and send whatever the user types to the server
                if (stdIn.ready()) { // has data to read (like kb.hasNextLine())
                    String userInput = stdIn.readLine();
                    out.println(userInput);
                }

                if (in.ready()) {
                    // the server has sent something to us (client)
                    String serverResponse = in.readLine();
                    System.out.println("Server sent: " + serverResponse);
                    if (serverResponse.equals("closing")) {
                        System.out.println("Terminating program...");
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
