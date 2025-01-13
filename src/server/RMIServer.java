package src.server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
  public static void main(String[] args) {
    if (args.length != 1) {
      usage();
      return;
    }

    int port;
    try {
      port = Integer.parseInt(args[0]);
    } catch (Exception e) {
      usage();
      return;
    }

    if (port < 1024 || port > 65535) {
      usage();
      return;
    }

    try {
      // create a registry
      Registry registry = LocateRegistry.createRegistry(port);
      // bine the service
      KeyValueServiceImpl server = new KeyValueServiceImpl();
      registry.rebind("KeyValueService", server);
      System.out.println("Server is running on port " + port);
    } catch (Exception e) {
      System.out.println("Server error: " + e.getMessage());
    }
  }

  public static void usage() {
    System.out.println("Usage: java RMIServer <port_number>");
    System.out.println("Example: java RMIServer 12345");
  }
}
