package src.client;

import src.utils.Logger;

public class PrePopulateClient {
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

    Logger.log("Start pre-populating...");

    rmiClient.handleRequest("put key1 value1");
    rmiClient.handleRequest("put key2 value2");
    rmiClient.handleRequest("put key3 value3");
    rmiClient.handleRequest("put key4 value4");
    rmiClient.handleRequest("put key5 value5");

    rmiClient.handleRequest("get key1");
    rmiClient.handleRequest("get key2");
    rmiClient.handleRequest("get key13");
    rmiClient.handleRequest("get key4");
    rmiClient.handleRequest("get key15");

    rmiClient.handleRequest("delete key1");
    rmiClient.handleRequest("delete key12");
    rmiClient.handleRequest("delete key3");
    rmiClient.handleRequest("delete key14");
    rmiClient.handleRequest("delete key5");

    Logger.log("Completed pre-populating");
  }
}
