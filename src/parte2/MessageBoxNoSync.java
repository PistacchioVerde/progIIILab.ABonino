package parte2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import parte1.*;

public class MessageBoxNoSync extends UnicastRemoteObject{
	
	protected PersonalAgentID owner;
	protected ArrayList<Message> box; //funziona senza problemi anche con Vector<E>
	
	public MessageBoxNoSync() throws Exception{
		this.owner = null;
		this.box = new ArrayList<Message>();
	}
	
	public MessageBoxNoSync(PersonalAgentID owner) throws RemoteException{
		this.owner = owner;
		this.box = new ArrayList<Message>();
	}
	
	public void setOwner(PersonalAgentID owner) {
		this.owner = owner;
	}
	
	
	public PersonalAgentID getOwner() {
		return owner;
	}

	public List<Message> getBox() {
		return box;
	}

	public void setBox(ArrayList<Message> box) {
		this.box = box;
	}
	
	public boolean isBoxEmpty(){
		return box.isEmpty();
	}
	
	public Message readMessage() throws Exception{
		if (this.isBoxEmpty()){ throw new JAMMessageBoxException();}
		return  (Message) box.remove(0);
	}
	
	public Message readMessage(AgentID agent) throws Exception{
		if (this.isBoxEmpty()) return null;
		for (Message msg : box){
			if(msg.getSender().equals(agent)){
				return box.remove(box.indexOf(msg));
			}
		}
		throw new JAMMessageBoxException();
	}
	
	public Message readMessage(Performative perf) throws Exception{
		if (this.isBoxEmpty()) return null;
		for (Message msg : box){
			if(msg.getPerformative().equals(perf)){
				return box.remove(box.indexOf(msg));
			}
		}
		throw new JAMMessageBoxException();
	}

	public Message readMessage(AgentID agent, Performative perf) throws Exception{
		if (this.isBoxEmpty()) return null;
		for (Message msg : box){
			if(msg.getSender().equals(agent) && msg.getPerformative().equals(perf)){
				return box.remove(box.indexOf(msg));
			}
		}
		throw new JAMMessageBoxException();
	}
	
	public boolean isThereAMessage(){
		return !this.isBoxEmpty();
	}
	
	public boolean isThereAMessage(AgentID agent){
		if (this.isBoxEmpty()) return false;
		for(Message msg : box){
			if(msg.getSender().equals(agent)) return true;
		}
		return false;
	}
	
	public boolean isThereAMessage(Performative perf) throws Exception{
		if (this.isBoxEmpty()) return false;
		for(Message msg : box){
			if(msg.getPerformative().equals(perf)) return true;
		}
		return false;
	}
	
	public boolean isThereAMessage(AgentID agent, Performative perf) throws Exception{
		if (this.isBoxEmpty()) return false;
		for(Message msg : box){
			if(msg.getSender().equals(agent) && msg.getPerformative().equals(perf)) return true;
		}
		return false;
	}
	
	public void write(Message msg){
		box.add(msg);
	}
	
	public String toString(){
		String str="";
		for(Message msg : box)
			str += "\n" + msg.toString() + "-------------------------";
		return str;
	}
	
}