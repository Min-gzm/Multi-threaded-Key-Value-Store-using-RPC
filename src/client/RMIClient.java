package src.client;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import src.utils.Logger;
import java.rmi.RemoteException;
import src.service.KeyValueService;

public class RMIClient {
  private KeyValueService stub;

  public RMIClient() {
    this.stub = null;
  }

  public boolean isConnected() {
    return stub != null;
  }

  public void lookup(String host, int port) {
    try {
      // get the registry and lookup the service
      Registry registry = LocateRegistry.getRegistry(host, port);
      KeyValueService stub = (KeyValueService) registry.lookup("KeyValueService");

      this.stub = stub;
    } catch (Exception e) {
      Logger.log("Error looking up the service: " + e.getMessage());
      return;
    }
  }

  public void handleRequest(String request) {
    try {
      if (stub == null) {
        Logger.log("Connection lost, please check the server or restart the client");
        return;
      }

      String[] commandsAndParams = request.split(" ");
      String command = commandsAndParams[0];

      // handle different commands
      switch (command.toUpperCase()) {
        case "GET": {
          if (commandsAndParams.length != 2) {
            Logger.log("Invalid number of parameters, get command should have 1 parameter");
            return;
          }

          String key = commandsAndParams[1];
          String value = stub.get(key);
          Logger.log("GET key " + key + " with value " + value + " successfully");
          return;
        }
        case "PUT": {
          if (commandsAndParams.length != 3) {
            Logger.log("Invalid number of parameters, put command should have 2 parameters");
            return;
          }

          String key = commandsAndParams[1];
          String value = commandsAndParams[2];
          stub.put(key, value);
          Logger.log("PUT key " + key + " with value " + value + " successfully");
          return;
        }

        case "DELETE": {
          if (commandsAndParams.length != 2) {
            Logger.log("Invalid number of parameters, delete command should have 1 parameter");
            return;
          }

          String key = commandsAndParams[1];
          stub.delete(key);
          Logger.log("DELETE key " + key + " successfully");
          return;
        }
        default: {
          Logger.log("Invalid command");
          return;
        }
      }
    } catch (RemoteException e) {
      String[] commandsAndParams = request.split(" ");
      String command = commandsAndParams[0].toUpperCase();
      String key = commandsAndParams[1];

      String fullMessage = e.getMessage();
      String cleanMessage = fullMessage;
      if (fullMessage.contains("java.rmi.RemoteException:")) {
        cleanMessage = fullMessage.split("java.rmi.RemoteException:")[1].trim();
      }

      // Check if it's a connection issue
      if (cleanMessage.contains("Connection refused")) {
        Logger.log("Connection lost, please check the server or restart the client");
        System.exit(1);
      }

      Logger.log("Error handling " + command + " " + key + ": " + cleanMessage);
    } catch (Exception e) {
      String[] commandsAndParams = request.split(" ");
      String command = commandsAndParams[0].toUpperCase();
      String key = commandsAndParams[1];
      Logger.log("Error handling " + command + " " + key + ": " + e.getMessage());
    }
  }

  public void lookupUsage() {
    Logger.log("Usage: java RMIClient <server_host_ip> <server_port_number>");
    Logger.log("Example: java RMIClient localhost 12345");
  }

  public void operationUsage() {
    Logger.log("Usage: <operation> <key> <value>");
    Logger.log("  put <key> <value>: put a key-value pair");
    Logger.log("  get <key>: get the value of the key");
    Logger.log("  delete <key>: delete the key-value pair");
    Logger.log("  exit: exit the client");
  }
}
