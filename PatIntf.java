import java.rmi.*;

public interface PatIntf extends Remote {
    int register(String n, String a, String d, String g, int h, int w, String b) throws RemoteException;

    String search(int pid) throws RemoteException;

    Boolean expiry(int pid) throws RemoteException;

    Boolean isExpire(String date) throws RemoteException;

    String getToday(String format) throws RemoteException;

    void del(int pid) throws RemoteException;

}