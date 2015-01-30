package parte4;

import java.rmi.*;

public class ProvaADSL {

	public static void main(String[] args) throws RemoteException {
			ADSLImpl adsl = new ADSLImpl();
			try{
				
				java.rmi.registry.LocateRegistry.createRegistry(2000);
				Naming.rebind("rmi://192.168.1.71:2000/ADSL", adsl);
			}catch(Exception e){
				System.out.println("Failed to bind to RMI registry" + e);
				System.exit(1);
			}
		
	}

}
