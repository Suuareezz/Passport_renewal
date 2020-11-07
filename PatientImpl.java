import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientImpl extends UnicastRemoteObject implements PatIntf {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public HashMap<Integer, Patient> record;
    public ArrayList<Integer> datez;
    public ArrayList<Integer> blacklist;
    public int count;

    public PatientImpl() throws RemoteException {
        record = new HashMap<Integer, Patient>();
        datez = new ArrayList<Integer>();
        blacklist = new ArrayList<Integer>();
        count = 0;
    }

    public int register(String n, String a, String d, String g, int h, int w, String b) throws RemoteException {
        datez.add(count, h + w);
        count++;
        Patient temp = new Patient(n, a, d, g, h, w, b);
        record.put(count, temp);

        return count;
    }

    public String search(int pid) throws RemoteException {
        if (blacklist.contains(pid)) {
            return new String("Expired ID! Create new one Using Register");
        } else if (!record.containsKey(pid))
            return new String("Invalid Patient ID!Record Not Found");
        return record.get(pid).toString();
    }

    public Boolean expiry(int pid) throws RemoteException {
        if (2031 > datez.get(pid - 1)) {
            return true;
        }
        return false;
    }

    public void del(int pid) throws RemoteException {
        blacklist.add(pid);
    }

    public Boolean isExpire(String date) throws RemoteException {
        if (date.isEmpty() || date.trim().equals("")) {
            return false;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a"); // Jan-20-2015 1:30:55 PM
            Date d = null;
            Date d1 = null;
            String today = getToday("MMM-dd-yyyy hh:mm:ss a");
            try {
                // System.out.println("expdate>> "+date);
                // System.out.println("today>> "+today+"\n\n");
                d = sdf.parse(date);
                d1 = sdf.parse(today);
                if (d1.compareTo(d) < 0) {// not expired
                    return false;
                } else if (d.compareTo(d1) == 0) {// both date are same
                    if (d.getTime() < d1.getTime()) {// not expired
                        return false;
                    } else if (d.getTime() == d1.getTime()) {// expired
                        return true;
                    } else {// expired
                        return true;
                    }
                } else {// expired
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public String getToday(String format) throws RemoteException {
        Date date = new Date();
        return new SimpleDateFormat(format).format(date);
    }
}