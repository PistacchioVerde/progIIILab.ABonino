package parte3;

import java.rmi.*;

import parte1.*;

public interface RemoteMessageBox extends Remote{
		
	public Message readMessage() throws Exception;
	public void write(Message msg) throws RemoteException;
	public PersonalAgentID getOwner() throws RemoteException;
	
}