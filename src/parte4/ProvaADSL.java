package parte4;

import java.rmi.*;

public class ProvaADSL {

	public static void main(String[] args) throws RemoteException {
			ADSLImpl adsl = new ADSLImpl();
			try{
				
				java.rmi.registry.LocateRegistry.createRegistry(2000);
				Naming.rebind("rmi://127.0.0.1:2000/ADSL", adsl);
				System.out.println("Server is running...");
			}catch(Exception e){
				System.out.println("Failed to bind to RMI registry" + e);
				System.exit(1);
			}
		
	}

}
