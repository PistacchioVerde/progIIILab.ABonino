package parte1;

import java.io.Serializable;

public class Message implements Serializable{

	private AgentID sender;
	private AgentID receiver;
	private Performative performative;
	private String content;
	private Object extraArgument;
	
	public Message(AgentID send, AgentID rec, Performative perf, String cont, Object extra){
		this.sender = send;
		this.receiver = rec;
		this.performative = perf;
		this.content = cont;
		this.extraArgument = extra;
	}

	public AgentID getSender() {
		return sender;
	}

	public void setSender(AgentID sender) {
		this.sender = sender;
	}

	public AgentID getReceiver() {
		return receiver;
	}

	public void setReceiver(AgentID receiver) {
		this.receiver = receiver;
	}

	public Performative getPerformative() {
		return performative;
	}

	public void setPerformative(Performative performative) {
		this.performative = performative;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object getExtraArgument() {
		return extraArgument;
	}

	public void setExtraArgument(Object extraArgument) {
		this.extraArgument = extraArgument;
	}
	
	@Override
	public String toString(){
		return "Performativa: " + this.performative + "\n"
				+ "Sender: (" + this.sender.getName() + ", " + this.sender.getCategory() + ")\n"
				+ "Receiver: (" + this.receiver.getName() + ", " + this.receiver.getCategory() + ")\n"
				+ "Content: " + this.content + "\n"
				+ "ExtraArgument: " + this.extraArgument.toString() + "\n";
	}

}