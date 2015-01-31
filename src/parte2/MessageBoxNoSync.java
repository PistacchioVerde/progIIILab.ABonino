package parte2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import parte1.*;

public class MessageBoxNoSync extends UnicastRemoteObject{
	
	protected PersonalAgentID owner;
	protected List<Message> box; //funziona senza problemi anche con Vector<E>
	
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
	
	public Message readMessage() throws Exception{
		if (this.box.isEmpty()){ throw new JAMMessageBoxException();}
		return  (Message) box.remove(0);
	}
	
	public Message readMessage(AgentID agent) throws Exception{
		if (this.box.isEmpty()) return null;
		for (Message msg : box){
			if(msg.getSender().equals(agent)){
				return box.remove(box.indexOf(msg));
			}
		}
		throw new JAMMessageBoxException();
	}
	
	public Message readMessage(Performative perf) throws Exception{
		if (this.box.isEmpty()) return null;
		for (Message msg : box){
			if(msg.getPerformative().equals(perf)){
				return box.remove(box.indexOf(msg));
			}
		}
		throw new JAMMessageBoxException();
	}

	public Message readMessage(AgentID agent, Performative perf) throws Exception{
		if (this.box.isEmpty()) return null;
		for (Message msg : box){
			if(msg.getSender().equals(agent) && msg.getPerformative().equals(perf)){
				return box.remove(box.indexOf(msg));
			}
		}
		throw new JAMMessageBoxException();
	}
	
	public boolean isThereAMessage(){
		System.out.println("Ho usato IsThere di MSBoxNOSync");
		return !this.box.isEmpty();
	}
	
	public boolean isThereAMessage(AgentID agent){
		System.out.println("Ho usato IsThere di MSBoxNOSync");
		if (this.box.isEmpty()) return false;
		for(Message msg : box){
			if(msg.getSender().equals(agent)) return true;
		}
		return false;
	}
	
	public boolean isThereAMessage(Performative perf) throws Exception{
		System.out.println("Ho usato IsThere di MSBoxNOSync");
		if (this.box.isEmpty()) return false;
		for(Message msg : box){
			if(msg.getPerformative().equals(perf)) return true;
		}
		return false;
	}
	
	public boolean isThereAMessage(AgentID agent, Performative perf) throws Exception{
		System.out.println("Ho usato IsThere di MSBoxNOSync");
		if (this.box.isEmpty()) return false;
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