package src.server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.HashMap;
import src.service.KeyValueService;
import java.util.concurrent.TimeUnit;

public class KeyValueServiceImpl extends UnicastRemoteObject implements KeyValueService {
  private Map<String, String> store;

  protected KeyValueServiceImpl() throws RemoteException {
    super();
    store = new HashMap<>();
  }

  @Override
  public synchronized String get(String key) throws RemoteException {
    if (!store.containsKey(key)) {
      throw new RemoteException("Key " + key + " not found");
    }
    return store.get(key);
  }

  @Override
  public synchronized void put(String key, String value) throws RemoteException {
    store.put(key, value);
  }

  @Override
  public synchronized void delete(String key) throws RemoteException {
    if (!store.containsKey(key)) {
      throw new RemoteException("Key " + key + " not found");
    }
    store.remove(key);
  }

  public void sleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
