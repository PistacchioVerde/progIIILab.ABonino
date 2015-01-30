package parte3;

import parte1.*;
import parte2.*;

public class MessageBox extends MessageBoxNoSync implements RemoteMessageBox{
	
	public MessageBox() throws Exception{
		super();
	}
	
	public MessageBox(PersonalAgentID agent) throws Exception{
		super(agent);
	}
	
	public PersonalAgentID getOwner() {
        return super.getOwner();
    }
	
	public synchronized void write(Message msg){
		super.write(msg);
		notifyAll();
	}
	
	public synchronized Message readMessage() throws Exception{
		while(true){
			try {
				Message m = super.readMessage();
				notifyAll();
				return m;
			} catch (JAMMessageBoxException e) {
				wait();
			}
		}
	}
	
	public synchronized Message readMessage(AgentID agent) throws Exception{
		while(true){
			try {
				Message m = super.readMessage(agent);
				notifyAll();
				return m;
			} catch (JAMMessageBoxException e) {
				wait();
			}
		}
	}
	
	public synchronized Message readMessage(Performative perf) throws Exception{
		while(true){
			try {
				Message m = super.readMessage(perf);
				notifyAll();
				return m;
			} catch (JAMMessageBoxException e) {
				wait();
			}
		}
	}
	
	public synchronized Message readMessage(AgentID agent, Performative perf) throws Exception{
		while(true){
			try {
				Message m = super.readMessage(agent, perf);
				notifyAll();
				return m;
			} catch (JAMMessageBoxException e) {
				wait();
			}
		}
	}
	
	public synchronized boolean isThereAMessage(){
		return super.isThereAMessage();
	}
	
	public synchronized boolean isThereAMessage(AgentID agent){
		return super.isThereAMessage(agent);
	}
	
	public synchronized boolean isThereAMessage(Performative perf) throws Exception{
		return super.isThereAMessage(perf);
	}
	
	public synchronized boolean isThereAMessage(AgentID agent, Performative perf) throws Exception{
		return super.isThereAMessage(perf);
	}
	
}