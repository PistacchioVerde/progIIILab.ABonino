package parte4;

import java.util.*;

import javax.swing.JTextArea;

import parte1.*;

public class Observer implements Runnable{

	private String name;
	private boolean running;
	private JTextArea monitor;
	private List<String> log;
	
	public Observer(JTextArea monitor){
		this.running = false;
		this.monitor = monitor;
	}
	
	public synchronized void update(GenericAgentID agent, String msg){
		this.log.add(agent.getName() + ": "+msg);
		notifyAll();
	}

	public void run() {
		this.running = true;
		while(running){
			if(this.log.size() <= 0){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.monitor.append(this.log.remove(0));
		}
	}
	
	public void setRunningState(boolean state){
		this.running = state;
	}
	
}
