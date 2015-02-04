package parte4;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

import parte1.*;
import parte3.*;

public class ADSLImpl extends UnicastRemoteObject implements ADSL{

	private List<RemoteMessageBox> messageBoxes;
	
	private Observer obs;
	
	private int port;
	private String name;
	
	public ADSLImpl() throws RemoteException{
		messageBoxes = new ArrayList<RemoteMessageBox>();
		this.name = "ADSL";
	}
	
	public ADSLImpl(int port) throws RemoteException{
		this.port = port;
		this.name = "ADSL";
		this.messageBoxes = new ArrayList<RemoteMessageBox>();
	}
	
	public void startRMIRegistry(){
		try {
			java.rmi.registry.LocateRegistry.createRegistry(port);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void startADSL(){
		try {
			Naming.rebind("rmi://127.0.0.1:"+port+"/"+name, this);
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void stopADSL(){
		try {
			Naming.unbind("rmi://127.0.0.1:"+port+"/"+name);
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public void setPort(int port){
		this.port = port;
	}
	
	public int getPort(){
		return this.port;
	}
	
	public synchronized ArrayList<RemoteMessageBox> getRemoteMessageBox(AgentID agent) throws RemoteException{
		ArrayList<RemoteMessageBox> list = new ArrayList<RemoteMessageBox>();
		for(RemoteMessageBox msgBox : messageBoxes)
			if(agent.equals(msgBox.getOwner())) list.add(msgBox);
		if (list.size() <= 0)  throw new RemoteException("Box messaggi di " +agent.getName()+ " non trovato");
		return list;
	}

	public synchronized void insertRemoteMessageBox(RemoteMessageBox messageBox) throws Exception {
		if(!isAlreadyHere(messageBox)){
			messageBoxes.add(messageBox);
		}	
	}

	public synchronized void removeRemoteMessageBox(AgentID agent) throws RemoteException {
		if (!messageBoxes.isEmpty()){
			for(int i=0; i<messageBoxes.size(); i++){
				if(messageBoxes.get(i).getOwner().equals(agent)) {
					messageBoxes.remove(i--);
				}
			}
		}
	}
	
	public synchronized boolean isAlreadyHere(RemoteMessageBox box) throws RemoteException{
		if (messageBoxes.contains(box)) return true;
		for(RemoteMessageBox msgBox : messageBoxes)
			if(msgBox.getOwner().equals(box.getOwner())) return true;
		return false;
	}

}