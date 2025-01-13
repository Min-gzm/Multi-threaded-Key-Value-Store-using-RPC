package src.client;

import src.utils.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Client {
  public static void main(String[] args) {
    if (args.length != 2) {
      Logger.log("Usage: java Client <server_host_ip> <server_port_number>");
      Logger.log("Example: java Client localhost 12345");
      return;
    }

    String host = args[0];
    int port = Integer.parseInt(args[1]);

    RMIClient rmiClient = new RMIClient();
    rmiClient.lookup(host, port);
    if (!rmiClient.isConnected()) {
      Logger.log("Failed to connect to the server, please check the server or restart the client");
      return;
    }

    Logger.log("Connected to the server at " + host + ":" + port);
    rmiClient.operationUsage();

    while (true) {
      BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
      // read one line message from the user
      Logger.log("Enter text (text length should be in [1, 80]), enter 'exit' to exit: ");

      String message = "";
      try {
        message = userInput.readLine();
      } catch (IOException e) {
        Logger.log("Error reading input: " + e.getMessage());
        continue;
      }

      // string validation
      if (message.isEmpty()) {
        Logger.log("Text cannot be empty");
        continue;
      }

      if (message.length() > 80) {
        Logger.log("Text length cannot be larger than 80");
        continue;
      }

      if (message.equals("exit")) {
        break;
      }

      try {
        rmiClient.handleRequest(message);
      } catch (Exception e) {
        Logger.log("Please check the health of the server or restart the client again");
        break;
      }
    }
  }
}
