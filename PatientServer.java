import java.rmi.*;
public class PatientServer
{
    public static void main(String args[])
    {
        try
        {
            PatientImpl patientimpl=new PatientImpl();
            Naming.rebind("HOSPITAL_SERVER",patientimpl);
            System.out.println("Server is ready");
        }

        catch(Exception e)
        {
            System.out.println("Exception :"+e);
        }
    }
}