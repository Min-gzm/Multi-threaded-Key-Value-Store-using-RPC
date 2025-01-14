# Multi-threaded Key-Value Store using RPC

Prerequisites: Java Development Kit (JDK) installed and Java develop environment

If you don't have JDK, you could follow this guide: https://www.geeksforgeeks.org/download-and-install-java-development-kit-jdk-on-windows-mac-and-linux/.

After installation, you could check the java version in the terminal by:

```bash
PS C:\Users\MIN\Desktop\6650\project2> java --version
java 18 2022-03-22
Java(TM) SE Runtime Environment (build 18+36-2087)
Java HotSpot(TM) 64-Bit Server VM (build 18+36-2087, mixed mode, sharing)
```

## Compile:

Compiled files are ready. You could **skip** this Compile step and try the next Run step.
If you encounter errors during the run process, it might be due to system or java development environment issues. In that case, please recompile according to the steps provided below.

In the root directory:

```bash
javac -d out src/server/*.java
javac -d out src/client/*.java
```

## Run

In the root directory:

1. Start server first

Start the rpc server

```bash
java -cp out src.server.RMIServer <host_ip_address>

// example
java -cp out src.server.RMIServer 12345
```

2. Then start client in a new terminal

Start the pre-prepolate client (make sure that you have start the server and input valid ip address, port number)

```bash
java -cp out src.client.PrePopulateClient <host_ip_address> <port_number>

// example
java -cp out src.client.PrePopulateClient localhost 12345
```

Start the general client (make sure that you have start the server and input valid ip address, port number)

```bash
java -cp out src.client.Client <host_ip_address> <port_number>

// example
java -cp out src.client.Client localhost 12345
```

## Output

RMI Server

```bash
C:\Users\MIN\Desktop\6650\project2>java -cp out src.server.RMIServer 12345
Server is running on port 12345
```

Pre-propulate Client

```bash
C:\Users\MIN\Desktop\6650\project2>java -cp out src.client.PrePopulateClient localhost 12345
[2025-02-27 18:16:04] Connected to the server at localhost:12345
[2025-02-27 18:16:04] Usage: <operation> <key> <value>
[2025-02-27 18:16:04]   put <key> <value>: put a key-value pair
[2025-02-27 18:16:04]   get <key>: get the value of the key
[2025-02-27 18:16:04]   delete <key>: delete the key-value pair
[2025-02-27 18:16:04]   exit: exit the client
[2025-02-27 18:16:04] Start pre-populating...
[2025-02-27 18:16:04] PUT key key1 with value value1 successfully
[2025-02-27 18:16:04] PUT key key2 with value value2 successfully
[2025-02-27 18:16:04] PUT key key3 with value value3 successfully
[2025-02-27 18:16:04] PUT key key4 with value value4 successfully
[2025-02-27 18:16:04] PUT key key5 with value value5 successfully
[2025-02-27 18:16:04] GET key key1 with value value1 successfully
[2025-02-27 18:16:04] GET key key2 with value value2 successfully
[2025-02-27 18:16:04] Error handling GET key13: Key key13 not found
[2025-02-27 18:16:04] GET key key4 with value value4 successfully
[2025-02-27 18:16:04] Error handling GET key15: Key key15 not found
[2025-02-27 18:16:04] DELETE key key1 successfully
[2025-02-27 18:16:04] Error handling DELETE key12: Key key12 not found
[2025-02-27 18:16:04] DELETE key key3 successfully
[2025-02-27 18:16:04] Error handling DELETE key14: Key key14 not found
[2025-02-27 18:16:04] DELETE key key5 successfully
[2025-02-27 18:16:04] Completed pre-populating
```

General Client

```bash
C:\Users\MIN\Desktop\6650\project2>java -cp out src.client.Client localhost 12345
[2025-02-27 18:16:09] Connected to the server at localhost:12345
[2025-02-27 18:16:09] Usage: <operation> <key> <value>
[2025-02-27 18:16:09]   put <key> <value>: put a key-value pair
[2025-02-27 18:16:09]   get <key>: get the value of the key
[2025-02-27 18:16:09]   delete <key>: delete the key-value pair
[2025-02-27 18:16:09]   exit: exit the client
[2025-02-27 18:16:09] Enter text (text length should be in [1, 80]), enter 'exit' to exit:
put min 0905
[2025-02-27 18:17:12] PUT key min with value 0905 successfully
[2025-02-27 18:17:12] Enter text (text length should be in [1, 80]), enter 'exit' to exit:
get min
[2025-02-27 18:17:14] GET key min with value 0905 successfully
[2025-02-27 18:17:14] Enter text (text length should be in [1, 80]), enter 'exit' to exit:
get Min
[2025-02-27 18:17:16] Error handling GET Min: Key Min not found
[2025-02-27 18:17:16] Enter text (text length should be in [1, 80]), enter 'exit' to exit:
delete min
[2025-02-27 18:17:23] DELETE key min successfully
[2025-02-27 18:17:23] Enter text (text length should be in [1, 80]), enter 'exit' to exit:
get min
[2025-02-27 18:17:24] Error handling GET min: Key min not found
[2025-02-27 18:17:24] Enter text (text length should be in [1, 80]), enter 'exit' to exit:
exit
```

## Error Handling

Try to connect to a inactive server

```bash
C:\Users\MIN\Desktop\6650\project2>java -cp out src.client.Client localhost 12345
[2025-02-27 18:18:31] Error looking up the service: Connection refused to host: localhost; nested exception is:
        java.net.ConnectException: Connection refused: connect
[2025-02-27 18:18:31] Failed to connect to the server, please check the server or restart the client
```

The server shutdown in the connection

```bash
C:\Users\MIN\Desktop\6650\project2>java -cp out src.client.Client localhost 12345
[2025-02-27 18:20:07] Connected to the server at localhost:12345
[2025-02-27 18:20:07] Usage: <operation> <key> <value>
[2025-02-27 18:20:07]   put <key> <value>: put a key-value pair
[2025-02-27 18:20:07]   get <key>: get the value of the key
[2025-02-27 18:20:07]   delete <key>: delete the key-value pair
[2025-02-27 18:20:07]   exit: exit the client
[2025-02-27 18:20:07] Enter text (text length should be in [1, 80]), enter 'exit' to exit:
put min 2
[2025-02-27 18:20:11] PUT key min with value 2 successfully
[2025-02-27 18:20:11] Enter text (text length should be in [1, 80]), enter 'exit' to exit:
get min
[2025-02-27 18:20:14] GET key min with value 2 successfully
[2025-02-27 18:20:14] Enter text (text length should be in [1, 80]), enter 'exit' to exit:
get min
[2025-02-27 18:20:22] Connection lost, please check the server or restart the client
```
