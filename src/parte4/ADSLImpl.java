package parte4;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

import parte1.*;
import parte3.*;

public class ADSLImpl extends UnicastRemoteObject implements ADSL{

	private ArrayList<RemoteMessageBox> messageBoxes;
	
	public ADSLImpl() throws RemoteException{
		messageBoxes = new ArrayList<RemoteMessageBox>();
	}
	
	public synchronized ArrayList<RemoteMessageBox> getRemoteMessageBox(AgentID agent) throws RemoteException{
		ArrayList<RemoteMessageBox> list = new ArrayList<RemoteMessageBox>();
		for(RemoteMessageBox msgBox : messageBoxes)
			if(agent.equals(msgBox.getOwner())) list.add(msgBox);
		return list;
	}

	public synchronized void insertRemoteMessageBox(RemoteMessageBox messageBox) throws Exception {
		if(!isAlreadyHere(messageBox)){
			messageBoxes.add(messageBox);
		}	
	}

	public synchronized void removeRemoteMessageBox(AgentID agent) throws RemoteException {
		if (!messageBoxes.isEmpty()){
		for(RemoteMessageBox msgBox : messageBoxes)
			if(msgBox.getOwner().equals(agent)) messageBoxes.remove(msgBox);
		}
	}
	
	public boolean isAlreadyHere(RemoteMessageBox box) throws RemoteException{
		if (messageBoxes.contains(box)) return true;
		for(RemoteMessageBox msgBox : messageBoxes)
			if(msgBox.getOwner().equals(box.getOwner())) return true;
		return false;
	}

}