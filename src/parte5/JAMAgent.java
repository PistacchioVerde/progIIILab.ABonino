package parte5;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;

import parte1.*;
import parte3.*;
import parte4.*;
import parte7.*;

public abstract class JAMAgent extends Observable{

	private MessageBox myMessageBox;
	private PersonalAgentID myID;
	private ADSL adsl;
	private String name;
	private String ip;
	private int port;
	
	private ArrayList<JAMBehaviour> myBehaviours;
	
	public JAMAgent(PersonalAgentID agentID, String ip, String name, int port) throws Exception{
		myID = agentID;
		this.ip = ip;
		this.name = name;
		this.port = port;
		myMessageBox = new MessageBox(myID);
	}
	
	public JAMAgent(PersonalAgentID agentID) throws Exception{
		myID = agentID;
		ip = "127.0.0.1";
		port = 1099;
		name = "ADSL";
		myMessageBox = new MessageBox(myID);
	}
	
	public void init() throws Exception{
		adsl = (ADSL) Naming.lookup("rmi://"+ip+":"+port+"/"+name);
		adsl.insertRemoteMessageBox(this.myMessageBox);
	}
	
	public void destroy() throws Exception{
		adsl.removeRemoteMessageBox(this.myID);
		for(JAMBehaviour behave : myBehaviours){
			behave.done();
		}
	}
	
	public void send(Message msg) throws Exception{
		ArrayList<RemoteMessageBox> list = (ArrayList<RemoteMessageBox>) adsl.getRemoteMessageBox(msg.getReceiver());
		for(RemoteMessageBox msgBox : list){
			msgBox.write(msg);
			setChanged();
			notifyObservers("SEND message "+msg.getPerformative()+" to "+msg.getReceiver().toString());
		}
	}
	
	public Message receive() throws Exception{
		Message msg = this.myMessageBox.readMessage();
		setChanged();
		notifyObservers("RECEIVE message "+msg.getPerformative()+" from "+msg.getReceiver().toString());
		return msg;
	}
	
	public Message receive(AgentID agent) throws Exception{
		Message msg = this.myMessageBox.readMessage(agent);
		setChanged();
		notifyObservers("RECEIVE message "+msg.getPerformative()+" from "+msg.getReceiver().toString());
		return msg;
	}
	
	public Message receive(Performative perf) throws Exception{
		Message msg = this.myMessageBox.readMessage(perf);
		setChanged();
		notifyObservers("RECEIVE message "+msg.getPerformative()+" from "+msg.getReceiver().toString());
		return msg;
	}
	
	public Message receive(AgentID agent, Performative perf) throws Exception{
		Message msg = this.myMessageBox.readMessage(agent, perf);
		setChanged();
		notifyObservers("RECEIVE message "+msg.getPerformative()+" from "+msg.getReceiver().toString());
		return msg;
	}
	
	public boolean check(){
		return this.myMessageBox.isThereAMessage();
	}
	
	public boolean check(AgentID agent){
		return this.myMessageBox.isThereAMessage(agent);
	}
	
	public boolean check(Performative perf) throws Exception{
		return this.myMessageBox.isThereAMessage(perf);
	}
	
	public boolean check(AgentID agent, Performative perf) throws Exception{
		return this.myMessageBox.isThereAMessage(agent, perf);
	}
	
	public void addBehaviour(JAMBehaviour behave){
		this.myBehaviours.add(behave);
	}
	
	public void start(){
		Thread t;
		for(JAMBehaviour behave : myBehaviours){
			if(!behave.isRunning()){
				t = new Thread(behave);
				behave.setDone(false);
				behave.setMyThread(t);
				t.start();
			}
		}
	}
	
}