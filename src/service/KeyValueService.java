package src.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KeyValueService extends Remote {
  String get(String key) throws RemoteException;

  void put(String key, String value) throws RemoteException;

  void delete(String key) throws RemoteException;
}
